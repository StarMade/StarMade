package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2IntFunction
  extends Function<Short, Integer>
{
  public abstract int put(short paramShort, int paramInt);
  
  public abstract int get(short paramShort);
  
  public abstract int remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */