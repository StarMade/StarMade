package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class ShortCollections
{
  public static ShortCollection synchronize(ShortCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static ShortCollection synchronize(ShortCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static ShortCollection unmodifiable(ShortCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static ShortCollection asCollection(ShortIterable iterable)
  {
    if ((iterable instanceof ShortCollection)) {
      return (ShortCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractShortCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortIterable iterable;
    
    protected IterableCollection(ShortIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      ShortIterator iterator = iterator();
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
    
    public ShortIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public ShortIterator shortIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements ShortCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortCollection collection;
    
    protected UnmodifiableCollection(ShortCollection local_c)
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
    
    public boolean contains(short local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public ShortIterator iterator()
    {
      return ShortIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public ShortIterator shortIterator()
    {
      return iterator();
    }
    
    public boolean add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Short> local_c)
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
    
    public short[] toShortArray()
    {
      return this.collection.toShortArray();
    }
    
    public short[] toShortArray(short[] local_a)
    {
      return this.collection.toShortArray(local_a);
    }
    
    public short[] toArray(short[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(ShortCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements ShortCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(ShortCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(ShortCollection local_c)
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
    
    public boolean contains(short local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public short[] toShortArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toShortArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public short[] toShortArray(short[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toShortArray(local_a);
      }
    }
    
    public short[] toArray(short[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toShortArray(local_a);
      }
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Short local_k)
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
    
    public ShortIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public ShortIterator shortIterator()
    {
      return iterator();
    }
    
    public boolean add(short local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(short local_k)
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
    
    public boolean addAll(Collection<? extends Short> local_c)
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
    extends AbstractShortCollection
  {
    public boolean add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(short local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public short[] toShortArray(short[] local_a)
    {
      return local_a;
    }
    
    public short[] toShortArray()
    {
      return ShortArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(ShortCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return ShortIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */