package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2ShortFunction
  extends Function<Long, Short>
{
  public abstract short put(long paramLong, short paramShort);
  
  public abstract short get(long paramLong);
  
  public abstract short remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(short paramShort);
  
  public abstract short defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */