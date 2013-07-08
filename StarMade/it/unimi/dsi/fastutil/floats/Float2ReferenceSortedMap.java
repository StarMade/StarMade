package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2ReferenceSortedMap<V>
  extends Float2ReferenceMap<V>, SortedMap<Float, V>
{
  public abstract ObjectSortedSet<Map.Entry<Float, V>> entrySet();
  
  public abstract ObjectSortedSet<Float2ReferenceMap.Entry<V>> float2ReferenceEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2ReferenceSortedMap<V> subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2ReferenceSortedMap<V> headMap(Float paramFloat);
  
  public abstract Float2ReferenceSortedMap<V> tailMap(Float paramFloat);
  
  public abstract Float2ReferenceSortedMap<V> subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2ReferenceSortedMap<V> headMap(float paramFloat);
  
  public abstract Float2ReferenceSortedMap<V> tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Float2ReferenceMap.Entry<V>>, Float2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Float2ReferenceMap.Entry<V>> fastIterator(Float2ReferenceMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */