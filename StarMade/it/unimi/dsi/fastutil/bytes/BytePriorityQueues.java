package it.unimi.dsi.fastutil.bytes;

public class BytePriorityQueues
{
  public static BytePriorityQueue synchronize(BytePriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static BytePriorityQueue synchronize(BytePriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements BytePriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BytePriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(BytePriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(BytePriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(byte local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public byte dequeueByte()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueByte();
      }
    }
    
    public byte firstByte()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstByte();
      }
    }
    
    public byte lastByte()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastByte();
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
    
    public ByteComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Byte local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Byte dequeue()
    {
      synchronized (this.sync)
      {
        return (Byte)this.field_62.dequeue();
      }
    }
    
    public Byte first()
    {
      synchronized (this.sync)
      {
        return (Byte)this.field_62.first();
      }
    }
    
    public Byte last()
    {
      synchronized (this.sync)
      {
        return (Byte)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.BytePriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */