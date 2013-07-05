package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2BooleanSortedMap<K> extends Reference2BooleanMap<K>, SortedMap<K, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<K, Boolean>> entrySet();

  public abstract ObjectSortedSet<Reference2BooleanMap.Entry<K>> reference2BooleanEntrySet();

  public abstract ReferenceSortedSet<K> keySet();

  public abstract BooleanCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Reference2BooleanSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Reference2BooleanSortedMap<K> headMap(K paramK);

  public abstract Reference2BooleanSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Reference2BooleanMap.Entry<K>>, Reference2BooleanMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2BooleanMap.Entry<K>> fastIterator(Reference2BooleanMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */