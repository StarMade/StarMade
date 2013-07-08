package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2ShortFunction
  extends Function<Byte, Short>
{
  public abstract short put(byte paramByte, short paramShort);
  
  public abstract short get(byte paramByte);
  
  public abstract short remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(short paramShort);
  
  public abstract short defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */