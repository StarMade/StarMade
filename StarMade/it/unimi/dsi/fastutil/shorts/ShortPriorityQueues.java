package it.unimi.dsi.fastutil.shorts;

public class ShortPriorityQueues
{
  public static ShortPriorityQueue synchronize(ShortPriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static ShortPriorityQueue synchronize(ShortPriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements ShortPriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortPriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(ShortPriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(ShortPriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(short local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public short dequeueShort()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueShort();
      }
    }
    
    public short firstShort()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstShort();
      }
    }
    
    public short lastShort()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastShort();
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
    
    public ShortComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Short local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Short dequeue()
    {
      synchronized (this.sync)
      {
        return (Short)this.field_62.dequeue();
      }
    }
    
    public Short first()
    {
      synchronized (this.sync)
      {
        return (Short)this.field_62.first();
      }
    }
    
    public Short last()
    {
      synchronized (this.sync)
      {
        return (Short)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */