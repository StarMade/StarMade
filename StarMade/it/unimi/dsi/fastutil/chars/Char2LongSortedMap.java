package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2LongSortedMap extends Char2LongMap, SortedMap<Character, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Long>> entrySet();

  public abstract ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet();

  public abstract CharSortedSet keySet();

  public abstract LongCollection values();

  public abstract CharComparator comparator();

  public abstract Char2LongSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2LongSortedMap headMap(Character paramCharacter);

  public abstract Char2LongSortedMap tailMap(Character paramCharacter);

  public abstract Char2LongSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2LongSortedMap headMap(char paramChar);

  public abstract Char2LongSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2LongMap.Entry>, Char2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2LongMap.Entry> fastIterator(Char2LongMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongSortedMap
 * JD-Core Version:    0.6.2
 */