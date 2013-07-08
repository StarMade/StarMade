package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.IndirectPriorityQueue;

public abstract interface LongIndirectPriorityQueue
  extends IndirectPriorityQueue<Long>
{
  public abstract LongComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */