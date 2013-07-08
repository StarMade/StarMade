package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortListIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Double2ShortLinkedOpenHashMap
  extends AbstractDouble2ShortSortedMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[] key;
  protected transient short[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Double2ShortSortedMap.FastSortedEntrySet entries;
  protected volatile transient DoubleSortedSet keys;
  protected volatile transient ShortCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Double2ShortLinkedOpenHashMap(int expected, float local_f)
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
    this.key = new double[this.field_49];
    this.value = new short[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Double2ShortLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Double2ShortLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Double2ShortLinkedOpenHashMap(Map<? extends Double, ? extends Short> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2ShortLinkedOpenHashMap(Map<? extends Double, ? extends Short> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2ShortLinkedOpenHashMap(Double2ShortMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2ShortLinkedOpenHashMap(Double2ShortMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2ShortLinkedOpenHashMap(double[] local_k, short[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Double2ShortLinkedOpenHashMap(double[] local_k, short[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public short put(double local_k, short local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        short oldValue = this.value[pos];
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
  
  public Short put(Double local_ok, Short local_ov)
  {
    short local_v = local_ov.shortValue();
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Short oldValue = Short.valueOf(this.value[pos]);
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
  
  public short add(double local_k, short incr)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        short oldValue = this.value[pos];
        int tmp52_50 = pos;
        short[] tmp52_47 = this.value;
        tmp52_47[tmp52_50] = ((short)(tmp52_47[tmp52_50] + incr));
        return tmp52_50;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = ((short)(this.defRetValue + incr));
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
        int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(this.key[pos])) & this.mask;
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
  
  public short remove(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        short local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Short remove(Object local_ok)
  {
    double local_k = ((Double)local_ok).doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        short local_v = this.value[pos];
        shiftKeys(pos);
        return Short.valueOf(local_v);
      }
    }
    return null;
  }
  
  public short removeFirstShort()
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
    short local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public short removeLastShort()
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
    short local_v = this.value[pos];
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
  
  public short getAndMoveToFirst(double local_k)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public short getAndMoveToLast(double local_k)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public short putAndMoveToFirst(double local_k, short local_v)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        short oldValue = this.value[pos];
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
  
  public short putAndMoveToLast(double local_k, short local_v)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        short oldValue = this.value[pos];
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
  
  public Short get(Double local_ok)
  {
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Short.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public short get(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(short local_v)
  {
    short[] value = this.value;
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
  
  public double firstDoubleKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public double lastDoubleKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public DoubleComparator comparator()
  {
    return null;
  }
  
  public Double2ShortSortedMap tailMap(double from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2ShortSortedMap headMap(double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2ShortSortedMap subMap(double from, double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2ShortSortedMap.FastSortedEntrySet double2ShortEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public DoubleSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ShortCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractShortCollection()
      {
        public ShortIterator iterator()
        {
          return new Double2ShortLinkedOpenHashMap.ValueIterator(Double2ShortLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Double2ShortLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(short local_v)
        {
          return Double2ShortLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Double2ShortLinkedOpenHashMap.this.clear();
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
    double[] key = this.key;
    short[] value = this.value;
    int newMask = newN - 1;
    double[] newKey = new double[newN];
    short[] newValue = new short[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      double local_k = key[local_i];
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Double2ShortLinkedOpenHashMap clone()
  {
    Double2ShortLinkedOpenHashMap local_c;
    try
    {
      local_c = (Double2ShortLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((double[])this.key.clone());
    local_c.value = ((short[])this.value.clone());
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
      local_t = HashCommon.double2int(this.key[local_i]);
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    double[] key = this.key;
    short[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeDouble(key[local_e]);
      local_s.writeShort(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    double[] key = this.key = new double[this.field_49];
    short[] value = this.value = new short[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      double local_k = local_s.readDouble();
      short local_v = local_s.readShort();
      for (pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
    extends Double2ShortLinkedOpenHashMap.MapIterator
    implements ShortListIterator
  {
    public short previousShort()
    {
      return Double2ShortLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public Short previous()
    {
      return Short.valueOf(Double2ShortLinkedOpenHashMap.this.value[previousEntry()]);
    }
    
    public void set(Short local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Short local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(short local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public short nextShort()
    {
      return Double2ShortLinkedOpenHashMap.this.value[nextEntry()];
    }
    
    public Short next()
    {
      return Short.valueOf(Double2ShortLinkedOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractDoubleSortedSet
  {
    private KeySet() {}
    
    public DoubleListIterator iterator(double from)
    {
      return new Double2ShortLinkedOpenHashMap.KeyIterator(Double2ShortLinkedOpenHashMap.this, from);
    }
    
    public DoubleListIterator iterator()
    {
      return new Double2ShortLinkedOpenHashMap.KeyIterator(Double2ShortLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Double2ShortLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(double local_k)
    {
      return Double2ShortLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(double local_k)
    {
      int oldSize = Double2ShortLinkedOpenHashMap.this.size;
      Double2ShortLinkedOpenHashMap.this.remove(local_k);
      return Double2ShortLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Double2ShortLinkedOpenHashMap.this.clear();
    }
    
    public double firstDouble()
    {
      if (Double2ShortLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.first];
    }
    
    public double lastDouble()
    {
      if (Double2ShortLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.last];
    }
    
    public DoubleComparator comparator()
    {
      return null;
    }
    
    public final DoubleSortedSet tailSet(double from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final DoubleSortedSet headSet(double local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final DoubleSortedSet subSet(double from, double local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Double2ShortLinkedOpenHashMap.MapIterator
    implements DoubleListIterator
  {
    public KeyIterator(double local_k)
    {
      super(local_k, null);
    }
    
    public double previousDouble()
    {
      return Double2ShortLinkedOpenHashMap.this.key[previousEntry()];
    }
    
    public void set(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double previous()
    {
      return Double.valueOf(Double2ShortLinkedOpenHashMap.this.key[previousEntry()]);
    }
    
    public void set(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Double2ShortLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Double2ShortLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Double2ShortMap.Entry>
    implements Double2ShortSortedMap.FastSortedEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Double2ShortMap.Entry> iterator()
    {
      return new Double2ShortLinkedOpenHashMap.EntryIterator(Double2ShortLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Double2ShortMap.Entry> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> subSet(Double2ShortMap.Entry fromElement, Double2ShortMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> headSet(Double2ShortMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Double2ShortMap.Entry> tailSet(Double2ShortMap.Entry fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double2ShortMap.Entry first()
    {
      if (Double2ShortLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this, Double2ShortLinkedOpenHashMap.this.first);
    }
    
    public Double2ShortMap.Entry last()
    {
      if (Double2ShortLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this, Double2ShortLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Short> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2ShortLinkedOpenHashMap.this.mask; Double2ShortLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask) {
        if (Double2ShortLinkedOpenHashMap.this.key[pos] == local_k) {
          return Double2ShortLinkedOpenHashMap.this.value[pos] == ((Short)local_e.getValue()).shortValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Short> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2ShortLinkedOpenHashMap.this.mask; Double2ShortLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask) {
        if (Double2ShortLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Double2ShortLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Double2ShortLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Double2ShortLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Double2ShortMap.Entry> iterator(Double2ShortMap.Entry from)
    {
      return new Double2ShortLinkedOpenHashMap.EntryIterator(Double2ShortLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
    }
    
    public ObjectBidirectionalIterator<Double2ShortMap.Entry> fastIterator()
    {
      return new Double2ShortLinkedOpenHashMap.FastEntryIterator(Double2ShortLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Double2ShortMap.Entry> fastIterator(Double2ShortMap.Entry from)
    {
      return new Double2ShortLinkedOpenHashMap.FastEntryIterator(Double2ShortLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
    }
  }
  
  private class FastEntryIterator
    extends Double2ShortLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Double2ShortMap.Entry>
  {
    final AbstractDouble2ShortMap.BasicEntry entry = new AbstractDouble2ShortMap.BasicEntry(0.0D, (short)0);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(double from)
    {
      super(from, null);
    }
    
    public AbstractDouble2ShortMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Double2ShortLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Double2ShortLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractDouble2ShortMap.BasicEntry previous()
    {
      int local_e = previousEntry();
      this.entry.key = Double2ShortLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Double2ShortLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Double2ShortMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double2ShortMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Double2ShortLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Double2ShortMap.Entry>
  {
    private Double2ShortLinkedOpenHashMap.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(double from)
    {
      super(from, null);
    }
    
    public Double2ShortLinkedOpenHashMap.MapEntry next()
    {
      return this.entry = new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this, nextEntry());
    }
    
    public Double2ShortLinkedOpenHashMap.MapEntry previous()
    {
      return this.entry = new Double2ShortLinkedOpenHashMap.MapEntry(Double2ShortLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Double2ShortLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Double2ShortMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double2ShortMap.Entry local_ok)
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
      this.next = Double2ShortLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(double from)
    {
      if (Double2ShortLinkedOpenHashMap.this.key[Double2ShortLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Double2ShortLinkedOpenHashMap.this.last;
        this.index = Double2ShortLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(from)) & Double2ShortLinkedOpenHashMap.this.mask; Double2ShortLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask) {
          if (Double2ShortLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Double2ShortLinkedOpenHashMap.this.link[pos]);
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
        this.index = Double2ShortLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Double2ShortLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Double2ShortLinkedOpenHashMap.this.link[pos];
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
        return Double2ShortLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Double2ShortLinkedOpenHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Double2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Double2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Double2ShortLinkedOpenHashMap.this.link[this.curr]);
      }
      Double2ShortLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Double2ShortLinkedOpenHashMap.this.first = this.next;
      } else {
        Double2ShortLinkedOpenHashMap.this.link[this.prev] ^= (Double2ShortLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Double2ShortLinkedOpenHashMap.this.last = this.prev;
      } else {
        Double2ShortLinkedOpenHashMap.this.link[this.next] ^= (Double2ShortLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Double2ShortLinkedOpenHashMap.this.mask; Double2ShortLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ShortLinkedOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2ShortLinkedOpenHashMap.this.key[pos])) & Double2ShortLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Double2ShortLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Double2ShortLinkedOpenHashMap.this.key[last] = Double2ShortLinkedOpenHashMap.this.key[pos];
        Double2ShortLinkedOpenHashMap.this.value[last] = Double2ShortLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Double2ShortLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Double2ShortLinkedOpenHashMap.this.used[last] = false;
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
    implements Double2ShortMap.Entry, Map.Entry<Double, Short>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Double getKey()
    {
      return Double.valueOf(Double2ShortLinkedOpenHashMap.this.key[this.index]);
    }
    
    public double getDoubleKey()
    {
      return Double2ShortLinkedOpenHashMap.this.key[this.index];
    }
    
    public Short getValue()
    {
      return Short.valueOf(Double2ShortLinkedOpenHashMap.this.value[this.index]);
    }
    
    public short getShortValue()
    {
      return Double2ShortLinkedOpenHashMap.this.value[this.index];
    }
    
    public short setValue(short local_v)
    {
      short oldValue = Double2ShortLinkedOpenHashMap.this.value[this.index];
      Double2ShortLinkedOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Short setValue(Short local_v)
    {
      return Short.valueOf(setValue(local_v.shortValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Short> local_e = (Map.Entry)local_o;
      return (Double2ShortLinkedOpenHashMap.this.key[this.index] == ((Double)local_e.getKey()).doubleValue()) && (Double2ShortLinkedOpenHashMap.this.value[this.index] == ((Short)local_e.getValue()).shortValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(Double2ShortLinkedOpenHashMap.this.key[this.index]) ^ Double2ShortLinkedOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Double2ShortLinkedOpenHashMap.this.key[this.index] + "=>" + Double2ShortLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ShortLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */