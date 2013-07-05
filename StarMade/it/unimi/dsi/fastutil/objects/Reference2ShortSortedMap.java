package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2ShortSortedMap<K> extends Reference2ShortMap<K>, SortedMap<K, Short>
{
  public abstract ObjectSortedSet<Map.Entry<K, Short>> entrySet();

  public abstract ObjectSortedSet<Reference2ShortMap.Entry<K>> reference2ShortEntrySet();

  public abstract ReferenceSortedSet<K> keySet();

  public abstract ShortCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Reference2ShortSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Reference2ShortSortedMap<K> headMap(K paramK);

  public abstract Reference2ShortSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Reference2ShortMap.Entry<K>>, Reference2ShortMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2ShortMap.Entry<K>> fastIterator(Reference2ShortMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ShortSortedMap
 * JD-Core Version:    0.6.2
 */