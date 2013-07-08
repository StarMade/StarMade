package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigList;

public abstract interface LongBigList
  extends BigList<Long>, LongCollection, Comparable<BigList<? extends Long>>
{
  public abstract LongBigListIterator iterator();
  
  public abstract LongBigListIterator listIterator();
  
  public abstract LongBigListIterator listIterator(long paramLong);
  
  public abstract LongBigList subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, long[][] paramArrayOfLong, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, long[][] paramArrayOfLong);
  
  public abstract void addElements(long paramLong1, long[][] paramArrayOfLong, long paramLong2, long paramLong3);
  
  public abstract void add(long paramLong1, long paramLong2);
  
  public abstract boolean addAll(long paramLong, LongCollection paramLongCollection);
  
  public abstract boolean addAll(long paramLong, LongBigList paramLongBigList);
  
  public abstract boolean addAll(LongBigList paramLongBigList);
  
  public abstract long getLong(long paramLong);
  
  public abstract long indexOf(long paramLong);
  
  public abstract long lastIndexOf(long paramLong);
  
  public abstract long removeLong(long paramLong);
  
  public abstract long set(long paramLong1, long paramLong2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */