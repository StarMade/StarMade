package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2ReferenceSortedMap<V>
  extends Char2ReferenceMap<V>, SortedMap<Character, V>
{
  public abstract ObjectSortedSet<Map.Entry<Character, V>> entrySet();
  
  public abstract ObjectSortedSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet();
  
  public abstract CharSortedSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract CharComparator comparator();
  
  public abstract Char2ReferenceSortedMap<V> subMap(Character paramCharacter1, Character paramCharacter2);
  
  public abstract Char2ReferenceSortedMap<V> headMap(Character paramCharacter);
  
  public abstract Char2ReferenceSortedMap<V> tailMap(Character paramCharacter);
  
  public abstract Char2ReferenceSortedMap<V> subMap(char paramChar1, char paramChar2);
  
  public abstract Char2ReferenceSortedMap<V> headMap(char paramChar);
  
  public abstract Char2ReferenceSortedMap<V> tailMap(char paramChar);
  
  public abstract char firstCharKey();
  
  public abstract char lastCharKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Char2ReferenceMap.Entry<V>>, Char2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Char2ReferenceMap.Entry<V>> fastIterator(Char2ReferenceMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */