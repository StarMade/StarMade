package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2IntSortedMap extends Double2IntMap, SortedMap<Double, Integer>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Integer>> entrySet();

  public abstract ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet();

  public abstract DoubleSortedSet keySet();

  public abstract IntCollection values();

  public abstract DoubleComparator comparator();

  public abstract Double2IntSortedMap subMap(Double paramDouble1, Double paramDouble2);

  public abstract Double2IntSortedMap headMap(Double paramDouble);

  public abstract Double2IntSortedMap tailMap(Double paramDouble);

  public abstract Double2IntSortedMap subMap(double paramDouble1, double paramDouble2);

  public abstract Double2IntSortedMap headMap(double paramDouble);

  public abstract Double2IntSortedMap tailMap(double paramDouble);

  public abstract double firstDoubleKey();

  public abstract double lastDoubleKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Double2IntMap.Entry>, Double2IntMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2IntMap.Entry> fastIterator(Double2IntMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntSortedMap
 * JD-Core Version:    0.6.2
 */