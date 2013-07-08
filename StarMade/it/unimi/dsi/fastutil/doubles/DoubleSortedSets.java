package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class DoubleSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static DoubleSortedSet singleton(double element)
  {
    return new Singleton(element, null);
  }
  
  public static DoubleSortedSet singleton(double element, DoubleComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static DoubleSortedSet singleton(Object element)
  {
    return new Singleton(((Double)element).doubleValue(), null);
  }
  
  public static DoubleSortedSet singleton(Object element, DoubleComparator comparator)
  {
    return new Singleton(((Double)element).doubleValue(), comparator, null);
  }
  
  public static DoubleSortedSet synchronize(DoubleSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static DoubleSortedSet synchronize(DoubleSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static DoubleSortedSet unmodifiable(DoubleSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends DoubleSets.UnmodifiableSet
    implements DoubleSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(DoubleSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public DoubleComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return DoubleIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return DoubleIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public DoubleBidirectionalIterator doubleIterator()
    {
      return iterator();
    }
    
    public double firstDouble()
    {
      return this.sortedSet.firstDouble();
    }
    
    public double lastDouble()
    {
      return this.sortedSet.lastDouble();
    }
    
    public Double first()
    {
      return (Double)this.sortedSet.first();
    }
    
    public Double last()
    {
      return (Double)this.sortedSet.last();
    }
    
    public DoubleSortedSet subSet(Double from, Double local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public DoubleSortedSet headSet(Double local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public DoubleSortedSet tailSet(Double from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends DoubleSets.SynchronizedSet
    implements DoubleSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleSortedSet sortedSet;
    
    protected SynchronizedSortedSet(DoubleSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(DoubleSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public DoubleComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public DoubleBidirectionalIterator doubleIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public double firstDouble()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstDouble();
      }
    }
    
    public double lastDouble()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastDouble();
      }
    }
    
    public Double first()
    {
      synchronized (this.sync)
      {
        return (Double)this.sortedSet.first();
      }
    }
    
    public Double last()
    {
      synchronized (this.sync)
      {
        return (Double)this.sortedSet.last();
      }
    }
    
    public DoubleSortedSet subSet(Double from, Double local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public DoubleSortedSet headSet(Double local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public DoubleSortedSet tailSet(Double from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends DoubleSets.Singleton
    implements DoubleSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final DoubleComparator comparator;
    
    private Singleton(double element, DoubleComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(double element)
    {
      this(element, null);
    }
    
    final int compare(double local_k1, double local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public DoubleBidirectionalIterator doubleIterator()
    {
      return iterator();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      DoubleBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public DoubleComparator comparator()
    {
      return this.comparator;
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public double firstDouble()
    {
      return this.element;
    }
    
    public double lastDouble()
    {
      return this.element;
    }
    
    public Double first()
    {
      return Double.valueOf(this.element);
    }
    
    public Double last()
    {
      return Double.valueOf(this.element);
    }
    
    public DoubleSortedSet subSet(Double from, Double local_to)
    {
      return subSet(from.doubleValue(), local_to.doubleValue());
    }
    
    public DoubleSortedSet headSet(Double local_to)
    {
      return headSet(local_to.doubleValue());
    }
    
    public DoubleSortedSet tailSet(Double from)
    {
      return tailSet(from.doubleValue());
    }
  }
  
  public static class EmptySet
    extends DoubleSets.EmptySet
    implements DoubleSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public DoubleBidirectionalIterator doubleIterator()
    {
      return iterator();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet headSet(double from)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet tailSet(double local_to)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public double firstDouble()
    {
      throw new NoSuchElementException();
    }
    
    public double lastDouble()
    {
      throw new NoSuchElementException();
    }
    
    public DoubleComparator comparator()
    {
      return null;
    }
    
    public DoubleSortedSet subSet(Double from, Double local_to)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet headSet(Double from)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public DoubleSortedSet tailSet(Double local_to)
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    public Double first()
    {
      throw new NoSuchElementException();
    }
    
    public Double last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return DoubleSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return DoubleSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */