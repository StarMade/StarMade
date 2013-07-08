package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.Collection;

public class ShortSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static ShortSet singleton(short element)
  {
    return new Singleton(element);
  }
  
  public static ShortSet singleton(Short element)
  {
    return new Singleton(element.shortValue());
  }
  
  public static ShortSet synchronize(ShortSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static ShortSet synchronize(ShortSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static ShortSet unmodifiable(ShortSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends ShortCollections.UnmodifiableCollection
    implements ShortSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(ShortSet local_s)
    {
      super();
    }
    
    public boolean remove(short local_k)
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
    extends ShortCollections.SynchronizedCollection
    implements ShortSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(ShortSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(ShortSet local_s)
    {
      super();
    }
    
    public boolean remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Short.valueOf(local_k));
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
    extends AbstractShortSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short element;
    
    protected Singleton(short element)
    {
      this.element = element;
    }
    
    public boolean add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(short local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Short> local_c)
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
    
    public short[] toShortArray()
    {
      short[] local_a = new short[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public ShortListIterator iterator()
    {
      return ShortIterators.singleton(this.element);
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
    extends ShortCollections.EmptyCollection
    implements ShortSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(short local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return ShortSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ShortSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */