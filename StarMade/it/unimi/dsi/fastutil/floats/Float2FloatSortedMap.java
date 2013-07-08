package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2FloatSortedMap
  extends Float2FloatMap, SortedMap<Float, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Float>> entrySet();
  
  public abstract ObjectSortedSet<Float2FloatMap.Entry> float2FloatEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2FloatSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2FloatSortedMap headMap(Float paramFloat);
  
  public abstract Float2FloatSortedMap tailMap(Float paramFloat);
  
  public abstract Float2FloatSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2FloatSortedMap headMap(float paramFloat);
  
  public abstract Float2FloatSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2FloatMap.Entry>, Float2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2FloatMap.Entry> fastIterator(Float2FloatMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */