package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class FloatLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static FloatList shuffle(FloatList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      float local_t = local_l.getFloat(local_i);
      local_l.set(local_i, local_l.getFloat(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static FloatList singleton(float element)
  {
    return new Singleton(element, null);
  }
  
  public static FloatList singleton(Object element)
  {
    return new Singleton(((Float)element).floatValue(), null);
  }
  
  public static FloatList synchronize(FloatList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static FloatList synchronize(FloatList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static FloatList unmodifiable(FloatList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends FloatCollections.UnmodifiableCollection
    implements FloatList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatList list;
    
    protected UnmodifiableList(FloatList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public float getFloat(int local_i)
    {
      return this.list.getFloat(local_i);
    }
    
    public float set(int local_i, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float removeFloat(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(float local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(float local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, float[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, float[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, float[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public FloatListIterator iterator()
    {
      return listIterator();
    }
    
    public FloatListIterator listIterator()
    {
      return FloatIterators.unmodifiable(this.list.listIterator());
    }
    
    public FloatListIterator listIterator(int local_i)
    {
      return FloatIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public FloatListIterator floatListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public FloatListIterator floatListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public FloatList subList(int from, int local_to)
    {
      return FloatLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public FloatList floatSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Float> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, FloatList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float get(int local_i)
    {
      return (Float)this.list.get(local_i);
    }
    
    public void add(int local_i, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float set(int index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float remove(int local_i)
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
    extends FloatCollections.SynchronizedCollection
    implements FloatList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final FloatList list;
    
    protected SynchronizedList(FloatList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(FloatList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public float getFloat(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getFloat(local_i);
      }
    }
    
    public float set(int local_i, float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, float local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public float removeFloat(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeFloat(local_i);
      }
    }
    
    public int indexOf(float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(float local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Float> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, float[] local_a, int offset, int length)
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
    
    public void addElements(int index, float[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, float[] local_a)
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
    
    public FloatListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public FloatListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public FloatListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public FloatListIterator floatListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public FloatListIterator floatListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public FloatList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return FloatLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public FloatList floatSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Float> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, FloatCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, FloatList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(FloatList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Float get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Float local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Float set(int index, Float local_k)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.set(index, local_k);
      }
    }
    
    public Float remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Float)this.list.remove(local_i);
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
    extends AbstractFloatList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final float element;
    
    private Singleton(float element)
    {
      this.element = element;
    }
    
    public float getFloat(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public float removeFloat(int local_i)
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
    
    public boolean addAll(int local_i, Collection<? extends Float> local_c)
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
    
    public FloatListIterator listIterator()
    {
      return FloatIterators.singleton(this.element);
    }
    
    public FloatListIterator iterator()
    {
      return listIterator();
    }
    
    public FloatListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      FloatListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public FloatList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return FloatLists.EMPTY_LIST;
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
    
    public boolean rem(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends FloatCollections.EmptyCollection
    implements FloatList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float removeFloat(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public float set(int index, float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(float local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(float local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Float> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(FloatList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, FloatCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, FloatList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float set(int index, Float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float getFloat(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Float remove(int local_k)
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
    public FloatIterator floatIterator()
    {
      return FloatIterators.EMPTY_ITERATOR;
    }
    
    public FloatListIterator listIterator()
    {
      return FloatIterators.EMPTY_ITERATOR;
    }
    
    public FloatListIterator iterator()
    {
      return FloatIterators.EMPTY_ITERATOR;
    }
    
    public FloatListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return FloatIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public FloatListIterator floatListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public FloatListIterator floatListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public FloatList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public FloatList floatSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, float[] local_a, int offset, int length)
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
    
    public void addElements(int index, float[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, float[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Float> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return FloatLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return FloatLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */