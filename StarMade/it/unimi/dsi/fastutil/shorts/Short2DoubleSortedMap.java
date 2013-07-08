package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2DoubleSortedMap
  extends Short2DoubleMap, SortedMap<Short, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Double>> entrySet();
  
  public abstract ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2DoubleSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2DoubleSortedMap headMap(Short paramShort);
  
  public abstract Short2DoubleSortedMap tailMap(Short paramShort);
  
  public abstract Short2DoubleSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2DoubleSortedMap headMap(short paramShort);
  
  public abstract Short2DoubleSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2DoubleMap.Entry>, Short2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2DoubleMap.Entry> fastIterator(Short2DoubleMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */