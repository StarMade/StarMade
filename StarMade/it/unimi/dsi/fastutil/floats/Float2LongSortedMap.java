package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2LongSortedMap
  extends Float2LongMap, SortedMap<Float, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Long>> entrySet();
  
  public abstract ObjectSortedSet<Float2LongMap.Entry> float2LongEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract LongCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2LongSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2LongSortedMap headMap(Float paramFloat);
  
  public abstract Float2LongSortedMap tailMap(Float paramFloat);
  
  public abstract Float2LongSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2LongSortedMap headMap(float paramFloat);
  
  public abstract Float2LongSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2LongMap.Entry>, Float2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2LongMap.Entry> fastIterator(Float2LongMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */