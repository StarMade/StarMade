package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class FloatCollections
{
  public static FloatCollection synchronize(FloatCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static FloatCollection synchronize(FloatCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static FloatCollection unmodifiable(FloatCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static FloatCollection asCollection(FloatIterable iterable)
  {
    if ((iterable instanceof FloatCollection)) {
      return (FloatCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractFloatCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatIterable iterable;
    
    protected IterableCollection(FloatIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      FloatIterator iterator = iterator();
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
    
    public FloatIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public FloatIterator floatIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements FloatCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatCollection collection;
    
    protected UnmodifiableCollection(FloatCollection local_c)
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
    
    public boolean contains(float local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public FloatIterator iterator()
    {
      return FloatIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public FloatIterator floatIterator()
    {
      return iterator();
    }
    
    public boolean add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Float> local_c)
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
    
    public float[] toFloatArray()
    {
      return this.collection.toFloatArray();
    }
    
    public float[] toFloatArray(float[] local_a)
    {
      return this.collection.toFloatArray(local_a);
    }
    
    public float[] toArray(float[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(FloatCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements FloatCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(FloatCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(FloatCollection local_c)
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
    
    public boolean contains(float local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public float[] toFloatArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toFloatArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public float[] toFloatArray(float[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toFloatArray(local_a);
      }
    }
    
    public float[] toArray(float[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toFloatArray(local_a);
      }
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Float local_k)
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
    
    public FloatIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public FloatIterator floatIterator()
    {
      return iterator();
    }
    
    public boolean add(float local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(float local_k)
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
    
    public boolean addAll(Collection<? extends Float> local_c)
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
    extends AbstractFloatCollection
  {
    public boolean add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(float local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public float[] toFloatArray(float[] local_a)
    {
      return local_a;
    }
    
    public float[] toFloatArray()
    {
      return FloatArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(FloatCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return FloatIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */