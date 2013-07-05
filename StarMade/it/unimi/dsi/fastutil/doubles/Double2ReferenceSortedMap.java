package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2ReferenceSortedMap<V> extends Double2ReferenceMap<V>, SortedMap<Double, V>
{
  public abstract ObjectSortedSet<Map.Entry<Double, V>> entrySet();

  public abstract ObjectSortedSet<Double2ReferenceMap.Entry<V>> double2ReferenceEntrySet();

  public abstract DoubleSortedSet keySet();

  public abstract ReferenceCollection<V> values();

  public abstract DoubleComparator comparator();

  public abstract Double2ReferenceSortedMap<V> subMap(Double paramDouble1, Double paramDouble2);

  public abstract Double2ReferenceSortedMap<V> headMap(Double paramDouble);

  public abstract Double2ReferenceSortedMap<V> tailMap(Double paramDouble);

  public abstract Double2ReferenceSortedMap<V> subMap(double paramDouble1, double paramDouble2);

  public abstract Double2ReferenceSortedMap<V> headMap(double paramDouble);

  public abstract Double2ReferenceSortedMap<V> tailMap(double paramDouble);

  public abstract double firstDoubleKey();

  public abstract double lastDoubleKey();

  public static abstract interface FastSortedEntrySet<V> extends ObjectSortedSet<Double2ReferenceMap.Entry<V>>, Double2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Double2ReferenceMap.Entry<V>> fastIterator(Double2ReferenceMap.Entry<V> paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */