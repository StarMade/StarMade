package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2LongSortedMap
  extends Long2LongMap, SortedMap<Long, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Long>> entrySet();
  
  public abstract ObjectSortedSet<Long2LongMap.Entry> long2LongEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract LongCollection values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2LongSortedMap subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2LongSortedMap headMap(Long paramLong);
  
  public abstract Long2LongSortedMap tailMap(Long paramLong);
  
  public abstract Long2LongSortedMap subMap(long paramLong1, long paramLong2);
  
  public abstract Long2LongSortedMap headMap(long paramLong);
  
  public abstract Long2LongSortedMap tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Long2LongMap.Entry>, Long2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2LongMap.Entry> fastIterator(Long2LongMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */