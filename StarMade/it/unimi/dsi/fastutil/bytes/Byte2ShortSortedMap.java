package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2ShortSortedMap extends Byte2ShortMap, SortedMap<Byte, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Short>> entrySet();

  public abstract ObjectSortedSet<Byte2ShortMap.Entry> byte2ShortEntrySet();

  public abstract ByteSortedSet keySet();

  public abstract ShortCollection values();

  public abstract ByteComparator comparator();

  public abstract Byte2ShortSortedMap subMap(Byte paramByte1, Byte paramByte2);

  public abstract Byte2ShortSortedMap headMap(Byte paramByte);

  public abstract Byte2ShortSortedMap tailMap(Byte paramByte);

  public abstract Byte2ShortSortedMap subMap(byte paramByte1, byte paramByte2);

  public abstract Byte2ShortSortedMap headMap(byte paramByte);

  public abstract Byte2ShortSortedMap tailMap(byte paramByte);

  public abstract byte firstByteKey();

  public abstract byte lastByteKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Byte2ShortMap.Entry>, Byte2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2ShortMap.Entry> fastIterator(Byte2ShortMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortSortedMap
 * JD-Core Version:    0.6.2
 */