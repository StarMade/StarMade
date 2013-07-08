package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2ByteFunction
  extends Function<Double, Byte>
{
  public abstract byte put(double paramDouble, byte paramByte);
  
  public abstract byte get(double paramDouble);
  
  public abstract byte remove(double paramDouble);
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract void defaultReturnValue(byte paramByte);
  
  public abstract byte defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */