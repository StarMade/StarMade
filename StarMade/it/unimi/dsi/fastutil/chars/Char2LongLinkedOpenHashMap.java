package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongListIterator;
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

public class Char2LongLinkedOpenHashMap
  extends AbstractChar2LongSortedMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient char[] key;
  protected transient long[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Char2LongSortedMap.FastSortedEntrySet entries;
  protected volatile transient CharSortedSet keys;
  protected volatile transient LongCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Char2LongLinkedOpenHashMap(int expected, float local_f)
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
    this.key = new char[this.field_49];
    this.value = new long[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Char2LongLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Char2LongLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Char2LongLinkedOpenHashMap(Map<? extends Character, ? extends Long> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Char2LongLinkedOpenHashMap(Map<? extends Character, ? extends Long> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Char2LongLinkedOpenHashMap(Char2LongMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Char2LongLinkedOpenHashMap(Char2LongMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Char2LongLinkedOpenHashMap(char[] local_k, long[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Char2LongLinkedOpenHashMap(char[] local_k, long[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public long put(char local_k, long local_v)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        long oldValue = this.value[pos];
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
  
  public Long put(Character local_ok, Long local_ov)
  {
    long local_v = local_ov.longValue();
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Long oldValue = Long.valueOf(this.value[pos]);
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
  
  public long add(char local_k, long incr)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        long oldValue = this.value[pos];
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
        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
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
  
  public long remove(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        long local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Long remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        long local_v = this.value[pos];
        shiftKeys(pos);
        return Long.valueOf(local_v);
      }
    }
    return null;
  }
  
  public long removeFirstLong()
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
    long local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public long removeLastLong()
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
    long local_v = this.value[pos];
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
  
  public long getAndMoveToFirst(char local_k)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public long getAndMoveToLast(char local_k)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public long putAndMoveToFirst(char local_k, long local_v)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        long oldValue = this.value[pos];
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
  
  public long putAndMoveToLast(char local_k, long local_v)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        long oldValue = this.value[pos];
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
  
  public Long get(Character local_ok)
  {
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Long.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public long get(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
  
  public char firstCharKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public char lastCharKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public CharComparator comparator()
  {
    return null;
  }
  
  public Char2LongSortedMap tailMap(char from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2LongSortedMap headMap(char local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2LongSortedMap subMap(char from, char local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2LongSortedMap.FastSortedEntrySet char2LongEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public CharSortedSet keySet()
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
          return new Char2LongLinkedOpenHashMap.ValueIterator(Char2LongLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Char2LongLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(long local_v)
        {
          return Char2LongLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Char2LongLinkedOpenHashMap.this.clear();
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
    char[] key = this.key;
    long[] value = this.value;
    int newMask = newN - 1;
    char[] newKey = new char[newN];
    long[] newValue = new long[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      char local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Char2LongLinkedOpenHashMap clone()
  {
    Char2LongLinkedOpenHashMap local_c;
    try
    {
      local_c = (Char2LongLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((long[])this.value.clone());
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
      local_t = this.key[local_i];
      local_t ^= HashCommon.long2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    char[] key = this.key;
    long[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeChar(key[local_e]);
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
    char[] key = this.key = new char[this.field_49];
    long[] value = this.value = new long[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      char local_k = local_s.readChar();
      long local_v = local_s.readLong();
      for (pos = HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
    extends Char2LongLinkedOpenHashMap.MapIterator
    implements LongListIterator
  {
    public long previousLong()
    {
      return Char2LongLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public Long previous()
    {
      return Long.valueOf(Char2LongLinkedOpenHashMap.this.value[previousEntry()]);
    }
    
    public void set(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public long nextLong()
    {
      return Char2LongLinkedOpenHashMap.this.value[nextEntry()];
    }
    
    public Long next()
    {
      return Long.valueOf(Char2LongLinkedOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractCharSortedSet
  {
    private KeySet() {}
    
    public CharListIterator iterator(char from)
    {
      return new Char2LongLinkedOpenHashMap.KeyIterator(Char2LongLinkedOpenHashMap.this, from);
    }
    
    public CharListIterator iterator()
    {
      return new Char2LongLinkedOpenHashMap.KeyIterator(Char2LongLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Char2LongLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(char local_k)
    {
      return Char2LongLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(char local_k)
    {
      int oldSize = Char2LongLinkedOpenHashMap.this.size;
      Char2LongLinkedOpenHashMap.this.remove(local_k);
      return Char2LongLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Char2LongLinkedOpenHashMap.this.clear();
    }
    
    public char firstChar()
    {
      if (Char2LongLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Char2LongLinkedOpenHashMap.this.key[Char2LongLinkedOpenHashMap.this.first];
    }
    
    public char lastChar()
    {
      if (Char2LongLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Char2LongLinkedOpenHashMap.this.key[Char2LongLinkedOpenHashMap.this.last];
    }
    
    public CharComparator comparator()
    {
      return null;
    }
    
    public final CharSortedSet tailSet(char from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final CharSortedSet headSet(char local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final CharSortedSet subSet(char from, char local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Char2LongLinkedOpenHashMap.MapIterator
    implements CharListIterator
  {
    public KeyIterator(char local_k)
    {
      super(local_k, null);
    }
    
    public char previousChar()
    {
      return Char2LongLinkedOpenHashMap.this.key[previousEntry()];
    }
    
    public void set(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character previous()
    {
      return Character.valueOf(Char2LongLinkedOpenHashMap.this.key[previousEntry()]);
    }
    
    public void set(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public char nextChar()
    {
      return Char2LongLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Character next()
    {
      return Character.valueOf(Char2LongLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Char2LongMap.Entry>
    implements Char2LongSortedMap.FastSortedEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator()
    {
      return new Char2LongLinkedOpenHashMap.EntryIterator(Char2LongLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Char2LongMap.Entry> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Char2LongMap.Entry> subSet(Char2LongMap.Entry fromElement, Char2LongMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Char2LongMap.Entry> headSet(Char2LongMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Char2LongMap.Entry> tailSet(Char2LongMap.Entry fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Char2LongMap.Entry first()
    {
      if (Char2LongLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Char2LongLinkedOpenHashMap.MapEntry(Char2LongLinkedOpenHashMap.this, Char2LongLinkedOpenHashMap.this.first);
    }
    
    public Char2LongMap.Entry last()
    {
      if (Char2LongLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Char2LongLinkedOpenHashMap.MapEntry(Char2LongLinkedOpenHashMap.this, Char2LongLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Char2LongLinkedOpenHashMap.this.mask; Char2LongLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2LongLinkedOpenHashMap.this.mask) {
        if (Char2LongLinkedOpenHashMap.this.key[pos] == local_k) {
          return Char2LongLinkedOpenHashMap.this.value[pos] == ((Long)local_e.getValue()).longValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Char2LongLinkedOpenHashMap.this.mask; Char2LongLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2LongLinkedOpenHashMap.this.mask) {
        if (Char2LongLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Char2LongLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Char2LongLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Char2LongLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Char2LongMap.Entry> iterator(Char2LongMap.Entry from)
    {
      return new Char2LongLinkedOpenHashMap.EntryIterator(Char2LongLinkedOpenHashMap.this, ((Character)from.getKey()).charValue());
    }
    
    public ObjectBidirectionalIterator<Char2LongMap.Entry> fastIterator()
    {
      return new Char2LongLinkedOpenHashMap.FastEntryIterator(Char2LongLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Char2LongMap.Entry> fastIterator(Char2LongMap.Entry from)
    {
      return new Char2LongLinkedOpenHashMap.FastEntryIterator(Char2LongLinkedOpenHashMap.this, ((Character)from.getKey()).charValue());
    }
  }
  
  private class FastEntryIterator
    extends Char2LongLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Char2LongMap.Entry>
  {
    final AbstractChar2LongMap.BasicEntry entry = new AbstractChar2LongMap.BasicEntry('\000', 0L);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(char from)
    {
      super(from, null);
    }
    
    public AbstractChar2LongMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Char2LongLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Char2LongLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractChar2LongMap.BasicEntry previous()
    {
      int local_e = previousEntry();
      this.entry.key = Char2LongLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Char2LongLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Char2LongMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Char2LongMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Char2LongLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Char2LongMap.Entry>
  {
    private Char2LongLinkedOpenHashMap.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(char from)
    {
      super(from, null);
    }
    
    public Char2LongLinkedOpenHashMap.MapEntry next()
    {
      return this.entry = new Char2LongLinkedOpenHashMap.MapEntry(Char2LongLinkedOpenHashMap.this, nextEntry());
    }
    
    public Char2LongLinkedOpenHashMap.MapEntry previous()
    {
      return this.entry = new Char2LongLinkedOpenHashMap.MapEntry(Char2LongLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Char2LongLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Char2LongMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Char2LongMap.Entry local_ok)
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
      this.next = Char2LongLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(char from)
    {
      if (Char2LongLinkedOpenHashMap.this.key[Char2LongLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Char2LongLinkedOpenHashMap.this.last;
        this.index = Char2LongLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(from) & Char2LongLinkedOpenHashMap.this.mask; Char2LongLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2LongLinkedOpenHashMap.this.mask) {
          if (Char2LongLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Char2LongLinkedOpenHashMap.this.link[pos]);
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
        this.index = Char2LongLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Char2LongLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Char2LongLinkedOpenHashMap.this.link[pos];
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
        return Char2LongLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Char2LongLinkedOpenHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Char2LongLinkedOpenHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Char2LongLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Char2LongLinkedOpenHashMap.this.link[this.curr]);
      }
      Char2LongLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Char2LongLinkedOpenHashMap.this.first = this.next;
      } else {
        Char2LongLinkedOpenHashMap.this.link[this.prev] ^= (Char2LongLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Char2LongLinkedOpenHashMap.this.last = this.prev;
      } else {
        Char2LongLinkedOpenHashMap.this.link[this.next] ^= (Char2LongLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Char2LongLinkedOpenHashMap.this.mask; Char2LongLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2LongLinkedOpenHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Char2LongLinkedOpenHashMap.this.key[pos]) & Char2LongLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Char2LongLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Char2LongLinkedOpenHashMap.this.key[last] = Char2LongLinkedOpenHashMap.this.key[pos];
        Char2LongLinkedOpenHashMap.this.value[last] = Char2LongLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Char2LongLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Char2LongLinkedOpenHashMap.this.used[last] = false;
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
    implements Char2LongMap.Entry, Map.Entry<Character, Long>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Character getKey()
    {
      return Character.valueOf(Char2LongLinkedOpenHashMap.this.key[this.index]);
    }
    
    public char getCharKey()
    {
      return Char2LongLinkedOpenHashMap.this.key[this.index];
    }
    
    public Long getValue()
    {
      return Long.valueOf(Char2LongLinkedOpenHashMap.this.value[this.index]);
    }
    
    public long getLongValue()
    {
      return Char2LongLinkedOpenHashMap.this.value[this.index];
    }
    
    public long setValue(long local_v)
    {
      long oldValue = Char2LongLinkedOpenHashMap.this.value[this.index];
      Char2LongLinkedOpenHashMap.this.value[this.index] = local_v;
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
      Map.Entry<Character, Long> local_e = (Map.Entry)local_o;
      return (Char2LongLinkedOpenHashMap.this.key[this.index] == ((Character)local_e.getKey()).charValue()) && (Char2LongLinkedOpenHashMap.this.value[this.index] == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return Char2LongLinkedOpenHashMap.this.key[this.index] ^ HashCommon.long2int(Char2LongLinkedOpenHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Char2LongLinkedOpenHashMap.this.key[this.index] + "=>" + Char2LongLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */