package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2DoubleFunction
  extends Function<Short, Double>
{
  public abstract double put(short paramShort, double paramDouble);
  
  public abstract double get(short paramShort);
  
  public abstract double remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */