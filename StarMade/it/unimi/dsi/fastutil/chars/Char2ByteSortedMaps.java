package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Char2ByteSortedMaps
{
  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
  
  public static Comparator<? super Map.Entry<Character, ?>> entryComparator(CharComparator comparator)
  {
    new Comparator()
    {
      public int compare(Map.Entry<Character, ?> local_x, Map.Entry<Character, ?> local_y)
      {
        return this.val$comparator.compare(local_x.getKey(), local_y.getKey());
      }
    };
  }
  
  public static Char2ByteSortedMap singleton(Character key, Byte value)
  {
    return new Singleton(key.charValue(), value.byteValue());
  }
  
  public static Char2ByteSortedMap singleton(Character key, Byte value, CharComparator comparator)
  {
    return new Singleton(key.charValue(), value.byteValue(), comparator);
  }
  
  public static Char2ByteSortedMap singleton(char key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static Char2ByteSortedMap singleton(char key, byte value, CharComparator comparator)
  {
    return new Singleton(key, value, comparator);
  }
  
  public static Char2ByteSortedMap synchronize(Char2ByteSortedMap local_m)
  {
    return new SynchronizedSortedMap(local_m);
  }
  
  public static Char2ByteSortedMap synchronize(Char2ByteSortedMap local_m, Object sync)
  {
    return new SynchronizedSortedMap(local_m, sync);
  }
  
  public static Char2ByteSortedMap unmodifiable(Char2ByteSortedMap local_m)
  {
    return new UnmodifiableSortedMap(local_m);
  }
  
  public static class UnmodifiableSortedMap
    extends Char2ByteMaps.UnmodifiableMap
    implements Char2ByteSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2ByteSortedMap sortedMap;
    
    protected UnmodifiableSortedMap(Char2ByteSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public CharComparator comparator()
    {
      return this.sortedMap.comparator();
    }
    
    public ObjectSortedSet<Char2ByteMap.Entry> char2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.char2ByteEntrySet());
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Character, Byte>> entrySet()
    {
      return char2ByteEntrySet();
    }
    
    public CharSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSortedSets.unmodifiable(this.sortedMap.keySet());
      }
      return (CharSortedSet)this.keys;
    }
    
    public Char2ByteSortedMap subMap(char from, char local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Char2ByteSortedMap headMap(char local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Char2ByteSortedMap tailMap(char from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
    
    public char firstCharKey()
    {
      return this.sortedMap.firstCharKey();
    }
    
    public char lastCharKey()
    {
      return this.sortedMap.lastCharKey();
    }
    
    public Character firstKey()
    {
      return (Character)this.sortedMap.firstKey();
    }
    
    public Character lastKey()
    {
      return (Character)this.sortedMap.lastKey();
    }
    
    public Char2ByteSortedMap subMap(Character from, Character local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.subMap(from, local_to));
    }
    
    public Char2ByteSortedMap headMap(Character local_to)
    {
      return new UnmodifiableSortedMap(this.sortedMap.headMap(local_to));
    }
    
    public Char2ByteSortedMap tailMap(Character from)
    {
      return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
    }
  }
  
  public static class SynchronizedSortedMap
    extends Char2ByteMaps.SynchronizedMap
    implements Char2ByteSortedMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2ByteSortedMap sortedMap;
    
    protected SynchronizedSortedMap(Char2ByteSortedMap local_m, Object sync)
    {
      super(sync);
      this.sortedMap = local_m;
    }
    
    protected SynchronizedSortedMap(Char2ByteSortedMap local_m)
    {
      super();
      this.sortedMap = local_m;
    }
    
    public CharComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.comparator();
      }
    }
    
    public ObjectSortedSet<Char2ByteMap.Entry> char2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.synchronize(this.sortedMap.char2ByteEntrySet(), this.sync);
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Character, Byte>> entrySet()
    {
      return char2ByteEntrySet();
    }
    
    public CharSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
      }
      return (CharSortedSet)this.keys;
    }
    
    public Char2ByteSortedMap subMap(char from, char local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Char2ByteSortedMap headMap(char local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Char2ByteSortedMap tailMap(char from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
    
    public char firstCharKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.firstCharKey();
      }
    }
    
    public char lastCharKey()
    {
      synchronized (this.sync)
      {
        return this.sortedMap.lastCharKey();
      }
    }
    
    public Character firstKey()
    {
      synchronized (this.sync)
      {
        return (Character)this.sortedMap.firstKey();
      }
    }
    
    public Character lastKey()
    {
      synchronized (this.sync)
      {
        return (Character)this.sortedMap.lastKey();
      }
    }
    
    public Char2ByteSortedMap subMap(Character from, Character local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.subMap(from, local_to), this.sync);
    }
    
    public Char2ByteSortedMap headMap(Character local_to)
    {
      return new SynchronizedSortedMap(this.sortedMap.headMap(local_to), this.sync);
    }
    
    public Char2ByteSortedMap tailMap(Character from)
    {
      return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
    }
  }
  
  public static class Singleton
    extends Char2ByteMaps.Singleton
    implements Char2ByteSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharComparator comparator;
    
    protected Singleton(char key, byte value, CharComparator comparator)
    {
      super(value);
      this.comparator = comparator;
    }
    
    protected Singleton(char key, byte value)
    {
      this(key, value, null);
    }
    
    final int compare(char local_k1, char local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    public CharComparator comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<Char2ByteMap.Entry> char2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSortedSets.singleton(new Char2ByteMaps.Singleton.SingletonEntry(this), Char2ByteSortedMaps.entryComparator(this.comparator));
      }
      return (ObjectSortedSet)this.entries;
    }
    
    public ObjectSortedSet<Map.Entry<Character, Byte>> entrySet()
    {
      return char2ByteEntrySet();
    }
    
    public CharSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSortedSets.singleton(this.key, this.comparator);
      }
      return (CharSortedSet)this.keys;
    }
    
    public Char2ByteSortedMap subMap(char from, char local_to)
    {
      if ((compare(from, this.key) <= 0) && (compare(this.key, local_to) < 0)) {
        return this;
      }
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Char2ByteSortedMap headMap(char local_to)
    {
      if (compare(this.key, local_to) < 0) {
        return this;
      }
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Char2ByteSortedMap tailMap(char from)
    {
      if (compare(from, this.key) <= 0) {
        return this;
      }
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public char firstCharKey()
    {
      return this.key;
    }
    
    public char lastCharKey()
    {
      return this.key;
    }
    
    public Char2ByteSortedMap headMap(Character oto)
    {
      return headMap(oto.charValue());
    }
    
    public Char2ByteSortedMap tailMap(Character ofrom)
    {
      return tailMap(ofrom.charValue());
    }
    
    public Char2ByteSortedMap subMap(Character ofrom, Character oto)
    {
      return subMap(ofrom.charValue(), oto.charValue());
    }
    
    public Character firstKey()
    {
      return Character.valueOf(firstCharKey());
    }
    
    public Character lastKey()
    {
      return Character.valueOf(lastCharKey());
    }
  }
  
  public static class EmptySortedMap
    extends Char2ByteMaps.EmptyMap
    implements Char2ByteSortedMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public CharComparator comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Char2ByteMap.Entry> char2ByteEntrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<Map.Entry<Character, Byte>> entrySet()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet keySet()
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public Char2ByteSortedMap subMap(char from, char local_to)
    {
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Char2ByteSortedMap headMap(char local_to)
    {
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public Char2ByteSortedMap tailMap(char from)
    {
      return Char2ByteSortedMaps.EMPTY_MAP;
    }
    
    public char firstCharKey()
    {
      throw new NoSuchElementException();
    }
    
    public char lastCharKey()
    {
      throw new NoSuchElementException();
    }
    
    public Char2ByteSortedMap headMap(Character oto)
    {
      return headMap(oto.charValue());
    }
    
    public Char2ByteSortedMap tailMap(Character ofrom)
    {
      return tailMap(ofrom.charValue());
    }
    
    public Char2ByteSortedMap subMap(Character ofrom, Character oto)
    {
      return subMap(ofrom.charValue(), oto.charValue());
    }
    
    public Character firstKey()
    {
      return Character.valueOf(firstCharKey());
    }
    
    public Character lastKey()
    {
      return Character.valueOf(lastCharKey());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ByteSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */