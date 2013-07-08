package it.unimi.dsi.fastutil.bytes;

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

public class Byte2FloatMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Byte2FloatMap singleton(byte key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2FloatMap singleton(Byte key, Float value)
  {
    return new Singleton(key.byteValue(), value.floatValue());
  }
  
  public static Byte2FloatMap synchronize(Byte2FloatMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Byte2FloatMap synchronize(Byte2FloatMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Byte2FloatMap unmodifiable(Byte2FloatMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Byte2FloatFunctions.UnmodifiableFunction
    implements Byte2FloatMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatMap map;
    protected volatile transient ObjectSet<Byte2FloatMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient FloatCollection values;
    
    protected UnmodifiableMap(Byte2FloatMap local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(byte local_k)
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
    
    public float put(byte local_k, float local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Byte, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.byte2FloatEntrySet());
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.unmodifiable(this.map.keySet());
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
    
    public Float put(Byte local_k, Float local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public float remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Float>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Byte2FloatFunctions.SynchronizedFunction
    implements Byte2FloatMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatMap map;
    protected volatile transient ObjectSet<Byte2FloatMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient FloatCollection values;
    
    protected SynchronizedMap(Byte2FloatMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Byte2FloatMap local_m)
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
    
    public boolean containsKey(byte local_k)
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
    
    public float put(byte local_k, float local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Byte, ? extends Float> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.byte2FloatEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.synchronize(this.map.keySet(), this.sync);
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
    
    public Float put(Byte local_k, Float local_v)
    {
      synchronized (this.sync)
      {
        return (Float)this.map.put(local_k, local_v);
      }
    }
    
    public float remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public float get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Float>> entrySet()
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
    extends Byte2FloatFunctions.Singleton
    implements Byte2FloatMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Byte2FloatMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient FloatCollection values;
    
    protected Singleton(byte key, float value)
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
    
    public void putAll(Map<? extends Byte, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ByteSet keySet()
    {
      if (this.keys == null) {
        this.keys = ByteSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<Byte, Float>> entrySet()
    {
      return byte2FloatEntrySet();
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
      implements Byte2FloatMap.Entry, Map.Entry<Byte, Float>
    {
      protected SingletonEntry() {}
      
      public Byte getKey()
      {
        return Byte.valueOf(Byte2FloatMaps.Singleton.this.key);
      }
      
      public Float getValue()
      {
        return Float.valueOf(Byte2FloatMaps.Singleton.this.value);
      }
      
      public byte getByteKey()
      {
        return Byte2FloatMaps.Singleton.this.key;
      }
      
      public float getFloatValue()
      {
        return Byte2FloatMaps.Singleton.this.value;
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
        return (Byte2FloatMaps.Singleton.this.key == ((Byte)local_e.getKey()).byteValue()) && (Byte2FloatMaps.Singleton.this.value == ((Float)local_e.getValue()).floatValue());
      }
      
      public int hashCode()
      {
        return Byte2FloatMaps.Singleton.this.key ^ HashCommon.float2int(Byte2FloatMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Byte2FloatMaps.Singleton.this.key + "->" + Byte2FloatMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Byte2FloatFunctions.EmptyFunction
    implements Byte2FloatMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(float local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Byte, ? extends Float> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2FloatMap.Entry> byte2FloatEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ByteSet keySet()
    {
      return ByteSets.EMPTY_SET;
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
      return Byte2FloatMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Byte2FloatMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Byte, Float>> entrySet()
    {
      return byte2FloatEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */