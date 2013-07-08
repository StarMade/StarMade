package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2ByteSortedMap
  extends Int2ByteMap, SortedMap<Integer, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Int2ByteMap.Entry> int2ByteEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2ByteSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2ByteSortedMap headMap(Integer paramInteger);
  
  public abstract Int2ByteSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2ByteSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2ByteSortedMap headMap(int paramInt);
  
  public abstract Int2ByteSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2ByteMap.Entry>, Int2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2ByteMap.Entry> fastIterator(Int2ByteMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */