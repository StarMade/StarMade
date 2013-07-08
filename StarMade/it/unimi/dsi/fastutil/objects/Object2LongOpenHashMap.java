package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2LongOpenHashMap<K>
  extends AbstractObject2LongMap<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient long[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Object2LongMap.FastEntrySet<K> entries;
  protected volatile transient ObjectSet<K> keys;
  protected volatile transient LongCollection values;
  
  public Object2LongOpenHashMap(int expected, float local_f)
  {
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
    this.value = new long[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Object2LongOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Object2LongOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Object2LongOpenHashMap(Map<? extends K, ? extends Long> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Object2LongOpenHashMap(Map<? extends K, ? extends Long> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Object2LongOpenHashMap(Object2LongMap<K> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Object2LongOpenHashMap(Object2LongMap<K> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Object2LongOpenHashMap(K[] local_k, long[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2LongOpenHashMap(K[] local_k, long[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public long put(K local_k, long local_v)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        long oldValue = this.value[pos];
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
  
  public Long put(K local_ok, Long local_ov)
  {
    long local_v = local_ov.longValue();
    K local_k = local_ok;
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        Long oldValue = Long.valueOf(this.value[pos]);
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
  
  public long add(K local_k, long incr)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        long oldValue = this.value[pos];
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
        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
  
  public long removeLong(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        this.size -= 1;
        long local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Long remove(Object local_ok)
  {
    K local_k = local_ok;
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        this.size -= 1;
        long local_v = this.value[pos];
        shiftKeys(pos);
        return Long.valueOf(local_v);
      }
    }
    return null;
  }
  
  public long getLong(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(long local_v)
  {
    long[] value = this.value;
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
  
  public Object2LongMap.FastEntrySet<K> object2LongEntrySet()
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
  
  public LongCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractLongCollection()
      {
        public LongIterator iterator()
        {
          return new Object2LongOpenHashMap.ValueIterator(Object2LongOpenHashMap.this);
        }
        
        public int size()
        {
          return Object2LongOpenHashMap.this.size;
        }
        
        public boolean contains(long local_v)
        {
          return Object2LongOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2LongOpenHashMap.this.clear();
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
    long[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    long[] newValue = new long[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      K local_k = key[local_i];
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Object2LongOpenHashMap<K> clone()
  {
    Object2LongOpenHashMap<K> local_c;
    try
    {
      local_c = (Object2LongOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((long[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
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
        local_t = this.key[local_i] == null ? 0 : this.key[local_i].hashCode();
      }
      local_t ^= HashCommon.long2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    K[] key = this.key;
    long[] value = this.value;
    Object2LongOpenHashMap<K>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeObject(key[local_e]);
      local_s.writeLong(value[local_e]);
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
    long[] value = this.value = new long[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      long local_v = local_s.readLong();
      for (pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Object2LongOpenHashMap.MapIterator
    implements LongIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public long nextLong()
    {
      return Object2LongOpenHashMap.this.value[nextEntry()];
    }
    
    public Long next()
    {
      return Long.valueOf(Object2LongOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractObjectSet<K>
  {
    private KeySet() {}
    
    public ObjectIterator<K> iterator()
    {
      return new Object2LongOpenHashMap.KeyIterator(Object2LongOpenHashMap.this);
    }
    
    public int size()
    {
      return Object2LongOpenHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2LongOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2LongOpenHashMap.this.size;
      Object2LongOpenHashMap.this.remove(local_k);
      return Object2LongOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2LongOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Object2LongOpenHashMap<K>.MapIterator
    implements ObjectIterator<K>
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public K next()
    {
      return Object2LongOpenHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Object2LongMap.Entry<K>>
    implements Object2LongMap.FastEntrySet<K>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Object2LongMap.Entry<K>> iterator()
    {
      return new Object2LongOpenHashMap.EntryIterator(Object2LongOpenHashMap.this, null);
    }
    
    public ObjectIterator<Object2LongMap.Entry<K>> fastIterator()
    {
      return new Object2LongOpenHashMap.FastEntryIterator(Object2LongOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Long> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2LongOpenHashMap.this.mask; Object2LongOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2LongOpenHashMap.this.mask) {
        if (Object2LongOpenHashMap.this.key[pos] == null ? local_k == null : Object2LongOpenHashMap.this.key[pos].equals(local_k)) {
          return Object2LongOpenHashMap.this.value[pos] == ((Long)local_e.getValue()).longValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Long> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2LongOpenHashMap.this.mask; Object2LongOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2LongOpenHashMap.this.mask) {
        if (Object2LongOpenHashMap.this.key[pos] == null ? local_k == null : Object2LongOpenHashMap.this.key[pos].equals(local_k))
        {
          Object2LongOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2LongOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Object2LongOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Object2LongOpenHashMap<K>.MapIterator
    implements ObjectIterator<Object2LongMap.Entry<K>>
  {
    final AbstractObject2LongMap.BasicEntry<K> entry = new AbstractObject2LongMap.BasicEntry(null, 0L);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractObject2LongMap.BasicEntry<K> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2LongOpenHashMap.this.key[local_e];
      this.entry.value = Object2LongOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Object2LongOpenHashMap<K>.MapIterator
    implements ObjectIterator<Object2LongMap.Entry<K>>
  {
    private Object2LongOpenHashMap<K>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Object2LongMap.Entry<K> next()
    {
      return this.entry = new Object2LongOpenHashMap.MapEntry(Object2LongOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2LongOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Object2LongOpenHashMap.this.field_49;
    int last = -1;
    int field_2021 = Object2LongOpenHashMap.this.size;
    ObjectArrayList<K> wrapped;
    
    private MapIterator()
    {
      boolean[] used = Object2LongOpenHashMap.this.used;
      while ((this.field_2021 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_2021 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_2021 -= 1;
      if (this.pos < 0)
      {
        Object local_k = this.wrapped.get(-(this.last = --this.pos) - 2);
        for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2LongOpenHashMap.this.mask; Object2LongOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2LongOpenHashMap.this.mask) {
          if (Object2LongOpenHashMap.this.key[pos] == null ? local_k == null : Object2LongOpenHashMap.this.key[pos].equals(local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_2021 != 0)
      {
        boolean[] local_k = Object2LongOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2LongOpenHashMap.this.mask; Object2LongOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2LongOpenHashMap.this.mask)
        {
          int slot = (Object2LongOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2LongOpenHashMap.this.key[pos].hashCode())) & Object2LongOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2LongOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ObjectArrayList();
          }
          this.wrapped.add(Object2LongOpenHashMap.this.key[pos]);
        }
        Object2LongOpenHashMap.this.key[last] = Object2LongOpenHashMap.this.key[pos];
        Object2LongOpenHashMap.this.value[last] = Object2LongOpenHashMap.this.value[pos];
      }
      Object2LongOpenHashMap.this.used[last] = false;
      Object2LongOpenHashMap.this.key[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Object2LongOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
        this.last = -1;
        return;
      }
      Object2LongOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_2021 > 0))
      {
        this.field_2021 += 1;
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
    implements Object2LongMap.Entry<K>, Map.Entry<K, Long>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2LongOpenHashMap.this.key[this.index];
    }
    
    public Long getValue()
    {
      return Long.valueOf(Object2LongOpenHashMap.this.value[this.index]);
    }
    
    public long getLongValue()
    {
      return Object2LongOpenHashMap.this.value[this.index];
    }
    
    public long setValue(long local_v)
    {
      long oldValue = Object2LongOpenHashMap.this.value[this.index];
      Object2LongOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Long setValue(Long local_v)
    {
      return Long.valueOf(setValue(local_v.longValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Long> local_e = (Map.Entry)local_o;
      return (Object2LongOpenHashMap.this.key[this.index] == null ? local_e.getKey() == null : Object2LongOpenHashMap.this.key[this.index].equals(local_e.getKey())) && (Object2LongOpenHashMap.this.value[this.index] == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return (Object2LongOpenHashMap.this.key[this.index] == null ? 0 : Object2LongOpenHashMap.this.key[this.index].hashCode()) ^ HashCommon.long2int(Object2LongOpenHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Object2LongOpenHashMap.this.key[this.index] + "=>" + Object2LongOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */