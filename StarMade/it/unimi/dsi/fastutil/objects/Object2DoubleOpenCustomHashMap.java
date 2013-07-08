package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2DoubleOpenCustomHashMap<K>
  extends AbstractObject2DoubleMap<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient double[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Object2DoubleMap.FastEntrySet<K> entries;
  protected volatile transient ObjectSet<K> keys;
  protected volatile transient DoubleCollection values;
  protected Hash.Strategy<K> strategy;
  
  public Object2DoubleOpenCustomHashMap(int expected, float local_f, Hash.Strategy<K> strategy)
  {
    this.strategy = strategy;
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_48 = local_f;
    this.field_49 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_49 - 1);
    this.maxFill = HashCommon.maxFill(this.field_49, local_f);
    this.key = ((Object[])new Object[this.field_49]);
    this.value = new double[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Object2DoubleOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Object2DoubleOpenCustomHashMap(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Object2DoubleOpenCustomHashMap(Map<? extends K, ? extends Double> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2DoubleOpenCustomHashMap(Map<? extends K, ? extends Double> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2DoubleOpenCustomHashMap(Object2DoubleMap<K> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2DoubleOpenCustomHashMap(Object2DoubleMap<K> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2DoubleOpenCustomHashMap(K[] local_k, double[] local_v, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2DoubleOpenCustomHashMap(K[] local_k, double[] local_v, Hash.Strategy<K> strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public Hash.Strategy<K> strategy()
  {
    return this.strategy;
  }
  
  public double put(K local_k, double local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        double oldValue = this.value[pos];
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return this.defRetValue;
  }
  
  public Double put(K local_ok, Double local_ov)
  {
    double local_v = local_ov.doubleValue();
    K local_k = local_ok;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        Double oldValue = Double.valueOf(this.value[pos]);
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return null;
  }
  
  public double add(K local_k, double incr)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        double oldValue = this.value[pos];
        this.value[pos] += incr;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = (this.defRetValue + incr);
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return this.defRetValue;
  }
  
  protected final int shiftKeys(int pos)
  {
    int last;
    for (;;)
    {
      for (pos = (last = pos) + 1 & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask)
      {
        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (this.used[pos] == 0) {
        break;
      }
      this.key[last] = this.key[pos];
      this.value[last] = this.value[pos];
    }
    this.used[last] = false;
    this.key[last] = null;
    return last;
  }
  
  public double removeDouble(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        double local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Double remove(Object local_ok)
  {
    K local_k = local_ok;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        double local_v = this.value[pos];
        shiftKeys(pos);
        return Double.valueOf(local_v);
      }
    }
    return null;
  }
  
  public double getDouble(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(double local_v)
  {
    double[] value = this.value;
    boolean[] used = this.used;
    int local_i = this.field_49;
    while (local_i-- != 0) {
      if ((used[local_i] != 0) && (value[local_i] == local_v)) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    if (this.size == 0) {
      return;
    }
    this.size = 0;
    BooleanArrays.fill(this.used, false);
    ObjectArrays.fill(this.key, null);
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  @Deprecated
  public void growthFactor(int growthFactor) {}
  
  @Deprecated
  public int growthFactor()
  {
    return 16;
  }
  
  public Object2DoubleMap.FastEntrySet<K> object2DoubleEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ObjectSet<K> keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public DoubleCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractDoubleCollection()
      {
        public DoubleIterator iterator()
        {
          return new Object2DoubleOpenCustomHashMap.ValueIterator(Object2DoubleOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Object2DoubleOpenCustomHashMap.this.size;
        }
        
        public boolean contains(double local_v)
        {
          return Object2DoubleOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2DoubleOpenCustomHashMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  @Deprecated
  public boolean rehash()
  {
    return true;
  }
  
  public boolean trim()
  {
    int local_l = HashCommon.arraySize(this.size, this.field_48);
    if (local_l >= this.field_49) {
      return true;
    }
    try
    {
      rehash(local_l);
    }
    catch (OutOfMemoryError cantDoIt)
    {
      return false;
    }
    return true;
  }
  
  public boolean trim(int local_n)
  {
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_48));
    if (this.field_49 <= local_l) {
      return true;
    }
    try
    {
      rehash(local_l);
    }
    catch (OutOfMemoryError cantDoIt)
    {
      return false;
    }
    return true;
  }
  
  protected void rehash(int newN)
  {
    int local_i = 0;
    boolean[] used = this.used;
    K[] key = this.key;
    double[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    double[] newValue = new double[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      K local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      newValue[pos] = value[local_i];
      local_i++;
    }
    this.field_49 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.key = newKey;
    this.value = newValue;
    this.used = newUsed;
  }
  
  public Object2DoubleOpenCustomHashMap<K> clone()
  {
    Object2DoubleOpenCustomHashMap<K> local_c;
    try
    {
      local_c = (Object2DoubleOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((double[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.strategy = this.strategy;
    return local_c;
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_j = this.size;
    int local_i = 0;
    int local_t = 0;
    while (local_j-- != 0)
    {
      while (this.used[local_i] == 0) {
        local_i++;
      }
      if (this != this.key[local_i]) {
        local_t = this.strategy.hashCode(this.key[local_i]);
      }
      local_t ^= HashCommon.double2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    K[] key = this.key;
    double[] value = this.value;
    Object2DoubleOpenCustomHashMap<K>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeObject(key[local_e]);
      local_s.writeDouble(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    K[] key = this.key = (Object[])new Object[this.field_49];
    double[] value = this.value = new double[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      double local_v = local_s.readDouble();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Object2DoubleOpenCustomHashMap.MapIterator
    implements DoubleIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Object2DoubleOpenCustomHashMap.this.value[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Object2DoubleOpenCustomHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractObjectSet<K>
  {
    private KeySet() {}
    
    public ObjectIterator<K> iterator()
    {
      return new Object2DoubleOpenCustomHashMap.KeyIterator(Object2DoubleOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Object2DoubleOpenCustomHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2DoubleOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2DoubleOpenCustomHashMap.this.size;
      Object2DoubleOpenCustomHashMap.this.remove(local_k);
      return Object2DoubleOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2DoubleOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Object2DoubleOpenCustomHashMap<K>.MapIterator
    implements ObjectIterator<K>
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public K next()
    {
      return Object2DoubleOpenCustomHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Object2DoubleMap.Entry<K>>
    implements Object2DoubleMap.FastEntrySet<K>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Object2DoubleMap.Entry<K>> iterator()
    {
      return new Object2DoubleOpenCustomHashMap.EntryIterator(Object2DoubleOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Object2DoubleMap.Entry<K>> fastIterator()
    {
      return new Object2DoubleOpenCustomHashMap.FastEntryIterator(Object2DoubleOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Double> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2DoubleOpenCustomHashMap.this.mask; Object2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2DoubleOpenCustomHashMap.this.mask) {
        if (Object2DoubleOpenCustomHashMap.this.strategy.equals(Object2DoubleOpenCustomHashMap.this.key[pos], local_k)) {
          return Object2DoubleOpenCustomHashMap.this.value[pos] == ((Double)local_e.getValue()).doubleValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Double> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2DoubleOpenCustomHashMap.this.mask; Object2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2DoubleOpenCustomHashMap.this.mask) {
        if (Object2DoubleOpenCustomHashMap.this.strategy.equals(Object2DoubleOpenCustomHashMap.this.key[pos], local_k))
        {
          Object2DoubleOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2DoubleOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Object2DoubleOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Object2DoubleOpenCustomHashMap<K>.MapIterator
    implements ObjectIterator<Object2DoubleMap.Entry<K>>
  {
    final AbstractObject2DoubleMap.BasicEntry<K> entry = new AbstractObject2DoubleMap.BasicEntry(null, 0.0D);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractObject2DoubleMap.BasicEntry<K> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2DoubleOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2DoubleOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Object2DoubleOpenCustomHashMap<K>.MapIterator
    implements ObjectIterator<Object2DoubleMap.Entry<K>>
  {
    private Object2DoubleOpenCustomHashMap<K>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Object2DoubleMap.Entry<K> next()
    {
      return this.entry = new Object2DoubleOpenCustomHashMap.MapEntry(Object2DoubleOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2DoubleOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Object2DoubleOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_482 = Object2DoubleOpenCustomHashMap.this.size;
    ObjectArrayList<K> wrapped;
    
    private MapIterator()
    {
      boolean[] used = Object2DoubleOpenCustomHashMap.this.used;
      while ((this.field_482 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_482 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_482 -= 1;
      if (this.pos < 0)
      {
        Object local_k = this.wrapped.get(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Object2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2DoubleOpenCustomHashMap.this.mask; Object2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2DoubleOpenCustomHashMap.this.mask) {
          if (Object2DoubleOpenCustomHashMap.this.strategy.equals(Object2DoubleOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_482 != 0)
      {
        boolean[] local_k = Object2DoubleOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2DoubleOpenCustomHashMap.this.mask; Object2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2DoubleOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Object2DoubleOpenCustomHashMap.this.strategy.hashCode(Object2DoubleOpenCustomHashMap.this.key[pos])) & Object2DoubleOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2DoubleOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ObjectArrayList();
          }
          this.wrapped.add(Object2DoubleOpenCustomHashMap.this.key[pos]);
        }
        Object2DoubleOpenCustomHashMap.this.key[last] = Object2DoubleOpenCustomHashMap.this.key[pos];
        Object2DoubleOpenCustomHashMap.this.value[last] = Object2DoubleOpenCustomHashMap.this.value[pos];
      }
      Object2DoubleOpenCustomHashMap.this.used[last] = false;
      Object2DoubleOpenCustomHashMap.this.key[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Object2DoubleOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
        this.last = -1;
        return;
      }
      Object2DoubleOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_482 > 0))
      {
        this.field_482 += 1;
        nextEntry();
      }
      this.last = -1;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private final class MapEntry
    implements Object2DoubleMap.Entry<K>, Map.Entry<K, Double>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2DoubleOpenCustomHashMap.this.key[this.index];
    }
    
    public Double getValue()
    {
      return Double.valueOf(Object2DoubleOpenCustomHashMap.this.value[this.index]);
    }
    
    public double getDoubleValue()
    {
      return Object2DoubleOpenCustomHashMap.this.value[this.index];
    }
    
    public double setValue(double local_v)
    {
      double oldValue = Object2DoubleOpenCustomHashMap.this.value[this.index];
      Object2DoubleOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Double setValue(Double local_v)
    {
      return Double.valueOf(setValue(local_v.doubleValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Double> local_e = (Map.Entry)local_o;
      return (Object2DoubleOpenCustomHashMap.this.strategy.equals(Object2DoubleOpenCustomHashMap.this.key[this.index], local_e.getKey())) && (Object2DoubleOpenCustomHashMap.this.value[this.index] == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return Object2DoubleOpenCustomHashMap.this.strategy.hashCode(Object2DoubleOpenCustomHashMap.this.key[this.index]) ^ HashCommon.double2int(Object2DoubleOpenCustomHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Object2DoubleOpenCustomHashMap.this.key[this.index] + "=>" + Object2DoubleOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2DoubleOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */