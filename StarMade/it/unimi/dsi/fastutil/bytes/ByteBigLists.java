package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigList;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

public class ByteBigLists
{
  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
  
  public static ByteBigList shuffle(ByteBigList local_l, Random random)
  {
    long local_i = local_l.size64();
    while (local_i-- != 0L)
    {
      long local_p = (random.nextLong() & 0xFFFFFFFF) % (local_i + 1L);
      byte local_t = local_l.getByte(local_i);
      local_l.set(local_i, local_l.getByte(local_p));
      local_l.set(local_p, local_t);
    }
    return local_l;
  }
  
  public static ByteBigList singleton(byte element)
  {
    return new Singleton(element, null);
  }
  
  public static ByteBigList singleton(Object element)
  {
    return new Singleton(((Byte)element).byteValue(), null);
  }
  
  public static ByteBigList synchronize(ByteBigList local_l)
  {
    return new SynchronizedBigList(local_l);
  }
  
  public static ByteBigList synchronize(ByteBigList local_l, Object sync)
  {
    return new SynchronizedBigList(local_l, sync);
  }
  
  public static ByteBigList unmodifiable(ByteBigList local_l)
  {
    return new UnmodifiableBigList(local_l);
  }
  
  public static ByteBigList asBigList(ByteList list)
  {
    return new ListBigList(list);
  }
  
  public static class ListBigList
    extends AbstractByteBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final ByteList list;
    
    protected ListBigList(ByteList list)
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
    
    public ByteBigListIterator iterator()
    {
      return ByteBigListIterators.asBigListIterator(this.list.iterator());
    }
    
    public ByteBigListIterator listIterator()
    {
      return ByteBigListIterators.asBigListIterator(this.list.listIterator());
    }
    
    public boolean addAll(long index, Collection<? extends Byte> local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public ByteBigListIterator listIterator(long index)
    {
      return ByteBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index)));
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      return new ListBigList(this.list.subList(intIndex(from), intIndex(local_to)));
    }
    
    public boolean contains(byte key)
    {
      return this.list.contains(key);
    }
    
    public byte[] toByteArray()
    {
      return this.list.toByteArray();
    }
    
    public void removeElements(long from, long local_to)
    {
      this.list.removeElements(intIndex(from), intIndex(local_to));
    }
    
    public byte[] toByteArray(byte[] local_a)
    {
      return this.list.toByteArray(local_a);
    }
    
    public void add(long index, byte key)
    {
      this.list.add(intIndex(index), key);
    }
    
    public boolean addAll(long index, ByteCollection local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean addAll(long index, ByteBigList local_c)
    {
      return this.list.addAll(intIndex(index), local_c);
    }
    
    public boolean add(byte key)
    {
      return this.list.add(key);
    }
    
    public boolean addAll(ByteBigList local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public byte getByte(long index)
    {
      return this.list.getByte(intIndex(index));
    }
    
    public long indexOf(byte local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(byte local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public byte removeByte(long index)
    {
      return this.list.removeByte(intIndex(index));
    }
    
    public byte set(long index, byte local_k)
    {
      return this.list.set(intIndex(index), local_k);
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      return this.list.addAll(local_c);
    }
    
    public boolean containsAll(ByteCollection local_c)
    {
      return this.list.containsAll(local_c);
    }
    
    public boolean removeAll(ByteCollection local_c)
    {
      return this.list.removeAll(local_c);
    }
    
    public boolean retainAll(ByteCollection local_c)
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
    
    public boolean addAll(Collection<? extends Byte> local_c)
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
    extends ByteCollections.UnmodifiableCollection
    implements ByteBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteBigList list;
    
    protected UnmodifiableBigList(ByteBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public byte getByte(long local_i)
    {
      return this.list.getByte(local_i);
    }
    
    public byte set(long local_i, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long local_i, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte removeByte(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(byte local_k)
    {
      return this.list.indexOf(local_k);
    }
    
    public long lastIndexOf(byte local_k)
    {
      return this.list.lastIndexOf(local_k);
    }
    
    public boolean addAll(long index, Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void getElements(long from, byte[][] local_a, long offset, long length)
    {
      this.list.getElements(from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, byte[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, byte[][] local_a)
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
    
    public ByteBigListIterator iterator()
    {
      return listIterator();
    }
    
    public ByteBigListIterator listIterator()
    {
      return ByteBigListIterators.unmodifiable(this.list.listIterator());
    }
    
    public ByteBigListIterator listIterator(long local_i)
    {
      return ByteBigListIterators.unmodifiable(this.list.listIterator(local_i));
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      return ByteBigLists.unmodifiable(this.list.subList(from, local_to));
    }
    
    public boolean equals(Object local_o)
    {
      return this.list.equals(local_o);
    }
    
    public int hashCode()
    {
      return this.list.hashCode();
    }
    
    public int compareTo(BigList<? extends Byte> local_o)
    {
      return this.list.compareTo(local_o);
    }
    
    public boolean addAll(long index, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long index, ByteBigList local_l)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte get(long local_i)
    {
      return (Byte)this.list.get(local_i);
    }
    
    public void add(long local_i, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte set(long index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte remove(long local_i)
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
    extends ByteCollections.SynchronizedCollection
    implements ByteBigList, Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteBigList list;
    
    protected SynchronizedBigList(ByteBigList local_l, Object sync)
    {
      super(sync);
      this.list = local_l;
    }
    
    protected SynchronizedBigList(ByteBigList local_l)
    {
      super();
      this.list = local_l;
    }
    
    public byte getByte(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.getByte(local_i);
      }
    }
    
    public byte set(long local_i, byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.set(local_i, local_k);
      }
    }
    
    public void add(long local_i, byte local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public byte removeByte(long local_i)
    {
      synchronized (this.sync)
      {
        return this.list.removeByte(local_i);
      }
    }
    
    public long indexOf(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.indexOf(local_k);
      }
    }
    
    public long lastIndexOf(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.list.lastIndexOf(local_k);
      }
    }
    
    public boolean addAll(long index, Collection<? extends Byte> local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public void getElements(long from, byte[][] local_a, long offset, long length)
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
    
    public void addElements(long index, byte[][] local_a, long offset, long length)
    {
      synchronized (this.sync)
      {
        this.list.addElements(index, local_a, offset, length);
      }
    }
    
    public void addElements(long index, byte[][] local_a)
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
    
    public ByteBigListIterator iterator()
    {
      return this.list.listIterator();
    }
    
    public ByteBigListIterator listIterator()
    {
      return this.list.listIterator();
    }
    
    public ByteBigListIterator listIterator(long local_i)
    {
      return this.list.listIterator(local_i);
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      synchronized (this.sync)
      {
        return ByteBigLists.synchronize(this.list.subList(from, local_to), this.sync);
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
    
    public int compareTo(BigList<? extends Byte> local_o)
    {
      synchronized (this.sync)
      {
        return this.list.compareTo(local_o);
      }
    }
    
    public boolean addAll(long index, ByteCollection local_c)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_c);
      }
    }
    
    public boolean addAll(long index, ByteBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(index, local_l);
      }
    }
    
    public boolean addAll(ByteBigList local_l)
    {
      synchronized (this.sync)
      {
        return this.list.addAll(local_l);
      }
    }
    
    public Byte get(long local_i)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.get(local_i);
      }
    }
    
    public void add(long local_i, Byte local_k)
    {
      synchronized (this.sync)
      {
        this.list.add(local_i, local_k);
      }
    }
    
    public Byte set(long index, Byte local_k)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.set(index, local_k);
      }
    }
    
    public Byte remove(long local_i)
    {
      synchronized (this.sync)
      {
        return (Byte)this.list.remove(local_i);
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
    extends AbstractByteBigList
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    private final byte element;
    
    private Singleton(byte element)
    {
      this.element = element;
    }
    
    public byte getByte(long local_i)
    {
      if (local_i == 0L) {
        return this.element;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public byte removeByte(long local_i)
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
    
    public boolean addAll(long local_i, Collection<? extends Byte> local_c)
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
    
    public ByteBigListIterator listIterator()
    {
      return ByteBigListIterators.singleton(this.element);
    }
    
    public ByteBigListIterator iterator()
    {
      return listIterator();
    }
    
    public ByteBigListIterator listIterator(long local_i)
    {
      if ((local_i > 1L) || (local_i < 0L)) {
        throw new IndexOutOfBoundsException();
      }
      ByteBigListIterator local_l = listIterator();
      if (local_i == 1L) {
        local_l.next();
      }
      return local_l;
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      if ((from != 0L) || (local_to != 1L)) {
        return ByteBigLists.EMPTY_BIG_LIST;
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
    
    public boolean rem(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class EmptyBigList
    extends ByteCollections.EmptyCollection
    implements ByteBigList, Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public void add(long index, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte removeByte(long local_i)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte set(long index, byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long indexOf(byte local_k)
    {
      return -1L;
    }
    
    public long lastIndexOf(byte local_k)
    {
      return -1L;
    }
    
    public boolean addAll(Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, Collection<? extends Byte> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte get(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public boolean addAll(ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(ByteBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ByteCollection local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(long local_i, ByteBigList local_c)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(long index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean add(Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte set(long index, Byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte getByte(long local_i)
    {
      throw new IndexOutOfBoundsException();
    }
    
    public Byte remove(long local_k)
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
    
    public ByteBigListIterator listIterator()
    {
      return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ByteBigListIterator iterator()
    {
      return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    public ByteBigListIterator listIterator(long local_i)
    {
      if (local_i == 0L) {
        return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR;
      }
      throw new IndexOutOfBoundsException(String.valueOf(local_i));
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      if ((from == 0L) && (local_to == 0L)) {
        return this;
      }
      throw new IndexOutOfBoundsException();
    }
    
    public void getElements(long from, byte[][] local_a, long offset, long length)
    {
      ByteBigArrays.ensureOffsetLength(local_a, offset, length);
      if (from != 0L) {
        throw new IndexOutOfBoundsException();
      }
    }
    
    public void removeElements(long from, long local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, byte[][] local_a, long offset, long length)
    {
      throw new UnsupportedOperationException();
    }
    
    public void addElements(long index, byte[][] local_a)
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
    
    public int compareTo(BigList<? extends Byte> local_o)
    {
      if (local_o == this) {
        return 0;
      }
      return local_o.isEmpty() ? 0 : -1;
    }
    
    private Object readResolve()
    {
      return ByteBigLists.EMPTY_BIG_LIST;
    }
    
    public Object clone()
    {
      return ByteBigLists.EMPTY_BIG_LIST;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */