package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class IntSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static IntSortedSet singleton(int element)
  {
    return new Singleton(element, null);
  }
  
  public static IntSortedSet singleton(int element, IntComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static IntSortedSet singleton(Object element)
  {
    return new Singleton(((Integer)element).intValue(), null);
  }
  
  public static IntSortedSet singleton(Object element, IntComparator comparator)
  {
    return new Singleton(((Integer)element).intValue(), comparator, null);
  }
  
  public static IntSortedSet synchronize(IntSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static IntSortedSet synchronize(IntSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static IntSortedSet unmodifiable(IntSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends IntSets.UnmodifiableSet
    implements IntSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(IntSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public IntComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public IntSortedSet tailSet(int from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return IntIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return IntIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public IntBidirectionalIterator intIterator()
    {
      return iterator();
    }
    
    public int firstInt()
    {
      return this.sortedSet.firstInt();
    }
    
    public int lastInt()
    {
      return this.sortedSet.lastInt();
    }
    
    public Integer first()
    {
      return (Integer)this.sortedSet.first();
    }
    
    public Integer last()
    {
      return (Integer)this.sortedSet.last();
    }
    
    public IntSortedSet subSet(Integer from, Integer local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public IntSortedSet headSet(Integer local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public IntSortedSet tailSet(Integer from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends IntSets.SynchronizedSet
    implements IntSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntSortedSet sortedSet;
    
    protected SynchronizedSortedSet(IntSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(IntSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public IntComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public IntSortedSet tailSet(int from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public IntBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public IntBidirectionalIterator intIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public int firstInt()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstInt();
      }
    }
    
    public int lastInt()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastInt();
      }
    }
    
    public Integer first()
    {
      synchronized (this.sync)
      {
        return (Integer)this.sortedSet.first();
      }
    }
    
    public Integer last()
    {
      synchronized (this.sync)
      {
        return (Integer)this.sortedSet.last();
      }
    }
    
    public IntSortedSet subSet(Integer from, Integer local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public IntSortedSet headSet(Integer local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public IntSortedSet tailSet(Integer from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends IntSets.Singleton
    implements IntSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final IntComparator comparator;
    
    private Singleton(int element, IntComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(int element)
    {
      this(element, null);
    }
    
    final int compare(int local_k1, int local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public IntBidirectionalIterator intIterator()
    {
      return iterator();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      IntBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public IntComparator comparator()
    {
      return this.comparator;
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet headSet(int local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet tailSet(int from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return IntSortedSets.EMPTY_SET;
    }
    
    public int firstInt()
    {
      return this.element;
    }
    
    public int lastInt()
    {
      return this.element;
    }
    
    public Integer first()
    {
      return Integer.valueOf(this.element);
    }
    
    public Integer last()
    {
      return Integer.valueOf(this.element);
    }
    
    public IntSortedSet subSet(Integer from, Integer local_to)
    {
      return subSet(from.intValue(), local_to.intValue());
    }
    
    public IntSortedSet headSet(Integer local_to)
    {
      return headSet(local_to.intValue());
    }
    
    public IntSortedSet tailSet(Integer from)
    {
      return tailSet(from.intValue());
    }
  }
  
  public static class EmptySet
    extends IntSets.EmptySet
    implements IntSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(int local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public IntBidirectionalIterator intIterator()
    {
      return iterator();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return IntIterators.EMPTY_ITERATOR;
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet headSet(int from)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet tailSet(int local_to)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public int firstInt()
    {
      throw new NoSuchElementException();
    }
    
    public int lastInt()
    {
      throw new NoSuchElementException();
    }
    
    public IntComparator comparator()
    {
      return null;
    }
    
    public IntSortedSet subSet(Integer from, Integer local_to)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet headSet(Integer from)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public IntSortedSet tailSet(Integer local_to)
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    public Integer first()
    {
      throw new NoSuchElementException();
    }
    
    public Integer last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return IntSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return IntSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */