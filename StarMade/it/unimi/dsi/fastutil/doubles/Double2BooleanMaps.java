package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanCollections;
import it.unimi.dsi.fastutil.booleans.BooleanSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Double2BooleanMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Double2BooleanMap singleton(double key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Double2BooleanMap singleton(Double key, Boolean value)
  {
    return new Singleton(key.doubleValue(), value.booleanValue());
  }
  
  public static Double2BooleanMap synchronize(Double2BooleanMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Double2BooleanMap synchronize(Double2BooleanMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Double2BooleanMap unmodifiable(Double2BooleanMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Double2BooleanFunctions.UnmodifiableFunction
    implements Double2BooleanMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2BooleanMap map;
    protected volatile transient ObjectSet<Double2BooleanMap.Entry> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient BooleanCollection values;
    
    protected UnmodifiableMap(Double2BooleanMap local_m)
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
    
    public boolean put(double local_k, boolean local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Double, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2BooleanMap.Entry> double2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.double2BooleanEntrySet());
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
    
    public Boolean put(Double local_k, Boolean local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(double local_k)
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
    
    public ObjectSet<Map.Entry<Double, Boolean>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Double2BooleanFunctions.SynchronizedFunction
    implements Double2BooleanMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2BooleanMap map;
    protected volatile transient ObjectSet<Double2BooleanMap.Entry> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient BooleanCollection values;
    
    protected SynchronizedMap(Double2BooleanMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Double2BooleanMap local_m)
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
    
    public boolean put(double local_k, boolean local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Double, ? extends Boolean> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Double2BooleanMap.Entry> double2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.double2BooleanEntrySet(), this.sync);
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
    
    public Boolean put(Double local_k, Boolean local_v)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.map.put(local_k, local_v);
      }
    }
    
    public boolean remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public boolean get(double local_k)
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
    
    public ObjectSet<Map.Entry<Double, Boolean>> entrySet()
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
    extends Double2BooleanFunctions.Singleton
    implements Double2BooleanMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Double2BooleanMap.Entry> entries;
    protected volatile transient DoubleSet keys;
    protected volatile transient BooleanCollection values;
    
    protected Singleton(double key, boolean value)
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
    
    public void putAll(Map<? extends Double, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2BooleanMap.Entry> double2BooleanEntrySet()
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
    
    public ObjectSet<Map.Entry<Double, Boolean>> entrySet()
    {
      return double2BooleanEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ (this.value ? 1231 : 1237);
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
      implements Double2BooleanMap.Entry, Map.Entry<Double, Boolean>
    {
      protected SingletonEntry() {}
      
      public Double getKey()
      {
        return Double.valueOf(Double2BooleanMaps.Singleton.this.key);
      }
      
      public Boolean getValue()
      {
        return Boolean.valueOf(Double2BooleanMaps.Singleton.this.value);
      }
      
      public double getDoubleKey()
      {
        return Double2BooleanMaps.Singleton.this.key;
      }
      
      public boolean getBooleanValue()
      {
        return Double2BooleanMaps.Singleton.this.value;
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
        return (Double2BooleanMaps.Singleton.this.key == ((Double)local_e.getKey()).doubleValue()) && (Double2BooleanMaps.Singleton.this.value == ((Boolean)local_e.getValue()).booleanValue());
      }
      
      public int hashCode()
      {
        return HashCommon.double2int(Double2BooleanMaps.Singleton.this.key) ^ (Double2BooleanMaps.Singleton.this.value ? 1231 : 1237);
      }
      
      public String toString()
      {
        return Double2BooleanMaps.Singleton.this.key + "->" + Double2BooleanMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Double2BooleanFunctions.EmptyFunction
    implements Double2BooleanMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(boolean local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Double, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Double2BooleanMap.Entry> double2BooleanEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public DoubleSet keySet()
    {
      return DoubleSets.EMPTY_SET;
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
      return Double2BooleanMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Double2BooleanMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Double, Boolean>> entrySet()
    {
      return double2BooleanEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2BooleanMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */