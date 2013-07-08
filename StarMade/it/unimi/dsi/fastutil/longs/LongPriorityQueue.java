package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.PriorityQueue;

public abstract interface LongPriorityQueue
  extends PriorityQueue<Long>
{
  public abstract void enqueue(long paramLong);
  
  public abstract long dequeueLong();
  
  public abstract long firstLong();
  
  public abstract long lastLong();
  
  public abstract LongComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */