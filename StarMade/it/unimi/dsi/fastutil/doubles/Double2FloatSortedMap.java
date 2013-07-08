package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2FloatSortedMap
  extends Double2FloatMap, SortedMap<Double, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Float>> entrySet();
  
  public abstract ObjectSortedSet<Double2FloatMap.Entry> double2FloatEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2FloatSortedMap subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2FloatSortedMap headMap(Double paramDouble);
  
  public abstract Double2FloatSortedMap tailMap(Double paramDouble);
  
  public abstract Double2FloatSortedMap subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2FloatSortedMap headMap(double paramDouble);
  
  public abstract Double2FloatSortedMap tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Double2FloatMap.Entry>, Double2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2FloatMap.Entry> fastIterator(Double2FloatMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */