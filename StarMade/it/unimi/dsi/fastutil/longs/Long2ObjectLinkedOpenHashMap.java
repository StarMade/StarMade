package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Long2ObjectLinkedOpenHashMap<V>
  extends AbstractLong2ObjectSortedMap<V>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient long[] key;
  protected transient V[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Long2ObjectSortedMap.FastSortedEntrySet<V> entries;
  protected volatile transient LongSortedSet keys;
  protected volatile transient ObjectCollection<V> values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Long2ObjectLinkedOpenHashMap(int expected, float local_f)
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
    this.key = new long[this.field_49];
    this.value = ((Object[])new Object[this.field_49]);
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Long2ObjectLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Long2ObjectLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Long2ObjectLinkedOpenHashMap(Map<? extends Long, ? extends V> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Long2ObjectLinkedOpenHashMap(Map<? extends Long, ? extends V> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Long2ObjectLinkedOpenHashMap(Long2ObjectMap<V> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Long2ObjectLinkedOpenHashMap(Long2ObjectMap<V> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Long2ObjectLinkedOpenHashMap(long[] local_k, V[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Long2ObjectLinkedOpenHashMap(long[] local_k, V[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public V put(long local_k, V local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        V oldValue = this.value[pos];
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return this.defRetValue;
  }
  
  public V put(Long local_ok, V local_ov)
  {
    V local_v = local_ov;
    long local_k = local_ok.longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        V oldValue = this.value[pos];
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
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
        int slot = (int)HashCommon.murmurHash3(this.key[pos]) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (this.used[pos] == 0) {
        break;
      }
      this.key[last] = this.key[pos];
      this.value[last] = this.value[pos];
      fixPointers(pos, last);
    }
    this.used[last] = false;
    this.value[last] = null;
    return last;
  }
  
  public V remove(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V removeFirst()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    this.size -= 1;
    int pos = this.first;
    this.first = ((int)this.link[pos]);
    if (0 <= this.first) {
      this.link[this.first] |= -4294967296L;
    }
    V local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public V removeLast()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    this.size -= 1;
    int pos = this.last;
    this.last = ((int)(this.link[pos] >>> 32));
    if (0 <= this.last) {
      this.link[this.last] |= 4294967295L;
    }
    V local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  private void moveIndexToFirst(int local_i)
  {
    if ((this.size == 1) || (this.first == local_i)) {
      return;
    }
    if (this.last == local_i)
    {
      this.last = ((int)(this.link[local_i] >>> 32));
      this.link[this.last] |= 4294967295L;
    }
    else
    {
      long linki = this.link[local_i];
      int prev = (int)(linki >>> 32);
      int next = (int)linki;
      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
    }
    this.link[this.first] ^= (this.link[this.first] ^ (local_i & 0xFFFFFFFF) << 32) & 0x0;
    this.link[local_i] = (0x0 | this.first & 0xFFFFFFFF);
    this.first = local_i;
  }
  
  private void moveIndexToLast(int local_i)
  {
    if ((this.size == 1) || (this.last == local_i)) {
      return;
    }
    if (this.first == local_i)
    {
      this.first = ((int)this.link[local_i]);
      this.link[this.first] |= -4294967296L;
    }
    else
    {
      long linki = this.link[local_i];
      int prev = (int)(linki >>> 32);
      int next = (int)linki;
      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
    }
    this.link[this.last] ^= (this.link[this.last] ^ local_i & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[local_i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
    this.last = local_i;
  }
  
  public V getAndMoveToFirst(long local_k)
  {
    long[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V getAndMoveToLast(long local_k)
  {
    long[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V putAndMoveToFirst(long local_k, V local_v)
  {
    long[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        V oldValue = this.value[pos];
        this.value[pos] = local_v;
        moveIndexToFirst(pos);
        return oldValue;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
      this.first = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size, this.field_48));
    }
    return this.defRetValue;
  }
  
  public V putAndMoveToLast(long local_k, V local_v)
  {
    long[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        V oldValue = this.value[pos];
        this.value[pos] = local_v;
        moveIndexToLast(pos);
        return oldValue;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size, this.field_48));
    }
    return this.defRetValue;
  }
  
  public V get(Long local_ok)
  {
    long local_k = local_ok.longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V get(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
      if ((used[local_i] != 0) && (value[local_i] == null ? local_v == null : value[local_i].equals(local_v))) {
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
    this.first = (this.last = -1);
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
  
  protected void fixPointers(int local_i)
  {
    if (this.size == 0)
    {
      this.first = (this.last = -1);
      return;
    }
    if (this.first == local_i)
    {
      this.first = ((int)this.link[local_i]);
      if (0 <= this.first) {
        this.link[this.first] |= -4294967296L;
      }
      return;
    }
    if (this.last == local_i)
    {
      this.last = ((int)(this.link[local_i] >>> 32));
      if (0 <= this.last) {
        this.link[this.last] |= 4294967295L;
      }
      return;
    }
    long linki = this.link[local_i];
    int prev = (int)(linki >>> 32);
    int next = (int)linki;
    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
  }
  
  protected void fixPointers(int local_s, int local_d)
  {
    if (this.size == 1)
    {
      this.first = (this.last = local_d);
      this.link[local_d] = -1L;
      return;
    }
    if (this.first == local_s)
    {
      this.first = local_d;
      this.link[((int)this.link[local_s])] ^= (this.link[((int)this.link[local_s])] ^ (local_d & 0xFFFFFFFF) << 32) & 0x0;
      this.link[local_d] = this.link[local_s];
      return;
    }
    if (this.last == local_s)
    {
      this.last = local_d;
      this.link[((int)(this.link[local_s] >>> 32))] ^= (this.link[((int)(this.link[local_s] >>> 32))] ^ local_d & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[local_d] = this.link[local_s];
      return;
    }
    long links = this.link[local_s];
    int prev = (int)(links >>> 32);
    int next = (int)links;
    this.link[prev] ^= (this.link[prev] ^ local_d & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[next] ^= (this.link[next] ^ (local_d & 0xFFFFFFFF) << 32) & 0x0;
    this.link[local_d] = links;
  }
  
  public long firstLongKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public long lastLongKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public LongComparator comparator()
  {
    return null;
  }
  
  public Long2ObjectSortedMap<V> tailMap(long from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Long2ObjectSortedMap<V> headMap(long local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Long2ObjectSortedMap<V> subMap(long from, long local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Long2ObjectSortedMap.FastSortedEntrySet<V> long2ObjectEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public LongSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ObjectCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractObjectCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Long2ObjectLinkedOpenHashMap.ValueIterator(Long2ObjectLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Long2ObjectLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Long2ObjectLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Long2ObjectLinkedOpenHashMap.this.clear();
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
    int local_i = this.first;
    int prev = -1;
    int newPrev = -1;
    long[] key = this.key;
    V[] value = this.value;
    int newMask = newN - 1;
    long[] newKey = new long[newN];
    V[] newValue = (Object[])new Object[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      long local_k = key[local_i];
      for (int pos = (int)HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      newValue[pos] = value[local_i];
      if (prev != -1)
      {
        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
        newPrev = pos;
      }
      else
      {
        newPrev = this.first = pos;
        newLink[pos] = -1L;
      }
      int local_t = local_i;
      local_i = (int)link[local_i];
      prev = local_t;
    }
    this.field_49 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.key = newKey;
    this.value = newValue;
    this.used = newUsed;
    this.link = newLink;
    this.last = newPrev;
    if (newPrev != -1) {
      newLink[newPrev] |= 4294967295L;
    }
  }
  
  public Long2ObjectLinkedOpenHashMap<V> clone()
  {
    Long2ObjectLinkedOpenHashMap<V> local_c;
    try
    {
      local_c = (Long2ObjectLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((long[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.link = ((long[])this.link.clone());
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
      local_t = HashCommon.long2int(this.key[local_i]);
      if (this != this.value[local_i]) {
        local_t ^= (this.value[local_i] == null ? 0 : this.value[local_i].hashCode());
      }
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    long[] key = this.key;
    V[] value = this.value;
    Long2ObjectLinkedOpenHashMap<V>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeLong(key[local_e]);
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
    long[] key = this.key = new long[this.field_49];
    V[] value = this.value = (Object[])new Object[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      long local_k = local_s.readLong();
      V local_v = local_s.readObject();
      for (pos = (int)HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
      if (this.first != -1)
      {
        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
        prev = pos;
      }
      else
      {
        prev = this.first = pos;
        link[pos] |= -4294967296L;
      }
    }
    this.last = prev;
    if (prev != -1) {
      link[prev] |= 4294967295L;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Long2ObjectLinkedOpenHashMap<V>.MapIterator
    implements ObjectListIterator<V>
  {
    public V previous()
    {
      return Long2ObjectLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public void set(V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public V next()
    {
      return Long2ObjectLinkedOpenHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractLongSortedSet
  {
    private KeySet() {}
    
    public LongListIterator iterator(long from)
    {
      return new Long2ObjectLinkedOpenHashMap.KeyIterator(Long2ObjectLinkedOpenHashMap.this, from);
    }
    
    public LongListIterator iterator()
    {
      return new Long2ObjectLinkedOpenHashMap.KeyIterator(Long2ObjectLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Long2ObjectLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(long local_k)
    {
      return Long2ObjectLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(long local_k)
    {
      int oldSize = Long2ObjectLinkedOpenHashMap.this.size;
      Long2ObjectLinkedOpenHashMap.this.remove(local_k);
      return Long2ObjectLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Long2ObjectLinkedOpenHashMap.this.clear();
    }
    
    public long firstLong()
    {
      if (Long2ObjectLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Long2ObjectLinkedOpenHashMap.this.key[Long2ObjectLinkedOpenHashMap.this.first];
    }
    
    public long lastLong()
    {
      if (Long2ObjectLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Long2ObjectLinkedOpenHashMap.this.key[Long2ObjectLinkedOpenHashMap.this.last];
    }
    
    public LongComparator comparator()
    {
      return null;
    }
    
    public final LongSortedSet tailSet(long from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final LongSortedSet headSet(long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final LongSortedSet subSet(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Long2ObjectLinkedOpenHashMap.MapIterator
    implements LongListIterator
  {
    public KeyIterator(long local_k)
    {
      super(local_k, null);
    }
    
    public long previousLong()
    {
      return Long2ObjectLinkedOpenHashMap.this.key[previousEntry()];
    }
    
    public void set(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long previous()
    {
      return Long.valueOf(Long2ObjectLinkedOpenHashMap.this.key[previousEntry()]);
    }
    
    public void set(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public long nextLong()
    {
      return Long2ObjectLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Long next()
    {
      return Long.valueOf(Long2ObjectLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Long2ObjectMap.Entry<V>>
    implements Long2ObjectSortedMap.FastSortedEntrySet<V>
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Long2ObjectMap.Entry<V>> iterator()
    {
      return new Long2ObjectLinkedOpenHashMap.EntryIterator(Long2ObjectLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Long2ObjectMap.Entry<V>> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Long2ObjectMap.Entry<V>> subSet(Long2ObjectMap.Entry<V> fromElement, Long2ObjectMap.Entry<V> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Long2ObjectMap.Entry<V>> headSet(Long2ObjectMap.Entry<V> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Long2ObjectMap.Entry<V>> tailSet(Long2ObjectMap.Entry<V> fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long2ObjectMap.Entry<V> first()
    {
      if (Long2ObjectLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Long2ObjectLinkedOpenHashMap.MapEntry(Long2ObjectLinkedOpenHashMap.this, Long2ObjectLinkedOpenHashMap.this.first);
    }
    
    public Long2ObjectMap.Entry<V> last()
    {
      if (Long2ObjectLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Long2ObjectLinkedOpenHashMap.MapEntry(Long2ObjectLinkedOpenHashMap.this, Long2ObjectLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, V> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      for (int pos = (int)HashCommon.murmurHash3(local_k) & Long2ObjectLinkedOpenHashMap.this.mask; Long2ObjectLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ObjectLinkedOpenHashMap.this.mask) {
        if (Long2ObjectLinkedOpenHashMap.this.key[pos] == local_k) {
          return Long2ObjectLinkedOpenHashMap.this.value[pos] == null ? false : local_e.getValue() == null ? true : Long2ObjectLinkedOpenHashMap.this.value[pos].equals(local_e.getValue());
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, V> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      for (int pos = (int)HashCommon.murmurHash3(local_k) & Long2ObjectLinkedOpenHashMap.this.mask; Long2ObjectLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ObjectLinkedOpenHashMap.this.mask) {
        if (Long2ObjectLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Long2ObjectLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Long2ObjectLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Long2ObjectLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Long2ObjectMap.Entry<V>> iterator(Long2ObjectMap.Entry<V> from)
    {
      return new Long2ObjectLinkedOpenHashMap.EntryIterator(Long2ObjectLinkedOpenHashMap.this, ((Long)from.getKey()).longValue());
    }
    
    public ObjectBidirectionalIterator<Long2ObjectMap.Entry<V>> fastIterator()
    {
      return new Long2ObjectLinkedOpenHashMap.FastEntryIterator(Long2ObjectLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Long2ObjectMap.Entry<V>> fastIterator(Long2ObjectMap.Entry<V> from)
    {
      return new Long2ObjectLinkedOpenHashMap.FastEntryIterator(Long2ObjectLinkedOpenHashMap.this, ((Long)from.getKey()).longValue());
    }
  }
  
  private class FastEntryIterator
    extends Long2ObjectLinkedOpenHashMap<V>.MapIterator
    implements ObjectListIterator<Long2ObjectMap.Entry<V>>
  {
    final AbstractLong2ObjectMap.BasicEntry<V> entry = new AbstractLong2ObjectMap.BasicEntry(0L, null);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(long from)
    {
      super(from, null);
    }
    
    public AbstractLong2ObjectMap.BasicEntry<V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Long2ObjectLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Long2ObjectLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractLong2ObjectMap.BasicEntry<V> previous()
    {
      int local_e = previousEntry();
      this.entry.key = Long2ObjectLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Long2ObjectLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Long2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Long2ObjectLinkedOpenHashMap<V>.MapIterator
    implements ObjectListIterator<Long2ObjectMap.Entry<V>>
  {
    private Long2ObjectLinkedOpenHashMap<V>.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(long from)
    {
      super(from, null);
    }
    
    public Long2ObjectLinkedOpenHashMap<V>.MapEntry next()
    {
      return this.entry = new Long2ObjectLinkedOpenHashMap.MapEntry(Long2ObjectLinkedOpenHashMap.this, nextEntry());
    }
    
    public Long2ObjectLinkedOpenHashMap<V>.MapEntry previous()
    {
      return this.entry = new Long2ObjectLinkedOpenHashMap.MapEntry(Long2ObjectLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Long2ObjectLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Long2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class MapIterator
  {
    int prev = -1;
    int next = -1;
    int curr = -1;
    int index = -1;
    
    private MapIterator()
    {
      this.next = Long2ObjectLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(long from)
    {
      if (Long2ObjectLinkedOpenHashMap.this.key[Long2ObjectLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Long2ObjectLinkedOpenHashMap.this.last;
        this.index = Long2ObjectLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = (int)HashCommon.murmurHash3(from) & Long2ObjectLinkedOpenHashMap.this.mask; Long2ObjectLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ObjectLinkedOpenHashMap.this.mask) {
          if (Long2ObjectLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Long2ObjectLinkedOpenHashMap.this.link[pos]);
            this.prev = pos;
            return;
          }
        }
        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
      }
    }
    
    public boolean hasNext()
    {
      return this.next != -1;
    }
    
    public boolean hasPrevious()
    {
      return this.prev != -1;
    }
    
    private final void ensureIndexKnown()
    {
      if (this.index >= 0) {
        return;
      }
      if (this.prev == -1)
      {
        this.index = 0;
        return;
      }
      if (this.next == -1)
      {
        this.index = Long2ObjectLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Long2ObjectLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Long2ObjectLinkedOpenHashMap.this.link[pos];
      }
    }
    
    public int nextIndex()
    {
      ensureIndexKnown();
      return this.index;
    }
    
    public int previousIndex()
    {
      ensureIndexKnown();
      return this.index - 1;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        return Long2ObjectLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Long2ObjectLinkedOpenHashMap.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return this.curr;
    }
    
    public int previousEntry()
    {
      if (!hasPrevious()) {
        return -1;
      }
      this.curr = this.prev;
      this.prev = ((int)(Long2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return this.curr;
    }
    
    public void remove()
    {
      ensureIndexKnown();
      if (this.curr == -1) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev)
      {
        this.index -= 1;
        this.prev = ((int)(Long2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Long2ObjectLinkedOpenHashMap.this.link[this.curr]);
      }
      Long2ObjectLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Long2ObjectLinkedOpenHashMap.this.first = this.next;
      } else {
        Long2ObjectLinkedOpenHashMap.this.link[this.prev] ^= (Long2ObjectLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Long2ObjectLinkedOpenHashMap.this.last = this.prev;
      } else {
        Long2ObjectLinkedOpenHashMap.this.link[this.next] ^= (Long2ObjectLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Long2ObjectLinkedOpenHashMap.this.mask; Long2ObjectLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ObjectLinkedOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Long2ObjectLinkedOpenHashMap.this.key[pos]) & Long2ObjectLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Long2ObjectLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Long2ObjectLinkedOpenHashMap.this.key[last] = Long2ObjectLinkedOpenHashMap.this.key[pos];
        Long2ObjectLinkedOpenHashMap.this.value[last] = Long2ObjectLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Long2ObjectLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Long2ObjectLinkedOpenHashMap.this.used[last] = false;
      Long2ObjectLinkedOpenHashMap.this.value[last] = null;
      this.curr = -1;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
    
    public int back(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasPrevious())) {
        previousEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private final class MapEntry
    implements Long2ObjectMap.Entry<V>, Map.Entry<Long, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Long getKey()
    {
      return Long.valueOf(Long2ObjectLinkedOpenHashMap.this.key[this.index]);
    }
    
    public long getLongKey()
    {
      return Long2ObjectLinkedOpenHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Long2ObjectLinkedOpenHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Long2ObjectLinkedOpenHashMap.this.value[this.index];
      Long2ObjectLinkedOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, V> local_e = (Map.Entry)local_o;
      return (Long2ObjectLinkedOpenHashMap.this.key[this.index] == ((Long)local_e.getKey()).longValue()) && (Long2ObjectLinkedOpenHashMap.this.value[this.index] == null ? local_e.getValue() == null : Long2ObjectLinkedOpenHashMap.this.value[this.index].equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(Long2ObjectLinkedOpenHashMap.this.key[this.index]) ^ (Long2ObjectLinkedOpenHashMap.this.value[this.index] == null ? 0 : Long2ObjectLinkedOpenHashMap.this.value[this.index].hashCode());
    }
    
    public String toString()
    {
      return Long2ObjectLinkedOpenHashMap.this.key[this.index] + "=>" + Long2ObjectLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */