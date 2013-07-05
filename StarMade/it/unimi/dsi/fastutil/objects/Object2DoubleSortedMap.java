package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2DoubleSortedMap<K> extends Object2DoubleMap<K>, SortedMap<K, Double>
{
  public abstract ObjectSortedSet<Map.Entry<K, Double>> entrySet();

  public abstract ObjectSortedSet<Object2DoubleMap.Entry<K>> object2DoubleEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract DoubleCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2DoubleSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Object2DoubleSortedMap<K> headMap(K paramK);

  public abstract Object2DoubleSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Object2DoubleMap.Entry<K>>, Object2DoubleMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2DoubleMap.Entry<K>> fastIterator(Object2DoubleMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */