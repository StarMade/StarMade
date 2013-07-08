package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2DoubleFunction
  extends Function<Long, Double>
{
  public abstract double put(long paramLong, double paramDouble);
  
  public abstract double get(long paramLong);
  
  public abstract double remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */