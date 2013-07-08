package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class ObjectBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static <K> ObjectBigList<K> shuffle(ObjectBigList<K> local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      K local_t = local_l.get(local_i);
      local_l.set(local_i, local_l.get(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static <K> ObjectBigList<K> singleton(K element)
  {
    return new Singleton(element, null);
  }
  
  public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static <K> ObjectBigList<K> unmodifiable(ObjectBigList<K> local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static <K> ObjectBigList<K> asBigList(ObjectList<K> list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList<K>
    extends AbstractObjectBigList<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final ObjectList<K> list;
    
    protected ListBigList(ObjectList<K> list)
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
    
    public ObjectBigListIterator<K> iterator()
    {
      return ObjectBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public ObjectBigListIterator<K> listIterator()
    {
      return ObjectBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends K> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public ObjectBigListIterator<K> listIterator(long index)
    {
      return ObjectBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public ObjectBigList<K> subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(Object key)
    {
      return this.list.contains(key);
    }
    
    public Object[] toArray()
    {
      return this.list.toArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public void add(long index, K key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, ObjectCollection<K> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, ObjectBigList<K> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(K key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(ObjectBigList<K> local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public K get(long index)
    {
      return this.list.get(intIndex(index));
    }
    
    public long indexOf(Object local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(Object local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public K remove(long index)
    {
      return this.list.remove(intIndex(index));
    }
    
    public K set(long index, K local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(ObjectCollection<K> local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(ObjectCollection<K> local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(ObjectCollection<K> local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(ObjectCollection<K> local_c)
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
    
    public boolean addAll(Collection<? extends K> local_c)
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
  
  public static class UnmodifiableBigList<K>
    extends ObjectCollections.UnmodifiableCollection<K>
    implements ObjectBigList<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectBigList<K> list;
    
    protected UnmodifiableBigList(ObjectBigList<K> local_l)
    {
      super();
      this.list = local_l;
    }
    
    public K get(long local_i)
    {
      return this.list.get(local_i);
    }
    
    public K set(long local_i, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public K remove(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(Object local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(Object local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, Object[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, K[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, K[][] local_a)
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
    
    public ObjectBigListIterator<K> iterator()
    {
      return listIterator();
    }
    
    public ObjectBigListIterator<K> listIterator()
    {
      return ObjectBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public ObjectBigListIterator<K> listIterator(long local_i)
    {
      return ObjectBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public ObjectBigList<K> subList(long from, long local_to)
    {
      return ObjectBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends K> local_o)
    {
      return this.list.compareTo(local_o);
    }
  }
  
  public static class SynchronizedBigList<K>
    extends ObjectCollections.SynchronizedCollection<K>
    implements ObjectBigList<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectBigList<K> list;
    
    protected SynchronizedBigList(ObjectBigList<K> local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(ObjectBigList<K> local_l)
    {
      super();
      this.list = local_l;
    }
    
    public K get(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.get(local_i);
      }
    }
    
    public K set(long local_i, K local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, K local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public K remove(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.remove(local_i);
      }
    }
    
    public long indexOf(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends K> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, Object[][] local_a, long offset, long length)
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
    
    public void addElements(long index, K[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, K[][] local_a)
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
    
    public ObjectBigListIterator<K> iterator()
    {
      return this.list.listIterator();
    }
    
    public ObjectBigListIterator<K> listIterator()
    {
      return this.list.listIterator();
    }
    
    public ObjectBigListIterator<K> listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public ObjectBigList<K> subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return ObjectBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends K> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractObjectBigList<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final K element;
    
    private Singleton(K element)
    {
      this.element = element;
    }
    
    public K get(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public K remove(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(Object local_k)
    {
      return local_k == null ? false : this.element == null ? true : local_k.equals(this.element);
    }
    
    public boolean addAll(Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends K> local_c)
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
    
    public Object[] toArray()
    {
      Object[] local_a = new Object[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public ObjectBigListIterator<K> listIterator()
    {
      return ObjectBigListIterators.singleton(this.element);
    }
    
    public ObjectBigListIterator<K> iterator()
    {
      return listIterator();
    }
    
    public ObjectBigListIterator<K> listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      ObjectBigListIterator<K> local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public ObjectBigList<K> subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return ObjectBigLists.EMPTY_BIG_LIST;
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
    
    public boolean remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList<K>
    extends ObjectCollections.EmptyCollection<K>
    implements ObjectBigList<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public K remove(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public K set(long index, K local_k)
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
    
    public boolean addAll(Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public K get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectBigListIterator<K> listIterator()
    {
      return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ObjectBigListIterator<K> iterator()
    {
      return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ObjectBigListIterator<K> listIterator(long local_i)
    {
      if (local_i == 0L) {
        return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public ObjectBigList<K> subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, Object[][] local_a, long offset, long length)
    {
      ObjectBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, K[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, K[][] local_a)
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
    
    public int compareTo(BigList<? extends K> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ObjectBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return ObjectBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */