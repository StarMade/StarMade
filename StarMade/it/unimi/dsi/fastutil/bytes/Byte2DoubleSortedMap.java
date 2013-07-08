package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2DoubleSortedMap
  extends Byte2DoubleMap, SortedMap<Byte, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Double>> entrySet();
  
  public abstract ObjectSortedSet<Byte2DoubleMap.Entry> byte2DoubleEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2DoubleSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2DoubleSortedMap headMap(Byte paramByte);
  
  public abstract Byte2DoubleSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2DoubleSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2DoubleSortedMap headMap(byte paramByte);
  
  public abstract Byte2DoubleSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2DoubleMap.Entry>, Byte2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2DoubleMap.Entry> fastIterator(Byte2DoubleMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */