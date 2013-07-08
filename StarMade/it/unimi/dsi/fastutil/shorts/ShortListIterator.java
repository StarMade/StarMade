package it.unimi.dsi.fastutil.shorts;

import java.util.ListIterator;

public abstract interface ShortListIterator
  extends ListIterator<Short>, ShortBidirectionalIterator
{
  public abstract void set(short paramShort);
  
  public abstract void add(short paramShort);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */