package it.unimi.dsi.fastutil;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IndirectPriorityQueues
{
  public static final EmptyIndirectPriorityQueue EMPTY_QUEUE = new EmptyIndirectPriorityQueue();
  
  public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> local_q)
  {
    return new SynchronizedIndirectPriorityQueue(local_q);
  }
  
  public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> local_q, Object sync)
  {
    return new SynchronizedIndirectPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedIndirectPriorityQueue<K>
    implements IndirectPriorityQueue<K>
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IndirectPriorityQueue<K> field_397;
    protected final Object sync;
    
    protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> local_q, Object sync)
    {
      this.field_397 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> local_q)
    {
      this.field_397 = local_q;
      this.sync = this;
    }
    
    public void enqueue(int local_x)
    {
      synchronized (this.sync)
      {
        this.field_397.enqueue(local_x);
      }
    }
    
    public int dequeue()
    {
      synchronized (this.sync)
      {
        return this.field_397.dequeue();
      }
    }
    
    public boolean contains(int index)
    {
      synchronized (this.sync)
      {
        return this.field_397.contains(index);
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
    
    public int front(int[] local_a)
    {
      return this.field_397.front(local_a);
    }
  }
  
  public static class EmptyIndirectPriorityQueue
    extends AbstractIndirectPriorityQueue
  {
    public void enqueue(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int dequeue()
    {
      throw new NoSuchElementException();
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public int size()
    {
      return 0;
    }
    
    public boolean contains(int index)
    {
      return false;
    }
    
    public void clear() {}
    
    public int first()
    {
      throw new NoSuchElementException();
    }
    
    public int last()
    {
      throw new NoSuchElementException();
    }
    
    public void changed()
    {
      throw new NoSuchElementException();
    }
    
    public void allChanged() {}
    
    public Comparator<?> comparator()
    {
      return null;
    }
    
    public void changed(int local_i)
    {
      throw new IllegalArgumentException("Index " + local_i + " is not in the queue");
    }
    
    public boolean remove(int local_i)
    {
      return false;
    }
    
    public int front(int[] local_a)
    {
      return 0;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */