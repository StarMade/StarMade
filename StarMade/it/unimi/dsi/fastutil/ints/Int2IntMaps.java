package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Int2IntMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Int2IntMap singleton(int key, int value)
  {
    return new Singleton(key, value);
  }
  
  public static Int2IntMap singleton(Integer key, Integer value)
  {
    return new Singleton(key.intValue(), value.intValue());
  }
  
  public static Int2IntMap synchronize(Int2IntMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Int2IntMap synchronize(Int2IntMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Int2IntMap unmodifiable(Int2IntMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Int2IntFunctions.UnmodifiableFunction
    implements Int2IntMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2IntMap map;
    protected volatile transient ObjectSet<Int2IntMap.Entry> entries;
    protected volatile transient IntSet keys;
    protected volatile transient IntCollection values;
    
    protected UnmodifiableMap(Int2IntMap local_m)
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
    
    public int put(int local_k, int local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Integer, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2IntMap.Entry> int2IntEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.int2IntEntrySet());
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
    
    public Integer put(Integer local_k, Integer local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public int remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int get(int local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.map.containsKey(local_ok);
    }
    
    public boolean containsValue(Object local_ov)
    {
      return this.map.containsValue(local_ov);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<Integer, Integer>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Int2IntFunctions.SynchronizedFunction
    implements Int2IntMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2IntMap map;
    protected volatile transient ObjectSet<Int2IntMap.Entry> entries;
    protected volatile transient IntSet keys;
    protected volatile transient IntCollection values;
    
    protected SynchronizedMap(Int2IntMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Int2IntMap local_m)
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
    
    public int put(int local_k, int local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Integer, ? extends Integer> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Int2IntMap.Entry> int2IntEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.int2IntEntrySet(), this.sync);
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
    
    public Integer put(Integer local_k, Integer local_v)
    {
      synchronized (this.sync)
      {
        return (Integer)this.map.put(local_k, local_v);
      }
    }
    
    public int remove(int local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public int get(int local_k)
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
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<Integer, Integer>> entrySet()
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
  
  public static class Singleton
    extends Int2IntFunctions.Singleton
    implements Int2IntMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Int2IntMap.Entry> entries;
    protected volatile transient IntSet keys;
    protected volatile transient IntCollection values;
    
    protected Singleton(int key, int value)
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
    
    public void putAll(Map<? extends Integer, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2IntMap.Entry> int2IntEntrySet()
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
    
    public ObjectSet<Map.Entry<Integer, Integer>> entrySet()
    {
      return int2IntEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ this.value;
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
      implements Int2IntMap.Entry, Map.Entry<Integer, Integer>
    {
      protected SingletonEntry() {}
      
      public Integer getKey()
      {
        return Integer.valueOf(Int2IntMaps.Singleton.this.key);
      }
      
      public Integer getValue()
      {
        return Integer.valueOf(Int2IntMaps.Singleton.this.value);
      }
      
      public int getIntKey()
      {
        return Int2IntMaps.Singleton.this.key;
      }
      
      public int getIntValue()
      {
        return Int2IntMaps.Singleton.this.value;
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
        return (Int2IntMaps.Singleton.this.key == ((Integer)local_e.getKey()).intValue()) && (Int2IntMaps.Singleton.this.value == ((Integer)local_e.getValue()).intValue());
      }
      
      public int hashCode()
      {
        return Int2IntMaps.Singleton.this.key ^ Int2IntMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Int2IntMaps.Singleton.this.key + "->" + Int2IntMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Int2IntFunctions.EmptyFunction
    implements Int2IntMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(int local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Integer, ? extends Integer> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Int2IntMap.Entry> int2IntEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public IntSet keySet()
    {
      return IntSets.EMPTY_SET;
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
      return Int2IntMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Int2IntMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Integer, Integer>> entrySet()
    {
      return int2IntEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2IntMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */