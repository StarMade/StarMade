package it.unimi.dsi.fastutil.booleans;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class BooleanArraySet
  extends AbstractBooleanSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient boolean[] field_384;
  private int size;
  
  public BooleanArraySet(boolean[] local_a)
  {
    this.field_384 = local_a;
    this.size = local_a.length;
  }
  
  public BooleanArraySet()
  {
    this.field_384 = BooleanArrays.EMPTY_ARRAY;
  }
  
  public BooleanArraySet(int capacity)
  {
    this.field_384 = new boolean[capacity];
  }
  
  public BooleanArraySet(BooleanCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public BooleanArraySet(Collection<? extends Boolean> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public BooleanArraySet(boolean[] local_a, int size)
  {
    this.field_384 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(boolean local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_384[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public BooleanIterator iterator()
  {
    return BooleanIterators.wrap(this.field_384, 0, this.size);
  }
  
  public boolean contains(boolean local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(boolean local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_384[(pos + local_i)] = this.field_384[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(boolean local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_384.length)
    {
      boolean[] local_b = new boolean[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_384[local_i];
      }
      this.field_384 = local_b;
    }
    this.field_384[(this.size++)] = local_k;
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
  
  public BooleanArraySet clone()
  {
    BooleanArraySet local_c;
    try
    {
      local_c = (BooleanArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_384 = ((boolean[])this.field_384.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeBoolean(this.field_384[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_384 = new boolean[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_384[local_i] = local_s.readBoolean();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */