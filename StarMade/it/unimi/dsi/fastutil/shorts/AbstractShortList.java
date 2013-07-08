package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractShortList
  extends AbstractShortCollection
  implements ShortList, ShortStack
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
  
  public void add(int index, short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(short local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public short removeShort(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public short set(int index, short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Short> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Short> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Short)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(Collection<? extends Short> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public ShortListIterator shortListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public ShortListIterator shortListIterator(int index)
  {
    return listIterator(index);
  }
  
  public ShortListIterator iterator()
  {
    return listIterator();
  }
  
  public ShortListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public ShortListIterator listIterator(final int index)
  {
    new AbstractShortListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractShortList.this.size();
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
        return AbstractShortList.this.getShort(this.last = this.pos++);
      }
      
      public short previousShort()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractShortList.this.getShort(this.last = --this.pos);
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
        AbstractShortList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(short local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractShortList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractShortList.this.removeShort(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(short local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(short local_k)
  {
    ShortListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      short local_e = local_i.nextShort();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(short local_k)
  {
    ShortListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      short local_e = local_i.previousShort();
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
        add((short)0);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public ShortList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ShortSubList(this, from, local_to);
  }
  
  @Deprecated
  public ShortList shortSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    ShortListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextShort();
      local_i.remove();
    }
  }
  
  public void addElements(int index, short[] local_a, int offset, int length)
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
  
  public void addElements(int index, short[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, short[] local_a, int offset, int length)
  {
    ShortListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextShort();
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
  
  public int compareTo(List<? extends Short> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof ShortList))
    {
      ShortListIterator local_i1 = listIterator();
      ShortListIterator local_i2 = ((ShortList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        short local_e1 = local_i1.nextShort();
        short local_e2 = local_i2.nextShort();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    ListIterator<? extends Short> local_i1 = listIterator();
    ListIterator<? extends Short> local_i2 = local_l.listIterator();
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
    ShortIterator local_i = iterator();
    int local_h = 1;
    int local_s = size();
    while (local_s-- != 0)
    {
      short local_k = local_i.nextShort();
      local_h = 31 * local_h + local_k;
    }
    return local_h;
  }
  
  public void push(short local_o)
  {
    add(local_o);
  }
  
  public short popShort()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeShort(size() - 1);
  }
  
  public short topShort()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getShort(size() - 1);
  }
  
  public short peekShort(int local_i)
  {
    return getShort(size() - 1 - local_i);
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
  
  public boolean remove(Object local_o)
  {
    return rem(((Short)local_o).shortValue());
  }
  
  public boolean addAll(int index, ShortCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, ShortList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(ShortCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(ShortList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Short local_ok)
  {
    add(index, local_ok.shortValue());
  }
  
  public Short set(int index, Short local_ok)
  {
    return Short.valueOf(set(index, local_ok.shortValue()));
  }
  
  public Short get(int index)
  {
    return Short.valueOf(getShort(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Short)local_ok).shortValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Short)local_ok).shortValue());
  }
  
  public Short remove(int index)
  {
    return Short.valueOf(removeShort(index));
  }
  
  public void push(Short local_o)
  {
    push(local_o.shortValue());
  }
  
  public Short pop()
  {
    return Short.valueOf(popShort());
  }
  
  public Short top()
  {
    return Short.valueOf(topShort());
  }
  
  public Short peek(int local_i)
  {
    return Short.valueOf(peekShort(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ShortIterator local_i = iterator();
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
      short local_k = local_i.nextShort();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class ShortSubList
    extends AbstractShortList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortList field_389;
    protected final int from;
    protected int field_339;
    private static final boolean ASSERTS = false;
    
    public ShortSubList(ShortList local_l, int from, int local_to)
    {
      this.field_389 = local_l;
      this.from = from;
      this.field_339 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(short local_k)
    {
      this.field_389.add(this.field_339, local_k);
      this.field_339 += 1;
      return true;
    }
    
    public void add(int index, short local_k)
    {
      ensureIndex(index);
      this.field_389.add(this.from + index, local_k);
      this.field_339 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Short> local_c)
    {
      ensureIndex(index);
      this.field_339 += local_c.size();
      return this.field_389.addAll(this.from + index, local_c);
    }
    
    public short getShort(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_389.getShort(this.from + index);
    }
    
    public short removeShort(int index)
    {
      ensureRestrictedIndex(index);
      this.field_339 -= 1;
      return this.field_389.removeShort(this.from + index);
    }
    
    public short set(int index, short local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_389.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_339 - this.from;
    }
    
    public void getElements(int from, short[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_389.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_389.removeElements(this.from + from, this.from + local_to);
      this.field_339 -= local_to - from;
    }
    
    public void addElements(int index, short[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_389.addElements(this.from + index, local_a, offset, length);
      this.field_339 += length;
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
          return this.pos < AbstractShortList.ShortSubList.this.size();
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
          return AbstractShortList.ShortSubList.this.field_389.getShort(AbstractShortList.ShortSubList.this.from + (this.last = this.pos++));
        }
        
        public short previousShort()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractShortList.ShortSubList.this.field_389.getShort(AbstractShortList.ShortSubList.this.from + (this.last = --this.pos));
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
          AbstractShortList.ShortSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(short local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractShortList.ShortSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractShortList.ShortSubList.this.removeShort(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public ShortList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new ShortSubList(this, from, local_to);
    }
    
    public boolean rem(short local_k)
    {
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_339 -= 1;
      this.field_389.removeShort(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Short)local_o).shortValue());
    }
    
    public boolean addAll(int index, ShortCollection local_c)
    {
      ensureIndex(index);
      this.field_339 += local_c.size();
      return this.field_389.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, ShortList local_l)
    {
      ensureIndex(index);
      this.field_339 += local_l.size();
      return this.field_389.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */