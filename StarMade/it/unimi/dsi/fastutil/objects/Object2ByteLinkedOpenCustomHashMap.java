package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteListIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2ByteLinkedOpenCustomHashMap<K>
  extends AbstractObject2ByteSortedMap<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient byte[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Object2ByteSortedMap.FastSortedEntrySet<K> entries;
  protected volatile transient ObjectSortedSet<K> keys;
  protected volatile transient ByteCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  protected Hash.Strategy<K> strategy;
  
  public Object2ByteLinkedOpenCustomHashMap(int expected, float local_f, Hash.Strategy<K> strategy)
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
    this.value = new byte[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Object2ByteLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(Map<? extends K, ? extends Byte> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(Map<? extends K, ? extends Byte> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(Object2ByteMap<K> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(Object2ByteMap<K> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ByteLinkedOpenCustomHashMap(K[] local_k, byte[] local_v, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2ByteLinkedOpenCustomHashMap(K[] local_k, byte[] local_v, Hash.Strategy<K> strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public Hash.Strategy<K> strategy()
  {
    return this.strategy;
  }
  
  public byte put(K local_k, byte local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        byte oldValue = this.value[pos];
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
  
  public Byte put(K local_ok, Byte local_ov)
  {
    byte local_v = local_ov.byteValue();
    K local_k = local_ok;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        Byte oldValue = Byte.valueOf(this.value[pos]);
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
    return null;
  }
  
  public byte add(K local_k, byte incr)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        byte oldValue = this.value[pos];
        int tmp60_59 = pos;
        byte[] tmp60_56 = this.value;
        tmp60_56[tmp60_59] = ((byte)(tmp60_56[tmp60_59] + incr));
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = ((byte)(this.defRetValue + incr));
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
      fixPointers(pos, last);
    }
    this.used[last] = false;
    this.key[last] = null;
    return last;
  }
  
  public byte removeByte(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        fixPointers(pos);
        byte local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Byte remove(Object local_ok)
  {
    K local_k = local_ok;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        fixPointers(pos);
        byte local_v = this.value[pos];
        shiftKeys(pos);
        return Byte.valueOf(local_v);
      }
    }
    return null;
  }
  
  public byte removeFirstByte()
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
    byte local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public byte removeLastByte()
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
    byte local_v = this.value[pos];
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
  
  public byte getAndMoveToFirst(K local_k)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public byte getAndMoveToLast(K local_k)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public byte putAndMoveToFirst(K local_k, byte local_v)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        byte oldValue = this.value[pos];
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
  
  public byte putAndMoveToLast(K local_k, byte local_v)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        byte oldValue = this.value[pos];
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
  
  public byte getByte(Object local_k)
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
  
  public boolean containsValue(byte local_v)
  {
    byte[] value = this.value;
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
  
  public K firstKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public K lastKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public Comparator<? super K> comparator()
  {
    return null;
  }
  
  public Object2ByteSortedMap<K> tailMap(K from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ByteSortedMap<K> headMap(K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ByteSortedMap<K> subMap(K from, K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ByteSortedMap.FastSortedEntrySet<K> object2ByteEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ObjectSortedSet<K> keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ByteCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractByteCollection()
      {
        public ByteIterator iterator()
        {
          return new Object2ByteLinkedOpenCustomHashMap.ValueIterator(Object2ByteLinkedOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Object2ByteLinkedOpenCustomHashMap.this.size;
        }
        
        public boolean contains(byte local_v)
        {
          return Object2ByteLinkedOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2ByteLinkedOpenCustomHashMap.this.clear();
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
    K[] key = this.key;
    byte[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    byte[] newValue = new byte[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      K local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Object2ByteLinkedOpenCustomHashMap<K> clone()
  {
    Object2ByteLinkedOpenCustomHashMap<K> local_c;
    try
    {
      local_c = (Object2ByteLinkedOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((byte[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.link = ((long[])this.link.clone());
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
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    K[] key = this.key;
    byte[] value = this.value;
    Object2ByteLinkedOpenCustomHashMap<K>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeObject(key[local_e]);
      local_s.writeByte(value[local_e]);
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
    byte[] value = this.value = new byte[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      byte local_v = local_s.readByte();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
    extends Object2ByteLinkedOpenCustomHashMap.MapIterator
    implements ByteListIterator
  {
    public byte previousByte()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.value[previousEntry()];
    }
    
    public Byte previous()
    {
      return Byte.valueOf(Object2ByteLinkedOpenCustomHashMap.this.value[previousEntry()]);
    }
    
    public void set(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(byte local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public byte nextByte()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.value[nextEntry()];
    }
    
    public Byte next()
    {
      return Byte.valueOf(Object2ByteLinkedOpenCustomHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractObjectSortedSet<K>
  {
    private KeySet() {}
    
    public ObjectListIterator<K> iterator(K from)
    {
      return new Object2ByteLinkedOpenCustomHashMap.KeyIterator(Object2ByteLinkedOpenCustomHashMap.this, from);
    }
    
    public ObjectListIterator<K> iterator()
    {
      return new Object2ByteLinkedOpenCustomHashMap.KeyIterator(Object2ByteLinkedOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2ByteLinkedOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2ByteLinkedOpenCustomHashMap.this.size;
      Object2ByteLinkedOpenCustomHashMap.this.remove(local_k);
      return Object2ByteLinkedOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2ByteLinkedOpenCustomHashMap.this.clear();
    }
    
    public K first()
    {
      if (Object2ByteLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Object2ByteLinkedOpenCustomHashMap.this.key[Object2ByteLinkedOpenCustomHashMap.this.first];
    }
    
    public K last()
    {
      if (Object2ByteLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Object2ByteLinkedOpenCustomHashMap.this.key[Object2ByteLinkedOpenCustomHashMap.this.last];
    }
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public final ObjectSortedSet<K> tailSet(K from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final ObjectSortedSet<K> headSet(K local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final ObjectSortedSet<K> subSet(K from, K local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Object2ByteLinkedOpenCustomHashMap<K>.MapIterator
    implements ObjectListIterator<K>
  {
    public KeyIterator()
    {
      super(local_k, null);
    }
    
    public K previous()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.key[previousEntry()];
    }
    
    public void set(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public K next()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Object2ByteMap.Entry<K>>
    implements Object2ByteSortedMap.FastSortedEntrySet<K>
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator()
    {
      return new Object2ByteLinkedOpenCustomHashMap.EntryIterator(Object2ByteLinkedOpenCustomHashMap.this);
    }
    
    public Comparator<? super Object2ByteMap.Entry<K>> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> subSet(Object2ByteMap.Entry<K> fromElement, Object2ByteMap.Entry<K> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> headSet(Object2ByteMap.Entry<K> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Object2ByteMap.Entry<K>> tailSet(Object2ByteMap.Entry<K> fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object2ByteMap.Entry<K> first()
    {
      if (Object2ByteLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Object2ByteLinkedOpenCustomHashMap.MapEntry(Object2ByteLinkedOpenCustomHashMap.this, Object2ByteLinkedOpenCustomHashMap.this.first);
    }
    
    public Object2ByteMap.Entry<K> last()
    {
      if (Object2ByteLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Object2ByteLinkedOpenCustomHashMap.MapEntry(Object2ByteLinkedOpenCustomHashMap.this, Object2ByteLinkedOpenCustomHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Byte> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2ByteLinkedOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ByteLinkedOpenCustomHashMap.this.mask; Object2ByteLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ByteLinkedOpenCustomHashMap.this.mask) {
        if (Object2ByteLinkedOpenCustomHashMap.this.strategy.equals(Object2ByteLinkedOpenCustomHashMap.this.key[pos], local_k)) {
          return Object2ByteLinkedOpenCustomHashMap.this.value[pos] == ((Byte)local_e.getValue()).byteValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Byte> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2ByteLinkedOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ByteLinkedOpenCustomHashMap.this.mask; Object2ByteLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ByteLinkedOpenCustomHashMap.this.mask) {
        if (Object2ByteLinkedOpenCustomHashMap.this.strategy.equals(Object2ByteLinkedOpenCustomHashMap.this.key[pos], local_k))
        {
          Object2ByteLinkedOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Object2ByteLinkedOpenCustomHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator(Object2ByteMap.Entry<K> from)
    {
      return new Object2ByteLinkedOpenCustomHashMap.EntryIterator(Object2ByteLinkedOpenCustomHashMap.this, from.getKey());
    }
    
    public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> fastIterator()
    {
      return new Object2ByteLinkedOpenCustomHashMap.FastEntryIterator(Object2ByteLinkedOpenCustomHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> fastIterator(Object2ByteMap.Entry<K> from)
    {
      return new Object2ByteLinkedOpenCustomHashMap.FastEntryIterator(Object2ByteLinkedOpenCustomHashMap.this, from.getKey());
    }
  }
  
  private class FastEntryIterator
    extends Object2ByteLinkedOpenCustomHashMap<K>.MapIterator
    implements ObjectListIterator<Object2ByteMap.Entry<K>>
  {
    final AbstractObject2ByteMap.BasicEntry<K> entry = new AbstractObject2ByteMap.BasicEntry(null, (byte)0);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator()
    {
      super(from, null);
    }
    
    public AbstractObject2ByteMap.BasicEntry<K> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2ByteLinkedOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2ByteLinkedOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractObject2ByteMap.BasicEntry<K> previous()
    {
      int local_e = previousEntry();
      this.entry.key = Object2ByteLinkedOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2ByteLinkedOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Object2ByteMap.Entry<K> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2ByteMap.Entry<K> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Object2ByteLinkedOpenCustomHashMap<K>.MapIterator
    implements ObjectListIterator<Object2ByteMap.Entry<K>>
  {
    private Object2ByteLinkedOpenCustomHashMap<K>.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator()
    {
      super(from, null);
    }
    
    public Object2ByteLinkedOpenCustomHashMap<K>.MapEntry next()
    {
      return this.entry = new Object2ByteLinkedOpenCustomHashMap.MapEntry(Object2ByteLinkedOpenCustomHashMap.this, nextEntry());
    }
    
    public Object2ByteLinkedOpenCustomHashMap<K>.MapEntry previous()
    {
      return this.entry = new Object2ByteLinkedOpenCustomHashMap.MapEntry(Object2ByteLinkedOpenCustomHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2ByteLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Object2ByteMap.Entry<K> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2ByteMap.Entry<K> local_ok)
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
      this.next = Object2ByteLinkedOpenCustomHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator()
    {
      if (Object2ByteLinkedOpenCustomHashMap.this.strategy.equals(Object2ByteLinkedOpenCustomHashMap.this.key[Object2ByteLinkedOpenCustomHashMap.this.last], from))
      {
        this.prev = Object2ByteLinkedOpenCustomHashMap.this.last;
        this.index = Object2ByteLinkedOpenCustomHashMap.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(Object2ByteLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2ByteLinkedOpenCustomHashMap.this.mask; Object2ByteLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ByteLinkedOpenCustomHashMap.this.mask) {
          if (Object2ByteLinkedOpenCustomHashMap.this.strategy.equals(Object2ByteLinkedOpenCustomHashMap.this.key[pos], from))
          {
            this.next = ((int)Object2ByteLinkedOpenCustomHashMap.this.link[pos]);
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
        this.index = Object2ByteLinkedOpenCustomHashMap.this.size;
        return;
      }
      int pos = Object2ByteLinkedOpenCustomHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Object2ByteLinkedOpenCustomHashMap.this.link[pos];
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
        return Object2ByteLinkedOpenCustomHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Object2ByteLinkedOpenCustomHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Object2ByteLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Object2ByteLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Object2ByteLinkedOpenCustomHashMap.this.link[this.curr]);
      }
      Object2ByteLinkedOpenCustomHashMap.this.size -= 1;
      if (this.prev == -1) {
        Object2ByteLinkedOpenCustomHashMap.this.first = this.next;
      } else {
        Object2ByteLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2ByteLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Object2ByteLinkedOpenCustomHashMap.this.last = this.prev;
      } else {
        Object2ByteLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2ByteLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2ByteLinkedOpenCustomHashMap.this.mask; Object2ByteLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ByteLinkedOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Object2ByteLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ByteLinkedOpenCustomHashMap.this.key[pos])) & Object2ByteLinkedOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2ByteLinkedOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        Object2ByteLinkedOpenCustomHashMap.this.key[last] = Object2ByteLinkedOpenCustomHashMap.this.key[pos];
        Object2ByteLinkedOpenCustomHashMap.this.value[last] = Object2ByteLinkedOpenCustomHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Object2ByteLinkedOpenCustomHashMap.this.fixPointers(pos, last);
      }
      Object2ByteLinkedOpenCustomHashMap.this.used[last] = false;
      Object2ByteLinkedOpenCustomHashMap.this.key[last] = null;
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
    implements Object2ByteMap.Entry<K>, Map.Entry<K, Byte>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.key[this.index];
    }
    
    public Byte getValue()
    {
      return Byte.valueOf(Object2ByteLinkedOpenCustomHashMap.this.value[this.index]);
    }
    
    public byte getByteValue()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.value[this.index];
    }
    
    public byte setValue(byte local_v)
    {
      byte oldValue = Object2ByteLinkedOpenCustomHashMap.this.value[this.index];
      Object2ByteLinkedOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Byte setValue(Byte local_v)
    {
      return Byte.valueOf(setValue(local_v.byteValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Byte> local_e = (Map.Entry)local_o;
      return (Object2ByteLinkedOpenCustomHashMap.this.strategy.equals(Object2ByteLinkedOpenCustomHashMap.this.key[this.index], local_e.getKey())) && (Object2ByteLinkedOpenCustomHashMap.this.value[this.index] == ((Byte)local_e.getValue()).byteValue());
    }
    
    public int hashCode()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ByteLinkedOpenCustomHashMap.this.key[this.index]) ^ Object2ByteLinkedOpenCustomHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Object2ByteLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2ByteLinkedOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */