package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2BooleanSortedMap extends Long2BooleanMap, SortedMap<Long, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet();

  public abstract ObjectSortedSet<Long2BooleanMap.Entry> long2BooleanEntrySet();

  public abstract LongSortedSet keySet();

  public abstract BooleanCollection values();

  public abstract LongComparator comparator();

  public abstract Long2BooleanSortedMap subMap(Long paramLong1, Long paramLong2);

  public abstract Long2BooleanSortedMap headMap(Long paramLong);

  public abstract Long2BooleanSortedMap tailMap(Long paramLong);

  public abstract Long2BooleanSortedMap subMap(long paramLong1, long paramLong2);

  public abstract Long2BooleanSortedMap headMap(long paramLong);

  public abstract Long2BooleanSortedMap tailMap(long paramLong);

  public abstract long firstLongKey();

  public abstract long lastLongKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Long2BooleanMap.Entry>, Long2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2BooleanMap.Entry> fastIterator(Long2BooleanMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */