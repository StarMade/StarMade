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

public class FloatLinkedOpenHashSet
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
  
  public FloatLinkedOpenHashSet(int expected, float local_f)
  {
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
  
  public FloatLinkedOpenHashSet(int expected)
  {
    this(expected, 0.75F);
  }
  
  public FloatLinkedOpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public FloatLinkedOpenHashSet(Collection<? extends Float> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public FloatLinkedOpenHashSet(Collection<? extends Float> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public FloatLinkedOpenHashSet(FloatCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public FloatLinkedOpenHashSet(FloatCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public FloatLinkedOpenHashSet(FloatIterator local_i, float local_f)
  {
    this(16, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public FloatLinkedOpenHashSet(FloatIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public FloatLinkedOpenHashSet(Iterator<?> local_i, float local_f)
  {
    this(FloatIterators.asFloatIterator(local_i), local_f);
  }
  
  public FloatLinkedOpenHashSet(Iterator<?> local_i)
  {
    this(FloatIterators.asFloatIterator(local_i));
  }
  
  public FloatLinkedOpenHashSet(float[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0 : length, local_f);
    FloatArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public FloatLinkedOpenHashSet(float[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public FloatLinkedOpenHashSet(float[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public FloatLinkedOpenHashSet(float[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
        int slot = HashCommon.murmurHash3(HashCommon.float2int(this.key[pos])) & this.mask;
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
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
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
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
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
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
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
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public FloatLinkedOpenHashSet clone()
  {
    FloatLinkedOpenHashSet local_c;
    try
    {
      local_c = (FloatLinkedOpenHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((float[])this.key.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.link = ((long[])this.link.clone());
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
      local_h += HashCommon.float2int(this.key[local_i]);
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
      for (pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
      this.next = FloatLinkedOpenHashSet.this.first;
      this.index = 0;
    }
    
    SetIterator(float from)
    {
      if (FloatLinkedOpenHashSet.this.key[FloatLinkedOpenHashSet.this.last] == from)
      {
        this.prev = FloatLinkedOpenHashSet.this.last;
        this.index = FloatLinkedOpenHashSet.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(HashCommon.float2int(from)) & FloatLinkedOpenHashSet.this.mask; FloatLinkedOpenHashSet.this.used[pos] != 0; pos = pos + 1 & FloatLinkedOpenHashSet.this.mask) {
          if (FloatLinkedOpenHashSet.this.key[pos] == from)
          {
            this.next = ((int)FloatLinkedOpenHashSet.this.link[pos]);
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
      this.next = ((int)FloatLinkedOpenHashSet.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return FloatLinkedOpenHashSet.this.key[this.curr];
    }
    
    public float previousFloat()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = this.prev;
      this.prev = ((int)(FloatLinkedOpenHashSet.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return FloatLinkedOpenHashSet.this.key[this.curr];
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
        this.index = FloatLinkedOpenHashSet.this.size;
        return;
      }
      int pos = FloatLinkedOpenHashSet.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)FloatLinkedOpenHashSet.this.link[pos];
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
        this.prev = ((int)(FloatLinkedOpenHashSet.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)FloatLinkedOpenHashSet.this.link[this.curr]);
      }
      FloatLinkedOpenHashSet.this.size -= 1;
      if (this.prev == -1) {
        FloatLinkedOpenHashSet.this.first = this.next;
      } else {
        FloatLinkedOpenHashSet.this.link[this.prev] ^= (FloatLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        FloatLinkedOpenHashSet.this.last = this.prev;
      } else {
        FloatLinkedOpenHashSet.this.link[this.next] ^= (FloatLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & FloatLinkedOpenHashSet.this.mask; FloatLinkedOpenHashSet.this.used[pos] != 0; pos = pos + 1 & FloatLinkedOpenHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(HashCommon.float2int(FloatLinkedOpenHashSet.this.key[pos])) & FloatLinkedOpenHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (FloatLinkedOpenHashSet.this.used[pos] == 0) {
          break;
        }
        FloatLinkedOpenHashSet.this.key[last] = FloatLinkedOpenHashSet.this.key[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        FloatLinkedOpenHashSet.this.fixPointers(pos, last);
      }
      FloatLinkedOpenHashSet.this.used[last] = false;
      this.curr = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatLinkedOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */