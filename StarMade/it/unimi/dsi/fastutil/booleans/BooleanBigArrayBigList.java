package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class BooleanBigArrayBigList
  extends AbstractBooleanBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient boolean[][] field_384;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected BooleanBigArrayBigList(boolean[][] local_a, boolean dummy)
  {
    this.field_384 = local_a;
  }
  
  public BooleanBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_384 = BooleanBigArrays.newBigArray(capacity);
  }
  
  public BooleanBigArrayBigList()
  {
    this(16L);
  }
  
  public BooleanBigArrayBigList(BooleanCollection local_c)
  {
    this(local_c.size());
    BooleanIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextBoolean());
    }
  }
  
  public BooleanBigArrayBigList(BooleanBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_384, 0L, this.size = local_l.size64());
  }
  
  public BooleanBigArrayBigList(boolean[][] local_a)
  {
    this(local_a, 0L, BooleanBigArrays.length(local_a));
  }
  
  public BooleanBigArrayBigList(boolean[][] local_a, long offset, long length)
  {
    this(length);
    BooleanBigArrays.copy(local_a, offset, this.field_384, 0L, length);
    this.size = length;
  }
  
  public BooleanBigArrayBigList(Iterator<? extends Boolean> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Boolean)local_i.next());
    }
  }
  
  public BooleanBigArrayBigList(BooleanIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextBoolean());
    }
  }
  
  public boolean[][] elements()
  {
    return this.field_384;
  }
  
  public static BooleanBigArrayBigList wrap(boolean[][] local_a, long length)
  {
    if (length > BooleanBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + BooleanBigArrays.length(local_a) + ")");
    }
    BooleanBigArrayBigList local_l = new BooleanBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static BooleanBigArrayBigList wrap(boolean[][] local_a)
  {
    return wrap(local_a, BooleanBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_384 = BooleanBigArrays.ensureCapacity(this.field_384, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_384 = BooleanBigArrays.grow(this.field_384, capacity, this.size);
  }
  
  public void add(long index, boolean local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      BooleanBigArrays.copy(this.field_384, index, this.field_384, index + 1L, this.size - index);
    }
    BooleanBigArrays.set(this.field_384, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(boolean local_k)
  {
    grow(this.size + 1L);
    BooleanBigArrays.set(this.field_384, this.size++, local_k);
    return true;
  }
  
  public boolean getBoolean(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return BooleanBigArrays.get(this.field_384, index);
  }
  
  public long indexOf(boolean local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == BooleanBigArrays.get(this.field_384, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(boolean local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == BooleanBigArrays.get(this.field_384, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public boolean removeBoolean(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    boolean old = BooleanBigArrays.get(this.field_384, index);
    this.size -= 1L;
    if (index != this.size) {
      BooleanBigArrays.copy(this.field_384, index + 1L, this.field_384, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(boolean local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeBoolean(index);
    return true;
  }
  
  public boolean set(long index, boolean local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    boolean old = BooleanBigArrays.get(this.field_384, index);
    BooleanBigArrays.set(this.field_384, index, local_k);
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
    if (size > BooleanBigArrays.length(this.field_384)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      BooleanBigArrays.fill(this.field_384, this.size, size, false);
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
    long arrayLength = BooleanBigArrays.length(this.field_384);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_384 = BooleanBigArrays.trim(this.field_384, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, boolean[][] local_a, long offset, long length)
  {
    BooleanBigArrays.copy(this.field_384, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    BooleanBigArrays.copy(this.field_384, local_to, this.field_384, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, boolean[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    BooleanBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    BooleanBigArrays.copy(this.field_384, index, this.field_384, index + length, this.size - index);
    BooleanBigArrays.copy(local_a, offset, this.field_384, index, length);
    this.size += length;
  }
  
  public BooleanBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractBooleanBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < BooleanBigArrayBigList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public boolean nextBoolean()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return BooleanBigArrays.get(BooleanBigArrayBigList.this.field_384, this.last = this.pos++);
      }
      
      public boolean previousBoolean()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return BooleanBigArrays.get(BooleanBigArrayBigList.this.field_384, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanBigArrayBigList.this.removeBoolean(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public BooleanBigArrayBigList clone()
  {
    BooleanBigArrayBigList local_c = new BooleanBigArrayBigList(this.size);
    BooleanBigArrays.copy(this.field_384, 0L, local_c.field_384, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(BooleanBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    boolean[][] local_a1 = this.field_384;
    boolean[][] local_a2 = local_l.field_384;
    while (local_s-- != 0L) {
      if (BooleanBigArrays.get(local_a1, local_s) != BooleanBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(BooleanBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    boolean[][] local_a1 = this.field_384;
    boolean[][] local_a2 = local_l.field_384;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      boolean local_e1 = BooleanBigArrays.get(local_a1, local_i);
      boolean local_e2 = BooleanBigArrays.get(local_a2, local_i);
      int local_r;
      if ((local_r = local_e1 == local_e2 ? 0 : (!local_e1) && (local_e2) ? -1 : 1) != 0) {
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
      local_s.writeBoolean(BooleanBigArrays.get(this.field_384, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_384 = BooleanBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      BooleanBigArrays.set(this.field_384, local_i, local_s.readBoolean());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */