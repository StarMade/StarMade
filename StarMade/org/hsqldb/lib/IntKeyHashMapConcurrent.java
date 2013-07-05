package org.hsqldb.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class IntKeyHashMapConcurrent extends BaseHashMap
{
  Set keySet;
  Collection values;
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();

  public IntKeyHashMapConcurrent()
  {
    this(8);
  }

  public IntKeyHashMapConcurrent(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 1, 3, false);
  }

  public Lock getWriteLock()
  {
    return this.writeLock;
  }

  public Object get(int paramInt)
  {
    try
    {
      this.readLock.lock();
      int i = getLookup(paramInt);
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

  public Object put(int paramInt, Object paramObject)
  {
    try
    {
      this.writeLock.lock();
      Object localObject1 = super.addOrRemove(paramInt, 0L, null, paramObject, false);
      return localObject1;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public boolean containsValue(Object paramObject)
  {
    try
    {
      this.readLock.lock();
      boolean bool = super.containsValue(paramObject);
      return bool;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Object remove(int paramInt)
  {
    try
    {
      this.writeLock.lock();
      Object localObject1 = super.addOrRemove(paramInt, 0L, null, null, true);
      return localObject1;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public boolean containsKey(int paramInt)
  {
    try
    {
      this.readLock.lock();
      boolean bool = super.containsKey(paramInt);
      return bool;
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
      int j = i;
      return j;
    }
    finally
    {
      this.readLock.unlock();
    }
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
      IntKeyHashMapConcurrent tmp8_5 = IntKeyHashMapConcurrent.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }

    public int size()
    {
      return IntKeyHashMapConcurrent.this.size();
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
      IntKeyHashMapConcurrent.this.clear();
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
      IntKeyHashMapConcurrent tmp8_5 = IntKeyHashMapConcurrent.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }

    public int size()
    {
      return IntKeyHashMapConcurrent.this.size();
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
      IntKeyHashMapConcurrent.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.IntKeyHashMapConcurrent
 * JD-Core Version:    0.6.2
 */