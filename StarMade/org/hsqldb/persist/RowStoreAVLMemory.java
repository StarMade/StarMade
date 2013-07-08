package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.RowAction;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreAVLMemory
  extends RowStoreAVL
  implements PersistentStore
{
  Database database;
  int rowIdSequence = 0;
  
  public RowStoreAVLMemory(PersistentStoreCollection paramPersistentStoreCollection, Table paramTable)
  {
    this.database = paramTable.database;
    this.manager = paramPersistentStoreCollection;
    this.table = paramTable;
    this.indexList = paramTable.getIndexList();
    this.accessorList = new CachedObject[this.indexList.length];
    paramPersistentStoreCollection.setStore(paramTable, this);
  }
  
  public boolean isMemory()
  {
    return true;
  }
  
  public int getAccessCount()
  {
    return 0;
  }
  
  public void set(CachedObject paramCachedObject) {}
  
  public CachedObject get(long paramLong)
  {
    throw Error.runtimeError(201, "RowStoreAVMemory");
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean)
  {
    throw Error.runtimeError(201, "RowStoreAVLMemory");
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean)
  {
    return paramCachedObject;
  }
  
  public int getStorageSize(long paramLong)
  {
    return 0;
  }
  
  public void add(CachedObject paramCachedObject) {}
  
  public CachedObject get(RowInputInterface paramRowInputInterface)
  {
    return null;
  }
  
  public CachedObject getNewInstance(int paramInt)
  {
    return null;
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    int i;
    synchronized (this)
    {
      i = this.rowIdSequence++;
    }
    ??? = new RowAVL(this.table, (Object[])paramObject, i, this);
    if (paramBoolean)
    {
      RowAction localRowAction = new RowAction(paramSession, this.table, (byte)1, (Row)???, null);
      ((Row)???).rowAction = localRowAction;
    }
    return ???;
  }
  
  public void removeAll()
  {
    destroy();
    this.elementCount = 0L;
    ArrayUtil.fillArray(this.accessorList, null);
  }
  
  public void remove(long paramLong) {}
  
  public void removePersistence(long paramLong) {}
  
  public void release(long paramLong) {}
  
  public void commitPersistence(CachedObject paramCachedObject) {}
  
  public void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = paramRow.getData();
    switch (paramInt1)
    {
    case 2: 
      this.database.logger.writeDeleteStatement(paramSession, (Table)this.table, arrayOfObject);
      break;
    case 1: 
      this.database.logger.writeInsertStatement(paramSession, paramRow, (Table)this.table);
      break;
    case 4: 
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
      if (paramInt2 == 0) {
        remove(paramRow.getPos());
      }
      break;
    }
  }
  
  public DataFileCache getCache()
  {
    return null;
  }
  
  public void setCache(DataFileCache paramDataFileCache) {}
  
  public void release()
  {
    destroy();
    setTimestamp(0L);
    ArrayUtil.fillArray(this.accessorList, null);
    this.elementCount = 0L;
  }
  
  public void setAccessor(Index paramIndex, CachedObject paramCachedObject)
  {
    Index localIndex = paramIndex;
    this.accessorList[localIndex.getPosition()] = paramCachedObject;
  }
  
  public void setAccessor(Index paramIndex, long paramLong) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.RowStoreAVLMemory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */