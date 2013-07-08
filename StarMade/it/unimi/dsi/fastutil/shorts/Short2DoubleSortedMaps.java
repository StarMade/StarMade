package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Short2DoubleSortedMaps
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
  
  public static Short2DoubleSortedMap singleton(Short key, Double value)
  {
    return new Singleton(key.shortValue(), value.doubleValue());
  }
  
  public static Short2DoubleSortedMap singleton(Short key, Double value, ShortComparator comparator)
  {
    return new Singleton(key.shortValue(), value.doubleValue(), comparator);
  }
  
  public static Short2DoubleSortedMap singleton(short key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2DoubleSortedMap singleton(short key, double value, ShortComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Short2DoubleSortedMap synchronize(Short2DoubleSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Short2DoubleSortedMap synchronize(Short2DoubleSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Short2DoubleSortedMap unmodifiable(Short2DoubleSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Short2DoubleMaps.UnmodifiableMap
    implements Short2DoubleSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2DoubleSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Short2DoubleSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ShortComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.short2DoubleEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
    {
      return short2DoubleEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2DoubleSortedMap subMap(short from, short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Short2DoubleSortedMap headMap(short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Short2DoubleSortedMap tailMap(short from)
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
    
    public Short2DoubleSortedMap subMap(Short from, Short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Short2DoubleSortedMap headMap(Short local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Short2DoubleSortedMap tailMap(Short from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Short2DoubleMaps.SynchronizedMap
    implements Short2DoubleSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2DoubleSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Short2DoubleSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Short2DoubleSortedMap local_m)
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
    
    public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.short2DoubleEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
    {
      return short2DoubleEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2DoubleSortedMap subMap(short from, short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Short2DoubleSortedMap headMap(short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Short2DoubleSortedMap tailMap(short from)
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
    
    public Short2DoubleSortedMap subMap(Short from, Short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Short2DoubleSortedMap headMap(Short local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Short2DoubleSortedMap tailMap(Short from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Short2DoubleMaps.Singleton
    implements Short2DoubleSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortComparator comparator;
    
    protected Singleton(short key, double value, ShortComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(short key, double value)
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
    
    public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Short2DoubleMaps.Singleton.SingletonEntry(this), Short2DoubleSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
    {
      return short2DoubleEntrySet();
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSortedSets.singleton(this.key, this.comparator);
      }
      return (ShortSortedSet)this.keys;
    }
    
    public Short2DoubleSortedMap subMap(short from, short local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Short2DoubleSortedMap headMap(short local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Short2DoubleSortedMap tailMap(short from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public short firstShortKey()
    {
      return this.key;
    }
    
    public short lastShortKey()
    {
      return this.key;
    }
    
    public Short2DoubleSortedMap headMap(Short oto)
    {
      return headMap(oto.shortValue());
    }
    
    public Short2DoubleSortedMap tailMap(Short ofrom)
    {
      return tailMap(ofrom.shortValue());
    }
    
    public Short2DoubleSortedMap subMap(Short ofrom, Short oto)
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
  
  public static class EmptySortedMap
    extends Short2DoubleMaps.EmptyMap
    implements Short2DoubleSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public ShortComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet keySet()
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public Short2DoubleSortedMap subMap(short from, short local_to)
    {
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Short2DoubleSortedMap headMap(short local_to)
    {
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public Short2DoubleSortedMap tailMap(short from)
    {
      return Short2DoubleSortedMaps.EMPTY_MAP;
    }
    
    public short firstShortKey()
    {
      throw new NoSuchElementException();
    }
    
    public short lastShortKey()
    {
      throw new NoSuchElementException();
    }
    
    public Short2DoubleSortedMap headMap(Short oto)
    {
      return headMap(oto.shortValue());
    }
    
    public Short2DoubleSortedMap tailMap(Short ofrom)
    {
      return tailMap(ofrom.shortValue());
    }
    
    public Short2DoubleSortedMap subMap(Short ofrom, Short oto)
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */