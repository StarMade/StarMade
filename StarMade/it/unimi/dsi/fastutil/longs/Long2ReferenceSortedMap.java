package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2ReferenceSortedMap<V>
  extends Long2ReferenceMap<V>, SortedMap<Long, V>
{
  public abstract ObjectSortedSet<Map.Entry<Long, V>> entrySet();
  
  public abstract ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2ReferenceSortedMap<V> subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2ReferenceSortedMap<V> headMap(Long paramLong);
  
  public abstract Long2ReferenceSortedMap<V> tailMap(Long paramLong);
  
  public abstract Long2ReferenceSortedMap<V> subMap(long paramLong1, long paramLong2);
  
  public abstract Long2ReferenceSortedMap<V> headMap(long paramLong);
  
  public abstract Long2ReferenceSortedMap<V> tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Long2ReferenceMap.Entry<V>>, Long2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> fastIterator(Long2ReferenceMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */