package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;

public abstract interface Long2ByteFunction extends Function<Long, Byte>
{
  public abstract byte put(long paramLong, byte paramByte);

  public abstract byte get(long paramLong);

  public abstract byte remove(long paramLong);

  public abstract boolean containsKey(long paramLong);

  public abstract void defaultReturnValue(byte paramByte);

  public abstract byte defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ByteFunction
 * JD-Core Version:    0.6.2
 */