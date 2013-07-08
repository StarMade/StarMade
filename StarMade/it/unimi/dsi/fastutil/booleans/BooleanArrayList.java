package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class BooleanArrayList
  extends AbstractBooleanList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient boolean[] field_384;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected BooleanArrayList(boolean[] local_a, boolean dummy)
  {
    this.field_384 = local_a;
  }
  
  public BooleanArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_384 = new boolean[capacity];
  }
  
  public BooleanArrayList()
  {
    this(16);
  }
  
  public BooleanArrayList(Collection<? extends Boolean> local_c)
  {
    this(local_c.size());
    this.size = BooleanIterators.unwrap(BooleanIterators.asBooleanIterator(local_c.iterator()), this.field_384);
  }
  
  public BooleanArrayList(BooleanCollection local_c)
  {
    this(local_c.size());
    this.size = BooleanIterators.unwrap(local_c.iterator(), this.field_384);
  }
  
  public BooleanArrayList(BooleanList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_384, 0, this.size = local_l.size());
  }
  
  public BooleanArrayList(boolean[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public BooleanArrayList(boolean[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_384, 0, length);
    this.size = length;
  }
  
  public BooleanArrayList(Iterator<? extends Boolean> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Boolean)local_i.next());
    }
  }
  
  public BooleanArrayList(BooleanIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextBoolean());
    }
  }
  
  public boolean[] elements()
  {
    return this.field_384;
  }
  
  public static BooleanArrayList wrap(boolean[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    BooleanArrayList local_l = new BooleanArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static BooleanArrayList wrap(boolean[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_384 = BooleanArrays.ensureCapacity(this.field_384, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_384 = BooleanArrays.grow(this.field_384, capacity, this.size);
  }
  
  public void add(int index, boolean local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_384, index, this.field_384, index + 1, this.size - index);
    }
    this.field_384[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(boolean local_k)
  {
    grow(this.size + 1);
    this.field_384[(this.size++)] = local_k;
    return true;
  }
  
  public boolean getBoolean(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_384[index];
  }
  
  public int indexOf(boolean local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_384[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(boolean local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_384[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public boolean removeBoolean(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    boolean old = this.field_384[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_384, index + 1, this.field_384, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(boolean local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeBoolean(index);
    return true;
  }
  
  public boolean set(int index, boolean local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    boolean old = this.field_384[index];
    this.field_384[index] = local_k;
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
    if (size > this.field_384.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      BooleanArrays.fill(this.field_384, this.size, size, false);
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
    if ((local_n >= this.field_384.length) || (this.size == this.field_384.length)) {
      return;
    }
    boolean[] local_t = new boolean[Math.max(local_n, this.size)];
    System.arraycopy(this.field_384, 0, local_t, 0, this.size);
    this.field_384 = local_t;
  }
  
  public void getElements(int from, boolean[] local_a, int offset, int length)
  {
    BooleanArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_384, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_384, local_to, this.field_384, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, boolean[] local_a, int offset, int length)
  {
    ensureIndex(index);
    BooleanArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_384, index, this.field_384, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_384, index, length);
    this.size += length;
  }
  
  public boolean[] toBooleanArray(boolean[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new boolean[this.size];
    }
    System.arraycopy(this.field_384, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, BooleanCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_384, index, this.field_384, index + local_n, this.size - index);
    }
    BooleanIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_384[(index++)] = local_i.nextBoolean();
    }
    return true;
  }
  
  public boolean addAll(int index, BooleanList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_384, index, this.field_384, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_384, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public BooleanListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractBooleanListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < BooleanArrayList.this.size;
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
        return BooleanArrayList.this.field_384[(this.last = this.pos++)];
      }
      
      public boolean previousBoolean()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return BooleanArrayList.this.field_384[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        BooleanArrayList.this.removeBoolean(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public BooleanArrayList clone()
  {
    BooleanArrayList local_c = new BooleanArrayList(this.size);
    System.arraycopy(this.field_384, 0, local_c.field_384, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(BooleanArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    boolean[] local_a1 = this.field_384;
    boolean[] local_a2 = local_l.field_384;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(BooleanArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    boolean[] local_a1 = this.field_384;
    boolean[] local_a2 = local_l.field_384;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      boolean local_e1 = local_a1[local_i];
      boolean local_e2 = local_a2[local_i];
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
      local_s.writeBoolean(this.field_384[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_384 = new boolean[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_384[local_i] = local_s.readBoolean();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */