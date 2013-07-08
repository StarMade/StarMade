package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2LongFunction
  extends Function<Byte, Long>
{
  public abstract long put(byte paramByte, long paramLong);
  
  public abstract long get(byte paramByte);
  
  public abstract long remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(long paramLong);
  
  public abstract long defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */