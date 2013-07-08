package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractFloatList
  extends AbstractFloatCollection
  implements FloatList, FloatStack
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
  
  public void add(int index, float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(float local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public float removeFloat(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public float set(int index, float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Float> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Float> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Float)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(Collection<? extends Float> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public FloatListIterator floatListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public FloatListIterator floatListIterator(int index)
  {
    return listIterator(index);
  }
  
  public FloatListIterator iterator()
  {
    return listIterator();
  }
  
  public FloatListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public FloatListIterator listIterator(final int index)
  {
    new AbstractFloatListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractFloatList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public float nextFloat()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractFloatList.this.getFloat(this.last = this.pos++);
      }
      
      public float previousFloat()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractFloatList.this.getFloat(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractFloatList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(float local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractFloatList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractFloatList.this.removeFloat(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(float local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(float local_k)
  {
    FloatListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      float local_e = local_i.nextFloat();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(float local_k)
  {
    FloatListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      float local_e = local_i.previousFloat();
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
        add(0.0F);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public FloatList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new FloatSubList(this, from, local_to);
  }
  
  @Deprecated
  public FloatList floatSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    FloatListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextFloat();
      local_i.remove();
    }
  }
  
  public void addElements(int index, float[] local_a, int offset, int length)
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
  
  public void addElements(int index, float[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, float[] local_a, int offset, int length)
  {
    FloatListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextFloat();
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
  
  public int compareTo(List<? extends Float> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof FloatList))
    {
      FloatListIterator local_i1 = listIterator();
      FloatListIterator local_i2 = ((FloatList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        float local_e1 = local_i1.nextFloat();
        float local_e2 = local_i2.nextFloat();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    ListIterator<? extends Float> local_i1 = listIterator();
    ListIterator<? extends Float> local_i2 = local_l.listIterator();
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
    FloatIterator local_i = iterator();
    int local_h = 1;
    int local_s = size();
    while (local_s-- != 0)
    {
      float local_k = local_i.nextFloat();
      local_h = 31 * local_h + HashCommon.float2int(local_k);
    }
    return local_h;
  }
  
  public void push(float local_o)
  {
    add(local_o);
  }
  
  public float popFloat()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeFloat(size() - 1);
  }
  
  public float topFloat()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getFloat(size() - 1);
  }
  
  public float peekFloat(int local_i)
  {
    return getFloat(size() - 1 - local_i);
  }
  
  public boolean rem(float local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeFloat(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Float)local_o).floatValue());
  }
  
  public boolean addAll(int index, FloatCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, FloatList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(FloatCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(FloatList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Float local_ok)
  {
    add(index, local_ok.floatValue());
  }
  
  public Float set(int index, Float local_ok)
  {
    return Float.valueOf(set(index, local_ok.floatValue()));
  }
  
  public Float get(int index)
  {
    return Float.valueOf(getFloat(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Float)local_ok).floatValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Float)local_ok).floatValue());
  }
  
  public Float remove(int index)
  {
    return Float.valueOf(removeFloat(index));
  }
  
  public void push(Float local_o)
  {
    push(local_o.floatValue());
  }
  
  public Float pop()
  {
    return Float.valueOf(popFloat());
  }
  
  public Float top()
  {
    return Float.valueOf(topFloat());
  }
  
  public Float peek(int local_i)
  {
    return Float.valueOf(peekFloat(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    FloatIterator local_i = iterator();
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
      float local_k = local_i.nextFloat();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class FloatSubList
    extends AbstractFloatList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatList field_296;
    protected final int from;
    protected int field_82;
    private static final boolean ASSERTS = false;
    
    public FloatSubList(FloatList local_l, int from, int local_to)
    {
      this.field_296 = local_l;
      this.from = from;
      this.field_82 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(float local_k)
    {
      this.field_296.add(this.field_82, local_k);
      this.field_82 += 1;
      return true;
    }
    
    public void add(int index, float local_k)
    {
      ensureIndex(index);
      this.field_296.add(this.from + index, local_k);
      this.field_82 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Float> local_c)
    {
      ensureIndex(index);
      this.field_82 += local_c.size();
      return this.field_296.addAll(this.from + index, local_c);
    }
    
    public float getFloat(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_296.getFloat(this.from + index);
    }
    
    public float removeFloat(int index)
    {
      ensureRestrictedIndex(index);
      this.field_82 -= 1;
      return this.field_296.removeFloat(this.from + index);
    }
    
    public float set(int index, float local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_296.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_82 - this.from;
    }
    
    public void getElements(int from, float[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_296.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_296.removeElements(this.from + from, this.from + local_to);
      this.field_82 -= local_to - from;
    }
    
    public void addElements(int index, float[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_296.addElements(this.from + index, local_a, offset, length);
      this.field_82 += length;
    }
    
    public FloatListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractFloatListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractFloatList.FloatSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public float nextFloat()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractFloatList.FloatSubList.this.field_296.getFloat(AbstractFloatList.FloatSubList.this.from + (this.last = this.pos++));
        }
        
        public float previousFloat()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractFloatList.FloatSubList.this.field_296.getFloat(AbstractFloatList.FloatSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(float local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractFloatList.FloatSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(float local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractFloatList.FloatSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractFloatList.FloatSubList.this.removeFloat(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public FloatList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new FloatSubList(this, from, local_to);
    }
    
    public boolean rem(float local_k)
    {
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_82 -= 1;
      this.field_296.removeFloat(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Float)local_o).floatValue());
    }
    
    public boolean addAll(int index, FloatCollection local_c)
    {
      ensureIndex(index);
      this.field_82 += local_c.size();
      return this.field_296.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, FloatList local_l)
    {
      ensureIndex(index);
      this.field_82 += local_l.size();
      return this.field_296.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */