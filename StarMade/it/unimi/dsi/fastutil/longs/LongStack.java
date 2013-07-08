package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Stack;

public abstract interface LongStack
  extends Stack<Long>
{
  public abstract void push(long paramLong);
  
  public abstract long popLong();
  
  public abstract long topLong();
  
  public abstract long peekLong(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */