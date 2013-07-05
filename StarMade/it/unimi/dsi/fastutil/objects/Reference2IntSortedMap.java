package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2IntSortedMap<K> extends Reference2IntMap<K>, SortedMap<K, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<K, Integer>> entrySet();

  public abstract ObjectSortedSet<Reference2IntMap.Entry<K>> reference2IntEntrySet();

  public abstract ReferenceSortedSet<K> keySet();

  public abstract IntCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Reference2IntSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Reference2IntSortedMap<K> headMap(K paramK);

  public abstract Reference2IntSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Reference2IntMap.Entry<K>>, Reference2IntMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2IntMap.Entry<K>> fastIterator(Reference2IntMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2IntSortedMap
 * JD-Core Version:    0.6.2
 */