package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractFloatBigList
  extends AbstractFloatCollection
  implements FloatBigList, FloatStack
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
  
  public void add(long index, float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(float local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public float removeFloat(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public float removeFloat(int local_i)
  {
    return removeFloat(local_i);
  }
  
  public float set(long index, float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public float set(int index, float local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Float> local_c)
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
  
  public boolean addAll(int index, Collection<? extends Float> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Float> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public FloatBigListIterator iterator()
  {
    return listIterator();
  }
  
  public FloatBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public FloatBigListIterator listIterator(final long index)
  {
    new AbstractFloatBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractFloatBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public float nextFloat()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractFloatBigList.this.getFloat(this.last = this.pos++);
      }
      
      public float previousFloat()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractFloatBigList.this.getFloat(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(float local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractFloatBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(float local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractFloatBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractFloatBigList.this.removeFloat(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public FloatBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(float local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(float local_k)
  {
    FloatBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      float local_e = local_i.nextFloat();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(float local_k)
  {
    FloatBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      float local_e = local_i.previousFloat();
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
        add(0.0F);
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
  
  public FloatBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new FloatSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    FloatBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextFloat();
      local_i.remove();
    }
  }
  
  public void addElements(long index, float[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    FloatBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, FloatBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, float[][] local_a)
  {
    addElements(index, local_a, 0L, FloatBigArrays.length(local_a));
  }
  
  public void getElements(long from, float[][] local_a, long offset, long length)
  {
    FloatBigListIterator local_i = listIterator(from);
    FloatBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      FloatBigArrays.set(local_a, offset++, local_i.nextFloat());
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
  
  public int compareTo(BigList<? extends Float> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof FloatBigList))
    {
      FloatBigListIterator local_i1 = listIterator();
      FloatBigListIterator local_i2 = ((FloatBigList)local_l).listIterator();
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
    BigListIterator<? extends Float> local_i1 = listIterator();
    BigListIterator<? extends Float> local_i2 = local_l.listIterator();
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
    long local_s = size64();
    while (local_s-- != 0L)
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
    return removeFloat(size64() - 1L);
  }
  
  public float topFloat()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getFloat(size64() - 1L);
  }
  
  public float peekFloat(int local_i)
  {
    return getFloat(size64() - 1L - local_i);
  }
  
  public float getFloat(int index)
  {
    return getFloat(index);
  }
  
  public boolean rem(float local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeFloat(index);
    return true;
  }
  
  public boolean addAll(long index, FloatCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, FloatBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(FloatCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(FloatBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Float local_ok)
  {
    add(index, local_ok.floatValue());
  }
  
  public Float set(long index, Float local_ok)
  {
    return Float.valueOf(set(index, local_ok.floatValue()));
  }
  
  public Float get(long index)
  {
    return Float.valueOf(getFloat(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Float)local_ok).floatValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Float)local_ok).floatValue());
  }
  
  public Float remove(int index)
  {
    return Float.valueOf(removeFloat(index));
  }
  
  public Float remove(long index)
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
      float local_k = local_i.nextFloat();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class FloatSubList
    extends AbstractFloatBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatBigList field_296;
    protected final long from;
    protected long field_82;
    private static final boolean ASSERTS = false;
    
    public FloatSubList(FloatBigList local_l, long from, long local_to)
    {
      this.field_296 = local_l;
      this.from = from;
      this.field_82 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(float local_k)
    {
      this.field_296.add(this.field_82, local_k);
      this.field_82 += 1L;
      return true;
    }
    
    public void add(long index, float local_k)
    {
      ensureIndex(index);
      this.field_296.add(this.from + index, local_k);
      this.field_82 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Float> local_c)
    {
      ensureIndex(index);
      this.field_82 += local_c.size();
      return this.field_296.addAll(this.from + index, local_c);
    }
    
    public float getFloat(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_296.getFloat(this.from + index);
    }
    
    public float removeFloat(long index)
    {
      ensureRestrictedIndex(index);
      this.field_82 -= 1L;
      return this.field_296.removeFloat(this.from + index);
    }
    
    public float set(long index, float local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_296.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_82 - this.from;
    }
    
    public void getElements(long from, float[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_296.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_296.removeElements(this.from + from, this.from + local_to);
      this.field_82 -= local_to - from;
    }
    
    public void addElements(long index, float[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_296.addElements(this.from + index, local_a, offset, length);
      this.field_82 += length;
    }
    
    public FloatBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractFloatBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractFloatBigList.FloatSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public float nextFloat()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractFloatBigList.FloatSubList.this.field_296.getFloat(AbstractFloatBigList.FloatSubList.this.from + (this.last = this.pos++));
        }
        
        public float previousFloat()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractFloatBigList.FloatSubList.this.field_296.getFloat(AbstractFloatBigList.FloatSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(float local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractFloatBigList.FloatSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(float local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractFloatBigList.FloatSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractFloatBigList.FloatSubList.this.removeFloat(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public FloatBigList subList(long from, long local_to)
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
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_82 -= 1L;
      this.field_296.removeFloat(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Float)local_o).floatValue());
    }
    
    public boolean addAll(long index, FloatCollection local_c)
    {
      ensureIndex(index);
      this.field_82 += local_c.size();
      return this.field_296.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, FloatList local_l)
    {
      ensureIndex(index);
      this.field_82 += local_l.size();
      return this.field_296.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */