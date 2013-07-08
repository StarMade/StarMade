package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Byte2ByteMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Byte2ByteMap singleton(byte key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2ByteMap singleton(Byte key, Byte value)
  {
    return new Singleton(key.byteValue(), value.byteValue());
  }
  
  public static Byte2ByteMap synchronize(Byte2ByteMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Byte2ByteMap synchronize(Byte2ByteMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Byte2ByteMap unmodifiable(Byte2ByteMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Byte2ByteFunctions.UnmodifiableFunction
    implements Byte2ByteMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ByteMap map;
    protected volatile transient ObjectSet<Byte2ByteMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ByteCollection values;
    
    protected UnmodifiableMap(Byte2ByteMap local_m)
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
    
    public boolean containsValue(byte local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public byte defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte put(byte local_k, byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Byte, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.byte2ByteEntrySet());
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
    
    public ByteCollection values()
    {
      if (this.values == null) {
        return ByteCollections.unmodifiable(this.map.values());
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
    
    public Byte put(Byte local_k, Byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Byte>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Byte2ByteFunctions.SynchronizedFunction
    implements Byte2ByteMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ByteMap map;
    protected volatile transient ObjectSet<Byte2ByteMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ByteCollection values;
    
    protected SynchronizedMap(Byte2ByteMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Byte2ByteMap local_m)
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
    
    public boolean containsValue(byte local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public byte defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public byte put(byte local_k, byte local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Byte, ? extends Byte> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.byte2ByteEntrySet(), this.sync);
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
    
    public ByteCollection values()
    {
      if (this.values == null) {
        return ByteCollections.synchronize(this.map.values(), this.sync);
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
    
    public Byte put(Byte local_k, Byte local_v)
    {
      synchronized (this.sync)
      {
        return (Byte)this.map.put(local_k, local_v);
      }
    }
    
    public byte remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public byte get(byte local_k)
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
    
    public ObjectSet<Map.Entry<Byte, Byte>> entrySet()
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
    extends Byte2ByteFunctions.Singleton
    implements Byte2ByteMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Byte2ByteMap.Entry> entries;
    protected volatile transient ByteSet keys;
    protected volatile transient ByteCollection values;
    
    protected Singleton(byte key, byte value)
    {
      super(value);
    }
    
    public boolean containsValue(byte local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Byte)local_ov).byteValue() == this.value;
    }
    
    public void putAll(Map<? extends Byte, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
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
    
    public ByteCollection values()
    {
      if (this.values == null) {
        this.values = ByteSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Byte, Byte>> entrySet()
    {
      return byte2ByteEntrySet();
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
      implements Byte2ByteMap.Entry, Map.Entry<Byte, Byte>
    {
      protected SingletonEntry() {}
      
      public Byte getKey()
      {
        return Byte.valueOf(Byte2ByteMaps.Singleton.this.key);
      }
      
      public Byte getValue()
      {
        return Byte.valueOf(Byte2ByteMaps.Singleton.this.value);
      }
      
      public byte getByteKey()
      {
        return Byte2ByteMaps.Singleton.this.key;
      }
      
      public byte getByteValue()
      {
        return Byte2ByteMaps.Singleton.this.value;
      }
      
      public byte setValue(byte value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Byte setValue(Byte value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Byte2ByteMaps.Singleton.this.key == ((Byte)local_e.getKey()).byteValue()) && (Byte2ByteMaps.Singleton.this.value == ((Byte)local_e.getValue()).byteValue());
      }
      
      public int hashCode()
      {
        return Byte2ByteMaps.Singleton.this.key ^ Byte2ByteMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Byte2ByteMaps.Singleton.this.key + "->" + Byte2ByteMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Byte2ByteFunctions.EmptyFunction
    implements Byte2ByteMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(byte local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Byte, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Byte2ByteMap.Entry> byte2ByteEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ByteSet keySet()
    {
      return ByteSets.EMPTY_SET;
    }
    
    public ByteCollection values()
    {
      return ByteSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Byte2ByteMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Byte2ByteMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Byte, Byte>> entrySet()
    {
      return byte2ByteEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */