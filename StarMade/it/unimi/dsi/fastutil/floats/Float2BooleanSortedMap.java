package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2BooleanSortedMap
  extends Float2BooleanMap, SortedMap<Float, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Boolean>> entrySet();
  
  public abstract ObjectSortedSet<Float2BooleanMap.Entry> float2BooleanEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2BooleanSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2BooleanSortedMap headMap(Float paramFloat);
  
  public abstract Float2BooleanSortedMap tailMap(Float paramFloat);
  
  public abstract Float2BooleanSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2BooleanSortedMap headMap(float paramFloat);
  
  public abstract Float2BooleanSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2BooleanMap.Entry>, Float2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2BooleanMap.Entry> fastIterator(Float2BooleanMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */