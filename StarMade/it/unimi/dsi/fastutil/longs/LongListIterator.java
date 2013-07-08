package it.unimi.dsi.fastutil.longs;

import java.util.ListIterator;

public abstract interface LongListIterator
  extends ListIterator<Long>, LongBidirectionalIterator
{
  public abstract void set(long paramLong);
  
  public abstract void add(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */