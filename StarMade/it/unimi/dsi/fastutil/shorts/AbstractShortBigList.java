package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractShortBigList
  extends AbstractShortCollection
  implements ShortBigList, ShortStack
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
  
  public void add(long index, short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(short local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public short removeShort(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public short removeShort(int local_i)
  {
    return removeShort(local_i);
  }
  
  public short set(long index, short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public short set(int index, short local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Short> local_c)
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
  
  public boolean addAll(int index, Collection<? extends Short> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Short> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public ShortBigListIterator iterator()
  {
    return listIterator();
  }
  
  public ShortBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public ShortBigListIterator listIterator(final long index)
  {
    new AbstractShortBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractShortBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public short nextShort()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractShortBigList.this.getShort(this.last = this.pos++);
      }
      
      public short previousShort()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractShortBigList.this.getShort(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(short local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractShortBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(short local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractShortBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractShortBigList.this.removeShort(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public ShortBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(short local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(short local_k)
  {
    ShortBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      short local_e = local_i.nextShort();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(short local_k)
  {
    ShortBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      short local_e = local_i.previousShort();
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
        add((short)0);
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
  
  public ShortBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ShortSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    ShortBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextShort();
      local_i.remove();
    }
  }
  
  public void addElements(long index, short[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ShortBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, ShortBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, short[][] local_a)
  {
    addElements(index, local_a, 0L, ShortBigArrays.length(local_a));
  }
  
  public void getElements(long from, short[][] local_a, long offset, long length)
  {
    ShortBigListIterator local_i = listIterator(from);
    ShortBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      ShortBigArrays.set(local_a, offset++, local_i.nextShort());
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
  
  public int compareTo(BigList<? extends Short> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof ShortBigList))
    {
      ShortBigListIterator local_i1 = listIterator();
      ShortBigListIterator local_i2 = ((ShortBigList)local_l).listIterator();
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
    BigListIterator<? extends Short> local_i1 = listIterator();
    BigListIterator<? extends Short> local_i2 = local_l.listIterator();
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
    long local_s = size64();
    while (local_s-- != 0L)
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
    return removeShort(size64() - 1L);
  }
  
  public short topShort()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getShort(size64() - 1L);
  }
  
  public short peekShort(int local_i)
  {
    return getShort(size64() - 1L - local_i);
  }
  
  public short getShort(int index)
  {
    return getShort(index);
  }
  
  public boolean rem(short local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeShort(index);
    return true;
  }
  
  public boolean addAll(long index, ShortCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, ShortBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(ShortCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(ShortBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Short local_ok)
  {
    add(index, local_ok.shortValue());
  }
  
  public Short set(long index, Short local_ok)
  {
    return Short.valueOf(set(index, local_ok.shortValue()));
  }
  
  public Short get(long index)
  {
    return Short.valueOf(getShort(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Short)local_ok).shortValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Short)local_ok).shortValue());
  }
  
  public Short remove(int index)
  {
    return Short.valueOf(removeShort(index));
  }
  
  public Short remove(long index)
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
      short local_k = local_i.nextShort();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class ShortSubList
    extends AbstractShortBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortBigList field_389;
    protected final long from;
    protected long field_339;
    private static final boolean ASSERTS = false;
    
    public ShortSubList(ShortBigList local_l, long from, long local_to)
    {
      this.field_389 = local_l;
      this.from = from;
      this.field_339 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(short local_k)
    {
      this.field_389.add(this.field_339, local_k);
      this.field_339 += 1L;
      return true;
    }
    
    public void add(long index, short local_k)
    {
      ensureIndex(index);
      this.field_389.add(this.from + index, local_k);
      this.field_339 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Short> local_c)
    {
      ensureIndex(index);
      this.field_339 += local_c.size();
      return this.field_389.addAll(this.from + index, local_c);
    }
    
    public short getShort(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_389.getShort(this.from + index);
    }
    
    public short removeShort(long index)
    {
      ensureRestrictedIndex(index);
      this.field_339 -= 1L;
      return this.field_389.removeShort(this.from + index);
    }
    
    public short set(long index, short local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_389.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_339 - this.from;
    }
    
    public void getElements(long from, short[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_389.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_389.removeElements(this.from + from, this.from + local_to);
      this.field_339 -= local_to - from;
    }
    
    public void addElements(long index, short[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_389.addElements(this.from + index, local_a, offset, length);
      this.field_339 += length;
    }
    
    public ShortBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractShortBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractShortBigList.ShortSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public short nextShort()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractShortBigList.ShortSubList.this.field_389.getShort(AbstractShortBigList.ShortSubList.this.from + (this.last = this.pos++));
        }
        
        public short previousShort()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractShortBigList.ShortSubList.this.field_389.getShort(AbstractShortBigList.ShortSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(short local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractShortBigList.ShortSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(short local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractShortBigList.ShortSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractShortBigList.ShortSubList.this.removeShort(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public ShortBigList subList(long from, long local_to)
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
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_339 -= 1L;
      this.field_389.removeShort(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Short)local_o).shortValue());
    }
    
    public boolean addAll(long index, ShortCollection local_c)
    {
      ensureIndex(index);
      this.field_339 += local_c.size();
      return this.field_389.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, ShortList local_l)
    {
      ensureIndex(index);
      this.field_339 += local_l.size();
      return this.field_389.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */