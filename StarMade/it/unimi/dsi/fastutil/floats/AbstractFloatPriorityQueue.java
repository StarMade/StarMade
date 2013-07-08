package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractFloatPriorityQueue
  extends AbstractPriorityQueue<Float>
  implements FloatPriorityQueue
{
  public void enqueue(Float local_x)
  {
    enqueue(local_x.floatValue());
  }
  
  public Float dequeue()
  {
    return Float.valueOf(dequeueFloat());
  }
  
  public Float first()
  {
    return Float.valueOf(firstFloat());
  }
  
  public Float last()
  {
    return Float.valueOf(lastFloat());
  }
  
  public float lastFloat()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */