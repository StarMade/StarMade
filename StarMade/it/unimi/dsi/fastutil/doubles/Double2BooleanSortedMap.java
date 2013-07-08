package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2BooleanSortedMap
  extends Double2BooleanMap, SortedMap<Double, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Boolean>> entrySet();
  
  public abstract ObjectSortedSet<Double2BooleanMap.Entry> double2BooleanEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2BooleanSortedMap subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2BooleanSortedMap headMap(Double paramDouble);
  
  public abstract Double2BooleanSortedMap tailMap(Double paramDouble);
  
  public abstract Double2BooleanSortedMap subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2BooleanSortedMap headMap(double paramDouble);
  
  public abstract Double2BooleanSortedMap tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Double2BooleanMap.Entry>, Double2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2BooleanMap.Entry> fastIterator(Double2BooleanMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */