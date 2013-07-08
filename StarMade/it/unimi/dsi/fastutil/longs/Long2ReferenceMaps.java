package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.ReferenceCollections;
import it.unimi.dsi.fastutil.objects.ReferenceSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Long2ReferenceMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Long2ReferenceMap<V> singleton(long key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Long2ReferenceMap<V> singleton(Long key, V value)
  {
    return new Singleton(key.longValue(), value);
  }
  
  public static <V> Long2ReferenceMap<V> synchronize(Long2ReferenceMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Long2ReferenceMap<V> synchronize(Long2ReferenceMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Long2ReferenceMap<V> unmodifiable(Long2ReferenceMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Long2ReferenceFunctions.UnmodifiableFunction<V>
    implements Long2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
    protected volatile transient LongSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected UnmodifiableMap(Long2ReferenceMap<V> local_m)
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
    
    public boolean containsValue(Object local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public V defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public V put(long local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Long, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.long2ReferenceEntrySet());
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
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        return ReferenceCollections.unmodifiable(this.map.values());
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
    
    public V remove(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(long local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.map.containsKey(local_ok);
    }
    
    public V remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(Object local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<Long, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Long2ReferenceFunctions.SynchronizedFunction<V>
    implements Long2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
    protected volatile transient LongSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected SynchronizedMap(Long2ReferenceMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Long2ReferenceMap<V> local_m)
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
    
    public boolean containsValue(Object local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public V defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public V put(long local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Long, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.long2ReferenceEntrySet(), this.sync);
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
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        return ReferenceCollections.synchronize(this.map.values(), this.sync);
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
    
    public V put(Long local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(long local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(long local_k)
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
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<Long, V>> entrySet()
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
  
  public static class Singleton<V>
    extends Long2ReferenceFunctions.Singleton<V>
    implements Long2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
    protected volatile transient LongSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected Singleton(long key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == local_v;
    }
    
    public void putAll(Map<? extends Long, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
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
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        this.values = ReferenceSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Long, V>> entrySet()
    {
      return long2ReferenceEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
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
      implements Long2ReferenceMap.Entry<V>, Map.Entry<Long, V>
    {
      protected SingletonEntry() {}
      
      public Long getKey()
      {
        return Long.valueOf(Long2ReferenceMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Long2ReferenceMaps.Singleton.this.value;
      }
      
      public long getLongKey()
      {
        return Long2ReferenceMaps.Singleton.this.key;
      }
      
      public V setValue(V value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Long2ReferenceMaps.Singleton.this.key == ((Long)local_e.getKey()).longValue()) && (Long2ReferenceMaps.Singleton.this.value == local_e.getValue());
      }
      
      public int hashCode()
      {
        return HashCommon.long2int(Long2ReferenceMaps.Singleton.this.key) ^ (Long2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Long2ReferenceMaps.Singleton.this.value));
      }
      
      public String toString()
      {
        return Long2ReferenceMaps.Singleton.this.key + "->" + Long2ReferenceMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Long2ReferenceFunctions.EmptyFunction<V>
    implements Long2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Long, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public LongSet keySet()
    {
      return LongSets.EMPTY_SET;
    }
    
    public ReferenceCollection<V> values()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Long2ReferenceMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Long2ReferenceMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Long, V>> entrySet()
    {
      return long2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */