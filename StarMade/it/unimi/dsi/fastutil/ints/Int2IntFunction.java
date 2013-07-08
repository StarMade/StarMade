package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2IntFunction
  extends Function<Integer, Integer>
{
  public abstract int put(int paramInt1, int paramInt2);
  
  public abstract int get(int paramInt);
  
  public abstract int remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */