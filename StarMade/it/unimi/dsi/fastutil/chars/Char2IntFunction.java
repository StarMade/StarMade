package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2IntFunction
  extends Function<Character, Integer>
{
  public abstract int put(char paramChar, int paramInt);
  
  public abstract int get(char paramChar);
  
  public abstract int remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */