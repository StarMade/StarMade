package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class IntLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static IntList shuffle(IntList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      int local_t = local_l.getInt(local_i);
      local_l.set(local_i, local_l.getInt(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static IntList singleton(int element)
  {
    return new Singleton(element, null);
  }
  
  public static IntList singleton(Object element)
  {
    return new Singleton(((Integer)element).intValue(), null);
  }
  
  public static IntList synchronize(IntList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static IntList synchronize(IntList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static IntList unmodifiable(IntList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends IntCollections.UnmodifiableCollection
    implements IntList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntList list;
    
    protected UnmodifiableList(IntList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public int getInt(int local_i)
    {
      return this.list.getInt(local_i);
    }
    
    public int set(int local_i, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int removeInt(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(int local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(int local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, int[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, int[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, int[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public IntListIterator iterator()
    {
      return listIterator();
    }
    
    public IntListIterator listIterator()
    {
      return IntIterators.unmodifiable(this.list.listIterator());
    }
    
    public IntListIterator listIterator(int local_i)
    {
      return IntIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public IntListIterator intListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public IntListIterator intListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public IntList subList(int from, int local_to)
    {
      return IntLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public IntList intSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Integer> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, IntList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer get(int local_i)
    {
      return (Integer)this.list.get(local_i);
    }
    
    public void add(int local_i, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer set(int index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer remove(int local_i)
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
    extends IntCollections.SynchronizedCollection
    implements IntList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntList list;
    
    protected SynchronizedList(IntList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(IntList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public int getInt(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getInt(local_i);
      }
    }
    
    public int set(int local_i, int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, int local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public int removeInt(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeInt(local_i);
      }
    }
    
    public int indexOf(int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Integer> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, int[] local_a, int offset, int length)
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
    
    public void addElements(int index, int[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, int[] local_a)
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
    
    public IntListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public IntListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public IntListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public IntListIterator intListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public IntListIterator intListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public IntList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return IntLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public IntList intSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Integer> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, IntList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(IntList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Integer get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Integer local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Integer set(int index, Integer local_k)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.set(index, local_k);
      }
    }
    
    public Integer remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.remove(local_i);
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
    extends AbstractIntList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final int element;
    
    private Singleton(int element)
    {
      this.element = element;
    }
    
    public int getInt(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public int removeInt(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(int local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Integer> local_c)
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
    
    public int[] toIntArray()
    {
      int[] local_a = new int[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public IntListIterator listIterator()
    {
      return IntIterators.singleton(this.element);
    }
    
    public IntListIterator iterator()
    {
      return listIterator();
    }
    
    public IntListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      IntListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public IntList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return IntLists.EMPTY_LIST;
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
    
    public boolean rem(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends IntCollections.EmptyCollection
    implements IntList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int removeInt(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int set(int index, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(int local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(int local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, IntList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer set(int index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int getInt(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Integer remove(int local_k)
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
    public IntIterator intIterator()
    {
      return IntIterators.EMPTY_ITERATOR;
    }
    
    public IntListIterator listIterator()
    {
      return IntIterators.EMPTY_ITERATOR;
    }
    
    public IntListIterator iterator()
    {
      return IntIterators.EMPTY_ITERATOR;
    }
    
    public IntListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return IntIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public IntListIterator intListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public IntListIterator intListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public IntList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public IntList intSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, int[] local_a, int offset, int length)
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
    
    public void addElements(int index, int[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, int[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Integer> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return IntLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return IntLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */