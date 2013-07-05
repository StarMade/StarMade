package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class LongValueHashMap extends BaseHashMap
{
  Set keySet;

  public LongValueHashMap()
  {
    this(8);
  }

  public LongValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 3, 2, false);
  }

  public long get(Object paramObject)
    throws NoSuchElementException
  {
    if (paramObject == null)
      throw new NoSuchElementException();
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
      return this.longValueTable[j];
    throw new NoSuchElementException();
  }

  public long get(Object paramObject, int paramInt)
  {
    if (paramObject == null)
      throw new NoSuchElementException();
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
      return this.longValueTable[j];
    return paramInt;
  }

  public boolean get(Object paramObject, long[] paramArrayOfLong)
  {
    if (paramObject == null)
      throw new NoSuchElementException();
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
    {
      paramArrayOfLong[0] = this.longValueTable[j];
      return true;
    }
    return false;
  }

  public Object getKey(long paramLong)
  {
    BaseHashMap.BaseHashIterator localBaseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
    while (localBaseHashIterator.hasNext())
    {
      long l = localBaseHashIterator.nextLong();
      if (l == paramLong)
        return this.objectKeyTable[localBaseHashIterator.getLookup()];
    }
    return null;
  }

  public boolean put(Object paramObject, long paramLong)
  {
    if (paramObject == null)
      throw new NoSuchElementException();
    int i = size();
    super.addOrRemove(0L, paramLong, paramObject, null, false);
    return i != size();
  }

  public boolean remove(Object paramObject)
  {
    int i = size();
    super.addOrRemove(0L, 0L, paramObject, null, true);
    return i != size();
  }

  public boolean containsKey(Object paramObject)
  {
    return super.containsKey(paramObject);
  }

  public Set keySet()
  {
    if (this.keySet == null)
      this.keySet = new KeySet();
    return this.keySet;
  }

  public void putAll(LongValueHashMap paramLongValueHashMap)
  {
    Iterator localIterator = paramLongValueHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      put(localObject, paramLongValueHashMap.get(localObject));
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
      LongValueHashMap tmp8_5 = LongValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }

    public int size()
    {
      return LongValueHashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      return LongValueHashMap.this.containsKey(paramObject);
    }

    public Object get(Object paramObject)
    {
      int i = LongValueHashMap.this.getLookup(paramObject, paramObject.hashCode());
      if (i < 0)
        return null;
      return LongValueHashMap.this.objectKeyTable[i];
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
      int i = size();
      LongValueHashMap.this.remove(paramObject);
      return size() != i;
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      LongValueHashMap.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.LongValueHashMap
 * JD-Core Version:    0.6.2
 */