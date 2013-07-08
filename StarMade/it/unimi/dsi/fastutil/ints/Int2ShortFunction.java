package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2ShortFunction
  extends Function<Integer, Short>
{
  public abstract short put(int paramInt, short paramShort);
  
  public abstract short get(int paramInt);
  
  public abstract short remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(short paramShort);
  
  public abstract short defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */