package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollections;
import it.unimi.dsi.fastutil.doubles.DoubleSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Float2DoubleMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Float2DoubleMap singleton(float key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2DoubleMap singleton(Float key, Double value)
  {
    return new Singleton(key.floatValue(), value.doubleValue());
  }
  
  public static Float2DoubleMap synchronize(Float2DoubleMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Float2DoubleMap synchronize(Float2DoubleMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Float2DoubleMap unmodifiable(Float2DoubleMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Float2DoubleFunctions.UnmodifiableFunction
    implements Float2DoubleMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2DoubleMap map;
    protected volatile transient ObjectSet<Float2DoubleMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient DoubleCollection values;
    
    protected UnmodifiableMap(Float2DoubleMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(float local_k)
    {
      return this.map.containsKey(local_k);
    }
    
    public boolean containsValue(double local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public double defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public double put(float local_k, double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Float, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.float2DoubleEntrySet());
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        return DoubleCollections.unmodifiable(this.map.values());
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
    
    public Double put(Float local_k, Double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public double remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, Double>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Float2DoubleFunctions.SynchronizedFunction
    implements Float2DoubleMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2DoubleMap map;
    protected volatile transient ObjectSet<Float2DoubleMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient DoubleCollection values;
    
    protected SynchronizedMap(Float2DoubleMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Float2DoubleMap local_m)
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
    
    public boolean containsKey(float local_k)
    {
      synchronized (this.sync)
      {
        return this.map.containsKey(local_k);
      }
    }
    
    public boolean containsValue(double local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public double defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public double put(float local_k, double local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Float, ? extends Double> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.float2DoubleEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        return DoubleCollections.synchronize(this.map.values(), this.sync);
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
    
    public Double put(Float local_k, Double local_v)
    {
      synchronized (this.sync)
      {
        return (Double)this.map.put(local_k, local_v);
      }
    }
    
    public double remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public double get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, Double>> entrySet()
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
    extends Float2DoubleFunctions.Singleton
    implements Float2DoubleMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Float2DoubleMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient DoubleCollection values;
    
    protected Singleton(float key, double value)
    {
      super(value);
    }
    
    public boolean containsValue(double local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Double)local_ov).doubleValue() == this.value;
    }
    
    public void putAll(Map<? extends Float, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public FloatSet keySet()
    {
      if (this.keys == null) {
        this.keys = FloatSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        this.values = DoubleSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Float, Double>> entrySet()
    {
      return float2DoubleEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ HashCommon.double2int(this.value);
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
      implements Float2DoubleMap.Entry, Map.Entry<Float, Double>
    {
      protected SingletonEntry() {}
      
      public Float getKey()
      {
        return Float.valueOf(Float2DoubleMaps.Singleton.this.key);
      }
      
      public Double getValue()
      {
        return Double.valueOf(Float2DoubleMaps.Singleton.this.value);
      }
      
      public float getFloatKey()
      {
        return Float2DoubleMaps.Singleton.this.key;
      }
      
      public double getDoubleValue()
      {
        return Float2DoubleMaps.Singleton.this.value;
      }
      
      public double setValue(double value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Double setValue(Double value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Float2DoubleMaps.Singleton.this.key == ((Float)local_e.getKey()).floatValue()) && (Float2DoubleMaps.Singleton.this.value == ((Double)local_e.getValue()).doubleValue());
      }
      
      public int hashCode()
      {
        return HashCommon.float2int(Float2DoubleMaps.Singleton.this.key) ^ HashCommon.double2int(Float2DoubleMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Float2DoubleMaps.Singleton.this.key + "->" + Float2DoubleMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Float2DoubleFunctions.EmptyFunction
    implements Float2DoubleMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(double local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Float, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public FloatSet keySet()
    {
      return FloatSets.EMPTY_SET;
    }
    
    public DoubleCollection values()
    {
      return DoubleSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Float2DoubleMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Float2DoubleMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Float, Double>> entrySet()
    {
      return float2DoubleEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */