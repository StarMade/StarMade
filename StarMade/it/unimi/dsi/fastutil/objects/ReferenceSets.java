package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Collection;

public class ReferenceSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static <K> ReferenceSet<K> singleton(K element)
  {
    return new Singleton(element);
  }
  
  public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static <K> ReferenceSet<K> unmodifiable(ReferenceSet<K> local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet<K>
    extends ReferenceCollections.UnmodifiableCollection<K>
    implements ReferenceSet<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(ReferenceSet<K> local_s)
    {
      super();
    }
    
    public boolean remove(Object local_k)
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
  
  public static class SynchronizedSet<K>
    extends ReferenceCollections.SynchronizedCollection<K>
    implements ReferenceSet<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(ReferenceSet<K> local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(ReferenceSet<K> local_s)
    {
      super();
    }
    
    public boolean remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(local_k);
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
  
  public static class Singleton<K>
    extends AbstractReferenceSet<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K element;
    
    protected Singleton(K element)
    {
      this.element = element;
    }
    
    public boolean add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends K> local_c)
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
    
    public ObjectListIterator<K> iterator()
    {
      return ObjectIterators.singleton(this.element);
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
  
  public static class EmptySet<K>
    extends ReferenceCollections.EmptyCollection<K>
    implements ReferenceSet<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return ReferenceSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ReferenceSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */