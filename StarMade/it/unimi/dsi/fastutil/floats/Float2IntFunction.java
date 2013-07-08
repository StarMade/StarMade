package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2IntFunction
  extends Function<Float, Integer>
{
  public abstract int put(float paramFloat, int paramInt);
  
  public abstract int get(float paramFloat);
  
  public abstract int remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */