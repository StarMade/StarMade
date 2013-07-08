package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ObjectArrayList<K>
  extends AbstractObjectList<K>
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353131L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected final boolean wrapped;
  protected transient K[] field_75;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected ObjectArrayList(K[] local_a, boolean dummy)
  {
    this.field_75 = local_a;
    this.wrapped = true;
  }
  
  public ObjectArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_75 = ((Object[])new Object[capacity]);
    this.wrapped = false;
  }
  
  public ObjectArrayList()
  {
    this(16);
  }
  
  public ObjectArrayList(Collection<? extends K> local_c)
  {
    this(local_c.size());
    this.size = ObjectIterators.unwrap(local_c.iterator(), this.field_75);
  }
  
  public ObjectArrayList(ObjectCollection<? extends K> local_c)
  {
    this(local_c.size());
    this.size = ObjectIterators.unwrap(local_c.iterator(), this.field_75);
  }
  
  public ObjectArrayList(ObjectList<? extends K> local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_75, 0, this.size = local_l.size());
  }
  
  public ObjectArrayList(K[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public ObjectArrayList(K[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_75, 0, length);
    this.size = length;
  }
  
  public ObjectArrayList(Iterator<? extends K> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ObjectArrayList(ObjectIterator<? extends K> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public K[] elements()
  {
    return this.field_75;
  }
  
  public static <K> ObjectArrayList<K> wrap(K[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    ObjectArrayList<K> local_l = new ObjectArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static <K> ObjectArrayList<K> wrap(K[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    if (this.wrapped)
    {
      this.field_75 = ObjectArrays.ensureCapacity(this.field_75, capacity, this.size);
    }
    else if (capacity > this.field_75.length)
    {
      Object[] local_t = new Object[capacity];
      System.arraycopy(this.field_75, 0, local_t, 0, this.size);
      this.field_75 = ((Object[])local_t);
    }
  }
  
  private void grow(int capacity)
  {
    if (this.wrapped)
    {
      this.field_75 = ObjectArrays.grow(this.field_75, capacity, this.size);
    }
    else if (capacity > this.field_75.length)
    {
      int newLength = (int)Math.min(Math.max(106039L * this.field_75.length >>> 16, capacity), 2147483647L);
      Object[] local_t = new Object[newLength];
      System.arraycopy(this.field_75, 0, local_t, 0, this.size);
      this.field_75 = ((Object[])local_t);
    }
  }
  
  public void add(int index, K local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_75, index, this.field_75, index + 1, this.size - index);
    }
    this.field_75[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(K local_k)
  {
    grow(this.size + 1);
    this.field_75[(this.size++)] = local_k;
    return true;
  }
  
  public K get(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_75[index];
  }
  
  public int indexOf(Object local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == null ? this.field_75[local_i] == null : local_k.equals(this.field_75[local_i])) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(Object local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == null ? this.field_75[local_i] == null : local_k.equals(this.field_75[local_i])) {
        return local_i;
      }
    }
    return -1;
  }
  
  public K remove(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    K old = this.field_75[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_75, index + 1, this.field_75, index, this.size - index);
    }
    this.field_75[this.size] = null;
    return old;
  }
  
  public boolean rem(Object local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    remove(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(local_o);
  }
  
  public K set(int index, K local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    K old = this.field_75[index];
    this.field_75[index] = local_k;
    return old;
  }
  
  public void clear()
  {
    ObjectArrays.fill(this.field_75, 0, this.size, null);
    this.size = 0;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void size(int size)
  {
    if (size > this.field_75.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      ObjectArrays.fill(this.field_75, this.size, size, null);
    } else {
      ObjectArrays.fill(this.field_75, size, this.size, null);
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
    if ((local_n >= this.field_75.length) || (this.size == this.field_75.length)) {
      return;
    }
    K[] local_t = (Object[])new Object[Math.max(local_n, this.size)];
    System.arraycopy(this.field_75, 0, local_t, 0, this.size);
    this.field_75 = local_t;
  }
  
  public void getElements(int from, Object[] local_a, int offset, int length)
  {
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_75, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_75, local_to, this.field_75, from, this.size - local_to);
    this.size -= local_to - from;
    int local_i = local_to - from;
    while (local_i-- != 0) {
      this.field_75[(this.size + local_i)] = null;
    }
  }
  
  public void addElements(int index, K[] local_a, int offset, int length)
  {
    ensureIndex(index);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_75, index, this.field_75, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_75, index, length);
    this.size += length;
  }
  
  public ObjectListIterator<K> listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractObjectListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < ObjectArrayList.this.size;
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
        return ObjectArrayList.this.field_75[(this.last = this.pos++)];
      }
      
      public K previous()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return ObjectArrayList.this.field_75[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ObjectArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ObjectArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        ObjectArrayList.this.remove(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public ObjectArrayList<K> clone()
  {
    ObjectArrayList<K> local_c = new ObjectArrayList(this.size);
    System.arraycopy(this.field_75, 0, local_c.field_75, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  private boolean valEquals(K local_a, K local_b)
  {
    return local_a == null ? false : local_b == null ? true : local_a.equals(local_b);
  }
  
  public boolean equals(ObjectArrayList<K> local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    K[] local_a1 = this.field_75;
    K[] local_a2 = local_l.field_75;
    while (local_s-- != 0) {
      if (!valEquals(local_a1[local_s], local_a2[local_s])) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(ObjectArrayList<? extends K> local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    K[] local_a1 = this.field_75;
    K[] local_a2 = local_l.field_75;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      K local_e1 = local_a1[local_i];
      K local_e2 = local_a2[local_i];
      int local_r;
      if ((local_r = ((Comparable)local_e1).compareTo(local_e2)) != 0) {
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
      local_s.writeObject(this.field_75[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_75 = ((Object[])new Object[this.size]);
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_75[local_i] = local_s.readObject();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */