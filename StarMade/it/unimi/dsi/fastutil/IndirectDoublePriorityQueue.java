package it.unimi.dsi.fastutil;

import java.util.Comparator;

public abstract interface IndirectDoublePriorityQueue<K>
  extends IndirectPriorityQueue<K>
{
  public abstract Comparator<? super K> secondaryComparator();
  
  public abstract int secondaryFirst();
  
  public abstract int secondaryLast();
  
  public abstract int secondaryFront(int[] paramArrayOfInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectDoublePriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */