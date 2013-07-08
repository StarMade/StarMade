package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class BooleanBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static BooleanBigList shuffle(BooleanBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      boolean local_t = local_l.getBoolean(local_i);
      local_l.set(local_i, local_l.getBoolean(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static BooleanBigList singleton(boolean element)
  {
    return new Singleton(element, null);
  }
  
  public static BooleanBigList singleton(Object element)
  {
    return new Singleton(((Boolean)element).booleanValue(), null);
  }
  
  public static BooleanBigList synchronize(BooleanBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static BooleanBigList synchronize(BooleanBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static BooleanBigList unmodifiable(BooleanBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static BooleanBigList asBigList(BooleanList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractBooleanBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final BooleanList list;
    
    protected ListBigList(BooleanList list)
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
    
    public BooleanBigListIterator iterator()
    {
      return BooleanBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public BooleanBigListIterator listIterator()
    {
      return BooleanBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Boolean> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public BooleanBigListIterator listIterator(long index)
    {
      return BooleanBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public BooleanBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(boolean key)
    {
      return this.list.contains(key);
    }
    
    public boolean[] toBooleanArray()
    {
      return this.list.toBooleanArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public boolean[] toBooleanArray(boolean[] local_a)
    {
      return this.list.toBooleanArray(local_a);
    }
    
    public void add(long index, boolean key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, BooleanCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, BooleanBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(boolean key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(BooleanBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean getBoolean(long index)
    {
      return this.list.getBoolean(intIndex(index));
    }
    
    public long indexOf(boolean local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(boolean local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean removeBoolean(long index)
    {
      return this.list.removeBoolean(intIndex(index));
    }
    
    public boolean set(long index, boolean local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(BooleanCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(BooleanCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(BooleanCollection local_c)
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
    
    public boolean addAll(Collection<? extends Boolean> local_c)
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
    extends BooleanCollections.UnmodifiableCollection
    implements BooleanBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanBigList list;
    
    protected UnmodifiableBigList(BooleanBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public boolean getBoolean(long local_i)
    {
      return this.list.getBoolean(local_i);
    }
    
    public boolean set(long local_i, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeBoolean(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(boolean local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(boolean local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, boolean[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, boolean[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, boolean[][] local_a)
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
    
    public BooleanBigListIterator iterator()
    {
      return listIterator();
    }
    
    public BooleanBigListIterator listIterator()
    {
      return BooleanBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public BooleanBigListIterator listIterator(long local_i)
    {
      return BooleanBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public BooleanBigList subList(long from, long local_to)
    {
      return BooleanBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Boolean> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, BooleanBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean get(long local_i)
    {
      return (Boolean)this.list.get(local_i);
    }
    
    public void add(long local_i, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean set(long index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean remove(long local_i)
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
    extends BooleanCollections.SynchronizedCollection
    implements BooleanBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanBigList list;
    
    protected SynchronizedBigList(BooleanBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(BooleanBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public boolean getBoolean(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getBoolean(local_i);
      }
    }
    
    public boolean set(long local_i, boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, boolean local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public boolean removeBoolean(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeBoolean(local_i);
      }
    }
    
    public long indexOf(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Boolean> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, boolean[][] local_a, long offset, long length)
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
    
    public void addElements(long index, boolean[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, boolean[][] local_a)
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
    
    public BooleanBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public BooleanBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public BooleanBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public BooleanBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return BooleanBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Boolean> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, BooleanBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(BooleanBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Boolean get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Boolean local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Boolean set(long index, Boolean local_k)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.set(index, local_k);
      }
    }
    
    public Boolean remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.remove(local_i);
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
    extends AbstractBooleanBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final boolean element;
    
    private Singleton(boolean element)
    {
      this.element = element;
    }
    
    public boolean getBoolean(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public boolean removeBoolean(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(boolean local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Boolean> local_c)
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
    
    public boolean[] toBooleanArray()
    {
      boolean[] local_a = new boolean[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public BooleanBigListIterator listIterator()
    {
      return BooleanBigListIterators.singleton(this.element);
    }
    
    public BooleanBigListIterator iterator()
    {
      return listIterator();
    }
    
    public BooleanBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      BooleanBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public BooleanBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return BooleanBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends BooleanCollections.EmptyCollection
    implements BooleanBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeBoolean(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean set(long index, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(boolean local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(boolean local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, BooleanBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean set(long index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean getBoolean(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Boolean remove(long local_k)
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
    
    public BooleanBigListIterator listIterator()
    {
      return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public BooleanBigListIterator iterator()
    {
      return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public BooleanBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public BooleanBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, boolean[][] local_a, long offset, long length)
    {
      BooleanBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, boolean[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, boolean[][] local_a)
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
    
    public int compareTo(BigList<? extends Boolean> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return BooleanBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return BooleanBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */