package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import it.unimi.dsi.fastutil.shorts.ShortSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2ShortMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Object2ShortMap<K> singleton(K key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2ShortMap<K> singleton(K key, Short value)
  {
    return new Singleton(key, value.shortValue());
  }
  
  public static <K> Object2ShortMap<K> synchronize(Object2ShortMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Object2ShortMap<K> synchronize(Object2ShortMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Object2ShortMap<K> unmodifiable(Object2ShortMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Object2ShortFunctions.UnmodifiableFunction<K>
    implements Object2ShortMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ShortMap<K> map;
    protected volatile transient ObjectSet<Object2ShortMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ShortCollection values;
    
    protected UnmodifiableMap(Object2ShortMap<K> local_m)
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
    
    public boolean containsValue(short local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public short defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public short put(K local_k, short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ShortMap.Entry<K>> object2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2ShortEntrySet());
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
    
    public ShortCollection values()
    {
      if (this.values == null) {
        return ShortCollections.unmodifiable(this.map.values());
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
    
    public short removeShort(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short getShort(Object local_k)
    {
      return this.map.getShort(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Short>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Object2ShortFunctions.SynchronizedFunction<K>
    implements Object2ShortMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ShortMap<K> map;
    protected volatile transient ObjectSet<Object2ShortMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ShortCollection values;
    
    protected SynchronizedMap(Object2ShortMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2ShortMap<K> local_m)
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
    
    public boolean containsValue(short local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public short defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public short put(K local_k, short local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Short> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2ShortMap.Entry<K>> object2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2ShortEntrySet(), this.sync);
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
    
    public ShortCollection values()
    {
      if (this.values == null) {
        return ShortCollections.synchronize(this.map.values(), this.sync);
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
    
    public Short put(K local_k, Short local_v)
    {
      synchronized (this.sync)
      {
        return (Short)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public short removeShort(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeShort(local_k);
      }
    }
    
    public short getShort(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getShort(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Short>> entrySet()
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
    extends Object2ShortFunctions.Singleton<K>
    implements Object2ShortMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2ShortMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ShortCollection values;
    
    protected Singleton(K key, short value)
    {
      super(value);
    }
    
    public boolean containsValue(short local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Short)local_ov).shortValue() == this.value;
    }
    
    public void putAll(Map<? extends K, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ShortMap.Entry<K>> object2ShortEntrySet()
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
    
    public ShortCollection values()
    {
      if (this.values == null) {
        this.values = ShortSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<K, Short>> entrySet()
    {
      return object2ShortEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
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
      implements Object2ShortMap.Entry<K>, Map.Entry<K, Short>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2ShortMaps.Singleton.this.key;
      }
      
      public Short getValue()
      {
        return Short.valueOf(Object2ShortMaps.Singleton.this.value);
      }
      
      public short getShortValue()
      {
        return Object2ShortMaps.Singleton.this.value;
      }
      
      public short setValue(short value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Short setValue(Short value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Object2ShortMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2ShortMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2ShortMaps.Singleton.this.value == ((Short)local_e.getValue()).shortValue());
      }
      
      public int hashCode()
      {
        return (Object2ShortMaps.Singleton.this.key == null ? 0 : Object2ShortMaps.Singleton.this.key.hashCode()) ^ Object2ShortMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Object2ShortMaps.Singleton.this.key + "->" + Object2ShortMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Object2ShortFunctions.EmptyFunction<K>
    implements Object2ShortMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(short local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ShortMap.Entry<K>> object2ShortEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ShortCollection values()
    {
      return ShortSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Object2ShortMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2ShortMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Short>> entrySet()
    {
      return object2ShortEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ShortMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */