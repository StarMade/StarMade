package org.hsqldb.lib;

import java.util.NoSuchElementException;

public abstract interface LongLookup
{
  public abstract int add(long paramLong1, long paramLong2);
  
  public abstract long lookup(long paramLong)
    throws NoSuchElementException;
  
  public abstract long lookup(long paramLong1, long paramLong2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.LongLookup
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */