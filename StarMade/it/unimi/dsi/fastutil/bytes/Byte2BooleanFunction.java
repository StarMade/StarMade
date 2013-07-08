package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2BooleanFunction
  extends Function<Byte, Boolean>
{
  public abstract boolean put(byte paramByte, boolean paramBoolean);
  
  public abstract boolean get(byte paramByte);
  
  public abstract boolean remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */