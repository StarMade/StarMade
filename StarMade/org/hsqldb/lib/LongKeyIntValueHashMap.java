package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class LongKeyIntValueHashMap extends BaseHashMap
{
  private Set keySet;
  private Collection values;

  public LongKeyIntValueHashMap()
  {
    this(8);
  }

  public LongKeyIntValueHashMap(boolean paramBoolean)
  {
    this(8);
    this.minimizeOnEmpty = paramBoolean;
  }

  public LongKeyIntValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 2, 1, false);
  }

  public int get(long paramLong)
    throws NoSuchElementException
  {
    int i = getLookup(paramLong);
    if (i != -1)
      return this.intValueTable[i];
    throw new NoSuchElementException();
  }

  public int get(long paramLong, int paramInt)
  {
    int i = getLookup(paramLong);
    if (i != -1)
      return this.intValueTable[i];
    return paramInt;
  }

  public boolean get(long paramLong, int[] paramArrayOfInt)
  {
    int i = getLookup(paramLong);
    if (i != -1)
    {
      paramArrayOfInt[0] = this.intValueTable[i];
      return true;
    }
    return false;
  }

  public int getLookup(long paramLong)
  {
    return super.getLookup(paramLong);
  }

  public boolean put(long paramLong, int paramInt)
  {
    int i = size();
    super.addOrRemove(paramLong, paramInt, null, null, false);
    return i != size();
  }

  public boolean remove(long paramLong)
  {
    int i = size();
    super.addOrRemove(paramLong, 0L, null, null, true);
    return i != size();
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
      LongKeyIntValueHashMap tmp8_5 = LongKeyIntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }

    public int size()
    {
      return LongKeyIntValueHashMap.this.size();
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
      LongKeyIntValueHashMap.this.clear();
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
      LongKeyIntValueHashMap tmp8_5 = LongKeyIntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }

    public int size()
    {
      return LongKeyIntValueHashMap.this.size();
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
      LongKeyIntValueHashMap.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.LongKeyIntValueHashMap
 * JD-Core Version:    0.6.2
 */