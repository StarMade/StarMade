package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2IntSortedMap
  extends Int2IntMap, SortedMap<Integer, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Integer>> entrySet();
  
  public abstract ObjectSortedSet<Int2IntMap.Entry> int2IntEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract IntCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2IntSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2IntSortedMap headMap(Integer paramInteger);
  
  public abstract Int2IntSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2IntSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2IntSortedMap headMap(int paramInt);
  
  public abstract Int2IntSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2IntMap.Entry>, Int2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2IntMap.Entry> fastIterator(Int2IntMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */