package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2IntSortedMap
  extends Float2IntMap, SortedMap<Float, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Integer>> entrySet();
  
  public abstract ObjectSortedSet<Float2IntMap.Entry> float2IntEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract IntCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2IntSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2IntSortedMap headMap(Float paramFloat);
  
  public abstract Float2IntSortedMap tailMap(Float paramFloat);
  
  public abstract Float2IntSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2IntSortedMap headMap(float paramFloat);
  
  public abstract Float2IntSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2IntMap.Entry>, Float2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2IntMap.Entry> fastIterator(Float2IntMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */