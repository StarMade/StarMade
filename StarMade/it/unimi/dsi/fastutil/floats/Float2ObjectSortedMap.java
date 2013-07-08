package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2ObjectSortedMap<V>
  extends Float2ObjectMap<V>, SortedMap<Float, V>
{
  public abstract ObjectSortedSet<Map.Entry<Float, V>> entrySet();
  
  public abstract ObjectSortedSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2ObjectSortedMap<V> subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2ObjectSortedMap<V> headMap(Float paramFloat);
  
  public abstract Float2ObjectSortedMap<V> tailMap(Float paramFloat);
  
  public abstract Float2ObjectSortedMap<V> subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2ObjectSortedMap<V> headMap(float paramFloat);
  
  public abstract Float2ObjectSortedMap<V> tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Float2ObjectMap.Entry<V>>, Float2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> fastIterator(Float2ObjectMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */