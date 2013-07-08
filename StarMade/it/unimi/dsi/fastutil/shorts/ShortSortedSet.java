package it.unimi.dsi.fastutil.shorts;

import java.util.SortedSet;

public abstract interface ShortSortedSet
  extends ShortSet, SortedSet<Short>
{
  public abstract ShortBidirectionalIterator iterator(short paramShort);
  
  @Deprecated
  public abstract ShortBidirectionalIterator shortIterator();
  
  public abstract ShortBidirectionalIterator iterator();
  
  public abstract ShortSortedSet subSet(Short paramShort1, Short paramShort2);
  
  public abstract ShortSortedSet headSet(Short paramShort);
  
  public abstract ShortSortedSet tailSet(Short paramShort);
  
  public abstract ShortComparator comparator();
  
  public abstract ShortSortedSet subSet(short paramShort1, short paramShort2);
  
  public abstract ShortSortedSet headSet(short paramShort);
  
  public abstract ShortSortedSet tailSet(short paramShort);
  
  public abstract short firstShort();
  
  public abstract short lastShort();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */