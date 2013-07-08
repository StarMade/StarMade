package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanCollections;
import it.unimi.dsi.fastutil.booleans.BooleanSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2BooleanMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Object2BooleanMap<K> singleton(K key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2BooleanMap<K> singleton(K key, Boolean value)
  {
    return new Singleton(key, value.booleanValue());
  }
  
  public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Object2BooleanMap<K> unmodifiable(Object2BooleanMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Object2BooleanFunctions.UnmodifiableFunction<K>
    implements Object2BooleanMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2BooleanMap<K> map;
    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient BooleanCollection values;
    
    protected UnmodifiableMap(Object2BooleanMap<K> local_m)
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
    
    public boolean containsValue(boolean local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public boolean defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean put(K local_k, boolean local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2BooleanEntrySet());
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
    
    public BooleanCollection values()
    {
      if (this.values == null) {
        return BooleanCollections.unmodifiable(this.map.values());
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
    
    public boolean removeBoolean(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean getBoolean(Object local_k)
    {
      return this.map.getBoolean(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Boolean>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Object2BooleanFunctions.SynchronizedFunction<K>
    implements Object2BooleanMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2BooleanMap<K> map;
    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient BooleanCollection values;
    
    protected SynchronizedMap(Object2BooleanMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2BooleanMap<K> local_m)
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
    
    public boolean containsValue(boolean local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public boolean defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public boolean put(K local_k, boolean local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Boolean> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2BooleanEntrySet(), this.sync);
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
    
    public BooleanCollection values()
    {
      if (this.values == null) {
        return BooleanCollections.synchronize(this.map.values(), this.sync);
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
    
    public Boolean put(K local_k, Boolean local_v)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public boolean removeBoolean(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeBoolean(local_k);
      }
    }
    
    public boolean getBoolean(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getBoolean(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Boolean>> entrySet()
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
    extends Object2BooleanFunctions.Singleton<K>
    implements Object2BooleanMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient BooleanCollection values;
    
    protected Singleton(K key, boolean value)
    {
      super(value);
    }
    
    public boolean containsValue(boolean local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Boolean)local_ov).booleanValue() == this.value;
    }
    
    public void putAll(Map<? extends K, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet()
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
    
    public BooleanCollection values()
    {
      if (this.values == null) {
        this.values = BooleanSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<K, Boolean>> entrySet()
    {
      return object2BooleanEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value ? 1231 : 1237);
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
      implements Object2BooleanMap.Entry<K>, Map.Entry<K, Boolean>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2BooleanMaps.Singleton.this.key;
      }
      
      public Boolean getValue()
      {
        return Boolean.valueOf(Object2BooleanMaps.Singleton.this.value);
      }
      
      public boolean getBooleanValue()
      {
        return Object2BooleanMaps.Singleton.this.value;
      }
      
      public boolean setValue(boolean value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Boolean setValue(Boolean value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Object2BooleanMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2BooleanMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2BooleanMaps.Singleton.this.value == ((Boolean)local_e.getValue()).booleanValue());
      }
      
      public int hashCode()
      {
        return (Object2BooleanMaps.Singleton.this.key == null ? 0 : Object2BooleanMaps.Singleton.this.key.hashCode()) ^ (Object2BooleanMaps.Singleton.this.value ? 1231 : 1237);
      }
      
      public String toString()
      {
        return Object2BooleanMaps.Singleton.this.key + "->" + Object2BooleanMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Object2BooleanFunctions.EmptyFunction<K>
    implements Object2BooleanMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(boolean local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public BooleanCollection values()
    {
      return BooleanSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Object2BooleanMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2BooleanMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Boolean>> entrySet()
    {
      return object2BooleanEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */