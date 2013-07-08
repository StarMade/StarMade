package it.unimi.dsi.fastutil.shorts;

import java.util.Comparator;

public abstract interface ShortComparator
  extends Comparator<Short>
{
  public abstract int compare(short paramShort1, short paramShort2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */