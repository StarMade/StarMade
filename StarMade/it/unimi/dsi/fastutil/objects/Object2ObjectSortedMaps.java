package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2ObjectSortedMaps
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
  
  public static <K, V> Object2ObjectSortedMap<K, V> singleton(K key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <K, V> Object2ObjectSortedMap<K, V> singleton(K key, V value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <K, V> Object2ObjectSortedMap<K, V> synchronize(Object2ObjectSortedMap<K, V> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <K, V> Object2ObjectSortedMap<K, V> synchronize(Object2ObjectSortedMap<K, V> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <K, V> Object2ObjectSortedMap<K, V> unmodifiable(Object2ObjectSortedMap<K, V> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<K, V>
    extends Object2ObjectMaps.UnmodifiableMap<K, V>
    implements Object2ObjectSortedMap<K, V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ObjectSortedMap<K, V> sortedMap;
    
    protected UnmodifiableSortedMap(Object2ObjectSortedMap<K, V> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.object2ObjectEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, V>> entrySet()
    {
      return object2ObjectEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ObjectSortedMap<K, V> subMap(K from, K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Object2ObjectSortedMap<K, V> headMap(K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Object2ObjectSortedMap<K, V> tailMap(K from)
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
  
  public static class SynchronizedSortedMap<K, V>
    extends Object2ObjectMaps.SynchronizedMap<K, V>
    implements Object2ObjectSortedMap<K, V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ObjectSortedMap<K, V> sortedMap;
    
    protected SynchronizedSortedMap(Object2ObjectSortedMap<K, V> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Object2ObjectSortedMap<K, V> local_m)
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
    
    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.object2ObjectEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, V>> entrySet()
    {
      return object2ObjectEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ObjectSortedMap<K, V> subMap(K from, K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Object2ObjectSortedMap<K, V> headMap(K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Object2ObjectSortedMap<K, V> tailMap(K from)
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
  
  public static class Singleton<K, V>
    extends Object2ObjectMaps.Singleton<K, V>
    implements Object2ObjectSortedMap<K, V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Comparator<? super K> comparator;
    
    protected Singleton(K key, V value, Comparator<? super K> comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(K key, V value)
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
    
    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Object2ObjectMaps.Singleton.SingletonEntry(this), Object2ObjectSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, V>> entrySet()
    {
      return object2ObjectEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.singleton(this.key, this.comparator);
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ObjectSortedMap<K, V> subMap(K from, K local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Object2ObjectSortedMaps.EMPTY_MAP;
    }
    
    public Object2ObjectSortedMap<K, V> headMap(K local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Object2ObjectSortedMaps.EMPTY_MAP;
    }
    
    public Object2ObjectSortedMap<K, V> tailMap(K from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Object2ObjectSortedMaps.EMPTY_MAP;
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
  
  public static class EmptySortedMap<K, V>
    extends Object2ObjectMaps.EmptyMap<K, V>
    implements Object2ObjectSortedMap<K, V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<K, V>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> keySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public Object2ObjectSortedMap<K, V> subMap(K from, K local_to)
    {
      return Object2ObjectSortedMaps.EMPTY_MAP;
    }
    
    public Object2ObjectSortedMap<K, V> headMap(K local_to)
    {
      return Object2ObjectSortedMaps.EMPTY_MAP;
    }
    
    public Object2ObjectSortedMap<K, V> tailMap(K from)
    {
      return Object2ObjectSortedMaps.EMPTY_MAP;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */