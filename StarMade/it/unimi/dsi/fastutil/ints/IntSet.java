package it.unimi.dsi.fastutil.ints;

import java.util.Set;

public abstract interface IntSet
  extends IntCollection, Set<Integer>
{
  public abstract IntIterator iterator();
  
  public abstract boolean remove(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */