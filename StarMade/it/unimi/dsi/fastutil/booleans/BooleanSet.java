package it.unimi.dsi.fastutil.booleans;

import java.util.Set;

public abstract interface BooleanSet
  extends BooleanCollection, Set<Boolean>
{
  public abstract BooleanIterator iterator();
  
  public abstract boolean remove(boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */