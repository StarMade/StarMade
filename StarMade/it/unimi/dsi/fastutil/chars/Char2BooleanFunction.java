package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2BooleanFunction
  extends Function<Character, Boolean>
{
  public abstract boolean put(char paramChar, boolean paramBoolean);
  
  public abstract boolean get(char paramChar);
  
  public abstract boolean remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */