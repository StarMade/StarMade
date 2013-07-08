package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectLinkedOpenCustomHashSet<K>
  extends AbstractObjectSortedSet<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient boolean[] used;
  protected final float field_73;
  protected transient int field_74;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  protected Hash.Strategy<K> strategy;
  
  public ObjectLinkedOpenCustomHashSet(int expected, float local_f, Hash.Strategy<K> strategy)
  {
    this.strategy = strategy;
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_73 = local_f;
    this.field_74 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_74 - 1);
    this.maxFill = HashCommon.maxFill(this.field_74, local_f);
    this.key = ((Object[])new Object[this.field_74]);
    this.used = new boolean[this.field_74];
    this.link = new long[this.field_74];
  }
  
  public ObjectLinkedOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(Collection<? extends K> local_c, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public ObjectLinkedOpenCustomHashSet(Collection<? extends K> local_c, Hash.Strategy<K> strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> local_c, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> local_c, Hash.Strategy<K> strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> local_i, float local_f, Hash.Strategy<K> strategy)
  {
    this(16, local_f, strategy);
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> local_i, Hash.Strategy<K> strategy)
  {
    this(local_i, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(K[] local_a, int offset, int length, float local_f, Hash.Strategy<K> strategy)
  {
    this(length < 0 ? 0 : length, local_f, strategy);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ObjectLinkedOpenCustomHashSet(K[] local_a, int offset, int length, Hash.Strategy<K> strategy)
  {
    this(local_a, offset, length, 0.75F, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(K[] local_a, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_a, 0, local_a.length, local_f, strategy);
  }
  
  public ObjectLinkedOpenCustomHashSet(K[] local_a, Hash.Strategy<K> strategy)
  {
    this(local_a, 0.75F, strategy);
  }
  
  public Hash.Strategy<K> strategy()
  {
    return this.strategy;
  }
  
  public boolean add(K local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
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
      rehash(HashCommon.arraySize(this.size + 1, this.field_73));
    }
    return true;
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
      fixPointers(pos, last);
    }
    this.used[last] = false;
    this.key[last] = null;
    return last;
  }
  
  public boolean remove(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        fixPointers(pos);
        shiftKeys(pos);
        return true;
      }
    }
    return false;
  }
  
  public boolean contains(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public K get(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.key[pos];
      }
    }
    return null;
  }
  
  public K removeFirst()
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
    K local_k = this.key[pos];
    shiftKeys(pos);
    return local_k;
  }
  
  public K removeLast()
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
    K local_k = this.key[pos];
    shiftKeys(pos);
    return local_k;
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
  
  public boolean addAndMoveToFirst(K local_k)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        moveIndexToFirst(pos);
        return false;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
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
      rehash(HashCommon.arraySize(this.size, this.field_73));
    }
    return true;
  }
  
  public boolean addAndMoveToLast(K local_k)
  {
    K[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (this.strategy.equals(local_k, key[pos]))
      {
        moveIndexToLast(pos);
        return false;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
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
      rehash(HashCommon.arraySize(this.size, this.field_73));
    }
    return true;
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
  
  public K first()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public K last()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public ObjectSortedSet<K> tailSet(K from)
  {
    throw new UnsupportedOperationException();
  }
  
  public ObjectSortedSet<K> headSet(K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public ObjectSortedSet<K> subSet(K from, K local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Comparator<? super K> comparator()
  {
    return null;
  }
  
  public ObjectListIterator<K> iterator(K from)
  {
    return new SetIterator(from);
  }
  
  public ObjectListIterator<K> iterator()
  {
    return new SetIterator();
  }
  
  @Deprecated
  public boolean rehash()
  {
    return true;
  }
  
  public boolean trim()
  {
    int local_l = HashCommon.arraySize(this.size, this.field_73);
    if (local_l >= this.field_74) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_73));
    if (this.field_74 <= local_l) {
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
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
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
    this.field_74 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
    this.key = newKey;
    this.used = newUsed;
    this.link = newLink;
    this.last = newPrev;
    if (newPrev != -1) {
      newLink[newPrev] |= 4294967295L;
    }
  }
  
  public ObjectLinkedOpenCustomHashSet<K> clone()
  {
    ObjectLinkedOpenCustomHashSet<K> local_c;
    try
    {
      local_c = (ObjectLinkedOpenCustomHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((Object[])this.key.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.link = ((long[])this.link.clone());
    local_c.strategy = this.strategy;
    return local_c;
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_i = 0;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (this.used[local_i] == 0) {
        local_i++;
      }
      if (this != this.key[local_i]) {
        local_h += this.strategy.hashCode(this.key[local_i]);
      }
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    ObjectIterator<K> local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeObject(local_i.next());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_74 = HashCommon.arraySize(this.size, this.field_73);
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
    this.mask = (this.field_74 - 1);
    K[] key = this.key = (Object[])new Object[this.field_74];
    boolean[] used = this.used = new boolean[this.field_74];
    long[] link = this.link = new long[this.field_74];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
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
  
  private class SetIterator
    extends AbstractObjectListIterator<K>
  {
    int prev = -1;
    int next = -1;
    int curr = -1;
    int index = -1;
    
    SetIterator()
    {
      this.next = ObjectLinkedOpenCustomHashSet.this.first;
      this.index = 0;
    }
    
    SetIterator()
    {
      if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[ObjectLinkedOpenCustomHashSet.this.last], from))
      {
        this.prev = ObjectLinkedOpenCustomHashSet.this.last;
        this.index = ObjectLinkedOpenCustomHashSet.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & ObjectLinkedOpenCustomHashSet.this.mask; ObjectLinkedOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask) {
          if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[pos], from))
          {
            this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[pos]);
            this.prev = pos;
            return;
          }
        }
        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
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
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = this.next;
      this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
    }
    
    public K previous()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = this.prev;
      this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
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
        this.index = ObjectLinkedOpenCustomHashSet.this.size;
        return;
      }
      int pos = ObjectLinkedOpenCustomHashSet.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)ObjectLinkedOpenCustomHashSet.this.link[pos];
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
    
    public void remove()
    {
      ensureIndexKnown();
      if (this.curr == -1) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev)
      {
        this.index -= 1;
        this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]);
      }
      ObjectLinkedOpenCustomHashSet.this.size -= 1;
      if (this.prev == -1) {
        ObjectLinkedOpenCustomHashSet.this.first = this.next;
      } else {
        ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        ObjectLinkedOpenCustomHashSet.this.last = this.prev;
      } else {
        ObjectLinkedOpenCustomHashSet.this.link[this.next] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & ObjectLinkedOpenCustomHashSet.this.mask; ObjectLinkedOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(ObjectLinkedOpenCustomHashSet.this.key[pos])) & ObjectLinkedOpenCustomHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (ObjectLinkedOpenCustomHashSet.this.used[pos] == 0) {
          break;
        }
        ObjectLinkedOpenCustomHashSet.this.key[last] = ObjectLinkedOpenCustomHashSet.this.key[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        ObjectLinkedOpenCustomHashSet.this.fixPointers(pos, last);
      }
      ObjectLinkedOpenCustomHashSet.this.used[last] = false;
      ObjectLinkedOpenCustomHashSet.this.key[last] = null;
      this.curr = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLinkedOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */