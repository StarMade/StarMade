package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractDoubleBigList
  extends AbstractDoubleCollection
  implements DoubleBigList, DoubleStack
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
  
  public void add(long index, double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(double local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public double removeDouble(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public double removeDouble(int local_i)
  {
    return removeDouble(local_i);
  }
  
  public double set(long index, double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public double set(int index, double local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Double> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Double> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Double)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(int index, Collection<? extends Double> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Double> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public DoubleBigListIterator iterator()
  {
    return listIterator();
  }
  
  public DoubleBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public DoubleBigListIterator listIterator(final long index)
  {
    new AbstractDoubleBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractDoubleBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public double nextDouble()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractDoubleBigList.this.getDouble(this.last = this.pos++);
      }
      
      public double previousDouble()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractDoubleBigList.this.getDouble(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(double local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractDoubleBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(double local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractDoubleBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractDoubleBigList.this.removeDouble(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public DoubleBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(double local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(double local_k)
  {
    DoubleBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      double local_e = local_i.nextDouble();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(double local_k)
  {
    DoubleBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      double local_e = local_i.previousDouble();
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
        add(0.0D);
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
  
  public DoubleBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new DoubleSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    DoubleBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextDouble();
      local_i.remove();
    }
  }
  
  public void addElements(long index, double[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    DoubleBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, DoubleBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, double[][] local_a)
  {
    addElements(index, local_a, 0L, DoubleBigArrays.length(local_a));
  }
  
  public void getElements(long from, double[][] local_a, long offset, long length)
  {
    DoubleBigListIterator local_i = listIterator(from);
    DoubleBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      DoubleBigArrays.set(local_a, offset++, local_i.nextDouble());
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
  
  public int compareTo(BigList<? extends Double> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof DoubleBigList))
    {
      DoubleBigListIterator local_i1 = listIterator();
      DoubleBigListIterator local_i2 = ((DoubleBigList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        double local_e1 = local_i1.nextDouble();
        double local_e2 = local_i2.nextDouble();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    BigListIterator<? extends Double> local_i1 = listIterator();
    BigListIterator<? extends Double> local_i2 = local_l.listIterator();
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
    DoubleIterator local_i = iterator();
    int local_h = 1;
    long local_s = size64();
    while (local_s-- != 0L)
    {
      double local_k = local_i.nextDouble();
      local_h = 31 * local_h + HashCommon.double2int(local_k);
    }
    return local_h;
  }
  
  public void push(double local_o)
  {
    add(local_o);
  }
  
  public double popDouble()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeDouble(size64() - 1L);
  }
  
  public double topDouble()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getDouble(size64() - 1L);
  }
  
  public double peekDouble(int local_i)
  {
    return getDouble(size64() - 1L - local_i);
  }
  
  public double getDouble(int index)
  {
    return getDouble(index);
  }
  
  public boolean rem(double local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeDouble(index);
    return true;
  }
  
  public boolean addAll(long index, DoubleCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, DoubleBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(DoubleCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(DoubleBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Double local_ok)
  {
    add(index, local_ok.doubleValue());
  }
  
  public Double set(long index, Double local_ok)
  {
    return Double.valueOf(set(index, local_ok.doubleValue()));
  }
  
  public Double get(long index)
  {
    return Double.valueOf(getDouble(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Double)local_ok).doubleValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Double)local_ok).doubleValue());
  }
  
  public Double remove(int index)
  {
    return Double.valueOf(removeDouble(index));
  }
  
  public Double remove(long index)
  {
    return Double.valueOf(removeDouble(index));
  }
  
  public void push(Double local_o)
  {
    push(local_o.doubleValue());
  }
  
  public Double pop()
  {
    return Double.valueOf(popDouble());
  }
  
  public Double top()
  {
    return Double.valueOf(topDouble());
  }
  
  public Double peek(int local_i)
  {
    return Double.valueOf(peekDouble(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    DoubleIterator local_i = iterator();
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
      double local_k = local_i.nextDouble();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class DoubleSubList
    extends AbstractDoubleBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleBigList field_357;
    protected final long from;
    protected long field_297;
    private static final boolean ASSERTS = false;
    
    public DoubleSubList(DoubleBigList local_l, long from, long local_to)
    {
      this.field_357 = local_l;
      this.from = from;
      this.field_297 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(double local_k)
    {
      this.field_357.add(this.field_297, local_k);
      this.field_297 += 1L;
      return true;
    }
    
    public void add(long index, double local_k)
    {
      ensureIndex(index);
      this.field_357.add(this.from + index, local_k);
      this.field_297 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Double> local_c)
    {
      ensureIndex(index);
      this.field_297 += local_c.size();
      return this.field_357.addAll(this.from + index, local_c);
    }
    
    public double getDouble(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_357.getDouble(this.from + index);
    }
    
    public double removeDouble(long index)
    {
      ensureRestrictedIndex(index);
      this.field_297 -= 1L;
      return this.field_357.removeDouble(this.from + index);
    }
    
    public double set(long index, double local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_357.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_297 - this.from;
    }
    
    public void getElements(long from, double[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_357.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_357.removeElements(this.from + from, this.from + local_to);
      this.field_297 -= local_to - from;
    }
    
    public void addElements(long index, double[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_357.addElements(this.from + index, local_a, offset, length);
      this.field_297 += length;
    }
    
    public DoubleBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractDoubleBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractDoubleBigList.DoubleSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public double nextDouble()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractDoubleBigList.DoubleSubList.this.field_357.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = this.pos++));
        }
        
        public double previousDouble()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractDoubleBigList.DoubleSubList.this.field_357.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(double local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractDoubleBigList.DoubleSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(double local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractDoubleBigList.DoubleSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractDoubleBigList.DoubleSubList.this.removeDouble(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new DoubleSubList(this, from, local_to);
    }
    
    public boolean rem(double local_k)
    {
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_297 -= 1L;
      this.field_357.removeDouble(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Double)local_o).doubleValue());
    }
    
    public boolean addAll(long index, DoubleCollection local_c)
    {
      ensureIndex(index);
      this.field_297 += local_c.size();
      return this.field_357.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, DoubleList local_l)
    {
      ensureIndex(index);
      this.field_297 += local_l.size();
      return this.field_357.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */