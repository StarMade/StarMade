package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2DoubleSortedMap extends Int2DoubleMap, SortedMap<Integer, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Double>> entrySet();

  public abstract ObjectSortedSet<Int2DoubleMap.Entry> int2DoubleEntrySet();

  public abstract IntSortedSet keySet();

  public abstract DoubleCollection values();

  public abstract IntComparator comparator();

  public abstract Int2DoubleSortedMap subMap(Integer paramInteger1, Integer paramInteger2);

  public abstract Int2DoubleSortedMap headMap(Integer paramInteger);

  public abstract Int2DoubleSortedMap tailMap(Integer paramInteger);

  public abstract Int2DoubleSortedMap subMap(int paramInt1, int paramInt2);

  public abstract Int2DoubleSortedMap headMap(int paramInt);

  public abstract Int2DoubleSortedMap tailMap(int paramInt);

  public abstract int firstIntKey();

  public abstract int lastIntKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Int2DoubleMap.Entry>, Int2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2DoubleMap.Entry> fastIterator(Int2DoubleMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */