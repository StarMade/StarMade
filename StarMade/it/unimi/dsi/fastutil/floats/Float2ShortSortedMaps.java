package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Float2ShortSortedMaps
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
  
  public static Float2ShortSortedMap singleton(Float key, Short value)
  {
    return new Singleton(key.floatValue(), value.shortValue());
  }
  
  public static Float2ShortSortedMap singleton(Float key, Short value, FloatComparator comparator)
  {
    return new Singleton(key.floatValue(), value.shortValue(), comparator);
  }
  
  public static Float2ShortSortedMap singleton(float key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2ShortSortedMap singleton(float key, short value, FloatComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Float2ShortSortedMap synchronize(Float2ShortSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Float2ShortSortedMap synchronize(Float2ShortSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Float2ShortSortedMap unmodifiable(Float2ShortSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Float2ShortMaps.UnmodifiableMap
    implements Float2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ShortSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Float2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public FloatComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Float2ShortMap.Entry> float2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2ShortEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Short>> entrySet()
    {
      return float2ShortEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2ShortSortedMap subMap(float from, float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Float2ShortSortedMap headMap(float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Float2ShortSortedMap tailMap(float from)
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
    
    public Float2ShortSortedMap subMap(Float from, Float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Float2ShortSortedMap headMap(Float local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Float2ShortSortedMap tailMap(Float from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Float2ShortMaps.SynchronizedMap
    implements Float2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ShortSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Float2ShortSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Float2ShortSortedMap local_m)
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
    
    public ObjectSortedSet<Float2ShortMap.Entry> float2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2ShortEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Short>> entrySet()
    {
      return float2ShortEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2ShortSortedMap subMap(float from, float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Float2ShortSortedMap headMap(float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Float2ShortSortedMap tailMap(float from)
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
    
    public Float2ShortSortedMap subMap(Float from, Float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Float2ShortSortedMap headMap(Float local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Float2ShortSortedMap tailMap(Float from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Float2ShortMaps.Singleton
    implements Float2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatComparator comparator;
    
    protected Singleton(float key, short value, FloatComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(float key, short value)
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
    
    public ObjectSortedSet<Float2ShortMap.Entry> float2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Float2ShortMaps.Singleton.SingletonEntry(this), Float2ShortSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Short>> entrySet()
    {
      return float2ShortEntrySet();
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSortedSets.singleton(this.key, this.comparator);
      }
      return (FloatSortedSet)this.keys;
    }
    
    public Float2ShortSortedMap subMap(float from, float local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Float2ShortSortedMap headMap(float local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Float2ShortSortedMap tailMap(float from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public float firstFloatKey()
    {
      return this.key;
    }
    
    public float lastFloatKey()
    {
      return this.key;
    }
    
    public Float2ShortSortedMap headMap(Float oto)
    {
      return headMap(oto.floatValue());
    }
    
    public Float2ShortSortedMap tailMap(Float ofrom)
    {
      return tailMap(ofrom.floatValue());
    }
    
    public Float2ShortSortedMap subMap(Float ofrom, Float oto)
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
    extends Float2ShortMaps.EmptyMap
    implements Float2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public FloatComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Float2ShortMap.Entry> float2ShortEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Float, Short>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet keySet()
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public Float2ShortSortedMap subMap(float from, float local_to)
    {
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Float2ShortSortedMap headMap(float local_to)
    {
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Float2ShortSortedMap tailMap(float from)
    {
      return Float2ShortSortedMaps.EMPTY_MAP;
    }
    
    public float firstFloatKey()
    {
      throw new NoSuchElementException();
    }
    
    public float lastFloatKey()
    {
      throw new NoSuchElementException();
    }
    
    public Float2ShortSortedMap headMap(Float oto)
    {
      return headMap(oto.floatValue());
    }
    
    public Float2ShortSortedMap tailMap(Float ofrom)
    {
      return tailMap(ofrom.floatValue());
    }
    
    public Float2ShortSortedMap subMap(Float ofrom, Float oto)
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ShortSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */