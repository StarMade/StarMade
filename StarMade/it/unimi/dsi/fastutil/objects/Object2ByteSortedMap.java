package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2ByteSortedMap<K>
  extends Object2ByteMap<K>, SortedMap<K, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<K, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet();
  
  public abstract ObjectSortedSet<K> keySet();
  
  public abstract ByteCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Object2ByteSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Object2ByteSortedMap<K> headMap(K paramK);
  
  public abstract Object2ByteSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Object2ByteMap.Entry<K>>, Object2ByteMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> fastIterator(Object2ByteMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */