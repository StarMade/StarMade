package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2FloatFunction
  extends Function<Float, Float>
{
  public abstract float put(float paramFloat1, float paramFloat2);
  
  public abstract float get(float paramFloat);
  
  public abstract float remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */