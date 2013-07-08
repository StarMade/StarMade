package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class LongArrayList
  extends AbstractLongList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient long[] field_81;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected LongArrayList(long[] local_a, boolean dummy)
  {
    this.field_81 = local_a;
  }
  
  public LongArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_81 = new long[capacity];
  }
  
  public LongArrayList()
  {
    this(16);
  }
  
  public LongArrayList(Collection<? extends Long> local_c)
  {
    this(local_c.size());
    this.size = LongIterators.unwrap(LongIterators.asLongIterator(local_c.iterator()), this.field_81);
  }
  
  public LongArrayList(LongCollection local_c)
  {
    this(local_c.size());
    this.size = LongIterators.unwrap(local_c.iterator(), this.field_81);
  }
  
  public LongArrayList(LongList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_81, 0, this.size = local_l.size());
  }
  
  public LongArrayList(long[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public LongArrayList(long[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_81, 0, length);
    this.size = length;
  }
  
  public LongArrayList(Iterator<? extends Long> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Long)local_i.next());
    }
  }
  
  public LongArrayList(LongIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextLong());
    }
  }
  
  public long[] elements()
  {
    return this.field_81;
  }
  
  public static LongArrayList wrap(long[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    LongArrayList local_l = new LongArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static LongArrayList wrap(long[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_81 = LongArrays.ensureCapacity(this.field_81, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_81 = LongArrays.grow(this.field_81, capacity, this.size);
  }
  
  public void add(int index, long local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_81, index, this.field_81, index + 1, this.size - index);
    }
    this.field_81[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(long local_k)
  {
    grow(this.size + 1);
    this.field_81[(this.size++)] = local_k;
    return true;
  }
  
  public long getLong(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_81[index];
  }
  
  public int indexOf(long local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_81[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(long local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_81[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public long removeLong(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    long old = this.field_81[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_81, index + 1, this.field_81, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(long local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeLong(index);
    return true;
  }
  
  public long set(int index, long local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    long old = this.field_81[index];
    this.field_81[index] = local_k;
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
    if (size > this.field_81.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      LongArrays.fill(this.field_81, this.size, size, 0L);
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
    if ((local_n >= this.field_81.length) || (this.size == this.field_81.length)) {
      return;
    }
    long[] local_t = new long[Math.max(local_n, this.size)];
    System.arraycopy(this.field_81, 0, local_t, 0, this.size);
    this.field_81 = local_t;
  }
  
  public void getElements(int from, long[] local_a, int offset, int length)
  {
    LongArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_81, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_81, local_to, this.field_81, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, long[] local_a, int offset, int length)
  {
    ensureIndex(index);
    LongArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_81, index, this.field_81, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_81, index, length);
    this.size += length;
  }
  
  public long[] toLongArray(long[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new long[this.size];
    }
    System.arraycopy(this.field_81, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, LongCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_81, index, this.field_81, index + local_n, this.size - index);
    }
    LongIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_81[(index++)] = local_i.nextLong();
    }
    return true;
  }
  
  public boolean addAll(int index, LongList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_81, index, this.field_81, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_81, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public LongListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractLongListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < LongArrayList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public long nextLong()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return LongArrayList.this.field_81[(this.last = this.pos++)];
      }
      
      public long previousLong()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return LongArrayList.this.field_81[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongArrayList.this.removeLong(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public LongArrayList clone()
  {
    LongArrayList local_c = new LongArrayList(this.size);
    System.arraycopy(this.field_81, 0, local_c.field_81, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(LongArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    long[] local_a1 = this.field_81;
    long[] local_a2 = local_l.field_81;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(LongArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    long[] local_a1 = this.field_81;
    long[] local_a2 = local_l.field_81;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      long local_e1 = local_a1[local_i];
      long local_e2 = local_a2[local_i];
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
      local_s.writeLong(this.field_81[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_81 = new long[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_81[local_i] = local_s.readLong();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */