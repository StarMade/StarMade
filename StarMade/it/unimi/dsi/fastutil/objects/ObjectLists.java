package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ObjectLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static <K> ObjectList<K> shuffle(ObjectList<K> local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      K local_t = local_l.get(local_i);
      local_l.set(local_i, local_l.get(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static <K> ObjectList<K> singleton(K element)
  {
    return new Singleton(element, null);
  }
  
  public static <K> ObjectList<K> synchronize(ObjectList<K> local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static <K> ObjectList<K> synchronize(ObjectList<K> local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static <K> ObjectList<K> unmodifiable(ObjectList<K> local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList<K>
    extends ObjectCollections.UnmodifiableCollection<K>
    implements ObjectList<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectList<K> list;
    
    protected UnmodifiableList(ObjectList<K> local_l)
    {
      super();
      this.list = local_l;
    }
    
    public K get(int local_i)
    {
      return this.list.get(local_i);
    }
    
    public K set(int local_i, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public K remove(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(Object local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(Object local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, Object[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, K[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, K[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public ObjectListIterator<K> iterator()
    {
      return listIterator();
    }
    
    public ObjectListIterator<K> listIterator()
    {
      return ObjectIterators.unmodifiable(this.list.listIterator());
    }
    
    public ObjectListIterator<K> listIterator(int local_i)
    {
      return ObjectIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ObjectList<K> subList(int from, int local_to)
    {
      return ObjectLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public ObjectList<K> objectSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends K> local_o)
    {
      return this.list.compareTo(local_o);
    }
  }
  
  public static class SynchronizedList<K>
    extends ObjectCollections.SynchronizedCollection<K>
    implements ObjectList<K>, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ObjectList<K> list;
    
    protected SynchronizedList(ObjectList<K> local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(ObjectList<K> local_l)
    {
      super();
      this.list = local_l;
    }
    
    public K get(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.get(local_i);
      }
    }
    
    public K set(int local_i, K local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, K local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public K remove(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.remove(local_i);
      }
    }
    
    public int indexOf(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends K> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, Object[] local_a, int offset, int length)
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
    
    public void addElements(int index, K[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, K[] local_a)
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
    
    public ObjectListIterator<K> iterator()
    {
      return this.list.listIterator();
    }
    
    public ObjectListIterator<K> listIterator()
    {
      return this.list.listIterator();
    }
    
    public ObjectListIterator<K> listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ObjectList<K> subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return ObjectLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public ObjectList<K> objectSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends K> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractObjectList<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final K element;
    
    private Singleton(K element)
    {
      this.element = element;
    }
    
    public K get(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public K remove(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends K> local_c)
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
    
    public ObjectListIterator<K> listIterator()
    {
      return ObjectIterators.singleton(this.element);
    }
    
    public ObjectListIterator<K> iterator()
    {
      return listIterator();
    }
    
    public ObjectListIterator<K> listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      ObjectListIterator<K> local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public ObjectList<K> subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return ObjectLists.EMPTY_LIST;
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
    
    public boolean remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList<K>
    extends ObjectCollections.EmptyCollection<K>
    implements ObjectList<K>, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public K remove(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public K set(int index, K local_k)
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
    
    public boolean addAll(Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends K> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public K get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public ObjectIterator<K> objectIterator()
    {
      return ObjectIterators.EMPTY_ITERATOR;
    }
    
    public ObjectListIterator<K> listIterator()
    {
      return ObjectIterators.EMPTY_ITERATOR;
    }
    
    public ObjectListIterator<K> iterator()
    {
      return ObjectIterators.EMPTY_ITERATOR;
    }
    
    public ObjectListIterator<K> listIterator(int local_i)
    {
      if (local_i == 0) {
        return ObjectIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ObjectListIterator<K> objectListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ObjectList<K> subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public ObjectList<K> objectSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, Object[] local_a, int offset, int length)
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
    
    public void addElements(int index, K[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, K[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends K> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ObjectLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return ObjectLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */