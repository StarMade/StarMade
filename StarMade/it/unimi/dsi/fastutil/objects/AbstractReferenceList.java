package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Stack;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractReferenceList<K>
  extends AbstractReferenceCollection<K>
  implements ReferenceList<K>, Stack<K>
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
  
  public void add(int index, K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(K local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public K remove(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public K set(int index, K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends K> local_c)
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
  
  public boolean addAll(Collection<? extends K> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public ObjectListIterator<K> objectListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public ObjectListIterator<K> objectListIterator(int index)
  {
    return listIterator(index);
  }
  
  public ObjectListIterator<K> iterator()
  {
    return listIterator();
  }
  
  public ObjectListIterator<K> listIterator()
  {
    return listIterator(0);
  }
  
  public ObjectListIterator<K> listIterator(final int index)
  {
    new AbstractObjectListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractReferenceList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public K next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractReferenceList.this.get(this.last = this.pos++);
      }
      
      public K previous()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractReferenceList.this.get(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractReferenceList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(K local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractReferenceList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractReferenceList.this.remove(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(Object local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(Object local_k)
  {
    ObjectListIterator<K> local_i = listIterator();
    while (local_i.hasNext())
    {
      K local_e = local_i.next();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(Object local_k)
  {
    ObjectListIterator<K> local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      K local_e = local_i.previous();
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
        add(null);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public ReferenceList<K> subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ReferenceSubList(this, from, local_to);
  }
  
  @Deprecated
  public ReferenceList<K> referenceSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    ObjectListIterator<K> local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.next();
      local_i.remove();
    }
  }
  
  public void addElements(int index, K[] local_a, int offset, int length)
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
  
  public void addElements(int index, K[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, Object[] local_a, int offset, int length)
  {
    ObjectListIterator<K> local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.next();
    }
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
    int local_s = size();
    while (local_s-- != 0)
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
    return remove(size() - 1);
  }
  
  public K top()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return get(size() - 1);
  }
  
  public K peek(int local_i)
  {
    return get(size() - 1 - local_i);
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ObjectIterator<K> local_i = iterator();
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
      K local_k = local_i.next();
      if (this == local_k) {
        local_s.append("(this list)");
      } else {
        local_s.append(String.valueOf(local_k));
      }
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class ReferenceSubList<K>
    extends AbstractReferenceList<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ReferenceList<K> field_56;
    protected final int from;
    protected int field_57;
    private static final boolean ASSERTS = false;
    
    public ReferenceSubList(ReferenceList<K> local_l, int from, int local_to)
    {
      this.field_56 = local_l;
      this.from = from;
      this.field_57 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(K local_k)
    {
      this.field_56.add(this.field_57, local_k);
      this.field_57 += 1;
      return true;
    }
    
    public void add(int index, K local_k)
    {
      ensureIndex(index);
      this.field_56.add(this.from + index, local_k);
      this.field_57 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends K> local_c)
    {
      ensureIndex(index);
      this.field_57 += local_c.size();
      return this.field_56.addAll(this.from + index, local_c);
    }
    
    public K get(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_56.get(this.from + index);
    }
    
    public K remove(int index)
    {
      ensureRestrictedIndex(index);
      this.field_57 -= 1;
      return this.field_56.remove(this.from + index);
    }
    
    public K set(int index, K local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_56.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_57 - this.from;
    }
    
    public void getElements(int from, Object[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_56.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_56.removeElements(this.from + from, this.from + local_to);
      this.field_57 -= local_to - from;
    }
    
    public void addElements(int index, K[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_56.addElements(this.from + index, local_a, offset, length);
      this.field_57 += length;
    }
    
    public ObjectListIterator<K> listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractObjectListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractReferenceList.ReferenceSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public K next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractReferenceList.ReferenceSubList.this.field_56.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = this.pos++));
        }
        
        public K previous()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractReferenceList.ReferenceSubList.this.field_56.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(K local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractReferenceList.ReferenceSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(K local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractReferenceList.ReferenceSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractReferenceList.ReferenceSubList.this.remove(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public ReferenceList<K> subList(int from, int local_to)
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
      int index = indexOf(local_o);
      if (index == -1) {
        return false;
      }
      remove(index);
      return true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */