package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Short2ShortMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Short2ShortMap singleton(short key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2ShortMap singleton(Short key, Short value)
  {
    return new Singleton(key.shortValue(), value.shortValue());
  }
  
  public static Short2ShortMap synchronize(Short2ShortMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Short2ShortMap synchronize(Short2ShortMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Short2ShortMap unmodifiable(Short2ShortMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Short2ShortFunctions.UnmodifiableFunction
    implements Short2ShortMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ShortMap map;
    protected volatile transient ObjectSet<Short2ShortMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ShortCollection values;
    
    protected UnmodifiableMap(Short2ShortMap local_m)
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
    
    public short put(short local_k, short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Short, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ShortMap.Entry> short2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.short2ShortEntrySet());
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
    
    public Short put(Short local_k, Short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public short remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Short>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Short2ShortFunctions.SynchronizedFunction
    implements Short2ShortMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ShortMap map;
    protected volatile transient ObjectSet<Short2ShortMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ShortCollection values;
    
    protected SynchronizedMap(Short2ShortMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Short2ShortMap local_m)
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
    
    public short put(short local_k, short local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Short, ? extends Short> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Short2ShortMap.Entry> short2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.short2ShortEntrySet(), this.sync);
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
    
    public Short put(Short local_k, Short local_v)
    {
      synchronized (this.sync)
      {
        return (Short)this.map.put(local_k, local_v);
      }
    }
    
    public short remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public short get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Short>> entrySet()
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
    extends Short2ShortFunctions.Singleton
    implements Short2ShortMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Short2ShortMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient ShortCollection values;
    
    protected Singleton(short key, short value)
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
    
    public void putAll(Map<? extends Short, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ShortMap.Entry> short2ShortEntrySet()
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
    
    public ObjectSet<Map.Entry<Short, Short>> entrySet()
    {
      return short2ShortEntrySet();
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
      implements Short2ShortMap.Entry, Map.Entry<Short, Short>
    {
      protected SingletonEntry() {}
      
      public Short getKey()
      {
        return Short.valueOf(Short2ShortMaps.Singleton.this.key);
      }
      
      public Short getValue()
      {
        return Short.valueOf(Short2ShortMaps.Singleton.this.value);
      }
      
      public short getShortKey()
      {
        return Short2ShortMaps.Singleton.this.key;
      }
      
      public short getShortValue()
      {
        return Short2ShortMaps.Singleton.this.value;
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
        return (Short2ShortMaps.Singleton.this.key == ((Short)local_e.getKey()).shortValue()) && (Short2ShortMaps.Singleton.this.value == ((Short)local_e.getValue()).shortValue());
      }
      
      public int hashCode()
      {
        return Short2ShortMaps.Singleton.this.key ^ Short2ShortMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Short2ShortMaps.Singleton.this.key + "->" + Short2ShortMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Short2ShortFunctions.EmptyFunction
    implements Short2ShortMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(short local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Short, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2ShortMap.Entry> short2ShortEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ShortSet keySet()
    {
      return ShortSets.EMPTY_SET;
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
      return Short2ShortMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Short2ShortMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Short, Short>> entrySet()
    {
      return short2ShortEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */