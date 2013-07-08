package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2DoubleSortedMap
  extends Long2DoubleMap, SortedMap<Long, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Double>> entrySet();
  
  public abstract ObjectSortedSet<Long2DoubleMap.Entry> long2DoubleEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2DoubleSortedMap subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2DoubleSortedMap headMap(Long paramLong);
  
  public abstract Long2DoubleSortedMap tailMap(Long paramLong);
  
  public abstract Long2DoubleSortedMap subMap(long paramLong1, long paramLong2);
  
  public abstract Long2DoubleSortedMap headMap(long paramLong);
  
  public abstract Long2DoubleSortedMap tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Long2DoubleMap.Entry>, Long2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2DoubleMap.Entry> fastIterator(Long2DoubleMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */