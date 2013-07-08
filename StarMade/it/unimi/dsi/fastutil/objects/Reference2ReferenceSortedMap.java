package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2ReferenceSortedMap<K, V>
  extends Reference2ReferenceMap<K, V>, SortedMap<K, V>
{
  public abstract ObjectSortedSet<Map.Entry<K, V>> entrySet();
  
  public abstract ObjectSortedSet<Reference2ReferenceMap.Entry<K, V>> reference2ReferenceEntrySet();
  
  public abstract ReferenceSortedSet<K> keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Reference2ReferenceSortedMap<K, V> subMap(K paramK1, K paramK2);
  
  public abstract Reference2ReferenceSortedMap<K, V> headMap(K paramK);
  
  public abstract Reference2ReferenceSortedMap<K, V> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K, V>
    extends ObjectSortedSet<Reference2ReferenceMap.Entry<K, V>>, Reference2ReferenceMap.FastEntrySet<K, V>
  {
    public abstract ObjectBidirectionalIterator<Reference2ReferenceMap.Entry<K, V>> fastIterator(Reference2ReferenceMap.Entry<K, V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */