package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2CharSortedMap extends Char2CharMap, SortedMap<Character, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Character>> entrySet();

  public abstract ObjectSortedSet<Char2CharMap.Entry> char2CharEntrySet();

  public abstract CharSortedSet keySet();

  public abstract CharCollection values();

  public abstract CharComparator comparator();

  public abstract Char2CharSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2CharSortedMap headMap(Character paramCharacter);

  public abstract Char2CharSortedMap tailMap(Character paramCharacter);

  public abstract Char2CharSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2CharSortedMap headMap(char paramChar);

  public abstract Char2CharSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2CharMap.Entry>, Char2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2CharMap.Entry> fastIterator(Char2CharMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2CharSortedMap
 * JD-Core Version:    0.6.2
 */