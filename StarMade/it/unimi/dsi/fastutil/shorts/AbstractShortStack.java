package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractShortStack
  extends AbstractStack<Short>
  implements ShortStack
{
  public void push(Short local_o)
  {
    push(local_o.shortValue());
  }
  
  public Short pop()
  {
    return Short.valueOf(popShort());
  }
  
  public Short top()
  {
    return Short.valueOf(topShort());
  }
  
  public Short peek(int local_i)
  {
    return Short.valueOf(peekShort(local_i));
  }
  
  public void push(short local_k)
  {
    push(Short.valueOf(local_k));
  }
  
  public short popShort()
  {
    return pop().shortValue();
  }
  
  public short topShort()
  {
    return top().shortValue();
  }
  
  public short peekShort(int local_i)
  {
    return peek(local_i).shortValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */