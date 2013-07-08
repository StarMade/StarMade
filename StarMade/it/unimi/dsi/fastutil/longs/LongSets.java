package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Collection;

public class LongSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static LongSet singleton(long element)
  {
    return new Singleton(element);
  }
  
  public static LongSet singleton(Long element)
  {
    return new Singleton(element.longValue());
  }
  
  public static LongSet synchronize(LongSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static LongSet synchronize(LongSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static LongSet unmodifiable(LongSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends LongCollections.UnmodifiableCollection
    implements LongSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(LongSet local_s)
    {
      super();
    }
    
    public boolean remove(long local_k)
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
    extends LongCollections.SynchronizedCollection
    implements LongSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(LongSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(LongSet local_s)
    {
      super();
    }
    
    public boolean remove(long local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Long.valueOf(local_k));
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
    extends AbstractLongSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final long element;
    
    protected Singleton(long element)
    {
      this.element = element;
    }
    
    public boolean add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(long local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Long> local_c)
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
    
    public long[] toLongArray()
    {
      long[] local_a = new long[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public LongListIterator iterator()
    {
      return LongIterators.singleton(this.element);
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
    extends LongCollections.EmptyCollection
    implements LongSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return LongSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return LongSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */