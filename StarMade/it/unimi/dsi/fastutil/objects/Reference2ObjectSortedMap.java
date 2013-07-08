package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2ObjectSortedMap<K, V>
  extends Reference2ObjectMap<K, V>, SortedMap<K, V>
{
  public abstract ObjectSortedSet<Map.Entry<K, V>> entrySet();
  
  public abstract ObjectSortedSet<Reference2ObjectMap.Entry<K, V>> reference2ObjectEntrySet();
  
  public abstract ReferenceSortedSet<K> keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Reference2ObjectSortedMap<K, V> subMap(K paramK1, K paramK2);
  
  public abstract Reference2ObjectSortedMap<K, V> headMap(K paramK);
  
  public abstract Reference2ObjectSortedMap<K, V> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K, V>
    extends ObjectSortedSet<Reference2ObjectMap.Entry<K, V>>, Reference2ObjectMap.FastEntrySet<K, V>
  {
    public abstract ObjectBidirectionalIterator<Reference2ObjectMap.Entry<K, V>> fastIterator(Reference2ObjectMap.Entry<K, V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */