package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractIntList
  extends AbstractIntCollection
  implements IntList, IntStack
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
  
  public void add(int index, int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(int local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public int removeInt(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public int set(int index, int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Integer> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Integer> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Integer)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(Collection<? extends Integer> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public IntListIterator intListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public IntListIterator intListIterator(int index)
  {
    return listIterator(index);
  }
  
  public IntListIterator iterator()
  {
    return listIterator();
  }
  
  public IntListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public IntListIterator listIterator(final int index)
  {
    new AbstractIntListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractIntList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public int nextInt()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractIntList.this.getInt(this.last = this.pos++);
      }
      
      public int previousInt()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractIntList.this.getInt(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractIntList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(int local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractIntList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractIntList.this.removeInt(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(int local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(int local_k)
  {
    IntListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      int local_e = local_i.nextInt();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(int local_k)
  {
    IntListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      int local_e = local_i.previousInt();
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
        add(0);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public IntList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new IntSubList(this, from, local_to);
  }
  
  @Deprecated
  public IntList intSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    IntListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextInt();
      local_i.remove();
    }
  }
  
  public void addElements(int index, int[] local_a, int offset, int length)
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
  
  public void addElements(int index, int[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, int[] local_a, int offset, int length)
  {
    IntListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextInt();
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
  
  public int compareTo(List<? extends Integer> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof IntList))
    {
      IntListIterator local_i1 = listIterator();
      IntListIterator local_i2 = ((IntList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        int local_e1 = local_i1.nextInt();
        int local_e2 = local_i2.nextInt();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    ListIterator<? extends Integer> local_i1 = listIterator();
    ListIterator<? extends Integer> local_i2 = local_l.listIterator();
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
    IntIterator local_i = iterator();
    int local_h = 1;
    int local_s = size();
    while (local_s-- != 0)
    {
      int local_k = local_i.nextInt();
      local_h = 31 * local_h + local_k;
    }
    return local_h;
  }
  
  public void push(int local_o)
  {
    add(local_o);
  }
  
  public int popInt()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeInt(size() - 1);
  }
  
  public int topInt()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getInt(size() - 1);
  }
  
  public int peekInt(int local_i)
  {
    return getInt(size() - 1 - local_i);
  }
  
  public boolean rem(int local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeInt(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Integer)local_o).intValue());
  }
  
  public boolean addAll(int index, IntCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, IntList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(IntCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(IntList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Integer local_ok)
  {
    add(index, local_ok.intValue());
  }
  
  public Integer set(int index, Integer local_ok)
  {
    return Integer.valueOf(set(index, local_ok.intValue()));
  }
  
  public Integer get(int index)
  {
    return Integer.valueOf(getInt(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Integer)local_ok).intValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Integer)local_ok).intValue());
  }
  
  public Integer remove(int index)
  {
    return Integer.valueOf(removeInt(index));
  }
  
  public void push(Integer local_o)
  {
    push(local_o.intValue());
  }
  
  public Integer pop()
  {
    return Integer.valueOf(popInt());
  }
  
  public Integer top()
  {
    return Integer.valueOf(topInt());
  }
  
  public Integer peek(int local_i)
  {
    return Integer.valueOf(peekInt(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    IntIterator local_i = iterator();
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
      int local_k = local_i.nextInt();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class IntSubList
    extends AbstractIntList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntList field_411;
    protected final int from;
    protected int field_328;
    private static final boolean ASSERTS = false;
    
    public IntSubList(IntList local_l, int from, int local_to)
    {
      this.field_411 = local_l;
      this.from = from;
      this.field_328 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(int local_k)
    {
      this.field_411.add(this.field_328, local_k);
      this.field_328 += 1;
      return true;
    }
    
    public void add(int index, int local_k)
    {
      ensureIndex(index);
      this.field_411.add(this.from + index, local_k);
      this.field_328 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Integer> local_c)
    {
      ensureIndex(index);
      this.field_328 += local_c.size();
      return this.field_411.addAll(this.from + index, local_c);
    }
    
    public int getInt(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_411.getInt(this.from + index);
    }
    
    public int removeInt(int index)
    {
      ensureRestrictedIndex(index);
      this.field_328 -= 1;
      return this.field_411.removeInt(this.from + index);
    }
    
    public int set(int index, int local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_411.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_328 - this.from;
    }
    
    public void getElements(int from, int[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_411.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_411.removeElements(this.from + from, this.from + local_to);
      this.field_328 -= local_to - from;
    }
    
    public void addElements(int index, int[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_411.addElements(this.from + index, local_a, offset, length);
      this.field_328 += length;
    }
    
    public IntListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractIntListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractIntList.IntSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public int nextInt()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractIntList.IntSubList.this.field_411.getInt(AbstractIntList.IntSubList.this.from + (this.last = this.pos++));
        }
        
        public int previousInt()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractIntList.IntSubList.this.field_411.getInt(AbstractIntList.IntSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(int local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractIntList.IntSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(int local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractIntList.IntSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractIntList.IntSubList.this.removeInt(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public IntList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new IntSubList(this, from, local_to);
    }
    
    public boolean rem(int local_k)
    {
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_328 -= 1;
      this.field_411.removeInt(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Integer)local_o).intValue());
    }
    
    public boolean addAll(int index, IntCollection local_c)
    {
      ensureIndex(index);
      this.field_328 += local_c.size();
      return this.field_411.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, IntList local_l)
    {
      ensureIndex(index);
      this.field_328 += local_l.size();
      return this.field_411.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */