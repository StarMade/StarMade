package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2ByteSortedMaps
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
  
  public static <K> Object2ByteSortedMap<K> singleton(K key, Byte value)
  {
    return new Singleton(key, value.byteValue());
  }
  
  public static <K> Object2ByteSortedMap<K> singleton(K key, Byte value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value.byteValue(), comparator);
  }
  
  public static <K> Object2ByteSortedMap<K> singleton(K key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2ByteSortedMap<K> singleton(K key, byte value, Comparator<? super K> comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <K> Object2ByteSortedMap<K> synchronize(Object2ByteSortedMap<K> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <K> Object2ByteSortedMap<K> synchronize(Object2ByteSortedMap<K> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <K> Object2ByteSortedMap<K> unmodifiable(Object2ByteSortedMap<K> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<K>
    extends Object2ByteMaps.UnmodifiableMap<K>
    implements Object2ByteSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ByteSortedMap<K> sortedMap;
    
    protected UnmodifiableSortedMap(Object2ByteSortedMap<K> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.object2ByteEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Byte>> entrySet()
    {
      return object2ByteEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ByteSortedMap<K> subMap(K from, K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Object2ByteSortedMap<K> headMap(K local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Object2ByteSortedMap<K> tailMap(K from)
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
    extends Object2ByteMaps.SynchronizedMap<K>
    implements Object2ByteSortedMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ByteSortedMap<K> sortedMap;
    
    protected SynchronizedSortedMap(Object2ByteSortedMap<K> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Object2ByteSortedMap<K> local_m)
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
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.object2ByteEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Byte>> entrySet()
    {
      return object2ByteEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ByteSortedMap<K> subMap(K from, K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Object2ByteSortedMap<K> headMap(K local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Object2ByteSortedMap<K> tailMap(K from)
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
    extends Object2ByteMaps.Singleton<K>
    implements Object2ByteSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Comparator<? super K> comparator;
    
    protected Singleton(K key, byte value, Comparator<? super K> comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(K key, byte value)
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
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Object2ByteMaps.Singleton.SingletonEntry(this), Object2ByteSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<K, Byte>> entrySet()
    {
      return object2ByteEntrySet();
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSortedSets.singleton(this.key, this.comparator);
      }
      return (ObjectSortedSet)this.keys;
    }
    
    public Object2ByteSortedMap<K> subMap(K from, K local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Object2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Object2ByteSortedMap<K> headMap(K local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Object2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Object2ByteSortedMap<K> tailMap(K from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Object2ByteSortedMaps.EMPTY_MAP;
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
    extends Object2ByteMaps.EmptyMap<K>
    implements Object2ByteSortedMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<K, Byte>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> keySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public Object2ByteSortedMap<K> subMap(K from, K local_to)
    {
      return Object2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Object2ByteSortedMap<K> headMap(K local_to)
    {
      return Object2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Object2ByteSortedMap<K> tailMap(K from)
    {
      return Object2ByteSortedMaps.EMPTY_MAP;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ByteSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */