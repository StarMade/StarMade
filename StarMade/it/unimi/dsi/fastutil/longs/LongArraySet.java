package it.unimi.dsi.fastutil.longs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class LongArraySet
  extends AbstractLongSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient long[] field_81;
  private int size;
  
  public LongArraySet(long[] local_a)
  {
    this.field_81 = local_a;
    this.size = local_a.length;
  }
  
  public LongArraySet()
  {
    this.field_81 = LongArrays.EMPTY_ARRAY;
  }
  
  public LongArraySet(int capacity)
  {
    this.field_81 = new long[capacity];
  }
  
  public LongArraySet(LongCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public LongArraySet(Collection<? extends Long> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public LongArraySet(long[] local_a, int size)
  {
    this.field_81 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(long local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_81[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public LongIterator iterator()
  {
    return LongIterators.wrap(this.field_81, 0, this.size);
  }
  
  public boolean contains(long local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(long local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_81[(pos + local_i)] = this.field_81[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(long local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_81.length)
    {
      long[] local_b = new long[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_81[local_i];
      }
      this.field_81 = local_b;
    }
    this.field_81[(this.size++)] = local_k;
    return true;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public LongArraySet clone()
  {
    LongArraySet local_c;
    try
    {
      local_c = (LongArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_81 = ((long[])this.field_81.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeLong(this.field_81[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_81 = new long[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_81[local_i] = local_s.readLong();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */