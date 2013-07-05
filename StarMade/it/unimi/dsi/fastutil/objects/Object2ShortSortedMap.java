package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2ShortSortedMap<K> extends Object2ShortMap<K>, SortedMap<K, Short>
{
  public abstract ObjectSortedSet<Map.Entry<K, Short>> entrySet();

  public abstract ObjectSortedSet<Object2ShortMap.Entry<K>> object2ShortEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract ShortCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2ShortSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Object2ShortSortedMap<K> headMap(K paramK);

  public abstract Object2ShortSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Object2ShortMap.Entry<K>>, Object2ShortMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2ShortMap.Entry<K>> fastIterator(Object2ShortMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ShortSortedMap
 * JD-Core Version:    0.6.2
 */