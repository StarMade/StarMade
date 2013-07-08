package it.unimi.dsi.fastutil;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IndirectDoublePriorityQueues
{
  public static final EmptyIndirectDoublePriorityQueue EMPTY_QUEUE = new EmptyIndirectDoublePriorityQueue();
  
  public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> local_q)
  {
    return new SynchronizedIndirectDoublePriorityQueue(local_q);
  }
  
  public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> local_q, Object sync)
  {
    return new SynchronizedIndirectDoublePriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedIndirectDoublePriorityQueue<K>
    implements IndirectDoublePriorityQueue<K>
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IndirectDoublePriorityQueue<K> field_397;
    protected final Object sync;
    
    protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> local_q, Object sync)
    {
      this.field_397 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> local_q)
    {
      this.field_397 = local_q;
      this.sync = this;
    }
    
    public void enqueue(int index)
    {
      synchronized (this.sync)
      {
        this.field_397.enqueue(index);
      }
    }
    
    public int dequeue()
    {
      synchronized (this.sync)
      {
        return this.field_397.dequeue();
      }
    }
    
    public int first()
    {
      synchronized (this.sync)
      {
        return this.field_397.first();
      }
    }
    
    public int last()
    {
      synchronized (this.sync)
      {
        return this.field_397.last();
      }
    }
    
    public boolean contains(int index)
    {
      synchronized (this.sync)
      {
        return this.field_397.contains(index);
      }
    }
    
    public int secondaryFirst()
    {
      synchronized (this.sync)
      {
        return this.field_397.secondaryFirst();
      }
    }
    
    public int secondaryLast()
    {
      synchronized (this.sync)
      {
        return this.field_397.secondaryLast();
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.field_397.isEmpty();
      }
    }
    
    public int size()
    {
      synchronized (this.sync)
      {
        return this.field_397.size();
      }
    }
    
    public void clear()
    {
      synchronized (this.sync)
      {
        this.field_397.clear();
      }
    }
    
    public void changed()
    {
      synchronized (this.sync)
      {
        this.field_397.changed();
      }
    }
    
    public void allChanged()
    {
      synchronized (this.sync)
      {
        this.field_397.allChanged();
      }
    }
    
    public void changed(int local_i)
    {
      synchronized (this.sync)
      {
        this.field_397.changed(local_i);
      }
    }
    
    public boolean remove(int local_i)
    {
      synchronized (this.sync)
      {
        return this.field_397.remove(local_i);
      }
    }
    
    public Comparator<? super K> comparator()
    {
      synchronized (this.sync)
      {
        return this.field_397.comparator();
      }
    }
    
    public Comparator<? super K> secondaryComparator()
    {
      synchronized (this.sync)
      {
        return this.field_397.secondaryComparator();
      }
    }
    
    public int secondaryFront(int[] local_a)
    {
      return this.field_397.secondaryFront(local_a);
    }
    
    public int front(int[] local_a)
    {
      return this.field_397.front(local_a);
    }
  }
  
  public static class EmptyIndirectDoublePriorityQueue
    extends IndirectPriorityQueues.EmptyIndirectPriorityQueue
  {
    public int secondaryFirst()
    {
      throw new NoSuchElementException();
    }
    
    public int secondaryLast()
    {
      throw new NoSuchElementException();
    }
    
    public Comparator<?> secondaryComparator()
    {
      return null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectDoublePriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */