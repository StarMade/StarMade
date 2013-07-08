package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface ShortBigListIterator
  extends ShortBidirectionalIterator, BigListIterator<Short>
{
  public abstract void set(short paramShort);
  
  public abstract void add(short paramShort);
  
  public abstract void set(Short paramShort);
  
  public abstract void add(Short paramShort);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */