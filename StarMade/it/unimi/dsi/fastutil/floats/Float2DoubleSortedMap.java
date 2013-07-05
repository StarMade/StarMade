package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2DoubleSortedMap extends Float2DoubleMap, SortedMap<Float, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Double>> entrySet();

  public abstract ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet();

  public abstract FloatSortedSet keySet();

  public abstract DoubleCollection values();

  public abstract FloatComparator comparator();

  public abstract Float2DoubleSortedMap subMap(Float paramFloat1, Float paramFloat2);

  public abstract Float2DoubleSortedMap headMap(Float paramFloat);

  public abstract Float2DoubleSortedMap tailMap(Float paramFloat);

  public abstract Float2DoubleSortedMap subMap(float paramFloat1, float paramFloat2);

  public abstract Float2DoubleSortedMap headMap(float paramFloat);

  public abstract Float2DoubleSortedMap tailMap(float paramFloat);

  public abstract float firstFloatKey();

  public abstract float lastFloatKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Float2DoubleMap.Entry>, Float2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2DoubleMap.Entry> fastIterator(Float2DoubleMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */