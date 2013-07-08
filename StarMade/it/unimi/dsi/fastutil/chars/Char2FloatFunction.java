package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2FloatFunction
  extends Function<Character, Float>
{
  public abstract float put(char paramChar, float paramFloat);
  
  public abstract float get(char paramChar);
  
  public abstract float remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */