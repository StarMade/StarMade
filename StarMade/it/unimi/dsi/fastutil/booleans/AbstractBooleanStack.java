package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractBooleanStack
  extends AbstractStack<Boolean>
  implements BooleanStack
{
  public void push(Boolean local_o)
  {
    push(local_o.booleanValue());
  }
  
  public Boolean pop()
  {
    return Boolean.valueOf(popBoolean());
  }
  
  public Boolean top()
  {
    return Boolean.valueOf(topBoolean());
  }
  
  public Boolean peek(int local_i)
  {
    return Boolean.valueOf(peekBoolean(local_i));
  }
  
  public void push(boolean local_k)
  {
    push(Boolean.valueOf(local_k));
  }
  
  public boolean popBoolean()
  {
    return pop().booleanValue();
  }
  
  public boolean topBoolean()
  {
    return top().booleanValue();
  }
  
  public boolean peekBoolean(int local_i)
  {
    return peek(local_i).booleanValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */