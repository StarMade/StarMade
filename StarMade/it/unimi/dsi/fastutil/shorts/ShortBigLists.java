package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class ShortBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static ShortBigList shuffle(ShortBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      short local_t = local_l.getShort(local_i);
      local_l.set(local_i, local_l.getShort(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static ShortBigList singleton(short element)
  {
    return new Singleton(element, null);
  }
  
  public static ShortBigList singleton(Object element)
  {
    return new Singleton(((Short)element).shortValue(), null);
  }
  
  public static ShortBigList synchronize(ShortBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static ShortBigList synchronize(ShortBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static ShortBigList unmodifiable(ShortBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static ShortBigList asBigList(ShortList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractShortBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final ShortList list;
    
    protected ListBigList(ShortList list)
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
    
    public ShortBigListIterator iterator()
    {
      return ShortBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public ShortBigListIterator listIterator()
    {
      return ShortBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Short> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public ShortBigListIterator listIterator(long index)
    {
      return ShortBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public ShortBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(short key)
    {
      return this.list.contains(key);
    }
    
    public short[] toShortArray()
    {
      return this.list.toShortArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public short[] toShortArray(short[] local_a)
    {
      return this.list.toShortArray(local_a);
    }
    
    public void add(long index, short key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, ShortCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, ShortBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(short key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(ShortBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public short getShort(long index)
    {
      return this.list.getShort(intIndex(index));
    }
    
    public long indexOf(short local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(short local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public short removeShort(long index)
    {
      return this.list.removeShort(intIndex(index));
    }
    
    public short set(long index, short local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(ShortCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(ShortCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(ShortCollection local_c)
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
    
    public boolean addAll(Collection<? extends Short> local_c)
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
    extends ShortCollections.UnmodifiableCollection
    implements ShortBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortBigList list;
    
    protected UnmodifiableBigList(ShortBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public short getShort(long local_i)
    {
      return this.list.getShort(local_i);
    }
    
    public short set(long local_i, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short removeShort(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(short local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(short local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, short[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, short[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, short[][] local_a)
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
    
    public ShortBigListIterator iterator()
    {
      return listIterator();
    }
    
    public ShortBigListIterator listIterator()
    {
      return ShortBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public ShortBigListIterator listIterator(long local_i)
    {
      return ShortBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public ShortBigList subList(long from, long local_to)
    {
      return ShortBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Short> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, ShortBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short get(long local_i)
    {
      return (Short)this.list.get(local_i);
    }
    
    public void add(long local_i, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short set(long index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short remove(long local_i)
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
    extends ShortCollections.SynchronizedCollection
    implements ShortBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortBigList list;
    
    protected SynchronizedBigList(ShortBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(ShortBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public short getShort(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getShort(local_i);
      }
    }
    
    public short set(long local_i, short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, short local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public short removeShort(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeShort(local_i);
      }
    }
    
    public long indexOf(short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Short> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, short[][] local_a, long offset, long length)
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
    
    public void addElements(long index, short[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, short[][] local_a)
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
    
    public ShortBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public ShortBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public ShortBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public ShortBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return ShortBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Short> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, ShortBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(ShortBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Short get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Short local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Short set(long index, Short local_k)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.set(index, local_k);
      }
    }
    
    public Short remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.remove(local_i);
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
    extends AbstractShortBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final short element;
    
    private Singleton(short element)
    {
      this.element = element;
    }
    
    public short getShort(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public short removeShort(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(short local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Short> local_c)
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
    
    public short[] toShortArray()
    {
      short[] local_a = new short[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public ShortBigListIterator listIterator()
    {
      return ShortBigListIterators.singleton(this.element);
    }
    
    public ShortBigListIterator iterator()
    {
      return listIterator();
    }
    
    public ShortBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      ShortBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public ShortBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return ShortBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends ShortCollections.EmptyCollection
    implements ShortBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short removeShort(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public short set(long index, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(short local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(short local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ShortBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short set(long index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short getShort(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Short remove(long local_k)
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
    
    public ShortBigListIterator listIterator()
    {
      return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ShortBigListIterator iterator()
    {
      return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ShortBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public ShortBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, short[][] local_a, long offset, long length)
    {
      ShortBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, short[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, short[][] local_a)
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
    
    public int compareTo(BigList<? extends Short> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ShortBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return ShortBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */