package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.BigList;

public abstract interface ShortBigList extends BigList<Short>, ShortCollection, Comparable<BigList<? extends Short>>
{
  public abstract ShortBigListIterator iterator();

  public abstract ShortBigListIterator listIterator();

  public abstract ShortBigListIterator listIterator(long paramLong);

  public abstract ShortBigList subList(long paramLong1, long paramLong2);

  public abstract void getElements(long paramLong1, short[][] paramArrayOfShort, long paramLong2, long paramLong3);

  public abstract void removeElements(long paramLong1, long paramLong2);

  public abstract void addElements(long paramLong, short[][] paramArrayOfShort);

  public abstract void addElements(long paramLong1, short[][] paramArrayOfShort, long paramLong2, long paramLong3);

  public abstract void add(long paramLong, short paramShort);

  public abstract boolean addAll(long paramLong, ShortCollection paramShortCollection);

  public abstract boolean addAll(long paramLong, ShortBigList paramShortBigList);

  public abstract boolean addAll(ShortBigList paramShortBigList);

  public abstract short getShort(long paramLong);

  public abstract long indexOf(short paramShort);

  public abstract long lastIndexOf(short paramShort);

  public abstract short removeShort(long paramLong);

  public abstract short set(long paramLong, short paramShort);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigList
 * JD-Core Version:    0.6.2
 */