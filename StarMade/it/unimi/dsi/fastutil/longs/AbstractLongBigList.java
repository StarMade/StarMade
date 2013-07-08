package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractLongBigList
  extends AbstractLongCollection
  implements LongBigList, LongStack
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
  
  public void add(long index, long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(long local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public long removeLong(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public long removeLong(int local_i)
  {
    return removeLong(local_i);
  }
  
  public long set(long index, long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long set(int index, long local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Long> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Long> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Long)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(int index, Collection<? extends Long> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Long> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public LongBigListIterator iterator()
  {
    return listIterator();
  }
  
  public LongBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public LongBigListIterator listIterator(final long index)
  {
    new AbstractLongBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractLongBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public long nextLong()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractLongBigList.this.getLong(this.last = this.pos++);
      }
      
      public long previousLong()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractLongBigList.this.getLong(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(long local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractLongBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(long local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractLongBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractLongBigList.this.removeLong(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public LongBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(long local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(long local_k)
  {
    LongBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      long local_e = local_i.nextLong();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(long local_k)
  {
    LongBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      long local_e = local_i.previousLong();
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
        add(0L);
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
  
  public LongBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new LongSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    LongBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextLong();
      local_i.remove();
    }
  }
  
  public void addElements(long index, long[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    LongBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, LongBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, long[][] local_a)
  {
    addElements(index, local_a, 0L, LongBigArrays.length(local_a));
  }
  
  public void getElements(long from, long[][] local_a, long offset, long length)
  {
    LongBigListIterator local_i = listIterator(from);
    LongBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      LongBigArrays.set(local_a, offset++, local_i.nextLong());
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
  
  public int compareTo(BigList<? extends Long> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof LongBigList))
    {
      LongBigListIterator local_i1 = listIterator();
      LongBigListIterator local_i2 = ((LongBigList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        long local_e1 = local_i1.nextLong();
        long local_e2 = local_i2.nextLong();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    BigListIterator<? extends Long> local_i1 = listIterator();
    BigListIterator<? extends Long> local_i2 = local_l.listIterator();
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
    LongIterator local_i = iterator();
    int local_h = 1;
    long local_s = size64();
    while (local_s-- != 0L)
    {
      long local_k = local_i.nextLong();
      local_h = 31 * local_h + HashCommon.long2int(local_k);
    }
    return local_h;
  }
  
  public void push(long local_o)
  {
    add(local_o);
  }
  
  public long popLong()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeLong(size64() - 1L);
  }
  
  public long topLong()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getLong(size64() - 1L);
  }
  
  public long peekLong(int local_i)
  {
    return getLong(size64() - 1L - local_i);
  }
  
  public long getLong(int index)
  {
    return getLong(index);
  }
  
  public boolean rem(long local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeLong(index);
    return true;
  }
  
  public boolean addAll(long index, LongCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, LongBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(LongCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(LongBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Long local_ok)
  {
    add(index, local_ok.longValue());
  }
  
  public Long set(long index, Long local_ok)
  {
    return Long.valueOf(set(index, local_ok.longValue()));
  }
  
  public Long get(long index)
  {
    return Long.valueOf(getLong(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Long)local_ok).longValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Long)local_ok).longValue());
  }
  
  public Long remove(int index)
  {
    return Long.valueOf(removeLong(index));
  }
  
  public Long remove(long index)
  {
    return Long.valueOf(removeLong(index));
  }
  
  public void push(Long local_o)
  {
    push(local_o.longValue());
  }
  
  public Long pop()
  {
    return Long.valueOf(popLong());
  }
  
  public Long top()
  {
    return Long.valueOf(topLong());
  }
  
  public Long peek(int local_i)
  {
    return Long.valueOf(peekLong(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    LongIterator local_i = iterator();
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
      long local_k = local_i.nextLong();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class LongSubList
    extends AbstractLongBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongBigList field_302;
    protected final long from;
    protected long field_303;
    private static final boolean ASSERTS = false;
    
    public LongSubList(LongBigList local_l, long from, long local_to)
    {
      this.field_302 = local_l;
      this.from = from;
      this.field_303 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(long local_k)
    {
      this.field_302.add(this.field_303, local_k);
      this.field_303 += 1L;
      return true;
    }
    
    public void add(long index, long local_k)
    {
      ensureIndex(index);
      this.field_302.add(this.from + index, local_k);
      this.field_303 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Long> local_c)
    {
      ensureIndex(index);
      this.field_303 += local_c.size();
      return this.field_302.addAll(this.from + index, local_c);
    }
    
    public long getLong(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_302.getLong(this.from + index);
    }
    
    public long removeLong(long index)
    {
      ensureRestrictedIndex(index);
      this.field_303 -= 1L;
      return this.field_302.removeLong(this.from + index);
    }
    
    public long set(long index, long local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_302.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_303 - this.from;
    }
    
    public void getElements(long from, long[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_302.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_302.removeElements(this.from + from, this.from + local_to);
      this.field_303 -= local_to - from;
    }
    
    public void addElements(long index, long[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_302.addElements(this.from + index, local_a, offset, length);
      this.field_303 += length;
    }
    
    public LongBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractLongBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractLongBigList.LongSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public long nextLong()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractLongBigList.LongSubList.this.field_302.getLong(AbstractLongBigList.LongSubList.this.from + (this.last = this.pos++));
        }
        
        public long previousLong()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractLongBigList.LongSubList.this.field_302.getLong(AbstractLongBigList.LongSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(long local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractLongBigList.LongSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(long local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractLongBigList.LongSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractLongBigList.LongSubList.this.removeLong(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public LongBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new LongSubList(this, from, local_to);
    }
    
    public boolean rem(long local_k)
    {
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_303 -= 1L;
      this.field_302.removeLong(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Long)local_o).longValue());
    }
    
    public boolean addAll(long index, LongCollection local_c)
    {
      ensureIndex(index);
      this.field_303 += local_c.size();
      return this.field_302.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, LongList local_l)
    {
      ensureIndex(index);
      this.field_303 += local_l.size();
      return this.field_302.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */