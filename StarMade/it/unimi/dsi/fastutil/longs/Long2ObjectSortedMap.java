package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2ObjectSortedMap<V>
  extends Long2ObjectMap<V>, SortedMap<Long, V>
{
  public abstract ObjectSortedSet<Map.Entry<Long, V>> entrySet();
  
  public abstract ObjectSortedSet<Long2ObjectMap.Entry<V>> long2ObjectEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2ObjectSortedMap<V> subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2ObjectSortedMap<V> headMap(Long paramLong);
  
  public abstract Long2ObjectSortedMap<V> tailMap(Long paramLong);
  
  public abstract Long2ObjectSortedMap<V> subMap(long paramLong1, long paramLong2);
  
  public abstract Long2ObjectSortedMap<V> headMap(long paramLong);
  
  public abstract Long2ObjectSortedMap<V> tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Long2ObjectMap.Entry<V>>, Long2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Long2ObjectMap.Entry<V>> fastIterator(Long2ObjectMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */