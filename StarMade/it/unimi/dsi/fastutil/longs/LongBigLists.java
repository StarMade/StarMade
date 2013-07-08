package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class LongBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static LongBigList shuffle(LongBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      long local_t = local_l.getLong(local_i);
      local_l.set(local_i, local_l.getLong(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static LongBigList singleton(long element)
  {
    return new Singleton(element, null);
  }
  
  public static LongBigList singleton(Object element)
  {
    return new Singleton(((Long)element).longValue(), null);
  }
  
  public static LongBigList synchronize(LongBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static LongBigList synchronize(LongBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static LongBigList unmodifiable(LongBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static LongBigList asBigList(LongList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractLongBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final LongList list;
    
    protected ListBigList(LongList list)
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
    
    public LongBigListIterator iterator()
    {
      return LongBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public LongBigListIterator listIterator()
    {
      return LongBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Long> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public LongBigListIterator listIterator(long index)
    {
      return LongBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public LongBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(long key)
    {
      return this.list.contains(key);
    }
    
    public long[] toLongArray()
    {
      return this.list.toLongArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public long[] toLongArray(long[] local_a)
    {
      return this.list.toLongArray(local_a);
    }
    
    public void add(long index, long key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, LongCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, LongBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(long key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(LongBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public long getLong(long index)
    {
      return this.list.getLong(intIndex(index));
    }
    
    public long indexOf(long local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(long local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public long removeLong(long index)
    {
      return this.list.removeLong(intIndex(index));
    }
    
    public long set(long index, long local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(LongCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(LongCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(LongCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(LongCollection local_c)
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
    
    public boolean addAll(Collection<? extends Long> local_c)
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
    extends LongCollections.UnmodifiableCollection
    implements LongBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongBigList list;
    
    protected UnmodifiableBigList(LongBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public long getLong(long local_i)
    {
      return this.list.getLong(local_i);
    }
    
    public long set(long local_i, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long removeLong(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(long local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(long local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, long[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, long[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, long[][] local_a)
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
    
    public LongBigListIterator iterator()
    {
      return listIterator();
    }
    
    public LongBigListIterator listIterator()
    {
      return LongBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public LongBigListIterator listIterator(long local_i)
    {
      return LongBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public LongBigList subList(long from, long local_to)
    {
      return LongBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Long> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, LongBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long get(long local_i)
    {
      return (Long)this.list.get(local_i);
    }
    
    public void add(long local_i, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long set(long index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long remove(long local_i)
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
    extends LongCollections.SynchronizedCollection
    implements LongBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongBigList list;
    
    protected SynchronizedBigList(LongBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(LongBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public long getLong(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getLong(local_i);
      }
    }
    
    public long set(long local_i, long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, long local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public long removeLong(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeLong(local_i);
      }
    }
    
    public long indexOf(long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Long> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, long[][] local_a, long offset, long length)
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
    
    public void addElements(long index, long[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, long[][] local_a)
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
    
    public LongBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public LongBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public LongBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public LongBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return LongBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Long> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, LongBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(LongBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Long get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Long local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Long set(long index, Long local_k)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.set(index, local_k);
      }
    }
    
    public Long remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.remove(local_i);
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
    extends AbstractLongBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final long element;
    
    private Singleton(long element)
    {
      this.element = element;
    }
    
    public long getLong(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public long removeLong(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(long local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Long> local_c)
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
    
    public long[] toLongArray()
    {
      long[] local_a = new long[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public LongBigListIterator listIterator()
    {
      return LongBigListIterators.singleton(this.element);
    }
    
    public LongBigListIterator iterator()
    {
      return listIterator();
    }
    
    public LongBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      LongBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public LongBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return LongBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends LongCollections.EmptyCollection
    implements LongBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long removeLong(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long set(long index, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(long local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(long local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, LongBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long set(long index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long getLong(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Long remove(long local_k)
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
    
    public LongBigListIterator listIterator()
    {
      return LongBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public LongBigListIterator iterator()
    {
      return LongBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public LongBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return LongBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public LongBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, long[][] local_a, long offset, long length)
    {
      LongBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, long[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, long[][] local_a)
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
    
    public int compareTo(BigList<? extends Long> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return LongBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return LongBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */