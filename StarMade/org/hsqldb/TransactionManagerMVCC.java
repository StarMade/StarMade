package org.hsqldb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.lib.CountUpDownLatch;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManagerMVCC extends TransactionManagerCommon
  implements TransactionManager
{
  HsqlDeque committedTransactions = new HsqlDeque();
  LongDeque committedTransactionTimestamps = new LongDeque();
  boolean isLockedMode;
  Session catalogWriteSession;
  long lockTxTs;
  long lockSessionId;
  long unlockTxTs;
  long unlockSessionId;
  int redoCount = 0;

  public TransactionManagerMVCC(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.rowActionMap = new LongKeyHashMap(10000);
    this.txModel = 2;
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
    return true;
  }

  public int getTransactionControl()
  {
    return 2;
  }

  public void setTransactionControl(Session paramSession, int paramInt)
  {
    super.setTransactionControl(paramSession, paramInt);
  }

  public void completeActions(Session paramSession)
  {
  }

  public boolean prepareCommitActions(Session paramSession)
  {
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    int i = paramSession.rowActionList.size();
    if (paramSession.abortTransaction)
      return false;
    this.writeLock.lock();
    try
    {
      Object localObject1;
      for (int j = 0; j < i; j++)
      {
        localObject1 = (RowAction)arrayOfObject[j];
        if (!((RowAction)localObject1).canCommit(paramSession, paramSession.tempSet))
        {
          boolean bool = false;
          return bool;
        }
      }
      paramSession.actionTimestamp = nextChangeTimestamp();
      for (j = 0; j < i; j++)
      {
        localObject1 = (RowAction)arrayOfObject[j];
        ((RowAction)localObject1).prepareCommit(paramSession);
      }
      for (j = 0; j < paramSession.tempSet.size(); j++)
      {
        localObject1 = ((RowActionBase)paramSession.tempSet.get(j)).session;
        ((Session)localObject1).abortTransaction = true;
      }
      j = 1;
      return j;
    }
    finally
    {
      this.writeLock.unlock();
      paramSession.tempSet.clear();
    }
  }

  public boolean commitTransaction(Session paramSession)
  {
    if (paramSession.abortTransaction)
      return false;
    int i = paramSession.rowActionList.size();
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    this.writeLock.lock();
    try
    {
      Object localObject1;
      for (int j = 0; j < i; j++)
      {
        localObject1 = (RowAction)arrayOfObject[j];
        if (!((RowAction)localObject1).canCommit(paramSession, paramSession.tempSet))
        {
          boolean bool = false;
          return bool;
        }
      }
      paramSession.actionTimestamp = nextChangeTimestamp();
      paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
      endTransaction(paramSession);
      for (j = 0; j < i; j++)
      {
        localObject1 = (RowAction)arrayOfObject[j];
        ((RowAction)localObject1).commit(paramSession);
      }
      for (j = 0; j < paramSession.tempSet.size(); j++)
      {
        localObject1 = ((RowActionBase)paramSession.tempSet.get(j)).session;
        ((Session)localObject1).abortTransaction = true;
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
      else if (paramSession.rowActionList.size() > 0)
      {
        arrayOfObject = paramSession.rowActionList.toArray();
        addToCommittedQueue(paramSession, arrayOfObject);
      }
      endTransactionTPL(paramSession);
      paramSession.isTransaction = false;
      countDownLatches(paramSession);
    }
    finally
    {
      this.writeLock.unlock();
      paramSession.tempSet.clear();
    }
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
      paramSession.isTransaction = false;
      countDownLatches(paramSession);
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
  }

  void rollbackPartial(Session paramSession, int paramInt, long paramLong)
  {
    Object[] arrayOfObject = paramSession.rowActionList.getArray();
    int i = paramSession.rowActionList.size();
    if (paramInt == i)
      return;
    for (int j = paramInt; j < i; j++)
    {
      RowAction localRowAction = (RowAction)arrayOfObject[j];
      if (localRowAction == null)
        throw Error.runtimeError(458, "null rollback action ");
      localRowAction.rollback(paramSession, paramLong);
    }
    this.writeLock.lock();
    try
    {
      mergeRolledBackTransaction(paramSession, paramLong, arrayOfObject, paramInt, i);
      finaliseRows(paramSession, arrayOfObject, paramInt, i, false);
    }
    finally
    {
      this.writeLock.unlock();
    }
    paramSession.rowActionList.setSize(paramInt);
  }

  public RowAction addDeleteAction(Session paramSession, Table paramTable, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = addDeleteActionToRow(paramSession, paramTable, paramRow, paramArrayOfInt);
    Session localSession = null;
    boolean bool = true;
    if (localRowAction == null)
    {
      this.writeLock.lock();
      try
      {
        rollbackAction(paramSession);
        if ((paramSession.isolationLevel == 4) || (paramSession.isolationLevel == 8))
        {
          paramSession.tempSet.clear();
          paramSession.redoAction = false;
          paramSession.abortTransaction = paramSession.txConflictRollback;
          throw Error.error(4871);
        }
        if ((paramRow.rowAction != null) && (paramRow.rowAction.isDeleted()))
        {
          paramSession.tempSet.clear();
          paramSession.redoAction = true;
          this.redoCount += 1;
          throw Error.error(4871);
        }
        bool = !paramSession.tempSet.isEmpty();
        if (bool)
        {
          localSession = ((RowActionBase)paramSession.tempSet.get(0)).session;
          paramSession.tempSet.clear();
          if (localSession != null)
            bool = checkDeadlock(paramSession, localSession);
        }
        if (bool)
        {
          paramSession.redoAction = true;
          if (localSession != null)
          {
            localSession.waitingSessions.add(paramSession);
            paramSession.waitedSessions.add(localSession);
            paramSession.latch.countUp();
          }
          this.redoCount += 1;
        }
        else
        {
          paramSession.redoAction = false;
          paramSession.abortTransaction = paramSession.txConflictRollback;
        }
        throw Error.error(4871);
      }
      finally
      {
        this.writeLock.unlock();
      }
    }
    paramSession.rowActionList.add(localRowAction);
    return localRowAction;
  }

  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    Session localSession = null;
    boolean bool = false;
    int i = 1;
    Object localObject1 = null;
    if (localRowAction == null)
      throw Error.runtimeError(458, "null insert action ");
    if (paramTable.tableType == 5)
      this.rowActionMap.put(localRowAction.getPos(), localRowAction);
    try
    {
      paramPersistentStore.indexRow(paramSession, paramRow);
    }
    catch (HsqlException localHsqlException)
    {
      if (paramSession.tempSet.isEmpty())
        throw localHsqlException;
      bool = true;
      localObject1 = localHsqlException;
    }
    if (!bool)
    {
      paramSession.rowActionList.add(localRowAction);
      return;
    }
    this.writeLock.lock();
    try
    {
      rollbackAction(paramSession);
      RowActionBase localRowActionBase = (RowActionBase)paramSession.tempSet.get(0);
      localSession = localRowActionBase.session;
      paramSession.tempSet.clear();
      if (localRowActionBase.commitTimestamp != 0L)
        i = 0;
      switch (paramSession.isolationLevel)
      {
      case 4:
      case 8:
        bool = false;
        break;
      default:
        bool = checkDeadlock(paramSession, localSession);
      }
      if (bool)
      {
        paramSession.redoAction = true;
        if (i != 0)
        {
          localSession.waitingSessions.add(paramSession);
          paramSession.waitedSessions.add(localSession);
          paramSession.latch.countUp();
        }
        this.redoCount += 1;
      }
      else
      {
        paramSession.abortTransaction = paramSession.txConflictRollback;
        paramSession.redoAction = false;
      }
      throw Error.error(localObject1, 4871, null);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public boolean canRead(Session paramSession, Row paramRow, int paramInt, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (paramInt == 0)
    {
      if (localRowAction == null)
        return true;
      return localRowAction.canRead(paramSession, 0);
    }
    if (paramInt == 2)
    {
      boolean bool;
      if (localRowAction == null)
        bool = true;
      else
        bool = localRowAction.canRead(paramSession, 0);
      return bool;
    }
    if (localRowAction == null)
      return true;
    return localRowAction.canRead(paramSession, paramInt);
  }

  public boolean canRead(Session paramSession, long paramLong, int paramInt)
  {
    RowAction localRowAction = (RowAction)this.rowActionMap.get(paramLong);
    if (localRowAction == null)
      return true;
    return localRowAction.canRead(paramSession, paramInt);
  }

  public void setTransactionInfo(CachedObject paramCachedObject)
  {
    if (paramCachedObject.isMemory())
      return;
    Row localRow = (Row)paramCachedObject;
    RowAction localRowAction = (RowAction)this.rowActionMap.get(localRow.position);
    localRow.rowAction = localRowAction;
  }

  public void removeTransactionInfo(CachedObject paramCachedObject)
  {
    if (paramCachedObject.isMemory())
      return;
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
    while (true)
    {
      long l2;
      Object[] arrayOfObject;
      synchronized (this.committedTransactionTimestamps)
      {
        if (this.committedTransactionTimestamps.isEmpty())
          break;
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
        this.liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
        this.transactionCount += 1;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void beginAction(Session paramSession, Statement paramStatement)
  {
    if (paramSession.isTransaction)
      return;
    if (paramStatement == null)
      return;
    this.writeLock.lock();
    try
    {
      if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp())
      {
        paramStatement = paramSession.statementManager.getStatement(paramSession, paramStatement);
        paramSession.sessionContext.currentStatement = paramStatement;
        if (paramStatement == null)
          return;
      }
      paramSession.isPreTransaction = true;
      if ((!this.isLockedMode) && (!paramStatement.isCatalogLock()))
        return;
      beginActionTPL(paramSession, paramStatement);
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
      paramSession.isPreTransaction = false;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  RowAction addDeleteActionToRow(Session paramSession, Table paramTable, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = null;
    synchronized (paramRow)
    {
      if (paramTable.tableType == 5)
      {
        Lock localLock = this.rowActionMap.getWriteLock();
        localLock.lock();
        try
        {
          localRowAction = (RowAction)this.rowActionMap.get(paramRow.getPos());
          if (localRowAction == null)
          {
            localRowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfInt);
            if (localRowAction != null)
              this.rowActionMap.put(paramRow.getPos(), localRowAction);
          }
          else
          {
            paramRow.rowAction = localRowAction;
            localRowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfInt);
          }
        }
        finally
        {
          localLock.unlock();
        }
      }
      else
      {
        localRowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfInt);
      }
    }
    return localRowAction;
  }

  void endTransaction(Session paramSession)
  {
    long l = paramSession.transactionTimestamp;
    int i = this.liveTransactionTimestamps.indexOf(l);
    if (i >= 0)
    {
      this.transactionCount -= 1;
      this.liveTransactionTimestamps.remove(i);
      mergeExpiredTransactions(paramSession);
    }
  }

  private void countDownLatches(Session paramSession)
  {
    for (int i = 0; i < paramSession.waitingSessions.size(); i++)
    {
      Session localSession = (Session)paramSession.waitingSessions.get(i);
      localSession.waitedSessions.remove(paramSession);
      localSession.latch.countDown();
    }
    paramSession.waitingSessions.clear();
  }

  void getTransactionSessions(HashSet paramHashSet)
  {
    Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
    for (int i = 0; i < arrayOfSession.length; i++)
    {
      long l = arrayOfSession[i].getTransactionTimestamp();
      if (this.liveTransactionTimestamps.contains(l))
        paramHashSet.add(arrayOfSession[i]);
      else if (arrayOfSession[i].isPreTransaction)
        paramHashSet.add(arrayOfSession[i]);
      else if (arrayOfSession[i].isTransaction)
        paramHashSet.add(arrayOfSession[i]);
    }
  }

  void endTransactionTPL(Session paramSession)
  {
    if (this.catalogWriteSession != paramSession)
      return;
    Object localObject = null;
    paramSession.waitingSessions.size();
    Session localSession;
    for (int i = 0; i < paramSession.waitingSessions.size(); i++)
    {
      localSession = (Session)paramSession.waitingSessions.get(i);
      Statement localStatement = localSession.sessionContext.currentStatement;
      if ((localStatement != null) && (localStatement.isCatalogLock()))
      {
        localObject = localSession;
        break;
      }
    }
    if (localObject == null)
    {
      this.catalogWriteSession = null;
      this.isLockedMode = false;
    }
    else
    {
      for (i = 0; i < paramSession.waitingSessions.size(); i++)
      {
        localSession = (Session)paramSession.waitingSessions.get(i);
        if (localSession != localObject)
        {
          localSession.waitedSessions.add(localObject);
          localObject.waitingSessions.add(localSession);
          localSession.latch.countUp();
        }
      }
      this.catalogWriteSession = localObject;
    }
    this.unlockTxTs = paramSession.actionTimestamp;
    this.unlockSessionId = paramSession.getId();
  }

  boolean beginActionTPL(Session paramSession, Statement paramStatement)
  {
    if (paramStatement == null)
      return true;
    if (paramSession.abortTransaction)
      return false;
    if (paramSession == this.catalogWriteSession)
      return true;
    paramSession.tempSet.clear();
    if ((paramStatement.isCatalogLock()) && (this.catalogWriteSession == null))
    {
      this.catalogWriteSession = paramSession;
      this.isLockedMode = true;
      this.lockTxTs = paramSession.actionTimestamp;
      this.lockSessionId = paramSession.getId();
      getTransactionSessions(paramSession.tempSet);
      paramSession.tempSet.remove(paramSession);
      if (!paramSession.tempSet.isEmpty())
        setWaitingSessionTPL(paramSession);
      return true;
    }
    if (!this.isLockedMode)
      return true;
    if (paramStatement.getTableNamesForWrite().length > 0)
    {
      if (paramStatement.getTableNamesForWrite()[0].schema == SqlInvariants.LOBS_SCHEMA_HSQLNAME)
        return true;
    }
    else if (paramStatement.getTableNamesForRead().length > 0)
    {
      if (paramStatement.getTableNamesForRead()[0].schema == SqlInvariants.LOBS_SCHEMA_HSQLNAME)
        return true;
    }
    else
      return true;
    if (paramSession.waitingSessions.contains(this.catalogWriteSession))
      return true;
    if (this.catalogWriteSession.waitingSessions.add(paramSession))
    {
      paramSession.waitedSessions.add(this.catalogWriteSession);
      paramSession.latch.countUp();
    }
    return true;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TransactionManagerMVCC
 * JD-Core Version:    0.6.2
 */