package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2DoubleSortedMap extends Char2DoubleMap, SortedMap<Character, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Double>> entrySet();

  public abstract ObjectSortedSet<Char2DoubleMap.Entry> char2DoubleEntrySet();

  public abstract CharSortedSet keySet();

  public abstract DoubleCollection values();

  public abstract CharComparator comparator();

  public abstract Char2DoubleSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2DoubleSortedMap headMap(Character paramCharacter);

  public abstract Char2DoubleSortedMap tailMap(Character paramCharacter);

  public abstract Char2DoubleSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2DoubleSortedMap headMap(char paramChar);

  public abstract Char2DoubleSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2DoubleMap.Entry>, Char2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2DoubleMap.Entry> fastIterator(Char2DoubleMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */