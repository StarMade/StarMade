package it.unimi.dsi.fastutil.shorts;

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

public class Short2BooleanMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Short2BooleanMap singleton(short key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2BooleanMap singleton(Short key, Boolean value)
  {
    return new Singleton(key.shortValue(), value.booleanValue());
  }
  
  public static Short2BooleanMap synchronize(Short2BooleanMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Short2BooleanMap synchronize(Short2BooleanMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Short2BooleanMap unmodifiable(Short2BooleanMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Short2BooleanFunctions.UnmodifiableFunction
    implements Short2BooleanMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2BooleanMap map;
    protected volatile transient ObjectSet<Short2BooleanMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient BooleanCollection values;
    
    protected UnmodifiableMap(Short2BooleanMap local_m)
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
    
    public boolean put(short local_k, boolean local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Short, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2BooleanMap.Entry> short2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.short2BooleanEntrySet());
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
    
    public Boolean put(Short local_k, Boolean local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Boolean>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Short2BooleanFunctions.SynchronizedFunction
    implements Short2BooleanMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2BooleanMap map;
    protected volatile transient ObjectSet<Short2BooleanMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient BooleanCollection values;
    
    protected SynchronizedMap(Short2BooleanMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Short2BooleanMap local_m)
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
    
    public boolean put(short local_k, boolean local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Short, ? extends Boolean> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Short2BooleanMap.Entry> short2BooleanEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.short2BooleanEntrySet(), this.sync);
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
    
    public Boolean put(Short local_k, Boolean local_v)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.map.put(local_k, local_v);
      }
    }
    
    public boolean remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public boolean get(short local_k)
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
    
    public ObjectSet<Map.Entry<Short, Boolean>> entrySet()
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
    extends Short2BooleanFunctions.Singleton
    implements Short2BooleanMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Short2BooleanMap.Entry> entries;
    protected volatile transient ShortSet keys;
    protected volatile transient BooleanCollection values;
    
    protected Singleton(short key, boolean value)
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
    
    public void putAll(Map<? extends Short, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2BooleanMap.Entry> short2BooleanEntrySet()
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
    
    public ObjectSet<Map.Entry<Short, Boolean>> entrySet()
    {
      return short2BooleanEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value ? 1231 : 1237);
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
      implements Short2BooleanMap.Entry, Map.Entry<Short, Boolean>
    {
      protected SingletonEntry() {}
      
      public Short getKey()
      {
        return Short.valueOf(Short2BooleanMaps.Singleton.this.key);
      }
      
      public Boolean getValue()
      {
        return Boolean.valueOf(Short2BooleanMaps.Singleton.this.value);
      }
      
      public short getShortKey()
      {
        return Short2BooleanMaps.Singleton.this.key;
      }
      
      public boolean getBooleanValue()
      {
        return Short2BooleanMaps.Singleton.this.value;
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
        return (Short2BooleanMaps.Singleton.this.key == ((Short)local_e.getKey()).shortValue()) && (Short2BooleanMaps.Singleton.this.value == ((Boolean)local_e.getValue()).booleanValue());
      }
      
      public int hashCode()
      {
        return Short2BooleanMaps.Singleton.this.key ^ (Short2BooleanMaps.Singleton.this.value ? 1231 : 1237);
      }
      
      public String toString()
      {
        return Short2BooleanMaps.Singleton.this.key + "->" + Short2BooleanMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Short2BooleanFunctions.EmptyFunction
    implements Short2BooleanMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(boolean local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Short, ? extends Boolean> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Short2BooleanMap.Entry> short2BooleanEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ShortSet keySet()
    {
      return ShortSets.EMPTY_SET;
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
      return Short2BooleanMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Short2BooleanMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Short, Boolean>> entrySet()
    {
      return short2BooleanEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */