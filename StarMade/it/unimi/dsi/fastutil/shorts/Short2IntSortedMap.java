package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2IntSortedMap extends Short2IntMap, SortedMap<Short, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Integer>> entrySet();

  public abstract ObjectSortedSet<Short2IntMap.Entry> short2IntEntrySet();

  public abstract ShortSortedSet keySet();

  public abstract IntCollection values();

  public abstract ShortComparator comparator();

  public abstract Short2IntSortedMap subMap(Short paramShort1, Short paramShort2);

  public abstract Short2IntSortedMap headMap(Short paramShort);

  public abstract Short2IntSortedMap tailMap(Short paramShort);

  public abstract Short2IntSortedMap subMap(short paramShort1, short paramShort2);

  public abstract Short2IntSortedMap headMap(short paramShort);

  public abstract Short2IntSortedMap tailMap(short paramShort);

  public abstract short firstShortKey();

  public abstract short lastShortKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Short2IntMap.Entry>, Short2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2IntMap.Entry> fastIterator(Short2IntMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2IntSortedMap
 * JD-Core Version:    0.6.2
 */