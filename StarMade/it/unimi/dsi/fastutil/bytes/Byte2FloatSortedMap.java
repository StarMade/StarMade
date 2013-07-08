package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2FloatSortedMap
  extends Byte2FloatMap, SortedMap<Byte, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Float>> entrySet();
  
  public abstract ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2FloatSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2FloatSortedMap headMap(Byte paramByte);
  
  public abstract Byte2FloatSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2FloatSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2FloatSortedMap headMap(byte paramByte);
  
  public abstract Byte2FloatSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2FloatMap.Entry>, Byte2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2FloatMap.Entry> fastIterator(Byte2FloatMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */