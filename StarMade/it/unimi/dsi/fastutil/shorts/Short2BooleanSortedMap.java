package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2BooleanSortedMap
  extends Short2BooleanMap, SortedMap<Short, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Boolean>> entrySet();
  
  public abstract ObjectSortedSet<Short2BooleanMap.Entry> short2BooleanEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2BooleanSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2BooleanSortedMap headMap(Short paramShort);
  
  public abstract Short2BooleanSortedMap tailMap(Short paramShort);
  
  public abstract Short2BooleanSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2BooleanSortedMap headMap(short paramShort);
  
  public abstract Short2BooleanSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2BooleanMap.Entry>, Short2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2BooleanMap.Entry> fastIterator(Short2BooleanMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */