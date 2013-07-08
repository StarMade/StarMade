package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class BooleanCollections
{
  public static BooleanCollection synchronize(BooleanCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static BooleanCollection synchronize(BooleanCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static BooleanCollection unmodifiable(BooleanCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static BooleanCollection asCollection(BooleanIterable iterable)
  {
    if ((iterable instanceof BooleanCollection)) {
      return (BooleanCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractBooleanCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanIterable iterable;
    
    protected IterableCollection(BooleanIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      BooleanIterator iterator = iterator();
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
    
    public BooleanIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public BooleanIterator booleanIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements BooleanCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanCollection collection;
    
    protected UnmodifiableCollection(BooleanCollection local_c)
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
    
    public boolean contains(boolean local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public BooleanIterator iterator()
    {
      return BooleanIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public BooleanIterator booleanIterator()
    {
      return iterator();
    }
    
    public boolean add(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Boolean> local_c)
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
    
    public boolean[] toBooleanArray()
    {
      return this.collection.toBooleanArray();
    }
    
    public boolean[] toBooleanArray(boolean[] local_a)
    {
      return this.collection.toBooleanArray(local_a);
    }
    
    public boolean[] toArray(boolean[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(BooleanCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements BooleanCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(BooleanCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(BooleanCollection local_c)
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
    
    public boolean contains(boolean local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public boolean[] toBooleanArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toBooleanArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public boolean[] toBooleanArray(boolean[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toBooleanArray(local_a);
      }
    }
    
    public boolean[] toArray(boolean[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toBooleanArray(local_a);
      }
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Boolean local_k)
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
    
    public BooleanIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public BooleanIterator booleanIterator()
    {
      return iterator();
    }
    
    public boolean add(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(boolean local_k)
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
    
    public boolean addAll(Collection<? extends Boolean> local_c)
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
    extends AbstractBooleanCollection
  {
    public boolean add(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(boolean local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public boolean[] toBooleanArray(boolean[] local_a)
    {
      return local_a;
    }
    
    public boolean[] toBooleanArray()
    {
      return BooleanArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(BooleanCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public BooleanBidirectionalIterator iterator()
    {
      return BooleanIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */