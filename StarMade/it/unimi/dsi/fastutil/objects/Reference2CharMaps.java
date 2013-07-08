package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharCollections;
import it.unimi.dsi.fastutil.chars.CharSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Reference2CharMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static <K> Reference2CharMap<K> singleton(K key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2CharMap<K> singleton(K key, Character value)
  {
    return new Singleton(key, value.charValue());
  }
  
  public static <K> Reference2CharMap<K> synchronize(Reference2CharMap<K> local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static <K> Reference2CharMap<K> synchronize(Reference2CharMap<K> local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static <K> Reference2CharMap<K> unmodifiable(Reference2CharMap<K> local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap<K>
    extends Reference2CharFunctions.UnmodifiableFunction<K>
    implements Reference2CharMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2CharMap<K> map;
    protected volatile transient ObjectSet<Reference2CharMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient CharCollection values;
    
    protected UnmodifiableMap(Reference2CharMap<K> local_m)
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
    
    public char put(K local_k, char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends K, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2CharMap.Entry<K>> reference2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.reference2CharEntrySet());
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.unmodifiable(this.map.keySet());
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
    
    public boolean containsValue(Object local_ov)
    {
      return this.map.containsValue(local_ov);
    }
    
    public char removeChar(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char getChar(Object local_k)
    {
      return this.map.getChar(local_k);
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public ObjectSet<Map.Entry<K, Character>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap<K>
    extends Reference2CharFunctions.SynchronizedFunction<K>
    implements Reference2CharMap<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2CharMap<K> map;
    protected volatile transient ObjectSet<Reference2CharMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient CharCollection values;
    
    protected SynchronizedMap(Reference2CharMap<K> local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Reference2CharMap<K> local_m)
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
    
    public char put(K local_k, char local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends K, ? extends Character> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Reference2CharMap.Entry<K>> reference2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.reference2CharEntrySet(), this.sync);
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.synchronize(this.map.keySet(), this.sync);
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
    
    public Character put(K local_k, Character local_v)
    {
      synchronized (this.sync)
      {
        return (Character)this.map.put(local_k, local_v);
      }
    }
    
    public boolean containsValue(Object local_ov)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_ov);
      }
    }
    
    public char removeChar(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.removeChar(local_k);
      }
    }
    
    public char getChar(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.map.getChar(local_k);
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.map.isEmpty();
      }
    }
    
    public ObjectSet<Map.Entry<K, Character>> entrySet()
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
    extends Reference2CharFunctions.Singleton<K>
    implements Reference2CharMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Reference2CharMap.Entry<K>> entries;
    protected volatile transient ReferenceSet<K> keys;
    protected volatile transient CharCollection values;
    
    protected Singleton(K key, char value)
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
    
    public void putAll(Map<? extends K, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2CharMap.Entry<K>> reference2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.singleton(new SingletonEntry());
      }
      return this.entries;
    }
    
    public ReferenceSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = ReferenceSets.singleton(this.key);
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
    
    public ObjectSet<Map.Entry<K, Character>> entrySet()
    {
      return reference2CharEntrySet();
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ this.value;
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
      implements Reference2CharMap.Entry<K>, Map.Entry<K, Character>
    {
      protected SingletonEntry() {}
      
      public K getKey()
      {
        return Reference2CharMaps.Singleton.this.key;
      }
      
      public Character getValue()
      {
        return Character.valueOf(Reference2CharMaps.Singleton.this.value);
      }
      
      public char getCharValue()
      {
        return Reference2CharMaps.Singleton.this.value;
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
        return (Reference2CharMaps.Singleton.this.key == local_e.getKey()) && (Reference2CharMaps.Singleton.this.value == ((Character)local_e.getValue()).charValue());
      }
      
      public int hashCode()
      {
        return (Reference2CharMaps.Singleton.this.key == null ? 0 : System.identityHashCode(Reference2CharMaps.Singleton.this.key)) ^ Reference2CharMaps.Singleton.this.value;
      }
      
      public String toString()
      {
        return Reference2CharMaps.Singleton.this.key + "->" + Reference2CharMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap<K>
    extends Reference2CharFunctions.EmptyFunction<K>
    implements Reference2CharMap<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(char local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends K, ? extends Character> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Reference2CharMap.Entry<K>> reference2CharEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public ReferenceSet<K> keySet()
    {
      return ReferenceSets.EMPTY_SET;
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
      return Reference2CharMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Reference2CharMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<K, Character>> entrySet()
    {
      return reference2CharEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2CharMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */