package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.longs.LongCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2LongSortedMap<K>
  extends Object2LongMap<K>, SortedMap<K, Long>
{
  public abstract ObjectSortedSet<Map.Entry<K, Long>> entrySet();
  
  public abstract ObjectSortedSet<Object2LongMap.Entry<K>> object2LongEntrySet();
  
  public abstract ObjectSortedSet<K> keySet();
  
  public abstract LongCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Object2LongSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Object2LongSortedMap<K> headMap(K paramK);
  
  public abstract Object2LongSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Object2LongMap.Entry<K>>, Object2LongMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2LongMap.Entry<K>> fastIterator(Object2LongMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */