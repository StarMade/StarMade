package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2BooleanSortedMap<K> extends Object2BooleanMap<K>, SortedMap<K, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<K, Boolean>> entrySet();

  public abstract ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet();

  public abstract ObjectSortedSet<K> keySet();

  public abstract BooleanCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Object2BooleanSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Object2BooleanSortedMap<K> headMap(K paramK);

  public abstract Object2BooleanSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Object2BooleanMap.Entry<K>>, Object2BooleanMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> fastIterator(Object2BooleanMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */