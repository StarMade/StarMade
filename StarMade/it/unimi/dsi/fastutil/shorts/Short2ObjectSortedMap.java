package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2ObjectSortedMap<V> extends Short2ObjectMap<V>, SortedMap<Short, V>
{
  public abstract ObjectSortedSet<Map.Entry<Short, V>> entrySet();

  public abstract ObjectSortedSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet();

  public abstract ShortSortedSet keySet();

  public abstract ObjectCollection<V> values();

  public abstract ShortComparator comparator();

  public abstract Short2ObjectSortedMap<V> subMap(Short paramShort1, Short paramShort2);

  public abstract Short2ObjectSortedMap<V> headMap(Short paramShort);

  public abstract Short2ObjectSortedMap<V> tailMap(Short paramShort);

  public abstract Short2ObjectSortedMap<V> subMap(short paramShort1, short paramShort2);

  public abstract Short2ObjectSortedMap<V> headMap(short paramShort);

  public abstract Short2ObjectSortedMap<V> tailMap(short paramShort);

  public abstract short firstShortKey();

  public abstract short lastShortKey();

  public static abstract interface FastSortedEntrySet<V> extends ObjectSortedSet<Short2ObjectMap.Entry<V>>, Short2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> fastIterator(Short2ObjectMap.Entry<V> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */