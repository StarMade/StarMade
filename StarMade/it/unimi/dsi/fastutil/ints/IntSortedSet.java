package it.unimi.dsi.fastutil.ints;

import java.util.SortedSet;

public abstract interface IntSortedSet
  extends IntSet, SortedSet<Integer>
{
  public abstract IntBidirectionalIterator iterator(int paramInt);
  
  @Deprecated
  public abstract IntBidirectionalIterator intIterator();
  
  public abstract IntBidirectionalIterator iterator();
  
  public abstract IntSortedSet subSet(Integer paramInteger1, Integer paramInteger2);
  
  public abstract IntSortedSet headSet(Integer paramInteger);
  
  public abstract IntSortedSet tailSet(Integer paramInteger);
  
  public abstract IntComparator comparator();
  
  public abstract IntSortedSet subSet(int paramInt1, int paramInt2);
  
  public abstract IntSortedSet headSet(int paramInt);
  
  public abstract IntSortedSet tailSet(int paramInt);
  
  public abstract int firstInt();
  
  public abstract int lastInt();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */