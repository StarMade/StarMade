package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Double2ShortSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Double, ?>> entryComparator(DoubleComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Double, ?> local_x, Map.Entry<Double, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Double2ShortSortedMap singleton(Double key, Short value)
  {
    return new Singleton(key.doubleValue(), value.shortValue());
  }
  
  public static Double2ShortSortedMap singleton(Double key, Short value, DoubleComparator comparator)
  {
    return new Singleton(key.doubleValue(), value.shortValue(), comparator);
  }
  
  public static Double2ShortSortedMap singleton(double key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Double2ShortSortedMap singleton(double key, short value, DoubleComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Double2ShortSortedMap synchronize(Double2ShortSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Double2ShortSortedMap synchronize(Double2ShortSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Double2ShortSortedMap unmodifiable(Double2ShortSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Double2ShortMaps.UnmodifiableMap
    implements Double2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ShortSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Double2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public DoubleComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> double2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.double2ShortEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
    {
      return double2ShortEntrySet();
    }
    
    public DoubleSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (DoubleSortedSet)this.keys;
    }
    
    public Double2ShortSortedMap subMap(double from, double local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Double2ShortSortedMap headMap(double local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Double2ShortSortedMap tailMap(double from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public double firstDoubleKey()
    {
      return this.sortedMap.firstDoubleKey();
    }
    
    public double lastDoubleKey()
    {
      return this.sortedMap.lastDoubleKey();
    }
    
    public Double firstKey()
    {
      return (Double)this.sortedMap.firstKey();
    }
    
    public Double lastKey()
    {
      return (Double)this.sortedMap.lastKey();
    }
    
    public Double2ShortSortedMap subMap(Double from, Double local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Double2ShortSortedMap headMap(Double local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Double2ShortSortedMap tailMap(Double from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Double2ShortMaps.SynchronizedMap
    implements Double2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ShortSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Double2ShortSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Double2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public DoubleComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> double2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.double2ShortEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
    {
      return double2ShortEntrySet();
    }
    
    public DoubleSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (DoubleSortedSet)this.keys;
    }
    
    public Double2ShortSortedMap subMap(double from, double local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Double2ShortSortedMap headMap(double local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Double2ShortSortedMap tailMap(double from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public double firstDoubleKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstDoubleKey();
      }
    }
    
    public double lastDoubleKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastDoubleKey();
      }
    }
    
    public Double firstKey()
    {
      synchronized (this.sync)
      {
        return (Double)this.sortedMap.firstKey();
      }
    }
    
    public Double lastKey()
    {
      synchronized (this.sync)
      {
        return (Double)this.sortedMap.lastKey();
      }
    }
    
    public Double2ShortSortedMap subMap(Double from, Double local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Double2ShortSortedMap headMap(Double local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Double2ShortSortedMap tailMap(Double from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Double2ShortMaps.Singleton
    implements Double2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleComparator comparator;
    
    protected Singleton(double key, short value, DoubleComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(double key, short value)
    {
      this(key, value, null);
    }
    
    final int compare(double local_k1, double local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public DoubleComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> double2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Double2ShortMaps.Singleton.SingletonEntry(this), Double2ShortSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
    {
      return double2ShortEntrySet();
    }
    
    public DoubleSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSortedSets.singleton(this.key, this.comparator);
      }
      return (DoubleSortedSet)this.keys;
    }
    
    public Double2ShortSortedMap subMap(double from, double local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Double2ShortSortedMap headMap(double local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Double2ShortSortedMap tailMap(double from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public double firstDoubleKey()
    {
      return this.key;
    }
    
    public double lastDoubleKey()
    {
      return this.key;
    }
    
    public Double2ShortSortedMap headMap(Double oto)
    {
      return headMap(oto.doubleValue());
    }
    
    public Double2ShortSortedMap tailMap(Double ofrom)
    {
      return tailMap(ofrom.doubleValue());
    }
    
    public Double2ShortSortedMap subMap(Double ofrom, Double oto)
    {
      return subMap(ofrom.doubleValue(), oto.doubleValue());
    }
    
    public Double firstKey()
    {
      return Double.valueOf(firstDoubleKey());
    }
    
    public Double lastKey()
    {
      return Double.valueOf(lastDoubleKey());
    }
  }
  
  public static class EmptySortedMap
    extends Double2ShortMaps.EmptyMap
    implements Double2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public DoubleComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> double2ShortEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet keySet()
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public Double2ShortSortedMap subMap(double from, double local_to)
    {
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Double2ShortSortedMap headMap(double local_to)
    {
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Double2ShortSortedMap tailMap(double from)
    {
      return Double2ShortSortedMaps.EMPTY_MAP;
    }
    
    public double firstDoubleKey()
    {
      throw new NoSuchElementException();
    }
    
    public double lastDoubleKey()
    {
      throw new NoSuchElementException();
    }
    
    public Double2ShortSortedMap headMap(Double oto)
    {
      return headMap(oto.doubleValue());
    }
    
    public Double2ShortSortedMap tailMap(Double ofrom)
    {
      return tailMap(ofrom.doubleValue());
    }
    
    public Double2ShortSortedMap subMap(Double ofrom, Double oto)
    {
      return subMap(ofrom.doubleValue(), oto.doubleValue());
    }
    
    public Double firstKey()
    {
      return Double.valueOf(firstDoubleKey());
    }
    
    public Double lastKey()
    {
      return Double.valueOf(lastDoubleKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ShortSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */