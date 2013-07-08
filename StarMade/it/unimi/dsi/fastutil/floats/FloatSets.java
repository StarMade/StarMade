package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.Collection;

public class FloatSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static FloatSet singleton(float element)
  {
    return new Singleton(element);
  }
  
  public static FloatSet singleton(Float element)
  {
    return new Singleton(element.floatValue());
  }
  
  public static FloatSet synchronize(FloatSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static FloatSet synchronize(FloatSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static FloatSet unmodifiable(FloatSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends FloatCollections.UnmodifiableCollection
    implements FloatSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(FloatSet local_s)
    {
      super();
    }
    
    public boolean remove(float local_k)
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
    extends FloatCollections.SynchronizedCollection
    implements FloatSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(FloatSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(FloatSet local_s)
    {
      super();
    }
    
    public boolean remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Float.valueOf(local_k));
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
    extends AbstractFloatSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final float element;
    
    protected Singleton(float element)
    {
      this.element = element;
    }
    
    public boolean add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(float local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Float> local_c)
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
    
    public float[] toFloatArray()
    {
      float[] local_a = new float[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public FloatListIterator iterator()
    {
      return FloatIterators.singleton(this.element);
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
    extends FloatCollections.EmptyCollection
    implements FloatSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(float local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return FloatSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return FloatSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */