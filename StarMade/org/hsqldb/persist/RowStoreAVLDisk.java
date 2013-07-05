package org.hsqldb.persist;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.RowAVLDiskLarge;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TransactionManager;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowStoreAVLDisk extends RowStoreAVL
{
  DataFileCache cache;
  RowOutputInterface rowOut;
  boolean largeData;

  public RowStoreAVLDisk(PersistentStoreCollection paramPersistentStoreCollection, DataFileCache paramDataFileCache, Table paramTable)
  {
    this.database = paramTable.database;
    this.manager = paramPersistentStoreCollection;
    this.table = paramTable;
    this.indexList = paramTable.getIndexList();
    this.accessorList = new CachedObject[this.indexList.length];
    this.cache = paramDataFileCache;
    if (paramDataFileCache != null)
    {
      this.rowOut = paramDataFileCache.rowOut.duplicate();
      paramDataFileCache.adjustStoreCount(1);
    }
    paramPersistentStoreCollection.setStore(paramTable, this);
    this.largeData = (this.database.logger.getDataFileFactor() > 1);
  }

  public boolean isMemory()
  {
    return false;
  }

  public int getAccessCount()
  {
    return this.cache.getAccessCount();
  }

  public void set(CachedObject paramCachedObject)
  {
    Row localRow = (Row)paramCachedObject;
    this.database.txManager.setTransactionInfo(localRow);
  }

  public CachedObject get(long paramLong)
  {
    CachedObject localCachedObject = this.cache.get(paramLong, this, false);
    return localCachedObject;
  }

  public CachedObject get(long paramLong, boolean paramBoolean)
  {
    CachedObject localCachedObject = this.cache.get(paramLong, this, paramBoolean);
    return localCachedObject;
  }

  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean)
  {
    paramCachedObject = this.cache.get(paramCachedObject, this, paramBoolean);
    return paramCachedObject;
  }

  public int getStorageSize(long paramLong)
  {
    return this.cache.get(paramLong, this, false).getStorageSize();
  }

  public void add(CachedObject paramCachedObject)
  {
    int i = paramCachedObject.getRealSize(this.rowOut);
    i += this.indexList.length * 16;
    i = this.rowOut.getStorageSize(i);
    paramCachedObject.setStorageSize(i);
    this.cache.add(paramCachedObject);
  }

  public CachedObject get(RowInputInterface paramRowInputInterface)
  {
    try
    {
      if (this.largeData)
        return new RowAVLDiskLarge(this.table, paramRowInputInterface);
      return new RowAVLDisk(this.table, paramRowInputInterface);
    }
    catch (IOException localIOException)
    {
      throw Error.error(466, localIOException);
    }
  }

  public CachedObject getNewInstance(int paramInt)
  {
    return null;
  }

  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    Object localObject;
    if (this.largeData)
      localObject = new RowAVLDiskLarge(this.table, (Object[])paramObject, this);
    else
      localObject = new RowAVLDisk(this.table, (Object[])paramObject, this);
    add((CachedObject)localObject);
    if (paramBoolean)
    {
      RowAction localRowAction = new RowAction(paramSession, this.table, (byte)1, (Row)localObject, null);
      ((Row)localObject).rowAction = localRowAction;
    }
    return localObject;
  }

  public void indexRow(Session paramSession, Row paramRow)
  {
    try
    {
      super.indexRow(paramSession, paramRow);
    }
    catch (HsqlException localHsqlException)
    {
      this.database.txManager.removeTransactionInfo(paramRow);
      throw localHsqlException;
    }
  }

  public void removeAll()
  {
    this.elementCount = 0L;
    ArrayUtil.fillArray(this.accessorList, null);
  }

  public void remove(long paramLong)
  {
    this.cache.remove(paramLong, this);
  }

  public void removePersistence(long paramLong)
  {
  }

  public void release(long paramLong)
  {
    this.cache.release(paramLong);
  }

  public void commitPersistence(CachedObject paramCachedObject)
  {
  }

  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = paramRow.getData();
    switch (paramInt1)
    {
    case 2:
      this.database.logger.writeDeleteStatement(paramSession, (Table)this.table, arrayOfObject);
      if (paramInt2 == 0)
        remove(paramRow.getPos());
      break;
    case 1:
      this.database.logger.writeInsertStatement(paramSession, paramRow, (Table)this.table);
      break;
    case 4:
      if (paramInt2 == 0)
        remove(paramRow.getPos());
      break;
    case 3:
      delete(paramSession, paramRow);
      this.database.txManager.removeTransactionInfo(paramRow);
      remove(paramRow.getPos());
    }
  }

  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2:
      if (paramInt2 == 0)
      {
        paramRow = (Row)get(paramRow, true);
        ((RowAVL)paramRow).setNewNodes(this);
        paramRow.keepInMemory(false);
        indexRow(paramSession, paramRow);
      }
      break;
    case 1:
      if (paramInt2 == 0)
      {
        delete(paramSession, paramRow);
        remove(paramRow.getPos());
      }
      break;
    case 4:
      if (paramInt2 == 0)
        remove(paramRow.getPos());
      break;
    case 3:
    }
  }

  public DataFileCache getCache()
  {
    return this.cache;
  }

  public void setCache(DataFileCache paramDataFileCache)
  {
    this.cache = paramDataFileCache;
  }

  public void release()
  {
    ArrayUtil.fillArray(this.accessorList, null);
    this.cache.adjustStoreCount(-1);
    this.cache = null;
    this.elementCount = 0L;
  }

  public CachedObject getAccessor(Index paramIndex)
  {
    NodeAVL localNodeAVL = (NodeAVL)this.accessorList[paramIndex.getPosition()];
    if (localNodeAVL == null)
      return null;
    return localNodeAVL;
  }

  public void setAccessor(Index paramIndex, CachedObject paramCachedObject)
  {
    Index localIndex = paramIndex;
    this.accessorList[localIndex.getPosition()] = paramCachedObject;
  }

  public void setAccessor(Index paramIndex, long paramLong)
  {
    Object localObject = get(paramLong, false);
    if (localObject != null)
    {
      NodeAVL localNodeAVL = ((RowAVL)localObject).getNode(paramIndex.getPosition());
      localObject = localNodeAVL;
    }
    setAccessor(paramIndex, (CachedObject)localObject);
  }

  public void resetAccessorKeys(Index[] paramArrayOfIndex)
  {
    if ((this.indexList.length == 0) || (this.accessorList[0] == null))
    {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    }
    throw Error.runtimeError(201, "RowStoreAVLDisk");
  }

  public void setReadOnly(boolean paramBoolean)
  {
  }

  public void writeLock()
  {
    this.cache.writeLock.lock();
  }

  public void writeUnlock()
  {
    this.cache.writeLock.unlock();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.RowStoreAVLDisk
 * JD-Core Version:    0.6.2
 */