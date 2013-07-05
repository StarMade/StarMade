package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2LongFunction<K> extends Function<K, Long>
{
  public abstract long put(K paramK, long paramLong);

  public abstract long getLong(Object paramObject);

  public abstract long removeLong(Object paramObject);

  public abstract void defaultReturnValue(long paramLong);

  public abstract long defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2LongFunction
 * JD-Core Version:    0.6.2
 */