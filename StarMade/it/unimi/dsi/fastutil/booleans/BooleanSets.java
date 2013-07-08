package it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Collection;

public class BooleanSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static BooleanSet singleton(boolean element)
  {
    return new Singleton(element);
  }
  
  public static BooleanSet singleton(Boolean element)
  {
    return new Singleton(element.booleanValue());
  }
  
  public static BooleanSet synchronize(BooleanSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static BooleanSet synchronize(BooleanSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static BooleanSet unmodifiable(BooleanSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends BooleanCollections.UnmodifiableCollection
    implements BooleanSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(BooleanSet local_s)
    {
      super();
    }
    
    public boolean remove(boolean local_k)
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
    extends BooleanCollections.SynchronizedCollection
    implements BooleanSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(BooleanSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(BooleanSet local_s)
    {
      super();
    }
    
    public boolean remove(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Boolean.valueOf(local_k));
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
    extends AbstractBooleanSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final boolean element;
    
    protected Singleton(boolean element)
    {
      this.element = element;
    }
    
    public boolean add(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(boolean local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Boolean> local_c)
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
    
    public boolean[] toBooleanArray()
    {
      boolean[] local_a = new boolean[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public BooleanListIterator iterator()
    {
      return BooleanIterators.singleton(this.element);
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
    extends BooleanCollections.EmptyCollection
    implements BooleanSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(boolean local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return BooleanSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return BooleanSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */