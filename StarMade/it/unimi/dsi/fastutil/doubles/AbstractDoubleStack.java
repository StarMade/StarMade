package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractDoubleStack
  extends AbstractStack<Double>
  implements DoubleStack
{
  public void push(Double local_o)
  {
    push(local_o.doubleValue());
  }
  
  public Double pop()
  {
    return Double.valueOf(popDouble());
  }
  
  public Double top()
  {
    return Double.valueOf(topDouble());
  }
  
  public Double peek(int local_i)
  {
    return Double.valueOf(peekDouble(local_i));
  }
  
  public void push(double local_k)
  {
    push(Double.valueOf(local_k));
  }
  
  public double popDouble()
  {
    return pop().doubleValue();
  }
  
  public double topDouble()
  {
    return top().doubleValue();
  }
  
  public double peekDouble(int local_i)
  {
    return peek(local_i).doubleValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */