package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2CharSortedMap
  extends Byte2CharMap, SortedMap<Byte, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Character>> entrySet();
  
  public abstract ObjectSortedSet<Byte2CharMap.Entry> byte2CharEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract CharCollection values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2CharSortedMap subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2CharSortedMap headMap(Byte paramByte);
  
  public abstract Byte2CharSortedMap tailMap(Byte paramByte);
  
  public abstract Byte2CharSortedMap subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2CharSortedMap headMap(byte paramByte);
  
  public abstract Byte2CharSortedMap tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Byte2CharMap.Entry>, Byte2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2CharMap.Entry> fastIterator(Byte2CharMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */