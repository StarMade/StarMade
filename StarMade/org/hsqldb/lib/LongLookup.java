package org.hsqldb.lib;

import java.util.NoSuchElementException;

public abstract interface LongLookup
{
  public abstract int add(long paramLong1, long paramLong2);

  public abstract long lookup(long paramLong)
    throws NoSuchElementException;

  public abstract long lookup(long paramLong1, long paramLong2);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.LongLookup
 * JD-Core Version:    0.6.2
 */