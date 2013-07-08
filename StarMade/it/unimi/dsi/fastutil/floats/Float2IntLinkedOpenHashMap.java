package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
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

public class Float2IntLinkedOpenHashMap
  extends AbstractFloat2IntSortedMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient float[] key;
  protected transient int[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Float2IntSortedMap.FastSortedEntrySet entries;
  protected volatile transient FloatSortedSet keys;
  protected volatile transient IntCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Float2IntLinkedOpenHashMap(int expected, float local_f)
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
    this.key = new float[this.field_49];
    this.value = new int[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Float2IntLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Float2IntLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Float2IntLinkedOpenHashMap(Map<? extends Float, ? extends Integer> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Float2IntLinkedOpenHashMap(Map<? extends Float, ? extends Integer> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Float2IntLinkedOpenHashMap(Float2IntMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Float2IntLinkedOpenHashMap(Float2IntMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Float2IntLinkedOpenHashMap(float[] local_k, int[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Float2IntLinkedOpenHashMap(float[] local_k, int[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public int put(float local_k, int local_v)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        int oldValue = this.value[pos];
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
  
  public Integer put(Float local_ok, Integer local_ov)
  {
    int local_v = local_ov.intValue();
    float local_k = local_ok.floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Integer oldValue = Integer.valueOf(this.value[pos]);
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
  
  public int add(float local_k, int incr)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        int oldValue = this.value[pos];
        this.value[pos] += incr;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = (this.defRetValue + incr);
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
        int slot = HashCommon.murmurHash3(HashCommon.float2int(this.key[pos])) & this.mask;
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
    return last;
  }
  
  public int remove(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        int local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Integer remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        int local_v = this.value[pos];
        shiftKeys(pos);
        return Integer.valueOf(local_v);
      }
    }
    return null;
  }
  
  public int removeFirstInt()
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
    int local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public int removeLastInt()
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
    int local_v = this.value[pos];
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
  
  public int getAndMoveToFirst(float local_k)
  {
    float[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public int getAndMoveToLast(float local_k)
  {
    float[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public int putAndMoveToFirst(float local_k, int local_v)
  {
    float[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        int oldValue = this.value[pos];
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
  
  public int putAndMoveToLast(float local_k, int local_v)
  {
    float[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        int oldValue = this.value[pos];
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
  
  public Integer get(Float local_ok)
  {
    float local_k = local_ok.floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Integer.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public int get(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(int local_v)
  {
    int[] value = this.value;
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
  
  public float firstFloatKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public float lastFloatKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public FloatComparator comparator()
  {
    return null;
  }
  
  public Float2IntSortedMap tailMap(float from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Float2IntSortedMap headMap(float local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Float2IntSortedMap subMap(float from, float local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Float2IntSortedMap.FastSortedEntrySet float2IntEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public FloatSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public IntCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractIntCollection()
      {
        public IntIterator iterator()
        {
          return new Float2IntLinkedOpenHashMap.ValueIterator(Float2IntLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Float2IntLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(int local_v)
        {
          return Float2IntLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Float2IntLinkedOpenHashMap.this.clear();
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
    float[] key = this.key;
    int[] value = this.value;
    int newMask = newN - 1;
    float[] newKey = new float[newN];
    int[] newValue = new int[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      float local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Float2IntLinkedOpenHashMap clone()
  {
    Float2IntLinkedOpenHashMap local_c;
    try
    {
      local_c = (Float2IntLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((float[])this.key.clone());
    local_c.value = ((int[])this.value.clone());
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
      local_t = HashCommon.float2int(this.key[local_i]);
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    float[] key = this.key;
    int[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeFloat(key[local_e]);
      local_s.writeInt(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    float[] key = this.key = new float[this.field_49];
    int[] value = this.value = new int[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      float local_k = local_s.readFloat();
      int local_v = local_s.readInt();
      for (pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
    extends Float2IntLinkedOpenHashMap.MapIterator
    implements IntListIterator
  {
    public int previousInt()
    {
      return Float2IntLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public Integer previous()
    {
      return Integer.valueOf(Float2IntLinkedOpenHashMap.this.value[previousEntry()]);
    }
    
    public void set(Integer local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Integer local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(int local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public int nextInt()
    {
      return Float2IntLinkedOpenHashMap.this.value[nextEntry()];
    }
    
    public Integer next()
    {
      return Integer.valueOf(Float2IntLinkedOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractFloatSortedSet
  {
    private KeySet() {}
    
    public FloatListIterator iterator(float from)
    {
      return new Float2IntLinkedOpenHashMap.KeyIterator(Float2IntLinkedOpenHashMap.this, from);
    }
    
    public FloatListIterator iterator()
    {
      return new Float2IntLinkedOpenHashMap.KeyIterator(Float2IntLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Float2IntLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(float local_k)
    {
      return Float2IntLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(float local_k)
    {
      int oldSize = Float2IntLinkedOpenHashMap.this.size;
      Float2IntLinkedOpenHashMap.this.remove(local_k);
      return Float2IntLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Float2IntLinkedOpenHashMap.this.clear();
    }
    
    public float firstFloat()
    {
      if (Float2IntLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.first];
    }
    
    public float lastFloat()
    {
      if (Float2IntLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.last];
    }
    
    public FloatComparator comparator()
    {
      return null;
    }
    
    public final FloatSortedSet tailSet(float from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final FloatSortedSet headSet(float local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final FloatSortedSet subSet(float from, float local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Float2IntLinkedOpenHashMap.MapIterator
    implements FloatListIterator
  {
    public KeyIterator(float local_k)
    {
      super(local_k, null);
    }
    
    public float previousFloat()
    {
      return Float2IntLinkedOpenHashMap.this.key[previousEntry()];
    }
    
    public void set(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float previous()
    {
      return Float.valueOf(Float2IntLinkedOpenHashMap.this.key[previousEntry()]);
    }
    
    public void set(Float local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Float local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public float nextFloat()
    {
      return Float2IntLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Float next()
    {
      return Float.valueOf(Float2IntLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Float2IntMap.Entry>
    implements Float2IntSortedMap.FastSortedEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Float2IntMap.Entry> iterator()
    {
      return new Float2IntLinkedOpenHashMap.EntryIterator(Float2IntLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Float2IntMap.Entry> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Float2IntMap.Entry> subSet(Float2IntMap.Entry fromElement, Float2IntMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Float2IntMap.Entry> headSet(Float2IntMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Float2IntMap.Entry> tailSet(Float2IntMap.Entry fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float2IntMap.Entry first()
    {
      if (Float2IntLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Float2IntLinkedOpenHashMap.MapEntry(Float2IntLinkedOpenHashMap.this, Float2IntLinkedOpenHashMap.this.first);
    }
    
    public Float2IntMap.Entry last()
    {
      if (Float2IntLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Float2IntLinkedOpenHashMap.MapEntry(Float2IntLinkedOpenHashMap.this, Float2IntLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      float local_k = ((Float)local_e.getKey()).floatValue();
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & Float2IntLinkedOpenHashMap.this.mask; Float2IntLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask) {
        if (Float2IntLinkedOpenHashMap.this.key[pos] == local_k) {
          return Float2IntLinkedOpenHashMap.this.value[pos] == ((Integer)local_e.getValue()).intValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      float local_k = ((Float)local_e.getKey()).floatValue();
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & Float2IntLinkedOpenHashMap.this.mask; Float2IntLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask) {
        if (Float2IntLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Float2IntLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Float2IntLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Float2IntLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Float2IntMap.Entry> iterator(Float2IntMap.Entry from)
    {
      return new Float2IntLinkedOpenHashMap.EntryIterator(Float2IntLinkedOpenHashMap.this, ((Float)from.getKey()).floatValue());
    }
    
    public ObjectBidirectionalIterator<Float2IntMap.Entry> fastIterator()
    {
      return new Float2IntLinkedOpenHashMap.FastEntryIterator(Float2IntLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Float2IntMap.Entry> fastIterator(Float2IntMap.Entry from)
    {
      return new Float2IntLinkedOpenHashMap.FastEntryIterator(Float2IntLinkedOpenHashMap.this, ((Float)from.getKey()).floatValue());
    }
  }
  
  private class FastEntryIterator
    extends Float2IntLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Float2IntMap.Entry>
  {
    final AbstractFloat2IntMap.BasicEntry entry = new AbstractFloat2IntMap.BasicEntry(0.0F, 0);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(float from)
    {
      super(from, null);
    }
    
    public AbstractFloat2IntMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Float2IntLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Float2IntLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractFloat2IntMap.BasicEntry previous()
    {
      int local_e = previousEntry();
      this.entry.key = Float2IntLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Float2IntLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Float2IntMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Float2IntMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Float2IntLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Float2IntMap.Entry>
  {
    private Float2IntLinkedOpenHashMap.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(float from)
    {
      super(from, null);
    }
    
    public Float2IntLinkedOpenHashMap.MapEntry next()
    {
      return this.entry = new Float2IntLinkedOpenHashMap.MapEntry(Float2IntLinkedOpenHashMap.this, nextEntry());
    }
    
    public Float2IntLinkedOpenHashMap.MapEntry previous()
    {
      return this.entry = new Float2IntLinkedOpenHashMap.MapEntry(Float2IntLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Float2IntLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Float2IntMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Float2IntMap.Entry local_ok)
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
      this.next = Float2IntLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(float from)
    {
      if (Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Float2IntLinkedOpenHashMap.this.last;
        this.index = Float2IntLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(HashCommon.float2int(from)) & Float2IntLinkedOpenHashMap.this.mask; Float2IntLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask) {
          if (Float2IntLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Float2IntLinkedOpenHashMap.this.link[pos]);
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
        this.index = Float2IntLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Float2IntLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Float2IntLinkedOpenHashMap.this.link[pos];
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
        return Float2IntLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Float2IntLinkedOpenHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Float2IntLinkedOpenHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Float2IntLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Float2IntLinkedOpenHashMap.this.link[this.curr]);
      }
      Float2IntLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Float2IntLinkedOpenHashMap.this.first = this.next;
      } else {
        Float2IntLinkedOpenHashMap.this.link[this.prev] ^= (Float2IntLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Float2IntLinkedOpenHashMap.this.last = this.prev;
      } else {
        Float2IntLinkedOpenHashMap.this.link[this.next] ^= (Float2IntLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Float2IntLinkedOpenHashMap.this.mask; Float2IntLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(HashCommon.float2int(Float2IntLinkedOpenHashMap.this.key[pos])) & Float2IntLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Float2IntLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Float2IntLinkedOpenHashMap.this.key[last] = Float2IntLinkedOpenHashMap.this.key[pos];
        Float2IntLinkedOpenHashMap.this.value[last] = Float2IntLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Float2IntLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Float2IntLinkedOpenHashMap.this.used[last] = false;
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
    implements Float2IntMap.Entry, Map.Entry<Float, Integer>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Float getKey()
    {
      return Float.valueOf(Float2IntLinkedOpenHashMap.this.key[this.index]);
    }
    
    public float getFloatKey()
    {
      return Float2IntLinkedOpenHashMap.this.key[this.index];
    }
    
    public Integer getValue()
    {
      return Integer.valueOf(Float2IntLinkedOpenHashMap.this.value[this.index]);
    }
    
    public int getIntValue()
    {
      return Float2IntLinkedOpenHashMap.this.value[this.index];
    }
    
    public int setValue(int local_v)
    {
      int oldValue = Float2IntLinkedOpenHashMap.this.value[this.index];
      Float2IntLinkedOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Integer setValue(Integer local_v)
    {
      return Integer.valueOf(setValue(local_v.intValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      return (Float2IntLinkedOpenHashMap.this.key[this.index] == ((Float)local_e.getKey()).floatValue()) && (Float2IntLinkedOpenHashMap.this.value[this.index] == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(Float2IntLinkedOpenHashMap.this.key[this.index]) ^ Float2IntLinkedOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Float2IntLinkedOpenHashMap.this.key[this.index] + "=>" + Float2IntLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */