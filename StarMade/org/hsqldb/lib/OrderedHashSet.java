package org.hsqldb.lib;

public class OrderedHashSet
  extends HashSet
  implements HsqlList, Set
{
  public OrderedHashSet()
  {
    super(8);
    this.isList = true;
  }
  
  public boolean remove(Object paramObject)
  {
    return super.removeObject(paramObject, true) != null;
  }
  
  public Object remove(int paramInt)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    return super.removeObject(this.objectKeyTable[paramInt], true);
  }
  
  public boolean insert(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    if ((paramInt < 0) || (paramInt > size())) {
      throw new IndexOutOfBoundsException();
    }
    if (contains(paramObject)) {
      return false;
    }
    if (paramInt == size()) {
      return add(paramObject);
    }
    Object[] arrayOfObject = new Object[size()];
    toArray(arrayOfObject);
    super.clear();
    for (int i = 0; i < paramInt; i++) {
      add(arrayOfObject[i]);
    }
    add(paramObject);
    for (i = paramInt; i < arrayOfObject.length; i++) {
      add(arrayOfObject[i]);
    }
    return true;
  }
  
  public Object set(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    throw new IndexOutOfBoundsException();
  }
  
  public void add(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    throw new IndexOutOfBoundsException();
  }
  
  public Object get(int paramInt)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    return this.objectKeyTable[paramInt];
  }
  
  public void toArray(Object[] paramArrayOfObject)
  {
    System.arraycopy(this.objectKeyTable, 0, paramArrayOfObject, 0, paramArrayOfObject.length);
  }
  
  public int getIndex(Object paramObject)
  {
    return getLookup(paramObject, paramObject.hashCode());
  }
  
  public int getLargestIndex(OrderedHashSet paramOrderedHashSet)
  {
    int i = -1;
    int j = 0;
    int k = paramOrderedHashSet.size();
    while (j < k)
    {
      int m = getIndex(paramOrderedHashSet.get(j));
      if (m > i) {
        i = m;
      }
      j++;
    }
    return i;
  }
  
  public int getSmallestIndex(OrderedHashSet paramOrderedHashSet)
  {
    int i = -1;
    int j = 0;
    int k = paramOrderedHashSet.size();
    while (j < k)
    {
      int m = getIndex(paramOrderedHashSet.get(j));
      if ((m != -1) && ((i == -1) || (m < i))) {
        i = m;
      }
      j++;
    }
    return i;
  }
  
  public int getCommonElementCount(Set paramSet)
  {
    int i = 0;
    int j = 0;
    int k = size();
    while (j < k)
    {
      if (paramSet.contains(this.objectKeyTable[j])) {
        i++;
      }
      j++;
    }
    return i;
  }
  
  public static OrderedHashSet addAll(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    if (paramOrderedHashSet2 == null) {
      return paramOrderedHashSet1;
    }
    if (paramOrderedHashSet1 == null) {
      paramOrderedHashSet1 = new OrderedHashSet();
    }
    paramOrderedHashSet1.addAll(paramOrderedHashSet2);
    return paramOrderedHashSet1;
  }
  
  public static OrderedHashSet add(OrderedHashSet paramOrderedHashSet, Object paramObject)
  {
    if (paramObject == null) {
      return paramOrderedHashSet;
    }
    if (paramOrderedHashSet == null) {
      paramOrderedHashSet = new OrderedHashSet();
    }
    paramOrderedHashSet.add(paramObject);
    return paramOrderedHashSet;
  }
  
  private void checkRange(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size())) {
      throw new IndexOutOfBoundsException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.OrderedHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */