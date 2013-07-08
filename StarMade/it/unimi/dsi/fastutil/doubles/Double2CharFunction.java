package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2CharFunction
  extends Function<Double, Character>
{
  public abstract char put(double paramDouble, char paramChar);
  
  public abstract char get(double paramDouble);
  
  public abstract char remove(double paramDouble);
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */