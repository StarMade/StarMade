package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2ShortSortedMap
  extends Short2ShortMap, SortedMap<Short, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Short>> entrySet();
  
  public abstract ObjectSortedSet<Short2ShortMap.Entry> short2ShortEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2ShortSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2ShortSortedMap headMap(Short paramShort);
  
  public abstract Short2ShortSortedMap tailMap(Short paramShort);
  
  public abstract Short2ShortSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2ShortSortedMap headMap(short paramShort);
  
  public abstract Short2ShortSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2ShortMap.Entry>, Short2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2ShortMap.Entry> fastIterator(Short2ShortMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */