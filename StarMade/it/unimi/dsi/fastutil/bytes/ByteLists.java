package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ByteLists
{
  public static final EmptyList EMPTY_LIST = new EmptyList();
  
  public static ByteList shuffle(ByteList local_l, Random random)
  {
    int local_i = local_l.size();
    while (local_i-- != 0)
    {
      int local_p = random.nextInt(local_i + 1);
      byte local_t = local_l.getByte(local_i);
      local_l.set(local_i, local_l.getByte(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static ByteList singleton(byte element)
  {
    return new Singleton(element, null);
  }
  
  public static ByteList singleton(Object element)
  {
    return new Singleton(((Byte)element).byteValue(), null);
  }
  
  public static ByteList synchronize(ByteList local_l)
  {
    return new SynchronizedList(local_l);
  }
  
  public static ByteList synchronize(ByteList local_l, Object sync)
  {
    return new SynchronizedList(local_l, sync);
  }
  
  public static ByteList unmodifiable(ByteList local_l)
  {
    return new UnmodifiableList(local_l);
  }
  
  public static class UnmodifiableList
    extends ByteCollections.UnmodifiableCollection
    implements ByteList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteList list;
    
    protected UnmodifiableList(ByteList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public byte getByte(int local_i)
    {
      return this.list.getByte(local_i);
    }
    
    public byte set(int local_i, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int local_i, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte removeByte(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(byte local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public int lastIndexOf(byte local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(int index, Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(int from, byte[] local_a, int offset, int length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, byte[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, byte[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int size)
    {
      this.list.size(size);
    }
    
    public ByteListIterator iterator()
    {
      return listIterator();
    }
    
    public ByteListIterator listIterator()
    {
      return ByteIterators.unmodifiable(this.list.listIterator());
    }
    
    public ByteListIterator listIterator(int local_i)
    {
      return ByteIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    @Deprecated
    public ByteListIterator byteListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ByteListIterator byteListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ByteList subList(int from, int local_to)
    {
      return ByteLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    @Deprecated
    public ByteList byteSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Byte> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(int index, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int index, ByteList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte get(int local_i)
    {
      return (Byte)this.list.get(local_i);
    }
    
    public void add(int local_i, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte set(int index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte remove(int local_i)
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
    extends ByteCollections.SynchronizedCollection
    implements ByteList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteList list;
    
    protected SynchronizedList(ByteList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedList(ByteList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public byte getByte(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getByte(local_i);
      }
    }
    
    public byte set(int local_i, byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(int local_i, byte local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public byte removeByte(int local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeByte(local_i);
      }
    }
    
    public int indexOf(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public int lastIndexOf(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(int index, Collection<? extends Byte> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(int from, byte[] local_a, int offset, int length)
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
    
    public void addElements(int index, byte[] local_a, int offset, int length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(int index, byte[] local_a)
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
    
    public ByteListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public ByteListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public ByteListIterator listIterator(int local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    @Deprecated
    public ByteListIterator byteListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ByteListIterator byteListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ByteList subList(int from, int local_to)
    {
      synchronized (this.sync)
      {
        return ByteLists.synchronize(this.list.subList(from, local_to), this.sync);
      }
    }
    
    @Deprecated
    public ByteList byteSubList(int from, int local_to)
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
    
    public int compareTo(List<? extends Byte> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(int index, ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(int index, ByteList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(ByteList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Byte get(int local_i)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.get(local_i);
      }
    }
    
    public void add(int local_i, Byte local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Byte set(int index, Byte local_k)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.set(index, local_k);
      }
    }
    
    public Byte remove(int local_i)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.remove(local_i);
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
    extends AbstractByteList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final byte element;
    
    private Singleton(byte element)
    {
      this.element = element;
    }
    
    public byte getByte(int local_i)
    {
      if (local_i == 0) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public byte removeByte(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean contains(byte local_k)
    {
      return local_k == this.element;
    }
    
    public boolean addAll(Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Byte> local_c)
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
    
    public byte[] toByteArray()
    {
      byte[] local_a = new byte[1];
      local_a[0] = this.element;
      return local_a;
    }
    
    public ByteListIterator listIterator()
    {
      return ByteIterators.singleton(this.element);
    }
    
    public ByteListIterator iterator()
    {
      return listIterator();
    }
    
    public ByteListIterator listIterator(int local_i)
    {
      if ((local_i > 1) || (local_i < 0)) {
        throw new IndexOutOfBoundsException();
      }
      ByteListIterator local_l = listIterator();
      if (local_i == 1) {
        local_l.next();
      }
      return local_l;
    }
    
    public ByteList subList(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0) || (local_to != 1)) {
        return ByteLists.EMPTY_LIST;
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
    
    public boolean rem(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyList
    extends ByteCollections.EmptyCollection
    implements ByteList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(int index, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte removeByte(int local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte set(int index, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int indexOf(byte local_k)
    {
      return -1;
    }
    
    public int lastIndexOf(byte local_k)
    {
      return -1;
    }
    
    public boolean addAll(Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte get(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(int local_i, ByteList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(int index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte set(int index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte getByte(int local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Byte remove(int local_k)
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
    public ByteIterator byteIterator()
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
    
    public ByteListIterator listIterator()
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
    
    public ByteListIterator iterator()
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
    
    public ByteListIterator listIterator(int local_i)
    {
      if (local_i == 0) {
        return ByteIterators.EMPTY_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    @Deprecated
    public ByteListIterator byteListIterator()
    {
      return listIterator();
    }
    
    @Deprecated
    public ByteListIterator byteListIterator(int local_i)
    {
      return listIterator(local_i);
    }
    
    public ByteList subList(int from, int local_to)
    {
      if ((from == 0) && (local_to == 0)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    @Deprecated
    public ByteList byteSubList(int from, int local_to)
    {
      return subList(from, local_to);
    }
    
    public void getElements(int from, byte[] local_a, int offset, int length)
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
    
    public void addElements(int index, byte[] local_a, int offset, int length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(int index, byte[] local_a)
    {
      throw new UnsupportedOperationException();
    }
    
    public void size(int local_s)
    {
      throw new UnsupportedOperationException();
    }
    
    public int compareTo(List<? extends Byte> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ByteLists.EMPTY_LIST;
    }
    
    public Object clone()
    {
      return ByteLists.EMPTY_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */