package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractIntPriorityQueue
  extends AbstractPriorityQueue<Integer>
  implements IntPriorityQueue
{
  public void enqueue(Integer local_x)
  {
    enqueue(local_x.intValue());
  }
  
  public Integer dequeue()
  {
    return Integer.valueOf(dequeueInt());
  }
  
  public Integer first()
  {
    return Integer.valueOf(firstInt());
  }
  
  public Integer last()
  {
    return Integer.valueOf(lastInt());
  }
  
  public int lastInt()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */