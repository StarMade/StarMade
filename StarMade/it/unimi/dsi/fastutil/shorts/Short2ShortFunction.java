package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2ShortFunction
  extends Function<Short, Short>
{
  public abstract short put(short paramShort1, short paramShort2);
  
  public abstract short get(short paramShort);
  
  public abstract short remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(short paramShort);
  
  public abstract short defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */