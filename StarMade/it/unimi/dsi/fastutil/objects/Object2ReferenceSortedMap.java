package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2ReferenceSortedMap<K, V> extends Object2ReferenceMap<K, V>, SortedMap<K, V>
{
  public abstract ObjectSortedSet<Map.Entry<K, V>> entrySet();

  public abstract ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract ReferenceCollection<V> values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2ReferenceSortedMap<K, V> subMap(K paramK1, K paramK2);

  public abstract Object2ReferenceSortedMap<K, V> headMap(K paramK);

  public abstract Object2ReferenceSortedMap<K, V> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K, V> extends ObjectSortedSet<Object2ReferenceMap.Entry<K, V>>, Object2ReferenceMap.FastEntrySet<K, V>
  {
    public abstract ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> fastIterator(Object2ReferenceMap.Entry<K, V> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */