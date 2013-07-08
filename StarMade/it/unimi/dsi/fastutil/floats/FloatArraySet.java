package it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class FloatArraySet
  extends AbstractFloatSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient float[] field_205;
  private int size;
  
  public FloatArraySet(float[] local_a)
  {
    this.field_205 = local_a;
    this.size = local_a.length;
  }
  
  public FloatArraySet()
  {
    this.field_205 = FloatArrays.EMPTY_ARRAY;
  }
  
  public FloatArraySet(int capacity)
  {
    this.field_205 = new float[capacity];
  }
  
  public FloatArraySet(FloatCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public FloatArraySet(Collection<? extends Float> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public FloatArraySet(float[] local_a, int size)
  {
    this.field_205 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(float local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_205[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public FloatIterator iterator()
  {
    return FloatIterators.wrap(this.field_205, 0, this.size);
  }
  
  public boolean contains(float local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(float local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_205[(pos + local_i)] = this.field_205[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(float local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_205.length)
    {
      float[] local_b = new float[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_205[local_i];
      }
      this.field_205 = local_b;
    }
    this.field_205[(this.size++)] = local_k;
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
  
  public FloatArraySet clone()
  {
    FloatArraySet local_c;
    try
    {
      local_c = (FloatArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_205 = ((float[])this.field_205.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeFloat(this.field_205[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_205 = new float[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_205[local_i] = local_s.readFloat();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */