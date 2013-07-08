package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Double2ByteSortedMap
  extends Double2ByteMap, SortedMap<Double, Byte>
{
  public abstract ObjectSortedSet<Map.Entry<Double, Byte>> entrySet();
  
  public abstract ObjectSortedSet<Double2ByteMap.Entry> double2ByteEntrySet();
  
  public abstract DoubleSortedSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract DoubleComparator comparator();
  
  public abstract Double2ByteSortedMap subMap(Double paramDouble1, Double paramDouble2);
  
  public abstract Double2ByteSortedMap headMap(Double paramDouble);
  
  public abstract Double2ByteSortedMap tailMap(Double paramDouble);
  
  public abstract Double2ByteSortedMap subMap(double paramDouble1, double paramDouble2);
  
  public abstract Double2ByteSortedMap headMap(double paramDouble);
  
  public abstract Double2ByteSortedMap tailMap(double paramDouble);
  
  public abstract double firstDoubleKey();
  
  public abstract double lastDoubleKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Double2ByteMap.Entry>, Double2ByteMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Double2ByteMap.Entry> fastIterator(Double2ByteMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */