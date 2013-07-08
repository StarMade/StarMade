package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class IntCollections
{
  public static IntCollection synchronize(IntCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static IntCollection synchronize(IntCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static IntCollection unmodifiable(IntCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static IntCollection asCollection(IntIterable iterable)
  {
    if ((iterable instanceof IntCollection)) {
      return (IntCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractIntCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntIterable iterable;
    
    protected IterableCollection(IntIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      IntIterator iterator = iterator();
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
    
    public IntIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public IntIterator intIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements IntCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntCollection collection;
    
    protected UnmodifiableCollection(IntCollection local_c)
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
    
    public boolean contains(int local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public IntIterator iterator()
    {
      return IntIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public IntIterator intIterator()
    {
      return iterator();
    }
    
    public boolean add(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Integer> local_c)
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
    
    public int[] toIntArray()
    {
      return this.collection.toIntArray();
    }
    
    public int[] toIntArray(int[] local_a)
    {
      return this.collection.toIntArray(local_a);
    }
    
    public int[] toArray(int[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(IntCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements IntCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(IntCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(IntCollection local_c)
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
    
    public boolean contains(int local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public int[] toIntArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toIntArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public int[] toIntArray(int[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toIntArray(local_a);
      }
    }
    
    public int[] toArray(int[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toIntArray(local_a);
      }
    }
    
    public boolean addAll(IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Integer local_k)
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
    
    public IntIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public IntIterator intIterator()
    {
      return iterator();
    }
    
    public boolean add(int local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(int local_k)
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
    
    public boolean addAll(Collection<? extends Integer> local_c)
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
    extends AbstractIntCollection
  {
    public boolean add(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(int local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public int[] toIntArray(int[] local_a)
    {
      return local_a;
    }
    
    public int[] toIntArray()
    {
      return IntArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(IntCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public IntBidirectionalIterator iterator()
    {
      return IntIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */