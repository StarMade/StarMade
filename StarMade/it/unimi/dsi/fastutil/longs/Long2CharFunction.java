package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2CharFunction
  extends Function<Long, Character>
{
  public abstract char put(long paramLong, char paramChar);
  
  public abstract char get(long paramLong);
  
  public abstract char remove(long paramLong);
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */