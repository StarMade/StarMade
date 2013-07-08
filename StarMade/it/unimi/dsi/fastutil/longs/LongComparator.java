package it.unimi.dsi.fastutil.longs;

import java.util.Comparator;

public abstract interface LongComparator
  extends Comparator<Long>
{
  public abstract int compare(long paramLong1, long paramLong2);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */