package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Collection;

public class DoubleSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static DoubleSet singleton(double element)
  {
    return new Singleton(element);
  }
  
  public static DoubleSet singleton(Double element)
  {
    return new Singleton(element.doubleValue());
  }
  
  public static DoubleSet synchronize(DoubleSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static DoubleSet synchronize(DoubleSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static DoubleSet unmodifiable(DoubleSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends DoubleCollections.UnmodifiableCollection
    implements DoubleSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(DoubleSet local_s)
    {
      super();
    }
    
    public boolean remove(double local_k)
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
    extends DoubleCollections.SynchronizedCollection
    implements DoubleSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(DoubleSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(DoubleSet local_s)
    {
      super();
    }
    
    public boolean remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Double.valueOf(local_k));
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
    extends AbstractDoubleSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final double element;
    
    protected Singleton(double element)
    {
      this.element = element;
    }
    
    public boolean add(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(double local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Double> local_c)
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
    
    public double[] toDoubleArray()
    {
      double[] local_a = new double[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public boolean addAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(DoubleCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public DoubleListIterator iterator()
    {
      return DoubleIterators.singleton(this.element);
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
    extends DoubleCollections.EmptyCollection
    implements DoubleSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return DoubleSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return DoubleSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */