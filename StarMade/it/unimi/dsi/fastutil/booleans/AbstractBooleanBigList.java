package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractBooleanBigList
  extends AbstractBooleanCollection
  implements BooleanBigList, BooleanStack
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
  
  public void add(long index, boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(boolean local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public boolean removeBoolean(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeBoolean(int local_i)
  {
    return removeBoolean(local_i);
  }
  
  public boolean set(long index, boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean set(int index, boolean local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Boolean> local_c)
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
  
  public boolean addAll(int index, Collection<? extends Boolean> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Boolean> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public BooleanBigListIterator iterator()
  {
    return listIterator();
  }
  
  public BooleanBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public BooleanBigListIterator listIterator(final long index)
  {
    new AbstractBooleanBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractBooleanBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public boolean nextBoolean()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractBooleanBigList.this.getBoolean(this.last = this.pos++);
      }
      
      public boolean previousBoolean()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractBooleanBigList.this.getBoolean(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(boolean local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractBooleanBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(boolean local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractBooleanBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractBooleanBigList.this.removeBoolean(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public BooleanBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(boolean local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(boolean local_k)
  {
    BooleanBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      boolean local_e = local_i.nextBoolean();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(boolean local_k)
  {
    BooleanBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      boolean local_e = local_i.previousBoolean();
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
        add(false);
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
  
  public BooleanBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new BooleanSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    BooleanBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextBoolean();
      local_i.remove();
    }
  }
  
  public void addElements(long index, boolean[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    BooleanBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, BooleanBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, boolean[][] local_a)
  {
    addElements(index, local_a, 0L, BooleanBigArrays.length(local_a));
  }
  
  public void getElements(long from, boolean[][] local_a, long offset, long length)
  {
    BooleanBigListIterator local_i = listIterator(from);
    BooleanBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      BooleanBigArrays.set(local_a, offset++, local_i.nextBoolean());
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
  
  public int compareTo(BigList<? extends Boolean> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof BooleanBigList))
    {
      BooleanBigListIterator local_i1 = listIterator();
      BooleanBigListIterator local_i2 = ((BooleanBigList)local_l).listIterator();
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
    BigListIterator<? extends Boolean> local_i1 = listIterator();
    BigListIterator<? extends Boolean> local_i2 = local_l.listIterator();
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
    long local_s = size64();
    while (local_s-- != 0L)
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
    return removeBoolean(size64() - 1L);
  }
  
  public boolean topBoolean()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getBoolean(size64() - 1L);
  }
  
  public boolean peekBoolean(int local_i)
  {
    return getBoolean(size64() - 1L - local_i);
  }
  
  public boolean getBoolean(int index)
  {
    return getBoolean(index);
  }
  
  public boolean rem(boolean local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeBoolean(index);
    return true;
  }
  
  public boolean addAll(long index, BooleanCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, BooleanBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(BooleanCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(BooleanBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Boolean local_ok)
  {
    add(index, local_ok.booleanValue());
  }
  
  public Boolean set(long index, Boolean local_ok)
  {
    return Boolean.valueOf(set(index, local_ok.booleanValue()));
  }
  
  public Boolean get(long index)
  {
    return Boolean.valueOf(getBoolean(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Boolean)local_ok).booleanValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Boolean)local_ok).booleanValue());
  }
  
  public Boolean remove(int index)
  {
    return Boolean.valueOf(removeBoolean(index));
  }
  
  public Boolean remove(long index)
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
      boolean local_k = local_i.nextBoolean();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class BooleanSubList
    extends AbstractBooleanBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanBigList field_395;
    protected final long from;
    protected long field_396;
    private static final boolean ASSERTS = false;
    
    public BooleanSubList(BooleanBigList local_l, long from, long local_to)
    {
      this.field_395 = local_l;
      this.from = from;
      this.field_396 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(boolean local_k)
    {
      this.field_395.add(this.field_396, local_k);
      this.field_396 += 1L;
      return true;
    }
    
    public void add(long index, boolean local_k)
    {
      ensureIndex(index);
      this.field_395.add(this.from + index, local_k);
      this.field_396 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Boolean> local_c)
    {
      ensureIndex(index);
      this.field_396 += local_c.size();
      return this.field_395.addAll(this.from + index, local_c);
    }
    
    public boolean getBoolean(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_395.getBoolean(this.from + index);
    }
    
    public boolean removeBoolean(long index)
    {
      ensureRestrictedIndex(index);
      this.field_396 -= 1L;
      return this.field_395.removeBoolean(this.from + index);
    }
    
    public boolean set(long index, boolean local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_395.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_396 - this.from;
    }
    
    public void getElements(long from, boolean[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_395.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_395.removeElements(this.from + from, this.from + local_to);
      this.field_396 -= local_to - from;
    }
    
    public void addElements(long index, boolean[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_395.addElements(this.from + index, local_a, offset, length);
      this.field_396 += length;
    }
    
    public BooleanBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractBooleanBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractBooleanBigList.BooleanSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public boolean nextBoolean()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractBooleanBigList.BooleanSubList.this.field_395.getBoolean(AbstractBooleanBigList.BooleanSubList.this.from + (this.last = this.pos++));
        }
        
        public boolean previousBoolean()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractBooleanBigList.BooleanSubList.this.field_395.getBoolean(AbstractBooleanBigList.BooleanSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(boolean local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractBooleanBigList.BooleanSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(boolean local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractBooleanBigList.BooleanSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractBooleanBigList.BooleanSubList.this.removeBoolean(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public BooleanBigList subList(long from, long local_to)
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
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_396 -= 1L;
      this.field_395.removeBoolean(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Boolean)local_o).booleanValue());
    }
    
    public boolean addAll(long index, BooleanCollection local_c)
    {
      ensureIndex(index);
      this.field_396 += local_c.size();
      return this.field_395.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, BooleanList local_l)
    {
      ensureIndex(index);
      this.field_396 += local_l.size();
      return this.field_395.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */