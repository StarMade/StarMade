package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractIntStack
  extends AbstractStack<Integer>
  implements IntStack
{
  public void push(Integer local_o)
  {
    push(local_o.intValue());
  }
  
  public Integer pop()
  {
    return Integer.valueOf(popInt());
  }
  
  public Integer top()
  {
    return Integer.valueOf(topInt());
  }
  
  public Integer peek(int local_i)
  {
    return Integer.valueOf(peekInt(local_i));
  }
  
  public void push(int local_k)
  {
    push(Integer.valueOf(local_k));
  }
  
  public int popInt()
  {
    return pop().intValue();
  }
  
  public int topInt()
  {
    return top().intValue();
  }
  
  public int peekInt(int local_i)
  {
    return peek(local_i).intValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */