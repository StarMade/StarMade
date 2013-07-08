package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2ByteFunction
  extends Function<Float, Byte>
{
  public abstract byte put(float paramFloat, byte paramByte);
  
  public abstract byte get(float paramFloat);
  
  public abstract byte remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(byte paramByte);
  
  public abstract byte defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */