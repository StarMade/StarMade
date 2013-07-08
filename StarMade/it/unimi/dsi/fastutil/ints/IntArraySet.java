package it.unimi.dsi.fastutil.ints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class IntArraySet
  extends AbstractIntSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient int[] field_382;
  private int size;
  
  public IntArraySet(int[] local_a)
  {
    this.field_382 = local_a;
    this.size = local_a.length;
  }
  
  public IntArraySet()
  {
    this.field_382 = IntArrays.EMPTY_ARRAY;
  }
  
  public IntArraySet(int capacity)
  {
    this.field_382 = new int[capacity];
  }
  
  public IntArraySet(IntCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public IntArraySet(Collection<? extends Integer> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public IntArraySet(int[] local_a, int size)
  {
    this.field_382 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(int local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_382[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public IntIterator iterator()
  {
    return IntIterators.wrap(this.field_382, 0, this.size);
  }
  
  public boolean contains(int local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(int local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_382[(pos + local_i)] = this.field_382[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(int local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_382.length)
    {
      int[] local_b = new int[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_382[local_i];
      }
      this.field_382 = local_b;
    }
    this.field_382[(this.size++)] = local_k;
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
  
  public IntArraySet clone()
  {
    IntArraySet local_c;
    try
    {
      local_c = (IntArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_382 = ((int[])this.field_382.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeInt(this.field_382[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_382 = new int[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_382[local_i] = local_s.readInt();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */