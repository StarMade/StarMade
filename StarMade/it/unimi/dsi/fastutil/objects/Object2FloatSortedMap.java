package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Object2FloatSortedMap<K>
  extends Object2FloatMap<K>, SortedMap<K, Float>
{
  public abstract ObjectSortedSet<Map.Entry<K, Float>> entrySet();
  
  public abstract ObjectSortedSet<Object2FloatMap.Entry<K>> object2FloatEntrySet();
  
  public abstract ObjectSortedSet<K> keySet();
  
  public abstract FloatCollection values();
  
  public abstract Comparator<? super K> comparator();
  
  public abstract Object2FloatSortedMap<K> subMap(K paramK1, K paramK2);
  
  public abstract Object2FloatSortedMap<K> headMap(K paramK);
  
  public abstract Object2FloatSortedMap<K> tailMap(K paramK);
  
  public static abstract interface FastSortedEntrySet<K>
    extends ObjectSortedSet<Object2FloatMap.Entry<K>>, Object2FloatMap.FastEntrySet<K>
  {
    public abstract ObjectBidirectionalIterator<Object2FloatMap.Entry<K>> fastIterator(Object2FloatMap.Entry<K> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */