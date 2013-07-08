package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ShortSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static ShortSortedSet singleton(short element)
  {
    return new Singleton(element, null);
  }
  
  public static ShortSortedSet singleton(short element, ShortComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static ShortSortedSet singleton(Object element)
  {
    return new Singleton(((Short)element).shortValue(), null);
  }
  
  public static ShortSortedSet singleton(Object element, ShortComparator comparator)
  {
    return new Singleton(((Short)element).shortValue(), comparator, null);
  }
  
  public static ShortSortedSet synchronize(ShortSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static ShortSortedSet synchronize(ShortSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static ShortSortedSet unmodifiable(ShortSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends ShortSets.UnmodifiableSet
    implements ShortSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(ShortSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public ShortComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return ShortIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return ShortIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public ShortBidirectionalIterator shortIterator()
    {
      return iterator();
    }
    
    public short firstShort()
    {
      return this.sortedSet.firstShort();
    }
    
    public short lastShort()
    {
      return this.sortedSet.lastShort();
    }
    
    public Short first()
    {
      return (Short)this.sortedSet.first();
    }
    
    public Short last()
    {
      return (Short)this.sortedSet.last();
    }
    
    public ShortSortedSet subSet(Short from, Short local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public ShortSortedSet headSet(Short local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public ShortSortedSet tailSet(Short from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends ShortSets.SynchronizedSet
    implements ShortSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortSortedSet sortedSet;
    
    protected SynchronizedSortedSet(ShortSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(ShortSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public ShortComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public ShortBidirectionalIterator shortIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public short firstShort()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstShort();
      }
    }
    
    public short lastShort()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastShort();
      }
    }
    
    public Short first()
    {
      synchronized (this.sync)
      {
        return (Short)this.sortedSet.first();
      }
    }
    
    public Short last()
    {
      synchronized (this.sync)
      {
        return (Short)this.sortedSet.last();
      }
    }
    
    public ShortSortedSet subSet(Short from, Short local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public ShortSortedSet headSet(Short local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public ShortSortedSet tailSet(Short from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends ShortSets.Singleton
    implements ShortSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final ShortComparator comparator;
    
    private Singleton(short element, ShortComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(short element)
    {
      this(element, null);
    }
    
    final int compare(short local_k1, short local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public ShortBidirectionalIterator shortIterator()
    {
      return iterator();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      ShortBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public ShortComparator comparator()
    {
      return this.comparator;
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet tailSet(short from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return ShortSortedSets.EMPTY_SET;
    }
    
    public short firstShort()
    {
      return this.element;
    }
    
    public short lastShort()
    {
      return this.element;
    }
    
    public Short first()
    {
      return Short.valueOf(this.element);
    }
    
    public Short last()
    {
      return Short.valueOf(this.element);
    }
    
    public ShortSortedSet subSet(Short from, Short local_to)
    {
      return subSet(from.shortValue(), local_to.shortValue());
    }
    
    public ShortSortedSet headSet(Short local_to)
    {
      return headSet(local_to.shortValue());
    }
    
    public ShortSortedSet tailSet(Short from)
    {
      return tailSet(from.shortValue());
    }
  }
  
  public static class EmptySet
    extends ShortSets.EmptySet
    implements ShortSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(short local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public ShortBidirectionalIterator shortIterator()
    {
      return iterator();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return ShortIterators.EMPTY_ITERATOR;
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet headSet(short from)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet tailSet(short local_to)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public short firstShort()
    {
      throw new NoSuchElementException();
    }
    
    public short lastShort()
    {
      throw new NoSuchElementException();
    }
    
    public ShortComparator comparator()
    {
      return null;
    }
    
    public ShortSortedSet subSet(Short from, Short local_to)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet headSet(Short from)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public ShortSortedSet tailSet(Short local_to)
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    public Short first()
    {
      throw new NoSuchElementException();
    }
    
    public Short last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return ShortSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ShortSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */