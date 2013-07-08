package it.unimi.dsi.fastutil.longs;

import java.util.Set;

public abstract interface LongSet
  extends LongCollection, Set<Long>
{
  public abstract LongIterator iterator();
  
  public abstract boolean remove(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */