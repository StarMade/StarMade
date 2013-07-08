package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2DoubleFunction
  extends Function<Float, Double>
{
  public abstract double put(float paramFloat, double paramDouble);
  
  public abstract double get(float paramFloat);
  
  public abstract double remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */