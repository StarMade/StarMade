package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractLongPriorityQueue
  extends AbstractPriorityQueue<Long>
  implements LongPriorityQueue
{
  public void enqueue(Long local_x)
  {
    enqueue(local_x.longValue());
  }
  
  public Long dequeue()
  {
    return Long.valueOf(dequeueLong());
  }
  
  public Long first()
  {
    return Long.valueOf(firstLong());
  }
  
  public Long last()
  {
    return Long.valueOf(lastLong());
  }
  
  public long lastLong()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */