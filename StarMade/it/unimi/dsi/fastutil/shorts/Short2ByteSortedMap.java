package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2ByteSortedMap
  extends Short2ByteMap, SortedMap<Short, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Short, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Short2ByteMap.Entry> short2ByteEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2ByteSortedMap subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2ByteSortedMap headMap(Short paramShort);
  
  public abstract Short2ByteSortedMap tailMap(Short paramShort);
  
  public abstract Short2ByteSortedMap subMap(short paramShort1, short paramShort2);
  
  public abstract Short2ByteSortedMap headMap(short paramShort);
  
  public abstract Short2ByteSortedMap tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Short2ByteMap.Entry>, Short2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Short2ByteMap.Entry> fastIterator(Short2ByteMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */