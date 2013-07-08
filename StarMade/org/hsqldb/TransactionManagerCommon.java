package org.hsqldb;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.CountUpDownLatch;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;

class TransactionManagerCommon
{
  Database database;
  Session lobSession;
  int txModel;
  HsqlNameManager.HsqlName[] catalogNameList;
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
  LongDeque liveTransactionTimestamps = new LongDeque();
  AtomicLong globalChangeTimestamp = new AtomicLong(1L);
  long globalDataChangeTimestamp;
  int transactionCount = 0;
  HashMap tableWriteLocks = new HashMap();
  MultiValueHashMap tableReadLocks = new MultiValueHashMap();
  public LongKeyHashMap rowActionMap;
  
  void setTransactionControl(Session paramSession, int paramInt)
  {
    Object localObject1 = null;
    if (paramInt == this.txModel) {
      return;
    }
    this.writeLock.lock();
    switch (this.txModel)
    {
    case 1: 
    case 2: 
      if (this.liveTransactionTimestamps.size() != 1) {
        throw Error.error(3701);
      }
      break;
    }
    try
    {
      switch (paramInt)
      {
      case 2: 
        localObject1 = new TransactionManagerMVCC(this.database);
        ((TransactionManagerCommon)localObject1).liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
        break;
      case 1: 
        localObject1 = new TransactionManagerMV2PL(this.database);
        ((TransactionManagerCommon)localObject1).liveTransactionTimestamps.addLast(paramSession.transactionTimestamp);
        break;
      case 0: 
        localObject1 = new TransactionManager2PL(this.database);
      }
      ((TransactionManagerCommon)localObject1).globalChangeTimestamp.set(this.globalChangeTimestamp.get());
      ((TransactionManagerCommon)localObject1).transactionCount = this.transactionCount;
      this.database.txManager = ((TransactionManager)localObject1);
      return;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void persistCommit(Session paramSession, Object[] paramArrayOfObject, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
    {
      RowAction localRowAction1 = (RowAction)paramArrayOfObject[i];
      if (localRowAction1.type != 0)
      {
        int j = localRowAction1.getCommitTypeOn(paramSession.actionTimestamp);
        Row localRow = localRowAction1.memoryRow;
        if (localRow == null) {
          localRow = (Row)localRowAction1.store.get(localRowAction1.getPos(), false);
        }
        if (localRowAction1.table.hasLobColumn)
        {
          switch (j)
          {
          case 1: 
            paramSession.sessionData.adjustLobUsageCount(localRowAction1.table, localRow.getData(), 1);
            break;
          case 2: 
            paramSession.sessionData.adjustLobUsageCount(localRowAction1.table, localRow.getData(), -1);
            break;
          }
          int k = paramSession.rowActionList.size();
          if (k > paramInt)
          {
            paramArrayOfObject = paramSession.rowActionList.getArray();
            for (int m = paramInt; m < k; m++)
            {
              RowAction localRowAction2 = (RowAction)paramArrayOfObject[m];
              localRowAction2.commit(paramSession);
            }
            paramInt = k;
          }
        }
        try
        {
          localRowAction1.store.commitRow(paramSession, localRow, j, this.txModel);
          if (this.txModel == 0)
          {
            localRowAction1.setAsNoOp();
            localRow.rowAction = null;
          }
        }
        catch (HsqlException localHsqlException2)
        {
          this.database.logger.logWarningEvent("data commit failed", localHsqlException2);
        }
      }
    }
    try
    {
      if (paramInt > 0) {
        this.database.logger.writeCommitStatement(paramSession);
      }
    }
    catch (HsqlException localHsqlException1)
    {
      this.database.logger.logWarningEvent("data commit failed", localHsqlException1);
    }
  }
  
  void finaliseRows(Session paramSession, Object[] paramArrayOfObject, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    for (int i = paramInt1; i < paramInt2; i++)
    {
      RowAction localRowAction = (RowAction)paramArrayOfObject[i];
      Object localObject1;
      if ((localRowAction.table.tableType == 5) && (localRowAction.type == 0))
      {
        localObject1 = this.rowActionMap.getWriteLock();
        ((Lock)localObject1).lock();
        try
        {
          synchronized (localRowAction)
          {
            if (localRowAction.type == 0) {
              this.rowActionMap.remove(localRowAction.getPos());
            }
          }
        }
        finally
        {
          ((Lock)localObject1).unlock();
        }
      }
      if ((localRowAction.type == 3) && (!localRowAction.deleteComplete)) {
        try
        {
          localRowAction.deleteComplete = true;
          if (localRowAction.table.getTableType() != 3)
          {
            localObject1 = localRowAction.memoryRow;
            if (localObject1 == null) {
              localObject1 = (Row)localRowAction.store.get(localRowAction.getPos(), false);
            }
            localRowAction.store.commitRow(paramSession, (Row)localObject1, localRowAction.type, this.txModel);
          }
        }
        catch (Exception localException) {}
      }
    }
  }
  
  void mergeRolledBackTransaction(Session paramSession, long paramLong, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    for (int i = paramInt1; i < paramInt2; i++)
    {
      RowAction localRowAction = (RowAction)paramArrayOfObject[i];
      Row localRow = localRowAction.memoryRow;
      if (localRow == null)
      {
        if (localRowAction.type != 0) {
          localRow = (Row)localRowAction.store.get(localRowAction.getPos(), false);
        }
      }
      else if (localRow != null) {
        synchronized (localRow)
        {
          localRowAction.mergeRollback(paramSession, paramLong, localRow);
        }
      }
    }
  }
  
  void mergeTransaction(Session paramSession, Object[] paramArrayOfObject, int paramInt1, int paramInt2, long paramLong)
  {
    for (int i = paramInt1; i < paramInt2; i++)
    {
      RowAction localRowAction = (RowAction)paramArrayOfObject[i];
      localRowAction.mergeToTimestamp(paramLong);
    }
  }
  
  long nextChangeTimestamp()
  {
    return this.globalChangeTimestamp.incrementAndGet();
  }
  
  boolean checkDeadlock(Session paramSession, OrderedHashSet paramOrderedHashSet)
  {
    int i = paramSession.waitingSessions.size();
    for (int j = 0; j < i; j++)
    {
      Session localSession = (Session)paramSession.waitingSessions.get(j);
      if (paramOrderedHashSet.contains(localSession)) {
        return false;
      }
      if (!checkDeadlock(localSession, paramOrderedHashSet)) {
        return false;
      }
    }
    return true;
  }
  
  boolean checkDeadlock(Session paramSession1, Session paramSession2)
  {
    int i = paramSession1.waitingSessions.size();
    for (int j = 0; j < i; j++)
    {
      Session localSession = (Session)paramSession1.waitingSessions.get(j);
      if (localSession == paramSession2) {
        return false;
      }
      if (!checkDeadlock(localSession, paramSession2)) {
        return false;
      }
    }
    return true;
  }
  
  void endActionTPL(Session paramSession)
  {
    if ((paramSession.isolationLevel == 4) || (paramSession.isolationLevel == 8)) {
      return;
    }
    if (paramSession.sessionContext.currentStatement == null) {
      return;
    }
    if (paramSession.sessionContext.depth > 0) {
      return;
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramSession.sessionContext.currentStatement.getTableNamesForRead();
    if (arrayOfHsqlName.length == 0) {
      return;
    }
    this.writeLock.lock();
    try
    {
      unlockReadTablesTPL(paramSession, arrayOfHsqlName);
      int i = paramSession.waitingSessions.size();
      if (i == 0) {
        return;
      }
      int j = 0;
      for (int k = 0; k < arrayOfHsqlName.length; k++) {
        if (this.tableWriteLocks.get(arrayOfHsqlName[k]) != paramSession)
        {
          j = 1;
          break;
        }
      }
      if (j == 0) {
        return;
      }
      j = 0;
      for (k = 0; k < i; k++)
      {
        Session localSession = (Session)paramSession.waitingSessions.get(k);
        if (localSession.abortTransaction)
        {
          j = 1;
          break;
        }
        Statement localStatement = localSession.sessionContext.currentStatement;
        if (localStatement == null)
        {
          j = 1;
          break;
        }
        if (ArrayUtil.containsAny(arrayOfHsqlName, localStatement.getTableNamesForWrite()))
        {
          j = 1;
          break;
        }
      }
      if (j == 0) {
        return;
      }
      resetLocks(paramSession);
      resetLatchesMidTransaction(paramSession);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void endTransactionTPL(Session paramSession)
  {
    unlockTablesTPL(paramSession);
    int i = paramSession.waitingSessions.size();
    if (i == 0) {
      return;
    }
    resetLocks(paramSession);
    resetLatches(paramSession);
  }
  
  void resetLocks(Session paramSession)
  {
    int i = paramSession.waitingSessions.size();
    Session localSession;
    for (int j = 0; j < i; j++)
    {
      localSession = (Session)paramSession.waitingSessions.get(j);
      localSession.tempUnlocked = false;
      long l = localSession.latch.getCount();
      if (l == 1L)
      {
        boolean bool = setWaitedSessionsTPL(localSession, localSession.sessionContext.currentStatement);
        if ((bool) && (localSession.tempSet.isEmpty()))
        {
          lockTablesTPL(localSession, localSession.sessionContext.currentStatement);
          localSession.tempUnlocked = true;
        }
      }
    }
    for (j = 0; j < i; j++)
    {
      localSession = (Session)paramSession.waitingSessions.get(j);
      if ((!localSession.tempUnlocked) && (!localSession.abortTransaction)) {
        setWaitedSessionsTPL(localSession, localSession.sessionContext.currentStatement);
      }
    }
  }
  
  void resetLatches(Session paramSession)
  {
    int i = paramSession.waitingSessions.size();
    for (int j = 0; j < i; j++)
    {
      Session localSession = (Session)paramSession.waitingSessions.get(j);
      if ((!localSession.abortTransaction) && (localSession.tempSet.isEmpty())) {}
      setWaitingSessionTPL(localSession);
    }
    paramSession.waitingSessions.clear();
    paramSession.latch.setCount(0);
  }
  
  void resetLatchesMidTransaction(Session paramSession)
  {
    paramSession.tempSet.clear();
    paramSession.tempSet.addAll(paramSession.waitingSessions);
    paramSession.waitingSessions.clear();
    int i = paramSession.tempSet.size();
    for (int j = 0; j < i; j++)
    {
      Session localSession = (Session)paramSession.tempSet.get(j);
      if ((!localSession.abortTransaction) && (localSession.tempSet.isEmpty())) {}
      setWaitingSessionTPL(localSession);
    }
    paramSession.tempSet.clear();
  }
  
  boolean setWaitedSessionsTPL(Session paramSession, Statement paramStatement)
  {
    paramSession.tempSet.clear();
    if (paramStatement == null) {
      return true;
    }
    if (paramSession.abortTransaction) {
      return false;
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    HsqlNameManager.HsqlName localHsqlName;
    Session localSession;
    for (int i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
      {
        localSession = (Session)this.tableWriteLocks.get(localHsqlName);
        if ((localSession != null) && (localSession != paramSession)) {
          paramSession.tempSet.add(localSession);
        }
        Iterator localIterator = this.tableReadLocks.get(localHsqlName);
        while (localIterator.hasNext())
        {
          localSession = (Session)localIterator.next();
          if (localSession != paramSession) {
            paramSession.tempSet.add(localSession);
          }
        }
      }
    }
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    if ((this.txModel == 1) && (paramSession.isReadOnly())) {
      arrayOfHsqlName = this.catalogNameList;
    }
    for (i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
      {
        localSession = (Session)this.tableWriteLocks.get(localHsqlName);
        if ((localSession != null) && (localSession != paramSession)) {
          paramSession.tempSet.add(localSession);
        }
      }
    }
    if (paramSession.tempSet.isEmpty()) {
      return true;
    }
    if (checkDeadlock(paramSession, paramSession.tempSet)) {
      return true;
    }
    paramSession.tempSet.clear();
    paramSession.abortTransaction = true;
    return false;
  }
  
  void setWaitingSessionTPL(Session paramSession)
  {
    int i = paramSession.tempSet.size();
    assert (paramSession.latch.getCount() <= i + 1);
    for (int j = 0; j < i; j++)
    {
      Session localSession = (Session)paramSession.tempSet.get(j);
      localSession.waitingSessions.add(paramSession);
    }
    paramSession.tempSet.clear();
    paramSession.latch.setCount(i);
  }
  
  void lockTablesTPL(Session paramSession, Statement paramStatement)
  {
    if ((paramStatement == null) || (paramSession.abortTransaction)) {
      return;
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    HsqlNameManager.HsqlName localHsqlName;
    for (int i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        this.tableWriteLocks.put(localHsqlName, paramSession);
      }
    }
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    for (i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        this.tableReadLocks.put(localHsqlName, paramSession);
      }
    }
  }
  
  void unlockTablesTPL(Session paramSession)
  {
    Iterator localIterator = this.tableWriteLocks.values().iterator();
    Session localSession;
    while (localIterator.hasNext())
    {
      localSession = (Session)localIterator.next();
      if (localSession == paramSession) {
        localIterator.remove();
      }
    }
    localIterator = this.tableReadLocks.values().iterator();
    while (localIterator.hasNext())
    {
      localSession = (Session)localIterator.next();
      if (localSession == paramSession) {
        localIterator.remove();
      }
    }
  }
  
  void unlockReadTablesTPL(Session paramSession, HsqlNameManager.HsqlName[] paramArrayOfHsqlName)
  {
    for (int i = 0; i < paramArrayOfHsqlName.length; i++) {
      this.tableReadLocks.remove(paramArrayOfHsqlName[i], paramSession);
    }
  }
  
  boolean hasLocks(Session paramSession, Statement paramStatement)
  {
    if (paramStatement == null) {
      return true;
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatement.getTableNamesForWrite();
    HsqlNameManager.HsqlName localHsqlName;
    Session localSession;
    for (int i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
      {
        localSession = (Session)this.tableWriteLocks.get(localHsqlName);
        if ((localSession != null) && (localSession != paramSession)) {
          return false;
        }
        Iterator localIterator = this.tableReadLocks.get(localHsqlName);
        while (localIterator.hasNext())
        {
          localSession = (Session)localIterator.next();
          if (localSession != paramSession) {
            return false;
          }
        }
      }
    }
    arrayOfHsqlName = paramStatement.getTableNamesForRead();
    for (i = 0; i < arrayOfHsqlName.length; i++)
    {
      localHsqlName = arrayOfHsqlName[i];
      if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
      {
        localSession = (Session)this.tableWriteLocks.get(localHsqlName);
        if ((localSession != null) && (localSession != paramSession)) {
          return false;
        }
      }
    }
    return true;
  }
  
  long getFirstLiveTransactionTimestamp()
  {
    if (this.liveTransactionTimestamps.isEmpty()) {
      return 9223372036854775807L;
    }
    return this.liveTransactionTimestamps.get(0);
  }
  
  RowAction[] getRowActionList()
  {
    this.writeLock.lock();
    try
    {
      Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
      int[] arrayOfInt = new int[arrayOfSession.length];
      int i = 0;
      int j = 0;
      for (int k = 0; k < arrayOfSession.length; k++) {
        j += arrayOfSession[k].getTransactionSize();
      }
      RowAction[] arrayOfRowAction = new RowAction[j];
      for (;;)
      {
        j = 0;
        long l = 9223372036854775807L;
        int m = 0;
        for (int n = 0; n < arrayOfSession.length; n++)
        {
          int i1 = arrayOfSession[n].getTransactionSize();
          if (arrayOfInt[n] < i1)
          {
            RowAction localRowAction2 = (RowAction)arrayOfSession[n].rowActionList.get(arrayOfInt[n]);
            if (localRowAction2.actionTimestamp < l)
            {
              l = localRowAction2.actionTimestamp;
              m = n;
            }
            j = 1;
          }
        }
        if (j == 0) {
          break;
        }
        HsqlArrayList localHsqlArrayList = arrayOfSession[m].rowActionList;
        while (arrayOfInt[m] < localHsqlArrayList.size())
        {
          RowAction localRowAction1 = (RowAction)localHsqlArrayList.get(arrayOfInt[m]);
          if (localRowAction1.actionTimestamp == l + 1L) {
            l += 1L;
          }
          if (localRowAction1.actionTimestamp != l) {
            break;
          }
          arrayOfRowAction[(i++)] = localRowAction1;
          arrayOfInt[m] += 1;
        }
      }
      Object localObject1 = arrayOfRowAction;
      return localObject1;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.TransactionManagerCommon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */