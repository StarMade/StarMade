package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2BooleanFunction
  extends Function<Long, Boolean>
{
  public abstract boolean put(long paramLong, boolean paramBoolean);
  
  public abstract boolean get(long paramLong);
  
  public abstract boolean remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */