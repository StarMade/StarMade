package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Long2BooleanSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Long, ?>> entryComparator(LongComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Long, ?> local_x, Map.Entry<Long, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Long2BooleanSortedMap singleton(Long key, Boolean value)
  {
    return new Singleton(key.longValue(), value.booleanValue());
  }
  
  public static Long2BooleanSortedMap singleton(Long key, Boolean value, LongComparator comparator)
  {
    return new Singleton(key.longValue(), value.booleanValue(), comparator);
  }
  
  public static Long2BooleanSortedMap singleton(long key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Long2BooleanSortedMap singleton(long key, boolean value, LongComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Long2BooleanSortedMap synchronize(Long2BooleanSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Long2BooleanSortedMap synchronize(Long2BooleanSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Long2BooleanSortedMap unmodifiable(Long2BooleanSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Long2BooleanMaps.UnmodifiableMap
    implements Long2BooleanSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2BooleanSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Long2BooleanSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public LongComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Long2BooleanMap.Entry> long2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.long2BooleanEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet()
    {
      return long2BooleanEntrySet();
    }
    
    public LongSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (LongSortedSet)this.keys;
    }
    
    public Long2BooleanSortedMap subMap(long from, long local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Long2BooleanSortedMap headMap(long local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Long2BooleanSortedMap tailMap(long from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public long firstLongKey()
    {
      return this.sortedMap.firstLongKey();
    }
    
    public long lastLongKey()
    {
      return this.sortedMap.lastLongKey();
    }
    
    public Long firstKey()
    {
      return (Long)this.sortedMap.firstKey();
    }
    
    public Long lastKey()
    {
      return (Long)this.sortedMap.lastKey();
    }
    
    public Long2BooleanSortedMap subMap(Long from, Long local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Long2BooleanSortedMap headMap(Long local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Long2BooleanSortedMap tailMap(Long from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Long2BooleanMaps.SynchronizedMap
    implements Long2BooleanSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2BooleanSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Long2BooleanSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Long2BooleanSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public LongComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Long2BooleanMap.Entry> long2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.long2BooleanEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet()
    {
      return long2BooleanEntrySet();
    }
    
    public LongSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (LongSortedSet)this.keys;
    }
    
    public Long2BooleanSortedMap subMap(long from, long local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Long2BooleanSortedMap headMap(long local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Long2BooleanSortedMap tailMap(long from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public long firstLongKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstLongKey();
      }
    }
    
    public long lastLongKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastLongKey();
      }
    }
    
    public Long firstKey()
    {
      synchronized (this.sync)
      {
        return (Long)this.sortedMap.firstKey();
      }
    }
    
    public Long lastKey()
    {
      synchronized (this.sync)
      {
        return (Long)this.sortedMap.lastKey();
      }
    }
    
    public Long2BooleanSortedMap subMap(Long from, Long local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Long2BooleanSortedMap headMap(Long local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Long2BooleanSortedMap tailMap(Long from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Long2BooleanMaps.Singleton
    implements Long2BooleanSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongComparator comparator;
    
    protected Singleton(long key, boolean value, LongComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(long key, boolean value)
    {
      this(key, value, null);
    }
    
    final int compare(long local_k1, long local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public LongComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Long2BooleanMap.Entry> long2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Long2BooleanMaps.Singleton.SingletonEntry(this), Long2BooleanSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet()
    {
      return long2BooleanEntrySet();
    }
    
    public LongSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSortedSets.singleton(this.key, this.comparator);
      }
      return (LongSortedSet)this.keys;
    }
    
    public Long2BooleanSortedMap subMap(long from, long local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public Long2BooleanSortedMap headMap(long local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public Long2BooleanSortedMap tailMap(long from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public long firstLongKey()
    {
      return this.key;
    }
    
    public long lastLongKey()
    {
      return this.key;
    }
    
    public Long2BooleanSortedMap headMap(Long oto)
    {
      return headMap(oto.longValue());
    }
    
    public Long2BooleanSortedMap tailMap(Long ofrom)
    {
      return tailMap(ofrom.longValue());
    }
    
    public Long2BooleanSortedMap subMap(Long ofrom, Long oto)
    {
      return subMap(ofrom.longValue(), oto.longValue());
    }
    
    public Long firstKey()
    {
      return Long.valueOf(firstLongKey());
    }
    
    public Long lastKey()
    {
      return Long.valueOf(lastLongKey());
    }
  }
  
  public static class EmptySortedMap
    extends Long2BooleanMaps.EmptyMap
    implements Long2BooleanSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public LongComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Long2BooleanMap.Entry> long2BooleanEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet keySet()
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public Long2BooleanSortedMap subMap(long from, long local_to)
    {
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public Long2BooleanSortedMap headMap(long local_to)
    {
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public Long2BooleanSortedMap tailMap(long from)
    {
      return Long2BooleanSortedMaps.EMPTY_MAP;
    }
    
    public long firstLongKey()
    {
      throw new NoSuchElementException();
    }
    
    public long lastLongKey()
    {
      throw new NoSuchElementException();
    }
    
    public Long2BooleanSortedMap headMap(Long oto)
    {
      return headMap(oto.longValue());
    }
    
    public Long2BooleanSortedMap tailMap(Long ofrom)
    {
      return tailMap(ofrom.longValue());
    }
    
    public Long2BooleanSortedMap subMap(Long ofrom, Long oto)
    {
      return subMap(ofrom.longValue(), oto.longValue());
    }
    
    public Long firstKey()
    {
      return Long.valueOf(firstLongKey());
    }
    
    public Long lastKey()
    {
      return Long.valueOf(lastLongKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2BooleanSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */