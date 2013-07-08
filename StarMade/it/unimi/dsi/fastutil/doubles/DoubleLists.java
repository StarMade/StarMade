package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DoubleLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static DoubleList shuffle(DoubleList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      double local_t = local_l.getDouble(local_i);
      local_l.set(local_i, local_l.getDouble(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static DoubleList singleton(double element)
  {
    return new Singleton(element, null);
  }
  
  public static DoubleList singleton(Object element)
  {
    return new Singleton(((Double)element).doubleValue(), null);
  }
  
  public static DoubleList synchronize(DoubleList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static DoubleList synchronize(DoubleList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static DoubleList unmodifiable(DoubleList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends DoubleCollections.UnmodifiableCollection
    implements DoubleList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleList list;
    
    protected UnmodifiableList(DoubleList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public double getDouble(int local_i)
    {
      return this.list.getDouble(local_i);
    }
    
    public double set(int local_i, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double removeDouble(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(double local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(double local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, double[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, double[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, double[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public DoubleListIterator iterator()
    {
      return listIterator();
    }
    
    public DoubleListIterator listIterator()
    {
      return DoubleIterators.unmodifiable(this.list.listIterator());
    }
    
    public DoubleListIterator listIterator(int local_i)
    {
      return DoubleIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public DoubleList subList(int from, int local_to)
    {
      return DoubleLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public DoubleList doubleSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public boolean equals(Object local_o)
    {
      return this.collection.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.collection.hashCode();
    }
    
    public int compareTo(List<? extends Double> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, DoubleList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double get(int local_i)
    {
      return (Double)this.list.get(local_i);
    }
    
    public void add(int local_i, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double set(int index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double remove(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(Object local_o)
    {
      return this.list.indexOf(local_o);
    }
    
    public int lastIndexOf(Object local_o)
    {
      return this.list.lastIndexOf(local_o);
    }
  }
  
  public static class SynchronizedList
    extends DoubleCollections.SynchronizedCollection
    implements DoubleList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleList list;
    
    protected SynchronizedList(DoubleList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(DoubleList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public double getDouble(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getDouble(local_i);
      }
    }
    
    public double set(int local_i, double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, double local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public double removeDouble(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeDouble(local_i);
      }
    }
    
    public int indexOf(double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Double> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, double[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.getElements(from, local_a, offset, length);
      }
    }
    
    public void removeElements(int from, int local_to)
    {
      synchronized (this.sync)
      {
        this.list.removeElements(from, local_to);
      }
    }
    
    public void addElements(int index, double[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, double[] local_a)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a);
      }
    }
    
    public void size(int size)
    {
      synchronized (this.sync)
      {
        this.list.size(size);
      }
    }
    
    public DoubleListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public DoubleListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public DoubleListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public DoubleList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return DoubleLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public DoubleList doubleSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public boolean equals(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.equals(local_o);
      }
    }
    
    public int hashCode()
    {
      synchronized (this.sync)
      {
        return this.collection.hashCode();
      }
    }
    
    public int compareTo(List<? extends Double> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, DoubleList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(DoubleList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Double get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Double local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Double set(int index, Double local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.set(index, local_k);
      }
    }
    
    public Double remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.remove(local_i);
      }
    }
    
    public int indexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_o);
      }
    }
    
    public int lastIndexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_o);
      }
    }
  }
  
  public static class Singleton
    extends AbstractDoubleList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final double element;
    
    private Singleton(double element)
    {
      this.element = element;
    }
    
    public double getDouble(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public double removeDouble(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(double local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public double[] toDoubleArray()
    {
      double[] local_a = new double[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public DoubleListIterator listIterator()
    {
      return DoubleIterators.singleton(this.element);
    }
    
    public DoubleListIterator iterator()
    {
      return listIterator();
    }
    
    public DoubleListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      DoubleListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public DoubleList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return DoubleLists.EMPTY_LIST;
      }
      return this;
    }
    
    public int size()
    {
      return 1;
    }
    
    public void size(int size)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return this;
    }
    
    public boolean rem(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends DoubleCollections.EmptyCollection
    implements DoubleList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double removeDouble(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public double set(int index, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(double local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(double local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, DoubleList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double set(int index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double getDouble(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Double remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(Object local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(Object local_k)
    {
      return -1;
    }
    
    @Deprecated
    public DoubleIterator doubleIterator()
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    public DoubleListIterator listIterator()
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    public DoubleListIterator iterator()
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    public DoubleListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return DoubleIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public DoubleListIterator doubleListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public DoubleList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public DoubleList doubleSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, double[] local_a, int offset, int length)
    {
      if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= local_a.length)) {
        return;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, double[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, double[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Double> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return DoubleLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return DoubleLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */