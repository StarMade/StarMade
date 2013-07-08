package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntCollections;
import it.unimi.dsi.fastutil.ints.IntSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Reference2IntMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Reference2IntMap<K> singleton(K key, int value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2IntMap<K> singleton(K key, Integer value)
  {
    return new Singleton(key, value.intValue());
  }
  
  public static <K> Reference2IntMap<K> synchronize(Reference2IntMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Reference2IntMap<K> synchronize(Reference2IntMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Reference2IntMap<K> unmodifiable(Reference2IntMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Reference2IntFunctions.UnmodifiableFunction<K>
    implements Reference2IntMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2IntMap<K> map;
    protected volatile transient ObjectSet<Reference2IntMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient IntCollection values;
    
    protected UnmodifiableMap(Reference2IntMap<K> local_m)
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
    
    public boolean containsValue(int local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public int defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(int defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public int put(K local_k, int local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2IntMap.Entry<K>> reference2IntEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.reference2IntEntrySet());
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public IntCollection values()
    {
      if (this.values == null) {
        return IntCollections.unmodifiable(this.map.values());
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
    
    public int removeInt(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int getInt(Object local_k)
    {
      return this.map.getInt(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Integer>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Reference2IntFunctions.SynchronizedFunction<K>
    implements Reference2IntMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2IntMap<K> map;
    protected volatile transient ObjectSet<Reference2IntMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient IntCollection values;
    
    protected SynchronizedMap(Reference2IntMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Reference2IntMap<K> local_m)
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
    
    public boolean containsValue(int local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public int defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(int defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public int put(K local_k, int local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Integer> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Reference2IntMap.Entry<K>> reference2IntEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.reference2IntEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public IntCollection values()
    {
      if (this.values == null) {
        return IntCollections.synchronize(this.map.values(), this.sync);
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
    
    public Integer put(K local_k, Integer local_v)
    {
      synchronized (this.sync)
      {
        return (Integer)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public int removeInt(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeInt(local_k);
      }
    }
    
    public int getInt(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getInt(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Integer>> entrySet()
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
    extends Reference2IntFunctions.Singleton<K>
    implements Reference2IntMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Reference2IntMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient IntCollection values;
    
    protected Singleton(K key, int value)
    {
      super(value);
    }
    
    public boolean containsValue(int local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Integer)local_ov).intValue() == this.value;
    }
    
    public void putAll(Map<? extends K, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2IntMap.Entry<K>> reference2IntEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public IntCollection values()
    {
      if (this.values == null) {
        this.values = IntSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<K, Integer>> entrySet()
    {
      return reference2IntEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ this.value;
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
      implements Reference2IntMap.Entry<K>, Map.Entry<K, Integer>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Reference2IntMaps.Singleton.this.key;
      }
      
      public Integer getValue()
      {
        return Integer.valueOf(Reference2IntMaps.Singleton.this.value);
      }
      
      public int getIntValue()
      {
        return Reference2IntMaps.Singleton.this.value;
      }
      
      public int setValue(int value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Integer setValue(Integer value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Reference2IntMaps.Singleton.this.key == local_e.getKey()) && (Reference2IntMaps.Singleton.this.value == ((Integer)local_e.getValue()).intValue());
      }
      
      public int hashCode()
      {
        return (Reference2IntMaps.Singleton.this.key == null ? 0 : System.identityHashCode(Reference2IntMaps.Singleton.this.key)) ^ Reference2IntMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Reference2IntMaps.Singleton.this.key + "->" + Reference2IntMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Reference2IntFunctions.EmptyFunction<K>
    implements Reference2IntMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(int local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2IntMap.Entry<K>> reference2IntEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ReferenceSet<K> keySet()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    public IntCollection values()
    {
      return IntSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Reference2IntMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Reference2IntMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Integer>> entrySet()
    {
      return reference2IntEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2IntMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */