package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2LongSortedMap extends Double2LongMap, SortedMap<Double, Long>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Long>> entrySet();

  public abstract ObjectSortedSet<Double2LongMap.Entry> double2LongEntrySet();

  public abstract DoubleSortedSet keySet();

  public abstract LongCollection values();

  public abstract DoubleComparator comparator();

  public abstract Double2LongSortedMap subMap(Double paramDouble1, Double paramDouble2);

  public abstract Double2LongSortedMap headMap(Double paramDouble);

  public abstract Double2LongSortedMap tailMap(Double paramDouble);

  public abstract Double2LongSortedMap subMap(double paramDouble1, double paramDouble2);

  public abstract Double2LongSortedMap headMap(double paramDouble);

  public abstract Double2LongSortedMap tailMap(double paramDouble);

  public abstract double firstDoubleKey();

  public abstract double lastDoubleKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Double2LongMap.Entry>, Double2LongMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2LongMap.Entry> fastIterator(Double2LongMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2LongSortedMap
 * JD-Core Version:    0.6.2
 */