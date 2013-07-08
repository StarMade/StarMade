package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2ShortSortedMap
  extends Float2ShortMap, SortedMap<Float, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Short>> entrySet();
  
  public abstract ObjectSortedSet<Float2ShortMap.Entry> float2ShortEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2ShortSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2ShortSortedMap headMap(Float paramFloat);
  
  public abstract Float2ShortSortedMap tailMap(Float paramFloat);
  
  public abstract Float2ShortSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2ShortSortedMap headMap(float paramFloat);
  
  public abstract Float2ShortSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2ShortMap.Entry>, Float2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2ShortMap.Entry> fastIterator(Float2ShortMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */