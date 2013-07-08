package it.unimi.dsi.fastutil.doubles;

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

public class DoubleOpenHashBigSet
  extends AbstractDoubleSet
  implements Serializable, Cloneable, Hash, Size64
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[][] key;
  protected transient boolean[][] used;
  protected final float field_64;
  protected transient long field_65;
  protected transient long maxFill;
  protected transient long mask;
  protected transient int segmentMask;
  protected transient int baseMask;
  protected long size;
  
  private void initMasks()
  {
    this.mask = (this.field_65 - 1L);
    this.segmentMask = (this.key[0].length - 1);
    this.baseMask = (this.key.length - 1);
  }
  
  public DoubleOpenHashBigSet(long expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (this.field_65 < 0L) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_64 = local_f;
    this.field_65 = HashCommon.bigArraySize(expected, local_f);
    this.maxFill = HashCommon.maxFill(this.field_65, local_f);
    this.key = DoubleBigArrays.newBigArray(this.field_65);
    this.used = BooleanBigArrays.newBigArray(this.field_65);
    initMasks();
  }
  
  public DoubleOpenHashBigSet(long expected)
  {
    this(expected, 0.75F);
  }
  
  public DoubleOpenHashBigSet()
  {
    this(16L, 0.75F);
  }
  
  public DoubleOpenHashBigSet(Collection<? extends Double> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public DoubleOpenHashBigSet(Collection<? extends Double> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public DoubleOpenHashBigSet(DoubleCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public DoubleOpenHashBigSet(DoubleCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public DoubleOpenHashBigSet(DoubleIterator local_i, float local_f)
  {
    this(16L, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextDouble());
    }
  }
  
  public DoubleOpenHashBigSet(DoubleIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public DoubleOpenHashBigSet(Iterator<?> local_i, float local_f)
  {
    this(DoubleIterators.asDoubleIterator(local_i), local_f);
  }
  
  public DoubleOpenHashBigSet(Iterator<?> local_i)
  {
    this(DoubleIterators.asDoubleIterator(local_i));
  }
  
  public DoubleOpenHashBigSet(double[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0L : length, local_f);
    DoubleArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public DoubleOpenHashBigSet(double[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public DoubleOpenHashBigSet(double[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public DoubleOpenHashBigSet(double[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(double local_k)
  {
    long local_h = HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k));
    int displ = (int)(local_h & this.segmentMask);
    for (int base = (int)((local_h & this.mask) >>> 27); this.used[base][displ] != 0; base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask) {
      if (this.key[base][displ] == local_k) {
        return false;
      }
    }
    this.used[base][displ] = 1;
    this.key[base][displ] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(2L * this.field_65);
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
        long slot = HashCommon.murmurHash3(Double.doubleToRawLongBits(DoubleBigArrays.get(this.key, pos))) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (!BooleanBigArrays.get(this.used, pos)) {
        break;
      }
      DoubleBigArrays.set(this.key, last, DoubleBigArrays.get(this.key, pos));
    }
    BooleanBigArrays.set(this.used, last, false);
    return last;
  }
  
  public boolean remove(double local_k)
  {
    long local_h = HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k));
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
  
  public boolean contains(double local_k)
  {
    long local_h = HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k));
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
  
  public DoubleIterator iterator()
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
    long local_l = HashCommon.bigArraySize(this.size, this.field_64);
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
  
  public boolean trim(long local_n)
  {
    long local_l = HashCommon.bigArraySize(local_n, this.field_64);
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
  
  protected void rehash(long newN)
  {
    boolean[][] used = this.used;
    double[][] key = this.key;
    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
    double[][] newKey = DoubleBigArrays.newBigArray(newN);
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
      double local_k = key[base][displ];
      long local_h = HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k));
      int local_d = (int)(local_h & newSegmentMask);
      for (int local_b = (int)((local_h & newMask) >>> 27); newUsed[local_b][local_d] != 0; local_b = local_b + ((local_d = local_d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask) {}
      newUsed[local_b][local_d] = 1;
      newKey[local_b][local_d] = local_k;
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    this.field_65 = newN;
    this.key = newKey;
    this.used = newUsed;
    initMasks();
    this.maxFill = HashCommon.maxFill(this.field_65, this.field_64);
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
  
  public DoubleOpenHashBigSet clone()
  {
    DoubleOpenHashBigSet local_c;
    try
    {
      local_c = (DoubleOpenHashBigSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = DoubleBigArrays.copy(this.key);
    local_c.used = BooleanBigArrays.copy(this.used);
    return local_c;
  }
  
  public int hashCode()
  {
    boolean[][] used = this.used;
    double[][] key = this.key;
    int local_h = 0;
    int base = 0;
    int displ = 0;
    long local_j = this.size;
    while (local_j-- != 0L)
    {
      while (used[base][displ] == 0) {
        base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
      }
      local_h += HashCommon.double2int(key[base][displ]);
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    DoubleIterator local_i = iterator();
    local_s.defaultWriteObject();
    long local_j = this.size;
    while (local_j-- != 0L) {
      local_s.writeDouble(local_i.nextDouble());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_65 = HashCommon.bigArraySize(this.size, this.field_64);
    this.maxFill = HashCommon.maxFill(this.field_65, this.field_64);
    double[][] key = this.key = DoubleBigArrays.newBigArray(this.field_65);
    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.field_65);
    initMasks();
    long local_i = this.size;
    while (local_i-- != 0L)
    {
      double local_k = local_s.readDouble();
      long local_h = HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k));
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
    extends AbstractDoubleIterator
  {
    int base = DoubleOpenHashBigSet.this.key.length;
    int displ;
    int lastBase = -1;
    int lastDispl;
    long field_301 = DoubleOpenHashBigSet.this.size;
    DoubleArrayList wrapped;
    
    private SetIterator()
    {
      boolean[][] used = DoubleOpenHashBigSet.this.used;
      if (this.field_301 != 0L) {
        do
        {
          if (this.displ-- == 0)
          {
            this.base -= 1;
            this.displ = ((int)DoubleOpenHashBigSet.this.mask);
          }
        } while (used[this.base][this.displ] == 0);
      }
    }
    
    public boolean hasNext()
    {
      return this.field_301 != 0L;
    }
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_301 -= 1L;
      if (this.base < 0) {
        return this.wrapped.getDouble(-(this.lastBase = --this.base) - 2);
      }
      double retVal = DoubleOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
      if (this.field_301 != 0L)
      {
        boolean[][] used = DoubleOpenHashBigSet.this.used;
        do
        {
          if (this.displ-- == 0)
          {
            if (this.base-- == 0) {
              break;
            }
            this.displ = ((int)DoubleOpenHashBigSet.this.mask);
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
        for (pos = (last = pos) + 1L & DoubleOpenHashBigSet.this.mask; BooleanBigArrays.get(DoubleOpenHashBigSet.this.used, pos); pos = pos + 1L & DoubleOpenHashBigSet.this.mask)
        {
          long slot = HashCommon.murmurHash3(Double.doubleToRawLongBits(DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos))) & DoubleOpenHashBigSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (!BooleanBigArrays.get(DoubleOpenHashBigSet.this.used, pos)) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new DoubleArrayList();
          }
          this.wrapped.add(DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos));
        }
        DoubleBigArrays.set(DoubleOpenHashBigSet.this.key, last, DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos));
      }
      BooleanBigArrays.set(DoubleOpenHashBigSet.this.used, last, false);
      return last;
    }
    
    public void remove()
    {
      if (this.lastBase == -1) {
        throw new IllegalStateException();
      }
      if (this.base < -1)
      {
        DoubleOpenHashBigSet.this.remove(this.wrapped.getDouble(-this.base - 2));
        this.lastBase = -1;
        return;
      }
      DoubleOpenHashBigSet.this.size -= 1L;
      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.field_301 > 0L))
      {
        this.field_301 += 1L;
        nextDouble();
      }
      this.lastBase = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */