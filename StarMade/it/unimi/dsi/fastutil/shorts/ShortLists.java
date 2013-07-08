package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ShortLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static ShortList shuffle(ShortList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      short local_t = local_l.getShort(local_i);
      local_l.set(local_i, local_l.getShort(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static ShortList singleton(short element)
  {
    return new Singleton(element, null);
  }
  
  public static ShortList singleton(Object element)
  {
    return new Singleton(((Short)element).shortValue(), null);
  }
  
  public static ShortList synchronize(ShortList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static ShortList synchronize(ShortList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static ShortList unmodifiable(ShortList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends ShortCollections.UnmodifiableCollection
    implements ShortList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortList list;
    
    protected UnmodifiableList(ShortList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public short getShort(int local_i)
    {
      return this.list.getShort(local_i);
    }
    
    public short set(int local_i, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short removeShort(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(short local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(short local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, short[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, short[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, short[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public ShortListIterator iterator()
    {
      return listIterator();
    }
    
    public ShortListIterator listIterator()
    {
      return ShortIterators.unmodifiable(this.list.listIterator());
    }
    
    public ShortListIterator listIterator(int local_i)
    {
      return ShortIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public ShortListIterator shortListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ShortListIterator shortListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ShortList subList(int from, int local_to)
    {
      return ShortLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public ShortList shortSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Short> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, ShortList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short get(int local_i)
    {
      return (Short)this.list.get(local_i);
    }
    
    public void add(int local_i, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short set(int index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short remove(int local_i)
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
    extends ShortCollections.SynchronizedCollection
    implements ShortList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ShortList list;
    
    protected SynchronizedList(ShortList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(ShortList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public short getShort(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getShort(local_i);
      }
    }
    
    public short set(int local_i, short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, short local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public short removeShort(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeShort(local_i);
      }
    }
    
    public int indexOf(short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(short local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Short> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, short[] local_a, int offset, int length)
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
    
    public void addElements(int index, short[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, short[] local_a)
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
    
    public ShortListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public ShortListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public ShortListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public ShortListIterator shortListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ShortListIterator shortListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ShortList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return ShortLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public ShortList shortSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Short> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, ShortCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, ShortList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(ShortList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Short get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Short local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Short set(int index, Short local_k)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.set(index, local_k);
      }
    }
    
    public Short remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Short)this.list.remove(local_i);
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
    extends AbstractShortList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final short element;
    
    private Singleton(short element)
    {
      this.element = element;
    }
    
    public short getShort(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public short removeShort(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends Short> local_c)
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
    
    public ShortListIterator listIterator()
    {
      return ShortIterators.singleton(this.element);
    }
    
    public ShortListIterator iterator()
    {
      return listIterator();
    }
    
    public ShortListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      ShortListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public ShortList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return ShortLists.EMPTY_LIST;
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
    
    public boolean rem(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends ShortCollections.EmptyCollection
    implements ShortList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short removeShort(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public short set(int index, short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(short local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(short local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Short> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ShortList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ShortCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ShortList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short set(int index, Short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short getShort(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Short remove(int local_k)
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
    public ShortIterator shortIterator()
    {
      return ShortIterators.EMPTY_ITERATOR;
    }
    
    public ShortListIterator listIterator()
    {
      return ShortIterators.EMPTY_ITERATOR;
    }
    
    public ShortListIterator iterator()
    {
      return ShortIterators.EMPTY_ITERATOR;
    }
    
    public ShortListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return ShortIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public ShortListIterator shortListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ShortListIterator shortListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ShortList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public ShortList shortSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, short[] local_a, int offset, int length)
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
    
    public void addElements(int index, short[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, short[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Short> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ShortLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return ShortLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */