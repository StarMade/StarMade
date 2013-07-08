package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class DoubleBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static DoubleBigList shuffle(DoubleBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      double local_t = local_l.getDouble(local_i);
      local_l.set(local_i, local_l.getDouble(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static DoubleBigList singleton(double element)
  {
    return new Singleton(element, null);
  }
  
  public static DoubleBigList singleton(Object element)
  {
    return new Singleton(((Double)element).doubleValue(), null);
  }
  
  public static DoubleBigList synchronize(DoubleBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static DoubleBigList synchronize(DoubleBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static DoubleBigList unmodifiable(DoubleBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static DoubleBigList asBigList(DoubleList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractDoubleBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final DoubleList list;
    
    protected ListBigList(DoubleList list)
    {
      this.list = list;
    }
    
    private int intIndex(long index)
    {
      if (index >= 2147483647L) {
        throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
      }
      return (int)index;
    }
    
    public long size64()
    {
      return this.list.size();
    }
    
    @Deprecated
    public int size()
    {
      return this.list.size();
    }
    
    public void size(long size)
    {
      this.list.size(intIndex(size));
    }
    
    public DoubleBigListIterator iterator()
    {
      return DoubleBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public DoubleBigListIterator listIterator()
    {
      return DoubleBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Double> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public DoubleBigListIterator listIterator(long index)
    {
      return DoubleBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(double key)
    {
      return this.list.contains(key);
    }
    
    public double[] toDoubleArray()
    {
      return this.list.toDoubleArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public double[] toDoubleArray(double[] local_a)
    {
      return this.list.toDoubleArray(local_a);
    }
    
    public void add(long index, double key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, DoubleCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, DoubleBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(double key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(DoubleBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public double getDouble(long index)
    {
      return this.list.getDouble(intIndex(index));
    }
    
    public long indexOf(double local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(double local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public double removeDouble(long index)
    {
      return this.list.removeDouble(intIndex(index));
    }
    
    public double set(long index, double local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(DoubleCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(DoubleCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(DoubleCollection local_c)
    {
      return this.list.retainAll(local_c);
    }
    
    public boolean isEmpty()
    {
      return this.list.isEmpty();
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      return this.list.toArray(local_a);
    }
    
    public boolean containsAll(Collection<?> local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      return this.list.retainAll(local_c);
    }
    
    public void clear()
    {
      this.list.clear();
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
  }
  
  public static class UnmodifiableBigList
    extends DoubleCollections.UnmodifiableCollection
    implements DoubleBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleBigList list;
    
    protected UnmodifiableBigList(DoubleBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public double getDouble(long local_i)
    {
      return this.list.getDouble(local_i);
    }
    
    public double set(long local_i, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double removeDouble(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(double local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(double local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, double[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, double[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, double[][] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(long size)
    {
      this.list.size(size);
    }
    
    public long size64()
    {
      return this.list.size64();
    }
    
    public DoubleBigListIterator iterator()
    {
      return listIterator();
    }
    
    public DoubleBigListIterator listIterator()
    {
      return DoubleBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public DoubleBigListIterator listIterator(long local_i)
    {
      return DoubleBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      return DoubleBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Double> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, DoubleBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double get(long local_i)
    {
      return (Double)this.list.get(local_i);
    }
    
    public void add(long local_i, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double set(long index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double remove(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(Object local_o)
    {
      return this.list.indexOf(local_o);
    }
    
    public long lastIndexOf(Object local_o)
    {
      return this.list.lastIndexOf(local_o);
    }
  }
  
  public static class SynchronizedBigList
    extends DoubleCollections.SynchronizedCollection
    implements DoubleBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleBigList list;
    
    protected SynchronizedBigList(DoubleBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(DoubleBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public double getDouble(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getDouble(local_i);
      }
    }
    
    public double set(long local_i, double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, double local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public double removeDouble(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeDouble(local_i);
      }
    }
    
    public long indexOf(double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(double local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Double> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, double[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.getElements(from, local_a, offset, length);
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      synchronized (this.sync)
      {
        this.list.removeElements(from, local_to);
      }
    }
    
    public void addElements(long index, double[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, double[][] local_a)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a);
      }
    }
    
    public void size(long size)
    {
      synchronized (this.sync)
      {
        this.list.size(size);
      }
    }
    
    public long size64()
    {
      synchronized (this.sync)
      {
        return this.list.size64();
      }
    }
    
    public DoubleBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public DoubleBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public DoubleBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return DoubleBigLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    public boolean equals(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.equals(local_o);
      }
    }
    
    public int hashCode()
    {
      synchronized (this.sync)
      {
        return this.list.hashCode();
      }
    }
    
    public int compareTo(BigList<? extends Double> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, DoubleBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(DoubleBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Double get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Double local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Double set(long index, Double local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.set(index, local_k);
      }
    }
    
    public Double remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Double)this.list.remove(local_i);
      }
    }
    
    public long indexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_o);
      }
    }
    
    public long lastIndexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_o);
      }
    }
  }
  
  public static class Singleton
    extends AbstractDoubleBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final double element;
    
    private Singleton(double element)
    {
      this.element = element;
    }
    
    public double getDouble(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public double removeDouble(long local_i)
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
    
    public boolean addAll(long local_i, Collection<? extends Double> local_c)
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
    
    public DoubleBigListIterator listIterator()
    {
      return DoubleBigListIterators.singleton(this.element);
    }
    
    public DoubleBigListIterator iterator()
    {
      return listIterator();
    }
    
    public DoubleBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      DoubleBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return DoubleBigLists.EMPTY_BIG_LIST;
      }
      return this;
    }
    
    @Deprecated
    public int size()
    {
      return 1;
    }
    
    public long size64()
    {
      return 1L;
    }
    
    public void size(long size)
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
    
    public boolean addAll(long local_i, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends DoubleCollections.EmptyCollection
    implements DoubleBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double removeDouble(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public double set(long index, double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(double local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(double local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, DoubleBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double set(long index, Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double getDouble(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Double remove(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(Object local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(Object local_k)
    {
      return -1L;
    }
    
    public DoubleBigListIterator listIterator()
    {
      return DoubleBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public DoubleBigListIterator iterator()
    {
      return DoubleBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public DoubleBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return DoubleBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public DoubleBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, double[][] local_a, long offset, long length)
    {
      DoubleBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, double[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, double[][] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(long local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public long size64()
    {
      return 0L;
    }
    
    public int compareTo(BigList<? extends Double> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return DoubleBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return DoubleBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */