package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.TransactionManager;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.ObjectComparator;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class Cache
  extends BaseHashMap
{
  final DataFileCache dataFileCache;
  private int capacity;
  private long bytesCapacity;
  private final CachedObjectComparator rowComparator;
  private final BaseHashMap.BaseHashIterator objectIterator;
  private CachedObject[] rowTable;
  long cacheBytesLength;
  StopWatch saveAllTimer = new StopWatch(false);
  StopWatch sortTimer = new StopWatch(false);
  int saveRowCount = 0;
  
  Cache(DataFileCache paramDataFileCache)
  {
    super(paramDataFileCache.capacity(), 3, 0, true);
    this.maxCapacity = paramDataFileCache.capacity();
    this.dataFileCache = paramDataFileCache;
    this.capacity = paramDataFileCache.capacity();
    this.bytesCapacity = paramDataFileCache.bytesCapacity();
    this.rowComparator = new CachedObjectComparator();
    this.rowTable = new CachedObject[this.capacity];
    this.cacheBytesLength = 0L;
    this.objectIterator = new BaseHashMap.BaseHashIterator(this, true);
    this.comparator = this.rowComparator;
  }
  
  void resize(int paramInt, long paramLong) {}
  
  long getTotalCachedBlockSize()
  {
    return this.cacheBytesLength;
  }
  
  public synchronized CachedObject get(long paramLong)
  {
    if (this.accessCount > 2146435071)
    {
      updateAccessCounts();
      resetAccessCount();
      updateObjectAccessCounts();
    }
    int i = getObjectLookup(paramLong);
    if (i == -1) {
      return null;
    }
    this.accessTable[i] = (++this.accessCount);
    CachedObject localCachedObject = (CachedObject)this.objectKeyTable[i];
    return localCachedObject;
  }
  
  synchronized void put(long paramLong, CachedObject paramCachedObject)
  {
    int i = paramCachedObject.getStorageSize();
    if ((size() >= this.capacity) || (i + this.cacheBytesLength > this.bytesCapacity))
    {
      cleanUp();
      if (size() >= this.capacity) {
        forceCleanUp();
      }
    }
    if (this.accessCount > 2146435071)
    {
      updateAccessCounts();
      resetAccessCount();
      updateObjectAccessCounts();
    }
    super.addOrRemoveObject(paramCachedObject, paramCachedObject.getPos(), false);
    paramCachedObject.setInMemory(true);
    this.cacheBytesLength += i;
  }
  
  synchronized CachedObject release(long paramLong)
  {
    CachedObject localCachedObject = (CachedObject)super.addOrRemoveObject(null, paramLong, true);
    if (localCachedObject == null) {
      return null;
    }
    this.cacheBytesLength -= localCachedObject.getStorageSize();
    localCachedObject.setInMemory(false);
    return localCachedObject;
  }
  
  synchronized void replace(long paramLong, CachedObject paramCachedObject)
  {
    int i = super.getLookup(paramLong);
    this.objectKeyTable[i] = paramCachedObject;
  }
  
  private void updateAccessCounts()
  {
    for (int j = 0; j < this.objectKeyTable.length; j++)
    {
      CachedObject localCachedObject = (CachedObject)this.objectKeyTable[j];
      if (localCachedObject != null)
      {
        int i = localCachedObject.getAccessCount();
        if (i > this.accessTable[j]) {
          this.accessTable[j] = i;
        }
      }
    }
  }
  
  private void updateObjectAccessCounts()
  {
    for (int j = 0; j < this.objectKeyTable.length; j++)
    {
      CachedObject localCachedObject = (CachedObject)this.objectKeyTable[j];
      if (localCachedObject != null)
      {
        int i = this.accessTable[j];
        localCachedObject.updateAccessCount(i);
      }
    }
  }
  
  private synchronized void cleanUp()
  {
    updateAccessCounts();
    int i = size() / 2;
    int j = getAccessCountCeiling(i, i / 8);
    int k = 0;
    this.objectIterator.reset();
    while (this.objectIterator.hasNext())
    {
      CachedObject localCachedObject = (CachedObject)this.objectIterator.next();
      int m = this.objectIterator.getAccessCount();
      int n = (localCachedObject.isNew()) && (localCachedObject.getStorageSize() >= 4096) ? 1 : 0;
      int i1 = m <= j ? 1 : 0;
      if ((i1 != 0) || (n != 0)) {
        synchronized (localCachedObject)
        {
          if (localCachedObject.isKeepInMemory())
          {
            this.objectIterator.setAccessCount(j + 1);
          }
          else
          {
            if (localCachedObject.hasChanged()) {
              this.rowTable[(k++)] = localCachedObject;
            }
            if (i1 != 0)
            {
              localCachedObject.setInMemory(false);
              this.objectIterator.remove();
              this.cacheBytesLength -= localCachedObject.getStorageSize();
              i--;
            }
          }
        }
      }
      if (k == this.rowTable.length)
      {
        saveRows(k);
        k = 0;
      }
    }
    super.setAccessCountFloor(j);
    saveRows(k);
  }
  
  synchronized void forceCleanUp()
  {
    this.objectIterator.reset();
    while (this.objectIterator.hasNext())
    {
      CachedObject localCachedObject = (CachedObject)this.objectIterator.next();
      synchronized (localCachedObject)
      {
        if ((!localCachedObject.hasChanged()) && (!localCachedObject.isKeepInMemory()))
        {
          localCachedObject.setInMemory(false);
          this.objectIterator.remove();
          this.cacheBytesLength -= localCachedObject.getStorageSize();
        }
      }
    }
  }
  
  private synchronized void saveRows(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    long l = this.saveAllTimer.elapsedTime();
    this.rowComparator.setType(1);
    this.sortTimer.zero();
    this.sortTimer.start();
    ArraySort.sort(this.rowTable, 0, paramInt, this.rowComparator);
    this.sortTimer.stop();
    this.saveAllTimer.start();
    this.dataFileCache.saveRows(this.rowTable, 0, paramInt);
    this.saveRowCount += paramInt;
    this.saveAllTimer.stop();
    logSaveRowsEvent(paramInt, l);
  }
  
  synchronized void saveAll()
  {
    int i = 0;
    this.objectIterator.reset();
    while (this.objectIterator.hasNext())
    {
      if (i == this.rowTable.length)
      {
        saveRows(i);
        i = 0;
      }
      CachedObject localCachedObject = (CachedObject)this.objectIterator.next();
      if (localCachedObject.hasChanged())
      {
        this.rowTable[i] = localCachedObject;
        i++;
      }
    }
    saveRows(i);
  }
  
  void logSynchEvent()
  {
    this.dataFileCache.logDetailEvent("cache sync");
  }
  
  void logSaveRowsEvent(int paramInt, long paramLong)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("cache save rows [count,time] totals ");
    localStringBuffer.append(this.saveRowCount);
    localStringBuffer.append(',').append(this.saveAllTimer.elapsedTime()).append(' ');
    localStringBuffer.append("operation ").append(paramInt).append(',');
    localStringBuffer.append(this.saveAllTimer.elapsedTime() - paramLong).append(' ');
    localStringBuffer.append("txts ");
    localStringBuffer.append(this.dataFileCache.database.txManager.getGlobalChangeTimestamp());
    this.dataFileCache.logDetailEvent(localStringBuffer.toString());
  }
  
  public synchronized void clear()
  {
    super.clear();
    this.cacheBytesLength = 0L;
  }
  
  public Iterator getIterator()
  {
    this.objectIterator.reset();
    return this.objectIterator;
  }
  
  static final class CachedObjectComparator
    implements ObjectComparator
  {
    static final int COMPARE_LAST_ACCESS = 0;
    static final int COMPARE_POSITION = 1;
    static final int COMPARE_SIZE = 2;
    private int compareType = 1;
    
    void setType(int paramInt)
    {
      this.compareType = paramInt;
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      long l;
      switch (this.compareType)
      {
      case 1: 
        l = ((CachedObject)paramObject1).getPos() - ((CachedObject)paramObject2).getPos();
        break;
      case 2: 
        l = ((CachedObject)paramObject1).getStorageSize() - ((CachedObject)paramObject2).getStorageSize();
        break;
      default: 
        return 0;
      }
      return l > 0L ? 1 : l == 0L ? 0 : -1;
    }
    
    public int hashCode(Object paramObject)
    {
      return paramObject.hashCode();
    }
    
    public long longKey(Object paramObject)
    {
      return ((CachedObject)paramObject).getPos();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.Cache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */