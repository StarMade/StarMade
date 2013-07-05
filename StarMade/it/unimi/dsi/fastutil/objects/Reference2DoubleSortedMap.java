package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2DoubleSortedMap<K> extends Reference2DoubleMap<K>, SortedMap<K, Double>
{
  public abstract ObjectSortedSet<Map.Entry<K, Double>> entrySet();

  public abstract ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet();

  public abstract ReferenceSortedSet<K> keySet();

  public abstract DoubleCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Reference2DoubleSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Reference2DoubleSortedMap<K> headMap(K paramK);

  public abstract Reference2DoubleSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Reference2DoubleMap.Entry<K>>, Reference2DoubleMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2DoubleMap.Entry<K>> fastIterator(Reference2DoubleMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */