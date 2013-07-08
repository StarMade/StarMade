package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2FloatSortedMap
  extends Short2FloatMap, SortedMap<Short, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Float>> entrySet();
  
  public abstract ObjectSortedSet<Short2FloatMap.Entry> short2FloatEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2FloatSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2FloatSortedMap headMap(Short paramShort);
  
  public abstract Short2FloatSortedMap tailMap(Short paramShort);
  
  public abstract Short2FloatSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2FloatSortedMap headMap(short paramShort);
  
  public abstract Short2FloatSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2FloatMap.Entry>, Short2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2FloatMap.Entry> fastIterator(Short2FloatMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */