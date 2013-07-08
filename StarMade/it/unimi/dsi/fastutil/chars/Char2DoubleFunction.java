package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2DoubleFunction
  extends Function<Character, Double>
{
  public abstract double put(char paramChar, double paramDouble);
  
  public abstract double get(char paramChar);
  
  public abstract double remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */