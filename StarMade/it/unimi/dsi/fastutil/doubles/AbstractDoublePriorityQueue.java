package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractDoublePriorityQueue
  extends AbstractPriorityQueue<Double>
  implements DoublePriorityQueue
{
  public void enqueue(Double local_x)
  {
    enqueue(local_x.doubleValue());
  }
  
  public Double dequeue()
  {
    return Double.valueOf(dequeueDouble());
  }
  
  public Double first()
  {
    return Double.valueOf(firstDouble());
  }
  
  public Double last()
  {
    return Double.valueOf(lastDouble());
  }
  
  public double lastDouble()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoublePriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */