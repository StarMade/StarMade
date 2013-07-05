package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.BigList;

public abstract interface IntBigList extends BigList<Integer>, IntCollection, Comparable<BigList<? extends Integer>>
{
  public abstract IntBigListIterator iterator();

  public abstract IntBigListIterator listIterator();

  public abstract IntBigListIterator listIterator(long paramLong);

  public abstract IntBigList subList(long paramLong1, long paramLong2);

  public abstract void getElements(long paramLong1, int[][] paramArrayOfInt, long paramLong2, long paramLong3);

  public abstract void removeElements(long paramLong1, long paramLong2);

  public abstract void addElements(long paramLong, int[][] paramArrayOfInt);

  public abstract void addElements(long paramLong1, int[][] paramArrayOfInt, long paramLong2, long paramLong3);

  public abstract void add(long paramLong, int paramInt);

  public abstract boolean addAll(long paramLong, IntCollection paramIntCollection);

  public abstract boolean addAll(long paramLong, IntBigList paramIntBigList);

  public abstract boolean addAll(IntBigList paramIntBigList);

  public abstract int getInt(long paramLong);

  public abstract long indexOf(int paramInt);

  public abstract long lastIndexOf(int paramInt);

  public abstract int removeInt(long paramLong);

  public abstract int set(long paramLong, int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntBigList
 * JD-Core Version:    0.6.2
 */