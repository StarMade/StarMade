package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class CharSortedSets
{
  public static final EmptySet EMPTY_SET = new EmptySet();
  
  public static CharSortedSet singleton(char element)
  {
    return new Singleton(element, null);
  }
  
  public static CharSortedSet singleton(char element, CharComparator comparator)
  {
    return new Singleton(element, comparator, null);
  }
  
  public static CharSortedSet singleton(Object element)
  {
    return new Singleton(((Character)element).charValue(), null);
  }
  
  public static CharSortedSet singleton(Object element, CharComparator comparator)
  {
    return new Singleton(((Character)element).charValue(), comparator, null);
  }
  
  public static CharSortedSet synchronize(CharSortedSet local_s)
  {
    return new SynchronizedSortedSet(local_s);
  }
  
  public static CharSortedSet synchronize(CharSortedSet local_s, Object sync)
  {
    return new SynchronizedSortedSet(local_s, sync);
  }
  
  public static CharSortedSet unmodifiable(CharSortedSet local_s)
  {
    return new UnmodifiableSortedSet(local_s);
  }
  
  public static class UnmodifiableSortedSet
    extends CharSets.UnmodifiableSet
    implements CharSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharSortedSet sortedSet;
    
    protected UnmodifiableSortedSet(CharSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public CharComparator comparator()
    {
      return this.sortedSet.comparator();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public CharSortedSet tailSet(char from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return CharIterators.unmodifiable(this.sortedSet.iterator());
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return CharIterators.unmodifiable(this.sortedSet.iterator(from));
    }
    
    @Deprecated
    public CharBidirectionalIterator charIterator()
    {
      return iterator();
    }
    
    public char firstChar()
    {
      return this.sortedSet.firstChar();
    }
    
    public char lastChar()
    {
      return this.sortedSet.lastChar();
    }
    
    public Character first()
    {
      return (Character)this.sortedSet.first();
    }
    
    public Character last()
    {
      return (Character)this.sortedSet.last();
    }
    
    public CharSortedSet subSet(Character from, Character local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.subSet(from, local_to));
    }
    
    public CharSortedSet headSet(Character local_to)
    {
      return new UnmodifiableSortedSet(this.sortedSet.headSet(local_to));
    }
    
    public CharSortedSet tailSet(Character from)
    {
      return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
    }
  }
  
  public static class SynchronizedSortedSet
    extends CharSets.SynchronizedSet
    implements CharSortedSet, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharSortedSet sortedSet;
    
    protected SynchronizedSortedSet(CharSortedSet local_s, Object sync)
    {
      super(sync);
      this.sortedSet = local_s;
    }
    
    protected SynchronizedSortedSet(CharSortedSet local_s)
    {
      super();
      this.sortedSet = local_s;
    }
    
    public CharComparator comparator()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.comparator();
      }
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public CharSortedSet tailSet(char from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
    
    public CharBidirectionalIterator iterator()
    {
      return this.sortedSet.iterator();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return this.sortedSet.iterator(from);
    }
    
    @Deprecated
    public CharBidirectionalIterator charIterator()
    {
      return this.sortedSet.iterator();
    }
    
    public char firstChar()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.firstChar();
      }
    }
    
    public char lastChar()
    {
      synchronized (this.sync)
      {
        return this.sortedSet.lastChar();
      }
    }
    
    public Character first()
    {
      synchronized (this.sync)
      {
        return (Character)this.sortedSet.first();
      }
    }
    
    public Character last()
    {
      synchronized (this.sync)
      {
        return (Character)this.sortedSet.last();
      }
    }
    
    public CharSortedSet subSet(Character from, Character local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.subSet(from, local_to), this.sync);
    }
    
    public CharSortedSet headSet(Character local_to)
    {
      return new SynchronizedSortedSet(this.sortedSet.headSet(local_to), this.sync);
    }
    
    public CharSortedSet tailSet(Character from)
    {
      return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
    }
  }
  
  public static class Singleton
    extends CharSets.Singleton
    implements CharSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    final CharComparator comparator;
    
    private Singleton(char element, CharComparator comparator)
    {
      super();
      this.comparator = comparator;
    }
    
    private Singleton(char element)
    {
      this(element, null);
    }
    
    final int compare(char local_k1, char local_k2)
    {
      return this.comparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.comparator.compare(local_k1, local_k2);
    }
    
    @Deprecated
    public CharBidirectionalIterator charIterator()
    {
      return iterator();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      CharBidirectionalIterator local_i = iterator();
      if (compare(this.element, from) <= 0) {
        local_i.next();
      }
      return local_i;
    }
    
    public CharComparator comparator()
    {
      return this.comparator;
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      if ((compare(from, this.element) <= 0) && (compare(this.element, local_to) < 0)) {
        return this;
      }
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet headSet(char local_to)
    {
      if (compare(this.element, local_to) < 0) {
        return this;
      }
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet tailSet(char from)
    {
      if (compare(from, this.element) <= 0) {
        return this;
      }
      return CharSortedSets.EMPTY_SET;
    }
    
    public char firstChar()
    {
      return this.element;
    }
    
    public char lastChar()
    {
      return this.element;
    }
    
    public Character first()
    {
      return Character.valueOf(this.element);
    }
    
    public Character last()
    {
      return Character.valueOf(this.element);
    }
    
    public CharSortedSet subSet(Character from, Character local_to)
    {
      return subSet(from.charValue(), local_to.charValue());
    }
    
    public CharSortedSet headSet(Character local_to)
    {
      return headSet(local_to.charValue());
    }
    
    public CharSortedSet tailSet(Character from)
    {
      return tailSet(from.charValue());
    }
  }
  
  public static class EmptySet
    extends CharSets.EmptySet
    implements CharSortedSet, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean remove(char local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    @Deprecated
    public CharBidirectionalIterator charIterator()
    {
      return iterator();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return CharIterators.EMPTY_ITERATOR;
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet headSet(char from)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet tailSet(char local_to)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public char firstChar()
    {
      throw new NoSuchElementException();
    }
    
    public char lastChar()
    {
      throw new NoSuchElementException();
    }
    
    public CharComparator comparator()
    {
      return null;
    }
    
    public CharSortedSet subSet(Character from, Character local_to)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet headSet(Character from)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public CharSortedSet tailSet(Character local_to)
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    public Character first()
    {
      throw new NoSuchElementException();
    }
    
    public Character last()
    {
      throw new NoSuchElementException();
    }
    
    public Object clone()
    {
      return CharSortedSets.EMPTY_SET;
    }
    
    private Object readResolve()
    {
      return CharSortedSets.EMPTY_SET;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */