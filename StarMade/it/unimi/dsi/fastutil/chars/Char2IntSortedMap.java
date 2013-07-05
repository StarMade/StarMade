package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2IntSortedMap extends Char2IntMap, SortedMap<Character, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Integer>> entrySet();

  public abstract ObjectSortedSet<Char2IntMap.Entry> char2IntEntrySet();

  public abstract CharSortedSet keySet();

  public abstract IntCollection values();

  public abstract CharComparator comparator();

  public abstract Char2IntSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2IntSortedMap headMap(Character paramCharacter);

  public abstract Char2IntSortedMap tailMap(Character paramCharacter);

  public abstract Char2IntSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2IntSortedMap headMap(char paramChar);

  public abstract Char2IntSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2IntMap.Entry>, Char2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2IntMap.Entry> fastIterator(Char2IntMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2IntSortedMap
 * JD-Core Version:    0.6.2
 */