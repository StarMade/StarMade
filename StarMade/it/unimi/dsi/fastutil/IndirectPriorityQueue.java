package it.unimi.dsi.fastutil;

import java.util.Comparator;

public abstract interface IndirectPriorityQueue<K>
{
  public abstract void enqueue(int paramInt);
  
  public abstract int dequeue();
  
  public abstract boolean isEmpty();
  
  public abstract int size();
  
  public abstract void clear();
  
  public abstract int first();
  
  public abstract int last();
  
  public abstract void changed();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract void changed(int paramInt);
  
  public abstract void allChanged();
  
  public abstract boolean contains(int paramInt);
  
  public abstract boolean remove(int paramInt);
  
  public abstract int front(int[] paramArrayOfInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */