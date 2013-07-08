package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Int2ShortSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Integer, ?>> entryComparator(IntComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Integer, ?> local_x, Map.Entry<Integer, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Int2ShortSortedMap singleton(Integer key, Short value)
  {
    return new Singleton(key.intValue(), value.shortValue());
  }
  
  public static Int2ShortSortedMap singleton(Integer key, Short value, IntComparator comparator)
  {
    return new Singleton(key.intValue(), value.shortValue(), comparator);
  }
  
  public static Int2ShortSortedMap singleton(int key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Int2ShortSortedMap singleton(int key, short value, IntComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Int2ShortSortedMap synchronize(Int2ShortSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Int2ShortSortedMap synchronize(Int2ShortSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Int2ShortSortedMap unmodifiable(Int2ShortSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Int2ShortMaps.UnmodifiableMap
    implements Int2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2ShortSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Int2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public IntComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.int2ShortEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
    {
      return int2ShortEntrySet();
    }
    
    public IntSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (IntSortedSet)this.keys;
    }
    
    public Int2ShortSortedMap subMap(int from, int local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Int2ShortSortedMap headMap(int local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Int2ShortSortedMap tailMap(int from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public int firstIntKey()
    {
      return this.sortedMap.firstIntKey();
    }
    
    public int lastIntKey()
    {
      return this.sortedMap.lastIntKey();
    }
    
    public Integer firstKey()
    {
      return (Integer)this.sortedMap.firstKey();
    }
    
    public Integer lastKey()
    {
      return (Integer)this.sortedMap.lastKey();
    }
    
    public Int2ShortSortedMap subMap(Integer from, Integer local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Int2ShortSortedMap headMap(Integer local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Int2ShortSortedMap tailMap(Integer from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Int2ShortMaps.SynchronizedMap
    implements Int2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2ShortSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Int2ShortSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Int2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public IntComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.int2ShortEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
    {
      return int2ShortEntrySet();
    }
    
    public IntSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (IntSortedSet)this.keys;
    }
    
    public Int2ShortSortedMap subMap(int from, int local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Int2ShortSortedMap headMap(int local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Int2ShortSortedMap tailMap(int from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public int firstIntKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstIntKey();
      }
    }
    
    public int lastIntKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastIntKey();
      }
    }
    
    public Integer firstKey()
    {
      synchronized (this.sync)
      {
        return (Integer)this.sortedMap.firstKey();
      }
    }
    
    public Integer lastKey()
    {
      synchronized (this.sync)
      {
        return (Integer)this.sortedMap.lastKey();
      }
    }
    
    public Int2ShortSortedMap subMap(Integer from, Integer local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Int2ShortSortedMap headMap(Integer local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Int2ShortSortedMap tailMap(Integer from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Int2ShortMaps.Singleton
    implements Int2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntComparator comparator;
    
    protected Singleton(int key, short value, IntComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(int key, short value)
    {
      this(key, value, null);
    }
    
    final int compare(int local_k1, int local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public IntComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Int2ShortMaps.Singleton.SingletonEntry(this), Int2ShortSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
    {
      return int2ShortEntrySet();
    }
    
    public IntSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSortedSets.singleton(this.key, this.comparator);
      }
      return (IntSortedSet)this.keys;
    }
    
    public Int2ShortSortedMap subMap(int from, int local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Int2ShortSortedMap headMap(int local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Int2ShortSortedMap tailMap(int from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public int firstIntKey()
    {
      return this.key;
    }
    
    public int lastIntKey()
    {
      return this.key;
    }
    
    public Int2ShortSortedMap headMap(Integer oto)
    {
      return headMap(oto.intValue());
    }
    
    public Int2ShortSortedMap tailMap(Integer ofrom)
    {
      return tailMap(ofrom.intValue());
    }
    
    public Int2ShortSortedMap subMap(Integer ofrom, Integer oto)
    {
      return subMap(ofrom.intValue(), oto.intValue());
    }
    
    public Integer firstKey()
    {
      return Integer.valueOf(firstIntKey());
    }
    
    public Integer lastKey()
    {
      return Integer.valueOf(lastIntKey());
    }
  }
  
  public static class EmptySortedMap
    extends Int2ShortMaps.EmptyMap
    implements Int2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public IntComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet keySet()
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public Int2ShortSortedMap subMap(int from, int local_to)
    {
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Int2ShortSortedMap headMap(int local_to)
    {
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Int2ShortSortedMap tailMap(int from)
    {
      return Int2ShortSortedMaps.EMPTY_MAP;
    }
    
    public int firstIntKey()
    {
      throw new NoSuchElementException();
    }
    
    public int lastIntKey()
    {
      throw new NoSuchElementException();
    }
    
    public Int2ShortSortedMap headMap(Integer oto)
    {
      return headMap(oto.intValue());
    }
    
    public Int2ShortSortedMap tailMap(Integer ofrom)
    {
      return tailMap(ofrom.intValue());
    }
    
    public Int2ShortSortedMap subMap(Integer ofrom, Integer oto)
    {
      return subMap(ofrom.intValue(), oto.intValue());
    }
    
    public Integer firstKey()
    {
      return Integer.valueOf(firstIntKey());
    }
    
    public Integer lastKey()
    {
      return Integer.valueOf(lastIntKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */