package it.unimi.dsi.fastutil.chars;

public class CharPriorityQueues
{
  public static CharPriorityQueue synchronize(CharPriorityQueue local_q)
  {
    return new SynchronizedPriorityQueue(local_q);
  }
  
  public static CharPriorityQueue synchronize(CharPriorityQueue local_q, Object sync)
  {
    return new SynchronizedPriorityQueue(local_q, sync);
  }
  
  public static class SynchronizedPriorityQueue
    implements CharPriorityQueue
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharPriorityQueue field_62;
    protected final Object sync;
    
    protected SynchronizedPriorityQueue(CharPriorityQueue local_q, Object sync)
    {
      this.field_62 = local_q;
      this.sync = sync;
    }
    
    protected SynchronizedPriorityQueue(CharPriorityQueue local_q)
    {
      this.field_62 = local_q;
      this.sync = this;
    }
    
    public void enqueue(char local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public char dequeueChar()
    {
      synchronized (this.sync)
      {
        return this.field_62.dequeueChar();
      }
    }
    
    public char firstChar()
    {
      synchronized (this.sync)
      {
        return this.field_62.firstChar();
      }
    }
    
    public char lastChar()
    {
      synchronized (this.sync)
      {
        return this.field_62.lastChar();
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
    
    public CharComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.field_62.comparator();
      }
    }
    
    public void enqueue(Character local_x)
    {
      synchronized (this.sync)
      {
        this.field_62.enqueue(local_x);
      }
    }
    
    public Character dequeue()
    {
      synchronized (this.sync)
      {
        return (Character)this.field_62.dequeue();
      }
    }
    
    public Character first()
    {
      synchronized (this.sync)
      {
        return (Character)this.field_62.first();
      }
    }
    
    public Character last()
    {
      synchronized (this.sync)
      {
        return (Character)this.field_62.last();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharPriorityQueues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */