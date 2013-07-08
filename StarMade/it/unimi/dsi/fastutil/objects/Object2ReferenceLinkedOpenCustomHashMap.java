package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2ReferenceLinkedOpenCustomHashMap<K, V>
  extends AbstractObject2ReferenceSortedMap<K, V>
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
  protected volatile transient Object2ReferenceSortedMap.FastSortedEntrySet<K, V> entries;
  protected volatile transient ObjectSortedSet<K> keys;
  protected volatile transient ReferenceCollection<V> values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  protected Hash.Strategy<K> strategy;
  
  public Object2ReferenceLinkedOpenCustomHashMap(int expected, float local_f, Hash.Strategy<K> strategy)
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
    this.link = new long[this.field_49];
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(Map<? extends K, ? extends V> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(Map<? extends K, ? extends V> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(Object2ReferenceMap<K, V> local_m, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(Object2ReferenceMap<K, V> local_m, Hash.Strategy<K> strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(K[] local_k, V[] local_v, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2ReferenceLinkedOpenCustomHashMap(K[] local_k, V[] local_v, Hash.Strategy<K> strategy)
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
    this.value[last] = null;
    return last;
  }
  
  public V remove(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
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
  
  public V getAndMoveToFirst(K local_k)
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
  
  public V getAndMoveToLast(K local_k)
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
  
  public V putAndMoveToFirst(K local_k, V local_v)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
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
  
  public V putAndMoveToLast(K local_k, V local_v)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
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
  
  public Object2ReferenceSortedMap<K, V> tailMap(K from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ReferenceSortedMap<K, V> headMap(K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ReferenceSortedMap<K, V> subMap(K from, K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object2ReferenceSortedMap.FastSortedEntrySet<K, V> object2ReferenceEntrySet()
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
  
  public ReferenceCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractReferenceCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Object2ReferenceLinkedOpenCustomHashMap.ValueIterator(Object2ReferenceLinkedOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Object2ReferenceLinkedOpenCustomHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Object2ReferenceLinkedOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2ReferenceLinkedOpenCustomHashMap.this.clear();
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
    V[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    V[] newValue = (Object[])new Object[newN];
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
  
  public Object2ReferenceLinkedOpenCustomHashMap<K, V> clone()
  {
    Object2ReferenceLinkedOpenCustomHashMap<K, V> local_c;
    try
    {
      local_c = (Object2ReferenceLinkedOpenCustomHashMap)super.clone();
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
    Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapIterator local_i = new MapIterator(null);
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
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
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
    extends Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapIterator
    implements ObjectListIterator<V>
  {
    public V previous()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.value[previousEntry()];
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
      return Object2ReferenceLinkedOpenCustomHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractObjectSortedSet<K>
  {
    private KeySet() {}
    
    public ObjectListIterator<K> iterator(K from)
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.KeyIterator(Object2ReferenceLinkedOpenCustomHashMap.this, from);
    }
    
    public ObjectListIterator<K> iterator()
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.KeyIterator(Object2ReferenceLinkedOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2ReferenceLinkedOpenCustomHashMap.this.size;
      Object2ReferenceLinkedOpenCustomHashMap.this.remove(local_k);
      return Object2ReferenceLinkedOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2ReferenceLinkedOpenCustomHashMap.this.clear();
    }
    
    public K first()
    {
      if (Object2ReferenceLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[Object2ReferenceLinkedOpenCustomHashMap.this.first];
    }
    
    public K last()
    {
      if (Object2ReferenceLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[Object2ReferenceLinkedOpenCustomHashMap.this.last];
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
    extends Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapIterator
    implements ObjectListIterator<K>
  {
    public KeyIterator()
    {
      super(local_k, null);
    }
    
    public K previous()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[previousEntry()];
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
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Object2ReferenceMap.Entry<K, V>>
    implements Object2ReferenceSortedMap.FastSortedEntrySet<K, V>
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator()
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.EntryIterator(Object2ReferenceLinkedOpenCustomHashMap.this);
    }
    
    public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> fromElement, Object2ReferenceMap.Entry<K, V> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object2ReferenceMap.Entry<K, V> first()
    {
      if (Object2ReferenceLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Object2ReferenceLinkedOpenCustomHashMap.MapEntry(Object2ReferenceLinkedOpenCustomHashMap.this, Object2ReferenceLinkedOpenCustomHashMap.this.first);
    }
    
    public Object2ReferenceMap.Entry<K, V> last()
    {
      if (Object2ReferenceLinkedOpenCustomHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Object2ReferenceLinkedOpenCustomHashMap.MapEntry(Object2ReferenceLinkedOpenCustomHashMap.this, Object2ReferenceLinkedOpenCustomHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = HashCommon.murmurHash3(Object2ReferenceLinkedOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ReferenceLinkedOpenCustomHashMap.this.mask; Object2ReferenceLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceLinkedOpenCustomHashMap.this.mask) {
        if (Object2ReferenceLinkedOpenCustomHashMap.this.strategy.equals(Object2ReferenceLinkedOpenCustomHashMap.this.key[pos], local_k)) {
          return Object2ReferenceLinkedOpenCustomHashMap.this.value[pos] == local_e.getValue();
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
      for (int pos = HashCommon.murmurHash3(Object2ReferenceLinkedOpenCustomHashMap.this.strategy.hashCode(local_k)) & Object2ReferenceLinkedOpenCustomHashMap.this.mask; Object2ReferenceLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceLinkedOpenCustomHashMap.this.mask) {
        if (Object2ReferenceLinkedOpenCustomHashMap.this.strategy.equals(Object2ReferenceLinkedOpenCustomHashMap.this.key[pos], local_k))
        {
          Object2ReferenceLinkedOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Object2ReferenceLinkedOpenCustomHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from)
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.EntryIterator(Object2ReferenceLinkedOpenCustomHashMap.this, from.getKey());
    }
    
    public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> fastIterator()
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.FastEntryIterator(Object2ReferenceLinkedOpenCustomHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> fastIterator(Object2ReferenceMap.Entry<K, V> from)
    {
      return new Object2ReferenceLinkedOpenCustomHashMap.FastEntryIterator(Object2ReferenceLinkedOpenCustomHashMap.this, from.getKey());
    }
  }
  
  private class FastEntryIterator
    extends Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapIterator
    implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
  {
    final AbstractObject2ReferenceMap.BasicEntry<K, V> entry = new AbstractObject2ReferenceMap.BasicEntry(null, null);
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator()
    {
      super(from, null);
    }
    
    public AbstractObject2ReferenceMap.BasicEntry<K, V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2ReferenceLinkedOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2ReferenceLinkedOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractObject2ReferenceMap.BasicEntry<K, V> previous()
    {
      int local_e = previousEntry();
      this.entry.key = Object2ReferenceLinkedOpenCustomHashMap.this.key[local_e];
      this.entry.value = Object2ReferenceLinkedOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Object2ReferenceMap.Entry<K, V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2ReferenceMap.Entry<K, V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapIterator
    implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
  {
    private Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator()
    {
      super(from, null);
    }
    
    public Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapEntry next()
    {
      return this.entry = new Object2ReferenceLinkedOpenCustomHashMap.MapEntry(Object2ReferenceLinkedOpenCustomHashMap.this, nextEntry());
    }
    
    public Object2ReferenceLinkedOpenCustomHashMap<K, V>.MapEntry previous()
    {
      return this.entry = new Object2ReferenceLinkedOpenCustomHashMap.MapEntry(Object2ReferenceLinkedOpenCustomHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2ReferenceLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Object2ReferenceMap.Entry<K, V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2ReferenceMap.Entry<K, V> local_ok)
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
      this.next = Object2ReferenceLinkedOpenCustomHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator()
    {
      if (Object2ReferenceLinkedOpenCustomHashMap.this.strategy.equals(Object2ReferenceLinkedOpenCustomHashMap.this.key[Object2ReferenceLinkedOpenCustomHashMap.this.last], from))
      {
        this.prev = Object2ReferenceLinkedOpenCustomHashMap.this.last;
        this.index = Object2ReferenceLinkedOpenCustomHashMap.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(Object2ReferenceLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2ReferenceLinkedOpenCustomHashMap.this.mask; Object2ReferenceLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceLinkedOpenCustomHashMap.this.mask) {
          if (Object2ReferenceLinkedOpenCustomHashMap.this.strategy.equals(Object2ReferenceLinkedOpenCustomHashMap.this.key[pos], from))
          {
            this.next = ((int)Object2ReferenceLinkedOpenCustomHashMap.this.link[pos]);
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
        this.index = Object2ReferenceLinkedOpenCustomHashMap.this.size;
        return;
      }
      int pos = Object2ReferenceLinkedOpenCustomHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Object2ReferenceLinkedOpenCustomHashMap.this.link[pos];
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
        return Object2ReferenceLinkedOpenCustomHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Object2ReferenceLinkedOpenCustomHashMap.this.link[this.curr]);
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
      this.prev = ((int)(Object2ReferenceLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
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
        this.prev = ((int)(Object2ReferenceLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Object2ReferenceLinkedOpenCustomHashMap.this.link[this.curr]);
      }
      Object2ReferenceLinkedOpenCustomHashMap.this.size -= 1;
      if (this.prev == -1) {
        Object2ReferenceLinkedOpenCustomHashMap.this.first = this.next;
      } else {
        Object2ReferenceLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2ReferenceLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Object2ReferenceLinkedOpenCustomHashMap.this.last = this.prev;
      } else {
        Object2ReferenceLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2ReferenceLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2ReferenceLinkedOpenCustomHashMap.this.mask; Object2ReferenceLinkedOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Object2ReferenceLinkedOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Object2ReferenceLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ReferenceLinkedOpenCustomHashMap.this.key[pos])) & Object2ReferenceLinkedOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2ReferenceLinkedOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        Object2ReferenceLinkedOpenCustomHashMap.this.key[last] = Object2ReferenceLinkedOpenCustomHashMap.this.key[pos];
        Object2ReferenceLinkedOpenCustomHashMap.this.value[last] = Object2ReferenceLinkedOpenCustomHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Object2ReferenceLinkedOpenCustomHashMap.this.fixPointers(pos, last);
      }
      Object2ReferenceLinkedOpenCustomHashMap.this.used[last] = false;
      Object2ReferenceLinkedOpenCustomHashMap.this.key[last] = null;
      Object2ReferenceLinkedOpenCustomHashMap.this.value[last] = null;
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
    implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index];
      Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      return (Object2ReferenceLinkedOpenCustomHashMap.this.strategy.equals(Object2ReferenceLinkedOpenCustomHashMap.this.key[this.index], local_e.getKey())) && (Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index] == local_e.getValue());
    }
    
    public int hashCode()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ReferenceLinkedOpenCustomHashMap.this.key[this.index]) ^ (Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index]));
    }
    
    public String toString()
    {
      return Object2ReferenceLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2ReferenceLinkedOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceLinkedOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */