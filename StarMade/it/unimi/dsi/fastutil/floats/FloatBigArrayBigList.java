package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class FloatBigArrayBigList
  extends AbstractFloatBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient float[][] field_205;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected FloatBigArrayBigList(float[][] local_a, boolean dummy)
  {
    this.field_205 = local_a;
  }
  
  public FloatBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_205 = FloatBigArrays.newBigArray(capacity);
  }
  
  public FloatBigArrayBigList()
  {
    this(16L);
  }
  
  public FloatBigArrayBigList(FloatCollection local_c)
  {
    this(local_c.size());
    FloatIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public FloatBigArrayBigList(FloatBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_205, 0L, this.size = local_l.size64());
  }
  
  public FloatBigArrayBigList(float[][] local_a)
  {
    this(local_a, 0L, FloatBigArrays.length(local_a));
  }
  
  public FloatBigArrayBigList(float[][] local_a, long offset, long length)
  {
    this(length);
    FloatBigArrays.copy(local_a, offset, this.field_205, 0L, length);
    this.size = length;
  }
  
  public FloatBigArrayBigList(Iterator<? extends Float> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Float)local_i.next());
    }
  }
  
  public FloatBigArrayBigList(FloatIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public float[][] elements()
  {
    return this.field_205;
  }
  
  public static FloatBigArrayBigList wrap(float[][] local_a, long length)
  {
    if (length > FloatBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + FloatBigArrays.length(local_a) + ")");
    }
    FloatBigArrayBigList local_l = new FloatBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static FloatBigArrayBigList wrap(float[][] local_a)
  {
    return wrap(local_a, FloatBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_205 = FloatBigArrays.ensureCapacity(this.field_205, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_205 = FloatBigArrays.grow(this.field_205, capacity, this.size);
  }
  
  public void add(long index, float local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      FloatBigArrays.copy(this.field_205, index, this.field_205, index + 1L, this.size - index);
    }
    FloatBigArrays.set(this.field_205, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(float local_k)
  {
    grow(this.size + 1L);
    FloatBigArrays.set(this.field_205, this.size++, local_k);
    return true;
  }
  
  public float getFloat(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return FloatBigArrays.get(this.field_205, index);
  }
  
  public long indexOf(float local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == FloatBigArrays.get(this.field_205, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(float local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == FloatBigArrays.get(this.field_205, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public float removeFloat(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    float old = FloatBigArrays.get(this.field_205, index);
    this.size -= 1L;
    if (index != this.size) {
      FloatBigArrays.copy(this.field_205, index + 1L, this.field_205, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(float local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeFloat(index);
    return true;
  }
  
  public float set(long index, float local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    float old = FloatBigArrays.get(this.field_205, index);
    FloatBigArrays.set(this.field_205, index, local_k);
    return old;
  }
  
  public void clear()
  {
    this.size = 0L;
  }
  
  public long size64()
  {
    return this.size;
  }
  
  public void size(long size)
  {
    if (size > FloatBigArrays.length(this.field_205)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      FloatBigArrays.fill(this.field_205, this.size, size, 0.0F);
    }
    this.size = size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0L;
  }
  
  public void trim()
  {
    trim(0L);
  }
  
  public void trim(long local_n)
  {
    long arrayLength = FloatBigArrays.length(this.field_205);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_205 = FloatBigArrays.trim(this.field_205, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, float[][] local_a, long offset, long length)
  {
    FloatBigArrays.copy(this.field_205, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    FloatBigArrays.copy(this.field_205, local_to, this.field_205, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, float[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    FloatBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    FloatBigArrays.copy(this.field_205, index, this.field_205, index + length, this.size - index);
    FloatBigArrays.copy(local_a, offset, this.field_205, index, length);
    this.size += length;
  }
  
  public FloatBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractFloatBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < FloatBigArrayBigList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public float nextFloat()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return FloatBigArrays.get(FloatBigArrayBigList.this.field_205, this.last = this.pos++);
      }
      
      public float previousFloat()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return FloatBigArrays.get(FloatBigArrayBigList.this.field_205, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatBigArrayBigList.this.removeFloat(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public FloatBigArrayBigList clone()
  {
    FloatBigArrayBigList local_c = new FloatBigArrayBigList(this.size);
    FloatBigArrays.copy(this.field_205, 0L, local_c.field_205, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(FloatBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    float[][] local_a1 = this.field_205;
    float[][] local_a2 = local_l.field_205;
    while (local_s-- != 0L) {
      if (FloatBigArrays.get(local_a1, local_s) != FloatBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(FloatBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    float[][] local_a1 = this.field_205;
    float[][] local_a2 = local_l.field_205;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      float local_e1 = FloatBigArrays.get(local_a1, local_i);
      float local_e2 = FloatBigArrays.get(local_a2, local_i);
      int local_r;
      if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
        return local_r;
      }
    }
    return local_i < local_s1 ? 1 : local_i < local_s2 ? -1 : 0;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeFloat(FloatBigArrays.get(this.field_205, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_205 = FloatBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      FloatBigArrays.set(this.field_205, local_i, local_s.readFloat());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */