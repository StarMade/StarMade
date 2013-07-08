package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2IntFunction
  extends Function<Long, Integer>
{
  public abstract int put(long paramLong, int paramInt);
  
  public abstract int get(long paramLong);
  
  public abstract int remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */