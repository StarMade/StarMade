package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.PriorityQueue;

public abstract interface IntPriorityQueue
  extends PriorityQueue<Integer>
{
  public abstract void enqueue(int paramInt);
  
  public abstract int dequeueInt();
  
  public abstract int firstInt();
  
  public abstract int lastInt();
  
  public abstract IntComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */