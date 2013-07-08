package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractIntBigList
  extends AbstractIntCollection
  implements IntBigList, IntStack
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
  
  public void add(long index, int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(int local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public int removeInt(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public int removeInt(int local_i)
  {
    return removeInt(local_i);
  }
  
  public int set(long index, int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public int set(int index, int local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Integer> local_c)
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
  
  public boolean addAll(int index, Collection<? extends Integer> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Integer> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public IntBigListIterator iterator()
  {
    return listIterator();
  }
  
  public IntBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public IntBigListIterator listIterator(final long index)
  {
    new AbstractIntBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractIntBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public int nextInt()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractIntBigList.this.getInt(this.last = this.pos++);
      }
      
      public int previousInt()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractIntBigList.this.getInt(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(int local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractIntBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(int local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractIntBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractIntBigList.this.removeInt(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public IntBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(int local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(int local_k)
  {
    IntBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      int local_e = local_i.nextInt();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(int local_k)
  {
    IntBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      int local_e = local_i.previousInt();
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
        add(0);
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
  
  public IntBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new IntSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    IntBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextInt();
      local_i.remove();
    }
  }
  
  public void addElements(long index, int[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    IntBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, IntBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, int[][] local_a)
  {
    addElements(index, local_a, 0L, IntBigArrays.length(local_a));
  }
  
  public void getElements(long from, int[][] local_a, long offset, long length)
  {
    IntBigListIterator local_i = listIterator(from);
    IntBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      IntBigArrays.set(local_a, offset++, local_i.nextInt());
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
  
  public int compareTo(BigList<? extends Integer> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof IntBigList))
    {
      IntBigListIterator local_i1 = listIterator();
      IntBigListIterator local_i2 = ((IntBigList)local_l).listIterator();
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
    BigListIterator<? extends Integer> local_i1 = listIterator();
    BigListIterator<? extends Integer> local_i2 = local_l.listIterator();
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
    long local_s = size64();
    while (local_s-- != 0L)
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
    return removeInt(size64() - 1L);
  }
  
  public int topInt()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getInt(size64() - 1L);
  }
  
  public int peekInt(int local_i)
  {
    return getInt(size64() - 1L - local_i);
  }
  
  public int getInt(int index)
  {
    return getInt(index);
  }
  
  public boolean rem(int local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeInt(index);
    return true;
  }
  
  public boolean addAll(long index, IntCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, IntBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(IntCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(IntBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Integer local_ok)
  {
    add(index, local_ok.intValue());
  }
  
  public Integer set(long index, Integer local_ok)
  {
    return Integer.valueOf(set(index, local_ok.intValue()));
  }
  
  public Integer get(long index)
  {
    return Integer.valueOf(getInt(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Integer)local_ok).intValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Integer)local_ok).intValue());
  }
  
  public Integer remove(int index)
  {
    return Integer.valueOf(removeInt(index));
  }
  
  public Integer remove(long index)
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
      int local_k = local_i.nextInt();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class IntSubList
    extends AbstractIntBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntBigList field_411;
    protected final long from;
    protected long field_328;
    private static final boolean ASSERTS = false;
    
    public IntSubList(IntBigList local_l, long from, long local_to)
    {
      this.field_411 = local_l;
      this.from = from;
      this.field_328 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(int local_k)
    {
      this.field_411.add(this.field_328, local_k);
      this.field_328 += 1L;
      return true;
    }
    
    public void add(long index, int local_k)
    {
      ensureIndex(index);
      this.field_411.add(this.from + index, local_k);
      this.field_328 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Integer> local_c)
    {
      ensureIndex(index);
      this.field_328 += local_c.size();
      return this.field_411.addAll(this.from + index, local_c);
    }
    
    public int getInt(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_411.getInt(this.from + index);
    }
    
    public int removeInt(long index)
    {
      ensureRestrictedIndex(index);
      this.field_328 -= 1L;
      return this.field_411.removeInt(this.from + index);
    }
    
    public int set(long index, int local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_411.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_328 - this.from;
    }
    
    public void getElements(long from, int[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_411.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_411.removeElements(this.from + from, this.from + local_to);
      this.field_328 -= local_to - from;
    }
    
    public void addElements(long index, int[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_411.addElements(this.from + index, local_a, offset, length);
      this.field_328 += length;
    }
    
    public IntBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractIntBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractIntBigList.IntSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public int nextInt()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractIntBigList.IntSubList.this.field_411.getInt(AbstractIntBigList.IntSubList.this.from + (this.last = this.pos++));
        }
        
        public int previousInt()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractIntBigList.IntSubList.this.field_411.getInt(AbstractIntBigList.IntSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(int local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractIntBigList.IntSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(int local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractIntBigList.IntSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractIntBigList.IntSubList.this.removeInt(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public IntBigList subList(long from, long local_to)
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
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_328 -= 1L;
      this.field_411.removeInt(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Integer)local_o).intValue());
    }
    
    public boolean addAll(long index, IntCollection local_c)
    {
      ensureIndex(index);
      this.field_328 += local_c.size();
      return this.field_411.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, IntList local_l)
    {
      ensureIndex(index);
      this.field_328 += local_l.size();
      return this.field_411.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */