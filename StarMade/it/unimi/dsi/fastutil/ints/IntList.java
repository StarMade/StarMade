package it.unimi.dsi.fastutil.ints;

import java.util.List;

public abstract interface IntList extends List<Integer>, Comparable<List<? extends Integer>>, IntCollection
{
  public abstract IntListIterator iterator();

  @Deprecated
  public abstract IntListIterator intListIterator();

  @Deprecated
  public abstract IntListIterator intListIterator(int paramInt);

  public abstract IntListIterator listIterator();

  public abstract IntListIterator listIterator(int paramInt);

  @Deprecated
  public abstract IntList intSubList(int paramInt1, int paramInt2);

  public abstract IntList subList(int paramInt1, int paramInt2);

  public abstract void size(int paramInt);

  public abstract void getElements(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3);

  public abstract void removeElements(int paramInt1, int paramInt2);

  public abstract void addElements(int paramInt, int[] paramArrayOfInt);

  public abstract void addElements(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3);

  public abstract boolean add(int paramInt);

  public abstract void add(int paramInt1, int paramInt2);

  public abstract boolean addAll(int paramInt, IntCollection paramIntCollection);

  public abstract boolean addAll(int paramInt, IntList paramIntList);

  public abstract boolean addAll(IntList paramIntList);

  public abstract int getInt(int paramInt);

  public abstract int indexOf(int paramInt);

  public abstract int lastIndexOf(int paramInt);

  public abstract int removeInt(int paramInt);

  public abstract int set(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntList
 * JD-Core Version:    0.6.2
 */