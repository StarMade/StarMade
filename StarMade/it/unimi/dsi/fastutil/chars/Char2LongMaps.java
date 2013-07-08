package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongCollections;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Char2LongMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Char2LongMap singleton(char key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static Char2LongMap singleton(Character key, Long value)
  {
    return new Singleton(key.charValue(), value.longValue());
  }
  
  public static Char2LongMap synchronize(Char2LongMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Char2LongMap synchronize(Char2LongMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Char2LongMap unmodifiable(Char2LongMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Char2LongFunctions.UnmodifiableFunction
    implements Char2LongMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2LongMap map;
    protected volatile transient ObjectSet<Char2LongMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient LongCollection values;
    
    protected UnmodifiableMap(Char2LongMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(char local_k)
    {
      return this.map.containsKey(local_k);
    }
    
    public boolean containsValue(long local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public long defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(long defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public long put(char local_k, long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Character, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2LongMap.Entry> char2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.char2LongEntrySet());
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public LongCollection values()
    {
      if (this.values == null) {
        return LongCollections.unmodifiable(this.map.values());
      }
      return this.values;
    }
    
    public void clear()
    {
      throw new UnsupportedOperationException();
    }
    
    public String toString()
    {
      return this.map.toString();
    }
    
    public Long put(Character local_k, Long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public long remove(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long get(char local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.map.containsKey(local_ok);
    }
    
    public boolean containsValue(Object local_ov)
    {
      return this.map.containsValue(local_ov);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<Character, Long>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Char2LongFunctions.SynchronizedFunction
    implements Char2LongMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2LongMap map;
    protected volatile transient ObjectSet<Char2LongMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient LongCollection values;
    
    protected SynchronizedMap(Char2LongMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Char2LongMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      synchronized (this.sync)
      {
        return this.map.size();
      }
    }
    
    public boolean containsKey(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.containsKey(local_k);
      }
    }
    
    public boolean containsValue(long local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public long defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(long defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public long put(char local_k, long local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Character, ? extends Long> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Char2LongMap.Entry> char2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.char2LongEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public LongCollection values()
    {
      if (this.values == null) {
        return LongCollections.synchronize(this.map.values(), this.sync);
      }
      return this.values;
    }
    
    public void clear()
    {
      synchronized (this.sync)
      {
        this.map.clear();
      }
    }
    
    public String toString()
    {
      synchronized (this.sync)
      {
        return this.map.toString();
      }
    }
    
    public Long put(Character local_k, Long local_v)
    {
      synchronized (this.sync)
      {
        return (Long)this.map.put(local_k, local_v);
      }
    }
    
    public long remove(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public long get(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.get(local_k);
      }
    }
    
    public boolean containsKey(Object local_ok)
    {
      synchronized (this.sync)
      {
        return this.map.containsKey(local_ok);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<Character, Long>> entrySet()
    {
      synchronized (this.sync)
      {
        return this.map.entrySet();
      }
    }
    
    public int hashCode()
    {
      synchronized (this.sync)
      {
        return this.map.hashCode();
      }
    }
    
    public boolean equals(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.map.equals(local_o);
      }
    }
  }
  
  public static class Singleton
    extends Char2LongFunctions.Singleton
    implements Char2LongMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Char2LongMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient LongCollection values;
    
    protected Singleton(char key, long value)
    {
      super(value);
    }
    
    public boolean containsValue(long local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Long)local_ov).longValue() == this.value;
    }
    
    public void putAll(Map<? extends Character, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2LongMap.Entry> char2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public LongCollection values()
    {
      if (this.values == null) {
        this.values = LongSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Character, Long>> entrySet()
    {
      return char2LongEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.long2int(this.value);
    }
    
    public boolean equals(Object local_o)
    {
      if (local_o == this) {
        return true;
      }
      if (!(local_o instanceof Map)) {
        return false;
      }
      Map<?, ?> local_m = (Map)local_o;
      if (local_m.size() != 1) {
        return false;
      }
      return ((Map.Entry)entrySet().iterator().next()).equals(local_m.entrySet().iterator().next());
    }
    
    public String toString()
    {
      return "{" + this.key + "=>" + this.value + "}";
    }
    
    protected class SingletonEntry
      implements Char2LongMap.Entry, Map.Entry<Character, Long>
    {
      protected SingletonEntry() {}
      
      public Character getKey()
      {
        return Character.valueOf(Char2LongMaps.Singleton.this.key);
      }
      
      public Long getValue()
      {
        return Long.valueOf(Char2LongMaps.Singleton.this.value);
      }
      
      public char getCharKey()
      {
        return Char2LongMaps.Singleton.this.key;
      }
      
      public long getLongValue()
      {
        return Char2LongMaps.Singleton.this.value;
      }
      
      public long setValue(long value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Long setValue(Long value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Char2LongMaps.Singleton.this.key == ((Character)local_e.getKey()).charValue()) && (Char2LongMaps.Singleton.this.value == ((Long)local_e.getValue()).longValue());
      }
      
      public int hashCode()
      {
        return Char2LongMaps.Singleton.this.key ^ HashCommon.long2int(Char2LongMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Char2LongMaps.Singleton.this.key + "->" + Char2LongMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Char2LongFunctions.EmptyFunction
    implements Char2LongMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(long local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Character, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2LongMap.Entry> char2LongEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public CharSet keySet()
    {
      return CharSets.EMPTY_SET;
    }
    
    public LongCollection values()
    {
      return LongSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Char2LongMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Char2LongMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Character, Long>> entrySet()
    {
      return char2LongEntrySet();
    }
    
    public int hashCode()
    {
      return 0;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map)) {
        return false;
      }
      return ((Map)local_o).isEmpty();
    }
    
    public String toString()
    {
      return "{}";
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */