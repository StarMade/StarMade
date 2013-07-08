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

public class FloatOpenCustomHashSet
  extends AbstractFloatSet
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
  protected FloatHash.Strategy strategy;
  
  public FloatOpenCustomHashSet(int expected, float local_f, FloatHash.Strategy strategy)
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
  }
  
  public FloatOpenCustomHashSet(int expected, FloatHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(FloatHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(Collection<? extends Float> local_c, float local_f, FloatHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public FloatOpenCustomHashSet(Collection<? extends Float> local_c, FloatHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(FloatCollection local_c, float local_f, FloatHash.Strategy strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public FloatOpenCustomHashSet(FloatCollection local_c, FloatHash.Strategy strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(FloatIterator local_i, float local_f, FloatHash.Strategy strategy)
  {
    this(16, local_f, strategy);
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public FloatOpenCustomHashSet(FloatIterator local_i, FloatHash.Strategy strategy)
  {
    this(local_i, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(Iterator<?> local_i, float local_f, FloatHash.Strategy strategy)
  {
    this(FloatIterators.asFloatIterator(local_i), local_f, strategy);
  }
  
  public FloatOpenCustomHashSet(Iterator<?> local_i, FloatHash.Strategy strategy)
  {
    this(FloatIterators.asFloatIterator(local_i), strategy);
  }
  
  public FloatOpenCustomHashSet(float[] local_a, int offset, int length, float local_f, FloatHash.Strategy strategy)
  {
    this(length < 0 ? 0 : length, local_f, strategy);
    FloatArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public FloatOpenCustomHashSet(float[] local_a, int offset, int length, FloatHash.Strategy strategy)
  {
    this(local_a, offset, length, 0.75F, strategy);
  }
  
  public FloatOpenCustomHashSet(float[] local_a, float local_f, FloatHash.Strategy strategy)
  {
    this(local_a, 0, local_a.length, local_f, strategy);
  }
  
  public FloatOpenCustomHashSet(float[] local_a, FloatHash.Strategy strategy)
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
  
  public FloatIterator iterator()
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
    int local_i = 0;
    boolean[] used = this.used;
    float[] key = this.key;
    int newMask = newN - 1;
    float[] newKey = new float[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      float local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_352 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_352, this.field_351);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public FloatOpenCustomHashSet clone()
  {
    FloatOpenCustomHashSet local_c;
    try
    {
      local_c = (FloatOpenCustomHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((float[])this.key.clone());
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
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      float local_k = local_s.readFloat();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractFloatIterator
  {
    int pos = FloatOpenCustomHashSet.this.field_352;
    int last = -1;
    int field_137 = FloatOpenCustomHashSet.this.size;
    FloatArrayList wrapped;
    
    private SetIterator()
    {
      boolean[] used = FloatOpenCustomHashSet.this.used;
      while ((this.field_137 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_137 != 0;
    }
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_137 -= 1;
      if (this.pos < 0) {
        return this.wrapped.getFloat(-(this.last = --this.pos) - 2);
      }
      float retVal = FloatOpenCustomHashSet.this.key[(this.last = this.pos)];
      if (this.field_137 != 0)
      {
        boolean[] used = FloatOpenCustomHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & FloatOpenCustomHashSet.this.mask; FloatOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & FloatOpenCustomHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(FloatOpenCustomHashSet.this.strategy.hashCode(FloatOpenCustomHashSet.this.key[pos])) & FloatOpenCustomHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (FloatOpenCustomHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new FloatArrayList();
          }
          this.wrapped.add(FloatOpenCustomHashSet.this.key[pos]);
        }
        FloatOpenCustomHashSet.this.key[last] = FloatOpenCustomHashSet.this.key[pos];
      }
      FloatOpenCustomHashSet.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        FloatOpenCustomHashSet.this.remove(this.wrapped.getFloat(-this.pos - 2));
        this.last = -1;
        return;
      }
      FloatOpenCustomHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_137 > 0))
      {
        this.field_137 += 1;
        nextFloat();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */