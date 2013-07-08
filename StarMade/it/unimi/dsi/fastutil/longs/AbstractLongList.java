package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractLongList
  extends AbstractLongCollection
  implements LongList, LongStack
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
  
  public void add(int index, long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(long local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public long removeLong(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public long set(int index, long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Long> local_c)
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
  
  public boolean addAll(Collection<? extends Long> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public LongListIterator longListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public LongListIterator longListIterator(int index)
  {
    return listIterator(index);
  }
  
  public LongListIterator iterator()
  {
    return listIterator();
  }
  
  public LongListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public LongListIterator listIterator(final int index)
  {
    new AbstractLongListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractLongList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public long nextLong()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractLongList.this.getLong(this.last = this.pos++);
      }
      
      public long previousLong()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractLongList.this.getLong(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractLongList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(long local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractLongList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractLongList.this.removeLong(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(long local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(long local_k)
  {
    LongListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      long local_e = local_i.nextLong();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(long local_k)
  {
    LongListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      long local_e = local_i.previousLong();
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
        add(0L);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public LongList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new LongSubList(this, from, local_to);
  }
  
  @Deprecated
  public LongList longSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    LongListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextLong();
      local_i.remove();
    }
  }
  
  public void addElements(int index, long[] local_a, int offset, int length)
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
  
  public void addElements(int index, long[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, long[] local_a, int offset, int length)
  {
    LongListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextLong();
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
  
  public int compareTo(List<? extends Long> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof LongList))
    {
      LongListIterator local_i1 = listIterator();
      LongListIterator local_i2 = ((LongList)local_l).listIterator();
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
    ListIterator<? extends Long> local_i1 = listIterator();
    ListIterator<? extends Long> local_i2 = local_l.listIterator();
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
    int local_s = size();
    while (local_s-- != 0)
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
    return removeLong(size() - 1);
  }
  
  public long topLong()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getLong(size() - 1);
  }
  
  public long peekLong(int local_i)
  {
    return getLong(size() - 1 - local_i);
  }
  
  public boolean rem(long local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeLong(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Long)local_o).longValue());
  }
  
  public boolean addAll(int index, LongCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, LongList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(LongCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(LongList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Long local_ok)
  {
    add(index, local_ok.longValue());
  }
  
  public Long set(int index, Long local_ok)
  {
    return Long.valueOf(set(index, local_ok.longValue()));
  }
  
  public Long get(int index)
  {
    return Long.valueOf(getLong(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Long)local_ok).longValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Long)local_ok).longValue());
  }
  
  public Long remove(int index)
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
      long local_k = local_i.nextLong();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class LongSubList
    extends AbstractLongList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongList field_302;
    protected final int from;
    protected int field_303;
    private static final boolean ASSERTS = false;
    
    public LongSubList(LongList local_l, int from, int local_to)
    {
      this.field_302 = local_l;
      this.from = from;
      this.field_303 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(long local_k)
    {
      this.field_302.add(this.field_303, local_k);
      this.field_303 += 1;
      return true;
    }
    
    public void add(int index, long local_k)
    {
      ensureIndex(index);
      this.field_302.add(this.from + index, local_k);
      this.field_303 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Long> local_c)
    {
      ensureIndex(index);
      this.field_303 += local_c.size();
      return this.field_302.addAll(this.from + index, local_c);
    }
    
    public long getLong(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_302.getLong(this.from + index);
    }
    
    public long removeLong(int index)
    {
      ensureRestrictedIndex(index);
      this.field_303 -= 1;
      return this.field_302.removeLong(this.from + index);
    }
    
    public long set(int index, long local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_302.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_303 - this.from;
    }
    
    public void getElements(int from, long[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_302.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_302.removeElements(this.from + from, this.from + local_to);
      this.field_303 -= local_to - from;
    }
    
    public void addElements(int index, long[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_302.addElements(this.from + index, local_a, offset, length);
      this.field_303 += length;
    }
    
    public LongListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractLongListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractLongList.LongSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public long nextLong()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractLongList.LongSubList.this.field_302.getLong(AbstractLongList.LongSubList.this.from + (this.last = this.pos++));
        }
        
        public long previousLong()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractLongList.LongSubList.this.field_302.getLong(AbstractLongList.LongSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(long local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractLongList.LongSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(long local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractLongList.LongSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractLongList.LongSubList.this.removeLong(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public LongList subList(int from, int local_to)
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
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_303 -= 1;
      this.field_302.removeLong(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Long)local_o).longValue());
    }
    
    public boolean addAll(int index, LongCollection local_c)
    {
      ensureIndex(index);
      this.field_303 += local_c.size();
      return this.field_302.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, LongList local_l)
    {
      ensureIndex(index);
      this.field_303 += local_l.size();
      return this.field_302.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */