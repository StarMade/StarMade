package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2ByteFunction extends Function<Short, Byte>
{
  public abstract byte put(short paramShort, byte paramByte);

  public abstract byte get(short paramShort);

  public abstract byte remove(short paramShort);

  public abstract boolean containsKey(short paramShort);

  public abstract void defaultReturnValue(byte paramByte);

  public abstract byte defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ByteFunction
 * JD-Core Version:    0.6.2
 */