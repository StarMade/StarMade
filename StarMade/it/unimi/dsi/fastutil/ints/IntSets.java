package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Collection;

public class IntSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static IntSet singleton(int element)
  {
    return new Singleton(element);
  }
  
  public static IntSet singleton(Integer element)
  {
    return new Singleton(element.intValue());
  }
  
  public static IntSet synchronize(IntSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static IntSet synchronize(IntSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static IntSet unmodifiable(IntSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends IntCollections.UnmodifiableCollection
    implements IntSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(IntSet local_s)
    {
      super();
    }
    
    public boolean remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean equals(Object local_o)
    {
      return this.collection.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.collection.hashCode();
    }
  }
  
  public static class SynchronizedSet
    extends IntCollections.SynchronizedCollection
    implements IntSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(IntSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(IntSet local_s)
    {
      super();
    }
    
    public boolean remove(int local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Integer.valueOf(local_k));
      }
    }
    
    public boolean equals(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.collection.equals(local_o);
      }
    }
    
    public int hashCode()
    {
      synchronized (this.sync)
      {
        return this.collection.hashCode();
      }
    }
  }
  
  public static class Singleton
    extends AbstractIntSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final int element;
    
    protected Singleton(int element)
    {
      this.element = element;
    }
    
    public boolean add(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(int local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public int[] toIntArray()
    {
      int[] local_a = new int[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public IntListIterator iterator()
    {
      return IntIterators.singleton(this.element);
    }
    
    public int size()
    {
      return 1;
    }
    
    public Object clone()
    {
      return this;
    }
  }
  
  public static class EmptySet
    extends IntCollections.EmptyCollection
    implements IntSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(int local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return IntSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return IntSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */