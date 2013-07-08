package it.unimi.dsi.fastutil.ints;

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

public class IntOpenCustomHashSet
  extends AbstractIntSet
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient int[] key;
  protected transient boolean[] used;
  protected final float field_320;
  protected transient int field_321;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected IntHash.Strategy strategy;
  
  public IntOpenCustomHashSet(int expected, float local_f, IntHash.Strategy strategy)
  {
    this.strategy = strategy;
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_320 = local_f;
    this.field_321 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_321 - 1);
    this.maxFill = HashCommon.maxFill(this.field_321, local_f);
    this.key = new int[this.field_321];
    this.used = new boolean[this.field_321];
  }
  
  public IntOpenCustomHashSet(int expected, IntHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(IntHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(Collection<? extends Integer> local_c, float local_f, IntHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public IntOpenCustomHashSet(Collection<? extends Integer> local_c, IntHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(IntCollection local_c, float local_f, IntHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public IntOpenCustomHashSet(IntCollection local_c, IntHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(IntIterator local_i, float local_f, IntHash.Strategy strategy)
  {
    this(16, local_f, strategy);
    while (local_i.hasNext()) {
      add(local_i.nextInt());
    }
  }
  
  public IntOpenCustomHashSet(IntIterator local_i, IntHash.Strategy strategy)
  {
    this(local_i, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(Iterator<?> local_i, float local_f, IntHash.Strategy strategy)
  {
    this(IntIterators.asIntIterator(local_i), local_f, strategy);
  }
  
  public IntOpenCustomHashSet(Iterator<?> local_i, IntHash.Strategy strategy)
  {
    this(IntIterators.asIntIterator(local_i), strategy);
  }
  
  public IntOpenCustomHashSet(int[] local_a, int offset, int length, float local_f, IntHash.Strategy strategy)
  {
    this(length < 0 ? 0 : length, local_f, strategy);
    IntArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public IntOpenCustomHashSet(int[] local_a, int offset, int length, IntHash.Strategy strategy)
  {
    this(local_a, offset, length, 0.75F, strategy);
  }
  
  public IntOpenCustomHashSet(int[] local_a, float local_f, IntHash.Strategy strategy)
  {
    this(local_a, 0, local_a.length, local_f, strategy);
  }
  
  public IntOpenCustomHashSet(int[] local_a, IntHash.Strategy strategy)
  {
    this(local_a, 0.75F, strategy);
  }
  
  public IntHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public boolean add(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_320));
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
    }
    this.used[last] = false;
    return last;
  }
  
  public boolean remove(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        shiftKeys(pos);
        return true;
      }
    }
    return false;
  }
  
  public boolean contains(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
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
    int local_l = HashCommon.arraySize(this.size, this.field_320);
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
  
  public boolean trim(int local_n)
  {
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_320));
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
  
  protected void rehash(int newN)
  {
    int local_i = 0;
    boolean[] used = this.used;
    int[] key = this.key;
    int newMask = newN - 1;
    int[] newKey = new int[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      int local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_321 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_321, this.field_320);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public IntOpenCustomHashSet clone()
  {
    IntOpenCustomHashSet local_c;
    try
    {
      local_c = (IntOpenCustomHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((int[])this.key.clone());
    local_c.used = ((boolean[])this.used.clone());
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
    IntIterator local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeInt(local_i.nextInt());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_321 = HashCommon.arraySize(this.size, this.field_320);
    this.maxFill = HashCommon.maxFill(this.field_321, this.field_320);
    this.mask = (this.field_321 - 1);
    int[] key = this.key = new int[this.field_321];
    boolean[] used = this.used = new boolean[this.field_321];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      int local_k = local_s.readInt();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractIntIterator
  {
    int pos = IntOpenCustomHashSet.this.field_321;
    int last = -1;
    int field_83 = IntOpenCustomHashSet.this.size;
    IntArrayList wrapped;
    
    private SetIterator()
    {
      boolean[] used = IntOpenCustomHashSet.this.used;
      while ((this.field_83 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_83 != 0;
    }
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_83 -= 1;
      if (this.pos < 0) {
        return this.wrapped.getInt(-(this.last = --this.pos) - 2);
      }
      int retVal = IntOpenCustomHashSet.this.key[(this.last = this.pos)];
      if (this.field_83 != 0)
      {
        boolean[] used = IntOpenCustomHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & IntOpenCustomHashSet.this.mask; IntOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & IntOpenCustomHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(IntOpenCustomHashSet.this.strategy.hashCode(IntOpenCustomHashSet.this.key[pos])) & IntOpenCustomHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (IntOpenCustomHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new IntArrayList();
          }
          this.wrapped.add(IntOpenCustomHashSet.this.key[pos]);
        }
        IntOpenCustomHashSet.this.key[last] = IntOpenCustomHashSet.this.key[pos];
      }
      IntOpenCustomHashSet.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        IntOpenCustomHashSet.this.remove(this.wrapped.getInt(-this.pos - 2));
        this.last = -1;
        return;
      }
      IntOpenCustomHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_83 > 0))
      {
        this.field_83 += 1;
        nextInt();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */