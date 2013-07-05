package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.CharCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2CharSortedMap<K> extends Object2CharMap<K>, SortedMap<K, Character>
{
  public abstract ObjectSortedSet<Map.Entry<K, Character>> entrySet();

  public abstract ObjectSortedSet<Object2CharMap.Entry<K>> object2CharEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract CharCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2CharSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Object2CharSortedMap<K> headMap(K paramK);

  public abstract Object2CharSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Object2CharMap.Entry<K>>, Object2CharMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2CharMap.Entry<K>> fastIterator(Object2CharMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharSortedMap
 * JD-Core Version:    0.6.2
 */