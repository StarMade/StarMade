package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharCollections;
import it.unimi.dsi.fastutil.chars.CharSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Float2CharMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Float2CharMap singleton(float key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2CharMap singleton(Float key, Character value)
  {
    return new Singleton(key.floatValue(), value.charValue());
  }
  
  public static Float2CharMap synchronize(Float2CharMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Float2CharMap synchronize(Float2CharMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Float2CharMap unmodifiable(Float2CharMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Float2CharFunctions.UnmodifiableFunction
    implements Float2CharMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2CharMap map;
    protected volatile transient ObjectSet<Float2CharMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient CharCollection values;
    
    protected UnmodifiableMap(Float2CharMap local_m)
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
    
    public boolean containsValue(char local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public char defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public char put(float local_k, char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Float, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2CharMap.Entry> float2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.float2CharEntrySet());
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
    
    public CharCollection values()
    {
      if (this.values == null) {
        return CharCollections.unmodifiable(this.map.values());
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
    
    public Character put(Float local_k, Character local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public char remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, Character>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Float2CharFunctions.SynchronizedFunction
    implements Float2CharMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2CharMap map;
    protected volatile transient ObjectSet<Float2CharMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient CharCollection values;
    
    protected SynchronizedMap(Float2CharMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Float2CharMap local_m)
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
    
    public boolean containsValue(char local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public char defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public char put(float local_k, char local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Float, ? extends Character> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Float2CharMap.Entry> float2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.float2CharEntrySet(), this.sync);
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
    
    public CharCollection values()
    {
      if (this.values == null) {
        return CharCollections.synchronize(this.map.values(), this.sync);
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
    
    public Character put(Float local_k, Character local_v)
    {
      synchronized (this.sync)
      {
        return (Character)this.map.put(local_k, local_v);
      }
    }
    
    public char remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public char get(float local_k)
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
    
    public ObjectSet<Map.Entry<Float, Character>> entrySet()
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
    extends Float2CharFunctions.Singleton
    implements Float2CharMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Float2CharMap.Entry> entries;
    protected volatile transient FloatSet keys;
    protected volatile transient CharCollection values;
    
    protected Singleton(float key, char value)
    {
      super(value);
    }
    
    public boolean containsValue(char local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Character)local_ov).charValue() == this.value;
    }
    
    public void putAll(Map<? extends Float, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2CharMap.Entry> float2CharEntrySet()
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
    
    public CharCollection values()
    {
      if (this.values == null) {
        this.values = CharSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Float, Character>> entrySet()
    {
      return float2CharEntrySet();
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ this.value;
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
      implements Float2CharMap.Entry, Map.Entry<Float, Character>
    {
      protected SingletonEntry() {}
      
      public Float getKey()
      {
        return Float.valueOf(Float2CharMaps.Singleton.this.key);
      }
      
      public Character getValue()
      {
        return Character.valueOf(Float2CharMaps.Singleton.this.value);
      }
      
      public float getFloatKey()
      {
        return Float2CharMaps.Singleton.this.key;
      }
      
      public char getCharValue()
      {
        return Float2CharMaps.Singleton.this.value;
      }
      
      public char setValue(char value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Character setValue(Character value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Float2CharMaps.Singleton.this.key == ((Float)local_e.getKey()).floatValue()) && (Float2CharMaps.Singleton.this.value == ((Character)local_e.getValue()).charValue());
      }
      
      public int hashCode()
      {
        return HashCommon.float2int(Float2CharMaps.Singleton.this.key) ^ Float2CharMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Float2CharMaps.Singleton.this.key + "->" + Float2CharMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Float2CharFunctions.EmptyFunction
    implements Float2CharMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(char local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Float, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Float2CharMap.Entry> float2CharEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public FloatSet keySet()
    {
      return FloatSets.EMPTY_SET;
    }
    
    public CharCollection values()
    {
      return CharSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Float2CharMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Float2CharMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Float, Character>> entrySet()
    {
      return float2CharEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2CharMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */