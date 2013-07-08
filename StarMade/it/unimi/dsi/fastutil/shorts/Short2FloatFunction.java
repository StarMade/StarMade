package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2FloatFunction
  extends Function<Short, Float>
{
  public abstract float put(short paramShort, float paramFloat);
  
  public abstract float get(short paramShort);
  
  public abstract float remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */