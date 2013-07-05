package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.BigList;

public abstract interface BooleanBigList extends BigList<Boolean>, BooleanCollection, Comparable<BigList<? extends Boolean>>
{
  public abstract BooleanBigListIterator iterator();

  public abstract BooleanBigListIterator listIterator();

  public abstract BooleanBigListIterator listIterator(long paramLong);

  public abstract BooleanBigList subList(long paramLong1, long paramLong2);

  public abstract void getElements(long paramLong1, boolean[][] paramArrayOfBoolean, long paramLong2, long paramLong3);

  public abstract void removeElements(long paramLong1, long paramLong2);

  public abstract void addElements(long paramLong, boolean[][] paramArrayOfBoolean);

  public abstract void addElements(long paramLong1, boolean[][] paramArrayOfBoolean, long paramLong2, long paramLong3);

  public abstract void add(long paramLong, boolean paramBoolean);

  public abstract boolean addAll(long paramLong, BooleanCollection paramBooleanCollection);

  public abstract boolean addAll(long paramLong, BooleanBigList paramBooleanBigList);

  public abstract boolean addAll(BooleanBigList paramBooleanBigList);

  public abstract boolean getBoolean(long paramLong);

  public abstract long indexOf(boolean paramBoolean);

  public abstract long lastIndexOf(boolean paramBoolean);

  public abstract boolean removeBoolean(long paramLong);

  public abstract boolean set(long paramLong, boolean paramBoolean);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigList
 * JD-Core Version:    0.6.2
 */