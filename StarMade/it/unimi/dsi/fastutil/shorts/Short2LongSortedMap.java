package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2LongSortedMap
  extends Short2LongMap, SortedMap<Short, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Long>> entrySet();
  
  public abstract ObjectSortedSet<Short2LongMap.Entry> short2LongEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract LongCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2LongSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2LongSortedMap headMap(Short paramShort);
  
  public abstract Short2LongSortedMap tailMap(Short paramShort);
  
  public abstract Short2LongSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2LongSortedMap headMap(short paramShort);
  
  public abstract Short2LongSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2LongMap.Entry>, Short2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2LongMap.Entry> fastIterator(Short2LongMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */