package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ByteSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static ByteSortedSet singleton(byte element)
  {
    return new Singleton(element, null);
  }
  
  public static ByteSortedSet singleton(byte element, ByteComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static ByteSortedSet singleton(Object element)
  {
    return new Singleton(((Byte)element).byteValue(), null);
  }
  
  public static ByteSortedSet singleton(Object element, ByteComparator comparator)
  {
    return new Singleton(((Byte)element).byteValue(), comparator, null);
  }
  
  public static ByteSortedSet synchronize(ByteSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static ByteSortedSet synchronize(ByteSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static ByteSortedSet unmodifiable(ByteSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends ByteSets.UnmodifiableSet
    implements ByteSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(ByteSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public ByteComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return ByteIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return ByteIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public ByteBidirectionalIterator byteIterator()
    {
      return iterator();
    }
    
    public byte firstByte()
    {
      return this.sortedSet.firstByte();
    }
    
    public byte lastByte()
    {
      return this.sortedSet.lastByte();
    }
    
    public Byte first()
    {
      return (Byte)this.sortedSet.first();
    }
    
    public Byte last()
    {
      return (Byte)this.sortedSet.last();
    }
    
    public ByteSortedSet subSet(Byte from, Byte local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public ByteSortedSet headSet(Byte local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public ByteSortedSet tailSet(Byte from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends ByteSets.SynchronizedSet
    implements ByteSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteSortedSet sortedSet;
    
    protected SynchronizedSortedSet(ByteSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(ByteSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public ByteComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public ByteBidirectionalIterator byteIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public byte firstByte()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstByte();
      }
    }
    
    public byte lastByte()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastByte();
      }
    }
    
    public Byte first()
    {
      synchronized (this.sync)
      {
        return (Byte)this.sortedSet.first();
      }
    }
    
    public Byte last()
    {
      synchronized (this.sync)
      {
        return (Byte)this.sortedSet.last();
      }
    }
    
    public ByteSortedSet subSet(Byte from, Byte local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public ByteSortedSet headSet(Byte local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public ByteSortedSet tailSet(Byte from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends ByteSets.Singleton
    implements ByteSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final ByteComparator comparator;
    
    private Singleton(byte element, ByteComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(byte element)
    {
      this(element, null);
    }
    
    final int compare(byte local_k1, byte local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public ByteBidirectionalIterator byteIterator()
    {
      return iterator();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      ByteBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public ByteComparator comparator()
    {
      return this.comparator;
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return ByteSortedSets.EMPTY_SET;
    }
    
    public byte firstByte()
    {
      return this.element;
    }
    
    public byte lastByte()
    {
      return this.element;
    }
    
    public Byte first()
    {
      return Byte.valueOf(this.element);
    }
    
    public Byte last()
    {
      return Byte.valueOf(this.element);
    }
    
    public ByteSortedSet subSet(Byte from, Byte local_to)
    {
      return subSet(from.byteValue(), local_to.byteValue());
    }
    
    public ByteSortedSet headSet(Byte local_to)
    {
      return headSet(local_to.byteValue());
    }
    
    public ByteSortedSet tailSet(Byte from)
    {
      return tailSet(from.byteValue());
    }
  }
  
  public static class EmptySet
    extends ByteSets.EmptySet
    implements ByteSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(byte local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public ByteBidirectionalIterator byteIterator()
    {
      return iterator();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet headSet(byte from)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet tailSet(byte local_to)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public byte firstByte()
    {
      throw new NoSuchElementException();
    }
    
    public byte lastByte()
    {
      throw new NoSuchElementException();
    }
    
    public ByteComparator comparator()
    {
      return null;
    }
    
    public ByteSortedSet subSet(Byte from, Byte local_to)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet headSet(Byte from)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public ByteSortedSet tailSet(Byte local_to)
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    public Byte first()
    {
      throw new NoSuchElementException();
    }
    
    public Byte last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return ByteSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return ByteSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */