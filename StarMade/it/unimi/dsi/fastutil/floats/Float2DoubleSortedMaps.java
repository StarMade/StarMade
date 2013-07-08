package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Float2DoubleSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Float, ?>> entryComparator(FloatComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Float, ?> local_x, Map.Entry<Float, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Float2DoubleSortedMap singleton(Float key, Double value)
  {
    return new Singleton(key.floatValue(), value.doubleValue());
  }
  
  public static Float2DoubleSortedMap singleton(Float key, Double value, FloatComparator comparator)
  {
    return new Singleton(key.floatValue(), value.doubleValue(), comparator);
  }
  
  public static Float2DoubleSortedMap singleton(float key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2DoubleSortedMap singleton(float key, double value, FloatComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Float2DoubleSortedMap synchronize(Float2DoubleSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Float2DoubleSortedMap synchronize(Float2DoubleSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Float2DoubleSortedMap unmodifiable(Float2DoubleSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Float2DoubleMaps.UnmodifiableMap
    implements Float2DoubleSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2DoubleSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Float2DoubleSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public FloatComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2DoubleEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
    {
      return float2DoubleEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2DoubleSortedMap subMap(float from, float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Float2DoubleSortedMap headMap(float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Float2DoubleSortedMap tailMap(float from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public float firstFloatKey()
    {
      return this.sortedMap.firstFloatKey();
    }
    
    public float lastFloatKey()
    {
      return this.sortedMap.lastFloatKey();
    }
    
    public Float firstKey()
    {
      return (Float)this.sortedMap.firstKey();
    }
    
    public Float lastKey()
    {
      return (Float)this.sortedMap.lastKey();
    }
    
    public Float2DoubleSortedMap subMap(Float from, Float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Float2DoubleSortedMap headMap(Float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Float2DoubleSortedMap tailMap(Float from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Float2DoubleMaps.SynchronizedMap
    implements Float2DoubleSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2DoubleSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Float2DoubleSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Float2DoubleSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public FloatComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2DoubleEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
    {
      return float2DoubleEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2DoubleSortedMap subMap(float from, float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Float2DoubleSortedMap headMap(float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Float2DoubleSortedMap tailMap(float from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public float firstFloatKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstFloatKey();
      }
    }
    
    public float lastFloatKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastFloatKey();
      }
    }
    
    public Float firstKey()
    {
      synchronized (this.sync)
      {
        return (Float)this.sortedMap.firstKey();
      }
    }
    
    public Float lastKey()
    {
      synchronized (this.sync)
      {
        return (Float)this.sortedMap.lastKey();
      }
    }
    
    public Float2DoubleSortedMap subMap(Float from, Float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Float2DoubleSortedMap headMap(Float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Float2DoubleSortedMap tailMap(Float from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Float2DoubleMaps.Singleton
    implements Float2DoubleSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatComparator comparator;
    
    protected Singleton(float key, double value, FloatComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(float key, double value)
    {
      this(key, value, null);
    }
    
    final int compare(float local_k1, float local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public FloatComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Float2DoubleMaps.Singleton.SingletonEntry(this), Float2DoubleSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
    {
      return float2DoubleEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.singleton(this.key, this.comparator);
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2DoubleSortedMap subMap(float from, float local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Float2DoubleSortedMap headMap(float local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Float2DoubleSortedMap tailMap(float from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public float firstFloatKey()
    {
      return this.key;
    }
    
    public float lastFloatKey()
    {
      return this.key;
    }
    
    public Float2DoubleSortedMap headMap(Float oto)
    {
      return headMap(oto.floatValue());
    }
    
    public Float2DoubleSortedMap tailMap(Float ofrom)
    {
      return tailMap(ofrom.floatValue());
    }
    
    public Float2DoubleSortedMap subMap(Float ofrom, Float oto)
    {
      return subMap(ofrom.floatValue(), oto.floatValue());
    }
    
    public Float firstKey()
    {
      return Float.valueOf(firstFloatKey());
    }
    
    public Float lastKey()
    {
      return Float.valueOf(lastFloatKey());
    }
  }
  
  public static class EmptySortedMap
    extends Float2DoubleMaps.EmptyMap
    implements Float2DoubleSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public FloatComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet keySet()
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public Float2DoubleSortedMap subMap(float from, float local_to)
    {
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Float2DoubleSortedMap headMap(float local_to)
    {
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Float2DoubleSortedMap tailMap(float from)
    {
      return Float2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public float firstFloatKey()
    {
      throw new NoSuchElementException();
    }
    
    public float lastFloatKey()
    {
      throw new NoSuchElementException();
    }
    
    public Float2DoubleSortedMap headMap(Float oto)
    {
      return headMap(oto.floatValue());
    }
    
    public Float2DoubleSortedMap tailMap(Float ofrom)
    {
      return tailMap(ofrom.floatValue());
    }
    
    public Float2DoubleSortedMap subMap(Float ofrom, Float oto)
    {
      return subMap(ofrom.floatValue(), oto.floatValue());
    }
    
    public Float firstKey()
    {
      return Float.valueOf(firstFloatKey());
    }
    
    public Float lastKey()
    {
      return Float.valueOf(lastFloatKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */