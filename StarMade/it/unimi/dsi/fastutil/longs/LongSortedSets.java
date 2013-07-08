package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class LongSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static LongSortedSet singleton(long element)
  {
    return new Singleton(element, null);
  }
  
  public static LongSortedSet singleton(long element, LongComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static LongSortedSet singleton(Object element)
  {
    return new Singleton(((Long)element).longValue(), null);
  }
  
  public static LongSortedSet singleton(Object element, LongComparator comparator)
  {
    return new Singleton(((Long)element).longValue(), comparator, null);
  }
  
  public static LongSortedSet synchronize(LongSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static LongSortedSet synchronize(LongSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static LongSortedSet unmodifiable(LongSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends LongSets.UnmodifiableSet
    implements LongSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(LongSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public LongComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public LongSortedSet tailSet(long from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return LongIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return LongIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public LongBidirectionalIterator longIterator()
    {
      return iterator();
    }
    
    public long firstLong()
    {
      return this.sortedSet.firstLong();
    }
    
    public long lastLong()
    {
      return this.sortedSet.lastLong();
    }
    
    public Long first()
    {
      return (Long)this.sortedSet.first();
    }
    
    public Long last()
    {
      return (Long)this.sortedSet.last();
    }
    
    public LongSortedSet subSet(Long from, Long local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public LongSortedSet headSet(Long local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public LongSortedSet tailSet(Long from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends LongSets.SynchronizedSet
    implements LongSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongSortedSet sortedSet;
    
    protected SynchronizedSortedSet(LongSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(LongSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public LongComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public LongSortedSet tailSet(long from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public LongBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public LongBidirectionalIterator longIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public long firstLong()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstLong();
      }
    }
    
    public long lastLong()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastLong();
      }
    }
    
    public Long first()
    {
      synchronized (this.sync)
      {
        return (Long)this.sortedSet.first();
      }
    }
    
    public Long last()
    {
      synchronized (this.sync)
      {
        return (Long)this.sortedSet.last();
      }
    }
    
    public LongSortedSet subSet(Long from, Long local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public LongSortedSet headSet(Long local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public LongSortedSet tailSet(Long from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends LongSets.Singleton
    implements LongSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final LongComparator comparator;
    
    private Singleton(long element, LongComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(long element)
    {
      this(element, null);
    }
    
    final int compare(long local_k1, long local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public LongBidirectionalIterator longIterator()
    {
      return iterator();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      LongBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public LongComparator comparator()
    {
      return this.comparator;
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet headSet(long local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet tailSet(long from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return LongSortedSets.EMPTY_SET;
    }
    
    public long firstLong()
    {
      return this.element;
    }
    
    public long lastLong()
    {
      return this.element;
    }
    
    public Long first()
    {
      return Long.valueOf(this.element);
    }
    
    public Long last()
    {
      return Long.valueOf(this.element);
    }
    
    public LongSortedSet subSet(Long from, Long local_to)
    {
      return subSet(from.longValue(), local_to.longValue());
    }
    
    public LongSortedSet headSet(Long local_to)
    {
      return headSet(local_to.longValue());
    }
    
    public LongSortedSet tailSet(Long from)
    {
      return tailSet(from.longValue());
    }
  }
  
  public static class EmptySet
    extends LongSets.EmptySet
    implements LongSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(long local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public LongBidirectionalIterator longIterator()
    {
      return iterator();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return LongIterators.EMPTY_ITERATOR;
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet headSet(long from)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet tailSet(long local_to)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public long firstLong()
    {
      throw new NoSuchElementException();
    }
    
    public long lastLong()
    {
      throw new NoSuchElementException();
    }
    
    public LongComparator comparator()
    {
      return null;
    }
    
    public LongSortedSet subSet(Long from, Long local_to)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet headSet(Long from)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public LongSortedSet tailSet(Long local_to)
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    public Long first()
    {
      throw new NoSuchElementException();
    }
    
    public Long last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return LongSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return LongSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */