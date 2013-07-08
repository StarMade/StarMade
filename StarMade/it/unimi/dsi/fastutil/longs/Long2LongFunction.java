package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2LongFunction
  extends Function<Long, Long>
{
  public abstract long put(long paramLong1, long paramLong2);
  
  public abstract long get(long paramLong);
  
  public abstract long remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */