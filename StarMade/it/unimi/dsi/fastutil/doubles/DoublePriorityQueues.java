package it.unimi.dsi.fastutil.doubles;

public class DoublePriorityQueues
{
  public static DoublePriorityQueue synchronize(DoublePriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static DoublePriorityQueue synchronize(DoublePriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements DoublePriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoublePriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(DoublePriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(DoublePriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(double local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public double dequeueDouble()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueDouble();
      }
    }
    
    public double firstDouble()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstDouble();
      }
    }
    
    public double lastDouble()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastDouble();
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
    
    public DoubleComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Double local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Double dequeue()
    {
      synchronized (this.sync)
      {
        return (Double)this.field_62.dequeue();
      }
    }
    
    public Double first()
    {
      synchronized (this.sync)
      {
        return (Double)this.field_62.first();
      }
    }
    
    public Double last()
    {
      synchronized (this.sync)
      {
        return (Double)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoublePriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */