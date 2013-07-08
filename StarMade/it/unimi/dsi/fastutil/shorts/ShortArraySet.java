package it.unimi.dsi.fastutil.shorts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class ShortArraySet
  extends AbstractShortSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient short[] field_220;
  private int size;
  
  public ShortArraySet(short[] local_a)
  {
    this.field_220 = local_a;
    this.size = local_a.length;
  }
  
  public ShortArraySet()
  {
    this.field_220 = ShortArrays.EMPTY_ARRAY;
  }
  
  public ShortArraySet(int capacity)
  {
    this.field_220 = new short[capacity];
  }
  
  public ShortArraySet(ShortCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ShortArraySet(Collection<? extends Short> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ShortArraySet(short[] local_a, int size)
  {
    this.field_220 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(short local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_220[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public ShortIterator iterator()
  {
    return ShortIterators.wrap(this.field_220, 0, this.size);
  }
  
  public boolean contains(short local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(short local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_220[(pos + local_i)] = this.field_220[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(short local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_220.length)
    {
      short[] local_b = new short[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_220[local_i];
      }
      this.field_220 = local_b;
    }
    this.field_220[(this.size++)] = local_k;
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
  
  public ShortArraySet clone()
  {
    ShortArraySet local_c;
    try
    {
      local_c = (ShortArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_220 = ((short[])this.field_220.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeShort(this.field_220[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_220 = new short[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_220[local_i] = local_s.readShort();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */