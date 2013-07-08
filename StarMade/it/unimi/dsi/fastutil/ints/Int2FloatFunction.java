package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2FloatFunction
  extends Function<Integer, Float>
{
  public abstract float put(int paramInt, float paramFloat);
  
  public abstract float get(int paramInt);
  
  public abstract float remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */