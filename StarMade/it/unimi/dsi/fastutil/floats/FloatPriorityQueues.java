package it.unimi.dsi.fastutil.floats;

public class FloatPriorityQueues
{
  public static FloatPriorityQueue synchronize(FloatPriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static FloatPriorityQueue synchronize(FloatPriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements FloatPriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatPriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(FloatPriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(FloatPriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(float local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public float dequeueFloat()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueFloat();
      }
    }
    
    public float firstFloat()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstFloat();
      }
    }
    
    public float lastFloat()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastFloat();
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
    
    public FloatComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Float local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Float dequeue()
    {
      synchronized (this.sync)
      {
        return (Float)this.field_62.dequeue();
      }
    }
    
    public Float first()
    {
      synchronized (this.sync)
      {
        return (Float)this.field_62.first();
      }
    }
    
    public Float last()
    {
      synchronized (this.sync)
      {
        return (Float)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */