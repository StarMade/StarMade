package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2CharFunction
  extends Function<Integer, Character>
{
  public abstract char put(int paramInt, char paramChar);
  
  public abstract char get(int paramInt);
  
  public abstract char remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */