package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2CharSortedMap
  extends Short2CharMap, SortedMap<Short, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Character>> entrySet();
  
  public abstract ObjectSortedSet<Short2CharMap.Entry> short2CharEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract CharCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2CharSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2CharSortedMap headMap(Short paramShort);
  
  public abstract Short2CharSortedMap tailMap(Short paramShort);
  
  public abstract Short2CharSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2CharSortedMap headMap(short paramShort);
  
  public abstract Short2CharSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2CharMap.Entry>, Short2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2CharMap.Entry> fastIterator(Short2CharMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */