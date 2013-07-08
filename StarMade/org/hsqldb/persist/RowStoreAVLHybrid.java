package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.IndexAVL;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowStoreAVLHybrid
  extends RowStoreAVL
  implements PersistentStore
{
  DataFileCacheSession cache;
  private int maxMemoryRowCount;
  private boolean useDisk;
  boolean isCached;
  int rowIdSequence = 0;
  
  public RowStoreAVLHybrid(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase, boolean paramBoolean)
  {
    this.session = paramSession;
    this.manager = paramPersistentStoreCollection;
    this.table = paramTableBase;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.useDisk = paramBoolean;
    if (this.maxMemoryRowCount == 0) {
      this.useDisk = false;
    }
    if (paramTableBase.getTableType() == 9) {
      setTimestamp(paramSession.getActionTimestamp());
    }
    resetAccessorKeys(paramTableBase.getIndexList());
    paramPersistentStoreCollection.setStore(paramTableBase, this);
    this.nullsList = new boolean[paramTableBase.getColumnCount()];
  }
  
  public boolean isMemory()
  {
    return !this.isCached;
  }
  
  public void setMemory(boolean paramBoolean)
  {
    this.useDisk = (!paramBoolean);
  }
  
  public synchronized int getAccessCount()
  {
    return this.isCached ? this.cache.getAccessCount() : 0;
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong)
  {
    try
    {
      if (this.isCached) {
        return this.cache.get(paramLong, this, false);
      }
      throw Error.runtimeError(201, "RowStoreAVLHybrid");
    }
    catch (HsqlException localHsqlException) {}
    return null;
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean)
  {
    try
    {
      if (this.isCached) {
        return this.cache.get(paramLong, this, paramBoolean);
      }
      throw Error.runtimeError(201, "RowStoreAVLHybrid");
    }
    catch (HsqlException localHsqlException) {}
    return null;
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean)
  {
    try
    {
      if (this.isCached) {
        return this.cache.get(paramCachedObject, this, paramBoolean);
      }
      return paramCachedObject;
    }
    catch (HsqlException localHsqlException) {}
    return null;
  }
  
  public int getStorageSize(long paramLong)
  {
    try
    {
      if (this.isCached) {
        return this.cache.get(paramLong, this, false).getStorageSize();
      }
      return 0;
    }
    catch (HsqlException localHsqlException) {}
    return 0;
  }
  
  public void add(CachedObject paramCachedObject)
  {
    if (this.isCached)
    {
      int i = paramCachedObject.getRealSize(this.cache.rowOut);
      i += this.indexList.length * 16;
      i = this.cache.rowOut.getStorageSize(i);
      paramCachedObject.setStorageSize(i);
      this.cache.add(paramCachedObject);
    }
    Object[] arrayOfObject = ((Row)paramCachedObject).getData();
    for (int j = 0; j < this.nullsList.length; j++) {
      if (arrayOfObject[j] == null) {
        this.nullsList[j] = true;
      }
    }
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface)
  {
    try
    {
      if (this.isCached) {
        return new RowAVLDisk(this.table, paramRowInputInterface);
      }
    }
    catch (HsqlException localHsqlException)
    {
      return null;
    }
    catch (IOException localIOException)
    {
      return null;
    }
    return null;
  }
  
  public CachedObject getNewInstance(int paramInt)
  {
    return null;
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    if ((!this.isCached) && (this.useDisk) && (this.elementCount >= this.maxMemoryRowCount)) {
      changeToDiskTable(paramSession);
    }
    if (this.isCached)
    {
      RowAVLDisk localRowAVLDisk = new RowAVLDisk(this.table, (Object[])paramObject, this);
      add(localRowAVLDisk);
      if (paramBoolean) {
        RowAction.addInsertAction(paramSession, (Table)this.table, localRowAVLDisk);
      }
      return localRowAVLDisk;
    }
    int i = this.rowIdSequence++;
    RowAVL localRowAVL = new RowAVL(this.table, (Object[])paramObject, i, this);
    add(localRowAVL);
    if (paramBoolean)
    {
      RowAction localRowAction = new RowAction(paramSession, this.table, (byte)1, localRowAVL, null);
      localRowAVL.rowAction = localRowAction;
    }
    return localRowAVL;
  }
  
  public void removeAll()
  {
    if (!this.isCached) {
      destroy();
    }
    this.elementCount = 0L;
    ArrayUtil.fillArray(this.accessorList, null);
    for (int i = 0; i < this.nullsList.length; i++) {
      this.nullsList[i] = false;
    }
  }
  
  public void remove(long paramLong)
  {
    if (this.isCached) {
      this.cache.remove(paramLong, this);
    }
  }
  
  public void removePersistence(long paramLong) {}
  
  public void release(long paramLong)
  {
    if (this.isCached) {
      this.cache.release(paramLong);
    }
  }
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2: 
      remove(paramRow.getPos());
      break;
    case 1: 
      break;
    case 4: 
      remove(paramRow.getPos());
      break;
    case 3: 
      delete(paramSession, paramRow);
    }
  }
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2: 
      paramRow = (Row)get(paramRow, true);
      ((RowAVL)paramRow).setNewNodes(this);
      paramRow.keepInMemory(false);
      indexRow(paramSession, paramRow);
      break;
    case 1: 
      delete(paramSession, paramRow);
      remove(paramRow.getPos());
      break;
    case 4: 
      remove(paramRow.getPos());
    }
  }
  
  public DataFileCache getCache()
  {
    return this.cache;
  }
  
  public void setCache(DataFileCache paramDataFileCache)
  {
    throw Error.runtimeError(201, "RowStoreAVLHybrid");
  }
  
  public void release()
  {
    if (!this.isCached) {
      destroy();
    }
    ArrayUtil.fillArray(this.accessorList, null);
    if (this.isCached)
    {
      this.cache.adjustStoreCount(-1);
      this.cache = null;
      this.isCached = false;
    }
    this.manager.setStore(this.table, null);
    this.elementCount = 0L;
  }
  
  public void delete(Session paramSession, Row paramRow)
  {
    super.delete(paramSession, paramRow);
  }
  
  public void setAccessor(Index paramIndex, CachedObject paramCachedObject)
  {
    Index localIndex = paramIndex;
    this.accessorList[localIndex.getPosition()] = paramCachedObject;
  }
  
  public void setAccessor(Index paramIndex, long paramLong) {}
  
  public synchronized void resetAccessorKeys(Index[] paramArrayOfIndex)
  {
    if ((this.indexList.length == 0) || (this.accessorList[0] == null))
    {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    }
    if (this.isCached) {
      throw Error.runtimeError(201, "RowStoreAVLHybrid");
    }
    super.resetAccessorKeys(paramArrayOfIndex);
  }
  
  public boolean hasNull(int paramInt)
  {
    return this.nullsList[paramInt];
  }
  
  public final void changeToDiskTable(Session paramSession)
  {
    this.cache = ((PersistentStoreCollectionSession)this.manager).getResultCache();
    if (this.cache != null)
    {
      IndexAVL localIndexAVL = (IndexAVL)this.indexList[0];
      NodeAVL localNodeAVL = (NodeAVL)this.accessorList[0];
      RowIterator localRowIterator = this.table.rowIterator(this);
      ArrayUtil.fillArray(this.accessorList, null);
      this.elementCount = 0L;
      this.isCached = true;
      this.cache.adjustStoreCount(1);
      while (localRowIterator.hasNext())
      {
        Row localRow1 = localRowIterator.getNextRow();
        Row localRow2 = (Row)getNewCachedObject(paramSession, localRow1.getData(), false);
        indexRow(paramSession, localRow2);
      }
      localIndexAVL.unlinkNodes(localNodeAVL);
    }
    this.maxMemoryRowCount = 2147483647;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.RowStoreAVLHybrid
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */