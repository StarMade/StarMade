package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.Size64;
import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.NoSuchElementException;

public class ReferenceOpenHashBigSet<K>
  extends AbstractReferenceSet<K>
  implements Serializable, Cloneable, Hash, Size64
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[][] key;
  protected transient boolean[][] used;
  protected final float field_73;
  protected transient long field_74;
  protected transient long maxFill;
  protected transient long mask;
  protected transient int segmentMask;
  protected transient int baseMask;
  protected long size;
  
  private void initMasks()
  {
    this.mask = (this.field_74 - 1L);
    this.segmentMask = (this.key[0].length - 1);
    this.baseMask = (this.key.length - 1);
  }
  
  public ReferenceOpenHashBigSet(long expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (this.field_74 < 0L) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_73 = local_f;
    this.field_74 = HashCommon.bigArraySize(expected, local_f);
    this.maxFill = HashCommon.maxFill(this.field_74, local_f);
    this.key = ((Object[][])ObjectBigArrays.newBigArray(this.field_74));
    this.used = BooleanBigArrays.newBigArray(this.field_74);
    initMasks();
  }
  
  public ReferenceOpenHashBigSet(long expected)
  {
    this(expected, 0.75F);
  }
  
  public ReferenceOpenHashBigSet()
  {
    this(16L, 0.75F);
  }
  
  public ReferenceOpenHashBigSet(Collection<? extends K> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ReferenceOpenHashBigSet(Collection<? extends K> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> local_c, float local_f)
  {
    this(local_c.size(), local_f);
    addAll(local_c);
  }
  
  public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> local_c)
  {
    this(local_c, 0.75F);
  }
  
  public ReferenceOpenHashBigSet(ObjectIterator<K> local_i, float local_f)
  {
    this(16L, local_f);
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ReferenceOpenHashBigSet(ObjectIterator<K> local_i)
  {
    this(local_i, 0.75F);
  }
  
  public ReferenceOpenHashBigSet(K[] local_a, int offset, int length, float local_f)
  {
    this(length < 0 ? 0L : length, local_f);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ReferenceOpenHashBigSet(K[] local_a, int offset, int length)
  {
    this(local_a, offset, length, 0.75F);
  }
  
  public ReferenceOpenHashBigSet(K[] local_a, float local_f)
  {
    this(local_a, 0, local_a.length, local_f);
  }
  
  public ReferenceOpenHashBigSet(K[] local_a)
  {
    this(local_a, 0.75F);
  }
  
  public boolean add(K local_k)
  {
    long local_h = local_k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(local_k));
    int displ = (int)(local_h & this.segmentMask);
    for (int base = (int)((local_h & this.mask) >>> 27); this.used[base][displ] != 0; base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask) {
      if (this.key[base][displ] == local_k) {
        return false;
      }
    }
    this.used[base][displ] = 1;
    this.key[base][displ] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(2L * this.field_74);
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
        long slot = (ObjectBigArrays.get(this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(this.key, pos)))) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (!BooleanBigArrays.get(this.used, pos)) {
        break;
      }
      ObjectBigArrays.set(this.key, last, ObjectBigArrays.get(this.key, pos));
    }
    BooleanBigArrays.set(this.used, last, false);
    ObjectBigArrays.set(this.key, last, null);
    return last;
  }
  
  public boolean remove(Object local_k)
  {
    long local_h = local_k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(local_k));
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
  
  public boolean contains(Object local_k)
  {
    long local_h = local_k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(local_k));
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
    ObjectBigArrays.fill(this.key, null);
  }
  
  public ObjectIterator<K> iterator()
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
    long local_l = HashCommon.bigArraySize(this.size, this.field_73);
    if (local_l >= this.field_74) {
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
    long local_l = HashCommon.bigArraySize(local_n, this.field_73);
    if (this.field_74 <= local_l) {
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
    K[][] key = this.key;
    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
    K[][] newKey = (Object[][])ObjectBigArrays.newBigArray(newN);
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
      K local_k = key[base][displ];
      long local_h = local_k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(local_k));
      int local_d = (int)(local_h & newSegmentMask);
      for (int local_b = (int)((local_h & newMask) >>> 27); newUsed[local_b][local_d] != 0; local_b = local_b + ((local_d = local_d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask) {}
      newUsed[local_b][local_d] = 1;
      newKey[local_b][local_d] = local_k;
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    this.field_74 = newN;
    this.key = newKey;
    this.used = newUsed;
    initMasks();
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
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
  
  public ReferenceOpenHashBigSet<K> clone()
  {
    ReferenceOpenHashBigSet<K> local_c;
    try
    {
      local_c = (ReferenceOpenHashBigSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ObjectBigArrays.copy(this.key);
    local_c.used = BooleanBigArrays.copy(this.used);
    return local_c;
  }
  
  public int hashCode()
  {
    boolean[][] used = this.used;
    K[][] key = this.key;
    int local_h = 0;
    int base = 0;
    int displ = 0;
    long local_j = this.size;
    while (local_j-- != 0L)
    {
      while (used[base][displ] == 0) {
        base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
      }
      if (this != key[base][displ]) {
        local_h += (key[base][displ] == null ? 0 : System.identityHashCode(key[base][displ]));
      }
      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    ObjectIterator<K> local_i = iterator();
    local_s.defaultWriteObject();
    long local_j = this.size;
    while (local_j-- != 0L) {
      local_s.writeObject(local_i.next());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_74 = HashCommon.bigArraySize(this.size, this.field_73);
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
    K[][] key = this.key = (Object[][])ObjectBigArrays.newBigArray(this.field_74);
    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.field_74);
    initMasks();
    long local_i = this.size;
    while (local_i-- != 0L)
    {
      K local_k = local_s.readObject();
      long local_h = local_k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(local_k));
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
    extends AbstractObjectIterator<K>
  {
    int base = ReferenceOpenHashBigSet.this.key.length;
    int displ;
    int lastBase = -1;
    int lastDispl;
    long field_333 = ReferenceOpenHashBigSet.this.size;
    ReferenceArrayList<K> wrapped;
    
    private SetIterator()
    {
      boolean[][] used = ReferenceOpenHashBigSet.this.used;
      if (this.field_333 != 0L) {
        do
        {
          if (this.displ-- == 0)
          {
            this.base -= 1;
            this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
          }
        } while (used[this.base][this.displ] == 0);
      }
    }
    
    public boolean hasNext()
    {
      return this.field_333 != 0L;
    }
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_333 -= 1L;
      if (this.base < 0) {
        return this.wrapped.get(-(this.lastBase = --this.base) - 2);
      }
      K retVal = ReferenceOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
      if (this.field_333 != 0L)
      {
        boolean[][] used = ReferenceOpenHashBigSet.this.used;
        do
        {
          if (this.displ-- == 0)
          {
            if (this.base-- == 0) {
              break;
            }
            this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
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
        for (pos = (last = pos) + 1L & ReferenceOpenHashBigSet.this.mask; BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos); pos = pos + 1L & ReferenceOpenHashBigSet.this.mask)
        {
          long slot = (ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos)))) & ReferenceOpenHashBigSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (!BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos)) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ReferenceArrayList();
          }
          this.wrapped.add(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
        }
        ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
      }
      BooleanBigArrays.set(ReferenceOpenHashBigSet.this.used, last, false);
      ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, null);
      return last;
    }
    
    public void remove()
    {
      if (this.lastBase == -1) {
        throw new IllegalStateException();
      }
      if (this.base < -1)
      {
        ReferenceOpenHashBigSet.this.remove(this.wrapped.set(-this.base - 2, null));
        this.lastBase = -1;
        return;
      }
      ReferenceOpenHashBigSet.this.size -= 1L;
      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.field_333 > 0L))
      {
        this.field_333 += 1L;
        next();
      }
      this.lastBase = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */