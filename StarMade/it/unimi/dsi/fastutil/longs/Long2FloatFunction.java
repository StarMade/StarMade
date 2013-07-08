package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2FloatFunction
  extends Function<Long, Float>
{
  public abstract float put(long paramLong, float paramFloat);
  
  public abstract float get(long paramLong);
  
  public abstract float remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */