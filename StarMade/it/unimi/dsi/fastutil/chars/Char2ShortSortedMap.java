package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2ShortSortedMap extends Char2ShortMap, SortedMap<Character, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Short>> entrySet();

  public abstract ObjectSortedSet<Char2ShortMap.Entry> char2ShortEntrySet();

  public abstract CharSortedSet keySet();

  public abstract ShortCollection values();

  public abstract CharComparator comparator();

  public abstract Char2ShortSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2ShortSortedMap headMap(Character paramCharacter);

  public abstract Char2ShortSortedMap tailMap(Character paramCharacter);

  public abstract Char2ShortSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2ShortSortedMap headMap(char paramChar);

  public abstract Char2ShortSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2ShortMap.Entry>, Char2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2ShortMap.Entry> fastIterator(Char2ShortMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ShortSortedMap
 * JD-Core Version:    0.6.2
 */