package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2FloatSortedMap extends Long2FloatMap, SortedMap<Long, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Float>> entrySet();

  public abstract ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet();

  public abstract LongSortedSet keySet();

  public abstract FloatCollection values();

  public abstract LongComparator comparator();

  public abstract Long2FloatSortedMap subMap(Long paramLong1, Long paramLong2);

  public abstract Long2FloatSortedMap headMap(Long paramLong);

  public abstract Long2FloatSortedMap tailMap(Long paramLong);

  public abstract Long2FloatSortedMap subMap(long paramLong1, long paramLong2);

  public abstract Long2FloatSortedMap headMap(long paramLong);

  public abstract Long2FloatSortedMap tailMap(long paramLong);

  public abstract long firstLongKey();

  public abstract long lastLongKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Long2FloatMap.Entry>, Long2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2FloatMap.Entry> fastIterator(Long2FloatMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2FloatSortedMap
 * JD-Core Version:    0.6.2
 */