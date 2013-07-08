package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2DoubleSortedMap
  extends Double2DoubleMap, SortedMap<Double, Double>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Double>> entrySet();
  
  public abstract ObjectSortedSet<Double2DoubleMap.Entry> double2DoubleEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2DoubleSortedMap subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2DoubleSortedMap headMap(Double paramDouble);
  
  public abstract Double2DoubleSortedMap tailMap(Double paramDouble);
  
  public abstract Double2DoubleSortedMap subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2DoubleSortedMap headMap(double paramDouble);
  
  public abstract Double2DoubleSortedMap tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Double2DoubleMap.Entry>, Double2DoubleMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2DoubleMap.Entry> fastIterator(Double2DoubleMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */