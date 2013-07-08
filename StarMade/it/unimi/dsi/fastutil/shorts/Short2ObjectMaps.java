package it.unimi.dsi.fastutil.shorts;

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

public class Short2ObjectMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Short2ObjectMap<V> singleton(short key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Short2ObjectMap<V> singleton(Short key, V value)
  {
    return new Singleton(key.shortValue(), value);
  }
  
  public static <V> Short2ObjectMap<V> synchronize(Short2ObjectMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Short2ObjectMap<V> synchronize(Short2ObjectMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Short2ObjectMap<V> unmodifiable(Short2ObjectMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Short2ObjectFunctions.UnmodifiableFunction<V>
    implements Short2ObjectMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ObjectMap<V> map;
    protected volatile transient ObjectSet<Short2ObjectMap.Entry<V>> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected UnmodifiableMap(Short2ObjectMap<V> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(short local_k)
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
    
    public V put(short local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Short, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.short2ObjectEntrySet());
      }
      return this.entries;
    }
    
    public ShortSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSets.unmodifiable(this.map.keySet());
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
    
    public V remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Short2ObjectFunctions.SynchronizedFunction<V>
    implements Short2ObjectMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ObjectMap<V> map;
    protected volatile transient ObjectSet<Short2ObjectMap.Entry<V>> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected SynchronizedMap(Short2ObjectMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Short2ObjectMap<V> local_m)
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
    
    public boolean containsKey(short local_k)
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
    
    public V put(short local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Short, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.short2ObjectEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ShortSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSets.synchronize(this.map.keySet(), this.sync);
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
    
    public V put(Short local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, V>> entrySet()
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
    extends Short2ObjectFunctions.Singleton<V>
    implements Short2ObjectMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Short2ObjectMap.Entry<V>> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    protected Singleton(short key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == null ? false : local_v == null ? true : this.value.equals(local_v);
    }
    
    public void putAll(Map<? extends Short, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ShortSet keySet()
    {
      if (this.keys == null) {
        this.keys = ShortSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<Short, V>> entrySet()
    {
      return short2ObjectEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : this.value.hashCode());
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
      implements Short2ObjectMap.Entry<V>, Map.Entry<Short, V>
    {
      protected SingletonEntry() {}
      
      public Short getKey()
      {
        return Short.valueOf(Short2ObjectMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Short2ObjectMaps.Singleton.this.value;
      }
      
      public short getShortKey()
      {
        return Short2ObjectMaps.Singleton.this.key;
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
        return (Short2ObjectMaps.Singleton.this.key == ((Short)local_e.getKey()).shortValue()) && (Short2ObjectMaps.Singleton.this.value == null ? local_e.getValue() == null : Short2ObjectMaps.Singleton.this.value.equals(local_e.getValue()));
      }
      
      public int hashCode()
      {
        return Short2ObjectMaps.Singleton.this.key ^ (Short2ObjectMaps.Singleton.this.value == null ? 0 : Short2ObjectMaps.Singleton.this.value.hashCode());
      }
      
      public String toString()
      {
        return Short2ObjectMaps.Singleton.this.key + "->" + Short2ObjectMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Short2ObjectFunctions.EmptyFunction<V>
    implements Short2ObjectMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Short, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ShortSet keySet()
    {
      return ShortSets.EMPTY_SET;
    }
    
    public ObjectCollection<V> values()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Short2ObjectMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Short2ObjectMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Short, V>> entrySet()
    {
      return short2ObjectEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */