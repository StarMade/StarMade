package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2LongSortedMap
  extends Byte2LongMap, SortedMap<Byte, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Long>> entrySet();
  
  public abstract ObjectSortedSet<Byte2LongMap.Entry> byte2LongEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract LongCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2LongSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2LongSortedMap headMap(Byte paramByte);
  
  public abstract Byte2LongSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2LongSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2LongSortedMap headMap(byte paramByte);
  
  public abstract Byte2LongSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2LongMap.Entry>, Byte2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2LongMap.Entry> fastIterator(Byte2LongMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */