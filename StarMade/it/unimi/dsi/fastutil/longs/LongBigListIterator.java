package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface LongBigListIterator
  extends LongBidirectionalIterator, BigListIterator<Long>
{
  public abstract void set(long paramLong);
  
  public abstract void add(long paramLong);
  
  public abstract void set(Long paramLong);
  
  public abstract void add(Long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */