package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.CharCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Reference2CharSortedMap<K>
  extends Reference2CharMap<K>, SortedMap<K, Character>
{
  public abstract ObjectSortedSet<Map.Entry<K, Character>> entrySet();
  
  public abstract ObjectSortedSet<Reference2CharMap.Entry<K>> reference2CharEntrySet();
  
  public abstract ReferenceSortedSet<K> keySet();
  
  public abstract CharCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Reference2CharSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Reference2CharSortedMap<K> headMap(K paramK);
  
  public abstract Reference2CharSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Reference2CharMap.Entry<K>>, Reference2CharMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Reference2CharMap.Entry<K>> fastIterator(Reference2CharMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */