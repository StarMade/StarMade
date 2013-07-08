package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.PersistentStore;

public class RowAction
  extends RowActionBase
{
  final TableBase table;
  final PersistentStore store;
  Row memoryRow;
  long rowId;
  boolean isMemory;
  RowAction updatedAction;
  
  public static RowAction addInsertAction(Session paramSession, TableBase paramTableBase, Row paramRow)
  {
    RowAction localRowAction = new RowAction(paramSession, paramTableBase, (byte)1, paramRow, null);
    paramRow.rowAction = localRowAction;
    return localRowAction;
  }
  
  public static RowAction addDeleteAction(Session paramSession, TableBase paramTableBase, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (localRowAction == null)
    {
      localRowAction = new RowAction(paramSession, paramTableBase, (byte)2, paramRow, paramArrayOfInt);
      paramRow.rowAction = localRowAction;
      return localRowAction;
    }
    return localRowAction.addDeleteAction(paramSession, paramArrayOfInt);
  }
  
  public static boolean addRefAction(Session paramSession, Row paramRow, int[] paramArrayOfInt)
  {
    RowAction localRowAction = paramRow.rowAction;
    if (localRowAction == null)
    {
      localRowAction = new RowAction(paramSession, paramRow.getTable(), (byte)5, paramRow, paramArrayOfInt);
      paramRow.rowAction = localRowAction;
      return true;
    }
    return localRowAction.addRefAction(paramSession, paramArrayOfInt);
  }
  
  public RowAction(Session paramSession, TableBase paramTableBase, byte paramByte, Row paramRow, int[] paramArrayOfInt)
  {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
    this.table = paramTableBase;
    this.store = paramTableBase.getRowStore(paramSession);
    this.isMemory = paramRow.isMemory();
    this.memoryRow = paramRow;
    this.rowId = paramRow.getPos();
    this.changeColumnMap = paramArrayOfInt;
  }
  
  private RowAction(RowAction paramRowAction)
  {
    this.session = paramRowAction.session;
    this.type = paramRowAction.type;
    this.actionTimestamp = paramRowAction.actionTimestamp;
    this.table = paramRowAction.table;
    this.store = paramRowAction.store;
    this.isMemory = paramRowAction.isMemory;
    this.memoryRow = paramRowAction.memoryRow;
    this.rowId = paramRowAction.rowId;
    this.changeColumnMap = paramRowAction.changeColumnMap;
  }
  
  public synchronized int getType()
  {
    return this.type;
  }
  
  synchronized RowAction addDeleteAction(Session paramSession, int[] paramArrayOfInt)
  {
    if (this.type == 0)
    {
      setAsAction(paramSession, (byte)2);
      this.changeColumnMap = paramArrayOfInt;
    }
    else
    {
      Object localObject = this;
      for (;;)
      {
        if (((RowActionBase)localObject).rolledback)
        {
          if (((RowActionBase)localObject).next == null) {
            break;
          }
          localObject = ((RowActionBase)localObject).next;
        }
        else
        {
          switch (((RowActionBase)localObject).type)
          {
          case 1: 
            if ((((RowActionBase)localObject).commitTimestamp == 0L) && (paramSession != ((RowActionBase)localObject).session)) {
              throw Error.runtimeError(201, "RowAction");
            }
            break;
          case 2: 
          case 3: 
            if (paramSession != ((RowActionBase)localObject).session)
            {
              if (((RowActionBase)localObject).commitTimestamp == 0L)
              {
                if (!paramSession.tempSet.isEmpty()) {
                  paramSession.tempSet.clear();
                }
                paramSession.tempSet.add(localObject);
              }
              return null;
            }
            break;
          case 5: 
            if ((paramSession != ((RowActionBase)localObject).session) && (((RowActionBase)localObject).commitTimestamp == 0L) && ((paramArrayOfInt == null) || (ArrayUtil.haveCommonElement(paramArrayOfInt, ((RowActionBase)localObject).changeColumnMap))))
            {
              if (!paramSession.tempSet.isEmpty()) {
                paramSession.tempSet.clear();
              }
              paramSession.tempSet.add(localObject);
              return null;
            }
            break;
          }
          if (((RowActionBase)localObject).next == null) {
            break;
          }
          localObject = ((RowActionBase)localObject).next;
        }
      }
      RowActionBase localRowActionBase = new RowActionBase(paramSession, (byte)2);
      localRowActionBase.changeColumnMap = paramArrayOfInt;
      ((RowActionBase)localObject).next = localRowActionBase;
    }
    return this;
  }
  
  synchronized boolean addRefAction(Session paramSession, int[] paramArrayOfInt)
  {
    if (this.type == 0)
    {
      setAsAction(paramSession, (byte)5);
      this.changeColumnMap = paramArrayOfInt;
      return true;
    }
    for (Object localObject = this;; localObject = ((RowActionBase)localObject).next)
    {
      if (paramSession == ((RowActionBase)localObject).session)
      {
        if ((((RowActionBase)localObject).type == 5) && (((RowActionBase)localObject).changeColumnMap == paramArrayOfInt) && (((RowActionBase)localObject).commitTimestamp == 0L)) {
          return false;
        }
        if ((((RowActionBase)localObject).type == 1) && (((RowActionBase)localObject).commitTimestamp == 0L)) {
          return false;
        }
      }
      else if ((((RowActionBase)localObject).type == 2) && (((RowActionBase)localObject).commitTimestamp == 0L) && ((((RowActionBase)localObject).changeColumnMap == null) || (ArrayUtil.haveCommonElement(paramArrayOfInt, ((RowActionBase)localObject).changeColumnMap))))
      {
        if (!paramSession.tempSet.isEmpty()) {
          paramSession.tempSet.clear();
        }
        paramSession.tempSet.add(localObject);
        return false;
      }
      if (((RowActionBase)localObject).next == null) {
        break;
      }
    }
    RowActionBase localRowActionBase = new RowActionBase(paramSession, (byte)5);
    localRowActionBase.changeColumnMap = paramArrayOfInt;
    ((RowActionBase)localObject).next = localRowActionBase;
    return true;
  }
  
  public boolean checkDeleteActions()
  {
    return false;
  }
  
  public synchronized RowAction duplicate(Row paramRow)
  {
    RowAction localRowAction = new RowAction(this.session, this.table, this.type, paramRow, this.changeColumnMap);
    return localRowAction;
  }
  
  synchronized void setAsAction(Session paramSession, byte paramByte)
  {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
    this.changeColumnMap = null;
  }
  
  synchronized void setAsAction(RowActionBase paramRowActionBase)
  {
    super.setAsAction(paramRowActionBase);
  }
  
  public void setAsNoOp()
  {
    this.session = null;
    this.actionTimestamp = 0L;
    this.commitTimestamp = 0L;
    this.rolledback = false;
    this.deleteComplete = false;
    this.changeColumnMap = null;
    this.prepared = false;
    this.type = 0;
    this.next = null;
  }
  
  private void setAsDeleteFinal(long paramLong)
  {
    this.actionTimestamp = 0L;
    this.commitTimestamp = paramLong;
    this.rolledback = false;
    this.deleteComplete = false;
    this.prepared = false;
    this.changeColumnMap = null;
    this.type = 3;
    this.next = null;
  }
  
  synchronized void prepareCommit(Session paramSession)
  {
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).commitTimestamp == 0L)) {
        ((RowActionBase)localObject).prepared = true;
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
  }
  
  synchronized int commit(Session paramSession)
  {
    Object localObject = this;
    int i = 0;
    do
    {
      if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).commitTimestamp == 0L))
      {
        ((RowActionBase)localObject).commitTimestamp = paramSession.actionTimestamp;
        ((RowActionBase)localObject).prepared = false;
        if (((RowActionBase)localObject).type == 1) {
          i = ((RowActionBase)localObject).type;
        } else if (((RowActionBase)localObject).type == 2) {
          if (i == 1) {
            i = 4;
          } else {
            i = ((RowActionBase)localObject).type;
          }
        }
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return i;
  }
  
  public boolean isDeleted()
  {
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).commitTimestamp != 0L) && ((((RowActionBase)localObject).type == 2) || (((RowActionBase)localObject).type == 3))) {
        return true;
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return false;
  }
  
  synchronized int getCommitTypeOn(long paramLong)
  {
    Object localObject = this;
    int i = 0;
    do
    {
      if (((RowActionBase)localObject).commitTimestamp == paramLong) {
        if (((RowActionBase)localObject).type == 1) {
          i = ((RowActionBase)localObject).type;
        } else if (((RowActionBase)localObject).type == 2) {
          if (i == 1) {
            i = 4;
          } else {
            i = ((RowActionBase)localObject).type;
          }
        }
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return i;
  }
  
  synchronized boolean canCommit(Session paramSession, OrderedHashSet paramOrderedHashSet)
  {
    long l1 = paramSession.transactionTimestamp;
    long l2 = 0L;
    int i = paramSession.isolationLevel == 2 ? 1 : 0;
    int j = 0;
    Object localObject = this;
    if (i != 0)
    {
      do
      {
        if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).type == 2) && (((RowActionBase)localObject).commitTimestamp == 0L)) {
          l1 = ((RowActionBase)localObject).actionTimestamp;
        }
        localObject = ((RowActionBase)localObject).next;
      } while (localObject != null);
      localObject = this;
    }
    do
    {
      if (((RowActionBase)localObject).session == paramSession)
      {
        if (((RowActionBase)localObject).type == 2) {
          j = 1;
        }
      }
      else
      {
        if ((((RowActionBase)localObject).rolledback) || (((RowActionBase)localObject).type != 2))
        {
          localObject = ((RowActionBase)localObject).next;
          continue;
        }
        if (((RowActionBase)localObject).prepared) {
          return false;
        }
        if (((RowActionBase)localObject).commitTimestamp == 0L) {
          paramOrderedHashSet.add(localObject);
        } else if (((RowActionBase)localObject).commitTimestamp > l2) {
          l2 = ((RowActionBase)localObject).commitTimestamp;
        }
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    if (j == 0) {
      return true;
    }
    return l2 < l1;
  }
  
  synchronized void complete(Session paramSession)
  {
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).actionTimestamp == 0L)) {
        ((RowActionBase)localObject).actionTimestamp = paramSession.actionTimestamp;
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
  }
  
  synchronized boolean complete(Session paramSession, OrderedHashSet paramOrderedHashSet)
  {
    int i = paramSession.isolationLevel == 2 ? 1 : 0;
    boolean bool = true;
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).rolledback) || (((RowActionBase)localObject).type == 0))
      {
        localObject = ((RowActionBase)localObject).next;
      }
      else
      {
        if (((RowActionBase)localObject).session != paramSession)
        {
          if (((RowActionBase)localObject).prepared)
          {
            paramOrderedHashSet.add(((RowActionBase)localObject).session);
            return false;
          }
          if (i != 0)
          {
            if (((RowActionBase)localObject).commitTimestamp > paramSession.actionTimestamp)
            {
              paramOrderedHashSet.add(paramSession);
              bool = false;
            }
            else if (((RowActionBase)localObject).commitTimestamp == 0L)
            {
              paramOrderedHashSet.add(((RowActionBase)localObject).session);
              bool = false;
            }
          }
          else if (((RowActionBase)localObject).commitTimestamp > paramSession.transactionTimestamp) {
            return false;
          }
        }
        localObject = ((RowActionBase)localObject).next;
      }
    } while (localObject != null);
    return bool;
  }
  
  synchronized int getActionType(long paramLong)
  {
    int i = 0;
    Object localObject = this;
    do
    {
      if (((RowActionBase)localObject).actionTimestamp == paramLong) {
        if (((RowActionBase)localObject).type == 2)
        {
          if (i == 1) {
            i = 4;
          } else {
            i = ((RowActionBase)localObject).type;
          }
        }
        else if (((RowActionBase)localObject).type == 1) {
          i = ((RowActionBase)localObject).type;
        }
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return i;
  }
  
  public synchronized long getPos()
  {
    return this.rowId;
  }
  
  synchronized void setPos(long paramLong)
  {
    this.rowId = paramLong;
  }
  
  private int getRollbackType(Session paramSession)
  {
    int i = 0;
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).rolledback)) {
        if (((RowActionBase)localObject).type == 2)
        {
          if (i == 1) {
            i = 4;
          } else {
            i = ((RowActionBase)localObject).type;
          }
        }
        else if (((RowActionBase)localObject).type == 1) {
          i = ((RowActionBase)localObject).type;
        }
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return i;
  }
  
  synchronized void rollback(Session paramSession, long paramLong)
  {
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).session == paramSession) && (((RowActionBase)localObject).commitTimestamp == 0L) && (((RowActionBase)localObject).actionTimestamp >= paramLong))
      {
        ((RowActionBase)localObject).commitTimestamp = paramSession.actionTimestamp;
        ((RowActionBase)localObject).rolledback = true;
        ((RowActionBase)localObject).prepared = false;
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
  }
  
  synchronized int mergeRollback(Session paramSession, long paramLong, Row paramRow)
  {
    Object localObject1 = this;
    Object localObject2 = null;
    Object localObject3 = null;
    int i = getRollbackType(paramSession);
    do
    {
      if ((((RowActionBase)localObject1).session == paramSession) && (((RowActionBase)localObject1).rolledback))
      {
        if (localObject3 != null) {
          localObject3.next = null;
        }
      }
      else if (localObject2 == null)
      {
        localObject2 = localObject3 = localObject1;
      }
      else
      {
        localObject3.next = ((RowActionBase)localObject1);
        localObject3 = localObject1;
      }
      localObject1 = ((RowActionBase)localObject1).next;
    } while (localObject1 != null);
    if (localObject2 == null) {
      switch (i)
      {
      case 1: 
      case 4: 
        setAsDeleteFinal(paramLong);
        break;
      case 0: 
      case 2: 
      case 3: 
      default: 
        setAsNoOp();
        break;
      }
    } else if (localObject2 != this) {
      setAsAction(localObject2);
    }
    return i;
  }
  
  synchronized void mergeToTimestamp(long paramLong)
  {
    Object localObject1 = this;
    Object localObject2 = null;
    Object localObject3 = null;
    int i = getCommitTypeOn(paramLong);
    if ((this.type == 3) || (this.type == 0)) {
      return;
    }
    if ((i == 2) || (i == 4))
    {
      setAsDeleteFinal(paramLong);
      return;
    }
    do
    {
      int j = 0;
      if (((RowActionBase)localObject1).commitTimestamp != 0L) {
        if (((RowActionBase)localObject1).commitTimestamp <= paramLong) {
          j = 1;
        } else if (((RowActionBase)localObject1).type == 5) {
          j = 1;
        }
      }
      if (j != 0)
      {
        if (localObject3 != null) {
          localObject3.next = null;
        }
      }
      else if (localObject2 == null)
      {
        localObject2 = localObject3 = localObject1;
      }
      else
      {
        localObject3.next = ((RowActionBase)localObject1);
        localObject3 = localObject1;
      }
      localObject1 = ((RowActionBase)localObject1).next;
    } while (localObject1 != null);
    if (localObject2 == null) {
      switch (i)
      {
      case 2: 
      case 4: 
        setAsDeleteFinal(paramLong);
        break;
      case 0: 
      case 1: 
      case 3: 
      default: 
        setAsNoOp();
        break;
      }
    } else if (localObject2 != this) {
      setAsAction(localObject2);
    }
    mergeExpiredRefActions();
  }
  
  synchronized boolean canRead(Session paramSession, int paramInt)
  {
    int i = 0;
    if (this.type == 3) {
      return false;
    }
    if (this.type == 0) {
      return true;
    }
    Object localObject = this;
    long l;
    if (paramSession == null) {
      l = 9223372036854775807L;
    } else {
      switch (paramSession.isolationLevel)
      {
      case 1: 
        l = 9223372036854775807L;
        break;
      case 2: 
        l = paramSession.actionTimestamp;
        break;
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
      default: 
        l = paramSession.transactionTimestamp;
      }
    }
    do
    {
      if (((RowActionBase)localObject).type == 5)
      {
        localObject = ((RowActionBase)localObject).next;
      }
      else if (((RowActionBase)localObject).rolledback)
      {
        if (((RowActionBase)localObject).type == 1) {
          i = 2;
        }
        localObject = ((RowActionBase)localObject).next;
      }
      else if (paramSession == ((RowActionBase)localObject).session)
      {
        if (((RowActionBase)localObject).type == 2) {
          i = ((RowActionBase)localObject).type;
        } else if (((RowActionBase)localObject).type == 1) {
          i = ((RowActionBase)localObject).type;
        }
        localObject = ((RowActionBase)localObject).next;
      }
      else if (((RowActionBase)localObject).commitTimestamp == 0L)
      {
        if (((RowActionBase)localObject).type == 0) {
          throw Error.runtimeError(201, "RowAction");
        }
        if (((RowActionBase)localObject).type == 1)
        {
          if (paramInt == 0)
          {
            i = 2;
            break;
          }
          if (paramInt == 1)
          {
            i = 1;
            paramSession.tempSet.clear();
            paramSession.tempSet.add(localObject);
            break;
          }
          if (paramInt != 2) {
            break;
          }
          i = 2;
          break;
        }
        if ((((RowActionBase)localObject).type == 2) && (paramInt != 1) && (paramInt == 2)) {
          i = 2;
        }
        localObject = ((RowActionBase)localObject).next;
      }
      else
      {
        if (((RowActionBase)localObject).commitTimestamp < l)
        {
          if (((RowActionBase)localObject).type == 2) {
            i = 2;
          } else if (((RowActionBase)localObject).type == 1) {
            i = 1;
          }
        }
        else if (((RowActionBase)localObject).type == 1) {
          if (paramInt == 0)
          {
            i = 2;
          }
          else if (paramInt == 1)
          {
            i = 1;
            paramSession.tempSet.clear();
            paramSession.tempSet.add(localObject);
          }
          else if (paramInt == 2)
          {
            i = 2;
          }
        }
        localObject = ((RowActionBase)localObject).next;
      }
    } while (localObject != null);
    return (i == 0) || (i == 1);
  }
  
  public boolean hasCurrentRefAction()
  {
    Object localObject = this;
    do
    {
      if ((((RowActionBase)localObject).type == 5) && (((RowActionBase)localObject).commitTimestamp == 0L)) {
        return true;
      }
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return false;
  }
  
  private RowAction mergeExpiredRefActions()
  {
    if (this.updatedAction != null) {
      this.updatedAction = this.updatedAction.mergeExpiredRefActions();
    }
    if (hasCurrentRefAction()) {
      return this;
    }
    return this.updatedAction;
  }
  
  public synchronized String describe(Session paramSession)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = this;
    do
    {
      if (localObject == this) {
        localStringBuilder.append(this.rowId).append(' ');
      }
      localStringBuilder.append(((RowActionBase)localObject).session.getId()).append(' ');
      localStringBuilder.append(((RowActionBase)localObject).type).append(' ').append(((RowActionBase)localObject).actionTimestamp);
      localStringBuilder.append(' ').append(((RowActionBase)localObject).commitTimestamp);
      if (((RowActionBase)localObject).commitTimestamp != 0L) {
        if (((RowActionBase)localObject).rolledback) {
          localStringBuilder.append('r');
        } else {
          localStringBuilder.append('c');
        }
      }
      localStringBuilder.append(" - ");
      localObject = ((RowActionBase)localObject).next;
    } while (localObject != null);
    return localStringBuilder.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RowAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */