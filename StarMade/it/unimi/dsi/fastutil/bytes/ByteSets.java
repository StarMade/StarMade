package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Collection;

public class ByteSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static ByteSet singleton(byte element)
  {
    return new Singleton(element);
  }
  
  public static ByteSet singleton(Byte element)
  {
    return new Singleton(element.byteValue());
  }
  
  public static ByteSet synchronize(ByteSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static ByteSet synchronize(ByteSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static ByteSet unmodifiable(ByteSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends ByteCollections.UnmodifiableCollection
    implements ByteSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(ByteSet local_s)
    {
      super();
    }
    
    public boolean remove(byte local_k)
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
    extends ByteCollections.SynchronizedCollection
    implements ByteSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(ByteSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(ByteSet local_s)
    {
      super();
    }
    
    public boolean remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Byte.valueOf(local_k));
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
    extends AbstractByteSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final byte element;
    
    protected Singleton(byte element)
    {
      this.element = element;
    }
    
    public boolean add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(byte local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Byte> local_c)
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
    
    public byte[] toByteArray()
    {
      byte[] local_a = new byte[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public ByteListIterator iterator()
    {
      return ByteIterators.singleton(this.element);
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
    extends ByteCollections.EmptyCollection
    implements ByteSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return ByteSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ByteSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */