package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface BooleanBigListIterator
  extends BooleanBidirectionalIterator, BigListIterator<Boolean>
{
  public abstract void set(boolean paramBoolean);
  
  public abstract void add(boolean paramBoolean);
  
  public abstract void set(Boolean paramBoolean);
  
  public abstract void add(Boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */