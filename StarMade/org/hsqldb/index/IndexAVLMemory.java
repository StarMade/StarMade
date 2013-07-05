package org.hsqldb.index;

import java.io.PrintStream;
import java.util.concurrent.locks.Lock;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.TransactionManager;
import org.hsqldb.error.Error;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public class IndexAVLMemory extends IndexAVL
{
  public IndexAVLMemory(HsqlNameManager.HsqlName paramHsqlName, long paramLong, TableBase paramTableBase, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, Type[] paramArrayOfType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    super(paramHsqlName, paramLong, paramTableBase, paramArrayOfInt, paramArrayOfBoolean1, paramArrayOfBoolean2, paramArrayOfType, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
  }

  public void checkIndex(PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      Object localObject2 = null;
      while (localObject1 != null)
      {
        localObject2 = localObject1;
        checkNodes(paramPersistentStore, (NodeAVL)localObject1);
        localObject1 = ((NodeAVL)localObject1).nLeft;
      }
      localObject1 = localObject2;
      while (localObject2 != null)
      {
        checkNodes(paramPersistentStore, (NodeAVL)localObject2);
        localObject2 = next(paramPersistentStore, (NodeAVL)localObject2);
      }
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  void checkNodes(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL1 = paramNodeAVL.nLeft;
    NodeAVL localNodeAVL2 = paramNodeAVL.nRight;
    if ((localNodeAVL1 != null) && (localNodeAVL1.getBalance(paramPersistentStore) == -2))
      System.out.print("broken index - deleted");
    if ((localNodeAVL2 != null) && (localNodeAVL2.getBalance(paramPersistentStore) == -2))
      System.out.print("broken index -deleted");
    if ((localNodeAVL1 != null) && (!paramNodeAVL.equals(localNodeAVL1.getParent(paramPersistentStore))))
      System.out.print("broken index - no parent");
    if ((localNodeAVL2 != null) && (!paramNodeAVL.equals(localNodeAVL2.getParent(paramPersistentStore))))
      System.out.print("broken index - no parent");
  }

  public void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow)
  {
    boolean bool1 = true;
    int i = -1;
    Object[] arrayOfObject = paramRow.getData();
    boolean bool2 = (!this.isUnique) || (hasNulls(paramSession, arrayOfObject));
    boolean bool3 = this.isSimple;
    this.writeLock.lock();
    try
    {
      NodeAVL localNodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL localNodeAVL2 = localNodeAVL1;
      if (localNodeAVL1 == null)
      {
        paramPersistentStore.setAccessor(this, ((RowAVL)paramRow).getNode(this.position));
        return;
      }
      while (true)
      {
        Row localRow = localNodeAVL1.row;
        i = 0;
        if (bool3)
        {
          i = this.colTypes[0].compare(paramSession, arrayOfObject[this.colIndex[0]], localRow.getData()[this.colIndex[0]]);
          if ((i == 0) && (bool2))
            i = compareRowForInsertOrDelete(paramSession, paramRow, localRow, bool2, 1);
        }
        else
        {
          i = compareRowForInsertOrDelete(paramSession, paramRow, localRow, bool2, 0);
        }
        if ((i == 0) && (paramSession != null) && (!bool2) && (paramSession.database.txManager.isMVRows()) && (!isEqualReadable(paramSession, paramPersistentStore, localNodeAVL1)))
        {
          bool2 = true;
          i = compareRowForInsertOrDelete(paramSession, paramRow, localRow, bool2, this.colIndex.length);
        }
        if (i == 0)
        {
          if (this.isConstraint)
          {
            Constraint localConstraint = ((Table)this.table).getUniqueConstraintForIndex(this);
            throw localConstraint.getException(paramRow.getData());
          }
          throw Error.error(104, this.name.statementName);
        }
        bool1 = i < 0;
        localNodeAVL2 = localNodeAVL1;
        localNodeAVL1 = bool1 ? localNodeAVL2.nLeft : localNodeAVL2.nRight;
        if (localNodeAVL1 == null)
          break;
      }
      localNodeAVL2 = localNodeAVL2.set(paramPersistentStore, bool1, ((RowAVL)paramRow).getNode(this.position));
      balance(paramPersistentStore, localNodeAVL2, bool1);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  void delete(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return;
    this.writeLock.lock();
    try
    {
      int i;
      NodeAVL localNodeAVL4;
      NodeAVL localNodeAVL6;
      if (paramNodeAVL.nLeft == null)
      {
        localNodeAVL1 = paramNodeAVL.nRight;
      }
      else if (paramNodeAVL.nRight == null)
      {
        localNodeAVL1 = paramNodeAVL.nLeft;
      }
      else
      {
        NodeAVL localNodeAVL2 = paramNodeAVL;
        NodeAVL localNodeAVL3;
        for (paramNodeAVL = paramNodeAVL.nLeft; ; paramNodeAVL = localNodeAVL3)
        {
          localNodeAVL3 = paramNodeAVL.nRight;
          if (localNodeAVL3 == null)
            break;
        }
        localNodeAVL1 = paramNodeAVL.nLeft;
        i = paramNodeAVL.iBalance;
        paramNodeAVL.iBalance = localNodeAVL2.iBalance;
        localNodeAVL2.iBalance = i;
        localNodeAVL4 = paramNodeAVL.nParent;
        NodeAVL localNodeAVL5 = localNodeAVL2.nParent;
        if (localNodeAVL2.isRoot(paramPersistentStore))
          paramPersistentStore.setAccessor(this, paramNodeAVL);
        paramNodeAVL.nParent = localNodeAVL5;
        if (localNodeAVL5 != null)
          if (localNodeAVL5.nRight == localNodeAVL2)
            localNodeAVL5.nRight = paramNodeAVL;
          else
            localNodeAVL5.nLeft = paramNodeAVL;
        if (localNodeAVL2 == localNodeAVL4)
        {
          localNodeAVL2.nParent = paramNodeAVL;
          if (localNodeAVL2.nLeft == paramNodeAVL)
          {
            paramNodeAVL.nLeft = localNodeAVL2;
            localNodeAVL6 = localNodeAVL2.nRight;
            paramNodeAVL.nRight = localNodeAVL6;
          }
          else
          {
            paramNodeAVL.nRight = localNodeAVL2;
            localNodeAVL6 = localNodeAVL2.nLeft;
            paramNodeAVL.nLeft = localNodeAVL6;
          }
        }
        else
        {
          localNodeAVL2.nParent = localNodeAVL4;
          localNodeAVL4.nRight = localNodeAVL2;
          localNodeAVL6 = localNodeAVL2.nLeft;
          NodeAVL localNodeAVL7 = localNodeAVL2.nRight;
          paramNodeAVL.nLeft = localNodeAVL6;
          paramNodeAVL.nRight = localNodeAVL7;
        }
        paramNodeAVL.nRight.nParent = paramNodeAVL;
        paramNodeAVL.nLeft.nParent = paramNodeAVL;
        localNodeAVL2.nLeft = localNodeAVL1;
        if (localNodeAVL1 != null)
          localNodeAVL1.nParent = localNodeAVL2;
        localNodeAVL2.nRight = null;
        paramNodeAVL = localNodeAVL2;
      }
      boolean bool = paramNodeAVL.isFromLeft(paramPersistentStore);
      paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL1);
      NodeAVL localNodeAVL1 = paramNodeAVL.nParent;
      paramNodeAVL.delete();
      while (localNodeAVL1 != null)
      {
        paramNodeAVL = localNodeAVL1;
        i = bool ? 1 : -1;
        switch (paramNodeAVL.iBalance * i)
        {
        case -1:
          paramNodeAVL.iBalance = 0;
          break;
        case 0:
          paramNodeAVL.iBalance = i;
          return;
        case 1:
          localNodeAVL4 = paramNodeAVL.child(paramPersistentStore, !bool);
          int j = localNodeAVL4.iBalance;
          if (j * i >= 0)
          {
            paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL4);
            localNodeAVL6 = localNodeAVL4.child(paramPersistentStore, bool);
            paramNodeAVL.set(paramPersistentStore, !bool, localNodeAVL6);
            localNodeAVL4.set(paramPersistentStore, bool, paramNodeAVL);
            if (j == 0)
            {
              paramNodeAVL.iBalance = i;
              localNodeAVL4.iBalance = (-i);
              return;
            }
            paramNodeAVL.iBalance = 0;
            localNodeAVL4.iBalance = 0;
            paramNodeAVL = localNodeAVL4;
          }
          else
          {
            localNodeAVL6 = localNodeAVL4.child(paramPersistentStore, bool);
            paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL6);
            j = localNodeAVL6.iBalance;
            localNodeAVL4.set(paramPersistentStore, bool, localNodeAVL6.child(paramPersistentStore, !bool));
            localNodeAVL6.set(paramPersistentStore, !bool, localNodeAVL4);
            paramNodeAVL.set(paramPersistentStore, !bool, localNodeAVL6.child(paramPersistentStore, bool));
            localNodeAVL6.set(paramPersistentStore, bool, paramNodeAVL);
            paramNodeAVL.iBalance = (j == i ? -i : 0);
            localNodeAVL4.iBalance = (j == -i ? i : 0);
            localNodeAVL6.iBalance = 0;
            paramNodeAVL = localNodeAVL6;
          }
          break;
        }
        bool = paramNodeAVL.isFromLeft(paramPersistentStore);
        localNodeAVL1 = paramNodeAVL.nParent;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL1 = paramNodeAVL.nRight;
    if (localNodeAVL1 != null)
    {
      paramNodeAVL = localNodeAVL1;
      for (localNodeAVL2 = paramNodeAVL.nLeft; localNodeAVL2 != null; localNodeAVL2 = paramNodeAVL.nLeft)
        paramNodeAVL = localNodeAVL2;
      return paramNodeAVL;
    }
    NodeAVL localNodeAVL2 = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.nParent; (paramNodeAVL != null) && (localNodeAVL2 == paramNodeAVL.nRight); paramNodeAVL = paramNodeAVL.nParent)
      localNodeAVL2 = paramNodeAVL;
    return paramNodeAVL;
  }

  NodeAVL last(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return null;
    NodeAVL localNodeAVL1 = paramNodeAVL.nLeft;
    if (localNodeAVL1 != null)
    {
      paramNodeAVL = localNodeAVL1;
      for (localNodeAVL2 = paramNodeAVL.nRight; localNodeAVL2 != null; localNodeAVL2 = paramNodeAVL.nRight)
        paramNodeAVL = localNodeAVL2;
      return paramNodeAVL;
    }
    NodeAVL localNodeAVL2 = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.nParent; (paramNodeAVL != null) && (localNodeAVL2.equals(paramNodeAVL.nLeft)); paramNodeAVL = paramNodeAVL.nParent)
      localNodeAVL2 = paramNodeAVL;
    return paramNodeAVL;
  }

  void balance(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean)
  {
    while (true)
    {
      int i = paramBoolean ? 1 : -1;
      switch (paramNodeAVL.iBalance * i)
      {
      case 1:
        paramNodeAVL.iBalance = 0;
        return;
      case 0:
        paramNodeAVL.iBalance = (-i);
        break;
      case -1:
        NodeAVL localNodeAVL1 = paramBoolean ? paramNodeAVL.nLeft : paramNodeAVL.nRight;
        if (localNodeAVL1.iBalance == -i)
        {
          paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL1);
          paramNodeAVL.set(paramPersistentStore, paramBoolean, localNodeAVL1.child(paramPersistentStore, !paramBoolean));
          localNodeAVL1.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
          paramNodeAVL.iBalance = 0;
          localNodeAVL1.iBalance = 0;
        }
        else
        {
          NodeAVL localNodeAVL2 = !paramBoolean ? localNodeAVL1.nLeft : localNodeAVL1.nRight;
          paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL2);
          localNodeAVL1.set(paramPersistentStore, !paramBoolean, localNodeAVL2.child(paramPersistentStore, paramBoolean));
          localNodeAVL2.set(paramPersistentStore, paramBoolean, localNodeAVL1);
          paramNodeAVL.set(paramPersistentStore, paramBoolean, localNodeAVL2.child(paramPersistentStore, !paramBoolean));
          localNodeAVL2.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
          int j = localNodeAVL2.iBalance;
          paramNodeAVL.iBalance = (j == -i ? i : 0);
          localNodeAVL1.iBalance = (j == i ? -i : 0);
          localNodeAVL2.iBalance = 0;
        }
        return;
      }
      if (paramNodeAVL.nParent == null)
        return;
      paramBoolean = (paramNodeAVL.nParent == null) || (paramNodeAVL == paramNodeAVL.nParent.nLeft);
      paramNodeAVL = paramNodeAVL.nParent;
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.index.IndexAVLMemory
 * JD-Core Version:    0.6.2
 */