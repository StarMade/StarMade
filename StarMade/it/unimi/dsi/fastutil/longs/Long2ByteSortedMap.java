package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2ByteSortedMap extends Long2ByteMap, SortedMap<Long, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Byte>> entrySet();

  public abstract ObjectSortedSet<Long2ByteMap.Entry> long2ByteEntrySet();

  public abstract LongSortedSet keySet();

  public abstract ByteCollection values();

  public abstract LongComparator comparator();

  public abstract Long2ByteSortedMap subMap(Long paramLong1, Long paramLong2);

  public abstract Long2ByteSortedMap headMap(Long paramLong);

  public abstract Long2ByteSortedMap tailMap(Long paramLong);

  public abstract Long2ByteSortedMap subMap(long paramLong1, long paramLong2);

  public abstract Long2ByteSortedMap headMap(long paramLong);

  public abstract Long2ByteSortedMap tailMap(long paramLong);

  public abstract long firstLongKey();

  public abstract long lastLongKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Long2ByteMap.Entry>, Long2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2ByteMap.Entry> fastIterator(Long2ByteMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ByteSortedMap
 * JD-Core Version:    0.6.2
 */