package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2FloatSortedMap<K>
  extends Reference2FloatMap<K>, SortedMap<K, Float>
{
  public abstract ObjectSortedSet<Map.Entry<K, Float>> entrySet();
  
  public abstract ObjectSortedSet<Reference2FloatMap.Entry<K>> reference2FloatEntrySet();
  
  public abstract ReferenceSortedSet<K> keySet();
  
  public abstract FloatCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Reference2FloatSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Reference2FloatSortedMap<K> headMap(K paramK);
  
  public abstract Reference2FloatSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Reference2FloatMap.Entry<K>>, Reference2FloatMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2FloatMap.Entry<K>> fastIterator(Reference2FloatMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */