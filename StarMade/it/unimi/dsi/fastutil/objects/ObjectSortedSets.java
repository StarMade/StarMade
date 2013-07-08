package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static <K> ObjectSortedSet<K> singleton(K element)
  {
    return new Singleton(element, null);
  }
  
  public static <K> ObjectSortedSet<K> singleton(K element, Comparator<? super K> comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static <K> ObjectSortedSet<K> unmodifiable(ObjectSortedSet<K> local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet<K>
    extends ObjectSets.UnmodifiableSet<K>
    implements ObjectSortedSet<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectSortedSet<K> sortedSet;
    
    protected UnmodifiableSortedSet(ObjectSortedSet<K> local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return ObjectIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return ObjectIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public ObjectBidirectionalIterator<K> objectIterator()
    {
      return iterator();
    }
    
    public K first()
    {
      return this.sortedSet.first();
    }
    
    public K last()
    {
      return this.sortedSet.last();
    }
  }
  
  public static class SynchronizedSortedSet<K>
    extends ObjectSets.SynchronizedSet<K>
    implements ObjectSortedSet<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectSortedSet<K> sortedSet;
    
    protected SynchronizedSortedSet(ObjectSortedSet<K> local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(ObjectSortedSet<K> local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public Comparator<? super K> comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public ObjectBidirectionalIterator<K> objectIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public K first()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.first();
      }
    }
    
    public K last()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.last();
      }
    }
  }
  
  public static class Singleton<K>
    extends ObjectSets.Singleton<K>
    implements ObjectSortedSet<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final Comparator<? super K> comparator;
    
    private Singleton(K element, Comparator<? super K> comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(K element)
    {
      this(element, null);
    }
    
    final int compare(K local_k1, K local_k2)
    {
      return this.comparator == null ? ((Comparable)local_k1).compareTo(local_k2) : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public ObjectBidirectionalIterator<K> objectIterator()
    {
      return iterator();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      ObjectBidirectionalIterator<K> local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public Comparator<? super K> comparator()
    {
      return this.comparator;
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public K first()
    {
      return this.element;
    }
    
    public K last()
    {
      return this.element;
    }
  }
  
  public static class EmptySet<K>
    extends ObjectSets.EmptySet<K>
    implements ObjectSortedSet<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(Object local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public ObjectBidirectionalIterator<K> objectIterator()
    {
      return iterator();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return ObjectIterators.EMPTY_ITERATOR;
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> headSet(K from)
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public ObjectSortedSet<K> tailSet(K local_to)
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    public K first()
    {
      throw new NoSuchElementException();
    }
    
    public K last()
    {
      throw new NoSuchElementException();
    }
    
    public Comparator<? super K> comparator()
    {
      return null;
    }
    
    public Object clone()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ObjectSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */