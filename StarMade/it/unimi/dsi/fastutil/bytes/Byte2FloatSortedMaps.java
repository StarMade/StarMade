package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2FloatSortedMaps
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
  
  public static Byte2FloatSortedMap singleton(Byte key, Float value)
  {
    return new Singleton(key.byteValue(), value.floatValue());
  }
  
  public static Byte2FloatSortedMap singleton(Byte key, Float value, ByteComparator comparator)
  {
    return new Singleton(key.byteValue(), value.floatValue(), comparator);
  }
  
  public static Byte2FloatSortedMap singleton(byte key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2FloatSortedMap singleton(byte key, float value, ByteComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Byte2FloatSortedMap synchronize(Byte2FloatSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Byte2FloatSortedMap synchronize(Byte2FloatSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Byte2FloatSortedMap unmodifiable(Byte2FloatSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Byte2FloatMaps.UnmodifiableMap
    implements Byte2FloatSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Byte2FloatSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public ByteComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2FloatEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
    {
      return byte2FloatEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2FloatSortedMap subMap(byte from, byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2FloatSortedMap headMap(byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2FloatSortedMap tailMap(byte from)
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
    
    public Byte2FloatSortedMap subMap(Byte from, Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Byte2FloatSortedMap headMap(Byte local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Byte2FloatSortedMap tailMap(Byte from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Byte2FloatMaps.SynchronizedMap
    implements Byte2FloatSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Byte2FloatSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Byte2FloatSortedMap local_m)
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
    
    public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2FloatEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
    {
      return byte2FloatEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2FloatSortedMap subMap(byte from, byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2FloatSortedMap headMap(byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2FloatSortedMap tailMap(byte from)
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
    
    public Byte2FloatSortedMap subMap(Byte from, Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Byte2FloatSortedMap headMap(Byte local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Byte2FloatSortedMap tailMap(Byte from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Byte2FloatMaps.Singleton
    implements Byte2FloatSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteComparator comparator;
    
    protected Singleton(byte key, float value, ByteComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(byte key, float value)
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
    
    public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Byte2FloatMaps.Singleton.SingletonEntry(this), Byte2FloatSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
    {
      return byte2FloatEntrySet();
    }
    
    public ByteSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSortedSets.singleton(this.key, this.comparator);
      }
      return (ByteSortedSet)this.keys;
    }
    
    public Byte2FloatSortedMap subMap(byte from, byte local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public Byte2FloatSortedMap headMap(byte local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public Byte2FloatSortedMap tailMap(byte from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      return this.key;
    }
    
    public byte lastByteKey()
    {
      return this.key;
    }
    
    public Byte2FloatSortedMap headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2FloatSortedMap tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2FloatSortedMap subMap(Byte ofrom, Byte oto)
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
    extends Byte2FloatMaps.EmptyMap
    implements Byte2FloatSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public ByteComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet keySet()
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public Byte2FloatSortedMap subMap(byte from, byte local_to)
    {
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public Byte2FloatSortedMap headMap(byte local_to)
    {
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public Byte2FloatSortedMap tailMap(byte from)
    {
      return Byte2FloatSortedMaps.EMPTY_MAP;
    }
    
    public byte firstByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public byte lastByteKey()
    {
      throw new NoSuchElementException();
    }
    
    public Byte2FloatSortedMap headMap(Byte oto)
    {
      return headMap(oto.byteValue());
    }
    
    public Byte2FloatSortedMap tailMap(Byte ofrom)
    {
      return tailMap(ofrom.byteValue());
    }
    
    public Byte2FloatSortedMap subMap(Byte ofrom, Byte oto)
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */