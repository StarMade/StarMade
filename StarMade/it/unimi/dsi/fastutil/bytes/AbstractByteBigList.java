package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractByteBigList
  extends AbstractByteCollection
  implements ByteBigList, ByteStack
{
  protected void ensureIndex(long index)
  {
    if (index < 0L) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index > size64()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + size64() + ")");
    }
  }
  
  protected void ensureRestrictedIndex(long index)
  {
    if (index < 0L) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= size64()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + size64() + ")");
    }
  }
  
  public void add(long index, byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(byte local_k)
  {
    add(size64(), local_k);
    return true;
  }
  
  public byte removeByte(long local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte removeByte(int local_i)
  {
    return removeByte(local_i);
  }
  
  public byte set(long index, byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte set(int index, byte local_k)
  {
    return set(index, local_k);
  }
  
  public boolean addAll(long index, Collection<? extends Byte> local_c)
  {
    ensureIndex(index);
    int local_n = local_c.size();
    if (local_n == 0) {
      return false;
    }
    Iterator<? extends Byte> local_i = local_c.iterator();
    while (local_n-- != 0) {
      add(index++, (Byte)local_i.next());
    }
    return true;
  }
  
  public boolean addAll(int index, Collection<? extends Byte> local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(Collection<? extends Byte> local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public ByteBigListIterator iterator()
  {
    return listIterator();
  }
  
  public ByteBigListIterator listIterator()
  {
    return listIterator(0L);
  }
  
  public ByteBigListIterator listIterator(final long index)
  {
    new AbstractByteBigListIterator()
    {
      long pos = index;
      long last = -1L;
      
      public boolean hasNext()
      {
        return this.pos < AbstractByteBigList.this.size64();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0L;
      }
      
      public byte nextByte()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractByteBigList.this.getByte(this.last = this.pos++);
      }
      
      public byte previousByte()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractByteBigList.this.getByte(this.last = --this.pos);
      }
      
      public long nextIndex()
      {
        return this.pos;
      }
      
      public long previousIndex()
      {
        return this.pos - 1L;
      }
      
      public void add(byte local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractByteBigList.this.add(this.pos++, local_k);
        this.last = -1L;
      }
      
      public void set(byte local_k)
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractByteBigList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1L) {
          throw new IllegalStateException();
        }
        AbstractByteBigList.this.removeByte(this.last);
        if (this.last < this.pos) {
          this.pos -= 1L;
        }
        this.last = -1L;
      }
    };
  }
  
  public ByteBigListIterator listIterator(int index)
  {
    return listIterator(index);
  }
  
  public boolean contains(byte local_k)
  {
    return indexOf(local_k) >= 0L;
  }
  
  public long indexOf(byte local_k)
  {
    ByteBigListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      byte local_e = local_i.nextByte();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1L;
  }
  
  public long lastIndexOf(byte local_k)
  {
    ByteBigListIterator local_i = listIterator(size64());
    while (local_i.hasPrevious())
    {
      byte local_e = local_i.previousByte();
      if (local_k == local_e) {
        return local_i.nextIndex();
      }
    }
    return -1L;
  }
  
  public void size(long size)
  {
    long local_i = size64();
    if (size > local_i) {
      while (local_i++ < size) {
        add((byte)0);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public void size(int size)
  {
    size(size);
  }
  
  public ByteBigList subList(long from, long local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ByteSubList(this, from, local_to);
  }
  
  public void removeElements(long from, long local_to)
  {
    ensureIndex(local_to);
    ByteBigListIterator local_i = listIterator(from);
    long local_n = local_to - from;
    if (local_n < 0L) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0L)
    {
      local_i.nextByte();
      local_i.remove();
    }
  }
  
  public void addElements(long index, byte[][] local_a, long offset, long length)
  {
    ensureIndex(index);
    ByteBigArrays.ensureOffsetLength(local_a, offset, length);
    while (length-- != 0L) {
      add(index++, ByteBigArrays.get(local_a, offset++));
    }
  }
  
  public void addElements(long index, byte[][] local_a)
  {
    addElements(index, local_a, 0L, ByteBigArrays.length(local_a));
  }
  
  public void getElements(long from, byte[][] local_a, long offset, long length)
  {
    ByteBigListIterator local_i = listIterator(from);
    ByteBigArrays.ensureOffsetLength(local_a, offset, length);
    if (from + length > size64()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size64() + ")");
    }
    while (length-- != 0L) {
      ByteBigArrays.set(local_a, offset++, local_i.nextByte());
    }
  }
  
  @Deprecated
  public int size()
  {
    return (int)Math.min(2147483647L, size64());
  }
  
  private boolean valEquals(Object local_a, Object local_b)
  {
    return local_a == null ? false : local_b == null ? true : local_a.equals(local_b);
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (!(local_o instanceof BigList)) {
      return false;
    }
    BigList<?> local_l = (BigList)local_o;
    long local_s = size64();
    if (local_s != local_l.size64()) {
      return false;
    }
    BigListIterator<?> local_i1 = listIterator();
    BigListIterator<?> local_i2 = local_l.listIterator();
    while (local_s-- != 0L) {
      if (!valEquals(local_i1.next(), local_i2.next())) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(BigList<? extends Byte> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof ByteBigList))
    {
      ByteBigListIterator local_i1 = listIterator();
      ByteBigListIterator local_i2 = ((ByteBigList)local_l).listIterator();
      while ((local_i1.hasNext()) && (local_i2.hasNext()))
      {
        byte local_e1 = local_i1.nextByte();
        byte local_e2 = local_i2.nextByte();
        int local_r;
        if ((local_r = local_e1 == local_e2 ? 0 : local_e1 < local_e2 ? -1 : 1) != 0) {
          return local_r;
        }
      }
      return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
    }
    BigListIterator<? extends Byte> local_i1 = listIterator();
    BigListIterator<? extends Byte> local_i2 = local_l.listIterator();
    while ((local_i1.hasNext()) && (local_i2.hasNext()))
    {
      int local_r;
      if ((local_r = ((Comparable)local_i1.next()).compareTo(local_i2.next())) != 0) {
        return local_r;
      }
    }
    return local_i1.hasNext() ? 1 : local_i2.hasNext() ? -1 : 0;
  }
  
  public int hashCode()
  {
    ByteIterator local_i = iterator();
    int local_h = 1;
    long local_s = size64();
    while (local_s-- != 0L)
    {
      byte local_k = local_i.nextByte();
      local_h = 31 * local_h + local_k;
    }
    return local_h;
  }
  
  public void push(byte local_o)
  {
    add(local_o);
  }
  
  public byte popByte()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return removeByte(size64() - 1L);
  }
  
  public byte topByte()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getByte(size64() - 1L);
  }
  
  public byte peekByte(int local_i)
  {
    return getByte(size64() - 1L - local_i);
  }
  
  public byte getByte(int index)
  {
    return getByte(index);
  }
  
  public boolean rem(byte local_k)
  {
    long index = indexOf(local_k);
    if (index == -1L) {
      return false;
    }
    removeByte(index);
    return true;
  }
  
  public boolean addAll(long index, ByteCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(long index, ByteBigList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(ByteCollection local_c)
  {
    return addAll(size64(), local_c);
  }
  
  public boolean addAll(ByteBigList local_l)
  {
    return addAll(size64(), local_l);
  }
  
  public void add(long index, Byte local_ok)
  {
    add(index, local_ok.byteValue());
  }
  
  public Byte set(long index, Byte local_ok)
  {
    return Byte.valueOf(set(index, local_ok.byteValue()));
  }
  
  public Byte get(long index)
  {
    return Byte.valueOf(getByte(index));
  }
  
  public long indexOf(Object local_ok)
  {
    return indexOf(((Byte)local_ok).byteValue());
  }
  
  public long lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Byte)local_ok).byteValue());
  }
  
  public Byte remove(int index)
  {
    return Byte.valueOf(removeByte(index));
  }
  
  public Byte remove(long index)
  {
    return Byte.valueOf(removeByte(index));
  }
  
  public void push(Byte local_o)
  {
    push(local_o.byteValue());
  }
  
  public Byte pop()
  {
    return Byte.valueOf(popByte());
  }
  
  public Byte top()
  {
    return Byte.valueOf(topByte());
  }
  
  public Byte peek(int local_i)
  {
    return Byte.valueOf(peekByte(local_i));
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ByteIterator local_i = iterator();
    long local_n = size64();
    boolean first = true;
    local_s.append("[");
    while (local_n-- != 0L)
    {
      if (first) {
        first = false;
      } else {
        local_s.append(", ");
      }
      byte local_k = local_i.nextByte();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("]");
    return local_s.toString();
  }
  
  public static class ByteSubList
    extends AbstractByteBigList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteBigList field_288;
    protected final long from;
    protected long field_289;
    private static final boolean ASSERTS = false;
    
    public ByteSubList(ByteBigList local_l, long from, long local_to)
    {
      this.field_288 = local_l;
      this.from = from;
      this.field_289 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(byte local_k)
    {
      this.field_288.add(this.field_289, local_k);
      this.field_289 += 1L;
      return true;
    }
    
    public void add(long index, byte local_k)
    {
      ensureIndex(index);
      this.field_288.add(this.from + index, local_k);
      this.field_289 += 1L;
    }
    
    public boolean addAll(long index, Collection<? extends Byte> local_c)
    {
      ensureIndex(index);
      this.field_289 += local_c.size();
      return this.field_288.addAll(this.from + index, local_c);
    }
    
    public byte getByte(long index)
    {
      ensureRestrictedIndex(index);
      return this.field_288.getByte(this.from + index);
    }
    
    public byte removeByte(long index)
    {
      ensureRestrictedIndex(index);
      this.field_289 -= 1L;
      return this.field_288.removeByte(this.from + index);
    }
    
    public byte set(long index, byte local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_288.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0L, size64());
    }
    
    public long size64()
    {
      return this.field_289 - this.from;
    }
    
    public void getElements(long from, byte[][] local_a, long offset, long length)
    {
      ensureIndex(from);
      if (from + length > size64()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
      }
      this.field_288.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_288.removeElements(this.from + from, this.from + local_to);
      this.field_289 -= local_to - from;
    }
    
    public void addElements(long index, byte[][] local_a, long offset, long length)
    {
      ensureIndex(index);
      this.field_288.addElements(this.from + index, local_a, offset, length);
      this.field_289 += length;
    }
    
    public ByteBigListIterator listIterator(final long index)
    {
      ensureIndex(index);
      new AbstractByteBigListIterator()
      {
        long pos = index;
        long last = -1L;
        
        public boolean hasNext()
        {
          return this.pos < AbstractByteBigList.ByteSubList.this.size64();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0L;
        }
        
        public byte nextByte()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractByteBigList.ByteSubList.this.field_288.getByte(AbstractByteBigList.ByteSubList.this.from + (this.last = this.pos++));
        }
        
        public byte previousByte()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractByteBigList.ByteSubList.this.field_288.getByte(AbstractByteBigList.ByteSubList.this.from + (this.last = --this.pos));
        }
        
        public long nextIndex()
        {
          return this.pos;
        }
        
        public long previousIndex()
        {
          return this.pos - 1L;
        }
        
        public void add(byte local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractByteBigList.ByteSubList.this.add(this.pos++, local_k);
          this.last = -1L;
        }
        
        public void set(byte local_k)
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractByteBigList.ByteSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1L) {
            throw new IllegalStateException();
          }
          AbstractByteBigList.ByteSubList.this.removeByte(this.last);
          if (this.last < this.pos) {
            this.pos -= 1L;
          }
          this.last = -1L;
        }
      };
    }
    
    public ByteBigList subList(long from, long local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      if (from > local_to) {
        throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
      }
      return new ByteSubList(this, from, local_to);
    }
    
    public boolean rem(byte local_k)
    {
      long index = indexOf(local_k);
      if (index == -1L) {
        return false;
      }
      this.field_289 -= 1L;
      this.field_288.removeByte(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Byte)local_o).byteValue());
    }
    
    public boolean addAll(long index, ByteCollection local_c)
    {
      ensureIndex(index);
      this.field_289 += local_c.size();
      return this.field_288.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(long index, ByteList local_l)
    {
      ensureIndex(index);
      this.field_289 += local_l.size();
      return this.field_288.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */