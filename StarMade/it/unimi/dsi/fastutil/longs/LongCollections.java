package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class LongCollections
{
  public static LongCollection synchronize(LongCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static LongCollection synchronize(LongCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static LongCollection unmodifiable(LongCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static LongCollection asCollection(LongIterable iterable)
  {
    if ((iterable instanceof LongCollection)) {
      return (LongCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractLongCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongIterable iterable;
    
    protected IterableCollection(LongIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      LongIterator iterator = iterator();
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
    
    public LongIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public LongIterator longIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements LongCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongCollection collection;
    
    protected UnmodifiableCollection(LongCollection local_c)
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
    
    public boolean contains(long local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public LongIterator iterator()
    {
      return LongIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public LongIterator longIterator()
    {
      return iterator();
    }
    
    public boolean add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Long> local_c)
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
    
    public long[] toLongArray()
    {
      return this.collection.toLongArray();
    }
    
    public long[] toLongArray(long[] local_a)
    {
      return this.collection.toLongArray(local_a);
    }
    
    public long[] toArray(long[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(LongCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements LongCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(LongCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(LongCollection local_c)
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
    
    public boolean contains(long local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public long[] toLongArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toLongArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public long[] toLongArray(long[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toLongArray(local_a);
      }
    }
    
    public long[] toArray(long[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toLongArray(local_a);
      }
    }
    
    public boolean addAll(LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Long local_k)
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
    
    public LongIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public LongIterator longIterator()
    {
      return iterator();
    }
    
    public boolean add(long local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(long local_k)
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
    
    public boolean addAll(Collection<? extends Long> local_c)
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
    extends AbstractLongCollection
  {
    public boolean add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(long local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public long[] toLongArray(long[] local_a)
    {
      return local_a;
    }
    
    public long[] toLongArray()
    {
      return LongArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(LongCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public LongBidirectionalIterator iterator()
    {
      return LongIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */