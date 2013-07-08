package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2ObjectFunction<V>
  extends Function<Long, V>
{
  public abstract V put(long paramLong, V paramV);
  
  public abstract V get(long paramLong);
  
  public abstract V remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(V paramV);
  
  public abstract V defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ObjectFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */