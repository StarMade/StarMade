package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import it.unimi.dsi.fastutil.Stack;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractReferenceBigList<K>
  extends AbstractReferenceCollection<K>
  implements ReferenceBigList<K>, Stack<K>
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
  
  public void add(long index, K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(K local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public K remove(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public K remove(int local_i)
  {
    return remove(local_i);
  }
  
  public K set(long index, K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public K set(int index, K local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends K> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends K> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, local_i.next());
    }
    return true;
  }
  
  public boolean addAll(int index, Collection<? extends K> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends K> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public ObjectBigListIterator<K> iterator()
  {
    return listIterator();
  }
  
  public ObjectBigListIterator<K> listIterator()
  {
    return listIterator(0L);
  }
  
  public ObjectBigListIterator<K> listIterator(final long index)
  {
    new AbstractObjectBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractReferenceBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public K next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractReferenceBigList.this.get(this.last = this.pos++);
      }
      
      public K previous()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractReferenceBigList.this.get(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(K local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractReferenceBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(K local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractReferenceBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractReferenceBigList.this.remove(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public ObjectBigListIterator<K> listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(Object local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(Object local_k)
  {
    ObjectBigListIterator<K> local_i = listIterator();
    while (local_i.hasNext())
    {
      K local_e = local_i.next();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(Object local_k)
  {
    ObjectBigListIterator<K> local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      K local_e = local_i.previous();
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
        add(null);
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
  
  public ReferenceBigList<K> subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ReferenceSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    ObjectBigListIterator<K> local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.next();
      local_i.remove();
    }
  }
  
  public void addElements(long index, K[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ObjectBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, ObjectBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, K[][] local_a)
  {
    addElements(index, local_a, 0L, ObjectBigArrays.length(local_a));
  }
  
  public void getElements(long from, Object[][] local_a, long offset, long length)
  {
    ObjectBigListIterator<K> local_i = listIterator(from);
    ObjectBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      ObjectBigArrays.set(local_a, offset++, local_i.next());
    }
  }
  
  @Deprecated
  public int size()
  {
    return (int)Math.min(2147483647L, size64());
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
      if (local_i1.next() != local_i2.next()) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    ObjectIterator<K> local_i = iterator();
    int local_h = 1;
    long local_s = size64();
    while (local_s-- != 0L)
    {
      K local_k = local_i.next();
      local_h = 31 * local_h + (local_k == null ? 0 : System.identityHashCode(local_k));
    }
    return local_h;
  }
  
  public void push(K local_o)
  {
    add(local_o);
  }
  
  public K pop()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return remove(size64() - 1L);
  }
  
  public K top()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return get(size64() - 1L);
  }
  
  public K peek(int local_i)
  {
    return get(size64() - 1L - local_i);
  }
  
  public K get(int index)
  {
    return get(index);
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ObjectIterator<K> local_i = iterator();
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
      K local_k = local_i.next();
      if (this == local_k) {
        local_s.append("(this big list)");
      } else {
        local_s.append(String.valueOf(local_k));
      }
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class ReferenceSubList<K>
    extends AbstractReferenceBigList<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ReferenceBigList<K> field_56;
    protected final long from;
    protected long field_57;
    private static final boolean ASSERTS = false;
    
    public ReferenceSubList(ReferenceBigList<K> local_l, long from, long local_to)
    {
      this.field_56 = local_l;
      this.from = from;
      this.field_57 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(K local_k)
    {
      this.field_56.add(this.field_57, local_k);
      this.field_57 += 1L;
      return true;
    }
    
    public void add(long index, K local_k)
    {
      ensureIndex(index);
      this.field_56.add(this.from + index, local_k);
      this.field_57 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends K> local_c)
    {
      ensureIndex(index);
      this.field_57 += local_c.size();
      return this.field_56.addAll(this.from + index, local_c);
    }
    
    public K get(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_56.get(this.from + index);
    }
    
    public K remove(long index)
    {
      ensureRestrictedIndex(index);
      this.field_57 -= 1L;
      return this.field_56.remove(this.from + index);
    }
    
    public K set(long index, K local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_56.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_57 - this.from;
    }
    
    public void getElements(long from, Object[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_56.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_56.removeElements(this.from + from, this.from + local_to);
      this.field_57 -= local_to - from;
    }
    
    public void addElements(long index, K[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_56.addElements(this.from + index, local_a, offset, length);
      this.field_57 += length;
    }
    
    public ObjectBigListIterator<K> listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractObjectBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractReferenceBigList.ReferenceSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public K next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractReferenceBigList.ReferenceSubList.this.field_56.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = this.pos++));
        }
        
        public K previous()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractReferenceBigList.ReferenceSubList.this.field_56.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(K local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractReferenceBigList.ReferenceSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(K local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractReferenceBigList.ReferenceSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractReferenceBigList.ReferenceSubList.this.remove(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public ReferenceBigList<K> subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new ReferenceSubList(this, from, local_to);
    }
    
    public boolean remove(Object local_o)
    {
      long index = indexOf(local_o);
      if (index == -1L) {
        return false;
      }
      remove(index);
      return true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */