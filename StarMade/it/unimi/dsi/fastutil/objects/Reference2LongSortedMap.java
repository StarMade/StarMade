package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.longs.LongCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2LongSortedMap<K>
  extends Reference2LongMap<K>, SortedMap<K, Long>
{
  public abstract ObjectSortedSet<Map.Entry<K, Long>> entrySet();
  
  public abstract ObjectSortedSet<Reference2LongMap.Entry<K>> reference2LongEntrySet();
  
  public abstract ReferenceSortedSet<K> keySet();
  
  public abstract LongCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Reference2LongSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Reference2LongSortedMap<K> headMap(K paramK);
  
  public abstract Reference2LongSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Reference2LongMap.Entry<K>>, Reference2LongMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2LongMap.Entry<K>> fastIterator(Reference2LongMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */