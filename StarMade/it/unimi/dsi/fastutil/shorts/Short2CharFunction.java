package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2CharFunction
  extends Function<Short, Character>
{
  public abstract char put(short paramShort, char paramChar);
  
  public abstract char get(short paramShort);
  
  public abstract char remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */