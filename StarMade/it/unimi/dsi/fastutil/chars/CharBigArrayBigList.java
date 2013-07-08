package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.BigArrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class CharBigArrayBigList
  extends AbstractCharBigList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient char[][] field_61;
  protected long size;
  private static final boolean ASSERTS = false;
  
  protected CharBigArrayBigList(char[][] local_a, boolean dummy)
  {
    this.field_61 = local_a;
  }
  
  public CharBigArrayBigList(long capacity)
  {
    if (capacity < 0L) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_61 = CharBigArrays.newBigArray(capacity);
  }
  
  public CharBigArrayBigList()
  {
    this(16L);
  }
  
  public CharBigArrayBigList(CharCollection local_c)
  {
    this(local_c.size());
    CharIterator local_i = local_c.iterator();
    while (local_i.hasNext()) {
      add(local_i.nextChar());
    }
  }
  
  public CharBigArrayBigList(CharBigList local_l)
  {
    this(local_l.size64());
    local_l.getElements(0L, this.field_61, 0L, this.size = local_l.size64());
  }
  
  public CharBigArrayBigList(char[][] local_a)
  {
    this(local_a, 0L, CharBigArrays.length(local_a));
  }
  
  public CharBigArrayBigList(char[][] local_a, long offset, long length)
  {
    this(length);
    CharBigArrays.copy(local_a, offset, this.field_61, 0L, length);
    this.size = length;
  }
  
  public CharBigArrayBigList(Iterator<? extends Character> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Character)local_i.next());
    }
  }
  
  public CharBigArrayBigList(CharIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextChar());
    }
  }
  
  public char[][] elements()
  {
    return this.field_61;
  }
  
  public static CharBigArrayBigList wrap(char[][] local_a, long length)
  {
    if (length > CharBigArrays.length(local_a)) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + CharBigArrays.length(local_a) + ")");
    }
    CharBigArrayBigList local_l = new CharBigArrayBigList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static CharBigArrayBigList wrap(char[][] local_a)
  {
    return wrap(local_a, CharBigArrays.length(local_a));
  }
  
  public void ensureCapacity(long capacity)
  {
    this.field_61 = CharBigArrays.ensureCapacity(this.field_61, capacity, this.size);
  }
  
  private void grow(long capacity)
  {
    this.field_61 = CharBigArrays.grow(this.field_61, capacity, this.size);
  }
  
  public void add(long index, char local_k)
  {
    ensureIndex(index);
    grow(this.size + 1L);
    if (index != this.size) {
      CharBigArrays.copy(this.field_61, index, this.field_61, index + 1L, this.size - index);
    }
    CharBigArrays.set(this.field_61, index, local_k);
    this.size += 1L;
  }
  
  public boolean add(char local_k)
  {
    grow(this.size + 1L);
    CharBigArrays.set(this.field_61, this.size++, local_k);
    return true;
  }
  
  public char getChar(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return CharBigArrays.get(this.field_61, index);
  }
  
  public long indexOf(char local_k)
  {
    for (long local_i = 0L; local_i < this.size; local_i += 1L) {
      if (local_k == CharBigArrays.get(this.field_61, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(char local_k)
  {
    long local_i = this.size;
    while (local_i-- != 0L) {
      if (local_k == CharBigArrays.get(this.field_61, local_i)) {
        return local_i;
      }
    }
    return -1L;
  }
  
  public char removeChar(long index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    char old = CharBigArrays.get(this.field_61, index);
    this.size -= 1L;
    if (index != this.size) {
      CharBigArrays.copy(this.field_61, index + 1L, this.field_61, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(char local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeChar(index);
    return true;
  }
  
  public char set(long index, char local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    char old = CharBigArrays.get(this.field_61, index);
    CharBigArrays.set(this.field_61, index, local_k);
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
    if (size > CharBigArrays.length(this.field_61)) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      CharBigArrays.fill(this.field_61, this.size, size, '\000');
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
    long arrayLength = CharBigArrays.length(this.field_61);
    if ((local_n >= arrayLength) || (this.size == arrayLength)) {
      return;
    }
    this.field_61 = CharBigArrays.trim(this.field_61, Math.max(local_n, this.size));
  }
  
  public void getElements(int from, char[][] local_a, long offset, long length)
  {
    CharBigArrays.copy(this.field_61, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    BigArrays.ensureFromTo(this.size, from, local_to);
    CharBigArrays.copy(this.field_61, local_to, this.field_61, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, char[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    CharBigArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    CharBigArrays.copy(this.field_61, index, this.field_61, index + length, this.size - index);
    CharBigArrays.copy(local_a, offset, this.field_61, index, length);
    this.size += length;
  }
  
  public CharBigListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractCharBigListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < CharBigArrayBigList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public char nextChar()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return CharBigArrays.get(CharBigArrayBigList.this.field_61, this.last = this.pos++);
      }
      
      public char previousChar()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return CharBigArrays.get(CharBigArrayBigList.this.field_61, this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(char local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharBigArrayBigList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(char local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharBigArrayBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharBigArrayBigList.this.removeChar(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public CharBigArrayBigList clone()
  {
    CharBigArrayBigList local_c = new CharBigArrayBigList(this.size);
    CharBigArrays.copy(this.field_61, 0L, local_c.field_61, 0L, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(CharBigArrayBigList local_l)
  {
    if (local_l == this) {
      return true;
    }
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    char[][] local_a1 = this.field_61;
    char[][] local_a2 = local_l.field_61;
    while (local_s-- != 0L) {
      if (CharBigArrays.get(local_a1, local_s) != CharBigArrays.get(local_a2, local_s)) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(CharBigArrayBigList local_l)
  {
    long local_s1 = size64();
    long local_s2 = local_l.size64();
    char[][] local_a1 = this.field_61;
    char[][] local_a2 = local_l.field_61;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      char local_e1 = CharBigArrays.get(local_a1, local_i);
      char local_e2 = CharBigArrays.get(local_a2, local_i);
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
      local_s.writeChar(CharBigArrays.get(this.field_61, local_i));
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_61 = CharBigArrays.newBigArray(this.size);
    for (int local_i = 0; local_i < this.size; local_i++) {
      CharBigArrays.set(this.field_61, local_i, local_s.readChar());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */