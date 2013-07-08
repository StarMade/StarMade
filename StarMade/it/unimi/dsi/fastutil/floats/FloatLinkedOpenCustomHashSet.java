package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FloatLinkedOpenCustomHashSet
  extends AbstractFloatSortedSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient float[] key;
  protected transient boolean[] used;
  protected final float field_351;
  protected transient int field_352;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  protected FloatHash.Strategy strategy;
  
  public FloatLinkedOpenCustomHashSet(int expected, float local_f, FloatHash.Strategy strategy)
  {
    this.strategy = strategy;
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_351 = local_f;
    this.field_352 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_352 - 1);
    this.maxFill = HashCommon.maxFill(this.field_352, local_f);
    this.key = new float[this.field_352];
    this.used = new boolean[this.field_352];
    this.link = new long[this.field_352];
  }
  
  public FloatLinkedOpenCustomHashSet(int expected, FloatHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(FloatHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(Collection<? extends Float> local_c, float local_f, FloatHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public FloatLinkedOpenCustomHashSet(Collection<? extends Float> local_c, FloatHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(FloatCollection local_c, float local_f, FloatHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public FloatLinkedOpenCustomHashSet(FloatCollection local_c, FloatHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(FloatIterator local_i, float local_f, FloatHash.Strategy strategy)
  {
    this(16, local_f, strategy);
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public FloatLinkedOpenCustomHashSet(FloatIterator local_i, FloatHash.Strategy strategy)
  {
    this(local_i, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(Iterator<?> local_i, float local_f, FloatHash.Strategy strategy)
  {
    this(FloatIterators.asFloatIterator(local_i), local_f, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(Iterator<?> local_i, FloatHash.Strategy strategy)
  {
    this(FloatIterators.asFloatIterator(local_i), strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(float[] local_a, int offset, int length, float local_f, FloatHash.Strategy strategy)
  {
    this(length < 0 ? 0 : length, local_f, strategy);
    FloatArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public FloatLinkedOpenCustomHashSet(float[] local_a, int offset, int length, FloatHash.Strategy strategy)
  {
    this(local_a, offset, length, 0.75F, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(float[] local_a, float local_f, FloatHash.Strategy strategy)
  {
    this(local_a, 0, local_a.length, local_f, strategy);
  }
  
  public FloatLinkedOpenCustomHashSet(float[] local_a, FloatHash.Strategy strategy)
  {
    this(local_a, 0.75F, strategy);
  }
  
  public FloatHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public boolean add(float local_k)
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
      rehash(HashCommon.arraySize(this.size + 1, this.field_351));
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
    return last;
  }
  
  public boolean remove(float local_k)
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
  
  public boolean contains(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public float removeFirstFloat()
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
    float local_k = this.key[pos];
    shiftKeys(pos);
    return local_k;
  }
  
  public float removeLastFloat()
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
    float local_k = this.key[pos];
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
  
  public boolean addAndMoveToFirst(float local_k)
  {
    float[] key = this.key;
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
      rehash(HashCommon.arraySize(this.size, this.field_351));
    }
    return true;
  }
  
  public boolean addAndMoveToLast(float local_k)
  {
    float[] key = this.key;
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
      rehash(HashCommon.arraySize(this.size, this.field_351));
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
  
  public float firstFloat()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public float lastFloat()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public FloatSortedSet tailSet(float from)
  {
    throw new UnsupportedOperationException();
  }
  
  public FloatSortedSet headSet(float local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public FloatSortedSet subSet(float from, float local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public FloatComparator comparator()
  {
    return null;
  }
  
  public FloatListIterator iterator(float from)
  {
    return new SetIterator(from);
  }
  
  public FloatListIterator iterator()
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
    int local_l = HashCommon.arraySize(this.size, this.field_351);
    if (local_l >= this.field_352) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_351));
    if (this.field_352 <= local_l) {
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
    int newMask = newN - 1;
    float[] newKey = new float[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      float local_k = key[local_i];
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
    this.field_352 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_352, this.field_351);
    this.key = newKey;
    this.used = newUsed;
    this.link = newLink;
    this.last = newPrev;
    if (newPrev != -1) {
      newLink[newPrev] |= 4294967295L;
    }
  }
  
  public FloatLinkedOpenCustomHashSet clone()
  {
    FloatLinkedOpenCustomHashSet local_c;
    try
    {
      local_c = (FloatLinkedOpenCustomHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((float[])this.key.clone());
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
      local_h += this.strategy.hashCode(this.key[local_i]);
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    FloatIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeFloat(local_i.nextFloat());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_352 = HashCommon.arraySize(this.size, this.field_351);
    this.maxFill = HashCommon.maxFill(this.field_352, this.field_351);
    this.mask = (this.field_352 - 1);
    float[] key = this.key = new float[this.field_352];
    boolean[] used = this.used = new boolean[this.field_352];
    long[] link = this.link = new long[this.field_352];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      float local_k = local_s.readFloat();
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
    extends AbstractFloatListIterator
  {
    int prev = -1;
    int next = -1;
    int curr = -1;
    int index = -1;
    
    SetIterator()
    {
      this.next = FloatLinkedOpenCustomHashSet.this.first;
      this.index = 0;
    }
    
    SetIterator(float from)
    {
      if (FloatLinkedOpenCustomHashSet.this.strategy.equals(FloatLinkedOpenCustomHashSet.this.key[FloatLinkedOpenCustomHashSet.this.last], from))
      {
        this.prev = FloatLinkedOpenCustomHashSet.this.last;
        this.index = FloatLinkedOpenCustomHashSet.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(FloatLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & FloatLinkedOpenCustomHashSet.this.mask; FloatLinkedOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & FloatLinkedOpenCustomHashSet.this.mask) {
          if (FloatLinkedOpenCustomHashSet.this.strategy.equals(FloatLinkedOpenCustomHashSet.this.key[pos], from))
          {
            this.next = ((int)FloatLinkedOpenCustomHashSet.this.link[pos]);
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
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = this.next;
      this.next = ((int)FloatLinkedOpenCustomHashSet.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return FloatLinkedOpenCustomHashSet.this.key[this.curr];
    }
    
    public float previousFloat()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = this.prev;
      this.prev = ((int)(FloatLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return FloatLinkedOpenCustomHashSet.this.key[this.curr];
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
        this.index = FloatLinkedOpenCustomHashSet.this.size;
        return;
      }
      int pos = FloatLinkedOpenCustomHashSet.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)FloatLinkedOpenCustomHashSet.this.link[pos];
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
        this.prev = ((int)(FloatLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)FloatLinkedOpenCustomHashSet.this.link[this.curr]);
      }
      FloatLinkedOpenCustomHashSet.this.size -= 1;
      if (this.prev == -1) {
        FloatLinkedOpenCustomHashSet.this.first = this.next;
      } else {
        FloatLinkedOpenCustomHashSet.this.link[this.prev] ^= (FloatLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        FloatLinkedOpenCustomHashSet.this.last = this.prev;
      } else {
        FloatLinkedOpenCustomHashSet.this.link[this.next] ^= (FloatLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & FloatLinkedOpenCustomHashSet.this.mask; FloatLinkedOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & FloatLinkedOpenCustomHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(FloatLinkedOpenCustomHashSet.this.strategy.hashCode(FloatLinkedOpenCustomHashSet.this.key[pos])) & FloatLinkedOpenCustomHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (FloatLinkedOpenCustomHashSet.this.used[pos] == 0) {
          break;
        }
        FloatLinkedOpenCustomHashSet.this.key[last] = FloatLinkedOpenCustomHashSet.this.key[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        FloatLinkedOpenCustomHashSet.this.fixPointers(pos, last);
      }
      FloatLinkedOpenCustomHashSet.this.used[last] = false;
      this.curr = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatLinkedOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */