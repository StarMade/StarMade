package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface LongBigListIterator extends LongBidirectionalIterator, BigListIterator<Long>
{
  public abstract void set(long paramLong);

  public abstract void add(long paramLong);

  public abstract void set(Long paramLong);

  public abstract void add(Long paramLong);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigListIterator
 * JD-Core Version:    0.6.2
 */