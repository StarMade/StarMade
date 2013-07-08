package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2ShortFunction
  extends Function<Float, Short>
{
  public abstract short put(float paramFloat, short paramShort);
  
  public abstract short get(float paramFloat);
  
  public abstract short remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(short paramShort);
  
  public abstract short defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */