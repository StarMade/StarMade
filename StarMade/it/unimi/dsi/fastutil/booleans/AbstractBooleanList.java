package it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractBooleanList
  extends AbstractBooleanCollection
  implements BooleanList, BooleanStack
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
  
  public void add(int index, boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(boolean local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public boolean removeBoolean(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean set(int index, boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Boolean> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Boolean> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Boolean)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(Collection<? extends Boolean> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public BooleanListIterator booleanListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public BooleanListIterator booleanListIterator(int index)
  {
    return listIterator(index);
  }
  
  public BooleanListIterator iterator()
  {
    return listIterator();
  }
  
  public BooleanListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public BooleanListIterator listIterator(final int index)
  {
    new AbstractBooleanListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractBooleanList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public boolean nextBoolean()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractBooleanList.this.getBoolean(this.last = this.pos++);
      }
      
      public boolean previousBoolean()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractBooleanList.this.getBoolean(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractBooleanList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(boolean local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractBooleanList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractBooleanList.this.removeBoolean(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(boolean local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(boolean local_k)
  {
    BooleanListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      boolean local_e = local_i.nextBoolean();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(boolean local_k)
  {
    BooleanListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      boolean local_e = local_i.previousBoolean();
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
        add(false);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public BooleanList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new BooleanSubList(this, from, local_to);
  }
  
  @Deprecated
  public BooleanList booleanSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    BooleanListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextBoolean();
      local_i.remove();
    }
  }
  
  public void addElements(int index, boolean[] local_a, int offset, int length)
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
  
  public void addElements(int index, boolean[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, boolean[] local_a, int offset, int length)
  {
    BooleanListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextBoolean();
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
  
  public int compareTo(List<? extends Boolean> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof BooleanList))
    {
      BooleanListIterator local_i1 = listIterator();
      BooleanListIterator local_i2 = ((BooleanList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        boolean local_e1 = local_i1.nextBoolean();
        boolean local_e2 = local_i2.nextBoolean();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : (!local_e1) && (local_e2) ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    ListIterator<? extends Boolean> local_i1 = listIterator();
    ListIterator<? extends Boolean> local_i2 = local_l.listIterator();
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
    BooleanIterator local_i = iterator();
    int local_h = 1;
    int local_s = size();
    while (local_s-- != 0)
    {
      boolean local_k = local_i.nextBoolean();
      local_h = 31 * local_h + (local_k ? 1231 : 1237);
    }
    return local_h;
  }
  
  public void push(boolean local_o)
  {
    add(local_o);
  }
  
  public boolean popBoolean()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeBoolean(size() - 1);
  }
  
  public boolean topBoolean()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getBoolean(size() - 1);
  }
  
  public boolean peekBoolean(int local_i)
  {
    return getBoolean(size() - 1 - local_i);
  }
  
  public boolean rem(boolean local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeBoolean(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Boolean)local_o).booleanValue());
  }
  
  public boolean addAll(int index, BooleanCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, BooleanList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(BooleanCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(BooleanList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Boolean local_ok)
  {
    add(index, local_ok.booleanValue());
  }
  
  public Boolean set(int index, Boolean local_ok)
  {
    return Boolean.valueOf(set(index, local_ok.booleanValue()));
  }
  
  public Boolean get(int index)
  {
    return Boolean.valueOf(getBoolean(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Boolean)local_ok).booleanValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Boolean)local_ok).booleanValue());
  }
  
  public Boolean remove(int index)
  {
    return Boolean.valueOf(removeBoolean(index));
  }
  
  public void push(Boolean local_o)
  {
    push(local_o.booleanValue());
  }
  
  public Boolean pop()
  {
    return Boolean.valueOf(popBoolean());
  }
  
  public Boolean top()
  {
    return Boolean.valueOf(topBoolean());
  }
  
  public Boolean peek(int local_i)
  {
    return Boolean.valueOf(peekBoolean(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    BooleanIterator local_i = iterator();
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
      boolean local_k = local_i.nextBoolean();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class BooleanSubList
    extends AbstractBooleanList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanList field_395;
    protected final int from;
    protected int field_396;
    private static final boolean ASSERTS = false;
    
    public BooleanSubList(BooleanList local_l, int from, int local_to)
    {
      this.field_395 = local_l;
      this.from = from;
      this.field_396 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(boolean local_k)
    {
      this.field_395.add(this.field_396, local_k);
      this.field_396 += 1;
      return true;
    }
    
    public void add(int index, boolean local_k)
    {
      ensureIndex(index);
      this.field_395.add(this.from + index, local_k);
      this.field_396 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Boolean> local_c)
    {
      ensureIndex(index);
      this.field_396 += local_c.size();
      return this.field_395.addAll(this.from + index, local_c);
    }
    
    public boolean getBoolean(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_395.getBoolean(this.from + index);
    }
    
    public boolean removeBoolean(int index)
    {
      ensureRestrictedIndex(index);
      this.field_396 -= 1;
      return this.field_395.removeBoolean(this.from + index);
    }
    
    public boolean set(int index, boolean local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_395.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_396 - this.from;
    }
    
    public void getElements(int from, boolean[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_395.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_395.removeElements(this.from + from, this.from + local_to);
      this.field_396 -= local_to - from;
    }
    
    public void addElements(int index, boolean[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_395.addElements(this.from + index, local_a, offset, length);
      this.field_396 += length;
    }
    
    public BooleanListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractBooleanListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractBooleanList.BooleanSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public boolean nextBoolean()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractBooleanList.BooleanSubList.this.field_395.getBoolean(AbstractBooleanList.BooleanSubList.this.from + (this.last = this.pos++));
        }
        
        public boolean previousBoolean()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractBooleanList.BooleanSubList.this.field_395.getBoolean(AbstractBooleanList.BooleanSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(boolean local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractBooleanList.BooleanSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(boolean local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractBooleanList.BooleanSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractBooleanList.BooleanSubList.this.removeBoolean(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public BooleanList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new BooleanSubList(this, from, local_to);
    }
    
    public boolean rem(boolean local_k)
    {
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_396 -= 1;
      this.field_395.removeBoolean(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Boolean)local_o).booleanValue());
    }
    
    public boolean addAll(int index, BooleanCollection local_c)
    {
      ensureIndex(index);
      this.field_396 += local_c.size();
      return this.field_395.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, BooleanList local_l)
    {
      ensureIndex(index);
      this.field_396 += local_l.size();
      return this.field_395.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */