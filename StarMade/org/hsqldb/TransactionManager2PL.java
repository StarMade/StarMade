package org.hsqldb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public class TransactionManager2PL
  extends TransactionManagerCommon
  implements TransactionManager
{
  public TransactionManager2PL(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.lobSession = this.database.sessionManager.getSysLobSession();
    this.txModel = 0;
  }
  
  public long getGlobalChangeTimestamp()
  {
    return this.globalChangeTimestamp.get();
  }
  
  public boolean isMVRows()
  {
    return false;
  }
  
  public boolean isMVCC()
  {
    return false;
  }
  
  public int getTransactionControl()
  {
    return 0;
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
    paramSession.actionTimestamp = nextChangeTimestamp();
    return true;
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
    paramSession.abortTransaction = false;
    paramSession.actionTimestamp = nextChangeTimestamp();
    paramSession.transactionEndTimestamp = paramSession.actionTimestamp;
    rollbackPartial(paramSession, 0, paramSession.transactionTimestamp);
    endTransaction(paramSession);
    this.writeLock.lock();
    try
    {
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
    for (int j = i - 1; j >= paramInt; j--)
    {
      RowAction localRowAction = (RowAction)arrayOfObject[j];
      if ((localRowAction != null) && (localRowAction.type != 0) && (localRowAction.type != 3))
      {
        Row localRow = localRowAction.memoryRow;
        if (localRow == null) {
          localRow = (Row)localRowAction.store.get(localRowAction.getPos(), false);
        }
        if (localRow != null)
        {
          localRowAction.rollback(paramSession, paramLong);
          int k = localRowAction.mergeRollback(paramSession, paramLong, localRow);
          localRowAction.store.rollbackRow(paramSession, localRow, k, this.txModel);
        }
      }
    }
    paramSession.rowActionList.setSize(paramInt);
  }
  
  public RowAction addDeleteAction(Session paramSession, Table paramTable, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction;
    synchronized (paramRow)
    {
      localRowAction = RowAction.addDeleteAction(paramSession, paramTable, paramRow, paramArrayOfInt);
    }
    paramSession.rowActionList.add(localRowAction);
    ??? = paramTable.getRowStore(paramSession);
    ((PersistentStore)???).delete(paramSession, paramRow);
    paramRow.rowAction = null;
    return localRowAction;
  }
  
  public void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (localRowAction == null) {
      throw Error.runtimeError(458, "null insert action ");
    }
    paramPersistentStore.indexRow(paramSession, paramRow);
    paramSession.rowActionList.add(localRowAction);
    paramRow.rowAction = null;
  }
  
  public boolean canRead(Session paramSession, Row paramRow, int paramInt, int[] paramArrayOfInt)
  {
    return true;
  }
  
  public boolean canRead(Session paramSession, long paramLong, int paramInt)
  {
    return true;
  }
  
  public void setTransactionInfo(CachedObject paramCachedObject) {}
  
  public void removeTransactionInfo(CachedObject paramCachedObject) {}
  
  public void beginTransaction(Session paramSession)
  {
    if (!paramSession.isTransaction)
    {
      paramSession.actionTimestamp = nextChangeTimestamp();
      paramSession.transactionTimestamp = paramSession.actionTimestamp;
      paramSession.isTransaction = true;
      this.transactionCount += 1;
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
      if (bool) {
        if (paramSession.tempSet.isEmpty()) {
          lockTablesTPL(paramSession, paramStatement);
        } else {
          setWaitingSessionTPL(paramSession);
        }
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void beginActionResume(Session paramSession)
  {
    paramSession.actionTimestamp = nextChangeTimestamp();
    if (!paramSession.isTransaction)
    {
      paramSession.transactionTimestamp = paramSession.actionTimestamp;
      paramSession.isTransaction = true;
      this.transactionCount += 1;
    }
  }
  
  void endTransaction(Session paramSession)
  {
    if (paramSession.isTransaction)
    {
      paramSession.isTransaction = false;
      this.transactionCount -= 1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.TransactionManager2PL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */