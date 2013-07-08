package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2ReferenceOpenCustomHashMap<K, V>
  extends AbstractObject2ReferenceMap<K, V>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient V[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Object2ReferenceMap.FastEntrySet<K, V> entries;
  protected volatile transient ObjectSet<K> keys;
  protected volatile transient ReferenceCollection<V> values;
  protected Hash.Strategy<K> strategy;
  
  public Object2ReferenceOpenCustomHashMap(int expected, float local_f, Hash.Strategy<K> strategy)
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
    this.value = ((Object[])new Object[this.field_49]);
    this.used = new boolean[this.field_49];
  }
  
  public Object2ReferenceOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Object2ReferenceOpenCustomHashMap(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Object2ReferenceOpenCustomHashMap(Map<? extends K, ? extends V> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ReferenceOpenCustomHashMap(Map<? extends K, ? extends V> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ReferenceOpenCustomHashMap(Object2ReferenceMap<K, V> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ReferenceOpenCustomHashMap(Object2ReferenceMap<K, V> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ReferenceOpenCustomHashMap(K[] local_k, V[] local_v, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2ReferenceOpenCustomHashMap(K[] local_k, V[] local_v, Hash.Strategy<K> strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public Hash.Strategy<K> strategy()
  {
    return this.strategy;
  }
  
  public V put(K local_k, V local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        V oldValue = this.value[pos];
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
    this.value[last] = null;
    return last;
  }
  
  public V remove(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V get(Object local_k)
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
  
  public boolean containsValue(Object local_v)
  {
    V[] value = this.value;
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
    ObjectArrays.fill(this.value, null);
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
  
  public Object2ReferenceMap.FastEntrySet<K, V> object2ReferenceEntrySet()
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
  
  public ReferenceCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractReferenceCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Object2ReferenceOpenCustomHashMap.ValueIterator(Object2ReferenceOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Object2ReferenceOpenCustomHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Object2ReferenceOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2ReferenceOpenCustomHashMap.this.clear();
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
    V[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    V[] newValue = (Object[])new Object[newN];
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
  
  public Object2ReferenceOpenCustomHashMap<K, V> clone()
  {
    Object2ReferenceOpenCustomHashMap<K, V> local_c;
    try
    {
      local_c = (Object2ReferenceOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
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
      if (this != this.value[local_i]) {
        local_t ^= (this.value[local_i] == null ? 0 : System.identityHashCode(this.value[local_i]));
      }
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    K[] key = this.key;
    V[] value = this.value;
    Object2ReferenceOpenCustomHashMap<K, V>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeObject(key[local_e]);
      local_s.writeObject(value[local_e]);
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
    V[] value = this.value = (Object[])new Object[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      V local_v = local_s.readObject();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Object2ReferenceOpenCustomHashMap<K, V>.MapIterator
    implements ObjectIterator<V>
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public V next()
    {
      return Object2ReferenceOpenCustomHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractObjectSet<K>
  {
    private KeySet() {}
    
    public ObjectIterator<K> iterator()
    {
      return new Object2ReferenceOpenCustomHashMap.KeyIterator(Object2ReferenceOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Object2ReferenceOpenCustomHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2ReferenceOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2ReferenceOpenCustomHashMap.this.size;
      Object2ReferenceOpenCustomHashMap.this.remove(local_k);
      return Object2ReferenceOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2ReferenceOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Object2ReferenceOpenCustomHashMap<K, V>.MapIterator
    implements ObjectIterator<K>
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public K next()
    {
      return Object2ReferenceOpenCustomHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Object2ReferenceMap.Entry<K, V>>
    implements Object2ReferenceMap.FastEntrySet<K, V>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Object2ReferenceMap.Entry<K, V>> iterator()
    {
      return new Object2ReferenceOpenCustomHashMap.EntryIterator(Object2ReferenceOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Object2ReferenceMap.Entry<K, V>> fastIterator()
    {
      return new Object2ReferenceOpenCustomHashMap.FastEntryIterator(Object2ReferenceOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ReferenceOpenCustomHashMap.this.mask; Object2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceOpenCustomHashMap.this.mask) {
        if (Object2ReferenceOpenCustomHashMap.this.strategy.equals(Object2ReferenceOpenCustomHashMap.this.key[pos], local_k)) {
          return Object2ReferenceOpenCustomHashMap.this.value[pos] == local_e.getValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ReferenceOpenCustomHashMap.this.mask; Object2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceOpenCustomHashMap.this.mask) {
        if (Object2ReferenceOpenCustomHashMap.this.strategy.equals(Object2ReferenceOpenCustomHashMap.this.key[pos], local_k))
        {
          Object2ReferenceOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2ReferenceOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Object2ReferenceOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Object2ReferenceOpenCustomHashMap<K, V>.MapIterator
    implements ObjectIterator<Object2ReferenceMap.Entry<K, V>>
  {
    final AbstractObject2ReferenceMap.BasicEntry<K, V> entry = new AbstractObject2ReferenceMap.BasicEntry(null, null);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractObject2ReferenceMap.BasicEntry<K, V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2ReferenceOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2ReferenceOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Object2ReferenceOpenCustomHashMap<K, V>.MapIterator
    implements ObjectIterator<Object2ReferenceMap.Entry<K, V>>
  {
    private Object2ReferenceOpenCustomHashMap<K, V>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Object2ReferenceMap.Entry<K, V> next()
    {
      return this.entry = new Object2ReferenceOpenCustomHashMap.MapEntry(Object2ReferenceOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2ReferenceOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Object2ReferenceOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_569 = Object2ReferenceOpenCustomHashMap.this.size;
    ObjectArrayList<K> wrapped;
    
    private MapIterator()
    {
      boolean[] used = Object2ReferenceOpenCustomHashMap.this.used;
      while ((this.field_569 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_569 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_569 -= 1;
      if (this.pos < 0)
      {
        Object local_k = this.wrapped.get(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Object2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ReferenceOpenCustomHashMap.this.mask; Object2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceOpenCustomHashMap.this.mask) {
          if (Object2ReferenceOpenCustomHashMap.this.strategy.equals(Object2ReferenceOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_569 != 0)
      {
        boolean[] local_k = Object2ReferenceOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2ReferenceOpenCustomHashMap.this.mask; Object2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Object2ReferenceOpenCustomHashMap.this.strategy.hashCode(Object2ReferenceOpenCustomHashMap.this.key[pos])) & Object2ReferenceOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2ReferenceOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ObjectArrayList();
          }
          this.wrapped.add(Object2ReferenceOpenCustomHashMap.this.key[pos]);
        }
        Object2ReferenceOpenCustomHashMap.this.key[last] = Object2ReferenceOpenCustomHashMap.this.key[pos];
        Object2ReferenceOpenCustomHashMap.this.value[last] = Object2ReferenceOpenCustomHashMap.this.value[pos];
      }
      Object2ReferenceOpenCustomHashMap.this.used[last] = false;
      Object2ReferenceOpenCustomHashMap.this.key[last] = null;
      Object2ReferenceOpenCustomHashMap.this.value[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Object2ReferenceOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
        this.last = -1;
        return;
      }
      Object2ReferenceOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_569 > 0))
      {
        this.field_569 += 1;
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
    implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2ReferenceOpenCustomHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Object2ReferenceOpenCustomHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Object2ReferenceOpenCustomHashMap.this.value[this.index];
      Object2ReferenceOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      return (Object2ReferenceOpenCustomHashMap.this.strategy.equals(Object2ReferenceOpenCustomHashMap.this.key[this.index], local_e.getKey())) && (Object2ReferenceOpenCustomHashMap.this.value[this.index] == local_e.getValue());
    }
    
    public int hashCode()
    {
      return Object2ReferenceOpenCustomHashMap.this.strategy.hashCode(Object2ReferenceOpenCustomHashMap.this.key[this.index]) ^ (Object2ReferenceOpenCustomHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Object2ReferenceOpenCustomHashMap.this.value[this.index]));
    }
    
    public String toString()
    {
      return Object2ReferenceOpenCustomHashMap.this.key[this.index] + "=>" + Object2ReferenceOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */