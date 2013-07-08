package it.unimi.dsi.fastutil.bytes;

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

public class ByteOpenHashSet
  extends AbstractByteSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient byte[] key;
  protected transient boolean[] used;
  protected final float field_326;
  protected transient int field_327;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  
  public ByteOpenHashSet(int expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_326 = local_f;
    this.field_327 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_327 - 1);
    this.maxFill = HashCommon.maxFill(this.field_327, local_f);
    this.key = new byte[this.field_327];
    this.used = new boolean[this.field_327];
  }
  
  public ByteOpenHashSet(int expected)
  {
    this(expected, 0.75F);
  }
  
  public ByteOpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public ByteOpenHashSet(Collection<? extends Byte> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ByteOpenHashSet(Collection<? extends Byte> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ByteOpenHashSet(ByteCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ByteOpenHashSet(ByteCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ByteOpenHashSet(ByteIterator local_i, float local_f)
  {
    this(16, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextByte());
    }
  }
  
  public ByteOpenHashSet(ByteIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public ByteOpenHashSet(Iterator<?> local_i, float local_f)
  {
    this(ByteIterators.asByteIterator(local_i), local_f);
  }
  
  public ByteOpenHashSet(Iterator<?> local_i)
  {
    this(ByteIterators.asByteIterator(local_i));
  }
  
  public ByteOpenHashSet(byte[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0 : length, local_f);
    ByteArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ByteOpenHashSet(byte[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public ByteOpenHashSet(byte[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public ByteOpenHashSet(byte[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_326));
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
  
  public boolean remove(byte local_k)
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
  
  public boolean contains(byte local_k)
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
  
  public ByteIterator iterator()
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
    int local_l = HashCommon.arraySize(this.size, this.field_326);
    if (local_l >= this.field_327) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_326));
    if (this.field_327 <= local_l) {
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
    byte[] key = this.key;
    int newMask = newN - 1;
    byte[] newKey = new byte[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      byte local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_327 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_327, this.field_326);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public ByteOpenHashSet clone()
  {
    ByteOpenHashSet local_c;
    try
    {
      local_c = (ByteOpenHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((byte[])this.key.clone());
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
    ByteIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeByte(local_i.nextByte());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_327 = HashCommon.arraySize(this.size, this.field_326);
    this.maxFill = HashCommon.maxFill(this.field_327, this.field_326);
    this.mask = (this.field_327 - 1);
    byte[] key = this.key = new byte[this.field_327];
    boolean[] used = this.used = new boolean[this.field_327];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      byte local_k = local_s.readByte();
      for (pos = HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractByteIterator
  {
    int pos = ByteOpenHashSet.this.field_327;
    int last = -1;
    int field_363 = ByteOpenHashSet.this.size;
    ByteArrayList wrapped;
    
    private SetIterator()
    {
      boolean[] used = ByteOpenHashSet.this.used;
      while ((this.field_363 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_363 != 0;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_363 -= 1;
      if (this.pos < 0) {
        return this.wrapped.getByte(-(this.last = --this.pos) - 2);
      }
      byte retVal = ByteOpenHashSet.this.key[(this.last = this.pos)];
      if (this.field_363 != 0)
      {
        boolean[] used = ByteOpenHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & ByteOpenHashSet.this.mask; ByteOpenHashSet.this.used[pos] != 0; pos = pos + 1 & ByteOpenHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(ByteOpenHashSet.this.key[pos]) & ByteOpenHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (ByteOpenHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ByteArrayList();
          }
          this.wrapped.add(ByteOpenHashSet.this.key[pos]);
        }
        ByteOpenHashSet.this.key[last] = ByteOpenHashSet.this.key[pos];
      }
      ByteOpenHashSet.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        ByteOpenHashSet.this.remove(this.wrapped.getByte(-this.pos - 2));
        this.last = -1;
        return;
      }
      ByteOpenHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_363 > 0))
      {
        this.field_363 += 1;
        nextByte();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */