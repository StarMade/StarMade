package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class ByteCollections
{
  public static ByteCollection synchronize(ByteCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static ByteCollection synchronize(ByteCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static ByteCollection unmodifiable(ByteCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static ByteCollection asCollection(ByteIterable iterable)
  {
    if ((iterable instanceof ByteCollection)) {
      return (ByteCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractByteCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteIterable iterable;
    
    protected IterableCollection(ByteIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      ByteIterator iterator = iterator();
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
    
    public ByteIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public ByteIterator byteIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements ByteCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteCollection collection;
    
    protected UnmodifiableCollection(ByteCollection local_c)
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
    
    public boolean contains(byte local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public ByteIterator iterator()
    {
      return ByteIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public ByteIterator byteIterator()
    {
      return iterator();
    }
    
    public boolean add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Byte> local_c)
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
    
    public byte[] toByteArray()
    {
      return this.collection.toByteArray();
    }
    
    public byte[] toByteArray(byte[] local_a)
    {
      return this.collection.toByteArray(local_a);
    }
    
    public byte[] toArray(byte[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(ByteCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements ByteCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(ByteCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(ByteCollection local_c)
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
    
    public boolean contains(byte local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public byte[] toByteArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toByteArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public byte[] toByteArray(byte[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toByteArray(local_a);
      }
    }
    
    public byte[] toArray(byte[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toByteArray(local_a);
      }
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Byte local_k)
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
    
    public ByteIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public ByteIterator byteIterator()
    {
      return iterator();
    }
    
    public boolean add(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(byte local_k)
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
    
    public boolean addAll(Collection<? extends Byte> local_c)
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
    extends AbstractByteCollection
  {
    public boolean add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(byte local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public byte[] toByteArray(byte[] local_a)
    {
      return local_a;
    }
    
    public byte[] toByteArray()
    {
      return ByteArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(ByteCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return ByteIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */