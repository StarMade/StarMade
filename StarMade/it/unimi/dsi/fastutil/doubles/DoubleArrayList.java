package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class DoubleArrayList
  extends AbstractDoubleList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient double[] field_59;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected DoubleArrayList(double[] local_a, boolean dummy)
  {
    this.field_59 = local_a;
  }
  
  public DoubleArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_59 = new double[capacity];
  }
  
  public DoubleArrayList()
  {
    this(16);
  }
  
  public DoubleArrayList(Collection<? extends Double> local_c)
  {
    this(local_c.size());
    this.size = DoubleIterators.unwrap(DoubleIterators.asDoubleIterator(local_c.iterator()), this.field_59);
  }
  
  public DoubleArrayList(DoubleCollection local_c)
  {
    this(local_c.size());
    this.size = DoubleIterators.unwrap(local_c.iterator(), this.field_59);
  }
  
  public DoubleArrayList(DoubleList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_59, 0, this.size = local_l.size());
  }
  
  public DoubleArrayList(double[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public DoubleArrayList(double[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_59, 0, length);
    this.size = length;
  }
  
  public DoubleArrayList(Iterator<? extends Double> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Double)local_i.next());
    }
  }
  
  public DoubleArrayList(DoubleIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextDouble());
    }
  }
  
  public double[] elements()
  {
    return this.field_59;
  }
  
  public static DoubleArrayList wrap(double[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    DoubleArrayList local_l = new DoubleArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static DoubleArrayList wrap(double[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_59 = DoubleArrays.ensureCapacity(this.field_59, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_59 = DoubleArrays.grow(this.field_59, capacity, this.size);
  }
  
  public void add(int index, double local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_59, index, this.field_59, index + 1, this.size - index);
    }
    this.field_59[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(double local_k)
  {
    grow(this.size + 1);
    this.field_59[(this.size++)] = local_k;
    return true;
  }
  
  public double getDouble(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_59[index];
  }
  
  public int indexOf(double local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_59[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(double local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_59[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public double removeDouble(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    double old = this.field_59[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_59, index + 1, this.field_59, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(double local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeDouble(index);
    return true;
  }
  
  public double set(int index, double local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    double old = this.field_59[index];
    this.field_59[index] = local_k;
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
    if (size > this.field_59.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      DoubleArrays.fill(this.field_59, this.size, size, 0.0D);
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
    if ((local_n >= this.field_59.length) || (this.size == this.field_59.length)) {
      return;
    }
    double[] local_t = new double[Math.max(local_n, this.size)];
    System.arraycopy(this.field_59, 0, local_t, 0, this.size);
    this.field_59 = local_t;
  }
  
  public void getElements(int from, double[] local_a, int offset, int length)
  {
    DoubleArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_59, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_59, local_to, this.field_59, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, double[] local_a, int offset, int length)
  {
    ensureIndex(index);
    DoubleArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_59, index, this.field_59, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_59, index, length);
    this.size += length;
  }
  
  public double[] toDoubleArray(double[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new double[this.size];
    }
    System.arraycopy(this.field_59, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, DoubleCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_59, index, this.field_59, index + local_n, this.size - index);
    }
    DoubleIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_59[(index++)] = local_i.nextDouble();
    }
    return true;
  }
  
  public boolean addAll(int index, DoubleList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_59, index, this.field_59, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_59, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public DoubleListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractDoubleListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < DoubleArrayList.this.size;
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
        return DoubleArrayList.this.field_59[(this.last = this.pos++)];
      }
      
      public double previousDouble()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return DoubleArrayList.this.field_59[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        DoubleArrayList.this.removeDouble(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public DoubleArrayList clone()
  {
    DoubleArrayList local_c = new DoubleArrayList(this.size);
    System.arraycopy(this.field_59, 0, local_c.field_59, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(DoubleArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    double[] local_a1 = this.field_59;
    double[] local_a2 = local_l.field_59;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(DoubleArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    double[] local_a1 = this.field_59;
    double[] local_a2 = local_l.field_59;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      double local_e1 = local_a1[local_i];
      double local_e2 = local_a2[local_i];
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
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */