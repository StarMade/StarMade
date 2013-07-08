package it.unimi.dsi.fastutil.ints;

import java.util.Iterator;

public abstract interface IntIterator
  extends Iterator<Integer>
{
  public abstract int nextInt();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */