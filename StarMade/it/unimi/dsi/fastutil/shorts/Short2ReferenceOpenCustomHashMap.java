package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Short2ReferenceOpenCustomHashMap<V>
  extends AbstractShort2ReferenceMap<V>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient short[] key;
  protected transient V[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Short2ReferenceMap.FastEntrySet<V> entries;
  protected volatile transient ShortSet keys;
  protected volatile transient ReferenceCollection<V> values;
  protected ShortHash.Strategy strategy;
  
  public Short2ReferenceOpenCustomHashMap(int expected, float local_f, ShortHash.Strategy strategy)
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
    this.key = new short[this.field_49];
    this.value = ((Object[])new Object[this.field_49]);
    this.used = new boolean[this.field_49];
  }
  
  public Short2ReferenceOpenCustomHashMap(int expected, ShortHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Short2ReferenceOpenCustomHashMap(ShortHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Short2ReferenceOpenCustomHashMap(Map<? extends Short, ? extends V> local_m, float local_f, ShortHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Short2ReferenceOpenCustomHashMap(Map<? extends Short, ? extends V> local_m, ShortHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Short2ReferenceOpenCustomHashMap(Short2ReferenceMap<V> local_m, float local_f, ShortHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Short2ReferenceOpenCustomHashMap(Short2ReferenceMap<V> local_m, ShortHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Short2ReferenceOpenCustomHashMap(short[] local_k, V[] local_v, float local_f, ShortHash.Strategy strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Short2ReferenceOpenCustomHashMap(short[] local_k, V[] local_v, ShortHash.Strategy strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public ShortHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public V put(short local_k, V local_v)
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
  
  public V put(Short local_ok, V local_ov)
  {
    V local_v = local_ov;
    short local_k = local_ok.shortValue();
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
    this.value[last] = null;
    return last;
  }
  
  public V remove(short local_k)
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
  
  public V remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
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
  
  public V get(Short local_ok)
  {
    short local_k = local_ok.shortValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V get(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(short local_k)
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
  
  public Short2ReferenceMap.FastEntrySet<V> short2ReferenceEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ShortSet keySet()
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
          return new Short2ReferenceOpenCustomHashMap.ValueIterator(Short2ReferenceOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Short2ReferenceOpenCustomHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Short2ReferenceOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Short2ReferenceOpenCustomHashMap.this.clear();
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
    short[] key = this.key;
    V[] value = this.value;
    int newMask = newN - 1;
    short[] newKey = new short[newN];
    V[] newValue = (Object[])new Object[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      short local_k = key[local_i];
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
  
  public Short2ReferenceOpenCustomHashMap<V> clone()
  {
    Short2ReferenceOpenCustomHashMap<V> local_c;
    try
    {
      local_c = (Short2ReferenceOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((short[])this.key.clone());
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
      local_t = this.strategy.hashCode(this.key[local_i]);
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
    short[] key = this.key;
    V[] value = this.value;
    Short2ReferenceOpenCustomHashMap<V>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeShort(key[local_e]);
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
    short[] key = this.key = new short[this.field_49];
    V[] value = this.value = (Object[])new Object[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      short local_k = local_s.readShort();
      V local_v = local_s.readObject();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Short2ReferenceOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<V>
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public V next()
    {
      return Short2ReferenceOpenCustomHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractShortSet
  {
    private KeySet() {}
    
    public ShortIterator iterator()
    {
      return new Short2ReferenceOpenCustomHashMap.KeyIterator(Short2ReferenceOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Short2ReferenceOpenCustomHashMap.this.size;
    }
    
    public boolean contains(short local_k)
    {
      return Short2ReferenceOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(short local_k)
    {
      int oldSize = Short2ReferenceOpenCustomHashMap.this.size;
      Short2ReferenceOpenCustomHashMap.this.remove(local_k);
      return Short2ReferenceOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Short2ReferenceOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Short2ReferenceOpenCustomHashMap.MapIterator
    implements ShortIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public short nextShort()
    {
      return Short2ReferenceOpenCustomHashMap.this.key[nextEntry()];
    }
    
    public Short next()
    {
      return Short.valueOf(Short2ReferenceOpenCustomHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Short2ReferenceMap.Entry<V>>
    implements Short2ReferenceMap.FastEntrySet<V>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Short2ReferenceMap.Entry<V>> iterator()
    {
      return new Short2ReferenceOpenCustomHashMap.EntryIterator(Short2ReferenceOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Short2ReferenceMap.Entry<V>> fastIterator()
    {
      return new Short2ReferenceOpenCustomHashMap.FastEntryIterator(Short2ReferenceOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      for (int pos = HashCommon.murmurHash3(Short2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ReferenceOpenCustomHashMap.this.mask; Short2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ReferenceOpenCustomHashMap.this.mask) {
        if (Short2ReferenceOpenCustomHashMap.this.strategy.equals(Short2ReferenceOpenCustomHashMap.this.key[pos], local_k)) {
          return Short2ReferenceOpenCustomHashMap.this.value[pos] == local_e.getValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      for (int pos = HashCommon.murmurHash3(Short2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ReferenceOpenCustomHashMap.this.mask; Short2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ReferenceOpenCustomHashMap.this.mask) {
        if (Short2ReferenceOpenCustomHashMap.this.strategy.equals(Short2ReferenceOpenCustomHashMap.this.key[pos], local_k))
        {
          Short2ReferenceOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Short2ReferenceOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Short2ReferenceOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Short2ReferenceOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<Short2ReferenceMap.Entry<V>>
  {
    final AbstractShort2ReferenceMap.BasicEntry<V> entry = new AbstractShort2ReferenceMap.BasicEntry((short)0, null);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractShort2ReferenceMap.BasicEntry<V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Short2ReferenceOpenCustomHashMap.this.key[local_e];
      this.entry.value = Short2ReferenceOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Short2ReferenceOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<Short2ReferenceMap.Entry<V>>
  {
    private Short2ReferenceOpenCustomHashMap<V>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Short2ReferenceMap.Entry<V> next()
    {
      return this.entry = new Short2ReferenceOpenCustomHashMap.MapEntry(Short2ReferenceOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Short2ReferenceOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Short2ReferenceOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_509 = Short2ReferenceOpenCustomHashMap.this.size;
    ShortArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Short2ReferenceOpenCustomHashMap.this.used;
      while ((this.field_509 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_509 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_509 -= 1;
      if (this.pos < 0)
      {
        short local_k = this.wrapped.getShort(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Short2ReferenceOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ReferenceOpenCustomHashMap.this.mask; Short2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ReferenceOpenCustomHashMap.this.mask) {
          if (Short2ReferenceOpenCustomHashMap.this.strategy.equals(Short2ReferenceOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_509 != 0)
      {
        boolean[] local_k = Short2ReferenceOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Short2ReferenceOpenCustomHashMap.this.mask; Short2ReferenceOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ReferenceOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Short2ReferenceOpenCustomHashMap.this.strategy.hashCode(Short2ReferenceOpenCustomHashMap.this.key[pos])) & Short2ReferenceOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Short2ReferenceOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ShortArrayList();
          }
          this.wrapped.add(Short2ReferenceOpenCustomHashMap.this.key[pos]);
        }
        Short2ReferenceOpenCustomHashMap.this.key[last] = Short2ReferenceOpenCustomHashMap.this.key[pos];
        Short2ReferenceOpenCustomHashMap.this.value[last] = Short2ReferenceOpenCustomHashMap.this.value[pos];
      }
      Short2ReferenceOpenCustomHashMap.this.used[last] = false;
      Short2ReferenceOpenCustomHashMap.this.value[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Short2ReferenceOpenCustomHashMap.this.remove(this.wrapped.getShort(-this.pos - 2));
        this.last = -1;
        return;
      }
      Short2ReferenceOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_509 > 0))
      {
        this.field_509 += 1;
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
    implements Short2ReferenceMap.Entry<V>, Map.Entry<Short, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Short getKey()
    {
      return Short.valueOf(Short2ReferenceOpenCustomHashMap.this.key[this.index]);
    }
    
    public short getShortKey()
    {
      return Short2ReferenceOpenCustomHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Short2ReferenceOpenCustomHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Short2ReferenceOpenCustomHashMap.this.value[this.index];
      Short2ReferenceOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      return (Short2ReferenceOpenCustomHashMap.this.strategy.equals(Short2ReferenceOpenCustomHashMap.this.key[this.index], ((Short)local_e.getKey()).shortValue())) && (Short2ReferenceOpenCustomHashMap.this.value[this.index] == local_e.getValue());
    }
    
    public int hashCode()
    {
      return Short2ReferenceOpenCustomHashMap.this.strategy.hashCode(Short2ReferenceOpenCustomHashMap.this.key[this.index]) ^ (Short2ReferenceOpenCustomHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Short2ReferenceOpenCustomHashMap.this.value[this.index]));
    }
    
    public String toString()
    {
      return Short2ReferenceOpenCustomHashMap.this.key[this.index] + "=>" + Short2ReferenceOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */