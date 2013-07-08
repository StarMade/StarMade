package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharCollections;
import it.unimi.dsi.fastutil.chars.CharSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Long2CharMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Long2CharMap singleton(long key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static Long2CharMap singleton(Long key, Character value)
  {
    return new Singleton(key.longValue(), value.charValue());
  }
  
  public static Long2CharMap synchronize(Long2CharMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Long2CharMap synchronize(Long2CharMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Long2CharMap unmodifiable(Long2CharMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Long2CharFunctions.UnmodifiableFunction
    implements Long2CharMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2CharMap map;
    protected volatile transient ObjectSet<Long2CharMap.Entry> entries;
    protected volatile transient LongSet keys;
    protected volatile transient CharCollection values;
    
    protected UnmodifiableMap(Long2CharMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(long local_k)
    {
      return this.map.containsKey(local_k);
    }
    
    public boolean containsValue(char local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public char defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public char put(long local_k, char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Long, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2CharMap.Entry> long2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.long2CharEntrySet());
      }
      return this.entries;
    }
    
    public LongSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public CharCollection values()
    {
      if (this.values == null) {
        return CharCollections.unmodifiable(this.map.values());
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
    
    public Character put(Long local_k, Character local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public char remove(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char get(long local_k)
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
    
    public ObjectSet<Map.Entry<Long, Character>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Long2CharFunctions.SynchronizedFunction
    implements Long2CharMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2CharMap map;
    protected volatile transient ObjectSet<Long2CharMap.Entry> entries;
    protected volatile transient LongSet keys;
    protected volatile transient CharCollection values;
    
    protected SynchronizedMap(Long2CharMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Long2CharMap local_m)
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
    
    public boolean containsKey(long local_k)
    {
      synchronized (this.sync)
      {
        return this.map.containsKey(local_k);
      }
    }
    
    public boolean containsValue(char local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public char defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public char put(long local_k, char local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Long, ? extends Character> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Long2CharMap.Entry> long2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.long2CharEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public LongSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public CharCollection values()
    {
      if (this.values == null) {
        return CharCollections.synchronize(this.map.values(), this.sync);
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
    
    public Character put(Long local_k, Character local_v)
    {
      synchronized (this.sync)
      {
        return (Character)this.map.put(local_k, local_v);
      }
    }
    
    public char remove(long local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public char get(long local_k)
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
    
    public ObjectSet<Map.Entry<Long, Character>> entrySet()
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
    extends Long2CharFunctions.Singleton
    implements Long2CharMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Long2CharMap.Entry> entries;
    protected volatile transient LongSet keys;
    protected volatile transient CharCollection values;
    
    protected Singleton(long key, char value)
    {
      super(value);
    }
    
    public boolean containsValue(char local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Character)local_ov).charValue() == this.value;
    }
    
    public void putAll(Map<? extends Long, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2CharMap.Entry> long2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public LongSet keySet()
    {
      if (this.keys == null) {
        this.keys = LongSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public CharCollection values()
    {
      if (this.values == null) {
        this.values = CharSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Long, Character>> entrySet()
    {
      return long2CharEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ this.value;
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
      implements Long2CharMap.Entry, Map.Entry<Long, Character>
    {
      protected SingletonEntry() {}
      
      public Long getKey()
      {
        return Long.valueOf(Long2CharMaps.Singleton.this.key);
      }
      
      public Character getValue()
      {
        return Character.valueOf(Long2CharMaps.Singleton.this.value);
      }
      
      public long getLongKey()
      {
        return Long2CharMaps.Singleton.this.key;
      }
      
      public char getCharValue()
      {
        return Long2CharMaps.Singleton.this.value;
      }
      
      public char setValue(char value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Character setValue(Character value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Long2CharMaps.Singleton.this.key == ((Long)local_e.getKey()).longValue()) && (Long2CharMaps.Singleton.this.value == ((Character)local_e.getValue()).charValue());
      }
      
      public int hashCode()
      {
        return HashCommon.long2int(Long2CharMaps.Singleton.this.key) ^ Long2CharMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Long2CharMaps.Singleton.this.key + "->" + Long2CharMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Long2CharFunctions.EmptyFunction
    implements Long2CharMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(char local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Long, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2CharMap.Entry> long2CharEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public LongSet keySet()
    {
      return LongSets.EMPTY_SET;
    }
    
    public CharCollection values()
    {
      return CharSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Long2CharMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Long2CharMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Long, Character>> entrySet()
    {
      return long2CharEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2CharMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */