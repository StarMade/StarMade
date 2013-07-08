package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2IntFunction
  extends Function<Byte, Integer>
{
  public abstract int put(byte paramByte, int paramInt);
  
  public abstract int get(byte paramByte);
  
  public abstract int remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */