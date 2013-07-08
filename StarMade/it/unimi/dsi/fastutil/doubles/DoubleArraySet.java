package it.unimi.dsi.fastutil.doubles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class DoubleArraySet
  extends AbstractDoubleSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient double[] field_59;
  private int size;
  
  public DoubleArraySet(double[] local_a)
  {
    this.field_59 = local_a;
    this.size = local_a.length;
  }
  
  public DoubleArraySet()
  {
    this.field_59 = DoubleArrays.EMPTY_ARRAY;
  }
  
  public DoubleArraySet(int capacity)
  {
    this.field_59 = new double[capacity];
  }
  
  public DoubleArraySet(DoubleCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public DoubleArraySet(Collection<? extends Double> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public DoubleArraySet(double[] local_a, int size)
  {
    this.field_59 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(double local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_59[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public DoubleIterator iterator()
  {
    return DoubleIterators.wrap(this.field_59, 0, this.size);
  }
  
  public boolean contains(double local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(double local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_59[(pos + local_i)] = this.field_59[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(double local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_59.length)
    {
      double[] local_b = new double[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_59[local_i];
      }
      this.field_59 = local_b;
    }
    this.field_59[(this.size++)] = local_k;
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
  
  public DoubleArraySet clone()
  {
    DoubleArraySet local_c;
    try
    {
      local_c = (DoubleArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_59 = ((double[])this.field_59.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeDouble(this.field_59[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_59 = new double[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_59[local_i] = local_s.readDouble();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */