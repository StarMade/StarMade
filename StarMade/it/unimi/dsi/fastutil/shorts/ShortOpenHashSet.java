package it.unimi.dsi.fastutil.shorts;

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

public class ShortOpenHashSet
  extends AbstractShortSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient short[] key;
  protected transient boolean[] used;
  protected final float field_71;
  protected transient int field_72;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  
  public ShortOpenHashSet(int expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_71 = local_f;
    this.field_72 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_72 - 1);
    this.maxFill = HashCommon.maxFill(this.field_72, local_f);
    this.key = new short[this.field_72];
    this.used = new boolean[this.field_72];
  }
  
  public ShortOpenHashSet(int expected)
  {
    this(expected, 0.75F);
  }
  
  public ShortOpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public ShortOpenHashSet(Collection<? extends Short> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ShortOpenHashSet(Collection<? extends Short> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ShortOpenHashSet(ShortCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ShortOpenHashSet(ShortCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ShortOpenHashSet(ShortIterator local_i, float local_f)
  {
    this(16, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextShort());
    }
  }
  
  public ShortOpenHashSet(ShortIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public ShortOpenHashSet(Iterator<?> local_i, float local_f)
  {
    this(ShortIterators.asShortIterator(local_i), local_f);
  }
  
  public ShortOpenHashSet(Iterator<?> local_i)
  {
    this(ShortIterators.asShortIterator(local_i));
  }
  
  public ShortOpenHashSet(short[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0 : length, local_f);
    ShortArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ShortOpenHashSet(short[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public ShortOpenHashSet(short[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public ShortOpenHashSet(short[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_71));
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
        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (this.used[pos] == 0) {
        break;
      }
      this.key[last] = this.key[pos];
    }
    this.used[last] = false;
    return last;
  }
  
  public boolean remove(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        shiftKeys(pos);
        return true;
      }
    }
    return false;
  }
  
  public boolean contains(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
  
  public ShortIterator iterator()
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
    int local_l = HashCommon.arraySize(this.size, this.field_71);
    if (local_l >= this.field_72) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_71));
    if (this.field_72 <= local_l) {
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
    int local_i = 0;
    boolean[] used = this.used;
    short[] key = this.key;
    int newMask = newN - 1;
    short[] newKey = new short[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      short local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_72 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_72, this.field_71);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public ShortOpenHashSet clone()
  {
    ShortOpenHashSet local_c;
    try
    {
      local_c = (ShortOpenHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((short[])this.key.clone());
    local_c.used = ((boolean[])this.used.clone());
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
      local_h += this.key[local_i];
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    ShortIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeShort(local_i.nextShort());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_72 = HashCommon.arraySize(this.size, this.field_71);
    this.maxFill = HashCommon.maxFill(this.field_72, this.field_71);
    this.mask = (this.field_72 - 1);
    short[] key = this.key = new short[this.field_72];
    boolean[] used = this.used = new boolean[this.field_72];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      short local_k = local_s.readShort();
      for (pos = HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractShortIterator
  {
    int pos = ShortOpenHashSet.this.field_72;
    int last = -1;
    int field_381 = ShortOpenHashSet.this.size;
    ShortArrayList wrapped;
    
    private SetIterator()
    {
      boolean[] used = ShortOpenHashSet.this.used;
      while ((this.field_381 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_381 != 0;
    }
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_381 -= 1;
      if (this.pos < 0) {
        return this.wrapped.getShort(-(this.last = --this.pos) - 2);
      }
      short retVal = ShortOpenHashSet.this.key[(this.last = this.pos)];
      if (this.field_381 != 0)
      {
        boolean[] used = ShortOpenHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & ShortOpenHashSet.this.mask; ShortOpenHashSet.this.used[pos] != 0; pos = pos + 1 & ShortOpenHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(ShortOpenHashSet.this.key[pos]) & ShortOpenHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (ShortOpenHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ShortArrayList();
          }
          this.wrapped.add(ShortOpenHashSet.this.key[pos]);
        }
        ShortOpenHashSet.this.key[last] = ShortOpenHashSet.this.key[pos];
      }
      ShortOpenHashSet.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        ShortOpenHashSet.this.remove(this.wrapped.getShort(-this.pos - 2));
        this.last = -1;
        return;
      }
      ShortOpenHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_381 > 0))
      {
        this.field_381 += 1;
        nextShort();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */