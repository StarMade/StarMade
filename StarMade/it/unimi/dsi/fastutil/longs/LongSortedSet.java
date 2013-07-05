package it.unimi.dsi.fastutil.longs;

import java.util.SortedSet;

public abstract interface LongSortedSet extends LongSet, SortedSet<Long>
{
  public abstract LongBidirectionalIterator iterator(long paramLong);

  @Deprecated
  public abstract LongBidirectionalIterator longIterator();

  public abstract LongBidirectionalIterator iterator();

  public abstract LongSortedSet subSet(Long paramLong1, Long paramLong2);

  public abstract LongSortedSet headSet(Long paramLong);

  public abstract LongSortedSet tailSet(Long paramLong);

  public abstract LongComparator comparator();

  public abstract LongSortedSet subSet(long paramLong1, long paramLong2);

  public abstract LongSortedSet headSet(long paramLong);

  public abstract LongSortedSet tailSet(long paramLong);

  public abstract long firstLong();

  public abstract long lastLong();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSortedSet
 * JD-Core Version:    0.6.2
 */