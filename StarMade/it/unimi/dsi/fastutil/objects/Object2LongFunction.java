package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Object2LongFunction<K>
  extends Function<K, Long>
{
  public abstract long put(K paramK, long paramLong);
  
  public abstract long getLong(Object paramObject);
  
  public abstract long removeLong(Object paramObject);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */