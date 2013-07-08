package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2BooleanSortedMap
  extends Char2BooleanMap, SortedMap<Character, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Boolean>> entrySet();
  
  public abstract ObjectSortedSet<Char2BooleanMap.Entry> char2BooleanEntrySet();
  
  public abstract CharSortedSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract CharComparator comparator();
  
  public abstract Char2BooleanSortedMap subMap(Character paramCharacter1, Character paramCharacter2);
  
  public abstract Char2BooleanSortedMap headMap(Character paramCharacter);
  
  public abstract Char2BooleanSortedMap tailMap(Character paramCharacter);
  
  public abstract Char2BooleanSortedMap subMap(char paramChar1, char paramChar2);
  
  public abstract Char2BooleanSortedMap headMap(char paramChar);
  
  public abstract Char2BooleanSortedMap tailMap(char paramChar);
  
  public abstract char firstCharKey();
  
  public abstract char lastCharKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Char2BooleanMap.Entry>, Char2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2BooleanMap.Entry> fastIterator(Char2BooleanMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */