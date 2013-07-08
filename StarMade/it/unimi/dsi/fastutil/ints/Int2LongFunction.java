package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2LongFunction
  extends Function<Integer, Long>
{
  public abstract long put(int paramInt, long paramLong);
  
  public abstract long get(int paramInt);
  
  public abstract long remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */