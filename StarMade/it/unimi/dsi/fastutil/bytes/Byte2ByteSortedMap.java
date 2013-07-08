package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2ByteSortedMap
  extends Byte2ByteMap, SortedMap<Byte, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Byte2ByteMap.Entry> byte2ByteEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2ByteSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2ByteSortedMap headMap(Byte paramByte);
  
  public abstract Byte2ByteSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2ByteSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2ByteSortedMap headMap(byte paramByte);
  
  public abstract Byte2ByteSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2ByteMap.Entry>, Byte2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2ByteMap.Entry> fastIterator(Byte2ByteMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */