package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Reference2LongSortedMaps
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
  
  public static <K> Reference2LongSortedMap<K> singleton(K key, Long value)
  {
    return new Singleton(key, value.longValue());
  }
  
  public static <K> Reference2LongSortedMap<K> singleton(K key, Long value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value.longValue(), comparator);
  }
  
  public static <K> Reference2LongSortedMap<K> singleton(K key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2LongSortedMap<K> singleton(K key, long value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <K> Reference2LongSortedMap<K> synchronize(Reference2LongSortedMap<K> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <K> Reference2LongSortedMap<K> synchronize(Reference2LongSortedMap<K> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <K> Reference2LongSortedMap<K> unmodifiable(Reference2LongSortedMap<K> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<K>
    extends Reference2LongMaps.UnmodifiableMap<K>
    implements Reference2LongSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2LongSortedMap<K> sortedMap;
    
    protected UnmodifiableSortedMap(Reference2LongSortedMap<K> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Reference2LongMap.Entry<K>> reference2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.reference2LongEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Long>> entrySet()
    {
      return reference2LongEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2LongSortedMap<K> subMap(K from, K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Reference2LongSortedMap<K> headMap(K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Reference2LongSortedMap<K> tailMap(K from)
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
    extends Reference2LongMaps.SynchronizedMap<K>
    implements Reference2LongSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2LongSortedMap<K> sortedMap;
    
    protected SynchronizedSortedMap(Reference2LongSortedMap<K> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Reference2LongSortedMap<K> local_m)
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
    
    public ObjectSortedSet<Reference2LongMap.Entry<K>> reference2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.reference2LongEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Long>> entrySet()
    {
      return reference2LongEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2LongSortedMap<K> subMap(K from, K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Reference2LongSortedMap<K> headMap(K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Reference2LongSortedMap<K> tailMap(K from)
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
    extends Reference2LongMaps.Singleton<K>
    implements Reference2LongSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Comparator<? super K> comparator;
    
    protected Singleton(K key, long value, Comparator<? super K> comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(K key, long value)
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
    
    public ObjectSortedSet<Reference2LongMap.Entry<K>> reference2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Reference2LongMaps.Singleton.SingletonEntry(this), Reference2LongSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Long>> entrySet()
    {
      return reference2LongEntrySet();
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSortedSets.singleton(this.key, this.comparator);
      }
      return (ReferenceSortedSet)this.keys;
    }
    
    public Reference2LongSortedMap<K> subMap(K from, K local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Reference2LongSortedMaps.EMPTY_MAP;
    }
    
    public Reference2LongSortedMap<K> headMap(K local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Reference2LongSortedMaps.EMPTY_MAP;
    }
    
    public Reference2LongSortedMap<K> tailMap(K from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Reference2LongSortedMaps.EMPTY_MAP;
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
    extends Reference2LongMaps.EmptyMap<K>
    implements Reference2LongSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Reference2LongMap.Entry<K>> reference2LongEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<K, Long>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ReferenceSortedSet<K> keySet()
    {
      return ReferenceSortedSets.EMPTY_SET;
    }
    
    public Reference2LongSortedMap<K> subMap(K from, K local_to)
    {
      return Reference2LongSortedMaps.EMPTY_MAP;
    }
    
    public Reference2LongSortedMap<K> headMap(K local_to)
    {
      return Reference2LongSortedMaps.EMPTY_MAP;
    }
    
    public Reference2LongSortedMap<K> tailMap(K from)
    {
      return Reference2LongSortedMaps.EMPTY_MAP;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2LongSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */