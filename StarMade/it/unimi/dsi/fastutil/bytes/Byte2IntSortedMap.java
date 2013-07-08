package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2IntSortedMap
  extends Byte2IntMap, SortedMap<Byte, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Integer>> entrySet();
  
  public abstract ObjectSortedSet<Byte2IntMap.Entry> byte2IntEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract IntCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2IntSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2IntSortedMap headMap(Byte paramByte);
  
  public abstract Byte2IntSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2IntSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2IntSortedMap headMap(byte paramByte);
  
  public abstract Byte2IntSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2IntMap.Entry>, Byte2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2IntMap.Entry> fastIterator(Byte2IntMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */