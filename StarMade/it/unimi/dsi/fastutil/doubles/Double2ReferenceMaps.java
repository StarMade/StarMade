package it.unimi.dsi.fastutil.doubles;

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

public class Double2ReferenceMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Double2ReferenceMap<V> singleton(double key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Double2ReferenceMap<V> singleton(Double key, V value)
  {
    return new Singleton(key.doubleValue(), value);
  }
  
  public static <V> Double2ReferenceMap<V> synchronize(Double2ReferenceMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Double2ReferenceMap<V> synchronize(Double2ReferenceMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Double2ReferenceMap<V> unmodifiable(Double2ReferenceMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Double2ReferenceFunctions.UnmodifiableFunction<V>
    implements Double2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Double2ReferenceMap.Entry<V>> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected UnmodifiableMap(Double2ReferenceMap<V> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(double local_k)
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
    
    public V put(double local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Double, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2ReferenceMap.Entry<V>> double2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.double2ReferenceEntrySet());
      }
      return this.entries;
    }
    
    public DoubleSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSets.unmodifiable(this.map.keySet());
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
    
    public V remove(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(double local_k)
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
    
    public ObjectSet<Map.Entry<Double, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Double2ReferenceFunctions.SynchronizedFunction<V>
    implements Double2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Double2ReferenceMap.Entry<V>> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected SynchronizedMap(Double2ReferenceMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Double2ReferenceMap<V> local_m)
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
    
    public boolean containsKey(double local_k)
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
    
    public V put(double local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Double, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Double2ReferenceMap.Entry<V>> double2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.double2ReferenceEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public DoubleSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSets.synchronize(this.map.keySet(), this.sync);
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
    
    public V put(Double local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(double local_k)
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
    
    public ObjectSet<Map.Entry<Double, V>> entrySet()
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
    extends Double2ReferenceFunctions.Singleton<V>
    implements Double2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Double2ReferenceMap.Entry<V>> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected Singleton(double key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == local_v;
    }
    
    public void putAll(Map<? extends Double, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2ReferenceMap.Entry<V>> double2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public DoubleSet keySet()
    {
      if (this.keys == null) {
        this.keys = DoubleSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<Double, V>> entrySet()
    {
      return double2ReferenceEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
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
      implements Double2ReferenceMap.Entry<V>, Map.Entry<Double, V>
    {
      protected SingletonEntry() {}
      
      public Double getKey()
      {
        return Double.valueOf(Double2ReferenceMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Double2ReferenceMaps.Singleton.this.value;
      }
      
      public double getDoubleKey()
      {
        return Double2ReferenceMaps.Singleton.this.key;
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
        return (Double2ReferenceMaps.Singleton.this.key == ((Double)local_e.getKey()).doubleValue()) && (Double2ReferenceMaps.Singleton.this.value == local_e.getValue());
      }
      
      public int hashCode()
      {
        return HashCommon.double2int(Double2ReferenceMaps.Singleton.this.key) ^ (Double2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Double2ReferenceMaps.Singleton.this.value));
      }
      
      public String toString()
      {
        return Double2ReferenceMaps.Singleton.this.key + "->" + Double2ReferenceMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Double2ReferenceFunctions.EmptyFunction<V>
    implements Double2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Double, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2ReferenceMap.Entry<V>> double2ReferenceEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public DoubleSet keySet()
    {
      return DoubleSets.EMPTY_SET;
    }
    
    public ReferenceCollection<V> values()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Double2ReferenceMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Double2ReferenceMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Double, V>> entrySet()
    {
      return double2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */