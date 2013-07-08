package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class FloatArrayList
  extends AbstractFloatList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient float[] field_205;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected FloatArrayList(float[] local_a, boolean dummy)
  {
    this.field_205 = local_a;
  }
  
  public FloatArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_205 = new float[capacity];
  }
  
  public FloatArrayList()
  {
    this(16);
  }
  
  public FloatArrayList(Collection<? extends Float> local_c)
  {
    this(local_c.size());
    this.size = FloatIterators.unwrap(FloatIterators.asFloatIterator(local_c.iterator()), this.field_205);
  }
  
  public FloatArrayList(FloatCollection local_c)
  {
    this(local_c.size());
    this.size = FloatIterators.unwrap(local_c.iterator(), this.field_205);
  }
  
  public FloatArrayList(FloatList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_205, 0, this.size = local_l.size());
  }
  
  public FloatArrayList(float[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public FloatArrayList(float[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_205, 0, length);
    this.size = length;
  }
  
  public FloatArrayList(Iterator<? extends Float> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Float)local_i.next());
    }
  }
  
  public FloatArrayList(FloatIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextFloat());
    }
  }
  
  public float[] elements()
  {
    return this.field_205;
  }
  
  public static FloatArrayList wrap(float[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    FloatArrayList local_l = new FloatArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static FloatArrayList wrap(float[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_205 = FloatArrays.ensureCapacity(this.field_205, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_205 = FloatArrays.grow(this.field_205, capacity, this.size);
  }
  
  public void add(int index, float local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_205, index, this.field_205, index + 1, this.size - index);
    }
    this.field_205[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(float local_k)
  {
    grow(this.size + 1);
    this.field_205[(this.size++)] = local_k;
    return true;
  }
  
  public float getFloat(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_205[index];
  }
  
  public int indexOf(float local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_205[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(float local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_205[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public float removeFloat(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    float old = this.field_205[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_205, index + 1, this.field_205, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(float local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeFloat(index);
    return true;
  }
  
  public float set(int index, float local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    float old = this.field_205[index];
    this.field_205[index] = local_k;
    return old;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void size(int size)
  {
    if (size > this.field_205.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      FloatArrays.fill(this.field_205, this.size, size, 0.0F);
    }
    this.size = size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public void trim()
  {
    trim(0);
  }
  
  public void trim(int local_n)
  {
    if ((local_n >= this.field_205.length) || (this.size == this.field_205.length)) {
      return;
    }
    float[] local_t = new float[Math.max(local_n, this.size)];
    System.arraycopy(this.field_205, 0, local_t, 0, this.size);
    this.field_205 = local_t;
  }
  
  public void getElements(int from, float[] local_a, int offset, int length)
  {
    FloatArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_205, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_205, local_to, this.field_205, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, float[] local_a, int offset, int length)
  {
    ensureIndex(index);
    FloatArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_205, index, this.field_205, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_205, index, length);
    this.size += length;
  }
  
  public float[] toFloatArray(float[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new float[this.size];
    }
    System.arraycopy(this.field_205, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, FloatCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_205, index, this.field_205, index + local_n, this.size - index);
    }
    FloatIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_205[(index++)] = local_i.nextFloat();
    }
    return true;
  }
  
  public boolean addAll(int index, FloatList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_205, index, this.field_205, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_205, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public FloatListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractFloatListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < FloatArrayList.this.size;
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
        return FloatArrayList.this.field_205[(this.last = this.pos++)];
      }
      
      public float previousFloat()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return FloatArrayList.this.field_205[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        FloatArrayList.this.removeFloat(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public FloatArrayList clone()
  {
    FloatArrayList local_c = new FloatArrayList(this.size);
    System.arraycopy(this.field_205, 0, local_c.field_205, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(FloatArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    float[] local_a1 = this.field_205;
    float[] local_a2 = local_l.field_205;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(FloatArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    float[] local_a1 = this.field_205;
    float[] local_a2 = local_l.field_205;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      float local_e1 = local_a1[local_i];
      float local_e2 = local_a2[local_i];
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */