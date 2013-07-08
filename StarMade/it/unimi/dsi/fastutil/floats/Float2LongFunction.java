package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2LongFunction
  extends Function<Float, Long>
{
  public abstract long put(float paramFloat, long paramLong);
  
  public abstract long get(float paramFloat);
  
  public abstract long remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */