package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2LongFunction
  extends Function<Double, Long>
{
  public abstract long put(double paramDouble, long paramLong);
  
  public abstract long get(double paramDouble);
  
  public abstract long remove(double paramDouble);
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */