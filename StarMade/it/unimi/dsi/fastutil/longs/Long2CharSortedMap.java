package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Long2CharSortedMap
  extends Long2CharMap, SortedMap<Long, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Long, Character>> entrySet();
  
  public abstract ObjectSortedSet<Long2CharMap.Entry> long2CharEntrySet();
  
  public abstract LongSortedSet keySet();
  
  public abstract CharCollection values();
  
  public abstract LongComparator comparator();
  
  public abstract Long2CharSortedMap subMap(Long paramLong1, Long paramLong2);
  
  public abstract Long2CharSortedMap headMap(Long paramLong);
  
  public abstract Long2CharSortedMap tailMap(Long paramLong);
  
  public abstract Long2CharSortedMap subMap(long paramLong1, long paramLong2);
  
  public abstract Long2CharSortedMap headMap(long paramLong);
  
  public abstract Long2CharSortedMap tailMap(long paramLong);
  
  public abstract long firstLongKey();
  
  public abstract long lastLongKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Long2CharMap.Entry>, Long2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Long2CharMap.Entry> fastIterator(Long2CharMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */