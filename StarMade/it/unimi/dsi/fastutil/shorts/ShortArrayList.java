package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ShortArrayList
  extends AbstractShortList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient short[] field_220;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected ShortArrayList(short[] local_a, boolean dummy)
  {
    this.field_220 = local_a;
  }
  
  public ShortArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_220 = new short[capacity];
  }
  
  public ShortArrayList()
  {
    this(16);
  }
  
  public ShortArrayList(Collection<? extends Short> local_c)
  {
    this(local_c.size());
    this.size = ShortIterators.unwrap(ShortIterators.asShortIterator(local_c.iterator()), this.field_220);
  }
  
  public ShortArrayList(ShortCollection local_c)
  {
    this(local_c.size());
    this.size = ShortIterators.unwrap(local_c.iterator(), this.field_220);
  }
  
  public ShortArrayList(ShortList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_220, 0, this.size = local_l.size());
  }
  
  public ShortArrayList(short[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public ShortArrayList(short[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_220, 0, length);
    this.size = length;
  }
  
  public ShortArrayList(Iterator<? extends Short> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Short)local_i.next());
    }
  }
  
  public ShortArrayList(ShortIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextShort());
    }
  }
  
  public short[] elements()
  {
    return this.field_220;
  }
  
  public static ShortArrayList wrap(short[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    ShortArrayList local_l = new ShortArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static ShortArrayList wrap(short[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_220 = ShortArrays.ensureCapacity(this.field_220, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_220 = ShortArrays.grow(this.field_220, capacity, this.size);
  }
  
  public void add(int index, short local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_220, index, this.field_220, index + 1, this.size - index);
    }
    this.field_220[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(short local_k)
  {
    grow(this.size + 1);
    this.field_220[(this.size++)] = local_k;
    return true;
  }
  
  public short getShort(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_220[index];
  }
  
  public int indexOf(short local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_220[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(short local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_220[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public short removeShort(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    short old = this.field_220[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_220, index + 1, this.field_220, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(short local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeShort(index);
    return true;
  }
  
  public short set(int index, short local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    short old = this.field_220[index];
    this.field_220[index] = local_k;
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
    if (size > this.field_220.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ShortArrays.fill(this.field_220, this.size, size, (short)0);
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
    if ((local_n >= this.field_220.length) || (this.size == this.field_220.length)) {
      return;
    }
    short[] local_t = new short[Math.max(local_n, this.size)];
    System.arraycopy(this.field_220, 0, local_t, 0, this.size);
    this.field_220 = local_t;
  }
  
  public void getElements(int from, short[] local_a, int offset, int length)
  {
    ShortArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_220, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_220, local_to, this.field_220, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, short[] local_a, int offset, int length)
  {
    ensureIndex(index);
    ShortArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_220, index, this.field_220, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_220, index, length);
    this.size += length;
  }
  
  public short[] toShortArray(short[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new short[this.size];
    }
    System.arraycopy(this.field_220, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, ShortCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_220, index, this.field_220, index + local_n, this.size - index);
    }
    ShortIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_220[(index++)] = local_i.nextShort();
    }
    return true;
  }
  
  public boolean addAll(int index, ShortList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_220, index, this.field_220, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_220, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public ShortListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractShortListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ShortArrayList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public short nextShort()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return ShortArrayList.this.field_220[(this.last = this.pos++)];
      }
      
      public short previousShort()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ShortArrayList.this.field_220[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(short local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(short local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortArrayList.this.removeShort(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ShortArrayList clone()
  {
    ShortArrayList local_c = new ShortArrayList(this.size);
    System.arraycopy(this.field_220, 0, local_c.field_220, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(ShortArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    short[] local_a1 = this.field_220;
    short[] local_a2 = local_l.field_220;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(ShortArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    short[] local_a1 = this.field_220;
    short[] local_a2 = local_l.field_220;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      short local_e1 = local_a1[local_i];
      short local_e2 = local_a2[local_i];
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
      local_s.writeShort(this.field_220[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_220 = new short[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_220[local_i] = local_s.readShort();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */