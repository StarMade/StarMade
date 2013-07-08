package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractCharBigList
  extends AbstractCharCollection
  implements CharBigList, CharStack
{
  protected void ensureIndex(long index)
  {
    if (index < 0L) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index > size64()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + size64() + ")");
    }
  }
  
  protected void ensureRestrictedIndex(long index)
  {
    if (index < 0L) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= size64()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + size64() + ")");
    }
  }
  
  public void add(long index, char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(char local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public char removeChar(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public char removeChar(int local_i)
  {
    return removeChar(local_i);
  }
  
  public char set(long index, char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public char set(int index, char local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Character> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Character> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Character)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(int index, Collection<? extends Character> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Character> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public CharBigListIterator iterator()
  {
    return listIterator();
  }
  
  public CharBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public CharBigListIterator listIterator(final long index)
  {
    new AbstractCharBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractCharBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public char nextChar()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractCharBigList.this.getChar(this.last = this.pos++);
      }
      
      public char previousChar()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractCharBigList.this.getChar(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(char local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractCharBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(char local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractCharBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractCharBigList.this.removeChar(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public CharBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(char local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(char local_k)
  {
    CharBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      char local_e = local_i.nextChar();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(char local_k)
  {
    CharBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      char local_e = local_i.previousChar();
      if (local_k == local_e) {
        return local_i.nextIndex();
      }
    }
    return -1L;
  }
  
  public void size(long size)
  {
    long local_i = size64();
    if (size > local_i) {
      while (local_i++ < size) {
        add('\000');
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public void size(int size)
  {
    size(size);
  }
  
  public CharBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new CharSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    CharBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextChar();
      local_i.remove();
    }
  }
  
  public void addElements(long index, char[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    CharBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, CharBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, char[][] local_a)
  {
    addElements(index, local_a, 0L, CharBigArrays.length(local_a));
  }
  
  public void getElements(long from, char[][] local_a, long offset, long length)
  {
    CharBigListIterator local_i = listIterator(from);
    CharBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      CharBigArrays.set(local_a, offset++, local_i.nextChar());
    }
  }
  
  @Deprecated
  public int size()
  {
    return (int)Math.min(2147483647L, size64());
  }
  
  private boolean valEquals(Object local_a, Object local_b)
  {
    return local_a == null ? false : local_b == null ? true : local_a.equals(local_b);
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (!(local_o instanceof BigList)) {
      return false;
    }
    BigList<?> local_l = (BigList)local_o;
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    BigListIterator<?> local_i1 = listIterator();
    BigListIterator<?> local_i2 = local_l.listIterator();
    while (local_s-- != 0L) {
      if (!valEquals(local_i1.next(), local_i2.next())) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(BigList<? extends Character> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof CharBigList))
    {
      CharBigListIterator local_i1 = listIterator();
      CharBigListIterator local_i2 = ((CharBigList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        char local_e1 = local_i1.nextChar();
        char local_e2 = local_i2.nextChar();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    BigListIterator<? extends Character> local_i1 = listIterator();
    BigListIterator<? extends Character> local_i2 = local_l.listIterator();
    while ((local_i1.hasNext()) && (local_i2.hasNext()))
    {
      int local_r;
      if ((local_r = ((Comparable)local_i1.next()).compareTo(local_i2.next())) != 0) {
        return local_r;
      }
    }
    return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
  }
  
  public int hashCode()
  {
    CharIterator local_i = iterator();
    int local_h = 1;
    long local_s = size64();
    while (local_s-- != 0L)
    {
      char local_k = local_i.nextChar();
      local_h = 31 * local_h + local_k;
    }
    return local_h;
  }
  
  public void push(char local_o)
  {
    add(local_o);
  }
  
  public char popChar()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeChar(size64() - 1L);
  }
  
  public char topChar()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getChar(size64() - 1L);
  }
  
  public char peekChar(int local_i)
  {
    return getChar(size64() - 1L - local_i);
  }
  
  public char getChar(int index)
  {
    return getChar(index);
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
  
  public boolean addAll(long index, CharCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, CharBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(CharCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(CharBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Character local_ok)
  {
    add(index, local_ok.charValue());
  }
  
  public Character set(long index, Character local_ok)
  {
    return Character.valueOf(set(index, local_ok.charValue()));
  }
  
  public Character get(long index)
  {
    return Character.valueOf(getChar(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Character)local_ok).charValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Character)local_ok).charValue());
  }
  
  public Character remove(int index)
  {
    return Character.valueOf(removeChar(index));
  }
  
  public Character remove(long index)
  {
    return Character.valueOf(removeChar(index));
  }
  
  public void push(Character local_o)
  {
    push(local_o.charValue());
  }
  
  public Character pop()
  {
    return Character.valueOf(popChar());
  }
  
  public Character top()
  {
    return Character.valueOf(topChar());
  }
  
  public Character peek(int local_i)
  {
    return Character.valueOf(peekChar(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    CharIterator local_i = iterator();
    long local_n = size64();
    boolean first = true;
    local_s.append("[");
    while (local_n-- != 0L)
    {
      if (first) {
        first = false;
      } else {
        local_s.append(", ");
      }
      char local_k = local_i.nextChar();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class CharSubList
    extends AbstractCharBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharBigList field_308;
    protected final long from;
    protected long field_309;
    private static final boolean ASSERTS = false;
    
    public CharSubList(CharBigList local_l, long from, long local_to)
    {
      this.field_308 = local_l;
      this.from = from;
      this.field_309 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(char local_k)
    {
      this.field_308.add(this.field_309, local_k);
      this.field_309 += 1L;
      return true;
    }
    
    public void add(long index, char local_k)
    {
      ensureIndex(index);
      this.field_308.add(this.from + index, local_k);
      this.field_309 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Character> local_c)
    {
      ensureIndex(index);
      this.field_309 += local_c.size();
      return this.field_308.addAll(this.from + index, local_c);
    }
    
    public char getChar(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_308.getChar(this.from + index);
    }
    
    public char removeChar(long index)
    {
      ensureRestrictedIndex(index);
      this.field_309 -= 1L;
      return this.field_308.removeChar(this.from + index);
    }
    
    public char set(long index, char local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_308.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_309 - this.from;
    }
    
    public void getElements(long from, char[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_308.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_308.removeElements(this.from + from, this.from + local_to);
      this.field_309 -= local_to - from;
    }
    
    public void addElements(long index, char[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_308.addElements(this.from + index, local_a, offset, length);
      this.field_309 += length;
    }
    
    public CharBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractCharBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractCharBigList.CharSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public char nextChar()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractCharBigList.CharSubList.this.field_308.getChar(AbstractCharBigList.CharSubList.this.from + (this.last = this.pos++));
        }
        
        public char previousChar()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractCharBigList.CharSubList.this.field_308.getChar(AbstractCharBigList.CharSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(char local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractCharBigList.CharSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(char local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractCharBigList.CharSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractCharBigList.CharSubList.this.removeChar(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public CharBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new CharSubList(this, from, local_to);
    }
    
    public boolean rem(char local_k)
    {
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_309 -= 1L;
      this.field_308.removeChar(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Character)local_o).charValue());
    }
    
    public boolean addAll(long index, CharCollection local_c)
    {
      ensureIndex(index);
      this.field_309 += local_c.size();
      return this.field_308.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, CharList local_l)
    {
      ensureIndex(index);
      this.field_309 += local_l.size();
      return this.field_308.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */