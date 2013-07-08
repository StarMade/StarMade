package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import it.unimi.dsi.fastutil.shorts.ShortSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Byte2ShortMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Byte2ShortMap singleton(byte key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2ShortMap singleton(Byte key, Short value)
  {
    return new Singleton(key.byteValue(), value.shortValue());
  }
  
  public static Byte2ShortMap synchronize(Byte2ShortMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Byte2ShortMap synchronize(Byte2ShortMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Byte2ShortMap unmodifiable(Byte2ShortMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Byte2ShortFunctions.UnmodifiableFunction
    implements Byte2ShortMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ShortMap map;
    protected volatile transient ObjectSet<Byte2ShortMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ShortCollection values;
    
    protected UnmodifiableMap(Byte2ShortMap local_m)
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
    
    public short put(byte local_k, short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Byte, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.byte2ShortEntrySet());
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
    
    public Short put(Byte local_k, Short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public short remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Short>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Byte2ShortFunctions.SynchronizedFunction
    implements Byte2ShortMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ShortMap map;
    protected volatile transient ObjectSet<Byte2ShortMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ShortCollection values;
    
    protected SynchronizedMap(Byte2ShortMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Byte2ShortMap local_m)
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
    
    public short put(byte local_k, short local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Byte, ? extends Short> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.byte2ShortEntrySet(), this.sync);
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
    
    public Short put(Byte local_k, Short local_v)
    {
      synchronized (this.sync)
      {
        return (Short)this.map.put(local_k, local_v);
      }
    }
    
    public short remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public short get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Short>> entrySet()
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
    extends Byte2ShortFunctions.Singleton
    implements Byte2ShortMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Byte2ShortMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ShortCollection values;
    
    protected Singleton(byte key, short value)
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
    
    public void putAll(Map<? extends Byte, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
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
    
    public ObjectSet<Map.Entry<Byte, Short>> entrySet()
    {
      return byte2ShortEntrySet();
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
      implements Byte2ShortMap.Entry, Map.Entry<Byte, Short>
    {
      protected SingletonEntry() {}
      
      public Byte getKey()
      {
        return Byte.valueOf(Byte2ShortMaps.Singleton.this.key);
      }
      
      public Short getValue()
      {
        return Short.valueOf(Byte2ShortMaps.Singleton.this.value);
      }
      
      public byte getByteKey()
      {
        return Byte2ShortMaps.Singleton.this.key;
      }
      
      public short getShortValue()
      {
        return Byte2ShortMaps.Singleton.this.value;
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
        return (Byte2ShortMaps.Singleton.this.key == ((Byte)local_e.getKey()).byteValue()) && (Byte2ShortMaps.Singleton.this.value == ((Short)local_e.getValue()).shortValue());
      }
      
      public int hashCode()
      {
        return Byte2ShortMaps.Singleton.this.key ^ Byte2ShortMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Byte2ShortMaps.Singleton.this.key + "->" + Byte2ShortMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Byte2ShortFunctions.EmptyFunction
    implements Byte2ShortMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(short local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Byte, ? extends Short> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ShortMap.Entry> byte2ShortEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ByteSet keySet()
    {
      return ByteSets.EMPTY_SET;
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
      return Byte2ShortMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Byte2ShortMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Byte, Short>> entrySet()
    {
      return byte2ShortEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */