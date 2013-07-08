package it.unimi.dsi.fastutil.doubles;

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

public class DoubleLinkedOpenHashSet
  extends AbstractDoubleSortedSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[] key;
  protected transient boolean[] used;
  protected final float field_64;
  protected transient int field_65;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public DoubleLinkedOpenHashSet(int expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_64 = local_f;
    this.field_65 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_65 - 1);
    this.maxFill = HashCommon.maxFill(this.field_65, local_f);
    this.key = new double[this.field_65];
    this.used = new boolean[this.field_65];
    this.link = new long[this.field_65];
  }
  
  public DoubleLinkedOpenHashSet(int expected)
  {
    this(expected, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet(Collection<? extends Double> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public DoubleLinkedOpenHashSet(Collection<? extends Double> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet(DoubleCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public DoubleLinkedOpenHashSet(DoubleCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet(DoubleIterator local_i, float local_f)
  {
    this(16, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextDouble());
    }
  }
  
  public DoubleLinkedOpenHashSet(DoubleIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet(Iterator<?> local_i, float local_f)
  {
    this(DoubleIterators.asDoubleIterator(local_i), local_f);
  }
  
  public DoubleLinkedOpenHashSet(Iterator<?> local_i)
  {
    this(DoubleIterators.asDoubleIterator(local_i));
  }
  
  public DoubleLinkedOpenHashSet(double[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0 : length, local_f);
    DoubleArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public DoubleLinkedOpenHashSet(double[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public DoubleLinkedOpenHashSet(double[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public DoubleLinkedOpenHashSet(double[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
      rehash(HashCommon.arraySize(this.size + 1, this.field_64));
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
        int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(this.key[pos])) & this.mask;
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
  
  public boolean remove(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public boolean contains(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
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
    double local_k = this.key[pos];
    shiftKeys(pos);
    return local_k;
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
    double local_k = this.key[pos];
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
  
  public boolean addAndMoveToFirst(double local_k)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
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
      rehash(HashCommon.arraySize(this.size, this.field_64));
    }
    return true;
  }
  
  public boolean addAndMoveToLast(double local_k)
  {
    double[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & mask; used[pos] != 0; pos = pos + 1 & mask) {
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
      rehash(HashCommon.arraySize(this.size, this.field_64));
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
  
  public double firstDouble()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public double lastDouble()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public DoubleSortedSet tailSet(double from)
  {
    throw new UnsupportedOperationException();
  }
  
  public DoubleSortedSet headSet(double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public DoubleSortedSet subSet(double from, double local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public DoubleComparator comparator()
  {
    return null;
  }
  
  public DoubleListIterator iterator(double from)
  {
    return new SetIterator(from);
  }
  
  public DoubleListIterator iterator()
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
    int local_l = HashCommon.arraySize(this.size, this.field_64);
    if (local_l >= this.field_65) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_64));
    if (this.field_65 <= local_l) {
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
    int newMask = newN - 1;
    double[] newKey = new double[newN];
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
    this.field_65 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_65, this.field_64);
    this.key = newKey;
    this.used = newUsed;
    this.link = newLink;
    this.last = newPrev;
    if (newPrev != -1) {
      newLink[newPrev] |= 4294967295L;
    }
  }
  
  public DoubleLinkedOpenHashSet clone()
  {
    DoubleLinkedOpenHashSet local_c;
    try
    {
      local_c = (DoubleLinkedOpenHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((double[])this.key.clone());
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
      local_h += HashCommon.double2int(this.key[local_i]);
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    DoubleIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeDouble(local_i.nextDouble());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_65 = HashCommon.arraySize(this.size, this.field_64);
    this.maxFill = HashCommon.maxFill(this.field_65, this.field_64);
    this.mask = (this.field_65 - 1);
    double[] key = this.key = new double[this.field_65];
    boolean[] used = this.used = new boolean[this.field_65];
    long[] link = this.link = new long[this.field_65];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      double local_k = local_s.readDouble();
      for (pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
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
    extends AbstractDoubleListIterator
  {
    int prev = -1;
    int next = -1;
    int curr = -1;
    int index = -1;
    
    SetIterator()
    {
      this.next = DoubleLinkedOpenHashSet.this.first;
      this.index = 0;
    }
    
    SetIterator(double from)
    {
      if (DoubleLinkedOpenHashSet.this.key[DoubleLinkedOpenHashSet.this.last] == from)
      {
        this.prev = DoubleLinkedOpenHashSet.this.last;
        this.index = DoubleLinkedOpenHashSet.this.size;
      }
      else
      {
        for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(from)) & DoubleLinkedOpenHashSet.this.mask; DoubleLinkedOpenHashSet.this.used[pos] != 0; pos = pos + 1 & DoubleLinkedOpenHashSet.this.mask) {
          if (DoubleLinkedOpenHashSet.this.key[pos] == from)
          {
            this.next = ((int)DoubleLinkedOpenHashSet.this.link[pos]);
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
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = this.next;
      this.next = ((int)DoubleLinkedOpenHashSet.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return DoubleLinkedOpenHashSet.this.key[this.curr];
    }
    
    public double previousDouble()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = this.prev;
      this.prev = ((int)(DoubleLinkedOpenHashSet.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return DoubleLinkedOpenHashSet.this.key[this.curr];
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
        this.index = DoubleLinkedOpenHashSet.this.size;
        return;
      }
      int pos = DoubleLinkedOpenHashSet.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)DoubleLinkedOpenHashSet.this.link[pos];
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
        this.prev = ((int)(DoubleLinkedOpenHashSet.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)DoubleLinkedOpenHashSet.this.link[this.curr]);
      }
      DoubleLinkedOpenHashSet.this.size -= 1;
      if (this.prev == -1) {
        DoubleLinkedOpenHashSet.this.first = this.next;
      } else {
        DoubleLinkedOpenHashSet.this.link[this.prev] ^= (DoubleLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        DoubleLinkedOpenHashSet.this.last = this.prev;
      } else {
        DoubleLinkedOpenHashSet.this.link[this.next] ^= (DoubleLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & DoubleLinkedOpenHashSet.this.mask; DoubleLinkedOpenHashSet.this.used[pos] != 0; pos = pos + 1 & DoubleLinkedOpenHashSet.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(DoubleLinkedOpenHashSet.this.key[pos])) & DoubleLinkedOpenHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (DoubleLinkedOpenHashSet.this.used[pos] == 0) {
          break;
        }
        DoubleLinkedOpenHashSet.this.key[last] = DoubleLinkedOpenHashSet.this.key[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        DoubleLinkedOpenHashSet.this.fixPointers(pos, last);
      }
      DoubleLinkedOpenHashSet.this.used[last] = false;
      this.curr = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleLinkedOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */