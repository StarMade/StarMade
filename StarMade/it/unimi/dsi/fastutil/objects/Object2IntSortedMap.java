package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2IntSortedMap<K> extends Object2IntMap<K>, SortedMap<K, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<K, Integer>> entrySet();

  public abstract ObjectSortedSet<Object2IntMap.Entry<K>> object2IntEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract IntCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2IntSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Object2IntSortedMap<K> headMap(K paramK);

  public abstract Object2IntSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Object2IntMap.Entry<K>>, Object2IntMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2IntMap.Entry<K>> fastIterator(Object2IntMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2IntSortedMap
 * JD-Core Version:    0.6.2
 */