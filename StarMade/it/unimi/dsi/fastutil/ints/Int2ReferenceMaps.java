package it.unimi.dsi.fastutil.ints;

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

public class Int2ReferenceMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Int2ReferenceMap<V> singleton(int key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Int2ReferenceMap<V> singleton(Integer key, V value)
  {
    return new Singleton(key.intValue(), value);
  }
  
  public static <V> Int2ReferenceMap<V> synchronize(Int2ReferenceMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Int2ReferenceMap<V> synchronize(Int2ReferenceMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Int2ReferenceMap<V> unmodifiable(Int2ReferenceMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Int2ReferenceFunctions.UnmodifiableFunction<V>
    implements Int2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Int2ReferenceMap.Entry<V>> entries;
    protected volatile transient IntSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected UnmodifiableMap(Int2ReferenceMap<V> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(int local_k)
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
    
    public V put(int local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Integer, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2ReferenceMap.Entry<V>> int2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.int2ReferenceEntrySet());
      }
      return this.entries;
    }
    
    public IntSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSets.unmodifiable(this.map.keySet());
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
    
    public V remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(int local_k)
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
    
    public ObjectSet<Map.Entry<Integer, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Int2ReferenceFunctions.SynchronizedFunction<V>
    implements Int2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Int2ReferenceMap.Entry<V>> entries;
    protected volatile transient IntSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected SynchronizedMap(Int2ReferenceMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Int2ReferenceMap<V> local_m)
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
    
    public boolean containsKey(int local_k)
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
    
    public V put(int local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Integer, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Int2ReferenceMap.Entry<V>> int2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.int2ReferenceEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public IntSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSets.synchronize(this.map.keySet(), this.sync);
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
    
    public V put(Integer local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(int local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(int local_k)
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
    
    public ObjectSet<Map.Entry<Integer, V>> entrySet()
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
    extends Int2ReferenceFunctions.Singleton<V>
    implements Int2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Int2ReferenceMap.Entry<V>> entries;
    protected volatile transient IntSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected Singleton(int key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == local_v;
    }
    
    public void putAll(Map<? extends Integer, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2ReferenceMap.Entry<V>> int2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public IntSet keySet()
    {
      if (this.keys == null) {
        this.keys = IntSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<Integer, V>> entrySet()
    {
      return int2ReferenceEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value));
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
      implements Int2ReferenceMap.Entry<V>, Map.Entry<Integer, V>
    {
      protected SingletonEntry() {}
      
      public Integer getKey()
      {
        return Integer.valueOf(Int2ReferenceMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Int2ReferenceMaps.Singleton.this.value;
      }
      
      public int getIntKey()
      {
        return Int2ReferenceMaps.Singleton.this.key;
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
        return (Int2ReferenceMaps.Singleton.this.key == ((Integer)local_e.getKey()).intValue()) && (Int2ReferenceMaps.Singleton.this.value == local_e.getValue());
      }
      
      public int hashCode()
      {
        return Int2ReferenceMaps.Singleton.this.key ^ (Int2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Int2ReferenceMaps.Singleton.this.value));
      }
      
      public String toString()
      {
        return Int2ReferenceMaps.Singleton.this.key + "->" + Int2ReferenceMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Int2ReferenceFunctions.EmptyFunction<V>
    implements Int2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Integer, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2ReferenceMap.Entry<V>> int2ReferenceEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public IntSet keySet()
    {
      return IntSets.EMPTY_SET;
    }
    
    public ReferenceCollection<V> values()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Int2ReferenceMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Int2ReferenceMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Integer, V>> entrySet()
    {
      return int2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */