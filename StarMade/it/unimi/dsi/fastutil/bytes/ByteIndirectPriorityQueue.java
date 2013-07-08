package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.IndirectPriorityQueue;

public abstract interface ByteIndirectPriorityQueue
  extends IndirectPriorityQueue<Byte>
{
  public abstract ByteComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */