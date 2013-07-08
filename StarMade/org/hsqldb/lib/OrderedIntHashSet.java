package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;

public class OrderedIntHashSet
  extends BaseHashMap
{
  public OrderedIntHashSet()
  {
    this(8);
  }
  
  public OrderedIntHashSet(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 1, 0, false);
    this.isList = true;
  }
  
  public boolean contains(int paramInt)
  {
    return super.containsKey(paramInt);
  }
  
  public boolean add(int paramInt)
  {
    int i = size();
    super.addOrRemove(paramInt, 0L, null, null, false);
    return i != size();
  }
  
  public boolean remove(int paramInt)
  {
    int i = size();
    super.addOrRemove(paramInt, 0L, null, null, true);
    boolean bool = i != size();
    if (bool)
    {
      int[] arrayOfInt = toArray();
      super.clear();
      for (int j = 0; j < arrayOfInt.length; j++) {
        add(arrayOfInt[j]);
      }
    }
    return bool;
  }
  
  public int get(int paramInt)
  {
    checkRange(paramInt);
    return this.intKeyTable[paramInt];
  }
  
  public int getIndex(int paramInt)
  {
    return getLookup(paramInt);
  }
  
  public int getStartMatchCount(int[] paramArrayOfInt)
  {
    for (int i = 0; (i < paramArrayOfInt.length) && (super.containsKey(paramArrayOfInt[i])); i++) {}
    return i;
  }
  
  public int getOrderedStartMatchCount(int[] paramArrayOfInt)
  {
    for (int i = 0; (i < paramArrayOfInt.length) && (i < size()) && (get(i) == paramArrayOfInt[i]); i++) {}
    return i;
  }
  
  public boolean addAll(Collection paramCollection)
  {
    int i = size();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      add(localIterator.nextInt());
    }
    return i != size();
  }
  
  public int[] toArray()
  {
    int i = -1;
    int[] arrayOfInt = new int[size()];
    for (int j = 0; j < arrayOfInt.length; j++)
    {
      i = super.nextLookup(i);
      int k = this.intKeyTable[i];
      arrayOfInt[j] = k;
    }
    return arrayOfInt;
  }
  
  private void checkRange(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size())) {
      throw new IndexOutOfBoundsException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.OrderedIntHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */