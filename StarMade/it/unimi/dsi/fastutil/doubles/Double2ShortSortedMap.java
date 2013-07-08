package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2ShortSortedMap
  extends Double2ShortMap, SortedMap<Double, Short>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Short>> entrySet();
  
  public abstract ObjectSortedSet<Double2ShortMap.Entry> double2ShortEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2ShortSortedMap subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2ShortSortedMap headMap(Double paramDouble);
  
  public abstract Double2ShortSortedMap tailMap(Double paramDouble);
  
  public abstract Double2ShortSortedMap subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2ShortSortedMap headMap(double paramDouble);
  
  public abstract Double2ShortSortedMap tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Double2ShortMap.Entry>, Double2ShortMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2ShortMap.Entry> fastIterator(Double2ShortMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */