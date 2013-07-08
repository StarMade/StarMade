package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.Size64;
import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntOpenHashBigSet
  extends AbstractIntSet
  implements Serializable, Cloneable, Hash, Size64
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient int[][] key;
  protected transient boolean[][] used;
  protected final float field_320;
  protected transient long field_321;
  protected transient long maxFill;
  protected transient long mask;
  protected transient int segmentMask;
  protected transient int baseMask;
  protected long size;
  
  private void initMasks()
  {
    this.mask = (this.field_321 - 1L);
    this.segmentMask = (this.key[0].length - 1);
    this.baseMask = (this.key.length - 1);
  }
  
  public IntOpenHashBigSet(long expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (this.field_321 < 0L) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_320 = local_f;
    this.field_321 = HashCommon.bigArraySize(expected, local_f);
    this.maxFill = HashCommon.maxFill(this.field_321, local_f);
    this.key = IntBigArrays.newBigArray(this.field_321);
    this.used = BooleanBigArrays.newBigArray(this.field_321);
    initMasks();
  }
  
  public IntOpenHashBigSet(long expected)
  {
    this(expected, 0.75F);
  }
  
  public IntOpenHashBigSet()
  {
    this(16L, 0.75F);
  }
  
  public IntOpenHashBigSet(Collection<? extends Integer> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public IntOpenHashBigSet(Collection<? extends Integer> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public IntOpenHashBigSet(IntCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public IntOpenHashBigSet(IntCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public IntOpenHashBigSet(IntIterator local_i, float local_f)
  {
    this(16L, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextInt());
    }
  }
  
  public IntOpenHashBigSet(IntIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public IntOpenHashBigSet(Iterator<?> local_i, float local_f)
  {
    this(IntIterators.asIntIterator(local_i), local_f);
  }
  
  public IntOpenHashBigSet(Iterator<?> local_i)
  {
    this(IntIterators.asIntIterator(local_i));
  }
  
  public IntOpenHashBigSet(int[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0L : length, local_f);
    IntArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public IntOpenHashBigSet(int[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public IntOpenHashBigSet(int[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public IntOpenHashBigSet(int[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(int local_k)
  {
    long local_h = HashCommon.murmurHash3(local_k);
    int displ = (int)(local_h & this.segmentMask);
    for (int base = (int)((local_h & this.mask) >>> 27); this.used[base][displ] != 0; base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask) {
      if (this.key[base][displ] == local_k) {
        return false;
      }
    }
    this.used[base][displ] = 1;
    this.key[base][displ] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(2L * this.field_321);
    }
    return true;
  }
  
  protected final long shiftKeys(long pos)
  {
    long last;
    for (;;)
    {
      for (pos = (last = pos) + 1L & this.mask; BooleanBigArrays.get(this.used, pos); pos = pos + 1L & this.mask)
      {
        long slot = HashCommon.murmurHash3(IntBigArrays.get(this.key, pos)) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (!BooleanBigArrays.get(this.used, pos)) {
        break;
      }
      IntBigArrays.set(this.key, last, IntBigArrays.get(this.key, pos));
    }
    BooleanBigArrays.set(this.used, last, false);
    return last;
  }
  
  public boolean remove(int local_k)
  {
    long local_h = HashCommon.murmurHash3(local_k);
    int displ = (int)(local_h & this.segmentMask);
    for (int base = (int)((local_h & this.mask) >>> 27); this.used[base][displ] != 0; base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask) {
      if (this.key[base][displ] == local_k)
      {
        this.size -= 1L;
        shiftKeys(base * 134217728L + displ);
        return true;
      }
    }
    return false;
  }
  
  public boolean contains(int local_k)
  {
    long local_h = HashCommon.murmurHash3(local_k);
    int displ = (int)(local_h & this.segmentMask);
    for (int base = (int)((local_h & this.mask) >>> 27); this.used[base][displ] != 0; base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask) {
      if (this.key[base][displ] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    if (this.size == 0L) {
      return;
    }
    this.size = 0L;
    BooleanBigArrays.fill(this.used, false);
  }
  
  public IntIterator iterator()
  {
    return new SetIterator(null);
  }
  
  @Deprecated
  public boolean rehash()
  {
    return true;
  }
  
  public boolean trim()
  {
    long local_l = HashCommon.bigArraySize(this.size, this.field_320);
    if (local_l >= this.field_321) {
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
  
  public boolean trim(long local_n)
  {
    long local_l = HashCommon.bigArraySize(local_n, this.field_320);
    if (this.field_321 <= local_l) {
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
  
  protected void rehash(long newN)
  {
    boolean[][] used = this.used;
    int[][] key = this.key;
    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
    int[][] newKey = IntBigArrays.newBigArray(newN);
    long newMask = newN - 1L;
    int newSegmentMask = newKey[0].length - 1;
    int newBaseMask = newKey.length - 1;
    int base = 0;
    int displ = 0;
    long local_i = this.size;
    while (local_i-- != 0L)
    {
      while (used[base][displ] == 0) {
        base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
      }
      int local_k = key[base][displ];
      long local_h = HashCommon.murmurHash3(local_k);
      int local_d = (int)(local_h & newSegmentMask);
      for (int local_b = (int)((local_h & newMask) >>> 27); newUsed[local_b][local_d] != 0; local_b = local_b + ((local_d = local_d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask) {}
      newUsed[local_b][local_d] = 1;
      newKey[local_b][local_d] = local_k;
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    this.field_321 = newN;
    this.key = newKey;
    this.used = newUsed;
    initMasks();
    this.maxFill = HashCommon.maxFill(this.field_321, this.field_320);
  }
  
  @Deprecated
  public int size()
  {
    return (int)Math.min(2147483647L, this.size);
  }
  
  public long size64()
  {
    return this.size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0L;
  }
  
  public IntOpenHashBigSet clone()
  {
    IntOpenHashBigSet local_c;
    try
    {
      local_c = (IntOpenHashBigSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = IntBigArrays.copy(this.key);
    local_c.used = BooleanBigArrays.copy(this.used);
    return local_c;
  }
  
  public int hashCode()
  {
    boolean[][] used = this.used;
    int[][] key = this.key;
    int local_h = 0;
    int base = 0;
    int displ = 0;
    long local_j = this.size;
    while (local_j-- != 0L)
    {
      while (used[base][displ] == 0) {
        base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
      }
      local_h += key[base][displ];
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    IntIterator local_i = iterator();
    local_s.defaultWriteObject();
    long local_j = this.size;
    while (local_j-- != 0L) {
      local_s.writeInt(local_i.nextInt());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_321 = HashCommon.bigArraySize(this.size, this.field_320);
    this.maxFill = HashCommon.maxFill(this.field_321, this.field_320);
    int[][] key = this.key = IntBigArrays.newBigArray(this.field_321);
    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.field_321);
    initMasks();
    long local_i = this.size;
    while (local_i-- != 0L)
    {
      int local_k = local_s.readInt();
      long local_h = HashCommon.murmurHash3(local_k);
      int base = (int)((local_h & this.mask) >>> 27);
      int displ = (int)(local_h & this.segmentMask);
      while (used[base][displ] != 0) {
        base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
      }
      used[base][displ] = 1;
      key[base][displ] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractIntIterator
  {
    int base = IntOpenHashBigSet.this.key.length;
    int displ;
    int lastBase = -1;
    int lastDispl;
    long field_83 = IntOpenHashBigSet.this.size;
    IntArrayList wrapped;
    
    private SetIterator()
    {
      boolean[][] used = IntOpenHashBigSet.this.used;
      if (this.field_83 != 0L) {
        do
        {
          if (this.displ-- == 0)
          {
            this.base -= 1;
            this.displ = ((int)IntOpenHashBigSet.this.mask);
          }
        } while (used[this.base][this.displ] == 0);
      }
    }
    
    public boolean hasNext()
    {
      return this.field_83 != 0L;
    }
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_83 -= 1L;
      if (this.base < 0) {
        return this.wrapped.getInt(-(this.lastBase = --this.base) - 2);
      }
      int retVal = IntOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
      if (this.field_83 != 0L)
      {
        boolean[][] used = IntOpenHashBigSet.this.used;
        do
        {
          if (this.displ-- == 0)
          {
            if (this.base-- == 0) {
              break;
            }
            this.displ = ((int)IntOpenHashBigSet.this.mask);
          }
        } while (used[this.base][this.displ] == 0);
      }
      return retVal;
    }
    
    protected final long shiftKeys(long pos)
    {
      long last;
      for (;;)
      {
        for (pos = (last = pos) + 1L & IntOpenHashBigSet.this.mask; BooleanBigArrays.get(IntOpenHashBigSet.this.used, pos); pos = pos + 1L & IntOpenHashBigSet.this.mask)
        {
          long slot = HashCommon.murmurHash3(IntBigArrays.get(IntOpenHashBigSet.this.key, pos)) & IntOpenHashBigSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (!BooleanBigArrays.get(IntOpenHashBigSet.this.used, pos)) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new IntArrayList();
          }
          this.wrapped.add(IntBigArrays.get(IntOpenHashBigSet.this.key, pos));
        }
        IntBigArrays.set(IntOpenHashBigSet.this.key, last, IntBigArrays.get(IntOpenHashBigSet.this.key, pos));
      }
      BooleanBigArrays.set(IntOpenHashBigSet.this.used, last, false);
      return last;
    }
    
    public void remove()
    {
      if (this.lastBase == -1) {
        throw new IllegalStateException();
      }
      if (this.base < -1)
      {
        IntOpenHashBigSet.this.remove(this.wrapped.getInt(-this.base - 2));
        this.lastBase = -1;
        return;
      }
      IntOpenHashBigSet.this.size -= 1L;
      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.field_83 > 0L))
      {
        this.field_83 += 1L;
        nextInt();
      }
      this.lastBase = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */