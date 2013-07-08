package it.unimi.dsi.fastutil.booleans;

import java.util.Comparator;

public abstract interface BooleanComparator
  extends Comparator<Boolean>
{
  public abstract int compare(boolean paramBoolean1, boolean paramBoolean2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */