package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAVLDiskData;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreAVLDiskData
  extends RowStoreAVLDisk
{
  public RowStoreAVLDiskData(PersistentStoreCollection paramPersistentStoreCollection, Table paramTable)
  {
    super(paramPersistentStoreCollection, null, paramTable);
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean)
  {
    paramCachedObject = this.cache.get(paramCachedObject, this, paramBoolean);
    return paramCachedObject;
  }
  
  public void add(CachedObject paramCachedObject)
  {
    writeLock();
    try
    {
      int i = paramCachedObject.getRealSize(this.cache.rowOut);
      paramCachedObject.setStorageSize(i);
      this.cache.add(paramCachedObject);
    }
    finally
    {
      writeUnlock();
    }
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface)
  {
    try
    {
      RowAVLDiskData localRowAVLDiskData = new RowAVLDiskData(this, this.table, paramRowInputInterface);
      localRowAVLDiskData.setPos(paramRowInputInterface.getPos());
      localRowAVLDiskData.setStorageSize(paramRowInputInterface.getSize());
      localRowAVLDiskData.setChanged(false);
      ((TextCache)this.cache).addInit(localRowAVLDiskData);
      return localRowAVLDiskData;
    }
    catch (IOException localIOException)
    {
      throw Error.error(484, localIOException);
    }
  }
  
  public CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface)
  {
    try
    {
      ((RowAVLDiskData)paramCachedObject).getRowData(this.table, paramRowInputInterface);
      return paramCachedObject;
    }
    catch (IOException localIOException)
    {
      throw Error.error(484, localIOException);
    }
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    RowAVLDiskData localRowAVLDiskData = new RowAVLDiskData(this, this.table, (Object[])paramObject);
    add(localRowAVLDiskData);
    if (paramBoolean) {
      RowAction.addInsertAction(paramSession, this.table, localRowAVLDiskData);
    }
    return localRowAVLDiskData;
  }
  
  public void indexRow(Session paramSession, Row paramRow)
  {
    super.indexRow(paramSession, paramRow);
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public void removeAll()
  {
    destroy();
    this.elementCount = 0L;
    ArrayUtil.fillArray(this.accessorList, null);
  }
  
  public void remove(long paramLong)
  {
    this.cache.remove(paramLong, this);
  }
  
  public void removePersistence(Row paramRow)
  {
    this.cache.removePersistence(paramRow);
  }
  
  public void release(long paramLong)
  {
    this.cache.release(paramLong);
  }
  
  public CachedObject getAccessor(Index paramIndex)
  {
    int i = paramIndex.getPosition();
    if (i >= this.accessorList.length) {
      throw Error.runtimeError(201, "RowStoreAVL");
    }
    return this.accessorList[i];
  }
  
  public void commitPersistence(CachedObject paramCachedObject)
  {
    try
    {
      this.cache.saveRow(paramCachedObject);
    }
    catch (HsqlException localHsqlException) {}
  }
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2: 
      removePersistence(paramRow);
      break;
    case 1: 
      commitPersistence(paramRow);
      break;
    case 4: 
      if (paramInt2 == 0)
      {
        remove(paramRow.getPos());
      }
      else
      {
        delete(paramSession, paramRow);
        remove(paramRow.getPos());
      }
      break;
    case 3: 
      if (paramInt2 != 0)
      {
        delete(paramSession, paramRow);
        remove(paramRow.getPos());
      }
      break;
    }
  }
  
  public void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2: 
      if (paramInt2 == 0)
      {
        ((RowAVL)paramRow).setNewNodes(this);
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
      {
        remove(paramRow.getPos());
      }
      else
      {
        delete(paramSession, paramRow);
        remove(paramRow.getPos());
      }
      break;
    }
  }
  
  public void release()
  {
    destroy();
    ArrayUtil.fillArray(this.accessorList, null);
    this.table.database.logger.closeTextCache((Table)this.table);
    this.cache = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.RowStoreAVLDiskData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */