package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.ReferenceCollections;
import it.unimi.dsi.fastutil.objects.ReferenceSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Char2ReferenceMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <V> Char2ReferenceMap<V> singleton(char key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Char2ReferenceMap<V> singleton(Character key, V value)
  {
    return new Singleton(key.charValue(), value);
  }
  
  public static <V> Char2ReferenceMap<V> synchronize(Char2ReferenceMap<V> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <V> Char2ReferenceMap<V> synchronize(Char2ReferenceMap<V> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <V> Char2ReferenceMap<V> unmodifiable(Char2ReferenceMap<V> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<V>
    extends Char2ReferenceFunctions.UnmodifiableFunction<V>
    implements Char2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Char2ReferenceMap.Entry<V>> entries;
    protected volatile transient CharSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected UnmodifiableMap(Char2ReferenceMap<V> local_m)
    {
      super();
      this.map = local_m;
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean containsKey(char local_k)
    {
      return this.map.containsKey(local_k);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public V defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public V put(char local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Character, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.char2ReferenceEntrySet());
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.unmodifiable(this.map.keySet());
      }
      return this.keys;
    }
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        return ReferenceCollections.unmodifiable(this.map.values());
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
    
    public V remove(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(char local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.map.containsKey(local_ok);
    }
    
    public V remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(Object local_k)
    {
      return this.map.get(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<Character, V>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<V>
    extends Char2ReferenceFunctions.SynchronizedFunction<V>
    implements Char2ReferenceMap<V>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2ReferenceMap<V> map;
    protected volatile transient ObjectSet<Char2ReferenceMap.Entry<V>> entries;
    protected volatile transient CharSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected SynchronizedMap(Char2ReferenceMap<V> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Char2ReferenceMap<V> local_m)
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
    
    public boolean containsKey(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.containsKey(local_k);
      }
    }
    
    public boolean containsValue(Object local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public V defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public V put(char local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Character, ? extends V> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.char2ReferenceEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.synchronize(this.map.keySet(), this.sync);
      }
      return this.keys;
    }
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        return ReferenceCollections.synchronize(this.map.values(), this.sync);
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
    
    public V put(Character local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public V remove(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public V get(char local_k)
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
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<Character, V>> entrySet()
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
  
  public static class Singleton<V>
    extends Char2ReferenceFunctions.Singleton<V>
    implements Char2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Char2ReferenceMap.Entry<V>> entries;
    protected volatile transient CharSet keys;
    protected volatile transient ReferenceCollection<V> values;
    
    protected Singleton(char key, V value)
    {
      super(value);
    }
    
    public boolean containsValue(Object local_v)
    {
      return this.value == local_v;
    }
    
    public void putAll(Map<? extends Character, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public CharSet keySet()
    {
      if (this.keys == null) {
        this.keys = CharSets.singleton(this.key);
      }
      return this.keys;
    }
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        this.values = ReferenceSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Character, V>> entrySet()
    {
      return char2ReferenceEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value));
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
      implements Char2ReferenceMap.Entry<V>, Map.Entry<Character, V>
    {
      protected SingletonEntry() {}
      
      public Character getKey()
      {
        return Character.valueOf(Char2ReferenceMaps.Singleton.this.key);
      }
      
      public V getValue()
      {
        return Char2ReferenceMaps.Singleton.this.value;
      }
      
      public char getCharKey()
      {
        return Char2ReferenceMaps.Singleton.this.key;
      }
      
      public V setValue(V value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Char2ReferenceMaps.Singleton.this.key == ((Character)local_e.getKey()).charValue()) && (Char2ReferenceMaps.Singleton.this.value == local_e.getValue());
      }
      
      public int hashCode()
      {
        return Char2ReferenceMaps.Singleton.this.key ^ (Char2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Char2ReferenceMaps.Singleton.this.value));
      }
      
      public String toString()
      {
        return Char2ReferenceMaps.Singleton.this.key + "->" + Char2ReferenceMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<V>
    extends Char2ReferenceFunctions.EmptyFunction<V>
    implements Char2ReferenceMap<V>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(Object local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Character, ? extends V> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public CharSet keySet()
    {
      return CharSets.EMPTY_SET;
    }
    
    public ReferenceCollection<V> values()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return Char2ReferenceMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Char2ReferenceMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Character, V>> entrySet()
    {
      return char2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */