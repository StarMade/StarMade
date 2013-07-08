package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ReferenceBigArrayBigList<K>
  extends AbstractReferenceBigList<K>
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353131L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected final boolean wrapped;
  protected transient K[][] field_75;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected ReferenceBigArrayBigList(K[][] local_a, boolean dummy)
  {
    this.field_75 = local_a;
    this.wrapped = true;
  }
  
  public ReferenceBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_75 = ((Object[][])ObjectBigArrays.newBigArray(capacity));
    this.wrapped = false;
  }
  
  public ReferenceBigArrayBigList()
  {
    this(16L);
  }
  
  public ReferenceBigArrayBigList(ReferenceCollection<? extends K> local_c)
  {
    this(local_c.size());
    ObjectIterator<? extends K> local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ReferenceBigArrayBigList(ReferenceBigList<? extends K> local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_75, 0L, this.size = local_l.size64());
  }
  
  public ReferenceBigArrayBigList(K[][] local_a)
  {
    this(local_a, 0L, ObjectBigArrays.length(local_a));
  }
  
  public ReferenceBigArrayBigList(K[][] local_a, long offset, long length)
  {
    this(length);
    ObjectBigArrays.copy(local_a, offset, this.field_75, 0L, length);
    this.size = length;
  }
  
  public ReferenceBigArrayBigList(Iterator<? extends K> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ReferenceBigArrayBigList(ObjectIterator<? extends K> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public K[][] elements()
  {
    return this.field_75;
  }
  
  public static <K> ReferenceBigArrayBigList<K> wrap(K[][] local_a, long length)
  {
    if (length > ObjectBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + ObjectBigArrays.length(local_a) + ")");
    }
    ReferenceBigArrayBigList<K> local_l = new ReferenceBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static <K> ReferenceBigArrayBigList<K> wrap(K[][] local_a)
  {
    return wrap(local_a, ObjectBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    if (this.wrapped)
    {
      this.field_75 = ObjectBigArrays.ensureCapacity(this.field_75, capacity, this.size);
    }
    else if (capacity > ObjectBigArrays.length(this.field_75))
    {
      Object[][] local_t = ObjectBigArrays.newBigArray(capacity);
      ObjectBigArrays.copy(this.field_75, 0L, local_t, 0L, this.size);
      this.field_75 = ((Object[][])local_t);
    }
  }
  
  private void grow(long capacity)
  {
    if (this.wrapped)
    {
      this.field_75 = ObjectBigArrays.grow(this.field_75, capacity, this.size);
    }
    else if (capacity > ObjectBigArrays.length(this.field_75))
    {
      int newLength = (int)Math.min(Math.max(106039L * ObjectBigArrays.length(this.field_75) >>> 16, capacity), 2147483647L);
      Object[][] local_t = ObjectBigArrays.newBigArray(newLength);
      ObjectBigArrays.copy(this.field_75, 0L, local_t, 0L, this.size);
      this.field_75 = ((Object[][])local_t);
    }
  }
  
  public void add(long index, K local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      ObjectBigArrays.copy(this.field_75, index, this.field_75, index + 1L, this.size - index);
    }
    ObjectBigArrays.set(this.field_75, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(K local_k)
  {
    grow(this.size + 1L);
    ObjectBigArrays.set(this.field_75, this.size++, local_k);
    return true;
  }
  
  public K get(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return ObjectBigArrays.get(this.field_75, index);
  }
  
  public long indexOf(Object local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == ObjectBigArrays.get(this.field_75, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(Object local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == ObjectBigArrays.get(this.field_75, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public K remove(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    K old = ObjectBigArrays.get(this.field_75, index);
    this.size -= 1L;
    if (index != this.size) {
      ObjectBigArrays.copy(this.field_75, index + 1L, this.field_75, index, this.size - index);
    }
    ObjectBigArrays.set(this.field_75, this.size, null);
    return old;
  }
  
  public boolean rem(Object local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    remove(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(local_o);
  }
  
  public K set(long index, K local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    K old = ObjectBigArrays.get(this.field_75, index);
    ObjectBigArrays.set(this.field_75, index, local_k);
    return old;
  }
  
  public void clear()
  {
    ObjectBigArrays.fill(this.field_75, 0L, this.size, null);
    this.size = 0L;
  }
  
  public long size64()
  {
    return this.size;
  }
  
  public void size(long size)
  {
    if (size > ObjectBigArrays.length(this.field_75)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ObjectBigArrays.fill(this.field_75, this.size, size, null);
    } else {
      ObjectBigArrays.fill(this.field_75, size, this.size, null);
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
    long arrayLength = ObjectBigArrays.length(this.field_75);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_75 = ObjectBigArrays.trim(this.field_75, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, Object[][] local_a, long offset, long length)
  {
    ObjectBigArrays.copy(this.field_75, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    ObjectBigArrays.copy(this.field_75, local_to, this.field_75, from, this.size - local_to);
    this.size -= local_to - from;
    ObjectBigArrays.fill(this.field_75, this.size, this.size + local_to - from, null);
  }
  
  public void addElements(int index, K[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ObjectBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    ObjectBigArrays.copy(this.field_75, index, this.field_75, index + length, this.size - index);
    ObjectBigArrays.copy(local_a, offset, this.field_75, index, length);
    this.size += length;
  }
  
  public ObjectBigListIterator<K> listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractObjectBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ReferenceBigArrayBigList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public K next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return ObjectBigArrays.get(ReferenceBigArrayBigList.this.field_75, this.last = this.pos++);
      }
      
      public K previous()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ObjectBigArrays.get(ReferenceBigArrayBigList.this.field_75, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ReferenceBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ReferenceBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ReferenceBigArrayBigList.this.remove(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ReferenceBigArrayBigList<K> clone()
  {
    ReferenceBigArrayBigList<K> local_c = new ReferenceBigArrayBigList(this.size);
    ObjectBigArrays.copy(this.field_75, 0L, local_c.field_75, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(ReferenceBigArrayBigList<K> local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    K[][] local_a1 = this.field_75;
    K[][] local_a2 = local_l.field_75;
    while (local_s-- != 0L) {
      if (ObjectBigArrays.get(local_a1, local_s) != ObjectBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeObject(ObjectBigArrays.get(this.field_75, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_75 = ((Object[][])ObjectBigArrays.newBigArray(this.size));
    for (int local_i = 0; local_i < this.size; local_i++) {
      ObjectBigArrays.set(this.field_75, local_i, local_s.readObject());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */