package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Char2ByteSortedMap
  extends Char2ByteMap, SortedMap<Character, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Character, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Char2ByteMap.Entry> char2ByteEntrySet();
  
  public abstract CharSortedSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract CharComparator comparator();
  
  public abstract Char2ByteSortedMap subMap(Character paramCharacter1, Character paramCharacter2);
  
  public abstract Char2ByteSortedMap headMap(Character paramCharacter);
  
  public abstract Char2ByteSortedMap tailMap(Character paramCharacter);
  
  public abstract Char2ByteSortedMap subMap(char paramChar1, char paramChar2);
  
  public abstract Char2ByteSortedMap headMap(char paramChar);
  
  public abstract Char2ByteSortedMap tailMap(char paramChar);
  
  public abstract char firstCharKey();
  
  public abstract char lastCharKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Char2ByteMap.Entry>, Char2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Char2ByteMap.Entry> fastIterator(Char2ByteMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */