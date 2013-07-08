package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class LongLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static LongList shuffle(LongList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      long local_t = local_l.getLong(local_i);
      local_l.set(local_i, local_l.getLong(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static LongList singleton(long element)
  {
    return new Singleton(element, null);
  }
  
  public static LongList singleton(Object element)
  {
    return new Singleton(((Long)element).longValue(), null);
  }
  
  public static LongList synchronize(LongList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static LongList synchronize(LongList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static LongList unmodifiable(LongList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends LongCollections.UnmodifiableCollection
    implements LongList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongList list;
    
    protected UnmodifiableList(LongList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public long getLong(int local_i)
    {
      return this.list.getLong(local_i);
    }
    
    public long set(int local_i, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long removeLong(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(long local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(long local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, long[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, long[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, long[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public LongListIterator iterator()
    {
      return listIterator();
    }
    
    public LongListIterator listIterator()
    {
      return LongIterators.unmodifiable(this.list.listIterator());
    }
    
    public LongListIterator listIterator(int local_i)
    {
      return LongIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public LongListIterator longListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public LongListIterator longListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public LongList subList(int from, int local_to)
    {
      return LongLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public LongList longSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Long> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, LongList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long get(int local_i)
    {
      return (Long)this.list.get(local_i);
    }
    
    public void add(int local_i, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long set(int index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long remove(int local_i)
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
    extends LongCollections.SynchronizedCollection
    implements LongList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final LongList list;
    
    protected SynchronizedList(LongList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(LongList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public long getLong(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getLong(local_i);
      }
    }
    
    public long set(int local_i, long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, long local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public long removeLong(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeLong(local_i);
      }
    }
    
    public int indexOf(long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(long local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Long> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, long[] local_a, int offset, int length)
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
    
    public void addElements(int index, long[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, long[] local_a)
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
    
    public LongListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public LongListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public LongListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public LongListIterator longListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public LongListIterator longListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public LongList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return LongLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public LongList longSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Long> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, LongCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, LongList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(LongList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Long get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Long local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Long set(int index, Long local_k)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.set(index, local_k);
      }
    }
    
    public Long remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Long)this.list.remove(local_i);
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
    extends AbstractLongList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final long element;
    
    private Singleton(long element)
    {
      this.element = element;
    }
    
    public long getLong(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public long removeLong(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends Long> local_c)
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
    
    public LongListIterator listIterator()
    {
      return LongIterators.singleton(this.element);
    }
    
    public LongListIterator iterator()
    {
      return listIterator();
    }
    
    public LongListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      LongListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public LongList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return LongLists.EMPTY_LIST;
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
    
    public boolean rem(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends LongCollections.EmptyCollection
    implements LongList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long removeLong(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long set(int index, long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(long local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(long local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Long> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(LongList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, LongCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, LongList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long set(int index, Long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long getLong(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Long remove(int local_k)
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
    public LongIterator longIterator()
    {
      return LongIterators.EMPTY_ITERATOR;
    }
    
    public LongListIterator listIterator()
    {
      return LongIterators.EMPTY_ITERATOR;
    }
    
    public LongListIterator iterator()
    {
      return LongIterators.EMPTY_ITERATOR;
    }
    
    public LongListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return LongIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public LongListIterator longListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public LongListIterator longListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public LongList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public LongList longSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, long[] local_a, int offset, int length)
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
    
    public void addElements(int index, long[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, long[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Long> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return LongLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return LongLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */