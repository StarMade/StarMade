package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractFloatStack
  extends AbstractStack<Float>
  implements FloatStack
{
  public void push(Float local_o)
  {
    push(local_o.floatValue());
  }
  
  public Float pop()
  {
    return Float.valueOf(popFloat());
  }
  
  public Float top()
  {
    return Float.valueOf(topFloat());
  }
  
  public Float peek(int local_i)
  {
    return Float.valueOf(peekFloat(local_i));
  }
  
  public void push(float local_k)
  {
    push(Float.valueOf(local_k));
  }
  
  public float popFloat()
  {
    return pop().floatValue();
  }
  
  public float topFloat()
  {
    return top().floatValue();
  }
  
  public float peekFloat(int local_i)
  {
    return peek(local_i).floatValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */