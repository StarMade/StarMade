package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.io.Serializable;
import java.util.Collection;

public class CharCollections
{
  public static CharCollection synchronize(CharCollection local_c)
  {
    return new SynchronizedCollection(local_c);
  }
  
  public static CharCollection synchronize(CharCollection local_c, Object sync)
  {
    return new SynchronizedCollection(local_c, sync);
  }
  
  public static CharCollection unmodifiable(CharCollection local_c)
  {
    return new UnmodifiableCollection(local_c);
  }
  
  public static CharCollection asCollection(CharIterable iterable)
  {
    if ((iterable instanceof CharCollection)) {
      return (CharCollection)iterable;
    }
    return new IterableCollection(iterable);
  }
  
  public static class IterableCollection
    extends AbstractCharCollection
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharIterable iterable;
    
    protected IterableCollection(CharIterable iterable)
    {
      if (iterable == null) {
        throw new NullPointerException();
      }
      this.iterable = iterable;
    }
    
    public int size()
    {
      int local_c = 0;
      CharIterator iterator = iterator();
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
    
    public CharIterator iterator()
    {
      return this.iterable.iterator();
    }
    
    @Deprecated
    public CharIterator charIterator()
    {
      return iterator();
    }
  }
  
  public static class UnmodifiableCollection
    implements CharCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharCollection collection;
    
    protected UnmodifiableCollection(CharCollection local_c)
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
    
    public boolean contains(char local_o)
    {
      return this.collection.contains(local_o);
    }
    
    public CharIterator iterator()
    {
      return CharIterators.unmodifiable(this.collection.iterator());
    }
    
    @Deprecated
    public CharIterator charIterator()
    {
      return iterator();
    }
    
    public boolean add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Character> local_c)
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
    
    public char[] toCharArray()
    {
      return this.collection.toCharArray();
    }
    
    public char[] toCharArray(char[] local_a)
    {
      return this.collection.toCharArray(local_a);
    }
    
    public char[] toArray(char[] local_a)
    {
      return this.collection.toArray(local_a);
    }
    
    public boolean rem(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(CharCollection local_c)
    {
      return this.collection.containsAll(local_c);
    }
    
    public boolean removeAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return this.collection.contains(local_k);
    }
  }
  
  public static class SynchronizedCollection
    implements CharCollection, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharCollection collection;
    protected final Object sync;
    
    protected SynchronizedCollection(CharCollection local_c, Object sync)
    {
      if (local_c == null) {
        throw new NullPointerException();
      }
      this.collection = local_c;
      this.sync = sync;
    }
    
    protected SynchronizedCollection(CharCollection local_c)
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
    
    public boolean contains(char local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.contains(local_o);
      }
    }
    
    public char[] toCharArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toCharArray();
      }
    }
    
    public Object[] toArray()
    {
      synchronized (this.sync)
      {
        return this.collection.toArray();
      }
    }
    
    public char[] toCharArray(char[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toCharArray(local_a);
      }
    }
    
    public char[] toArray(char[] local_a)
    {
      synchronized (this.sync)
      {
        return this.collection.toCharArray(local_a);
      }
    }
    
    public boolean addAll(CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.addAll(local_c);
      }
    }
    
    public boolean containsAll(CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.containsAll(local_c);
      }
    }
    
    public boolean removeAll(CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.removeAll(local_c);
      }
    }
    
    public boolean retainAll(CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.collection.retainAll(local_c);
      }
    }
    
    public boolean add(Character local_k)
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
    
    public CharIterator iterator()
    {
      return this.collection.iterator();
    }
    
    @Deprecated
    public CharIterator charIterator()
    {
      return iterator();
    }
    
    public boolean add(char local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.add(local_k);
      }
    }
    
    public boolean rem(char local_k)
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
    
    public boolean addAll(Collection<? extends Character> local_c)
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
    extends AbstractCharCollection
  {
    public boolean add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(char local_k)
    {
      return false;
    }
    
    public Object[] toArray()
    {
      return ObjectArrays.EMPTY_ARRAY;
    }
    
    public char[] toCharArray(char[] local_a)
    {
      return local_a;
    }
    
    public char[] toCharArray()
    {
      return CharArrays.EMPTY_ARRAY;
    }
    
    public boolean rem(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean containsAll(CharCollection local_c)
    {
      return local_c.isEmpty();
    }
    
    public CharBidirectionalIterator iterator()
    {
      return CharIterators.EMPTY_ITERATOR;
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */