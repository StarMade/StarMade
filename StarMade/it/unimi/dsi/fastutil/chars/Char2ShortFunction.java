package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2ShortFunction extends Function<Character, Short>
{
  public abstract short put(char paramChar, short paramShort);

  public abstract short get(char paramChar);

  public abstract short remove(char paramChar);

  public abstract boolean containsKey(char paramChar);

  public abstract void defaultReturnValue(short paramShort);

  public abstract short defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ShortFunction
 * JD-Core Version:    0.6.2
 */