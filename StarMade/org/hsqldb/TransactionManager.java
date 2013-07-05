package org.hsqldb;

import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;

public abstract interface TransactionManager
{
  public static final int LOCKS = 0;
  public static final int MVLOCKS = 1;
  public static final int MVCC = 2;
  public static final int ACTION_READ = 0;
  public static final int ACTION_DUP = 1;
  public static final int ACTION_REF = 2;

  public abstract long getGlobalChangeTimestamp();

  public abstract RowAction addDeleteAction(Session paramSession, Table paramTable, Row paramRow, int[] paramArrayOfInt);

  public abstract void addInsertAction(Session paramSession, Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfInt);

  public abstract void beginAction(Session paramSession, Statement paramStatement);

  public abstract void beginActionResume(Session paramSession);

  public abstract void beginTransaction(Session paramSession);

  public abstract boolean canRead(Session paramSession, Row paramRow, int paramInt, int[] paramArrayOfInt);

  public abstract boolean canRead(Session paramSession, long paramLong, int paramInt);

  public abstract boolean commitTransaction(Session paramSession);

  public abstract void completeActions(Session paramSession);

  public abstract int getTransactionControl();

  public abstract boolean isMVRows();

  public abstract boolean isMVCC();

  public abstract boolean prepareCommitActions(Session paramSession);

  public abstract void rollback(Session paramSession);

  public abstract void rollbackAction(Session paramSession);

  public abstract void rollbackSavepoint(Session paramSession, int paramInt);

  public abstract void setTransactionControl(Session paramSession, int paramInt);

  public abstract void setTransactionInfo(CachedObject paramCachedObject);

  public abstract void removeTransactionInfo(CachedObject paramCachedObject);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TransactionManager
 * JD-Core Version:    0.6.2
 */