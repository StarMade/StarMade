package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteCollections;
import it.unimi.dsi.fastutil.bytes.ByteSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Object2ByteMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Object2ByteMap<K> singleton(K key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2ByteMap<K> singleton(K key, Byte value)
  {
    return new Singleton(key, value.byteValue());
  }
  
  public static <K> Object2ByteMap<K> synchronize(Object2ByteMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Object2ByteMap<K> synchronize(Object2ByteMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Object2ByteMap<K> unmodifiable(Object2ByteMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Object2ByteFunctions.UnmodifiableFunction<K>
    implements Object2ByteMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ByteMap<K> map;
    protected volatile transient ObjectSet<Object2ByteMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ByteCollection values;
    
    protected UnmodifiableMap(Object2ByteMap<K> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(Object local_k)
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
    
    public byte put(K local_k, byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.object2ByteEntrySet());
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.unmodifiable(this.map.keySet());
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
    
    public boolean containsValue(Object local_ov)
    {
      return this.map.containsValue(local_ov);
    }
    
    public byte removeByte(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte getByte(Object local_k)
    {
      return this.map.getByte(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Byte>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Object2ByteFunctions.SynchronizedFunction<K>
    implements Object2ByteMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2ByteMap<K> map;
    protected volatile transient ObjectSet<Object2ByteMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ByteCollection values;
    
    protected SynchronizedMap(Object2ByteMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Object2ByteMap<K> local_m)
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
    
    public boolean containsKey(Object local_k)
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
    
    public byte put(K local_k, byte local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Byte> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.object2ByteEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync);
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
    
    public Byte put(K local_k, Byte local_v)
    {
      synchronized (this.sync)
      {
        return (Byte)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public byte removeByte(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeByte(local_k);
      }
    }
    
    public byte getByte(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getByte(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Byte>> entrySet()
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
  
  public static class Singleton<K>
    extends Object2ByteFunctions.Singleton<K>
    implements Object2ByteMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Object2ByteMap.Entry<K>> entries;
    protected volatile transient ObjectSet<K> keys;
    protected volatile transient ByteCollection values;
    
    protected Singleton(K key, byte value)
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
    
    public void putAll(Map<? extends K, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ObjectSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ObjectSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<K, Byte>> entrySet()
    {
      return object2ByteEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
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
      implements Object2ByteMap.Entry<K>, Map.Entry<K, Byte>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Object2ByteMaps.Singleton.this.key;
      }
      
      public Byte getValue()
      {
        return Byte.valueOf(Object2ByteMaps.Singleton.this.value);
      }
      
      public byte getByteValue()
      {
        return Object2ByteMaps.Singleton.this.value;
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
        return (Object2ByteMaps.Singleton.this.key == null ? local_e.getKey() == null : Object2ByteMaps.Singleton.this.key.equals(local_e.getKey())) && (Object2ByteMaps.Singleton.this.value == ((Byte)local_e.getValue()).byteValue());
      }
      
      public int hashCode()
      {
        return (Object2ByteMaps.Singleton.this.key == null ? 0 : Object2ByteMaps.Singleton.this.key.hashCode()) ^ Object2ByteMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Object2ByteMaps.Singleton.this.key + "->" + Object2ByteMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Object2ByteFunctions.EmptyFunction<K>
    implements Object2ByteMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(byte local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Byte> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Object2ByteMap.Entry<K>> object2ByteEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ObjectSet<K> keySet()
    {
      return ObjectSets.EMPTY_SET;
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
      return Object2ByteMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Object2ByteMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Byte>> entrySet()
    {
      return object2ByteEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ByteMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */