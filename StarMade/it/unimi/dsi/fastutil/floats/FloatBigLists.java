package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class FloatBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static FloatBigList shuffle(FloatBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      float local_t = local_l.getFloat(local_i);
      local_l.set(local_i, local_l.getFloat(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static FloatBigList singleton(float element)
  {
    return new Singleton(element, null);
  }
  
  public static FloatBigList singleton(Object element)
  {
    return new Singleton(((Float)element).floatValue(), null);
  }
  
  public static FloatBigList synchronize(FloatBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static FloatBigList synchronize(FloatBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static FloatBigList unmodifiable(FloatBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static FloatBigList asBigList(FloatList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractFloatBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final FloatList list;
    
    protected ListBigList(FloatList list)
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
    
    public FloatBigListIterator iterator()
    {
      return FloatBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public FloatBigListIterator listIterator()
    {
      return FloatBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Float> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public FloatBigListIterator listIterator(long index)
    {
      return FloatBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public FloatBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(float key)
    {
      return this.list.contains(key);
    }
    
    public float[] toFloatArray()
    {
      return this.list.toFloatArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public float[] toFloatArray(float[] local_a)
    {
      return this.list.toFloatArray(local_a);
    }
    
    public void add(long index, float key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, FloatCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, FloatBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(float key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(FloatBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public float getFloat(long index)
    {
      return this.list.getFloat(intIndex(index));
    }
    
    public long indexOf(float local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(float local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public float removeFloat(long index)
    {
      return this.list.removeFloat(intIndex(index));
    }
    
    public float set(long index, float local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(FloatCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(FloatCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(FloatCollection local_c)
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
    
    public boolean addAll(Collection<? extends Float> local_c)
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
    extends FloatCollections.UnmodifiableCollection
    implements FloatBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatBigList list;
    
    protected UnmodifiableBigList(FloatBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public float getFloat(long local_i)
    {
      return this.list.getFloat(local_i);
    }
    
    public float set(long local_i, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float removeFloat(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(float local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(float local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, float[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, float[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, float[][] local_a)
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
    
    public FloatBigListIterator iterator()
    {
      return listIterator();
    }
    
    public FloatBigListIterator listIterator()
    {
      return FloatBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public FloatBigListIterator listIterator(long local_i)
    {
      return FloatBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public FloatBigList subList(long from, long local_to)
    {
      return FloatBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Float> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, FloatBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float get(long local_i)
    {
      return (Float)this.list.get(local_i);
    }
    
    public void add(long local_i, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float set(long index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float remove(long local_i)
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
    extends FloatCollections.SynchronizedCollection
    implements FloatBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatBigList list;
    
    protected SynchronizedBigList(FloatBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(FloatBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public float getFloat(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getFloat(local_i);
      }
    }
    
    public float set(long local_i, float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, float local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public float removeFloat(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeFloat(local_i);
      }
    }
    
    public long indexOf(float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Float> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, float[][] local_a, long offset, long length)
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
    
    public void addElements(long index, float[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, float[][] local_a)
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
    
    public FloatBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public FloatBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public FloatBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public FloatBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return FloatBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Float> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, FloatBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(FloatBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Float get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Float local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Float set(long index, Float local_k)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.set(index, local_k);
      }
    }
    
    public Float remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.remove(local_i);
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
    extends AbstractFloatBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final float element;
    
    private Singleton(float element)
    {
      this.element = element;
    }
    
    public float getFloat(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public float removeFloat(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(float local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Float> local_c)
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
    
    public float[] toFloatArray()
    {
      float[] local_a = new float[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public FloatBigListIterator listIterator()
    {
      return FloatBigListIterators.singleton(this.element);
    }
    
    public FloatBigListIterator iterator()
    {
      return listIterator();
    }
    
    public FloatBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      FloatBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public FloatBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return FloatBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends FloatCollections.EmptyCollection
    implements FloatBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float removeFloat(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public float set(long index, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(float local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(float local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, FloatBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float set(long index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float getFloat(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Float remove(long local_k)
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
    
    public FloatBigListIterator listIterator()
    {
      return FloatBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public FloatBigListIterator iterator()
    {
      return FloatBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public FloatBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return FloatBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public FloatBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, float[][] local_a, long offset, long length)
    {
      FloatBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, float[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, float[][] local_a)
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
    
    public int compareTo(BigList<? extends Float> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return FloatBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return FloatBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */