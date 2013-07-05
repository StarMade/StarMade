package it.unimi.dsi.fastutil.longs;

import java.util.Collection;

public abstract interface LongCollection extends Collection<Long>, LongIterable
{
  public abstract LongIterator iterator();

  @Deprecated
  public abstract LongIterator longIterator();

  public abstract <T> T[] toArray(T[] paramArrayOfT);

  public abstract boolean contains(long paramLong);

  public abstract long[] toLongArray();

  public abstract long[] toLongArray(long[] paramArrayOfLong);

  public abstract long[] toArray(long[] paramArrayOfLong);

  public abstract boolean add(long paramLong);

  public abstract boolean rem(long paramLong);

  public abstract boolean addAll(LongCollection paramLongCollection);

  public abstract boolean containsAll(LongCollection paramLongCollection);

  public abstract boolean removeAll(LongCollection paramLongCollection);

  public abstract boolean retainAll(LongCollection paramLongCollection);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongCollection
 * JD-Core Version:    0.6.2
 */