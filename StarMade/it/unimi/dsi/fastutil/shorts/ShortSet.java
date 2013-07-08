package it.unimi.dsi.fastutil.shorts;

import java.util.Set;

public abstract interface ShortSet
  extends ShortCollection, Set<Short>
{
  public abstract ShortIterator iterator();
  
  public abstract boolean remove(short paramShort);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */