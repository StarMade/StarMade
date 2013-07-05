package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2CharSortedMap extends Double2CharMap, SortedMap<Double, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Character>> entrySet();

  public abstract ObjectSortedSet<Double2CharMap.Entry> double2CharEntrySet();

  public abstract DoubleSortedSet keySet();

  public abstract CharCollection values();

  public abstract DoubleComparator comparator();

  public abstract Double2CharSortedMap subMap(Double paramDouble1, Double paramDouble2);

  public abstract Double2CharSortedMap headMap(Double paramDouble);

  public abstract Double2CharSortedMap tailMap(Double paramDouble);

  public abstract Double2CharSortedMap subMap(double paramDouble1, double paramDouble2);

  public abstract Double2CharSortedMap headMap(double paramDouble);

  public abstract Double2CharSortedMap tailMap(double paramDouble);

  public abstract double firstDoubleKey();

  public abstract double lastDoubleKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Double2CharMap.Entry>, Double2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2CharMap.Entry> fastIterator(Double2CharMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharSortedMap
 * JD-Core Version:    0.6.2
 */