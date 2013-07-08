package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2ObjectSortedMap<V>
  extends Double2ObjectMap<V>, SortedMap<Double, V>
{
  public abstract ObjectSortedSet<Map.Entry<Double, V>> entrySet();
  
  public abstract ObjectSortedSet<Double2ObjectMap.Entry<V>> double2ObjectEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2ObjectSortedMap<V> subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2ObjectSortedMap<V> headMap(Double paramDouble);
  
  public abstract Double2ObjectSortedMap<V> tailMap(Double paramDouble);
  
  public abstract Double2ObjectSortedMap<V> subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2ObjectSortedMap<V> headMap(double paramDouble);
  
  public abstract Double2ObjectSortedMap<V> tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Double2ObjectMap.Entry<V>>, Double2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Double2ObjectMap.Entry<V>> fastIterator(Double2ObjectMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */