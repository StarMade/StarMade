package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2LongSortedMap
  extends Int2LongMap, SortedMap<Integer, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Long>> entrySet();
  
  public abstract ObjectSortedSet<Int2LongMap.Entry> int2LongEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract LongCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2LongSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2LongSortedMap headMap(Integer paramInteger);
  
  public abstract Int2LongSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2LongSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2LongSortedMap headMap(int paramInt);
  
  public abstract Int2LongSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2LongMap.Entry>, Int2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2LongMap.Entry> fastIterator(Int2LongMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */