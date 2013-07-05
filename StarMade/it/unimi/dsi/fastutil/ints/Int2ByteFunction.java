package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2ByteFunction extends Function<Integer, Byte>
{
  public abstract byte put(int paramInt, byte paramByte);

  public abstract byte get(int paramInt);

  public abstract byte remove(int paramInt);

  public abstract boolean containsKey(int paramInt);

  public abstract void defaultReturnValue(byte paramByte);

  public abstract byte defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ByteFunction
 * JD-Core Version:    0.6.2
 */