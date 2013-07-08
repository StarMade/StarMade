package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class DoubleCollections
{
  public static DoubleCollection synchronize(DoubleCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static DoubleCollection synchronize(DoubleCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static DoubleCollection unmodifiable(DoubleCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static DoubleCollection asCollection(DoubleIterable iterable)
  {
    if ((iterable instanceof DoubleCollection)) {
      return (DoubleCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractDoubleCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleIterable iterable;
    
    protected IterableCollection(DoubleIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      DoubleIterator iterator = iterator();
      while (iterator.hasNext())
      {
        iterator.next();
        local_c++;
      }
      return local_c;
    }
    
    public boolean isEmpty()
    {
      return this.iterable.iterator().hasNext();
    }
    
    public DoubleIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public DoubleIterator doubleIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements DoubleCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleCollection collection;
    
    protected UnmodifiableCollection(DoubleCollection local_c)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
    }
    
    public int size()
    {
      return this.collection.size();
    }
    
    public boolean isEmpty()
    {
      return this.collection.isEmpty();
    }
    
    public boolean contains(double local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public DoubleIterator iterator()
    {
      return DoubleIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public DoubleIterator doubleIterator()
    {
      return iterator();
    }
    
    public boolean add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(Collection<?> local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      throw new UnsupportedOperationException();
    }
    
    public String toString()
    {
      return this.collection.toString();
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public Object[] toArray()
    {
      return this.collection.toArray();
    }
    
    public double[] toDoubleArray()
    {
      return this.collection.toDoubleArray();
    }
    
    public double[] toDoubleArray(double[] local_a)
    {
      return this.collection.toDoubleArray(local_a);
    }
    
    public double[] toArray(double[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(DoubleCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements DoubleCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final DoubleCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(DoubleCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(DoubleCollection local_c)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = this;
    }
    
    public int size()
    {
      synchronized (this.sync)
      {
        return this.collection.size();
      }
    }
    
    public boolean isEmpty()
    {
      synchronized (this.sync)
      {
        return this.collection.isEmpty();
      }
    }
    
    public boolean contains(double local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public double[] toDoubleArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toDoubleArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public double[] toDoubleArray(double[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toDoubleArray(local_a);
      }
    }
    
    public double[] toArray(double[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toDoubleArray(local_a);
      }
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(DoubleCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Double local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean contains(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_k);
      }
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toArray(local_a);
      }
    }
    
    public DoubleIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public DoubleIterator doubleIterator()
    {
      return iterator();
    }
    
    public boolean add(double local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(double local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.rem(local_k);
      }
    }
    
    public boolean remove(Object local_ok)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(local_ok);
      }
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(Collection<?> local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public void clear()
    {
      synchronized (this.sync)
      {
        this.collection.clear();
      }
    }
    
    public String toString()
    {
      synchronized (this.sync)
      {
        return this.collection.toString();
      }
    }
  }
  
  public static abstract class EmptyCollection
    extends AbstractDoubleCollection
  {
    public boolean add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(double local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public double[] toDoubleArray(double[] local_a)
    {
      return local_a;
    }
    
    public double[] toDoubleArray()
    {
      return DoubleArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(DoubleCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    public int hashCode()
    {
      return 0;
    }
    
    public boolean equals(Object local_o)
    {
      if (local_o == this) {
        return true;
      }
      if (!(local_o instanceof Collection)) {
        return false;
      }
      return ((Collection)local_o).isEmpty();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */