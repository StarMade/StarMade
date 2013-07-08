package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2ReferenceMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K, V> Object2ReferenceMap<K, V> singleton(K key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <K, V> Object2ReferenceMap<K, V> synchronize(Object2ReferenceMap<K, V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K, V> Object2ReferenceMap<K, V> synchronize(Object2ReferenceMap<K, V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K, V> Object2ReferenceMap<K, V> unmodifiable(Object2ReferenceMap<K, V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K, V>
    extends Object2ReferenceFunctions.UnmodifiableFunction<K, V>
    implements Object2ReferenceMap<K, V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ReferenceMap<K, V> map;
    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected UnmodifiableMap(Object2ReferenceMap<K, V> local_m)
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
    
    public V put(K local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2ReferenceEntrySet());
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
    
    public ObjectSet<Map.Entry<K, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K, V>
    extends Object2ReferenceFunctions.SynchronizedFunction<K, V>
    implements Object2ReferenceMap<K, V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ReferenceMap<K, V> map;
    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected SynchronizedMap(Object2ReferenceMap<K, V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2ReferenceMap<K, V> local_m)
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
    
    public V put(K local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2ReferenceEntrySet(), this.sync);
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
    
    public V remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.get(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, V>> entrySet()
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
  
  public static class Singleton<K, V>
    extends Object2ReferenceFunctions.Singleton<K, V>
    implements Object2ReferenceMap<K, V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected Singleton(K key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == local_v;
    }
    
    public void putAll(Map<? extends K, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
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
    
    public ObjectSet<Map.Entry<K, V>> entrySet()
    {
      return object2ReferenceEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
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
      implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2ReferenceMaps.Singleton.this.key;
      }
      
      public V getValue()
      {
        return Object2ReferenceMaps.Singleton.this.value;
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
        return (Object2ReferenceMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2ReferenceMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2ReferenceMaps.Singleton.this.value == local_e.getValue());
      }
      
      public int hashCode()
      {
        return (Object2ReferenceMaps.Singleton.this.key == null ? 0 : Object2ReferenceMaps.Singleton.this.key.hashCode()) ^ (Object2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Object2ReferenceMaps.Singleton.this.value));
      }
      
      public String toString()
      {
        return Object2ReferenceMaps.Singleton.this.key + "->" + Object2ReferenceMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K, V>
    extends Object2ReferenceFunctions.EmptyFunction<K, V>
    implements Object2ReferenceMap<K, V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ReferenceCollection<V> values()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Object2ReferenceMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2ReferenceMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, V>> entrySet()
    {
      return object2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */