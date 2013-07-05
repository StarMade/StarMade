package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;

public class OrderedLongHashSet extends BaseHashMap
{
  public OrderedLongHashSet()
  {
    this(8);
  }

  public OrderedLongHashSet(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 2, 0, false);
    this.isList = true;
  }

  public boolean contains(long paramLong)
  {
    return super.containsKey(paramLong);
  }

  public boolean add(long paramLong)
  {
    int i = size();
    super.addOrRemove(paramLong, 0L, null, null, false);
    return i != size();
  }

  public boolean remove(long paramLong)
  {
    int i = size();
    super.addOrRemove(paramLong, 0L, null, null, true);
    boolean bool = i != size();
    if (bool)
    {
      long[] arrayOfLong = toArray();
      super.clear();
      for (int j = 0; j < arrayOfLong.length; j++)
        add(arrayOfLong[j]);
    }
    return bool;
  }

  public long get(int paramInt)
  {
    checkRange(paramInt);
    return this.longKeyTable[paramInt];
  }

  public int getIndex(long paramLong)
  {
    return getLookup(paramLong);
  }

  public int getStartMatchCount(long[] paramArrayOfLong)
  {
    for (int i = 0; (i < paramArrayOfLong.length) && (super.containsKey(paramArrayOfLong[i])); i++);
    return i;
  }

  public int getOrderedStartMatchCount(long[] paramArrayOfLong)
  {
    for (int i = 0; (i < paramArrayOfLong.length) && (i < size()) && (get(i) == paramArrayOfLong[i]); i++);
    return i;
  }

  public boolean addAll(Collection paramCollection)
  {
    int i = size();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      add(localIterator.nextLong());
    return i != size();
  }

  public long[] toArray()
  {
    int i = -1;
    long[] arrayOfLong = new long[size()];
    for (int j = 0; j < arrayOfLong.length; j++)
    {
      i = super.nextLookup(i);
      long l = this.intKeyTable[i];
      arrayOfLong[j] = l;
    }
    return arrayOfLong;
  }

  private void checkRange(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size()))
      throw new IndexOutOfBoundsException();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.OrderedLongHashSet
 * JD-Core Version:    0.6.2
 */