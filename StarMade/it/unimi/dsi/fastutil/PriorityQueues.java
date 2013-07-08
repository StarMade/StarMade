package it.unimi.dsi.fastutil;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueues
{
  public static final EmptyPriorityQueue EMPTY_QUEUE = new EmptyPriorityQueue();
  
  public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue<K>
    implements PriorityQueue<K>
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final PriorityQueue<K> field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(PriorityQueue<K> local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(PriorityQueue<K> local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(K local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public K dequeue()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeue();
      }
    }
    
    public K first()
    {
      synchronized (this.sync)
      {
        return this.field_62.first();
      }
    }
    
    public K last()
    {
      synchronized (this.sync)
      {
        return this.field_62.last();
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.field_62.isEmpty();
      }
    }
    
    public int size()
    {
      synchronized (this.sync)
      {
        return this.field_62.size();
      }
    }
    
    public void clear()
    {
      synchronized (this.sync)
      {
        this.field_62.clear();
      }
    }
    
    public void changed()
    {
      synchronized (this.sync)
      {
        this.field_62.changed();
      }
    }
    
    public Comparator<? super K> comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
  }
  
  public static class EmptyPriorityQueue
    extends AbstractPriorityQueue
  {
    public void enqueue(Object local_o)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object dequeue()
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
    
    public void clear() {}
    
    public Object first()
    {
      throw new NoSuchElementException();
    }
    
    public Object last()
    {
      throw new NoSuchElementException();
    }
    
    public void changed()
    {
      throw new NoSuchElementException();
    }
    
    public Comparator<?> comparator()
    {
      return null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.PriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */