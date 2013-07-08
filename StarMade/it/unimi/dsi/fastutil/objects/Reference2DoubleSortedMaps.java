package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Reference2DoubleSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator(Comparator<K> comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<K, ?> local_x, Map.Entry<K, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static <K> Reference2DoubleSortedMap<K> singleton(K key, Double value)
  {
    return new Singleton(key, value.doubleValue());
  }
  
  public static <K> Reference2DoubleSortedMap<K> singleton(K key, Double value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value.doubleValue(), comparator);
  }
  
  public static <K> Reference2DoubleSortedMap<K> singleton(K key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2DoubleSortedMap<K> singleton(K key, double value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <K> Reference2DoubleSortedMap<K> synchronize(Reference2DoubleSortedMap<K> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <K> Reference2DoubleSortedMap<K> synchronize(Reference2DoubleSortedMap<K> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <K> Reference2DoubleSortedMap<K> unmodifiable(Reference2DoubleSortedMap<K> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<K>
    extends Reference2DoubleMaps.UnmodifiableMap<K>
    implements Reference2DoubleSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2DoubleSortedMap<K> sortedMap;
    
    protected UnmodifiableSortedMap(Reference2DoubleSortedMap<K> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.reference2DoubleEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Double>> entrySet()
    {
      return reference2DoubleEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2DoubleSortedMap<K> subMap(K from, K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Reference2DoubleSortedMap<K> headMap(K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Reference2DoubleSortedMap<K> tailMap(K from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public K firstKey()
    {
      return this.sortedMap.firstKey();
    }
    
    public K lastKey()
    {
      return this.sortedMap.lastKey();
    }
  }
  
  public static class SynchronizedSortedMap<K>
    extends Reference2DoubleMaps.SynchronizedMap<K>
    implements Reference2DoubleSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2DoubleSortedMap<K> sortedMap;
    
    protected SynchronizedSortedMap(Reference2DoubleSortedMap<K> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Reference2DoubleSortedMap<K> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public Comparator<? super K> comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.reference2DoubleEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Double>> entrySet()
    {
      return reference2DoubleEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2DoubleSortedMap<K> subMap(K from, K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Reference2DoubleSortedMap<K> headMap(K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Reference2DoubleSortedMap<K> tailMap(K from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public K firstKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstKey();
      }
    }
    
    public K lastKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastKey();
      }
    }
  }
  
  public static class Singleton<K>
    extends Reference2DoubleMaps.Singleton<K>
    implements Reference2DoubleSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Comparator<? super K> comparator;
    
    protected Singleton(K key, double value, Comparator<? super K> comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(K key, double value)
    {
      this(key, value, null);
    }
    
    final int compare(K local_k1, K local_k2)
    {
      return this.comparator == null ? ((Comparable)local_k1).compareTo(local_k2) : this.comparator.compare(local_k1, local_k2);
    }
    
    public Comparator<? super K> comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Reference2DoubleMaps.Singleton.SingletonEntry(this), Reference2DoubleSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Double>> entrySet()
    {
      return reference2DoubleEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.singleton(this.key, this.comparator);
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2DoubleSortedMap<K> subMap(K from, K local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Reference2DoubleSortedMap<K> headMap(K local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Reference2DoubleSortedMap<K> tailMap(K from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public K firstKey()
    {
      return this.key;
    }
    
    public K lastKey()
    {
      return this.key;
    }
  }
  
  public static class EmptySortedMap<K>
    extends Reference2DoubleMaps.EmptyMap<K>
    implements Reference2DoubleSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<K, Double>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      return ReferenceSortedSets.EMPTY_SET;
    }
    
    public Reference2DoubleSortedMap<K> subMap(K from, K local_to)
    {
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Reference2DoubleSortedMap<K> headMap(K local_to)
    {
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Reference2DoubleSortedMap<K> tailMap(K from)
    {
      return Reference2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public K firstKey()
    {
      throw new NoSuchElementException();
    }
    
    public K lastKey()
    {
      throw new NoSuchElementException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */