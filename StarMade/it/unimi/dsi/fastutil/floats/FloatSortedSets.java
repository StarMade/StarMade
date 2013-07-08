package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class FloatSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static FloatSortedSet singleton(float element)
  {
    return new Singleton(element, null);
  }
  
  public static FloatSortedSet singleton(float element, FloatComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static FloatSortedSet singleton(Object element)
  {
    return new Singleton(((Float)element).floatValue(), null);
  }
  
  public static FloatSortedSet singleton(Object element, FloatComparator comparator)
  {
    return new Singleton(((Float)element).floatValue(), comparator, null);
  }
  
  public static FloatSortedSet synchronize(FloatSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static FloatSortedSet synchronize(FloatSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static FloatSortedSet unmodifiable(FloatSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends FloatSets.UnmodifiableSet
    implements FloatSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(FloatSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public FloatComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return FloatIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return FloatIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public FloatBidirectionalIterator floatIterator()
    {
      return iterator();
    }
    
    public float firstFloat()
    {
      return this.sortedSet.firstFloat();
    }
    
    public float lastFloat()
    {
      return this.sortedSet.lastFloat();
    }
    
    public Float first()
    {
      return (Float)this.sortedSet.first();
    }
    
    public Float last()
    {
      return (Float)this.sortedSet.last();
    }
    
    public FloatSortedSet subSet(Float from, Float local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public FloatSortedSet headSet(Float local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public FloatSortedSet tailSet(Float from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends FloatSets.SynchronizedSet
    implements FloatSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatSortedSet sortedSet;
    
    protected SynchronizedSortedSet(FloatSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(FloatSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public FloatComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public FloatBidirectionalIterator floatIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public float firstFloat()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstFloat();
      }
    }
    
    public float lastFloat()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastFloat();
      }
    }
    
    public Float first()
    {
      synchronized (this.sync)
      {
        return (Float)this.sortedSet.first();
      }
    }
    
    public Float last()
    {
      synchronized (this.sync)
      {
        return (Float)this.sortedSet.last();
      }
    }
    
    public FloatSortedSet subSet(Float from, Float local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public FloatSortedSet headSet(Float local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public FloatSortedSet tailSet(Float from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends FloatSets.Singleton
    implements FloatSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final FloatComparator comparator;
    
    private Singleton(float element, FloatComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(float element)
    {
      this(element, null);
    }
    
    final int compare(float local_k1, float local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public FloatBidirectionalIterator floatIterator()
    {
      return iterator();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      FloatBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public FloatComparator comparator()
    {
      return this.comparator;
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet tailSet(float from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return FloatSortedSets.EMPTY_SET;
    }
    
    public float firstFloat()
    {
      return this.element;
    }
    
    public float lastFloat()
    {
      return this.element;
    }
    
    public Float first()
    {
      return Float.valueOf(this.element);
    }
    
    public Float last()
    {
      return Float.valueOf(this.element);
    }
    
    public FloatSortedSet subSet(Float from, Float local_to)
    {
      return subSet(from.floatValue(), local_to.floatValue());
    }
    
    public FloatSortedSet headSet(Float local_to)
    {
      return headSet(local_to.floatValue());
    }
    
    public FloatSortedSet tailSet(Float from)
    {
      return tailSet(from.floatValue());
    }
  }
  
  public static class EmptySet
    extends FloatSets.EmptySet
    implements FloatSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(float local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public FloatBidirectionalIterator floatIterator()
    {
      return iterator();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return FloatIterators.EMPTY_ITERATOR;
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet headSet(float from)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet tailSet(float local_to)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public float firstFloat()
    {
      throw new NoSuchElementException();
    }
    
    public float lastFloat()
    {
      throw new NoSuchElementException();
    }
    
    public FloatComparator comparator()
    {
      return null;
    }
    
    public FloatSortedSet subSet(Float from, Float local_to)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet headSet(Float from)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public FloatSortedSet tailSet(Float local_to)
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    public Float first()
    {
      throw new NoSuchElementException();
    }
    
    public Float last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return FloatSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return FloatSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */