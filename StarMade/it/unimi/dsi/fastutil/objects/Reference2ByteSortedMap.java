package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2ByteSortedMap<K> extends Reference2ByteMap<K>, SortedMap<K, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<K, Byte>> entrySet();

  public abstract ObjectSortedSet<Reference2ByteMap.Entry<K>> reference2ByteEntrySet();

  public abstract ReferenceSortedSet<K> keySet();

  public abstract ByteCollection values();

  public abstract Comparator<? super K> comparator();

  public abstract Reference2ByteSortedMap<K> subMap(K paramK1, K paramK2);

  public abstract Reference2ByteSortedMap<K> headMap(K paramK);

  public abstract Reference2ByteSortedMap<K> tailMap(K paramK);

  public static abstract interface FastSortedEntrySet<K> extends ObjectSortedSet<Reference2ByteMap.Entry<K>>, Reference2ByteMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2ByteMap.Entry<K>> fastIterator(Reference2ByteMap.Entry<K> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ByteSortedMap
 * JD-Core Version:    0.6.2
 */