package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2ByteFunction<K>
  extends Function<K, Byte>
{
  public abstract byte put(K paramK, byte paramByte);
  
  public abstract byte getByte(Object paramObject);
  
  public abstract byte removeByte(Object paramObject);
  
  public abstract void defaultReturnValue(byte paramByte);
  
  public abstract byte defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */