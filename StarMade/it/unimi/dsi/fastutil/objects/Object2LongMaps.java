package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongCollections;
import it.unimi.dsi.fastutil.longs.LongSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2LongMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Object2LongMap<K> singleton(K key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2LongMap<K> singleton(K key, Long value)
  {
    return new Singleton(key, value.longValue());
  }
  
  public static <K> Object2LongMap<K> synchronize(Object2LongMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Object2LongMap<K> synchronize(Object2LongMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Object2LongMap<K> unmodifiable(Object2LongMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Object2LongFunctions.UnmodifiableFunction<K>
    implements Object2LongMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2LongMap<K> map;
    protected volatile transient ObjectSet<Object2LongMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient LongCollection values;
    
    protected UnmodifiableMap(Object2LongMap<K> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(Object local_k)
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
    
    public long put(K local_k, long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2LongMap.Entry<K>> object2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2LongEntrySet());
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.unmodifiable(this.map.keySet());
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
    
    public boolean containsValue(Object local_ov)
    {
      return this.map.containsValue(local_ov);
    }
    
    public long removeLong(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long getLong(Object local_k)
    {
      return this.map.getLong(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Long>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Object2LongFunctions.SynchronizedFunction<K>
    implements Object2LongMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2LongMap<K> map;
    protected volatile transient ObjectSet<Object2LongMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient LongCollection values;
    
    protected SynchronizedMap(Object2LongMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2LongMap<K> local_m)
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
    
    public boolean containsKey(Object local_k)
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
    
    public long put(K local_k, long local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Long> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2LongMap.Entry<K>> object2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2LongEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync);
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
    
    public Long put(K local_k, Long local_v)
    {
      synchronized (this.sync)
      {
        return (Long)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public long removeLong(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeLong(local_k);
      }
    }
    
    public long getLong(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getLong(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Long>> entrySet()
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
  
  public static class Singleton<K>
    extends Object2LongFunctions.Singleton<K>
    implements Object2LongMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2LongMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient LongCollection values;
    
    protected Singleton(K key, long value)
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
    
    public void putAll(Map<? extends K, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2LongMap.Entry<K>> object2LongEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<K, Long>> entrySet()
    {
      return object2LongEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ HashCommon.long2int(this.value);
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
      implements Object2LongMap.Entry<K>, Map.Entry<K, Long>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2LongMaps.Singleton.this.key;
      }
      
      public Long getValue()
      {
        return Long.valueOf(Object2LongMaps.Singleton.this.value);
      }
      
      public long getLongValue()
      {
        return Object2LongMaps.Singleton.this.value;
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
        return (Object2LongMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2LongMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2LongMaps.Singleton.this.value == ((Long)local_e.getValue()).longValue());
      }
      
      public int hashCode()
      {
        return (Object2LongMaps.Singleton.this.key == null ? 0 : Object2LongMaps.Singleton.this.key.hashCode()) ^ HashCommon.long2int(Object2LongMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Object2LongMaps.Singleton.this.key + "->" + Object2LongMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Object2LongFunctions.EmptyFunction<K>
    implements Object2LongMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(long local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Long> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2LongMap.Entry<K>> object2LongEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
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
      return Object2LongMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2LongMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Long>> entrySet()
    {
      return object2LongEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */