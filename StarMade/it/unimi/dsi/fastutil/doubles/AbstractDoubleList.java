package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractDoubleList
  extends AbstractDoubleCollection
  implements DoubleList, DoubleStack
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
  
  public void add(int index, double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(double local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public double removeDouble(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public double set(int index, double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Double> local_c)
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
  
  public boolean addAll(Collection<? extends Double> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public DoubleListIterator doubleListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public DoubleListIterator doubleListIterator(int index)
  {
    return listIterator(index);
  }
  
  public DoubleListIterator iterator()
  {
    return listIterator();
  }
  
  public DoubleListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public DoubleListIterator listIterator(final int index)
  {
    new AbstractDoubleListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractDoubleList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public double nextDouble()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractDoubleList.this.getDouble(this.last = this.pos++);
      }
      
      public double previousDouble()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractDoubleList.this.getDouble(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractDoubleList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(double local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractDoubleList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractDoubleList.this.removeDouble(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(double local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(double local_k)
  {
    DoubleListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      double local_e = local_i.nextDouble();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(double local_k)
  {
    DoubleListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      double local_e = local_i.previousDouble();
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
        add(0.0D);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public DoubleList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new DoubleSubList(this, from, local_to);
  }
  
  @Deprecated
  public DoubleList doubleSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    DoubleListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextDouble();
      local_i.remove();
    }
  }
  
  public void addElements(int index, double[] local_a, int offset, int length)
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
  
  public void addElements(int index, double[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, double[] local_a, int offset, int length)
  {
    DoubleListIterator local_i = listIterator(from);
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
      local_a[(offset++)] = local_i.nextDouble();
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
  
  public int compareTo(List<? extends Double> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof DoubleList))
    {
      DoubleListIterator local_i1 = listIterator();
      DoubleListIterator local_i2 = ((DoubleList)local_l).listIterator();
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
    ListIterator<? extends Double> local_i1 = listIterator();
    ListIterator<? extends Double> local_i2 = local_l.listIterator();
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
    int local_s = size();
    while (local_s-- != 0)
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
    return removeDouble(size() - 1);
  }
  
  public double topDouble()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getDouble(size() - 1);
  }
  
  public double peekDouble(int local_i)
  {
    return getDouble(size() - 1 - local_i);
  }
  
  public boolean rem(double local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeDouble(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Double)local_o).doubleValue());
  }
  
  public boolean addAll(int index, DoubleCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, DoubleList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(DoubleCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(DoubleList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Double local_ok)
  {
    add(index, local_ok.doubleValue());
  }
  
  public Double set(int index, Double local_ok)
  {
    return Double.valueOf(set(index, local_ok.doubleValue()));
  }
  
  public Double get(int index)
  {
    return Double.valueOf(getDouble(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Double)local_ok).doubleValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Double)local_ok).doubleValue());
  }
  
  public Double remove(int index)
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
      double local_k = local_i.nextDouble();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class DoubleSubList
    extends AbstractDoubleList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleList field_357;
    protected final int from;
    protected int field_297;
    private static final boolean ASSERTS = false;
    
    public DoubleSubList(DoubleList local_l, int from, int local_to)
    {
      this.field_357 = local_l;
      this.from = from;
      this.field_297 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(double local_k)
    {
      this.field_357.add(this.field_297, local_k);
      this.field_297 += 1;
      return true;
    }
    
    public void add(int index, double local_k)
    {
      ensureIndex(index);
      this.field_357.add(this.from + index, local_k);
      this.field_297 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Double> local_c)
    {
      ensureIndex(index);
      this.field_297 += local_c.size();
      return this.field_357.addAll(this.from + index, local_c);
    }
    
    public double getDouble(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_357.getDouble(this.from + index);
    }
    
    public double removeDouble(int index)
    {
      ensureRestrictedIndex(index);
      this.field_297 -= 1;
      return this.field_357.removeDouble(this.from + index);
    }
    
    public double set(int index, double local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_357.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_297 - this.from;
    }
    
    public void getElements(int from, double[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_357.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_357.removeElements(this.from + from, this.from + local_to);
      this.field_297 -= local_to - from;
    }
    
    public void addElements(int index, double[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_357.addElements(this.from + index, local_a, offset, length);
      this.field_297 += length;
    }
    
    public DoubleListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractDoubleListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractDoubleList.DoubleSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public double nextDouble()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractDoubleList.DoubleSubList.this.field_357.getDouble(AbstractDoubleList.DoubleSubList.this.from + (this.last = this.pos++));
        }
        
        public double previousDouble()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractDoubleList.DoubleSubList.this.field_357.getDouble(AbstractDoubleList.DoubleSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(double local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractDoubleList.DoubleSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(double local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractDoubleList.DoubleSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractDoubleList.DoubleSubList.this.removeDouble(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public DoubleList subList(int from, int local_to)
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
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_297 -= 1;
      this.field_357.removeDouble(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Double)local_o).doubleValue());
    }
    
    public boolean addAll(int index, DoubleCollection local_c)
    {
      ensureIndex(index);
      this.field_297 += local_c.size();
      return this.field_357.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, DoubleList local_l)
    {
      ensureIndex(index);
      this.field_297 += local_l.size();
      return this.field_357.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */