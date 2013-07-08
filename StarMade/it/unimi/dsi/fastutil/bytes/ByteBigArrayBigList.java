package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ByteBigArrayBigList
  extends AbstractByteBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient byte[][] field_338;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected ByteBigArrayBigList(byte[][] local_a, boolean dummy)
  {
    this.field_338 = local_a;
  }
  
  public ByteBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_338 = ByteBigArrays.newBigArray(capacity);
  }
  
  public ByteBigArrayBigList()
  {
    this(16L);
  }
  
  public ByteBigArrayBigList(ByteCollection local_c)
  {
    this(local_c.size());
    ByteIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextByte());
    }
  }
  
  public ByteBigArrayBigList(ByteBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_338, 0L, this.size = local_l.size64());
  }
  
  public ByteBigArrayBigList(byte[][] local_a)
  {
    this(local_a, 0L, ByteBigArrays.length(local_a));
  }
  
  public ByteBigArrayBigList(byte[][] local_a, long offset, long length)
  {
    this(length);
    ByteBigArrays.copy(local_a, offset, this.field_338, 0L, length);
    this.size = length;
  }
  
  public ByteBigArrayBigList(Iterator<? extends Byte> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Byte)local_i.next());
    }
  }
  
  public ByteBigArrayBigList(ByteIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextByte());
    }
  }
  
  public byte[][] elements()
  {
    return this.field_338;
  }
  
  public static ByteBigArrayBigList wrap(byte[][] local_a, long length)
  {
    if (length > ByteBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + ByteBigArrays.length(local_a) + ")");
    }
    ByteBigArrayBigList local_l = new ByteBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static ByteBigArrayBigList wrap(byte[][] local_a)
  {
    return wrap(local_a, ByteBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_338 = ByteBigArrays.ensureCapacity(this.field_338, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_338 = ByteBigArrays.grow(this.field_338, capacity, this.size);
  }
  
  public void add(long index, byte local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      ByteBigArrays.copy(this.field_338, index, this.field_338, index + 1L, this.size - index);
    }
    ByteBigArrays.set(this.field_338, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(byte local_k)
  {
    grow(this.size + 1L);
    ByteBigArrays.set(this.field_338, this.size++, local_k);
    return true;
  }
  
  public byte getByte(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return ByteBigArrays.get(this.field_338, index);
  }
  
  public long indexOf(byte local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == ByteBigArrays.get(this.field_338, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(byte local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == ByteBigArrays.get(this.field_338, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public byte removeByte(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    byte old = ByteBigArrays.get(this.field_338, index);
    this.size -= 1L;
    if (index != this.size) {
      ByteBigArrays.copy(this.field_338, index + 1L, this.field_338, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(byte local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeByte(index);
    return true;
  }
  
  public byte set(long index, byte local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    byte old = ByteBigArrays.get(this.field_338, index);
    ByteBigArrays.set(this.field_338, index, local_k);
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
    if (size > ByteBigArrays.length(this.field_338)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ByteBigArrays.fill(this.field_338, this.size, size, (byte)0);
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
    long arrayLength = ByteBigArrays.length(this.field_338);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_338 = ByteBigArrays.trim(this.field_338, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, byte[][] local_a, long offset, long length)
  {
    ByteBigArrays.copy(this.field_338, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    ByteBigArrays.copy(this.field_338, local_to, this.field_338, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, byte[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ByteBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    ByteBigArrays.copy(this.field_338, index, this.field_338, index + length, this.size - index);
    ByteBigArrays.copy(local_a, offset, this.field_338, index, length);
    this.size += length;
  }
  
  public ByteBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractByteBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ByteBigArrayBigList.this.size;
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
        return ByteBigArrays.get(ByteBigArrayBigList.this.field_338, this.last = this.pos++);
      }
      
      public byte previousByte()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ByteBigArrays.get(ByteBigArrayBigList.this.field_338, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ByteBigArrayBigList.this.removeByte(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ByteBigArrayBigList clone()
  {
    ByteBigArrayBigList local_c = new ByteBigArrayBigList(this.size);
    ByteBigArrays.copy(this.field_338, 0L, local_c.field_338, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(ByteBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    byte[][] local_a1 = this.field_338;
    byte[][] local_a2 = local_l.field_338;
    while (local_s-- != 0L) {
      if (ByteBigArrays.get(local_a1, local_s) != ByteBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(ByteBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    byte[][] local_a1 = this.field_338;
    byte[][] local_a2 = local_l.field_338;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      byte local_e1 = ByteBigArrays.get(local_a1, local_i);
      byte local_e2 = ByteBigArrays.get(local_a2, local_i);
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
      local_s.writeByte(ByteBigArrays.get(this.field_338, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_338 = ByteBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      ByteBigArrays.set(this.field_338, local_i, local_s.readByte());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */