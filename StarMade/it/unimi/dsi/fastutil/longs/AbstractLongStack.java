package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractLongStack
  extends AbstractStack<Long>
  implements LongStack
{
  public void push(Long local_o)
  {
    push(local_o.longValue());
  }
  
  public Long pop()
  {
    return Long.valueOf(popLong());
  }
  
  public Long top()
  {
    return Long.valueOf(topLong());
  }
  
  public Long peek(int local_i)
  {
    return Long.valueOf(peekLong(local_i));
  }
  
  public void push(long local_k)
  {
    push(Long.valueOf(local_k));
  }
  
  public long popLong()
  {
    return pop().longValue();
  }
  
  public long topLong()
  {
    return top().longValue();
  }
  
  public long peekLong(int local_i)
  {
    return peek(local_i).longValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */