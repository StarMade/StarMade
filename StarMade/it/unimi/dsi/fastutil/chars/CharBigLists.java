package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class CharBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static CharBigList shuffle(CharBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      char local_t = local_l.getChar(local_i);
      local_l.set(local_i, local_l.getChar(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static CharBigList singleton(char element)
  {
    return new Singleton(element, null);
  }
  
  public static CharBigList singleton(Object element)
  {
    return new Singleton(((Character)element).charValue(), null);
  }
  
  public static CharBigList synchronize(CharBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static CharBigList synchronize(CharBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static CharBigList unmodifiable(CharBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static CharBigList asBigList(CharList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractCharBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final CharList list;
    
    protected ListBigList(CharList list)
    {
      this.list = list;
    }
    
    private int intIndex(long index)
    {
      if (index >= 2147483647L) {
        throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
      }
      return (int)index;
    }
    
    public long size64()
    {
      return this.list.size();
    }
    
    @Deprecated
    public int size()
    {
      return this.list.size();
    }
    
    public void size(long size)
    {
      this.list.size(intIndex(size));
    }
    
    public CharBigListIterator iterator()
    {
      return CharBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public CharBigListIterator listIterator()
    {
      return CharBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Character> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public CharBigListIterator listIterator(long index)
    {
      return CharBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public CharBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(char key)
    {
      return this.list.contains(key);
    }
    
    public char[] toCharArray()
    {
      return this.list.toCharArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public char[] toCharArray(char[] local_a)
    {
      return this.list.toCharArray(local_a);
    }
    
    public void add(long index, char key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, CharCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, CharBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(char key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(CharBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public char getChar(long index)
    {
      return this.list.getChar(intIndex(index));
    }
    
    public long indexOf(char local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(char local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public char removeChar(long index)
    {
      return this.list.removeChar(intIndex(index));
    }
    
    public char set(long index, char local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(CharCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(CharCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(CharCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(CharCollection local_c)
    {
      return this.list.retainAll(local_c);
    }
    
    public boolean isEmpty()
    {
      return this.list.isEmpty();
    }
    
    public <T> T[] toArray(T[] local_a)
    {
      return this.list.toArray(local_a);
    }
    
    public boolean containsAll(Collection<?> local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean addAll(Collection<? extends Character> local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(Collection<?> local_c)
    {
      return this.list.retainAll(local_c);
    }
    
    public void clear()
    {
      this.list.clear();
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
  }
  
  public static class UnmodifiableBigList
    extends CharCollections.UnmodifiableCollection
    implements CharBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharBigList list;
    
    protected UnmodifiableBigList(CharBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public char getChar(long local_i)
    {
      return this.list.getChar(local_i);
    }
    
    public char set(long local_i, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char removeChar(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(char local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(char local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, char[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, char[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, char[][] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(long size)
    {
      this.list.size(size);
    }
    
    public long size64()
    {
      return this.list.size64();
    }
    
    public CharBigListIterator iterator()
    {
      return listIterator();
    }
    
    public CharBigListIterator listIterator()
    {
      return CharBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public CharBigListIterator listIterator(long local_i)
    {
      return CharBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public CharBigList subList(long from, long local_to)
    {
      return CharBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Character> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, CharBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character get(long local_i)
    {
      return (Character)this.list.get(local_i);
    }
    
    public void add(long local_i, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character set(long index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character remove(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(Object local_o)
    {
      return this.list.indexOf(local_o);
    }
    
    public long lastIndexOf(Object local_o)
    {
      return this.list.lastIndexOf(local_o);
    }
  }
  
  public static class SynchronizedBigList
    extends CharCollections.SynchronizedCollection
    implements CharBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharBigList list;
    
    protected SynchronizedBigList(CharBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(CharBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public char getChar(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getChar(local_i);
      }
    }
    
    public char set(long local_i, char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, char local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public char removeChar(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeChar(local_i);
      }
    }
    
    public long indexOf(char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Character> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, char[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.getElements(from, local_a, offset, length);
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      synchronized (this.sync)
      {
        this.list.removeElements(from, local_to);
      }
    }
    
    public void addElements(long index, char[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, char[][] local_a)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a);
      }
    }
    
    public void size(long size)
    {
      synchronized (this.sync)
      {
        this.list.size(size);
      }
    }
    
    public long size64()
    {
      synchronized (this.sync)
      {
        return this.list.size64();
      }
    }
    
    public CharBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public CharBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public CharBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public CharBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return CharBigLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    public boolean equals(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.equals(local_o);
      }
    }
    
    public int hashCode()
    {
      synchronized (this.sync)
      {
        return this.list.hashCode();
      }
    }
    
    public int compareTo(BigList<? extends Character> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, CharBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(CharBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Character get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Character local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Character set(long index, Character local_k)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.set(index, local_k);
      }
    }
    
    public Character remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.remove(local_i);
      }
    }
    
    public long indexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_o);
      }
    }
    
    public long lastIndexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_o);
      }
    }
  }
  
  public static class Singleton
    extends AbstractCharBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final char element;
    
    private Singleton(char element)
    {
      this.element = element;
    }
    
    public char getChar(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public char removeChar(long local_i)
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
    
    public boolean addAll(long local_i, Collection<? extends Character> local_c)
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
    
    public CharBigListIterator listIterator()
    {
      return CharBigListIterators.singleton(this.element);
    }
    
    public CharBigListIterator iterator()
    {
      return listIterator();
    }
    
    public CharBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      CharBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public CharBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return CharBigLists.EMPTY_BIG_LIST;
      }
      return this;
    }
    
    @Deprecated
    public int size()
    {
      return 1;
    }
    
    public long size64()
    {
      return 1L;
    }
    
    public void size(long size)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      throw new UnsupportedOperationException();
    }
    
    public Object clone()
    {
      return this;
    }
    
    public boolean rem(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends CharCollections.EmptyCollection
    implements CharBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char removeChar(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public char set(long index, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(char local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(char local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, CharBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character set(long index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char getChar(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Character remove(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(Object local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(Object local_k)
    {
      return -1L;
    }
    
    public CharBigListIterator listIterator()
    {
      return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public CharBigListIterator iterator()
    {
      return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public CharBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public CharBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, char[][] local_a, long offset, long length)
    {
      CharBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, char[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, char[][] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(long local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public long size64()
    {
      return 0L;
    }
    
    public int compareTo(BigList<? extends Character> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return CharBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return CharBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */