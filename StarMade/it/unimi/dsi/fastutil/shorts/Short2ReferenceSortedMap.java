package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Short2ReferenceSortedMap<V>
  extends Short2ReferenceMap<V>, SortedMap<Short, V>
{
  public abstract ObjectSortedSet<Map.Entry<Short, V>> entrySet();
  
  public abstract ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet();
  
  public abstract ShortSortedSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract ShortComparator comparator();
  
  public abstract Short2ReferenceSortedMap<V> subMap(Short paramShort1, Short paramShort2);
  
  public abstract Short2ReferenceSortedMap<V> headMap(Short paramShort);
  
  public abstract Short2ReferenceSortedMap<V> tailMap(Short paramShort);
  
  public abstract Short2ReferenceSortedMap<V> subMap(short paramShort1, short paramShort2);
  
  public abstract Short2ReferenceSortedMap<V> headMap(short paramShort);
  
  public abstract Short2ReferenceSortedMap<V> tailMap(short paramShort);
  
  public abstract short firstShortKey();
  
  public abstract short lastShortKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Short2ReferenceMap.Entry<V>>, Short2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Short2ReferenceMap.Entry<V>> fastIterator(Short2ReferenceMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */