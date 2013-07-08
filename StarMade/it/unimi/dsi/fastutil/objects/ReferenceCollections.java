package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Collection;

public class ReferenceCollections
{
  public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static <K> ReferenceCollection<K> unmodifiable(ReferenceCollection<K> local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static <K> ReferenceCollection<K> asCollection(ObjectIterable<K> iterable)
  {
    if ((iterable instanceof ReferenceCollection)) {
      return (ReferenceCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection<K>
    extends AbstractReferenceCollection<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectIterable<K> iterable;
    
    protected IterableCollection(ObjectIterable<K> iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      ObjectIterator<K> iterator = iterator();
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
    
    public ObjectIterator<K> iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public ObjectIterator<K> objectIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection<K>
    implements ReferenceCollection<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ReferenceCollection<K> collection;
    
    protected UnmodifiableCollection(ReferenceCollection<K> local_c)
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
    
    public boolean contains(Object local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public ObjectIterator<K> iterator()
    {
      return ObjectIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public ObjectIterator<K> objectIterator()
    {
      return iterator();
    }
    
    public boolean add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends K> local_c)
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
  }
  
  public static class SynchronizedCollection<K>
    implements ReferenceCollection<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ReferenceCollection<K> collection;
    protected final Object sync;
    
    protected SynchronizedCollection(ReferenceCollection<K> local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(ReferenceCollection<K> local_c)
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
    
    public boolean contains(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toArray(local_a);
      }
    }
    
    public ObjectIterator<K> iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public ObjectIterator<K> objectIterator()
    {
      return iterator();
    }
    
    public boolean add(K local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(local_k);
      }
    }
    
    public boolean remove(Object local_ok)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(local_ok);
      }
    }
    
    public boolean addAll(Collection<? extends K> local_c)
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
  
  public static abstract class EmptyCollection<K>
    extends AbstractReferenceCollection<K>
  {
    public boolean add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public boolean remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      return local_a;
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return ObjectIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */