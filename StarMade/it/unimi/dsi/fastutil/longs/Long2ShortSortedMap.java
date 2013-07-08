package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2ShortSortedMap
  extends Long2ShortMap, SortedMap<Long, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Short>> entrySet();
  
  public abstract ObjectSortedSet<Long2ShortMap.Entry> long2ShortEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2ShortSortedMap subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2ShortSortedMap headMap(Long paramLong);
  
  public abstract Long2ShortSortedMap tailMap(Long paramLong);
  
  public abstract Long2ShortSortedMap subMap(long paramLong1, long paramLong2);
  
  public abstract Long2ShortSortedMap headMap(long paramLong);
  
  public abstract Long2ShortSortedMap tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Long2ShortMap.Entry>, Long2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2ShortMap.Entry> fastIterator(Long2ShortMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */