package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2ShortSortedMap
  extends Int2ShortMap, SortedMap<Integer, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Short>> entrySet();
  
  public abstract ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2ShortSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2ShortSortedMap headMap(Integer paramInteger);
  
  public abstract Int2ShortSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2ShortSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2ShortSortedMap headMap(int paramInt);
  
  public abstract Int2ShortSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2ShortMap.Entry>, Int2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2ShortMap.Entry> fastIterator(Int2ShortMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */