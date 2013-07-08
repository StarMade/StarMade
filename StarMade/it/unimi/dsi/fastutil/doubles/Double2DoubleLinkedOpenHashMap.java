package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
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

public class Double2DoubleLinkedOpenHashMap
  extends AbstractDouble2DoubleSortedMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[] key;
  protected transient double[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Double2DoubleSortedMap.FastSortedEntrySet entries;
  protected volatile transient DoubleSortedSet keys;
  protected volatile transient DoubleCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Double2DoubleLinkedOpenHashMap(int expected, float local_f)
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
    this.value = new double[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Double2DoubleLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Double2DoubleLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Double2DoubleLinkedOpenHashMap(Map<? extends Double, ? extends Double> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2DoubleLinkedOpenHashMap(Map<? extends Double, ? extends Double> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2DoubleLinkedOpenHashMap(Double2DoubleMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2DoubleLinkedOpenHashMap(Double2DoubleMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2DoubleLinkedOpenHashMap(double[] local_k, double[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Double2DoubleLinkedOpenHashMap(double[] local_k, double[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public double put(double local_k, double local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        double oldValue = this.value[pos];
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
  
  public Double put(Double local_ok, Double local_ov)
  {
    double local_v = local_ov.doubleValue();
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Double oldValue = Double.valueOf(this.value[pos]);
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
  
  public double add(double local_k, double incr)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        double oldValue = this.value[pos];
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
  
  public double remove(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        double local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Double remove(Object local_ok)
  {
    double local_k = ((Double)local_ok).doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        double local_v = this.value[pos];
        shiftKeys(pos);
        return Double.valueOf(local_v);
      }
    }
    return null;
  }
  
  public double removeFirstDouble()
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
    double local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public double removeLastDouble()
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
    double local_v = this.value[pos];
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
  
  public double getAndMoveToFirst(double local_k)
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
  
  public double getAndMoveToLast(double local_k)
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
  
  public double putAndMoveToFirst(double local_k, double local_v)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        double oldValue = this.value[pos];
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
  
  public double putAndMoveToLast(double local_k, double local_v)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        double oldValue = this.value[pos];
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
  
  public Double get(Double local_ok)
  {
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Double.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public double get(double local_k)
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
  
  public Double2DoubleSortedMap tailMap(double from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2DoubleSortedMap headMap(double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2DoubleSortedMap subMap(double from, double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Double2DoubleSortedMap.FastSortedEntrySet double2DoubleEntrySet()
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
  
  public DoubleCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractDoubleCollection()
      {
        public DoubleIterator iterator()
        {
          return new Double2DoubleLinkedOpenHashMap.ValueIterator(Double2DoubleLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Double2DoubleLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(double local_v)
        {
          return Double2DoubleLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Double2DoubleLinkedOpenHashMap.this.clear();
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
    double[] value = this.value;
    int newMask = newN - 1;
    double[] newKey = new double[newN];
    double[] newValue = new double[newN];
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
  
  public Double2DoubleLinkedOpenHashMap clone()
  {
    Double2DoubleLinkedOpenHashMap local_c;
    try
    {
      local_c = (Double2DoubleLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((double[])this.key.clone());
    local_c.value = ((double[])this.value.clone());
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
      local_t ^= HashCommon.double2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    double[] key = this.key;
    double[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeDouble(key[local_e]);
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
    double[] key = this.key = new double[this.field_49];
    double[] value = this.value = new double[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      double local_k = local_s.readDouble();
      double local_v = local_s.readDouble();
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
    extends Double2DoubleLinkedOpenHashMap.MapIterator
    implements DoubleListIterator
  {
    public double previousDouble()
    {
      return Double2DoubleLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public Double previous()
    {
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.value[previousEntry()]);
    }
    
    public void set(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Double2DoubleLinkedOpenHashMap.this.value[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractDoubleSortedSet
  {
    private KeySet() {}
    
    public DoubleListIterator iterator(double from)
    {
      return new Double2DoubleLinkedOpenHashMap.KeyIterator(Double2DoubleLinkedOpenHashMap.this, from);
    }
    
    public DoubleListIterator iterator()
    {
      return new Double2DoubleLinkedOpenHashMap.KeyIterator(Double2DoubleLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Double2DoubleLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(double local_k)
    {
      return Double2DoubleLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(double local_k)
    {
      int oldSize = Double2DoubleLinkedOpenHashMap.this.size;
      Double2DoubleLinkedOpenHashMap.this.remove(local_k);
      return Double2DoubleLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Double2DoubleLinkedOpenHashMap.this.clear();
    }
    
    public double firstDouble()
    {
      if (Double2DoubleLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Double2DoubleLinkedOpenHashMap.this.key[Double2DoubleLinkedOpenHashMap.this.first];
    }
    
    public double lastDouble()
    {
      if (Double2DoubleLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Double2DoubleLinkedOpenHashMap.this.key[Double2DoubleLinkedOpenHashMap.this.last];
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
    extends Double2DoubleLinkedOpenHashMap.MapIterator
    implements DoubleListIterator
  {
    public KeyIterator(double local_k)
    {
      super(local_k, null);
    }
    
    public double previousDouble()
    {
      return Double2DoubleLinkedOpenHashMap.this.key[previousEntry()];
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
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.key[previousEntry()]);
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
      return Double2DoubleLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Double2DoubleMap.Entry>
    implements Double2DoubleSortedMap.FastSortedEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Double2DoubleMap.Entry> iterator()
    {
      return new Double2DoubleLinkedOpenHashMap.EntryIterator(Double2DoubleLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Double2DoubleMap.Entry> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Double2DoubleMap.Entry> subSet(Double2DoubleMap.Entry fromElement, Double2DoubleMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Double2DoubleMap.Entry> headSet(Double2DoubleMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Double2DoubleMap.Entry> tailSet(Double2DoubleMap.Entry fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double2DoubleMap.Entry first()
    {
      if (Double2DoubleLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Double2DoubleLinkedOpenHashMap.MapEntry(Double2DoubleLinkedOpenHashMap.this, Double2DoubleLinkedOpenHashMap.this.first);
    }
    
    public Double2DoubleMap.Entry last()
    {
      if (Double2DoubleLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Double2DoubleLinkedOpenHashMap.MapEntry(Double2DoubleLinkedOpenHashMap.this, Double2DoubleLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Double> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2DoubleLinkedOpenHashMap.this.mask; Double2DoubleLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2DoubleLinkedOpenHashMap.this.mask) {
        if (Double2DoubleLinkedOpenHashMap.this.key[pos] == local_k) {
          return Double2DoubleLinkedOpenHashMap.this.value[pos] == ((Double)local_e.getValue()).doubleValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Double> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2DoubleLinkedOpenHashMap.this.mask; Double2DoubleLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2DoubleLinkedOpenHashMap.this.mask) {
        if (Double2DoubleLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Double2DoubleLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Double2DoubleLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Double2DoubleLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Double2DoubleMap.Entry> iterator(Double2DoubleMap.Entry from)
    {
      return new Double2DoubleLinkedOpenHashMap.EntryIterator(Double2DoubleLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
    }
    
    public ObjectBidirectionalIterator<Double2DoubleMap.Entry> fastIterator()
    {
      return new Double2DoubleLinkedOpenHashMap.FastEntryIterator(Double2DoubleLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Double2DoubleMap.Entry> fastIterator(Double2DoubleMap.Entry from)
    {
      return new Double2DoubleLinkedOpenHashMap.FastEntryIterator(Double2DoubleLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
    }
  }
  
  private class FastEntryIterator
    extends Double2DoubleLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Double2DoubleMap.Entry>
  {
    final AbstractDouble2DoubleMap.BasicEntry entry = new AbstractDouble2DoubleMap.BasicEntry(0.0D, 0.0D);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(double from)
    {
      super(from, null);
    }
    
    public AbstractDouble2DoubleMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Double2DoubleLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Double2DoubleLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractDouble2DoubleMap.BasicEntry previous()
    {
      int local_e = previousEntry();
      this.entry.key = Double2DoubleLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Double2DoubleLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Double2DoubleMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double2DoubleMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Double2DoubleLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Double2DoubleMap.Entry>
  {
    private Double2DoubleLinkedOpenHashMap.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(double from)
    {
      super(from, null);
    }
    
    public Double2DoubleLinkedOpenHashMap.MapEntry next()
    {
      return this.entry = new Double2DoubleLinkedOpenHashMap.MapEntry(Double2DoubleLinkedOpenHashMap.this, nextEntry());
    }
    
    public Double2DoubleLinkedOpenHashMap.MapEntry previous()
    {
      return this.entry = new Double2DoubleLinkedOpenHashMap.MapEntry(Double2DoubleLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Double2DoubleLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Double2DoubleMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double2DoubleMap.Entry local_ok)
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
      this.next = Double2DoubleLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(double from)
    {
      if (Double2DoubleLinkedOpenHashMap.this.key[Double2DoubleLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Double2DoubleLinkedOpenHashMap.this.last;
        this.index = Double2DoubleLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(from)) & Double2DoubleLinkedOpenHashMap.this.mask; Double2DoubleLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2DoubleLinkedOpenHashMap.this.mask) {
          if (Double2DoubleLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Double2DoubleLinkedOpenHashMap.this.link[pos]);
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
        this.index = Double2DoubleLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Double2DoubleLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Double2DoubleLinkedOpenHashMap.this.link[pos];
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
        return Double2DoubleLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Double2DoubleLinkedOpenHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Double2DoubleLinkedOpenHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Double2DoubleLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Double2DoubleLinkedOpenHashMap.this.link[this.curr]);
      }
      Double2DoubleLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Double2DoubleLinkedOpenHashMap.this.first = this.next;
      } else {
        Double2DoubleLinkedOpenHashMap.this.link[this.prev] ^= (Double2DoubleLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Double2DoubleLinkedOpenHashMap.this.last = this.prev;
      } else {
        Double2DoubleLinkedOpenHashMap.this.link[this.next] ^= (Double2DoubleLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Double2DoubleLinkedOpenHashMap.this.mask; Double2DoubleLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2DoubleLinkedOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2DoubleLinkedOpenHashMap.this.key[pos])) & Double2DoubleLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Double2DoubleLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Double2DoubleLinkedOpenHashMap.this.key[last] = Double2DoubleLinkedOpenHashMap.this.key[pos];
        Double2DoubleLinkedOpenHashMap.this.value[last] = Double2DoubleLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Double2DoubleLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Double2DoubleLinkedOpenHashMap.this.used[last] = false;
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
    implements Double2DoubleMap.Entry, Map.Entry<Double, Double>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Double getKey()
    {
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.key[this.index]);
    }
    
    public double getDoubleKey()
    {
      return Double2DoubleLinkedOpenHashMap.this.key[this.index];
    }
    
    public Double getValue()
    {
      return Double.valueOf(Double2DoubleLinkedOpenHashMap.this.value[this.index]);
    }
    
    public double getDoubleValue()
    {
      return Double2DoubleLinkedOpenHashMap.this.value[this.index];
    }
    
    public double setValue(double local_v)
    {
      double oldValue = Double2DoubleLinkedOpenHashMap.this.value[this.index];
      Double2DoubleLinkedOpenHashMap.this.value[this.index] = local_v;
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
      Map.Entry<Double, Double> local_e = (Map.Entry)local_o;
      return (Double2DoubleLinkedOpenHashMap.this.key[this.index] == ((Double)local_e.getKey()).doubleValue()) && (Double2DoubleLinkedOpenHashMap.this.value[this.index] == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(Double2DoubleLinkedOpenHashMap.this.key[this.index]) ^ HashCommon.double2int(Double2DoubleLinkedOpenHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Double2DoubleLinkedOpenHashMap.this.key[this.index] + "=>" + Double2DoubleLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2DoubleLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */