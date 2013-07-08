package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2CharFunction
  extends Function<Byte, Character>
{
  public abstract char put(byte paramByte, char paramChar);
  
  public abstract char get(byte paramByte);
  
  public abstract char remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */