package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2ObjectSortedMap<V> extends Char2ObjectMap<V>, SortedMap<Character, V>
{
  public abstract ObjectSortedSet<Map.Entry<Character, V>> entrySet();

  public abstract ObjectSortedSet<Char2ObjectMap.Entry<V>> char2ObjectEntrySet();

  public abstract CharSortedSet keySet();

  public abstract ObjectCollection<V> values();

  public abstract CharComparator comparator();

  public abstract Char2ObjectSortedMap<V> subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2ObjectSortedMap<V> headMap(Character paramCharacter);

  public abstract Char2ObjectSortedMap<V> tailMap(Character paramCharacter);

  public abstract Char2ObjectSortedMap<V> subMap(char paramChar1, char paramChar2);

  public abstract Char2ObjectSortedMap<V> headMap(char paramChar);

  public abstract Char2ObjectSortedMap<V> tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet<V> extends ObjectSortedSet<Char2ObjectMap.Entry<V>>, Char2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Char2ObjectMap.Entry<V>> fastIterator(Char2ObjectMap.Entry<V> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */