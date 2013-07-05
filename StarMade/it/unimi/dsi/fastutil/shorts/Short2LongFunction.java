package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2LongFunction extends Function<Short, Long>
{
  public abstract long put(short paramShort, long paramLong);

  public abstract long get(short paramShort);

  public abstract long remove(short paramShort);

  public abstract boolean containsKey(short paramShort);

  public abstract void defaultReturnValue(long paramLong);

  public abstract long defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2LongFunction
 * JD-Core Version:    0.6.2
 */