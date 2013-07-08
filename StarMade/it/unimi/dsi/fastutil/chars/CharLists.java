package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CharLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static CharList shuffle(CharList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      char local_t = local_l.getChar(local_i);
      local_l.set(local_i, local_l.getChar(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static CharList singleton(char element)
  {
    return new Singleton(element, null);
  }
  
  public static CharList singleton(Object element)
  {
    return new Singleton(((Character)element).charValue(), null);
  }
  
  public static CharList synchronize(CharList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static CharList synchronize(CharList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static CharList unmodifiable(CharList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends CharCollections.UnmodifiableCollection
    implements CharList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharList list;
    
    protected UnmodifiableList(CharList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public char getChar(int local_i)
    {
      return this.list.getChar(local_i);
    }
    
    public char set(int local_i, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char removeChar(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(char local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(char local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, char[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, char[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, char[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public CharListIterator iterator()
    {
      return listIterator();
    }
    
    public CharListIterator listIterator()
    {
      return CharIterators.unmodifiable(this.list.listIterator());
    }
    
    public CharListIterator listIterator(int local_i)
    {
      return CharIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public CharListIterator charListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public CharListIterator charListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public CharList subList(int from, int local_to)
    {
      return CharLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public CharList charSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public boolean equals(Object local_o)
    {
      return this.collection.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.collection.hashCode();
    }
    
    public int compareTo(List<? extends Character> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, CharList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character get(int local_i)
    {
      return (Character)this.list.get(local_i);
    }
    
    public void add(int local_i, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character set(int index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character remove(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(Object local_o)
    {
      return this.list.indexOf(local_o);
    }
    
    public int lastIndexOf(Object local_o)
    {
      return this.list.lastIndexOf(local_o);
    }
  }
  
  public static class SynchronizedList
    extends CharCollections.SynchronizedCollection
    implements CharList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final CharList list;
    
    protected SynchronizedList(CharList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(CharList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public char getChar(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getChar(local_i);
      }
    }
    
    public char set(int local_i, char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, char local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public char removeChar(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeChar(local_i);
      }
    }
    
    public int indexOf(char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(char local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Character> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, char[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.getElements(from, local_a, offset, length);
      }
    }
    
    public void removeElements(int from, int local_to)
    {
      synchronized (this.sync)
      {
        this.list.removeElements(from, local_to);
      }
    }
    
    public void addElements(int index, char[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, char[] local_a)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a);
      }
    }
    
    public void size(int size)
    {
      synchronized (this.sync)
      {
        this.list.size(size);
      }
    }
    
    public CharListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public CharListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public CharListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public CharListIterator charListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public CharListIterator charListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public CharList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return CharLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public CharList charSubList(int from, int local_to)
    {
      return subList(from, local_to);
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
    
    public int compareTo(List<? extends Character> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, CharCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, CharList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(CharList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Character get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Character local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Character set(int index, Character local_k)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.set(index, local_k);
      }
    }
    
    public Character remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Character)this.list.remove(local_i);
      }
    }
    
    public int indexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_o);
      }
    }
    
    public int lastIndexOf(Object local_o)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_o);
      }
    }
  }
  
  public static class Singleton
    extends AbstractCharList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final char element;
    
    private Singleton(char element)
    {
      this.element = element;
    }
    
    public char getChar(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public char removeChar(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends Character> local_c)
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
    
    public CharListIterator listIterator()
    {
      return CharIterators.singleton(this.element);
    }
    
    public CharListIterator iterator()
    {
      return listIterator();
    }
    
    public CharListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      CharListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public CharList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return CharLists.EMPTY_LIST;
      }
      return this;
    }
    
    public int size()
    {
      return 1;
    }
    
    public void size(int size)
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
    
    public boolean addAll(int local_i, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends CharCollections.EmptyCollection
    implements CharList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char removeChar(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public char set(int index, char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(char local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(char local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Character> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(CharList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, CharCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, CharList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character set(int index, Character local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char getChar(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Character remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(Object local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(Object local_k)
    {
      return -1;
    }
    
    @Deprecated
    public CharIterator charIterator()
    {
      return CharIterators.EMPTY_ITERATOR;
    }
    
    public CharListIterator listIterator()
    {
      return CharIterators.EMPTY_ITERATOR;
    }
    
    public CharListIterator iterator()
    {
      return CharIterators.EMPTY_ITERATOR;
    }
    
    public CharListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return CharIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public CharListIterator charListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public CharListIterator charListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public CharList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public CharList charSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, char[] local_a, int offset, int length)
    {
      if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= local_a.length)) {
        return;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, char[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, char[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Character> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return CharLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return CharLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */