package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class IntBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static IntBigList shuffle(IntBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      int local_t = local_l.getInt(local_i);
      local_l.set(local_i, local_l.getInt(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static IntBigList singleton(int element)
  {
    return new Singleton(element, null);
  }
  
  public static IntBigList singleton(Object element)
  {
    return new Singleton(((Integer)element).intValue(), null);
  }
  
  public static IntBigList synchronize(IntBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static IntBigList synchronize(IntBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static IntBigList unmodifiable(IntBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static IntBigList asBigList(IntList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractIntBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final IntList list;
    
    protected ListBigList(IntList list)
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
    
    public IntBigListIterator iterator()
    {
      return IntBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public IntBigListIterator listIterator()
    {
      return IntBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Integer> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public IntBigListIterator listIterator(long index)
    {
      return IntBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public IntBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(int key)
    {
      return this.list.contains(key);
    }
    
    public int[] toIntArray()
    {
      return this.list.toIntArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public int[] toIntArray(int[] local_a)
    {
      return this.list.toIntArray(local_a);
    }
    
    public void add(long index, int key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, IntCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, IntBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(int key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(IntBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public int getInt(long index)
    {
      return this.list.getInt(intIndex(index));
    }
    
    public long indexOf(int local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(int local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public int removeInt(long index)
    {
      return this.list.removeInt(intIndex(index));
    }
    
    public int set(long index, int local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(IntCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(IntCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(IntCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(IntCollection local_c)
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
    
    public boolean addAll(Collection<? extends Integer> local_c)
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
    extends IntCollections.UnmodifiableCollection
    implements IntBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntBigList list;
    
    protected UnmodifiableBigList(IntBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public int getInt(long local_i)
    {
      return this.list.getInt(local_i);
    }
    
    public int set(long local_i, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int removeInt(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(int local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(int local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, int[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, int[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, int[][] local_a)
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
    
    public IntBigListIterator iterator()
    {
      return listIterator();
    }
    
    public IntBigListIterator listIterator()
    {
      return IntBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public IntBigListIterator listIterator(long local_i)
    {
      return IntBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public IntBigList subList(long from, long local_to)
    {
      return IntBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Integer> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, IntBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer get(long local_i)
    {
      return (Integer)this.list.get(local_i);
    }
    
    public void add(long local_i, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer set(long index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer remove(long local_i)
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
    extends IntCollections.SynchronizedCollection
    implements IntBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final IntBigList list;
    
    protected SynchronizedBigList(IntBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(IntBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public int getInt(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getInt(local_i);
      }
    }
    
    public int set(long local_i, int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, int local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public int removeInt(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeInt(local_i);
      }
    }
    
    public long indexOf(int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(int local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Integer> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, int[][] local_a, long offset, long length)
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
    
    public void addElements(long index, int[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, int[][] local_a)
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
    
    public IntBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public IntBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public IntBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public IntBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return IntBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Integer> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, IntCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, IntBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(IntBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Integer get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Integer local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Integer set(long index, Integer local_k)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.set(index, local_k);
      }
    }
    
    public Integer remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Integer)this.list.remove(local_i);
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
    extends AbstractIntBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final int element;
    
    private Singleton(int element)
    {
      this.element = element;
    }
    
    public int getInt(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public int removeInt(long local_i)
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
    
    public boolean addAll(long local_i, Collection<? extends Integer> local_c)
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
    
    public IntBigListIterator listIterator()
    {
      return IntBigListIterators.singleton(this.element);
    }
    
    public IntBigListIterator iterator()
    {
      return listIterator();
    }
    
    public IntBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      IntBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public IntBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return IntBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends IntCollections.EmptyCollection
    implements IntBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int removeInt(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int set(long index, int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(int local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(int local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Integer> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(IntBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, IntCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, IntBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer set(long index, Integer local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int getInt(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Integer remove(long local_k)
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
    
    public IntBigListIterator listIterator()
    {
      return IntBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public IntBigListIterator iterator()
    {
      return IntBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public IntBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return IntBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public IntBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, int[][] local_a, long offset, long length)
    {
      IntBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, int[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, int[][] local_a)
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
    
    public int compareTo(BigList<? extends Integer> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return IntBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return IntBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */