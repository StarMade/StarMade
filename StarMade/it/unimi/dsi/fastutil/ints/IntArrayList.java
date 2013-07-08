package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class IntArrayList
  extends AbstractIntList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient int[] field_382;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected IntArrayList(int[] local_a, boolean dummy)
  {
    this.field_382 = local_a;
  }
  
  public IntArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_382 = new int[capacity];
  }
  
  public IntArrayList()
  {
    this(16);
  }
  
  public IntArrayList(Collection<? extends Integer> local_c)
  {
    this(local_c.size());
    this.size = IntIterators.unwrap(IntIterators.asIntIterator(local_c.iterator()), this.field_382);
  }
  
  public IntArrayList(IntCollection local_c)
  {
    this(local_c.size());
    this.size = IntIterators.unwrap(local_c.iterator(), this.field_382);
  }
  
  public IntArrayList(IntList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_382, 0, this.size = local_l.size());
  }
  
  public IntArrayList(int[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public IntArrayList(int[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_382, 0, length);
    this.size = length;
  }
  
  public IntArrayList(Iterator<? extends Integer> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Integer)local_i.next());
    }
  }
  
  public IntArrayList(IntIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextInt());
    }
  }
  
  public int[] elements()
  {
    return this.field_382;
  }
  
  public static IntArrayList wrap(int[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    IntArrayList local_l = new IntArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static IntArrayList wrap(int[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_382 = IntArrays.ensureCapacity(this.field_382, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_382 = IntArrays.grow(this.field_382, capacity, this.size);
  }
  
  public void add(int index, int local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_382, index, this.field_382, index + 1, this.size - index);
    }
    this.field_382[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(int local_k)
  {
    grow(this.size + 1);
    this.field_382[(this.size++)] = local_k;
    return true;
  }
  
  public int getInt(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_382[index];
  }
  
  public int indexOf(int local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_382[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(int local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_382[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int removeInt(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    int old = this.field_382[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_382, index + 1, this.field_382, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(int local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeInt(index);
    return true;
  }
  
  public int set(int index, int local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    int old = this.field_382[index];
    this.field_382[index] = local_k;
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
    if (size > this.field_382.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      IntArrays.fill(this.field_382, this.size, size, 0);
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
    if ((local_n >= this.field_382.length) || (this.size == this.field_382.length)) {
      return;
    }
    int[] local_t = new int[Math.max(local_n, this.size)];
    System.arraycopy(this.field_382, 0, local_t, 0, this.size);
    this.field_382 = local_t;
  }
  
  public void getElements(int from, int[] local_a, int offset, int length)
  {
    IntArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_382, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_382, local_to, this.field_382, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, int[] local_a, int offset, int length)
  {
    ensureIndex(index);
    IntArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_382, index, this.field_382, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_382, index, length);
    this.size += length;
  }
  
  public int[] toIntArray(int[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new int[this.size];
    }
    System.arraycopy(this.field_382, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, IntCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_382, index, this.field_382, index + local_n, this.size - index);
    }
    IntIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_382[(index++)] = local_i.nextInt();
    }
    return true;
  }
  
  public boolean addAll(int index, IntList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_382, index, this.field_382, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_382, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public IntListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractIntListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < IntArrayList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public int nextInt()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return IntArrayList.this.field_382[(this.last = this.pos++)];
      }
      
      public int previousInt()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return IntArrayList.this.field_382[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntArrayList.this.removeInt(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public IntArrayList clone()
  {
    IntArrayList local_c = new IntArrayList(this.size);
    System.arraycopy(this.field_382, 0, local_c.field_382, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(IntArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    int[] local_a1 = this.field_382;
    int[] local_a2 = local_l.field_382;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(IntArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    int[] local_a1 = this.field_382;
    int[] local_a2 = local_l.field_382;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      int local_e1 = local_a1[local_i];
      int local_e2 = local_a2[local_i];
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
      local_s.writeInt(this.field_382[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_382 = new int[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_382[local_i] = local_s.readInt();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */