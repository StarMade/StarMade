package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class IntBigArrayBigList
  extends AbstractIntBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient int[][] field_382;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected IntBigArrayBigList(int[][] local_a, boolean dummy)
  {
    this.field_382 = local_a;
  }
  
  public IntBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_382 = IntBigArrays.newBigArray(capacity);
  }
  
  public IntBigArrayBigList()
  {
    this(16L);
  }
  
  public IntBigArrayBigList(IntCollection local_c)
  {
    this(local_c.size());
    IntIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextInt());
    }
  }
  
  public IntBigArrayBigList(IntBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_382, 0L, this.size = local_l.size64());
  }
  
  public IntBigArrayBigList(int[][] local_a)
  {
    this(local_a, 0L, IntBigArrays.length(local_a));
  }
  
  public IntBigArrayBigList(int[][] local_a, long offset, long length)
  {
    this(length);
    IntBigArrays.copy(local_a, offset, this.field_382, 0L, length);
    this.size = length;
  }
  
  public IntBigArrayBigList(Iterator<? extends Integer> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Integer)local_i.next());
    }
  }
  
  public IntBigArrayBigList(IntIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextInt());
    }
  }
  
  public int[][] elements()
  {
    return this.field_382;
  }
  
  public static IntBigArrayBigList wrap(int[][] local_a, long length)
  {
    if (length > IntBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + IntBigArrays.length(local_a) + ")");
    }
    IntBigArrayBigList local_l = new IntBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static IntBigArrayBigList wrap(int[][] local_a)
  {
    return wrap(local_a, IntBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_382 = IntBigArrays.ensureCapacity(this.field_382, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_382 = IntBigArrays.grow(this.field_382, capacity, this.size);
  }
  
  public void add(long index, int local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      IntBigArrays.copy(this.field_382, index, this.field_382, index + 1L, this.size - index);
    }
    IntBigArrays.set(this.field_382, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(int local_k)
  {
    grow(this.size + 1L);
    IntBigArrays.set(this.field_382, this.size++, local_k);
    return true;
  }
  
  public int getInt(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return IntBigArrays.get(this.field_382, index);
  }
  
  public long indexOf(int local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == IntBigArrays.get(this.field_382, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(int local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == IntBigArrays.get(this.field_382, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public int removeInt(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    int old = IntBigArrays.get(this.field_382, index);
    this.size -= 1L;
    if (index != this.size) {
      IntBigArrays.copy(this.field_382, index + 1L, this.field_382, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(int local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeInt(index);
    return true;
  }
  
  public int set(long index, int local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    int old = IntBigArrays.get(this.field_382, index);
    IntBigArrays.set(this.field_382, index, local_k);
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
    if (size > IntBigArrays.length(this.field_382)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      IntBigArrays.fill(this.field_382, this.size, size, 0);
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
    long arrayLength = IntBigArrays.length(this.field_382);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_382 = IntBigArrays.trim(this.field_382, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, int[][] local_a, long offset, long length)
  {
    IntBigArrays.copy(this.field_382, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    IntBigArrays.copy(this.field_382, local_to, this.field_382, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, int[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    IntBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    IntBigArrays.copy(this.field_382, index, this.field_382, index + length, this.size - index);
    IntBigArrays.copy(local_a, offset, this.field_382, index, length);
    this.size += length;
  }
  
  public IntBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractIntBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < IntBigArrayBigList.this.size;
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
        return IntBigArrays.get(IntBigArrayBigList.this.field_382, this.last = this.pos++);
      }
      
      public int previousInt()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return IntBigArrays.get(IntBigArrayBigList.this.field_382, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        IntBigArrayBigList.this.removeInt(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public IntBigArrayBigList clone()
  {
    IntBigArrayBigList local_c = new IntBigArrayBigList(this.size);
    IntBigArrays.copy(this.field_382, 0L, local_c.field_382, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(IntBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    int[][] local_a1 = this.field_382;
    int[][] local_a2 = local_l.field_382;
    while (local_s-- != 0L) {
      if (IntBigArrays.get(local_a1, local_s) != IntBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(IntBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    int[][] local_a1 = this.field_382;
    int[][] local_a2 = local_l.field_382;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      int local_e1 = IntBigArrays.get(local_a1, local_i);
      int local_e2 = IntBigArrays.get(local_a2, local_i);
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
      local_s.writeInt(IntBigArrays.get(this.field_382, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_382 = IntBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      IntBigArrays.set(this.field_382, local_i, local_s.readInt());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */