package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ByteArrayList
  extends AbstractByteList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient byte[] field_338;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected ByteArrayList(byte[] local_a, boolean dummy)
  {
    this.field_338 = local_a;
  }
  
  public ByteArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_338 = new byte[capacity];
  }
  
  public ByteArrayList()
  {
    this(16);
  }
  
  public ByteArrayList(Collection<? extends Byte> local_c)
  {
    this(local_c.size());
    this.size = ByteIterators.unwrap(ByteIterators.asByteIterator(local_c.iterator()), this.field_338);
  }
  
  public ByteArrayList(ByteCollection local_c)
  {
    this(local_c.size());
    this.size = ByteIterators.unwrap(local_c.iterator(), this.field_338);
  }
  
  public ByteArrayList(ByteList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_338, 0, this.size = local_l.size());
  }
  
  public ByteArrayList(byte[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public ByteArrayList(byte[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_338, 0, length);
    this.size = length;
  }
  
  public ByteArrayList(Iterator<? extends Byte> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Byte)local_i.next());
    }
  }
  
  public ByteArrayList(ByteIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextByte());
    }
  }
  
  public byte[] elements()
  {
    return this.field_338;
  }
  
  public static ByteArrayList wrap(byte[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    ByteArrayList local_l = new ByteArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static ByteArrayList wrap(byte[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_338 = ByteArrays.ensureCapacity(this.field_338, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_338 = ByteArrays.grow(this.field_338, capacity, this.size);
  }
  
  public void add(int index, byte local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_338, index, this.field_338, index + 1, this.size - index);
    }
    this.field_338[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(byte local_k)
  {
    grow(this.size + 1);
    this.field_338[(this.size++)] = local_k;
    return true;
  }
  
  public byte getByte(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_338[index];
  }
  
  public int indexOf(byte local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_338[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(byte local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_338[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public byte removeByte(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    byte old = this.field_338[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_338, index + 1, this.field_338, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(byte local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeByte(index);
    return true;
  }
  
  public byte set(int index, byte local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    byte old = this.field_338[index];
    this.field_338[index] = local_k;
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
    if (size > this.field_338.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ByteArrays.fill(this.field_338, this.size, size, (byte)0);
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
    if ((local_n >= this.field_338.length) || (this.size == this.field_338.length)) {
      return;
    }
    byte[] local_t = new byte[Math.max(local_n, this.size)];
    System.arraycopy(this.field_338, 0, local_t, 0, this.size);
    this.field_338 = local_t;
  }
  
  public void getElements(int from, byte[] local_a, int offset, int length)
  {
    ByteArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_338, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_338, local_to, this.field_338, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, byte[] local_a, int offset, int length)
  {
    ensureIndex(index);
    ByteArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_338, index, this.field_338, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_338, index, length);
    this.size += length;
  }
  
  public byte[] toByteArray(byte[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new byte[this.size];
    }
    System.arraycopy(this.field_338, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, ByteCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_338, index, this.field_338, index + local_n, this.size - index);
    }
    ByteIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_338[(index++)] = local_i.nextByte();
    }
    return true;
  }
  
  public boolean addAll(int index, ByteList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_338, index, this.field_338, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_338, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public ByteListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractByteListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ByteArrayList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public byte nextByte()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return ByteArrayList.this.field_338[(this.last = this.pos++)];
      }
      
      public byte previousByte()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ByteArrayList.this.field_338[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteArrayList.this.removeByte(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ByteArrayList clone()
  {
    ByteArrayList local_c = new ByteArrayList(this.size);
    System.arraycopy(this.field_338, 0, local_c.field_338, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(ByteArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    byte[] local_a1 = this.field_338;
    byte[] local_a2 = local_l.field_338;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(ByteArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    byte[] local_a1 = this.field_338;
    byte[] local_a2 = local_l.field_338;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      byte local_e1 = local_a1[local_i];
      byte local_e2 = local_a2[local_i];
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
      local_s.writeByte(this.field_338[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_338 = new byte[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_338[local_i] = local_s.readByte();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */