package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatCollections;
import it.unimi.dsi.fastutil.floats.FloatSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2FloatMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Object2FloatMap<K> singleton(K key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2FloatMap<K> singleton(K key, Float value)
  {
    return new Singleton(key, value.floatValue());
  }
  
  public static <K> Object2FloatMap<K> synchronize(Object2FloatMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Object2FloatMap<K> synchronize(Object2FloatMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Object2FloatMap<K> unmodifiable(Object2FloatMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Object2FloatFunctions.UnmodifiableFunction<K>
    implements Object2FloatMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2FloatMap<K> map;
    protected volatile transient ObjectSet<Object2FloatMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient FloatCollection values;
    
    protected UnmodifiableMap(Object2FloatMap<K> local_m)
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
    
    public boolean containsValue(float local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public float defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public float put(K local_k, float local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2FloatMap.Entry<K>> object2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2FloatEntrySet());
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        return FloatCollections.unmodifiable(this.map.values());
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
    
    public float removeFloat(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float getFloat(Object local_k)
    {
      return this.map.getFloat(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Float>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Object2FloatFunctions.SynchronizedFunction<K>
    implements Object2FloatMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2FloatMap<K> map;
    protected volatile transient ObjectSet<Object2FloatMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient FloatCollection values;
    
    protected SynchronizedMap(Object2FloatMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2FloatMap<K> local_m)
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
    
    public boolean containsValue(float local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public float defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public float put(K local_k, float local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Float> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2FloatMap.Entry<K>> object2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2FloatEntrySet(), this.sync);
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        return FloatCollections.synchronize(this.map.values(), this.sync);
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
    
    public Float put(K local_k, Float local_v)
    {
      synchronized (this.sync)
      {
        return (Float)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public float removeFloat(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeFloat(local_k);
      }
    }
    
    public float getFloat(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getFloat(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Float>> entrySet()
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
    extends Object2FloatFunctions.Singleton<K>
    implements Object2FloatMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2FloatMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient FloatCollection values;
    
    protected Singleton(K key, float value)
    {
      super(value);
    }
    
    public boolean containsValue(float local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Float)local_ov).floatValue() == this.value;
    }
    
    public void putAll(Map<? extends K, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2FloatMap.Entry<K>> object2FloatEntrySet()
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        this.values = FloatSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<K, Float>> entrySet()
    {
      return object2FloatEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ HashCommon.float2int(this.value);
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
      implements Object2FloatMap.Entry<K>, Map.Entry<K, Float>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2FloatMaps.Singleton.this.key;
      }
      
      public Float getValue()
      {
        return Float.valueOf(Object2FloatMaps.Singleton.this.value);
      }
      
      public float getFloatValue()
      {
        return Object2FloatMaps.Singleton.this.value;
      }
      
      public float setValue(float value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Float setValue(Float value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Object2FloatMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2FloatMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2FloatMaps.Singleton.this.value == ((Float)local_e.getValue()).floatValue());
      }
      
      public int hashCode()
      {
        return (Object2FloatMaps.Singleton.this.key == null ? 0 : Object2FloatMaps.Singleton.this.key.hashCode()) ^ HashCommon.float2int(Object2FloatMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Object2FloatMaps.Singleton.this.key + "->" + Object2FloatMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Object2FloatFunctions.EmptyFunction<K>
    implements Object2FloatMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(float local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2FloatMap.Entry<K>> object2FloatEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public FloatCollection values()
    {
      return FloatSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Object2FloatMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2FloatMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Float>> entrySet()
    {
      return object2FloatEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */