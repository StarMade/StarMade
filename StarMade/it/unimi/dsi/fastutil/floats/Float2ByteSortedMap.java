package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2ByteSortedMap extends Float2ByteMap, SortedMap<Float, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Byte>> entrySet();

  public abstract ObjectSortedSet<Float2ByteMap.Entry> float2ByteEntrySet();

  public abstract FloatSortedSet keySet();

  public abstract ByteCollection values();

  public abstract FloatComparator comparator();

  public abstract Float2ByteSortedMap subMap(Float paramFloat1, Float paramFloat2);

  public abstract Float2ByteSortedMap headMap(Float paramFloat);

  public abstract Float2ByteSortedMap tailMap(Float paramFloat);

  public abstract Float2ByteSortedMap subMap(float paramFloat1, float paramFloat2);

  public abstract Float2ByteSortedMap headMap(float paramFloat);

  public abstract Float2ByteSortedMap tailMap(float paramFloat);

  public abstract float firstFloatKey();

  public abstract float lastFloatKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Float2ByteMap.Entry>, Float2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2ByteMap.Entry> fastIterator(Float2ByteMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ByteSortedMap
 * JD-Core Version:    0.6.2
 */