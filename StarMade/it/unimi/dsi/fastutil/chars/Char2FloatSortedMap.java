package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2FloatSortedMap extends Char2FloatMap, SortedMap<Character, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Float>> entrySet();

  public abstract ObjectSortedSet<Char2FloatMap.Entry> char2FloatEntrySet();

  public abstract CharSortedSet keySet();

  public abstract FloatCollection values();

  public abstract CharComparator comparator();

  public abstract Char2FloatSortedMap subMap(Character paramCharacter1, Character paramCharacter2);

  public abstract Char2FloatSortedMap headMap(Character paramCharacter);

  public abstract Char2FloatSortedMap tailMap(Character paramCharacter);

  public abstract Char2FloatSortedMap subMap(char paramChar1, char paramChar2);

  public abstract Char2FloatSortedMap headMap(char paramChar);

  public abstract Char2FloatSortedMap tailMap(char paramChar);

  public abstract char firstCharKey();

  public abstract char lastCharKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Char2FloatMap.Entry>, Char2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2FloatMap.Entry> fastIterator(Char2FloatMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2FloatSortedMap
 * JD-Core Version:    0.6.2
 */