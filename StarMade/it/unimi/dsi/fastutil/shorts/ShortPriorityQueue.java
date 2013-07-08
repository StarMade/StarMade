package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.PriorityQueue;

public abstract interface ShortPriorityQueue
  extends PriorityQueue<Short>
{
  public abstract void enqueue(short paramShort);
  
  public abstract short dequeueShort();
  
  public abstract short firstShort();
  
  public abstract short lastShort();
  
  public abstract ShortComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */