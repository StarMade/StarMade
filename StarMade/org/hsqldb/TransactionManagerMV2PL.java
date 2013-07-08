package org.hsqldb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManagerMV2PL
  extends TransactionManagerCommon
  implements TransactionManager
{
  HsqlDeque committedTransactions = new HsqlDeque();
  LongDeque committedTransactionTimestamps = new LongDeque();
  
  public TransactionManagerMV2PL(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.rowActionMap = new LongKeyHashMap(10000);
    this.txModel = 1;
    this.catalogNameList = new HsqlNameManager.HsqlName[] { this.database.getCatalogName() };
  }
  
  public long getGlobalChangeTimestamp()
  {
    return this.globalChangeTimestamp.get();
  }
  
  public boolean isMVRows()
  {
    return true;
  }
  
  public boolean isMVCC()
  {
    return false;
  }
  
  public int getTransactionControl()
  {
    return 1;
  }
  
  public void setTransactionControl(Session paramSession, int paramInt)
  {
    super.setTransactionControl(paramSession, paramInt);
  }
  
  public void completeActions(Session paramSession)
  {
    endActionTPL(paramSession);
  }
  
  public boolean prepareCommitActions(Session paramSession)
  {
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    int i = paramSession.rowActionList.size();
    this.writeLock.lock();
    try
    {
      paramSession.actionTimestamp = nextChangeTimestamp();
      for (int j = 0; j < i; j++)
      {
        RowAction localRowAction = (RowAction)arrayOfObject[j];
        localRowAction.prepareCommit(paramSession);
      }
      j = 1;
      return j;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public boolean commitTransaction(Session paramSession)
  {
    if (paramSession.abortTransaction) {
      return false;
    }
    int i = paramSession.rowActionList.size();
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    this.writeLock.lock();
    try
    {
      paramSession.actionTimestamp = nextChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      endTransaction(paramSession);
      for (int j = 0; j < i; j++)
      {
        RowAction localRowAction = (RowAction)arrayOfObject[j];
        localRowAction.commit(paramSession);
      }
      persistCommit(paramSession, arrayOfObject, i);
      j = paramSession.rowActionList.size();
      if (j > i)
      {
        arrayOfObject = paramSession.rowActionList.getArray();
        mergeTransaction(paramSession, arrayOfObject, i, j, paramSession.actionTimestamp);
        finaliseRows(paramSession, arrayOfObject, i, j, true);
        paramSession.rowActionList.setSize(i);
      }
      if ((getFirstLiveTransactionTimestamp() > paramSession.actionTimestamp) || (paramSession == this.lobSession))
      {
        mergeTransaction(paramSession, arrayOfObject, 0, i, paramSession.actionTimestamp);
        finaliseRows(paramSession, arrayOfObject, 0, i, true);
      }
      else
      {
        arrayOfObject = paramSession.rowActionList.toArray();
        addToCommittedQueue(paramSession, arrayOfObject);
      }
      endTransactionTPL(paramSession);
    }
    finally
    {
      this.writeLock.unlock();
    }
    paramSession.tempSet.clear();
    return true;
  }
  
  public void rollback(Session paramSession)
  {
    this.writeLock.lock();
    try
    {
      paramSession.abortTransaction = false;
      paramSession.actionTimestamp = nextChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      rollbackPartial(paramSession, 0, paramSession.transactionTimestamp);
      endTransaction(paramSession);
      endTransactionTPL(paramSession);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void rollbackSavepoint(Session paramSession, int paramInt)
  {
    long l = paramSession.sessionContext.savepointTimestamps.get(paramInt);
    Integer localInteger = (Integer)paramSession.sessionContext.savepoints.get(paramInt);
    int i = localInteger.intValue();
    while (paramSession.sessionContext.savepoints.size() > paramInt + 1)
    {
      paramSession.sessionContext.savepoints.remove(paramSession.sessionContext.savepoints.size() - 1);
      paramSession.sessionContext.savepointTimestamps.removeLast();
    }
    rollbackPartial(paramSession, i, l);
  }
  
  public void rollbackAction(Session paramSession)
  {
    rollbackPartial(paramSession, paramSession.actionIndex, paramSession.actionTimestamp);
    endActionTPL(paramSession);
  }
  
  void rollbackPartial(Session paramSession, int paramInt, long paramLong)
  {
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    int i = paramSession.rowActionList.size();
    if (paramInt == i) {
      return;
    }
    for (int j = paramInt; j < i; j++)
    {
      RowAction localRowAction = (RowAction)arrayOfObject[j];
      if (localRowAction == null) {
        throw Error.runtimeError(458, "null rollback action ");
      }
      localRowAction.rollback(paramSession, paramLong);
    }
    mergeRolledBackTransaction(paramSession, paramLong, arrayOfObject, paramInt, i);
    finaliseRows(paramSession, arrayOfObject, paramInt, i, false);
    paramSession.rowActionList.setSize(paramInt);
  }
  
  public RowAction addDeleteAction(Session paramSession, Table paramTable, Row paramRow, int[] paramArrayOfInt)
  {
    int i;
    RowAction localRowAction;
    synchronized (paramRow)
    {
      i = paramRow.rowAction == null ? 1 : 0;
      localRowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfInt);
    }
    paramSession.rowActionList.add(localRowAction);
    if ((i != 0) && (paramTable.tableType == 5)) {
      this.rowActionMap.put(localRowAction.getPos(), localRowAction);
    }
    return localRowAction;
  }
  
  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (localRowAction == null) {
      throw Error.runtimeError(458, "null insert action ");
    }
    if (paramTable.tableType == 5) {
      this.rowActionMap.put(localRowAction.getPos(), localRowAction);
    }
    paramPersistentStore.indexRow(paramSession, paramRow);
    paramSession.rowActionList.add(localRowAction);
  }
  
  public boolean canRead(Session paramSession, Row paramRow, int paramInt, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (localRowAction == null) {
      return true;
    }
    return localRowAction.canRead(paramSession, 0);
  }
  
  public boolean canRead(Session paramSession, long paramLong, int paramInt)
  {
    RowAction localRowAction = (RowAction)this.rowActionMap.get(paramLong);
    return localRowAction == null ? true : localRowAction.canRead(paramSession, 0);
  }
  
  public void setTransactionInfo(CachedObject paramCachedObject)
  {
    if (paramCachedObject.isMemory()) {
      return;
    }
    Row localRow = (Row)paramCachedObject;
    RowAction localRowAction = (RowAction)this.rowActionMap.get(localRow.position);
    localRow.rowAction = localRowAction;
  }
  
  public void removeTransactionInfo(CachedObject paramCachedObject)
  {
    if (paramCachedObject.isMemory()) {
      return;
    }
    this.rowActionMap.remove(paramCachedObject.getPos());
  }
  
  void addToCommittedQueue(Session paramSession, Object[] paramArrayOfObject)
  {
    synchronized (this.committedTransactionTimestamps)
    {
      this.committedTransactions.addLast(paramArrayOfObject);
      this.committedTransactionTimestamps.addLast(paramSession.actionTimestamp);
    }
  }
  
  void mergeExpiredTransactions(Session paramSession)
  {
    long l1 = getFirstLiveTransactionTimestamp();
    for (;;)
    {
      long l2 = 0L;
      Object[] arrayOfObject = null;
      synchronized (this.committedTransactionTimestamps)
      {
        if (this.committedTransactionTimestamps.isEmpty()) {
          break;
        }
        l2 = this.committedTransactionTimestamps.getFirst();
        if (l2 < l1)
        {
          this.committedTransactionTimestamps.removeFirst();
          arrayOfObject = (Object[])this.committedTransactions.removeFirst();
        }
        else
        {
          break;
        }
      }
      mergeTransaction(paramSession, arrayOfObject, 0, arrayOfObject.length, l2);
      finaliseRows(paramSession, arrayOfObject, 0, arrayOfObject.length, true);
    }
  }
  
  public void beginTransaction(Session paramSession)
  {
    this.writeLock.lock();
    try
    {
      if (!paramSession.isTransaction)
      {
        paramSession.actionTimestamp = nextChangeTimestamp();
        paramSession.transactionTimestamp = paramSession.actionTimestamp;
        paramSession.isTransaction = true;
        this.transactionCount += 1;
        this.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void beginAction(Session paramSession, Statement paramStatement)
  {
    if (paramSession.hasLocks(paramStatement)) {
      return;
    }
    this.writeLock.lock();
    try
    {
      if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp())
      {
        paramStatement = paramSession.statementManager.getStatement(paramSession, paramStatement);
        paramSession.sessionContext.currentStatement = paramStatement;
        if (paramStatement == null) {
          return;
        }
      }
      boolean bool = setWaitedSessionsTPL(paramSession, paramStatement);
      if (bool)
      {
        if (paramSession.tempSet.isEmpty()) {
          lockTablesTPL(paramSession, paramStatement);
        } else {
          setWaitingSessionTPL(paramSession);
        }
      }
      else {
        paramSession.abortTransaction = true;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void beginActionResume(Session paramSession)
  {
    this.writeLock.lock();
    try
    {
      paramSession.actionTimestamp = nextChangeTimestamp();
      if (!paramSession.isTransaction)
      {
        paramSession.transactionTimestamp = paramSession.actionTimestamp;
        paramSession.isTransaction = true;
        this.liveTransactionTimestamps.addLast(paramSession.actionTimestamp);
        this.transactionCount += 1;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void endTransaction(Session paramSession)
  {
    long l = paramSession.transactionTimestamp;
    paramSession.isTransaction = false;
    int i = this.liveTransactionTimestamps.indexOf(l);
    if (i >= 0)
    {
      this.transactionCount -= 1;
      this.liveTransactionTimestamps.remove(i);
      mergeExpiredTransactions(paramSession);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TransactionManagerMV2PL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */