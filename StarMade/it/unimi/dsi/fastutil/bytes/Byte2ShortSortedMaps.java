package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2ShortSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(ByteComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Byte, ?> local_x, Map.Entry<Byte, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Byte2ShortSortedMap singleton(Byte key, Short value)
  {
    return new Singleton(key.byteValue(), value.shortValue());
  }
  
  public static Byte2ShortSortedMap singleton(Byte key, Short value, ByteComparator comparator)
  {
    return new Singleton(key.byteValue(), value.shortValue(), comparator);
  }
  
  public static Byte2ShortSortedMap singleton(byte key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2ShortSortedMap singleton(byte key, short value, ByteComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Byte2ShortSortedMap synchronize(Byte2ShortSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Byte2ShortSortedMap synchronize(Byte2ShortSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Byte2ShortSortedMap unmodifiable(Byte2ShortSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Byte2ShortMaps.UnmodifiableMap
    implements Byte2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ShortSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Byte2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ByteComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2ShortEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Short>> entrySet()
    {
      return byte2ShortEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ShortSortedMap subMap(byte from, byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2ShortSortedMap headMap(byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2ShortSortedMap tailMap(byte from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public byte firstByteKey()
    {
      return this.sortedMap.firstByteKey();
    }
    
    public byte lastByteKey()
    {
      return this.sortedMap.lastByteKey();
    }
    
    public Byte firstKey()
    {
      return (Byte)this.sortedMap.firstKey();
    }
    
    public Byte lastKey()
    {
      return (Byte)this.sortedMap.lastKey();
    }
    
    public Byte2ShortSortedMap subMap(Byte from, Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2ShortSortedMap headMap(Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2ShortSortedMap tailMap(Byte from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Byte2ShortMaps.SynchronizedMap
    implements Byte2ShortSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ShortSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Byte2ShortSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Byte2ShortSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ByteComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2ShortEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Short>> entrySet()
    {
      return byte2ShortEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ShortSortedMap subMap(byte from, byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2ShortSortedMap headMap(byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2ShortSortedMap tailMap(byte from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public byte firstByteKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstByteKey();
      }
    }
    
    public byte lastByteKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastByteKey();
      }
    }
    
    public Byte firstKey()
    {
      synchronized (this.sync)
      {
        return (Byte)this.sortedMap.firstKey();
      }
    }
    
    public Byte lastKey()
    {
      synchronized (this.sync)
      {
        return (Byte)this.sortedMap.lastKey();
      }
    }
    
    public Byte2ShortSortedMap subMap(Byte from, Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2ShortSortedMap headMap(Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2ShortSortedMap tailMap(Byte from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Byte2ShortMaps.Singleton
    implements Byte2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteComparator comparator;
    
    protected Singleton(byte key, short value, ByteComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(byte key, short value)
    {
      this(key, value, null);
    }
    
    final int compare(byte local_k1, byte local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public ByteComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Byte2ShortMaps.Singleton.SingletonEntry(this), Byte2ShortSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Short>> entrySet()
    {
      return byte2ShortEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.singleton(this.key, this.comparator);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ShortSortedMap subMap(byte from, byte local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ShortSortedMap headMap(byte local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ShortSortedMap tailMap(byte from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      return this.key;
    }
    
    public byte lastByteKey()
    {
      return this.key;
    }
    
    public Byte2ShortSortedMap headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2ShortSortedMap tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2ShortSortedMap subMap(Byte ofrom, Byte oto)
    {
      return subMap(ofrom.byteValue(), oto.byteValue());
    }
    
    public Byte firstKey()
    {
      return Byte.valueOf(firstByteKey());
    }
    
    public Byte lastKey()
    {
      return Byte.valueOf(lastByteKey());
    }
  }
  
  public static class EmptySortedMap
    extends Byte2ShortMaps.EmptyMap
    implements Byte2ShortSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public ByteComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Short>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet keySet()
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public Byte2ShortSortedMap subMap(byte from, byte local_to)
    {
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ShortSortedMap headMap(byte local_to)
    {
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ShortSortedMap tailMap(byte from)
    {
      return Byte2ShortSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public byte lastByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public Byte2ShortSortedMap headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2ShortSortedMap tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2ShortSortedMap subMap(Byte ofrom, Byte oto)
    {
      return subMap(ofrom.byteValue(), oto.byteValue());
    }
    
    public Byte firstKey()
    {
      return Byte.valueOf(firstByteKey());
    }
    
    public Byte lastKey()
    {
      return Byte.valueOf(lastByteKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */