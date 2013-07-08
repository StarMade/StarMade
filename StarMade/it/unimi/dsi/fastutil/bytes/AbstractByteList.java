package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbstractByteList
  extends AbstractByteCollection
  implements ByteList, ByteStack
{
  protected void ensureIndex(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index > size()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + size() + ")");
    }
  }
  
  protected void ensureRestrictedIndex(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + size() + ")");
    }
  }
  
  public void add(int index, byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(byte local_k)
  {
    add(size(), local_k);
    return true;
  }
  
  public byte removeByte(int local_i)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte set(int index, byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int index, Collection<? extends Byte> local_c)
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
  
  public boolean addAll(Collection<? extends Byte> local_c)
  {
    return addAll(size(), local_c);
  }
  
  @Deprecated
  public ByteListIterator byteListIterator()
  {
    return listIterator();
  }
  
  @Deprecated
  public ByteListIterator byteListIterator(int index)
  {
    return listIterator(index);
  }
  
  public ByteListIterator iterator()
  {
    return listIterator();
  }
  
  public ByteListIterator listIterator()
  {
    return listIterator(0);
  }
  
  public ByteListIterator listIterator(final int index)
  {
    new AbstractByteListIterator()
    {
      int pos = index;
      int last = -1;
      
      public boolean hasNext()
      {
        return this.pos < AbstractByteList.this.size();
      }
      
      public boolean hasPrevious()
      {
        return this.pos > 0;
      }
      
      public byte nextByte()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return AbstractByteList.this.getByte(this.last = this.pos++);
      }
      
      public byte previousByte()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        return AbstractByteList.this.getByte(this.last = --this.pos);
      }
      
      public int nextIndex()
      {
        return this.pos;
      }
      
      public int previousIndex()
      {
        return this.pos - 1;
      }
      
      public void add(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractByteList.this.add(this.pos++, local_k);
        this.last = -1;
      }
      
      public void set(byte local_k)
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractByteList.this.set(this.last, local_k);
      }
      
      public void remove()
      {
        if (this.last == -1) {
          throw new IllegalStateException();
        }
        AbstractByteList.this.removeByte(this.last);
        if (this.last < this.pos) {
          this.pos -= 1;
        }
        this.last = -1;
      }
    };
  }
  
  public boolean contains(byte local_k)
  {
    return indexOf(local_k) >= 0;
  }
  
  public int indexOf(byte local_k)
  {
    ByteListIterator local_i = listIterator();
    while (local_i.hasNext())
    {
      byte local_e = local_i.nextByte();
      if (local_k == local_e) {
        return local_i.previousIndex();
      }
    }
    return -1;
  }
  
  public int lastIndexOf(byte local_k)
  {
    ByteListIterator local_i = listIterator(size());
    while (local_i.hasPrevious())
    {
      byte local_e = local_i.previousByte();
      if (local_k == local_e) {
        return local_i.nextIndex();
      }
    }
    return -1;
  }
  
  public void size(int size)
  {
    int local_i = size();
    if (size > local_i) {
      while (local_i++ < size) {
        add((byte)0);
      }
    }
    while (local_i-- != size) {
      remove(local_i);
    }
  }
  
  public ByteList subList(int from, int local_to)
  {
    ensureIndex(from);
    ensureIndex(local_to);
    if (from > local_to) {
      throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    return new ByteSubList(this, from, local_to);
  }
  
  @Deprecated
  public ByteList byteSubList(int from, int local_to)
  {
    return subList(from, local_to);
  }
  
  public void removeElements(int from, int local_to)
  {
    ensureIndex(local_to);
    ByteListIterator local_i = listIterator(from);
    int local_n = local_to - from;
    if (local_n < 0) {
      throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + local_to + ")");
    }
    while (local_n-- != 0)
    {
      local_i.nextByte();
      local_i.remove();
    }
  }
  
  public void addElements(int index, byte[] local_a, int offset, int length)
  {
    ensureIndex(index);
    if (offset < 0) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (offset + length > local_a.length) {
      throw new ArrayIndexOutOfBoundsException("End index (" + (offset + length) + ") is greater than array length (" + local_a.length + ")");
    }
    while (length-- != 0) {
      add(index++, local_a[(offset++)]);
    }
  }
  
  public void addElements(int index, byte[] local_a)
  {
    addElements(index, local_a, 0, local_a.length);
  }
  
  public void getElements(int from, byte[] local_a, int offset, int length)
  {
    ByteListIterator local_i = listIterator(from);
    if (offset < 0) {
      throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
    }
    if (offset + length > local_a.length) {
      throw new ArrayIndexOutOfBoundsException("End index (" + (offset + length) + ") is greater than array length (" + local_a.length + ")");
    }
    if (from + length > size()) {
      throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + size() + ")");
    }
    while (length-- != 0) {
      local_a[(offset++)] = local_i.nextByte();
    }
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
    if (!(local_o instanceof List)) {
      return false;
    }
    List<?> local_l = (List)local_o;
    int local_s = size();
    if (local_s != local_l.size()) {
      return false;
    }
    ListIterator<?> local_i1 = listIterator();
    ListIterator<?> local_i2 = local_l.listIterator();
    while (local_s-- != 0) {
      if (!valEquals(local_i1.next(), local_i2.next())) {
        return false;
      }
    }
    return true;
  }
  
  public int compareTo(List<? extends Byte> local_l)
  {
    if (local_l == this) {
      return 0;
    }
    if ((local_l instanceof ByteList))
    {
      ByteListIterator local_i1 = listIterator();
      ByteListIterator local_i2 = ((ByteList)local_l).listIterator();
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
    ListIterator<? extends Byte> local_i1 = listIterator();
    ListIterator<? extends Byte> local_i2 = local_l.listIterator();
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
    int local_s = size();
    while (local_s-- != 0)
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
    return removeByte(size() - 1);
  }
  
  public byte topByte()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getByte(size() - 1);
  }
  
  public byte peekByte(int local_i)
  {
    return getByte(size() - 1 - local_i);
  }
  
  public boolean rem(byte local_k)
  {
    int index = indexOf(local_k);
    if (index == -1) {
      return false;
    }
    removeByte(index);
    return true;
  }
  
  public boolean remove(Object local_o)
  {
    return rem(((Byte)local_o).byteValue());
  }
  
  public boolean addAll(int index, ByteCollection local_c)
  {
    return addAll(index, local_c);
  }
  
  public boolean addAll(int index, ByteList local_l)
  {
    return addAll(index, local_l);
  }
  
  public boolean addAll(ByteCollection local_c)
  {
    return addAll(size(), local_c);
  }
  
  public boolean addAll(ByteList local_l)
  {
    return addAll(size(), local_l);
  }
  
  public void add(int index, Byte local_ok)
  {
    add(index, local_ok.byteValue());
  }
  
  public Byte set(int index, Byte local_ok)
  {
    return Byte.valueOf(set(index, local_ok.byteValue()));
  }
  
  public Byte get(int index)
  {
    return Byte.valueOf(getByte(index));
  }
  
  public int indexOf(Object local_ok)
  {
    return indexOf(((Byte)local_ok).byteValue());
  }
  
  public int lastIndexOf(Object local_ok)
  {
    return lastIndexOf(((Byte)local_ok).byteValue());
  }
  
  public Byte remove(int index)
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
    int local_n = size();
    boolean first = true;
    local_s.append("[");
    while (local_n-- != 0)
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
    extends AbstractByteList
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final ByteList field_288;
    protected final int from;
    protected int field_289;
    private static final boolean ASSERTS = false;
    
    public ByteSubList(ByteList local_l, int from, int local_to)
    {
      this.field_288 = local_l;
      this.from = from;
      this.field_289 = local_to;
    }
    
    private void assertRange() {}
    
    public boolean add(byte local_k)
    {
      this.field_288.add(this.field_289, local_k);
      this.field_289 += 1;
      return true;
    }
    
    public void add(int index, byte local_k)
    {
      ensureIndex(index);
      this.field_288.add(this.from + index, local_k);
      this.field_289 += 1;
    }
    
    public boolean addAll(int index, Collection<? extends Byte> local_c)
    {
      ensureIndex(index);
      this.field_289 += local_c.size();
      return this.field_288.addAll(this.from + index, local_c);
    }
    
    public byte getByte(int index)
    {
      ensureRestrictedIndex(index);
      return this.field_288.getByte(this.from + index);
    }
    
    public byte removeByte(int index)
    {
      ensureRestrictedIndex(index);
      this.field_289 -= 1;
      return this.field_288.removeByte(this.from + index);
    }
    
    public byte set(int index, byte local_k)
    {
      ensureRestrictedIndex(index);
      return this.field_288.set(this.from + index, local_k);
    }
    
    public void clear()
    {
      removeElements(0, size());
    }
    
    public int size()
    {
      return this.field_289 - this.from;
    }
    
    public void getElements(int from, byte[] local_a, int offset, int length)
    {
      ensureIndex(from);
      if (from + length > size()) {
        throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
      }
      this.field_288.getElements(this.from + from, local_a, offset, length);
    }
    
    public void removeElements(int from, int local_to)
    {
      ensureIndex(from);
      ensureIndex(local_to);
      this.field_288.removeElements(this.from + from, this.from + local_to);
      this.field_289 -= local_to - from;
    }
    
    public void addElements(int index, byte[] local_a, int offset, int length)
    {
      ensureIndex(index);
      this.field_288.addElements(this.from + index, local_a, offset, length);
      this.field_289 += length;
    }
    
    public ByteListIterator listIterator(final int index)
    {
      ensureIndex(index);
      new AbstractByteListIterator()
      {
        int pos = index;
        int last = -1;
        
        public boolean hasNext()
        {
          return this.pos < AbstractByteList.ByteSubList.this.size();
        }
        
        public boolean hasPrevious()
        {
          return this.pos > 0;
        }
        
        public byte nextByte()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return AbstractByteList.ByteSubList.this.field_288.getByte(AbstractByteList.ByteSubList.this.from + (this.last = this.pos++));
        }
        
        public byte previousByte()
        {
          if (!hasPrevious()) {
            throw new NoSuchElementException();
          }
          return AbstractByteList.ByteSubList.this.field_288.getByte(AbstractByteList.ByteSubList.this.from + (this.last = --this.pos));
        }
        
        public int nextIndex()
        {
          return this.pos;
        }
        
        public int previousIndex()
        {
          return this.pos - 1;
        }
        
        public void add(byte local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractByteList.ByteSubList.this.add(this.pos++, local_k);
          this.last = -1;
        }
        
        public void set(byte local_k)
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractByteList.ByteSubList.this.set(this.last, local_k);
        }
        
        public void remove()
        {
          if (this.last == -1) {
            throw new IllegalStateException();
          }
          AbstractByteList.ByteSubList.this.removeByte(this.last);
          if (this.last < this.pos) {
            this.pos -= 1;
          }
          this.last = -1;
        }
      };
    }
    
    public ByteList subList(int from, int local_to)
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
      int index = indexOf(local_k);
      if (index == -1) {
        return false;
      }
      this.field_289 -= 1;
      this.field_288.removeByte(this.from + index);
      return true;
    }
    
    public boolean remove(Object local_o)
    {
      return rem(((Byte)local_o).byteValue());
    }
    
    public boolean addAll(int index, ByteCollection local_c)
    {
      ensureIndex(index);
      this.field_289 += local_c.size();
      return this.field_288.addAll(this.from + index, local_c);
    }
    
    public boolean addAll(int index, ByteList local_l)
    {
      ensureIndex(index);
      this.field_289 += local_l.size();
      return this.field_288.addAll(this.from + index, local_l);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */