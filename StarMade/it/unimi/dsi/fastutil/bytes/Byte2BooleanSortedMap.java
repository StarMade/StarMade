package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2BooleanSortedMap extends Byte2BooleanMap, SortedMap<Byte, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, Boolean>> entrySet();

  public abstract ObjectSortedSet<Byte2BooleanMap.Entry> byte2BooleanEntrySet();

  public abstract ByteSortedSet keySet();

  public abstract BooleanCollection values();

  public abstract ByteComparator comparator();

  public abstract Byte2BooleanSortedMap subMap(Byte paramByte1, Byte paramByte2);

  public abstract Byte2BooleanSortedMap headMap(Byte paramByte);

  public abstract Byte2BooleanSortedMap tailMap(Byte paramByte);

  public abstract Byte2BooleanSortedMap subMap(byte paramByte1, byte paramByte2);

  public abstract Byte2BooleanSortedMap headMap(byte paramByte);

  public abstract Byte2BooleanSortedMap tailMap(byte paramByte);

  public abstract byte firstByteKey();

  public abstract byte lastByteKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Byte2BooleanMap.Entry>, Byte2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Byte2BooleanMap.Entry> fastIterator(Byte2BooleanMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */