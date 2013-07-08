package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2ObjectSortedMap<V>
  extends Int2ObjectMap<V>, SortedMap<Integer, V>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, V>> entrySet();
  
  public abstract ObjectSortedSet<Int2ObjectMap.Entry<V>> int2ObjectEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2ObjectSortedMap<V> subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2ObjectSortedMap<V> headMap(Integer paramInteger);
  
  public abstract Int2ObjectSortedMap<V> tailMap(Integer paramInteger);
  
  public abstract Int2ObjectSortedMap<V> subMap(int paramInt1, int paramInt2);
  
  public abstract Int2ObjectSortedMap<V> headMap(int paramInt);
  
  public abstract Int2ObjectSortedMap<V> tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Int2ObjectMap.Entry<V>>, Int2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Int2ObjectMap.Entry<V>> fastIterator(Int2ObjectMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */