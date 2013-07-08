package com.bulletphysics.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractList;
import java.util.RandomAccess;

public final class ObjectArrayList<T>
  extends AbstractList<T>
  implements RandomAccess, Externalizable
{
  private T[] array;
  private int size;
  
  public ObjectArrayList()
  {
    this(16);
  }
  
  public ObjectArrayList(int initialCapacity)
  {
    this.array = ((Object[])new Object[initialCapacity]);
  }
  
  public boolean add(T value)
  {
    if (this.size == this.array.length) {
      expand();
    }
    this.array[(this.size++)] = value;
    return true;
  }
  
  public void add(int index, T value)
  {
    if (this.size == this.array.length) {
      expand();
    }
    int num = this.size - index;
    if (num > 0) {
      System.arraycopy(this.array, index, this.array, index + 1, num);
    }
    this.array[index] = value;
    this.size += 1;
  }
  
  public T remove(int index)
  {
    if ((index < 0) || (index >= this.size)) {
      throw new IndexOutOfBoundsException();
    }
    T prev = this.array[index];
    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
    this.array[(this.size - 1)] = null;
    this.size -= 1;
    return prev;
  }
  
  private void expand()
  {
    T[] newArray = (Object[])new Object[this.array.length << 1];
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }
  
  public void removeQuick(int index)
  {
    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
    this.array[(this.size - 1)] = null;
    this.size -= 1;
  }
  
  public T get(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.array[index];
  }
  
  public T getQuick(int index)
  {
    return this.array[index];
  }
  
  public T set(int index, T value)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    T old = this.array[index];
    this.array[index] = value;
    return old;
  }
  
  public void setQuick(int index, T value)
  {
    this.array[index] = value;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public int capacity()
  {
    return this.array.length;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public int indexOf(Object local_o)
  {
    int _size = this.size;
    T[] _array = this.array;
    for (int local_i = 0; local_i < _size; local_i++) {
      if (local_o == null ? _array[local_i] == null : local_o.equals(_array[local_i])) {
        return local_i;
      }
    }
    return -1;
  }
  
  public void writeExternal(ObjectOutput out)
    throws IOException
  {
    out.writeInt(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      out.writeObject(this.array[local_i]);
    }
  }
  
  public void readExternal(ObjectInput local_in)
    throws IOException, ClassNotFoundException
  {
    this.size = local_in.readInt();
    int cap = 16;
    while (cap < this.size) {
      cap <<= 1;
    }
    this.array = ((Object[])new Object[cap]);
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.array[local_i] = local_in.readObject();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.ObjectArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */