package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class DoubleBigArrayBigList
  extends AbstractDoubleBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient double[][] field_59;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected DoubleBigArrayBigList(double[][] local_a, boolean dummy)
  {
    this.field_59 = local_a;
  }
  
  public DoubleBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_59 = DoubleBigArrays.newBigArray(capacity);
  }
  
  public DoubleBigArrayBigList()
  {
    this(16L);
  }
  
  public DoubleBigArrayBigList(DoubleCollection local_c)
  {
    this(local_c.size());
    DoubleIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextDouble());
    }
  }
  
  public DoubleBigArrayBigList(DoubleBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_59, 0L, this.size = local_l.size64());
  }
  
  public DoubleBigArrayBigList(double[][] local_a)
  {
    this(local_a, 0L, DoubleBigArrays.length(local_a));
  }
  
  public DoubleBigArrayBigList(double[][] local_a, long offset, long length)
  {
    this(length);
    DoubleBigArrays.copy(local_a, offset, this.field_59, 0L, length);
    this.size = length;
  }
  
  public DoubleBigArrayBigList(Iterator<? extends Double> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Double)local_i.next());
    }
  }
  
  public DoubleBigArrayBigList(DoubleIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextDouble());
    }
  }
  
  public double[][] elements()
  {
    return this.field_59;
  }
  
  public static DoubleBigArrayBigList wrap(double[][] local_a, long length)
  {
    if (length > DoubleBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + DoubleBigArrays.length(local_a) + ")");
    }
    DoubleBigArrayBigList local_l = new DoubleBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static DoubleBigArrayBigList wrap(double[][] local_a)
  {
    return wrap(local_a, DoubleBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_59 = DoubleBigArrays.ensureCapacity(this.field_59, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_59 = DoubleBigArrays.grow(this.field_59, capacity, this.size);
  }
  
  public void add(long index, double local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      DoubleBigArrays.copy(this.field_59, index, this.field_59, index + 1L, this.size - index);
    }
    DoubleBigArrays.set(this.field_59, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(double local_k)
  {
    grow(this.size + 1L);
    DoubleBigArrays.set(this.field_59, this.size++, local_k);
    return true;
  }
  
  public double getDouble(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return DoubleBigArrays.get(this.field_59, index);
  }
  
  public long indexOf(double local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == DoubleBigArrays.get(this.field_59, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(double local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == DoubleBigArrays.get(this.field_59, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public double removeDouble(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    double old = DoubleBigArrays.get(this.field_59, index);
    this.size -= 1L;
    if (index != this.size) {
      DoubleBigArrays.copy(this.field_59, index + 1L, this.field_59, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(double local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeDouble(index);
    return true;
  }
  
  public double set(long index, double local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    double old = DoubleBigArrays.get(this.field_59, index);
    DoubleBigArrays.set(this.field_59, index, local_k);
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
    if (size > DoubleBigArrays.length(this.field_59)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      DoubleBigArrays.fill(this.field_59, this.size, size, 0.0D);
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
    long arrayLength = DoubleBigArrays.length(this.field_59);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_59 = DoubleBigArrays.trim(this.field_59, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, double[][] local_a, long offset, long length)
  {
    DoubleBigArrays.copy(this.field_59, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    DoubleBigArrays.copy(this.field_59, local_to, this.field_59, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, double[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    DoubleBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    DoubleBigArrays.copy(this.field_59, index, this.field_59, index + length, this.size - index);
    DoubleBigArrays.copy(local_a, offset, this.field_59, index, length);
    this.size += length;
  }
  
  public DoubleBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractDoubleBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < DoubleBigArrayBigList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public double nextDouble()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return DoubleBigArrays.get(DoubleBigArrayBigList.this.field_59, this.last = this.pos++);
      }
      
      public double previousDouble()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return DoubleBigArrays.get(DoubleBigArrayBigList.this.field_59, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleBigArrayBigList.this.removeDouble(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public DoubleBigArrayBigList clone()
  {
    DoubleBigArrayBigList local_c = new DoubleBigArrayBigList(this.size);
    DoubleBigArrays.copy(this.field_59, 0L, local_c.field_59, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(DoubleBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    double[][] local_a1 = this.field_59;
    double[][] local_a2 = local_l.field_59;
    while (local_s-- != 0L) {
      if (DoubleBigArrays.get(local_a1, local_s) != DoubleBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(DoubleBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    double[][] local_a1 = this.field_59;
    double[][] local_a2 = local_l.field_59;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      double local_e1 = DoubleBigArrays.get(local_a1, local_i);
      double local_e2 = DoubleBigArrays.get(local_a2, local_i);
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
      local_s.writeDouble(DoubleBigArrays.get(this.field_59, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_59 = DoubleBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      DoubleBigArrays.set(this.field_59, local_i, local_s.readDouble());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */