package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2ObjectSortedMap<K, V>
  extends Object2ObjectMap<K, V>, SortedMap<K, V>
{
  public abstract ObjectSortedSet<Map.Entry<K, V>> entrySet();
  
  public abstract ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet();
  
  public abstract ObjectSortedSet<K> keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Object2ObjectSortedMap<K, V> subMap(K paramK1, K paramK2);
  
  public abstract Object2ObjectSortedMap<K, V> headMap(K paramK);
  
  public abstract Object2ObjectSortedMap<K, V> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K, V>
    extends ObjectSortedSet<Object2ObjectMap.Entry<K, V>>, Object2ObjectMap.FastEntrySet<K, V>
  {
    public abstract ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator(Object2ObjectMap.Entry<K, V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */