package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;

public abstract interface Byte2DoubleFunction
  extends Function<Byte, Double>
{
  public abstract double put(byte paramByte, double paramDouble);
  
  public abstract double get(byte paramByte);
  
  public abstract double remove(byte paramByte);
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */