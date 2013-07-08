package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class LongBigArrayBigList
  extends AbstractLongBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient long[][] field_81;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected LongBigArrayBigList(long[][] local_a, boolean dummy)
  {
    this.field_81 = local_a;
  }
  
  public LongBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_81 = LongBigArrays.newBigArray(capacity);
  }
  
  public LongBigArrayBigList()
  {
    this(16L);
  }
  
  public LongBigArrayBigList(LongCollection local_c)
  {
    this(local_c.size());
    LongIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextLong());
    }
  }
  
  public LongBigArrayBigList(LongBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_81, 0L, this.size = local_l.size64());
  }
  
  public LongBigArrayBigList(long[][] local_a)
  {
    this(local_a, 0L, LongBigArrays.length(local_a));
  }
  
  public LongBigArrayBigList(long[][] local_a, long offset, long length)
  {
    this(length);
    LongBigArrays.copy(local_a, offset, this.field_81, 0L, length);
    this.size = length;
  }
  
  public LongBigArrayBigList(Iterator<? extends Long> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Long)local_i.next());
    }
  }
  
  public LongBigArrayBigList(LongIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextLong());
    }
  }
  
  public long[][] elements()
  {
    return this.field_81;
  }
  
  public static LongBigArrayBigList wrap(long[][] local_a, long length)
  {
    if (length > LongBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + LongBigArrays.length(local_a) + ")");
    }
    LongBigArrayBigList local_l = new LongBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static LongBigArrayBigList wrap(long[][] local_a)
  {
    return wrap(local_a, LongBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_81 = LongBigArrays.ensureCapacity(this.field_81, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_81 = LongBigArrays.grow(this.field_81, capacity, this.size);
  }
  
  public void add(long index, long local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      LongBigArrays.copy(this.field_81, index, this.field_81, index + 1L, this.size - index);
    }
    LongBigArrays.set(this.field_81, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(long local_k)
  {
    grow(this.size + 1L);
    LongBigArrays.set(this.field_81, this.size++, local_k);
    return true;
  }
  
  public long getLong(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return LongBigArrays.get(this.field_81, index);
  }
  
  public long indexOf(long local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == LongBigArrays.get(this.field_81, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(long local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == LongBigArrays.get(this.field_81, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long removeLong(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    long old = LongBigArrays.get(this.field_81, index);
    this.size -= 1L;
    if (index != this.size) {
      LongBigArrays.copy(this.field_81, index + 1L, this.field_81, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(long local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeLong(index);
    return true;
  }
  
  public long set(long index, long local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    long old = LongBigArrays.get(this.field_81, index);
    LongBigArrays.set(this.field_81, index, local_k);
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
    if (size > LongBigArrays.length(this.field_81)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      LongBigArrays.fill(this.field_81, this.size, size, 0L);
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
    long arrayLength = LongBigArrays.length(this.field_81);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_81 = LongBigArrays.trim(this.field_81, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, long[][] local_a, long offset, long length)
  {
    LongBigArrays.copy(this.field_81, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    LongBigArrays.copy(this.field_81, local_to, this.field_81, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, long[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    LongBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    LongBigArrays.copy(this.field_81, index, this.field_81, index + length, this.size - index);
    LongBigArrays.copy(local_a, offset, this.field_81, index, length);
    this.size += length;
  }
  
  public LongBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractLongBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < LongBigArrayBigList.this.size;
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
        return LongBigArrays.get(LongBigArrayBigList.this.field_81, this.last = this.pos++);
      }
      
      public long previousLong()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return LongBigArrays.get(LongBigArrayBigList.this.field_81, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        LongBigArrayBigList.this.removeLong(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public LongBigArrayBigList clone()
  {
    LongBigArrayBigList local_c = new LongBigArrayBigList(this.size);
    LongBigArrays.copy(this.field_81, 0L, local_c.field_81, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(LongBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    long[][] local_a1 = this.field_81;
    long[][] local_a2 = local_l.field_81;
    while (local_s-- != 0L) {
      if (LongBigArrays.get(local_a1, local_s) != LongBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(LongBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    long[][] local_a1 = this.field_81;
    long[][] local_a2 = local_l.field_81;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      long local_e1 = LongBigArrays.get(local_a1, local_i);
      long local_e2 = LongBigArrays.get(local_a2, local_i);
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
      local_s.writeLong(LongBigArrays.get(this.field_81, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_81 = LongBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      LongBigArrays.set(this.field_81, local_i, local_s.readLong());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */