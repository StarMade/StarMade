package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2ReferenceSortedMaps
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
  
  public static <V> Byte2ReferenceSortedMap<V> singleton(Byte key, V value)
  {
    return new Singleton(key.byteValue(), value);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> singleton(Byte key, V value, ByteComparator comparator)
  {
    return new Singleton(key.byteValue(), value, comparator);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> singleton(byte key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> singleton(byte key, V value, ByteComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> synchronize(Byte2ReferenceSortedMap<V> local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> synchronize(Byte2ReferenceSortedMap<V> local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static <V> Byte2ReferenceSortedMap<V> unmodifiable(Byte2ReferenceSortedMap<V> local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap<V>
    extends Byte2ReferenceMaps.UnmodifiableMap<V>
    implements Byte2ReferenceSortedMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ReferenceSortedMap<V> sortedMap;
    
    protected UnmodifiableSortedMap(Byte2ReferenceSortedMap<V> local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ByteComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2ReferenceEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
    {
      return byte2ReferenceEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ReferenceSortedMap<V> subMap(byte from, byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2ReferenceSortedMap<V> headMap(byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(byte from)
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
    
    public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2ReferenceSortedMap<V> headMap(Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(Byte from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap<V>
    extends Byte2ReferenceMaps.SynchronizedMap<V>
    implements Byte2ReferenceSortedMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ReferenceSortedMap<V> sortedMap;
    
    protected SynchronizedSortedMap(Byte2ReferenceSortedMap<V> local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Byte2ReferenceSortedMap<V> local_m)
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
    
    public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2ReferenceEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
    {
      return byte2ReferenceEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ReferenceSortedMap<V> subMap(byte from, byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2ReferenceSortedMap<V> headMap(byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(byte from)
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
    
    public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2ReferenceSortedMap<V> headMap(Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(Byte from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton<V>
    extends Byte2ReferenceMaps.Singleton<V>
    implements Byte2ReferenceSortedMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteComparator comparator;
    
    protected Singleton(byte key, V value, ByteComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(byte key, V value)
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
    
    public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Byte2ReferenceMaps.Singleton.SingletonEntry(this), Byte2ReferenceSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
    {
      return byte2ReferenceEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.singleton(this.key, this.comparator);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2ReferenceSortedMap<V> subMap(byte from, byte local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ReferenceSortedMap<V> headMap(byte local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(byte from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      return this.key;
    }
    
    public byte lastByteKey()
    {
      return this.key;
    }
    
    public Byte2ReferenceSortedMap<V> headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2ReferenceSortedMap<V> subMap(Byte ofrom, Byte oto)
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
  
  public static class EmptySortedMap<V>
    extends Byte2ReferenceMaps.EmptyMap<V>
    implements Byte2ReferenceSortedMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public ByteComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet keySet()
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public Byte2ReferenceSortedMap<V> subMap(byte from, byte local_to)
    {
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ReferenceSortedMap<V> headMap(byte local_to)
    {
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(byte from)
    {
      return Byte2ReferenceSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public byte lastByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public Byte2ReferenceSortedMap<V> headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2ReferenceSortedMap<V> tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2ReferenceSortedMap<V> subMap(Byte ofrom, Byte oto)
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */