package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2ReferenceSortedMap<V> extends Int2ReferenceMap<V>, SortedMap<Integer, V>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, V>> entrySet();

  public abstract ObjectSortedSet<Int2ReferenceMap.Entry<V>> int2ReferenceEntrySet();

  public abstract IntSortedSet keySet();

  public abstract ReferenceCollection<V> values();

  public abstract IntComparator comparator();

  public abstract Int2ReferenceSortedMap<V> subMap(Integer paramInteger1, Integer paramInteger2);

  public abstract Int2ReferenceSortedMap<V> headMap(Integer paramInteger);

  public abstract Int2ReferenceSortedMap<V> tailMap(Integer paramInteger);

  public abstract Int2ReferenceSortedMap<V> subMap(int paramInt1, int paramInt2);

  public abstract Int2ReferenceSortedMap<V> headMap(int paramInt);

  public abstract Int2ReferenceSortedMap<V> tailMap(int paramInt);

  public abstract int firstIntKey();

  public abstract int lastIntKey();

  public static abstract interface FastSortedEntrySet<V> extends ObjectSortedSet<Int2ReferenceMap.Entry<V>>, Int2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Int2ReferenceMap.Entry<V>> fastIterator(Int2ReferenceMap.Entry<V> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */