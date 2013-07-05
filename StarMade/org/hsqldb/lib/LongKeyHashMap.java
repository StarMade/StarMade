package org.hsqldb.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class LongKeyHashMap extends BaseHashMap
{
  Set keySet;
  Collection values;
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();

  public LongKeyHashMap()
  {
    this(16);
  }

  public LongKeyHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 2, 3, false);
  }

  public Lock getWriteLock()
  {
    return this.writeLock;
  }

  public Object get(long paramLong)
  {
    this.readLock.lock();
    try
    {
      int i = getLookup(paramLong);
      if (i != -1)
      {
        localObject1 = this.objectValueTable[i];
        return localObject1;
      }
      Object localObject1 = null;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Object put(long paramLong, Object paramObject)
  {
    this.writeLock.lock();
    try
    {
      Object localObject1 = super.addOrRemove(paramLong, 0L, null, paramObject, false);
      return localObject1;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public boolean containsValue(Object paramObject)
  {
    this.readLock.lock();
    try
    {
      boolean bool = super.containsValue(paramObject);
      return bool;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Object remove(long paramLong)
  {
    this.writeLock.lock();
    try
    {
      Object localObject1 = super.addOrRemove(paramLong, 0L, null, null, true);
      return localObject1;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public boolean containsKey(long paramLong)
  {
    this.readLock.lock();
    try
    {
      boolean bool = super.containsKey(paramLong);
      return bool;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void clear()
  {
    this.writeLock.lock();
    try
    {
      super.clear();
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public Object[] toArray()
  {
    this.readLock.lock();
    try
    {
      Object[] arrayOfObject1 = new Object[size()];
      int i = 0;
      BaseHashMap.BaseHashIterator localBaseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
      while (localBaseHashIterator.hasNext())
        arrayOfObject1[(i++)] = localBaseHashIterator.next();
      Object[] arrayOfObject2 = arrayOfObject1;
      return arrayOfObject2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public int getOrderedMatchCount(int[] paramArrayOfInt)
  {
    int i = 0;
    try
    {
      this.readLock.lock();
      while ((i < paramArrayOfInt.length) && (super.containsKey(paramArrayOfInt[i])))
        i++;
    }
    finally
    {
      this.readLock.unlock();
    }
    return i;
  }

  public Set keySet()
  {
    if (this.keySet == null)
      this.keySet = new KeySet();
    return this.keySet;
  }

  public Collection values()
  {
    if (this.values == null)
      this.values = new Values();
    return this.values;
  }

  class Values
    implements Collection
  {
    Values()
    {
    }

    public Iterator iterator()
    {
      LongKeyHashMap tmp8_5 = LongKeyHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }

    public int size()
    {
      return LongKeyHashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean add(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new RuntimeException();
    }

    public boolean remove(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      LongKeyHashMap.this.clear();
    }
  }

  class KeySet
    implements Set
  {
    KeySet()
    {
    }

    public Iterator iterator()
    {
      LongKeyHashMap tmp8_5 = LongKeyHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }

    public int size()
    {
      return LongKeyHashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      throw new RuntimeException();
    }

    public Object get(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean add(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new RuntimeException();
    }

    public boolean remove(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      LongKeyHashMap.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.LongKeyHashMap
 * JD-Core Version:    0.6.2
 */