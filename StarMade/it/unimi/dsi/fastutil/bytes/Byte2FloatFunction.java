package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2FloatFunction
  extends Function<Byte, Float>
{
  public abstract float put(byte paramByte, float paramFloat);
  
  public abstract float get(byte paramByte);
  
  public abstract float remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(float paramFloat);
  
  public abstract float defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */