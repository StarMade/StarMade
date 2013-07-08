package it.unimi.dsi.fastutil.ints;

public class IntPriorityQueues
{
  public static IntPriorityQueue synchronize(IntPriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static IntPriorityQueue synchronize(IntPriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements IntPriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntPriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(IntPriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(IntPriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(int local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public int dequeueInt()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueInt();
      }
    }
    
    public int firstInt()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstInt();
      }
    }
    
    public int lastInt()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastInt();
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
    
    public IntComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Integer local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Integer dequeue()
    {
      synchronized (this.sync)
      {
        return (Integer)this.field_62.dequeue();
      }
    }
    
    public Integer first()
    {
      synchronized (this.sync)
      {
        return (Integer)this.field_62.first();
      }
    }
    
    public Integer last()
    {
      synchronized (this.sync)
      {
        return (Integer)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */