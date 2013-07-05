package org.hsqldb.persist;

import org.hsqldb.ColumnSchema;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.Session;
import org.hsqldb.SessionData;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.TransactionManager;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.IndexAVL;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.types.Type;

public abstract class RowStoreAVL
  implements PersistentStore
{
  Session session;
  Database database;
  PersistentStoreCollection manager;
  Index[] indexList = Index.emptyArray;
  CachedObject[] accessorList = CachedObject.emptyArray;
  TableBase table;
  long baseElementCount;
  long elementCount;
  boolean[] nullsList;
  double[][] searchCost;
  boolean isSchemaStore;
  private long timestamp;
  PersistentStore[] subStores = PersistentStore.emptyArray;

  public TableBase getTable()
  {
    return this.table;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public abstract boolean isMemory();

  public void setMemory(boolean paramBoolean)
  {
  }

  public abstract int getAccessCount();

  public abstract void set(CachedObject paramCachedObject);

  public abstract CachedObject get(long paramLong, boolean paramBoolean);

  public abstract CachedObject get(CachedObject paramCachedObject, boolean paramBoolean);

  public abstract int getStorageSize(long paramLong);

  public abstract void add(CachedObject paramCachedObject);

  public abstract CachedObject get(RowInputInterface paramRowInputInterface);

  public CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface)
  {
    return paramCachedObject;
  }

  public abstract CachedObject getNewInstance(int paramInt);

  public abstract CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean);

  public abstract void removePersistence(long paramLong);

  public abstract void removeAll();

  public abstract void remove(long paramLong);

  public abstract void release(long paramLong);

  public abstract void commitPersistence(CachedObject paramCachedObject);

  public abstract DataFileCache getCache();

  public abstract void setCache(DataFileCache paramDataFileCache);

  public abstract void release();

  public PersistentStore getAccessorStore(Index paramIndex)
  {
    return null;
  }

  public CachedObject getAccessor(Index paramIndex)
  {
    int i = paramIndex.getPosition();
    if (i >= this.accessorList.length)
      throw Error.runtimeError(201, "RowStoreAVL");
    return this.accessorList[i];
  }

  public void delete(Session paramSession, Row paramRow)
  {
    paramRow = (Row)get(paramRow, false);
    for (int i = 0; i < this.indexList.length; i++)
      this.indexList[i].delete(paramSession, this, paramRow);
    for (i = 0; i < this.subStores.length; i++)
      this.subStores[i].delete(paramSession, paramRow);
    paramRow.delete(this);
    this.elementCount -= 1L;
    if ((this.elementCount > 16384L) && (this.elementCount < this.baseElementCount / 2L))
    {
      this.baseElementCount = this.elementCount;
      this.searchCost = ((double[][])null);
    }
  }

  public void indexRow(Session paramSession, Row paramRow)
  {
    HsqlException localHsqlException1 = 0;
    try
    {
      while (localHsqlException1 < this.indexList.length)
      {
        this.indexList[localHsqlException1].insert(paramSession, this, paramRow);
        localHsqlException1++;
      }
      int i = 0;
      try
      {
        for (i = 0; i < this.subStores.length; i++)
          this.subStores[i].indexRow(paramSession, paramRow);
      }
      catch (HsqlException localHsqlException3)
      {
        int j = i;
        for (i = 0; i < j; i++)
          this.subStores[i].delete(paramSession, paramRow);
        throw localHsqlException3;
      }
      this.elementCount += 1L;
      if ((this.elementCount > 16384L) && (this.elementCount > this.baseElementCount * 2L))
      {
        this.baseElementCount = this.elementCount;
        this.searchCost = ((double[][])null);
      }
    }
    catch (HsqlException localHsqlException2)
    {
      localHsqlException3 = localHsqlException1;
      for (localHsqlException1 = 0; localHsqlException1 < localHsqlException3; localHsqlException1++)
        this.indexList[localHsqlException1].delete(paramSession, this, paramRow);
      remove(paramRow.getPos());
      throw localHsqlException2;
    }
  }

  public final void indexRows(Session paramSession)
  {
    for (int i = 1; i < this.indexList.length; i++)
      setAccessor(this.indexList[i], null);
    RowIterator localRowIterator = rowIterator();
    while (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      ((RowAVL)localRow).clearNonPrimaryNodes();
      for (int j = 1; j < this.indexList.length; j++)
        this.indexList[j].insert(paramSession, this, localRow);
    }
  }

  public final RowIterator rowIterator()
  {
    Index localIndex = this.indexList[0];
    for (int i = 0; i < this.indexList.length; i++)
      if (this.indexList[i].isClustered())
      {
        localIndex = this.indexList[i];
        break;
      }
    return localIndex.firstRow(this);
  }

  public abstract void setAccessor(Index paramIndex, CachedObject paramCachedObject);

  public abstract void setAccessor(Index paramIndex, long paramLong);

  public void resetAccessorKeys(Index[] paramArrayOfIndex)
  {
    Index[] arrayOfIndex1 = this.indexList;
    this.searchCost = ((double[][])null);
    if ((this.indexList.length == 0) || (this.accessorList[0] == null))
    {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    }
    if (this.indexList == paramArrayOfIndex)
      return;
    CachedObject[] arrayOfCachedObject = this.accessorList;
    int i = this.indexList.length;
    int j = paramArrayOfIndex.length - this.indexList.length;
    int k = 0;
    if (j < -1)
      throw Error.runtimeError(201, "RowStoreAV");
    if (j == -1)
    {
      i = paramArrayOfIndex.length;
    }
    else
    {
      if (j == 0)
        throw Error.runtimeError(201, "RowStoreAV");
      if (j != 1)
      {
        while ((k < i) && (this.indexList[k] == paramArrayOfIndex[k]))
          k++;
        Index[] arrayOfIndex2 = (Index[])ArrayUtil.toAdjustedArray(this.indexList, null, k, 1);
        arrayOfIndex2[k] = paramArrayOfIndex[k];
        resetAccessorKeys(arrayOfIndex2);
        resetAccessorKeys(paramArrayOfIndex);
        return;
      }
    }
    while ((k < i) && (this.indexList[k] == paramArrayOfIndex[k]))
      k++;
    this.accessorList = ((CachedObject[])ArrayUtil.toAdjustedArray(this.accessorList, null, k, j));
    this.indexList = paramArrayOfIndex;
    try
    {
      if (j > 0)
        insertIndexNodes(this.indexList[0], this.indexList[k]);
      else
        dropIndexFromRows(this.indexList[0], arrayOfIndex1[k]);
    }
    catch (HsqlException localHsqlException)
    {
      this.accessorList = arrayOfCachedObject;
      this.indexList = arrayOfIndex1;
      throw localHsqlException;
    }
  }

  public Index[] getAccessorKeys()
  {
    return this.indexList;
  }

  public synchronized double searchCost(Session paramSession, Index paramIndex, int paramInt1, int paramInt2)
  {
    if (paramInt2 != 41)
      return this.elementCount / 2L;
    if ((paramIndex.isUnique()) && (paramInt1 == paramIndex.getColumnCount()))
      return 1.0D;
    int i = paramIndex.getPosition();
    if ((this.searchCost == null) || (this.searchCost.length <= i))
      this.searchCost = new double[this.indexList.length][];
    if (this.searchCost[i] == null)
      this.searchCost[paramIndex.getPosition()] = this.indexList[paramIndex.getPosition()].searchCost(paramSession, this);
    return this.searchCost[paramIndex.getPosition()][(paramInt1 - 1)];
  }

  public long elementCount()
  {
    Index localIndex = this.indexList[0];
    if (this.elementCount < 0L)
      this.elementCount = ((IndexAVL)localIndex).getNodeCount(this.session, this);
    return this.elementCount;
  }

  public long elementCount(Session paramSession)
  {
    Index localIndex = this.indexList[0];
    if (this.elementCount < 0L)
      this.elementCount = ((IndexAVL)localIndex).getNodeCount(paramSession, this);
    if (paramSession != null)
    {
      int i = paramSession.database.txManager.getTransactionControl();
      if (i != 0)
        switch (this.table.getTableType())
        {
        case 4:
        case 5:
        case 7:
          return ((IndexAVL)localIndex).getNodeCount(paramSession, this);
        case 6:
        }
    }
    return this.elementCount;
  }

  public long elementCountUnique(Index paramIndex)
  {
    return 0L;
  }

  public void setElementCount(Index paramIndex, long paramLong1, long paramLong2)
  {
    this.elementCount = paramLong1;
  }

  public boolean hasNull(int paramInt)
  {
    return false;
  }

  public final void moveData(Session paramSession, PersistentStore paramPersistentStore, int paramInt1, int paramInt2)
  {
    Type localType1 = null;
    Type localType2 = null;
    Object localObject1 = null;
    Object localObject2;
    if ((paramInt2 >= 0) && (paramInt1 != -1))
    {
      localObject2 = ((Table)this.table).getColumn(paramInt1);
      localObject1 = ((ColumnSchema)localObject2).getDefaultValue(paramSession);
      localType2 = ((Table)this.table).getColumnTypes()[paramInt1];
    }
    if ((paramInt2 <= 0) && (paramInt1 != -1))
      localType1 = ((Table)paramPersistentStore.getTable()).getColumnTypes()[paramInt1];
    try
    {
      localObject2 = (Table)this.table;
      RowIterator localRowIterator = paramPersistentStore.rowIterator();
      Row localRow1;
      Object[] arrayOfObject;
      Object localObject3;
      while (localRowIterator.hasNext())
      {
        localRow1 = localRowIterator.getNextRow();
        arrayOfObject = localRow1.getData();
        localObject3 = ((Table)localObject2).getEmptyRowData();
        Object localObject4 = null;
        if ((paramInt2 == 0) && (paramInt1 != -1))
        {
          localObject4 = arrayOfObject[paramInt1];
          localObject1 = localType2.convertToType(paramSession, localObject4, localType1);
        }
        ArrayUtil.copyAdjustArray(arrayOfObject, localObject3, localObject1, paramInt1, paramInt2);
        ((Table)localObject2).systemSetIdentityColumn(paramSession, (Object[])localObject3);
        if (((Table)localObject2).hasGeneratedColumn())
          ((Table)localObject2).setGeneratedColumns(paramSession, (Object[])localObject3);
        ((Table)localObject2).enforceTypeLimits(paramSession, (Object[])localObject3);
        ((Table)localObject2).enforceRowConstraints(paramSession, (Object[])localObject3);
        Row localRow2 = (Row)getNewCachedObject(paramSession, localObject3, false);
        indexRow(paramSession, localRow2);
      }
      if (((Table)localObject2).isTemp())
        return;
      if ((localType1 != null) && (localType1.isLobType()))
      {
        localRowIterator = paramPersistentStore.rowIterator();
        while (localRowIterator.hasNext())
        {
          localRow1 = localRowIterator.getNextRow();
          arrayOfObject = localRow1.getData();
          localObject3 = arrayOfObject[paramInt1];
          if (localObject3 != null)
            paramSession.sessionData.adjustLobUsageCount(localObject3, -1);
        }
      }
      if ((localType2 != null) && (localType2.isLobType()))
      {
        localRowIterator = rowIterator();
        while (localRowIterator.hasNext())
        {
          localRow1 = localRowIterator.getNextRow();
          arrayOfObject = localRow1.getData();
          localObject3 = arrayOfObject[paramInt1];
          if (localObject3 != null)
            paramSession.sessionData.adjustLobUsageCount(localObject3, 1);
        }
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw Error.error(460);
    }
  }

  public void reindex(Session paramSession, Index paramIndex)
  {
    setAccessor(paramIndex, null);
    RowIterator localRowIterator = this.table.rowIterator(this);
    while (localRowIterator.hasNext())
    {
      RowAVL localRowAVL = (RowAVL)localRowIterator.getNextRow();
      localRowAVL.getNode(paramIndex.getPosition()).delete();
      paramIndex.insert(paramSession, this, localRowAVL);
    }
  }

  public void setReadOnly(boolean paramBoolean)
  {
  }

  public void writeLock()
  {
  }

  public void writeUnlock()
  {
  }

  void dropIndexFromRows(Index paramIndex1, Index paramIndex2)
  {
    RowIterator localRowIterator = paramIndex1.firstRow(this);
    int i = paramIndex2.getPosition() - 1;
    while (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      int j = i - 1;
      for (NodeAVL localNodeAVL = ((RowAVL)localRow).getNode(0); j-- > 0; localNodeAVL = localNodeAVL.nNext);
      localNodeAVL.nNext = localNodeAVL.nNext.nNext;
    }
  }

  boolean insertIndexNodes(Index paramIndex1, Index paramIndex2)
  {
    int i = paramIndex2.getPosition();
    RowIterator localRowIterator = paramIndex1.firstRow(this);
    int j = 0;
    Object localObject = null;
    try
    {
      while (localRowIterator.hasNext())
      {
        Row localRow1 = localRowIterator.getNextRow();
        ((RowAVL)localRow1).insertNode(i);
        j++;
        paramIndex2.insert(this.session, this, localRow1);
      }
      return true;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localObject = Error.error(460);
    }
    catch (HsqlException localHsqlException)
    {
      localObject = localHsqlException;
    }
    localRowIterator = paramIndex1.firstRow(this);
    for (int k = 0; k < j; k++)
    {
      Row localRow2 = localRowIterator.getNextRow();
      NodeAVL localNodeAVL = ((RowAVL)localRow2).getNode(0);
      int m = i;
      while (true)
      {
        m--;
        if (m <= 0)
          break;
        localNodeAVL = localNodeAVL.nNext;
      }
      localNodeAVL.nNext = localNodeAVL.nNext.nNext;
    }
    throw ((Throwable)localObject);
  }

  void destroy()
  {
    if (this.indexList.length == 0)
      return;
    IndexAVL localIndexAVL = (IndexAVL)this.indexList[0];
    NodeAVL localNodeAVL = (NodeAVL)this.accessorList[0];
    localIndexAVL.unlinkNodes(localNodeAVL);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.RowStoreAVL
 * JD-Core Version:    0.6.2
 */