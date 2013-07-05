package it.unimi.dsi.fastutil.longs;

import java.util.Set;

public abstract interface LongSet extends LongCollection, Set<Long>
{
  public abstract LongIterator iterator();

  public abstract boolean remove(long paramLong);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSet
 * JD-Core Version:    0.6.2
 */