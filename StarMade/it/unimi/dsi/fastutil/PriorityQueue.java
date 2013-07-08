package it.unimi.dsi.fastutil;

import java.util.Comparator;

public abstract interface PriorityQueue<K>
{
  public abstract void enqueue(K paramK);
  
  public abstract K dequeue();
  
  public abstract boolean isEmpty();
  
  public abstract int size();
  
  public abstract void clear();
  
  public abstract K first();
  
  public abstract K last();
  
  public abstract void changed();
  
  public abstract Comparator<? super K> comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.PriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */