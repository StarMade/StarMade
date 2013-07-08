package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Short2ReferenceSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Short, ?>> entryComparator(ShortComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Short, ?> local_x, Map.Entry<Short, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static <V> Short2ReferenceSortedMap<V> singleton(Short key, V value)
  {
    return new Singleton(key.shortValue(), value);
  }
  
  public static <V> Short2ReferenceSortedMap<V> singleton(Short key, V value, ShortComparator comparator)
  {
    return new Singleton(key.shortValue(), value, comparator);
  }
  
  public static <V> Short2ReferenceSortedMap<V> singleton(short key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Short2ReferenceSortedMap<V> singleton(short key, V value, ShortComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <V> Short2ReferenceSortedMap<V> synchronize(Short2ReferenceSortedMap<V> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <V> Short2ReferenceSortedMap<V> synchronize(Short2ReferenceSortedMap<V> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <V> Short2ReferenceSortedMap<V> unmodifiable(Short2ReferenceSortedMap<V> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<V>
    extends Short2ReferenceMaps.UnmodifiableMap<V>
    implements Short2ReferenceSortedMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ReferenceSortedMap<V> sortedMap;
    
    protected UnmodifiableSortedMap(Short2ReferenceSortedMap<V> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ShortComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.short2ReferenceEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
    {
      return short2ReferenceEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2ReferenceSortedMap<V> subMap(short from, short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Short2ReferenceSortedMap<V> headMap(short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Short2ReferenceSortedMap<V> tailMap(short from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public short firstShortKey()
    {
      return this.sortedMap.firstShortKey();
    }
    
    public short lastShortKey()
    {
      return this.sortedMap.lastShortKey();
    }
    
    public Short firstKey()
    {
      return (Short)this.sortedMap.firstKey();
    }
    
    public Short lastKey()
    {
      return (Short)this.sortedMap.lastKey();
    }
    
    public Short2ReferenceSortedMap<V> subMap(Short from, Short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Short2ReferenceSortedMap<V> headMap(Short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Short2ReferenceSortedMap<V> tailMap(Short from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap<V>
    extends Short2ReferenceMaps.SynchronizedMap<V>
    implements Short2ReferenceSortedMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ReferenceSortedMap<V> sortedMap;
    
    protected SynchronizedSortedMap(Short2ReferenceSortedMap<V> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Short2ReferenceSortedMap<V> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ShortComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.short2ReferenceEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
    {
      return short2ReferenceEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2ReferenceSortedMap<V> subMap(short from, short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Short2ReferenceSortedMap<V> headMap(short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Short2ReferenceSortedMap<V> tailMap(short from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public short firstShortKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstShortKey();
      }
    }
    
    public short lastShortKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastShortKey();
      }
    }
    
    public Short firstKey()
    {
      synchronized (this.sync)
      {
        return (Short)this.sortedMap.firstKey();
      }
    }
    
    public Short lastKey()
    {
      synchronized (this.sync)
      {
        return (Short)this.sortedMap.lastKey();
      }
    }
    
    public Short2ReferenceSortedMap<V> subMap(Short from, Short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Short2ReferenceSortedMap<V> headMap(Short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Short2ReferenceSortedMap<V> tailMap(Short from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton<V>
    extends Short2ReferenceMaps.Singleton<V>
    implements Short2ReferenceSortedMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortComparator comparator;
    
    protected Singleton(short key, V value, ShortComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(short key, V value)
    {
      this(key, value, null);
    }
    
    final int compare(short local_k1, short local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public ShortComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Short2ReferenceMaps.Singleton.SingletonEntry(this), Short2ReferenceSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
    {
      return short2ReferenceEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.singleton(this.key, this.comparator);
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2ReferenceSortedMap<V> subMap(short from, short local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Short2ReferenceSortedMap<V> headMap(short local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Short2ReferenceSortedMap<V> tailMap(short from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public short firstShortKey()
    {
      return this.key;
    }
    
    public short lastShortKey()
    {
      return this.key;
    }
    
    public Short2ReferenceSortedMap<V> headMap(Short oto)
    {
      return headMap(oto.shortValue());
    }
    
    public Short2ReferenceSortedMap<V> tailMap(Short ofrom)
    {
      return tailMap(ofrom.shortValue());
    }
    
    public Short2ReferenceSortedMap<V> subMap(Short ofrom, Short oto)
    {
      return subMap(ofrom.shortValue(), oto.shortValue());
    }
    
    public Short firstKey()
    {
      return Short.valueOf(firstShortKey());
    }
    
    public Short lastKey()
    {
      return Short.valueOf(lastShortKey());
    }
  }
  
  public static class EmptySortedMap<V>
    extends Short2ReferenceMaps.EmptyMap<V>
    implements Short2ReferenceSortedMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public ShortComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet keySet()
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public Short2ReferenceSortedMap<V> subMap(short from, short local_to)
    {
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Short2ReferenceSortedMap<V> headMap(short local_to)
    {
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Short2ReferenceSortedMap<V> tailMap(short from)
    {
      return Short2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public short firstShortKey()
    {
      throw new NoSuchElementException();
    }
    
    public short lastShortKey()
    {
      throw new NoSuchElementException();
    }
    
    public Short2ReferenceSortedMap<V> headMap(Short oto)
    {
      return headMap(oto.shortValue());
    }
    
    public Short2ReferenceSortedMap<V> tailMap(Short ofrom)
    {
      return tailMap(ofrom.shortValue());
    }
    
    public Short2ReferenceSortedMap<V> subMap(Short ofrom, Short oto)
    {
      return subMap(ofrom.shortValue(), oto.shortValue());
    }
    
    public Short firstKey()
    {
      return Short.valueOf(firstShortKey());
    }
    
    public Short lastKey()
    {
      return Short.valueOf(lastShortKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */