package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Arrays;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class CharArrayList
  extends AbstractCharList
  implements RandomAccess, Cloneable, Serializable
{
  public static final long serialVersionUID = -7046029254386353130L;
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  protected static final long ONEOVERPHI = 106039L;
  protected transient char[] field_61;
  protected int size;
  private static final boolean ASSERTS = false;
  
  protected CharArrayList(char[] local_a, boolean dummy)
  {
    this.field_61 = local_a;
  }
  
  public CharArrayList(int capacity)
  {
    if (capacity < 0) {
      throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
    }
    this.field_61 = new char[capacity];
  }
  
  public CharArrayList()
  {
    this(16);
  }
  
  public CharArrayList(Collection<? extends Character> local_c)
  {
    this(local_c.size());
    this.size = CharIterators.unwrap(CharIterators.asCharIterator(local_c.iterator()), this.field_61);
  }
  
  public CharArrayList(CharCollection local_c)
  {
    this(local_c.size());
    this.size = CharIterators.unwrap(local_c.iterator(), this.field_61);
  }
  
  public CharArrayList(CharList local_l)
  {
    this(local_l.size());
    local_l.getElements(0, this.field_61, 0, this.size = local_l.size());
  }
  
  public CharArrayList(char[] local_a)
  {
    this(local_a, 0, local_a.length);
  }
  
  public CharArrayList(char[] local_a, int offset, int length)
  {
    this(length);
    System.arraycopy(local_a, offset, this.field_61, 0, length);
    this.size = length;
  }
  
  public CharArrayList(Iterator<? extends Character> local_i)
  {
    this();
    while (local_i.hasNext()) {
      add((Character)local_i.next());
    }
  }
  
  public CharArrayList(CharIterator local_i)
  {
    this();
    while (local_i.hasNext()) {
      add(local_i.nextChar());
    }
  }
  
  public char[] elements()
  {
    return this.field_61;
  }
  
  public static CharArrayList wrap(char[] local_a, int length)
  {
    if (length > local_a.length) {
      throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + local_a.length + ")");
    }
    CharArrayList local_l = new CharArrayList(local_a, false);
    local_l.size = length;
    return local_l;
  }
  
  public static CharArrayList wrap(char[] local_a)
  {
    return wrap(local_a, local_a.length);
  }
  
  public void ensureCapacity(int capacity)
  {
    this.field_61 = CharArrays.ensureCapacity(this.field_61, capacity, this.size);
  }
  
  private void grow(int capacity)
  {
    this.field_61 = CharArrays.grow(this.field_61, capacity, this.size);
  }
  
  public void add(int index, char local_k)
  {
    ensureIndex(index);
    grow(this.size + 1);
    if (index != this.size) {
      System.arraycopy(this.field_61, index, this.field_61, index + 1, this.size - index);
    }
    this.field_61[index] = local_k;
    this.size += 1;
  }
  
  public boolean add(char local_k)
  {
    grow(this.size + 1);
    this.field_61[(this.size++)] = local_k;
    return true;
  }
  
  public char getChar(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    return this.field_61[index];
  }
  
  public int indexOf(char local_k)
  {
    for (int local_i = 0; local_i < this.size; local_i++) {
      if (local_k == this.field_61[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int lastIndexOf(char local_k)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_k == this.field_61[local_i]) {
        return local_i;
      }
    }
    return -1;
  }
  
  public char removeChar(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    char old = this.field_61[index];
    this.size -= 1;
    if (index != this.size) {
      System.arraycopy(this.field_61, index + 1, this.field_61, index, this.size - index);
    }
    return old;
  }
  
  public boolean rem(char local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeChar(index);
    return true;
  }
  
  public char set(int index, char local_k)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
    }
    char old = this.field_61[index];
    this.field_61[index] = local_k;
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
    if (size > this.field_61.length) {
      ensureCapacity(size);
    }
    if (size > this.size) {
      CharArrays.fill(this.field_61, this.size, size, '\000');
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
    if ((local_n >= this.field_61.length) || (this.size == this.field_61.length)) {
      return;
    }
    char[] local_t = new char[Math.max(local_n, this.size)];
    System.arraycopy(this.field_61, 0, local_t, 0, this.size);
    this.field_61 = local_t;
  }
  
  public void getElements(int from, char[] local_a, int offset, int length)
  {
    CharArrays.ensureOffsetLength(local_a, offset, length);
    System.arraycopy(this.field_61, from, local_a, offset, length);
  }
  
  public void removeElements(int from, int local_to)
  {
    Arrays.ensureFromTo(this.size, from, local_to);
    System.arraycopy(this.field_61, local_to, this.field_61, from, this.size - local_to);
    this.size -= local_to - from;
  }
  
  public void addElements(int index, char[] local_a, int offset, int length)
  {
    ensureIndex(index);
    CharArrays.ensureOffsetLength(local_a, offset, length);
    grow(this.size + length);
    System.arraycopy(this.field_61, index, this.field_61, index + length, this.size - index);
    System.arraycopy(local_a, offset, this.field_61, index, length);
    this.size += length;
  }
  
  public char[] toCharArray(char[] local_a)
  {
    if ((local_a == null) || (local_a.length < this.size)) {
      local_a = new char[this.size];
    }
    System.arraycopy(this.field_61, 0, local_a, 0, this.size);
    return local_a;
  }
  
  public boolean addAll(int index, CharCollection local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_61, index, this.field_61, index + local_n, this.size - index);
    }
    CharIterator local_i = local_c.iterator();
    this.size += local_n;
    while (local_n-- != 0) {
      this.field_61[(index++)] = local_i.nextChar();
    }
    return true;
  }
  
  public boolean addAll(int index, CharList local_l)
  {
    ensureIndex(index);
    int local_n = local_l.size();
    if (local_n == 0) {
      return false;
    }
    grow(this.size + local_n);
    if (index != this.size) {
      System.arraycopy(this.field_61, index, this.field_61, index + local_n, this.size - index);
    }
    local_l.getElements(0, this.field_61, index, local_n);
    this.size += local_n;
    return true;
  }
  
  public CharListIterator listIterator(final int index)
  {
    ensureIndex(index);
    new AbstractCharListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < CharArrayList.this.size;
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
        return CharArrayList.this.field_61[(this.last = this.pos++)];
      }
      
      public char previousChar()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return CharArrayList.this.field_61[(this.last = --this.pos)];
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(char local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharArrayList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(char local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharArrayList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        CharArrayList.this.removeChar(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public CharArrayList clone()
  {
    CharArrayList local_c = new CharArrayList(this.size);
    System.arraycopy(this.field_61, 0, local_c.field_61, 0, this.size);
    local_c.size = this.size;
    return local_c;
  }
  
  public boolean equals(CharArrayList local_l)
  {
    if (local_l == this) {
      return true;
    }
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    char[] local_a1 = this.field_61;
    char[] local_a2 = local_l.field_61;
    while (local_s-- != 0) {
      if (local_a1[local_s] != local_a2[local_s]) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(CharArrayList local_l)
  {
    int local_s1 = size();
    int local_s2 = local_l.size();
    char[] local_a1 = this.field_61;
    char[] local_a2 = local_l.field_61;
    for (int local_i = 0; (local_i < local_s1) && (local_i < local_s2); local_i++)
    {
      char local_e1 = local_a1[local_i];
      char local_e2 = local_a2[local_i];
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
      local_s.writeChar(this.field_61[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_61 = new char[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_61[local_i] = local_s.readChar();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */