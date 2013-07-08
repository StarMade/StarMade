package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface LongBidirectionalIterator
  extends LongIterator, ObjectBidirectionalIterator<Long>
{
  public abstract long previousLong();
  
  public abstract int back(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */