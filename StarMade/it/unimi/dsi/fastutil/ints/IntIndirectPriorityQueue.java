package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.IndirectPriorityQueue;

public abstract interface IntIndirectPriorityQueue
  extends IndirectPriorityQueue<Integer>
{
  public abstract IntComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */