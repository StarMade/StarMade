package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ShortBigArrayBigList
  extends AbstractShortBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient short[][] field_220;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected ShortBigArrayBigList(short[][] local_a, boolean dummy)
  {
    this.field_220 = local_a;
  }
  
  public ShortBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_220 = ShortBigArrays.newBigArray(capacity);
  }
  
  public ShortBigArrayBigList()
  {
    this(16L);
  }
  
  public ShortBigArrayBigList(ShortCollection local_c)
  {
    this(local_c.size());
    ShortIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextShort());
    }
  }
  
  public ShortBigArrayBigList(ShortBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_220, 0L, this.size = local_l.size64());
  }
  
  public ShortBigArrayBigList(short[][] local_a)
  {
    this(local_a, 0L, ShortBigArrays.length(local_a));
  }
  
  public ShortBigArrayBigList(short[][] local_a, long offset, long length)
  {
    this(length);
    ShortBigArrays.copy(local_a, offset, this.field_220, 0L, length);
    this.size = length;
  }
  
  public ShortBigArrayBigList(Iterator<? extends Short> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Short)local_i.next());
    }
  }
  
  public ShortBigArrayBigList(ShortIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextShort());
    }
  }
  
  public short[][] elements()
  {
    return this.field_220;
  }
  
  public static ShortBigArrayBigList wrap(short[][] local_a, long length)
  {
    if (length > ShortBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + ShortBigArrays.length(local_a) + ")");
    }
    ShortBigArrayBigList local_l = new ShortBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static ShortBigArrayBigList wrap(short[][] local_a)
  {
    return wrap(local_a, ShortBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_220 = ShortBigArrays.ensureCapacity(this.field_220, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_220 = ShortBigArrays.grow(this.field_220, capacity, this.size);
  }
  
  public void add(long index, short local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      ShortBigArrays.copy(this.field_220, index, this.field_220, index + 1L, this.size - index);
    }
    ShortBigArrays.set(this.field_220, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(short local_k)
  {
    grow(this.size + 1L);
    ShortBigArrays.set(this.field_220, this.size++, local_k);
    return true;
  }
  
  public short getShort(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return ShortBigArrays.get(this.field_220, index);
  }
  
  public long indexOf(short local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == ShortBigArrays.get(this.field_220, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(short local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == ShortBigArrays.get(this.field_220, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public short removeShort(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    short old = ShortBigArrays.get(this.field_220, index);
    this.size -= 1L;
    if (index != this.size) {
      ShortBigArrays.copy(this.field_220, index + 1L, this.field_220, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(short local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeShort(index);
    return true;
  }
  
  public short set(long index, short local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    short old = ShortBigArrays.get(this.field_220, index);
    ShortBigArrays.set(this.field_220, index, local_k);
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
    if (size > ShortBigArrays.length(this.field_220)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ShortBigArrays.fill(this.field_220, this.size, size, (short)0);
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
    long arrayLength = ShortBigArrays.length(this.field_220);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_220 = ShortBigArrays.trim(this.field_220, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, short[][] local_a, long offset, long length)
  {
    ShortBigArrays.copy(this.field_220, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    ShortBigArrays.copy(this.field_220, local_to, this.field_220, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, short[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ShortBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    ShortBigArrays.copy(this.field_220, index, this.field_220, index + length, this.size - index);
    ShortBigArrays.copy(local_a, offset, this.field_220, index, length);
    this.size += length;
  }
  
  public ShortBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractShortBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ShortBigArrayBigList.this.size;
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
        return ShortBigArrays.get(ShortBigArrayBigList.this.field_220, this.last = this.pos++);
      }
      
      public short previousShort()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ShortBigArrays.get(ShortBigArrayBigList.this.field_220, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(short local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(short local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ShortBigArrayBigList.this.removeShort(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ShortBigArrayBigList clone()
  {
    ShortBigArrayBigList local_c = new ShortBigArrayBigList(this.size);
    ShortBigArrays.copy(this.field_220, 0L, local_c.field_220, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(ShortBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    short[][] local_a1 = this.field_220;
    short[][] local_a2 = local_l.field_220;
    while (local_s-- != 0L) {
      if (ShortBigArrays.get(local_a1, local_s) != ShortBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(ShortBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    short[][] local_a1 = this.field_220;
    short[][] local_a2 = local_l.field_220;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      short local_e1 = ShortBigArrays.get(local_a1, local_i);
      short local_e2 = ShortBigArrays.get(local_a2, local_i);
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
      local_s.writeShort(ShortBigArrays.get(this.field_220, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_220 = ShortBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      ShortBigArrays.set(this.field_220, local_i, local_s.readShort());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */