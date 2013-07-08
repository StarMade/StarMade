package it.unimi.dsi.fastutil.longs;

public class LongPriorityQueues
{
  public static LongPriorityQueue synchronize(LongPriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static LongPriorityQueue synchronize(LongPriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements LongPriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongPriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(LongPriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(LongPriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(long local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public long dequeueLong()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueLong();
      }
    }
    
    public long firstLong()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstLong();
      }
    }
    
    public long lastLong()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastLong();
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
    
    public LongComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Long local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Long dequeue()
    {
      synchronized (this.sync)
      {
        return (Long)this.field_62.dequeue();
      }
    }
    
    public Long first()
    {
      synchronized (this.sync)
      {
        return (Long)this.field_62.first();
      }
    }
    
    public Long last()
    {
      synchronized (this.sync)
      {
        return (Long)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */