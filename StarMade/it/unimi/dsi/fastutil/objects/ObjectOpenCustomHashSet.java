package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.NoSuchElementException;

public class ObjectOpenCustomHashSet<K>
  extends AbstractObjectSet<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient boolean[] used;
  protected final float field_73;
  protected transient int field_74;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected Hash.Strategy<K> strategy;
  
  public ObjectOpenCustomHashSet(int expected, float local_f, Hash.Strategy<K> strategy)
  {
    this.strategy = strategy;
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_73 = local_f;
    this.field_74 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_74 - 1);
    this.maxFill = HashCommon.maxFill(this.field_74, local_f);
    this.key = ((Object[])new Object[this.field_74]);
    this.used = new boolean[this.field_74];
  }
  
  public ObjectOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(Hash.Strategy<K> strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(Collection<? extends K> local_c, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public ObjectOpenCustomHashSet(Collection<? extends K> local_c, Hash.Strategy<K> strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(ObjectCollection<? extends K> local_c, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_c.size(), local_f, strategy);
    addAll(local_c);
  }
  
  public ObjectOpenCustomHashSet(ObjectCollection<? extends K> local_c, Hash.Strategy<K> strategy)
  {
    this(local_c, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(ObjectIterator<K> local_i, float local_f, Hash.Strategy<K> strategy)
  {
    this(16, local_f, strategy);
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ObjectOpenCustomHashSet(ObjectIterator<K> local_i, Hash.Strategy<K> strategy)
  {
    this(local_i, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(K[] local_a, int offset, int length, float local_f, Hash.Strategy<K> strategy)
  {
    this(length < 0 ? 0 : length, local_f, strategy);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ObjectOpenCustomHashSet(K[] local_a, int offset, int length, Hash.Strategy<K> strategy)
  {
    this(local_a, offset, length, 0.75F, strategy);
  }
  
  public ObjectOpenCustomHashSet(K[] local_a, float local_f, Hash.Strategy<K> strategy)
  {
    this(local_a, 0, local_a.length, local_f, strategy);
  }
  
  public ObjectOpenCustomHashSet(K[] local_a, Hash.Strategy<K> strategy)
  {
    this(local_a, 0.75F, strategy);
  }
  
  public Hash.Strategy<K> strategy()
  {
    return this.strategy;
  }
  
  public boolean add(K local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return false;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_73));
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
    this.key[last] = null;
    return last;
  }
  
  public boolean remove(Object local_k)
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
  
  public boolean contains(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public K get(Object local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.key[pos];
      }
    }
    return null;
  }
  
  public void clear()
  {
    if (this.size == 0) {
      return;
    }
    this.size = 0;
    BooleanArrays.fill(this.used, false);
    ObjectArrays.fill(this.key, null);
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
    int local_l = HashCommon.arraySize(this.size, this.field_73);
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
  
  public boolean trim(int local_n)
  {
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_73));
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
  
  protected void rehash(int newN)
  {
    int local_i = 0;
    boolean[] used = this.used;
    K[] key = this.key;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      K local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      local_i++;
    }
    this.field_74 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
    this.key = newKey;
    this.used = newUsed;
  }
  
  public ObjectOpenCustomHashSet<K> clone()
  {
    ObjectOpenCustomHashSet<K> local_c;
    try
    {
      local_c = (ObjectOpenCustomHashSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((Object[])this.key.clone());
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
      if (this != this.key[local_i]) {
        local_h += this.strategy.hashCode(this.key[local_i]);
      }
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    ObjectIterator<K> local_i = iterator();
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0) {
      local_s.writeObject(local_i.next());
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_74 = HashCommon.arraySize(this.size, this.field_73);
    this.maxFill = HashCommon.maxFill(this.field_74, this.field_73);
    this.mask = (this.field_74 - 1);
    K[] key = this.key = (Object[])new Object[this.field_74];
    boolean[] used = this.used = new boolean[this.field_74];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
    }
  }
  
  private void checkTable() {}
  
  private class SetIterator
    extends AbstractObjectIterator<K>
  {
    int pos = ObjectOpenCustomHashSet.this.field_74;
    int last = -1;
    int field_333 = ObjectOpenCustomHashSet.this.size;
    ObjectArrayList<K> wrapped;
    
    private SetIterator()
    {
      boolean[] used = ObjectOpenCustomHashSet.this.used;
      while ((this.field_333 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_333 != 0;
    }
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_333 -= 1;
      if (this.pos < 0) {
        return this.wrapped.get(-(this.last = --this.pos) - 2);
      }
      K retVal = ObjectOpenCustomHashSet.this.key[(this.last = this.pos)];
      if (this.field_333 != 0)
      {
        boolean[] used = ObjectOpenCustomHashSet.this.used;
        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
      }
      return retVal;
    }
    
    final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & ObjectOpenCustomHashSet.this.mask; ObjectOpenCustomHashSet.this.used[pos] != 0; pos = pos + 1 & ObjectOpenCustomHashSet.this.mask)
        {
          int slot = HashCommon.murmurHash3(ObjectOpenCustomHashSet.this.strategy.hashCode(ObjectOpenCustomHashSet.this.key[pos])) & ObjectOpenCustomHashSet.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (ObjectOpenCustomHashSet.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ObjectArrayList();
          }
          this.wrapped.add(ObjectOpenCustomHashSet.this.key[pos]);
        }
        ObjectOpenCustomHashSet.this.key[last] = ObjectOpenCustomHashSet.this.key[pos];
      }
      ObjectOpenCustomHashSet.this.used[last] = false;
      ObjectOpenCustomHashSet.this.key[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        ObjectOpenCustomHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
        this.last = -1;
        return;
      }
      ObjectOpenCustomHashSet.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_333 > 0))
      {
        this.field_333 += 1;
        next();
      }
      this.last = -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */