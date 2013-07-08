package it.unimi.dsi.fastutil.bytes;

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

public class Byte2LongMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Byte2LongMap singleton(byte key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2LongMap singleton(Byte key, Long value)
  {
    return new Singleton(key.byteValue(), value.longValue());
  }
  
  public static Byte2LongMap synchronize(Byte2LongMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Byte2LongMap synchronize(Byte2LongMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Byte2LongMap unmodifiable(Byte2LongMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Byte2LongFunctions.UnmodifiableFunction
    implements Byte2LongMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2LongMap map;
    protected volatile transient ObjectSet<Byte2LongMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient LongCollection values;
    
    protected UnmodifiableMap(Byte2LongMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(byte local_k)
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
    
    public long put(byte local_k, long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Byte, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2LongMap.Entry> byte2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.byte2LongEntrySet());
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.unmodifiable(this.map.keySet());
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
    
    public Long put(Byte local_k, Long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public long remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Long>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Byte2LongFunctions.SynchronizedFunction
    implements Byte2LongMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2LongMap map;
    protected volatile transient ObjectSet<Byte2LongMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient LongCollection values;
    
    protected SynchronizedMap(Byte2LongMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Byte2LongMap local_m)
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
    
    public boolean containsKey(byte local_k)
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
    
    public long put(byte local_k, long local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Byte, ? extends Long> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Byte2LongMap.Entry> byte2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.byte2LongEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.synchronize(this.map.keySet(), this.sync);
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
    
    public Long put(Byte local_k, Long local_v)
    {
      synchronized (this.sync)
      {
        return (Long)this.map.put(local_k, local_v);
      }
    }
    
    public long remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public long get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Long>> entrySet()
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
    extends Byte2LongFunctions.Singleton
    implements Byte2LongMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Byte2LongMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient LongCollection values;
    
    protected Singleton(byte key, long value)
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
    
    public void putAll(Map<? extends Byte, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2LongMap.Entry> byte2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<Byte, Long>> entrySet()
    {
      return byte2LongEntrySet();
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
      implements Byte2LongMap.Entry, Map.Entry<Byte, Long>
    {
      protected SingletonEntry() {}
      
      public Byte getKey()
      {
        return Byte.valueOf(Byte2LongMaps.Singleton.this.key);
      }
      
      public Long getValue()
      {
        return Long.valueOf(Byte2LongMaps.Singleton.this.value);
      }
      
      public byte getByteKey()
      {
        return Byte2LongMaps.Singleton.this.key;
      }
      
      public long getLongValue()
      {
        return Byte2LongMaps.Singleton.this.value;
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
        return (Byte2LongMaps.Singleton.this.key == ((Byte)local_e.getKey()).byteValue()) && (Byte2LongMaps.Singleton.this.value == ((Long)local_e.getValue()).longValue());
      }
      
      public int hashCode()
      {
        return Byte2LongMaps.Singleton.this.key ^ HashCommon.long2int(Byte2LongMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Byte2LongMaps.Singleton.this.key + "->" + Byte2LongMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Byte2LongFunctions.EmptyFunction
    implements Byte2LongMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(long local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Byte, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2LongMap.Entry> byte2LongEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ByteSet keySet()
    {
      return ByteSets.EMPTY_SET;
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
      return Byte2LongMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Byte2LongMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Byte, Long>> entrySet()
    {
      return byte2LongEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2LongMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */