package it.unimi.dsi.fastutil.longs;

import java.util.Iterator;

public abstract interface LongIterator
  extends Iterator<Long>
{
  public abstract long nextLong();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */