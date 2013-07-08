package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractCharList
  extends AbstractCharCollection
  implements CharList, CharStack
{
  protected void ensureIndex(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index > size()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + size() + ")");
    }
  }
  
  protected void ensureRestrictedIndex(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + size() + ")");
    }
  }
  
  public void add(int index, char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(char local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public char removeChar(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public char set(int index, char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Character> local_c)
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
  
  public boolean addAll(Collection<? extends Character> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public CharListIterator charListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public CharListIterator charListIterator(int index)
  {
    return listIterator(index);
  }
  
  public CharListIterator iterator()
  {
    return listIterator();
  }
  
  public CharListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public CharListIterator listIterator(final int index)
  {
    new AbstractCharListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractCharList.this.size();
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
        return AbstractCharList.this.getChar(this.last = this.pos++);
      }
      
      public char previousChar()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractCharList.this.getChar(this.last = --this.pos);
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
        AbstractCharList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(char local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractCharList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractCharList.this.removeChar(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(char local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(char local_k)
  {
    CharListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      char local_e = local_i.nextChar();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(char local_k)
  {
    CharListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      char local_e = local_i.previousChar();
      if (local_k == local_e) {
        return local_i.nextIndex();
      }
    }
    return -1;
  }
  
  public void size(int size)
  {
    int local_i = size();
    if (size > local_i) {
      while (local_i++ < size) {
        add('\000');
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public CharList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new CharSubList(this, from, local_to);
  }
  
  @Deprecated
  public CharList charSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    CharListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextChar();
      local_i.remove();
    }
  }
  
  public void addElements(int index, char[] local_a, int offset, int length)
  {
    ensureIndex(index);
    if (offset < 0) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (offset + length > local_a.length) {
      throw new ArrayIndexOutOfBoundsException("End index (" + (offset + length) + ") is greater than array length (" + local_a.length + ")");
    }
    while (length-- != 0) {
      add(index++, local_a[(offset++)]);
    }
  }
  
  public void addElements(int index, char[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, char[] local_a, int offset, int length)
  {
    CharListIterator local_i = listIterator(from);
    if (offset < 0) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (offset + length > local_a.length) {
      throw new ArrayIndexOutOfBoundsException("End index (" + (offset + length) + ") is greater than array length (" + local_a.length + ")");
    }
    if (from + length > size()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size() + ")");
    }
    while (length-- != 0) {
      local_a[(offset++)] = local_i.nextChar();
    }
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
    if (!(local_o instanceof List)) {
      return false;
    }
    List<?> local_l = (List)local_o;
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    ListIterator<?> local_i1 = listIterator();
    ListIterator<?> local_i2 = local_l.listIterator();
    while (local_s-- != 0) {
      if (!valEquals(local_i1.next(), local_i2.next())) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(List<? extends Character> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof CharList))
    {
      CharListIterator local_i1 = listIterator();
      CharListIterator local_i2 = ((CharList)local_l).listIterator();
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
    ListIterator<? extends Character> local_i1 = listIterator();
    ListIterator<? extends Character> local_i2 = local_l.listIterator();
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
    int local_s = size();
    while (local_s-- != 0)
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
    return removeChar(size() - 1);
  }
  
  public char topChar()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getChar(size() - 1);
  }
  
  public char peekChar(int local_i)
  {
    return getChar(size() - 1 - local_i);
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
  
  public boolean remove(Object local_o)
  {
    return rem(((Character)local_o).charValue());
  }
  
  public boolean addAll(int index, CharCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, CharList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(CharCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(CharList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Character local_ok)
  {
    add(index, local_ok.charValue());
  }
  
  public Character set(int index, Character local_ok)
  {
    return Character.valueOf(set(index, local_ok.charValue()));
  }
  
  public Character get(int index)
  {
    return Character.valueOf(getChar(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Character)local_ok).charValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Character)local_ok).charValue());
  }
  
  public Character remove(int index)
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
    int local_n = size();
    boolean first = true;
    local_s.append("[");
    while (local_n-- != 0)
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
    extends AbstractCharList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharList field_308;
    protected final int from;
    protected int field_309;
    private static final boolean ASSERTS = false;
    
    public CharSubList(CharList local_l, int from, int local_to)
    {
      this.field_308 = local_l;
      this.from = from;
      this.field_309 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(char local_k)
    {
      this.field_308.add(this.field_309, local_k);
      this.field_309 += 1;
      return true;
    }
    
    public void add(int index, char local_k)
    {
      ensureIndex(index);
      this.field_308.add(this.from + index, local_k);
      this.field_309 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Character> local_c)
    {
      ensureIndex(index);
      this.field_309 += local_c.size();
      return this.field_308.addAll(this.from + index, local_c);
    }
    
    public char getChar(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_308.getChar(this.from + index);
    }
    
    public char removeChar(int index)
    {
      ensureRestrictedIndex(index);
      this.field_309 -= 1;
      return this.field_308.removeChar(this.from + index);
    }
    
    public char set(int index, char local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_308.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_309 - this.from;
    }
    
    public void getElements(int from, char[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_308.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_308.removeElements(this.from + from, this.from + local_to);
      this.field_309 -= local_to - from;
    }
    
    public void addElements(int index, char[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_308.addElements(this.from + index, local_a, offset, length);
      this.field_309 += length;
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
          return this.pos < AbstractCharList.CharSubList.this.size();
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
          return AbstractCharList.CharSubList.this.field_308.getChar(AbstractCharList.CharSubList.this.from + (this.last = this.pos++));
        }
        
        public char previousChar()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractCharList.CharSubList.this.field_308.getChar(AbstractCharList.CharSubList.this.from + (this.last = --this.pos));
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
          AbstractCharList.CharSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(char local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractCharList.CharSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractCharList.CharSubList.this.removeChar(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public CharList subList(int from, int local_to)
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
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_309 -= 1;
      this.field_308.removeChar(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Character)local_o).charValue());
    }
    
    public boolean addAll(int index, CharCollection local_c)
    {
      ensureIndex(index);
      this.field_309 += local_c.size();
      return this.field_308.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, CharList local_l)
    {
      ensureIndex(index);
      this.field_309 += local_l.size();
      return this.field_308.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */