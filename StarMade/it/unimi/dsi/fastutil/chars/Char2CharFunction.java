package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2CharFunction
  extends Function<Character, Character>
{
  public abstract char put(char paramChar1, char paramChar2);
  
  public abstract char get(char paramChar);
  
  public abstract char remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */