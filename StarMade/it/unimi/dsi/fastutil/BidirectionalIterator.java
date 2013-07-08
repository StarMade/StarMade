package it.unimi.dsi.fastutil;

import java.util.Iterator;

public abstract interface BidirectionalIterator<K>
  extends Iterator<K>
{
  public abstract K previous();
  
  public abstract boolean hasPrevious();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.BidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */