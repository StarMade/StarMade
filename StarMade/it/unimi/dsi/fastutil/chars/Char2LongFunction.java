package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2LongFunction extends Function<Character, Long>
{
  public abstract long put(char paramChar, long paramLong);

  public abstract long get(char paramChar);

  public abstract long remove(char paramChar);

  public abstract boolean containsKey(char paramChar);

  public abstract void defaultReturnValue(long paramLong);

  public abstract long defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongFunction
 * JD-Core Version:    0.6.2
 */