package it.unimi.dsi.fastutil.booleans;

import java.util.Set;

public abstract interface BooleanSet extends BooleanCollection, Set<Boolean>
{
  public abstract BooleanIterator iterator();

  public abstract boolean remove(boolean paramBoolean);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanSet
 * JD-Core Version:    0.6.2
 */