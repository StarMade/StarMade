package it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BooleanLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static BooleanList shuffle(BooleanList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      boolean local_t = local_l.getBoolean(local_i);
      local_l.set(local_i, local_l.getBoolean(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static BooleanList singleton(boolean element)
  {
    return new Singleton(element, null);
  }
  
  public static BooleanList singleton(Object element)
  {
    return new Singleton(((Boolean)element).booleanValue(), null);
  }
  
  public static BooleanList synchronize(BooleanList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static BooleanList synchronize(BooleanList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static BooleanList unmodifiable(BooleanList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends BooleanCollections.UnmodifiableCollection
    implements BooleanList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanList list;
    
    protected UnmodifiableList(BooleanList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public boolean getBoolean(int local_i)
    {
      return this.list.getBoolean(local_i);
    }
    
    public boolean set(int local_i, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeBoolean(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(boolean local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(boolean local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, boolean[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, boolean[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, boolean[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public BooleanListIterator iterator()
    {
      return listIterator();
    }
    
    public BooleanListIterator listIterator()
    {
      return BooleanIterators.unmodifiable(this.list.listIterator());
    }
    
    public BooleanListIterator listIterator(int local_i)
    {
      return BooleanIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public BooleanList subList(int from, int local_to)
    {
      return BooleanLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public BooleanList booleanSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Boolean> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, BooleanList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean get(int local_i)
    {
      return (Boolean)this.list.get(local_i);
    }
    
    public void add(int local_i, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean set(int index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean remove(int local_i)
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
    extends BooleanCollections.SynchronizedCollection
    implements BooleanList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final BooleanList list;
    
    protected SynchronizedList(BooleanList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(BooleanList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public boolean getBoolean(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getBoolean(local_i);
      }
    }
    
    public boolean set(int local_i, boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, boolean local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public boolean removeBoolean(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeBoolean(local_i);
      }
    }
    
    public int indexOf(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(boolean local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Boolean> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, boolean[] local_a, int offset, int length)
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
    
    public void addElements(int index, boolean[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, boolean[] local_a)
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
    
    public BooleanListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public BooleanListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public BooleanListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public BooleanList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return BooleanLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public BooleanList booleanSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Boolean> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, BooleanCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, BooleanList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(BooleanList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Boolean get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Boolean local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Boolean set(int index, Boolean local_k)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.set(index, local_k);
      }
    }
    
    public Boolean remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.list.remove(local_i);
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
    extends AbstractBooleanList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final boolean element;
    
    private Singleton(boolean element)
    {
      this.element = element;
    }
    
    public boolean getBoolean(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public boolean removeBoolean(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends Boolean> local_c)
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
    
    public BooleanListIterator listIterator()
    {
      return BooleanIterators.singleton(this.element);
    }
    
    public BooleanListIterator iterator()
    {
      return listIterator();
    }
    
    public BooleanListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      BooleanListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public BooleanList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return BooleanLists.EMPTY_LIST;
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
    
    public boolean rem(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends BooleanCollections.EmptyCollection
    implements BooleanList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeBoolean(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean set(int index, boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(boolean local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(boolean local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Boolean> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(BooleanList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, BooleanCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, BooleanList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean set(int index, Boolean local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean getBoolean(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Boolean remove(int local_k)
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
    public BooleanIterator booleanIterator()
    {
      return BooleanIterators.EMPTY_ITERATOR;
    }
    
    public BooleanListIterator listIterator()
    {
      return BooleanIterators.EMPTY_ITERATOR;
    }
    
    public BooleanListIterator iterator()
    {
      return BooleanIterators.EMPTY_ITERATOR;
    }
    
    public BooleanListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return BooleanIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public BooleanListIterator booleanListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public BooleanList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public BooleanList booleanSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, boolean[] local_a, int offset, int length)
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
    
    public void addElements(int index, boolean[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, boolean[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Boolean> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return BooleanLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return BooleanLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */