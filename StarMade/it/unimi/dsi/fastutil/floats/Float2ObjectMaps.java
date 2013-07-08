package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectCollections;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Float2ObjectMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Float2ObjectMap<V> singleton(float key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Float2ObjectMap<V> singleton(Float key, V value)
  {
    return new Singleton(key.floatValue(), value);
  }
  
  public static <V> Float2ObjectMap<V> synchronize(Float2ObjectMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Float2ObjectMap<V> synchronize(Float2ObjectMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Float2ObjectMap<V> unmodifiable(Float2ObjectMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Float2ObjectFunctions.UnmodifiableFunction<V>
    implements Float2ObjectMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ObjectMap<V> map;
    protected volatile transient ObjectSet<Float2ObjectMap.Entry<V>> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected UnmodifiableMap(Float2ObjectMap<V> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(float local_k)
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
    
    public V put(float local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Float, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.float2ObjectEntrySet());
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public ObjectCollection<V> values()
    {
      if (this.values == null) {
        return ObjectCollections.unmodifiable(this.map.values());
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
    
    public V remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Float2ObjectFunctions.SynchronizedFunction<V>
    implements Float2ObjectMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ObjectMap<V> map;
    protected volatile transient ObjectSet<Float2ObjectMap.Entry<V>> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected SynchronizedMap(Float2ObjectMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Float2ObjectMap<V> local_m)
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
    
    public boolean containsKey(float local_k)
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
    
    public V put(float local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Float, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.float2ObjectEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public ObjectCollection<V> values()
    {
      if (this.values == null) {
        return ObjectCollections.synchronize(this.map.values(), this.sync);
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
    
    public V put(Float local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, V>> entrySet()
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
    extends Float2ObjectFunctions.Singleton<V>
    implements Float2ObjectMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Float2ObjectMap.Entry<V>> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected Singleton(float key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == null ? false : local_v == null ? true : this.value.equals(local_v);
    }
    
    public void putAll(Map<? extends Float, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public ObjectCollection<V> values()
    {
      if (this.values == null) {
        this.values = ObjectSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Float, V>> entrySet()
    {
      return float2ObjectEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode());
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
      implements Float2ObjectMap.Entry<V>, Map.Entry<Float, V>
    {
      protected SingletonEntry() {}
      
      public Float getKey()
      {
        return Float.valueOf(Float2ObjectMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Float2ObjectMaps.Singleton.this.value;
      }
      
      public float getFloatKey()
      {
        return Float2ObjectMaps.Singleton.this.key;
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
        return (Float2ObjectMaps.Singleton.this.key == ((Float)local_e.getKey()).floatValue()) && (Float2ObjectMaps.Singleton.this.value == null ? local_e.getValue() == null : Float2ObjectMaps.Singleton.this.value.equals(local_e.getValue()));
      }
      
      public int hashCode()
      {
        return HashCommon.float2int(Float2ObjectMaps.Singleton.this.key) ^ (Float2ObjectMaps.Singleton.this.value == null ? 0 : Float2ObjectMaps.Singleton.this.value.hashCode());
      }
      
      public String toString()
      {
        return Float2ObjectMaps.Singleton.this.key + "->" + Float2ObjectMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Float2ObjectFunctions.EmptyFunction<V>
    implements Float2ObjectMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Float, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public FloatSet keySet()
    {
      return FloatSets.EMPTY_SET;
    }
    
    public ObjectCollection<V> values()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Float2ObjectMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Float2ObjectMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Float, V>> entrySet()
    {
      return float2ObjectEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ObjectMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */