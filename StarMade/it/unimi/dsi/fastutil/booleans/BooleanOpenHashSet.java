package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BooleanOpenHashSet
  extends AbstractBooleanSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient boolean[] key;
  protected transient boolean[] used;
  protected final float field_401;
  protected transient int field_402;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  
  public BooleanOpenHashSet(int expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_401 = local_f;
    this.field_402 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_402 - 1);
    this.maxFill = HashCommon.maxFill(this.field_402, local_f);
    this.key = new boolean[this.field_402];
    this.used = new boolean[this.field_402];
  }
  
  public BooleanOpenHashSet(int expected)
  {
    this(expected, 0.75F);
  }
  
  public BooleanOpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public BooleanOpenHashSet(Collection<? extends Boolean> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public BooleanOpenHashSet(Collection<? extends Boolean> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public BooleanOpenHashSet(BooleanCollection local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public BooleanOpenHashSet(BooleanCollection local_c)
  {
    this(local_c, 0.75F);
  }
  
  public BooleanOpenHashSet(BooleanIterator local_i, float local_f)
  {
    this(16, local_f);
    while (local_i.hasNext()) {
      add(local_i.nextBoolean());
    }
  }
  
  public BooleanOpenHashSet(BooleanIterator local_i)
  {
    this(local_i, 0.75F);
  }
  
  public BooleanOpenHashSet(Iterator<?> local_i, float local_f)
  {
    this(BooleanIterators.asBooleanIterator(local_i), local_f);
  }
  
  public BooleanOpenHashSet(Iterator<?> local_i)
  {
    this(BooleanIterators.asBooleanIterator(local_i));
  }
  
  public BooleanOpenHashSet(boolean[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0 : length, local_f);
    BooleanArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public BooleanOpenHashSet(boolean[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public BooleanOpenHashSet(boolean[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public BooleanOpenHashSet(boolean[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(boolean local_k)
  {
    for (int pos = (local_k ? 262886248 : -878682501) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_401));
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
        int slot = (this.key[pos] != 0 ? 262886248 : -878682501) & this.mask;
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
  
  public boolean remove(boolean local_k)
  {
    for (int pos = (local_k ? 262886248 : -878682501) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        shiftKeys(pos);
        return true;
      }
    }
    return false;
  }
  
  public boolean contains(boolean local_k)
  {
    for (int pos = (local_k ? 262886248 : -878682501) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public BooleanIterator iterator()
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
    int local_l = HashCommon.arraySize(this.size, this.field_401);
    if (local_l >= this.field_402) {
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
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_401));
    if (this.field_402 <= local_l) {
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
    boolean[] key = this.key;
    int newMask = newN - 1;
    boolean[] newKey = new boolean[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      boolean local_k = key[local_i];
      for (int pos = (local_k ? 262886248 : -878682501) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_402 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_402, this.field_401);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public BooleanOpenHashSet clone()
  {
    BooleanOpenHashSet local_c;
    try
    {
      local_c = (BooleanOpenHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((boolean[])this.key.clone());
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
      local_h += (this.key[local_i] != 0 ? 1231 : 1237);
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    BooleanIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeBoolean(local_i.nextBoolean());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_402 = HashCommon.arraySize(this.size, this.field_401);
    this.maxFill = HashCommon.maxFill(this.field_402, this.field_401);
    this.mask = (this.field_402 - 1);
    boolean[] key = this.key = new boolean[this.field_402];
    boolean[] used = this.used = new boolean[this.field_402];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      boolean local_k = local_s.readBoolean();
      for (pos = (local_k ? 262886248 : -878682501) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractBooleanIterator
  {
    int pos = BooleanOpenHashSet.this.field_402;
    int last = -1;
    int field_305 = BooleanOpenHashSet.this.size;
    BooleanArrayList wrapped;
    
    private SetIterator()
    {
      boolean[] used = BooleanOpenHashSet.this.used;
      while ((this.field_305 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_305 != 0;
    }
    
    public boolean nextBoolean()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_305 -= 1;
      if (this.pos < 0) {
        return this.wrapped.getBoolean(-(this.last = --this.pos) - 2);
      }
      boolean retVal = BooleanOpenHashSet.this.key[(this.last = this.pos)];
      if (this.field_305 != 0)
      {
        boolean[] used = BooleanOpenHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & BooleanOpenHashSet.this.mask; BooleanOpenHashSet.this.used[pos] != 0; pos = pos + 1 & BooleanOpenHashSet.this.mask)
        {
          int slot = (BooleanOpenHashSet.this.key[pos] != 0 ? 262886248 : -878682501) & BooleanOpenHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (BooleanOpenHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new BooleanArrayList();
          }
          this.wrapped.add(BooleanOpenHashSet.this.key[pos]);
        }
        BooleanOpenHashSet.this.key[last] = BooleanOpenHashSet.this.key[pos];
      }
      BooleanOpenHashSet.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        BooleanOpenHashSet.this.remove(this.wrapped.getBoolean(-this.pos - 2));
        this.last = -1;
        return;
      }
      BooleanOpenHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_305 > 0))
      {
        this.field_305 += 1;
        nextBoolean();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */