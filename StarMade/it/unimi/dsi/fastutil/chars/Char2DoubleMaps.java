package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollections;
import it.unimi.dsi.fastutil.doubles.DoubleSets;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Char2DoubleMaps
{
  public static final EmptyMap EMPTY_MAP = new EmptyMap();
  
  public static Char2DoubleMap singleton(char key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static Char2DoubleMap singleton(Character key, Double value)
  {
    return new Singleton(key.charValue(), value.doubleValue());
  }
  
  public static Char2DoubleMap synchronize(Char2DoubleMap local_m)
  {
    return new SynchronizedMap(local_m);
  }
  
  public static Char2DoubleMap synchronize(Char2DoubleMap local_m, Object sync)
  {
    return new SynchronizedMap(local_m, sync);
  }
  
  public static Char2DoubleMap unmodifiable(Char2DoubleMap local_m)
  {
    return new UnmodifiableMap(local_m);
  }
  
  public static class UnmodifiableMap
    extends Char2DoubleFunctions.UnmodifiableFunction
    implements Char2DoubleMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2DoubleMap map;
    protected volatile transient ObjectSet<Char2DoubleMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient DoubleCollection values;
    
    protected UnmodifiableMap(Char2DoubleMap local_m)
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
    
    public boolean containsValue(double local_v)
    {
      return this.map.containsValue(local_v);
    }
    
    public double defaultReturnValue()
    {
      throw new UnsupportedOperationException();
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public double put(char local_k, double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void putAll(Map<? extends Character, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2DoubleMap.Entry> char2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.unmodifiable(this.map.char2DoubleEntrySet());
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
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        return DoubleCollections.unmodifiable(this.map.values());
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
    
    public Double put(Character local_k, Double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public double remove(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double get(char local_k)
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
    
    public ObjectSet<Map.Entry<Character, Double>> entrySet()
    {
      return ObjectSets.unmodifiable(this.map.entrySet());
    }
  }
  
  public static class SynchronizedMap
    extends Char2DoubleFunctions.SynchronizedFunction
    implements Char2DoubleMap, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2DoubleMap map;
    protected volatile transient ObjectSet<Char2DoubleMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient DoubleCollection values;
    
    protected SynchronizedMap(Char2DoubleMap local_m, Object sync)
    {
      super(sync);
      this.map = local_m;
    }
    
    protected SynchronizedMap(Char2DoubleMap local_m)
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
    
    public boolean containsValue(double local_v)
    {
      synchronized (this.sync)
      {
        return this.map.containsValue(local_v);
      }
    }
    
    public double defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.map.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      synchronized (this.sync)
      {
        this.map.defaultReturnValue(defRetValue);
      }
    }
    
    public double put(char local_k, double local_v)
    {
      synchronized (this.sync)
      {
        return this.map.put(local_k, local_v);
      }
    }
    
    public void putAll(Map<? extends Character, ? extends Double> local_m)
    {
      synchronized (this.sync)
      {
        this.map.putAll(local_m);
      }
    }
    
    public ObjectSet<Char2DoubleMap.Entry> char2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = ObjectSets.synchronize(this.map.char2DoubleEntrySet(), this.sync);
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
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        return DoubleCollections.synchronize(this.map.values(), this.sync);
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
    
    public Double put(Character local_k, Double local_v)
    {
      synchronized (this.sync)
      {
        return (Double)this.map.put(local_k, local_v);
      }
    }
    
    public double remove(char local_k)
    {
      synchronized (this.sync)
      {
        return this.map.remove(local_k);
      }
    }
    
    public double get(char local_k)
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
    
    public ObjectSet<Map.Entry<Character, Double>> entrySet()
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
    extends Char2DoubleFunctions.Singleton
    implements Char2DoubleMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected volatile transient ObjectSet<Char2DoubleMap.Entry> entries;
    protected volatile transient CharSet keys;
    protected volatile transient DoubleCollection values;
    
    protected Singleton(char key, double value)
    {
      super(value);
    }
    
    public boolean containsValue(double local_v)
    {
      return this.value == local_v;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return ((Double)local_ov).doubleValue() == this.value;
    }
    
    public void putAll(Map<? extends Character, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2DoubleMap.Entry> char2DoubleEntrySet()
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
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        this.values = DoubleSets.singleton(this.value);
      }
      return this.values;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public ObjectSet<Map.Entry<Character, Double>> entrySet()
    {
      return char2DoubleEntrySet();
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.double2int(this.value);
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
      implements Char2DoubleMap.Entry, Map.Entry<Character, Double>
    {
      protected SingletonEntry() {}
      
      public Character getKey()
      {
        return Character.valueOf(Char2DoubleMaps.Singleton.this.key);
      }
      
      public Double getValue()
      {
        return Double.valueOf(Char2DoubleMaps.Singleton.this.value);
      }
      
      public char getCharKey()
      {
        return Char2DoubleMaps.Singleton.this.key;
      }
      
      public double getDoubleValue()
      {
        return Char2DoubleMaps.Singleton.this.value;
      }
      
      public double setValue(double value)
      {
        throw new UnsupportedOperationException();
      }
      
      public Double setValue(Double value)
      {
        throw new UnsupportedOperationException();
      }
      
      public boolean equals(Object local_o)
      {
        if (!(local_o instanceof Map.Entry)) {
          return false;
        }
        Map.Entry<?, ?> local_e = (Map.Entry)local_o;
        return (Char2DoubleMaps.Singleton.this.key == ((Character)local_e.getKey()).charValue()) && (Char2DoubleMaps.Singleton.this.value == ((Double)local_e.getValue()).doubleValue());
      }
      
      public int hashCode()
      {
        return Char2DoubleMaps.Singleton.this.key ^ HashCommon.double2int(Char2DoubleMaps.Singleton.this.value);
      }
      
      public String toString()
      {
        return Char2DoubleMaps.Singleton.this.key + "->" + Char2DoubleMaps.Singleton.this.value;
      }
    }
  }
  
  public static class EmptyMap
    extends Char2DoubleFunctions.EmptyFunction
    implements Char2DoubleMap, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean containsValue(double local_v)
    {
      return false;
    }
    
    public void putAll(Map<? extends Character, ? extends Double> local_m)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSet<Char2DoubleMap.Entry> char2DoubleEntrySet()
    {
      return ObjectSets.EMPTY_SET;
    }
    
    public CharSet keySet()
    {
      return CharSets.EMPTY_SET;
    }
    
    public DoubleCollection values()
    {
      return DoubleSets.EMPTY_SET;
    }
    
    public boolean containsValue(Object local_ov)
    {
      return false;
    }
    
    private Object readResolve()
    {
      return Char2DoubleMaps.EMPTY_MAP;
    }
    
    public Object clone()
    {
      return Char2DoubleMaps.EMPTY_MAP;
    }
    
    public boolean isEmpty()
    {
      return true;
    }
    
    public ObjectSet<Map.Entry<Character, Double>> entrySet()
    {
      return char2DoubleEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */