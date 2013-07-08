package org.hsqldb.persist;

import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowInputInterface;

public abstract interface PersistentStore
{
  public static final int INT_STORE_SIZE = 4;
  public static final int LONG_STORE_SIZE = 8;
  public static final PersistentStore[] emptyArray = new PersistentStore[0];
  
  public abstract TableBase getTable();
  
  public abstract long getTimestamp();
  
  public abstract void setTimestamp(long paramLong);
  
  public abstract boolean isMemory();
  
  public abstract void setMemory(boolean paramBoolean);
  
  public abstract int getAccessCount();
  
  public abstract void set(CachedObject paramCachedObject);
  
  public abstract CachedObject get(long paramLong);
  
  public abstract CachedObject get(long paramLong, boolean paramBoolean);
  
  public abstract CachedObject get(CachedObject paramCachedObject, boolean paramBoolean);
  
  public abstract int getStorageSize(long paramLong);
  
  public abstract void add(CachedObject paramCachedObject);
  
  public abstract CachedObject get(RowInputInterface paramRowInputInterface);
  
  public abstract CachedObject get(CachedObject paramCachedObject, RowInputInterface paramRowInputInterface);
  
  public abstract CachedObject getNewInstance(int paramInt);
  
  public abstract CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean);
  
  public abstract void removePersistence(long paramLong);
  
  public abstract void removeAll();
  
  public abstract void remove(long paramLong);
  
  public abstract void release(long paramLong);
  
  public abstract void commitPersistence(CachedObject paramCachedObject);
  
  public abstract void delete(Session paramSession, Row paramRow);
  
  public abstract void indexRow(Session paramSession, Row paramRow);
  
  public abstract void commitRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2);
  
  public abstract void rollbackRow(Session paramSession, Row paramRow, int paramInt1, int paramInt2);
  
  public abstract void indexRows(Session paramSession);
  
  public abstract RowIterator rowIterator();
  
  public abstract DataFileCache getCache();
  
  public abstract void setCache(DataFileCache paramDataFileCache);
  
  public abstract void release();
  
  public abstract PersistentStore getAccessorStore(Index paramIndex);
  
  public abstract CachedObject getAccessor(Index paramIndex);
  
  public abstract void setAccessor(Index paramIndex, CachedObject paramCachedObject);
  
  public abstract void setAccessor(Index paramIndex, long paramLong);
  
  public abstract double searchCost(Session paramSession, Index paramIndex, int paramInt1, int paramInt2);
  
  public abstract long elementCount();
  
  public abstract long elementCount(Session paramSession);
  
  public abstract long elementCountUnique(Index paramIndex);
  
  public abstract void setElementCount(Index paramIndex, long paramLong1, long paramLong2);
  
  public abstract boolean hasNull(int paramInt);
  
  public abstract void resetAccessorKeys(Index[] paramArrayOfIndex);
  
  public abstract Index[] getAccessorKeys();
  
  public abstract void moveData(Session paramSession, PersistentStore paramPersistentStore, int paramInt1, int paramInt2);
  
  public abstract void reindex(Session paramSession, Index paramIndex);
  
  public abstract void setReadOnly(boolean paramBoolean);
  
  public abstract void writeLock();
  
  public abstract void writeUnlock();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.PersistentStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */