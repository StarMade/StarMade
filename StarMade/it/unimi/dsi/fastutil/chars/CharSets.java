package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.Collection;

public class CharSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static CharSet singleton(char element)
  {
    return new Singleton(element);
  }
  
  public static CharSet singleton(Character element)
  {
    return new Singleton(element.charValue());
  }
  
  public static CharSet synchronize(CharSet local_s)
  {
    return new SynchronizedSet(local_s);
  }
  
  public static CharSet synchronize(CharSet local_s, Object sync)
  {
    return new SynchronizedSet(local_s, sync);
  }
  
  public static CharSet unmodifiable(CharSet local_s)
  {
    return new UnmodifiableSet(local_s);
  }
  
  public static class UnmodifiableSet
    extends CharCollections.UnmodifiableCollection
    implements CharSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected UnmodifiableSet(CharSet local_s)
    {
      super();
    }
    
    public boolean remove(char local_k)
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
    extends CharCollections.SynchronizedCollection
    implements CharSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    protected SynchronizedSet(CharSet local_s, Object sync)
    {
      super(sync);
    }
    
    protected SynchronizedSet(CharSet local_s)
    {
      super();
    }
    
    public boolean remove(char local_k)
    {
      synchronized (this.sync)
      {
        return this.collection.remove(Character.valueOf(local_k));
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
    extends AbstractCharSet
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final char element;
    
    protected Singleton(char element)
    {
      this.element = element;
    }
    
    public boolean add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(char local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Character> local_c)
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
    
    public char[] toCharArray()
    {
      char[] local_a = new char[1];
      local_a[0] = this.element;
      return local_a;
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
    
    public CharListIterator iterator()
    {
      return CharIterators.singleton(this.element);
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
    extends CharCollections.EmptyCollection
    implements CharSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(char local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return CharSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return CharSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */