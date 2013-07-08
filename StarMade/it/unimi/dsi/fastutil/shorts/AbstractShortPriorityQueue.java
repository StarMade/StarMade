package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractShortPriorityQueue
  extends AbstractPriorityQueue<Short>
  implements ShortPriorityQueue
{
  public void enqueue(Short local_x)
  {
    enqueue(local_x.shortValue());
  }
  
  public Short dequeue()
  {
    return Short.valueOf(dequeueShort());
  }
  
  public Short first()
  {
    return Short.valueOf(firstShort());
  }
  
  public Short last()
  {
    return Short.valueOf(lastShort());
  }
  
  public short lastShort()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */