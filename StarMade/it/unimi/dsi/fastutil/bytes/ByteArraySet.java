package it.unimi.dsi.fastutil.bytes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class ByteArraySet
  extends AbstractByteSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient byte[] field_338;
  private int size;
  
  public ByteArraySet(byte[] local_a)
  {
    this.field_338 = local_a;
    this.size = local_a.length;
  }
  
  public ByteArraySet()
  {
    this.field_338 = ByteArrays.EMPTY_ARRAY;
  }
  
  public ByteArraySet(int capacity)
  {
    this.field_338 = new byte[capacity];
  }
  
  public ByteArraySet(ByteCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ByteArraySet(Collection<? extends Byte> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ByteArraySet(byte[] local_a, int size)
  {
    this.field_338 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(byte local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_338[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public ByteIterator iterator()
  {
    return ByteIterators.wrap(this.field_338, 0, this.size);
  }
  
  public boolean contains(byte local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(byte local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_338[(pos + local_i)] = this.field_338[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(byte local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_338.length)
    {
      byte[] local_b = new byte[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_338[local_i];
      }
      this.field_338 = local_b;
    }
    this.field_338[(this.size++)] = local_k;
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
  
  public ByteArraySet clone()
  {
    ByteArraySet local_c;
    try
    {
      local_c = (ByteArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_338 = ((byte[])this.field_338.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeByte(this.field_338[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_338 = new byte[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_338[local_i] = local_s.readByte();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */