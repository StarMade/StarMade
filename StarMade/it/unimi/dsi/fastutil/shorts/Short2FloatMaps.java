package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatCollections;
import it.unimi.dsi.fastutil.floats.FloatSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Short2FloatMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Short2FloatMap singleton(short key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2FloatMap singleton(Short key, Float value)
  {
    return new Singleton(key.shortValue(), value.floatValue());
  }
  
  public static Short2FloatMap synchronize(Short2FloatMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Short2FloatMap synchronize(Short2FloatMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Short2FloatMap unmodifiable(Short2FloatMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Short2FloatFunctions.UnmodifiableFunction
    implements Short2FloatMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2FloatMap map;
    protected volatile transient ObjectSet<Short2FloatMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient FloatCollection values;
    
    protected UnmodifiableMap(Short2FloatMap local_m)
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
    
    public boolean containsValue(float local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public float defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public float put(short local_k, float local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Short, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2FloatMap.Entry> short2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.short2FloatEntrySet());
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        return FloatCollections.unmodifiable(this.map.values());
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
    
    public Float put(Short local_k, Float local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public float remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Float>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Short2FloatFunctions.SynchronizedFunction
    implements Short2FloatMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2FloatMap map;
    protected volatile transient ObjectSet<Short2FloatMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient FloatCollection values;
    
    protected SynchronizedMap(Short2FloatMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Short2FloatMap local_m)
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
    
    public boolean containsValue(float local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public float defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public float put(short local_k, float local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Short, ? extends Float> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Short2FloatMap.Entry> short2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.short2FloatEntrySet(), this.sync);
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        return FloatCollections.synchronize(this.map.values(), this.sync);
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
    
    public Float put(Short local_k, Float local_v)
    {
      synchronized (this.sync)
      {
        return (Float)this.map.put(local_k, local_v);
      }
    }
    
    public float remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public float get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Float>> entrySet()
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
    extends Short2FloatFunctions.Singleton
    implements Short2FloatMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Short2FloatMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient FloatCollection values;
    
    protected Singleton(short key, float value)
    {
      super(value);
    }
    
    public boolean containsValue(float local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Float)local_ov).floatValue() == this.value;
    }
    
    public void putAll(Map<? extends Short, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2FloatMap.Entry> short2FloatEntrySet()
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
    
    public FloatCollection values()
    {
      if (this.values == null) {
        this.values = FloatSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Short, Float>> entrySet()
    {
      return short2FloatEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.float2int(this.value);
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
      implements Short2FloatMap.Entry, Map.Entry<Short, Float>
    {
      protected SingletonEntry() {}
      
      public Short getKey()
      {
        return Short.valueOf(Short2FloatMaps.Singleton.this.key);
      }
      
      public Float getValue()
      {
        return Float.valueOf(Short2FloatMaps.Singleton.this.value);
      }
      
      public short getShortKey()
      {
        return Short2FloatMaps.Singleton.this.key;
      }
      
      public float getFloatValue()
      {
        return Short2FloatMaps.Singleton.this.value;
      }
      
      public float setValue(float value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Float setValue(Float value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Short2FloatMaps.Singleton.this.key == ((Short)local_e.getKey()).shortValue()) && (Short2FloatMaps.Singleton.this.value == ((Float)local_e.getValue()).floatValue());
      }
      
      public int hashCode()
      {
        return Short2FloatMaps.Singleton.this.key ^ HashCommon.float2int(Short2FloatMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Short2FloatMaps.Singleton.this.key + "->" + Short2FloatMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Short2FloatFunctions.EmptyFunction
    implements Short2FloatMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(float local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Short, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2FloatMap.Entry> short2FloatEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ShortSet keySet()
    {
      return ShortSets.EMPTY_SET;
    }
    
    public FloatCollection values()
    {
      return FloatSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Short2FloatMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Short2FloatMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Short, Float>> entrySet()
    {
      return short2FloatEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */