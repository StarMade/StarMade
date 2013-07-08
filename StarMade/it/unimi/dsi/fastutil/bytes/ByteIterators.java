package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ByteIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static ByteListIterator singleton(byte element)
  {
    return new SingletonIterator(element);
  }
  
  public static ByteListIterator wrap(byte[] array, int offset, int length)
  {
    ByteArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static ByteListIterator wrap(byte[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(ByteIterator local_i, byte[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextByte();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(ByteIterator local_i, byte[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static byte[] unwrap(ByteIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    byte[] array = new byte[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = ByteArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextByte();
    }
    return ByteArrays.trim(array, local_j);
  }
  
  public static byte[] unwrap(ByteIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(ByteIterator local_i, ByteCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextByte());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(ByteIterator local_i, ByteCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextByte());
    }
    return local_n;
  }
  
  public static int pour(ByteIterator local_i, ByteCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextByte());
    }
    return max - local_j - 1;
  }
  
  public static int pour(ByteIterator local_i, ByteCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static ByteList pour(ByteIterator local_i, int max)
  {
    ByteArrayList local_l = new ByteArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static ByteList pour(ByteIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static ByteIterator asByteIterator(Iterator local_i)
  {
    if ((local_i instanceof ByteIterator)) {
      return (ByteIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static ByteListIterator asByteIterator(ListIterator local_i)
  {
    if ((local_i instanceof ByteListIterator)) {
      return (ByteListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static ByteListIterator fromTo(byte from, byte local_to)
  {
    return new IntervalIterator(from, local_to);
  }
  
  public static ByteIterator concat(ByteIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static ByteIterator concat(ByteIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static ByteIterator unmodifiable(ByteIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static ByteBidirectionalIterator unmodifiable(ByteBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static ByteListIterator unmodifiable(ByteListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static class UnmodifiableListIterator
    extends AbstractByteListIterator
  {
    protected final ByteListIterator field_58;
    
    public UnmodifiableListIterator(ByteListIterator local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
    
    public byte nextByte()
    {
      return this.field_58.nextByte();
    }
    
    public byte previousByte()
    {
      return this.field_58.previousByte();
    }
    
    public int nextIndex()
    {
      return this.field_58.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_58.previousIndex();
    }
    
    public Byte next()
    {
      return (Byte)this.field_58.next();
    }
    
    public Byte previous()
    {
      return (Byte)this.field_58.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ByteBidirectionalIterator field_58;
    
    public UnmodifiableBidirectionalIterator(ByteBidirectionalIterator local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
    
    public byte nextByte()
    {
      return this.field_58.nextByte();
    }
    
    public byte previousByte()
    {
      return this.field_58.previousByte();
    }
    
    public Byte next()
    {
      return (Byte)this.field_58.next();
    }
    
    public Byte previous()
    {
      return (Byte)this.field_58.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractByteIterator
  {
    protected final ByteIterator field_58;
    
    public UnmodifiableIterator(ByteIterator local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public byte nextByte()
    {
      return this.field_58.nextByte();
    }
    
    public Byte next()
    {
      return (Byte)this.field_58.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractByteIterator
  {
    final ByteIterator[] field_358;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(ByteIterator[] local_a, int offset, int length)
    {
      this.field_358 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_358[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      byte next = this.field_358[(this.lastOffset = this.offset)].nextByte();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_358[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_358[this.offset].skip(local_n - skipped);
        if (this.field_358[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class IntervalIterator
    extends AbstractByteListIterator
  {
    private final byte from;
    private final byte field_430;
    byte curr;
    
    public IntervalIterator(byte from, byte local_to)
    {
      this.from = (this.curr = from);
      this.field_430 = local_to;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.field_430;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > this.from;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.curr++;
    }
    
    public byte previousByte()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return this.curr = (byte)(this.curr - 1);
    }
    
    public int nextIndex()
    {
      return this.curr - this.from;
    }
    
    public int previousIndex()
    {
      return this.curr - this.from - 1;
    }
    
    public int skip(int local_n)
    {
      if (this.curr + local_n <= this.field_430)
      {
        this.curr = ((byte)(this.curr + local_n));
        return local_n;
      }
      local_n = this.field_430 - this.curr;
      this.curr = this.field_430;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (this.curr - local_n >= this.from)
      {
        this.curr = ((byte)(this.curr - local_n));
        return local_n;
      }
      local_n = this.curr - this.from;
      this.curr = this.from;
      return local_n;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractByteListIterator
  {
    final ListIterator<Byte> field_58;
    
    public ListIteratorWrapper(ListIterator<Byte> local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_58.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_58.previousIndex();
    }
    
    public void set(byte local_k)
    {
      this.field_58.set(Byte.valueOf(local_k));
    }
    
    public void add(byte local_k)
    {
      this.field_58.add(Byte.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_58.remove();
    }
    
    public byte nextByte()
    {
      return ((Byte)this.field_58.next()).byteValue();
    }
    
    public byte previousByte()
    {
      return ((Byte)this.field_58.previous()).byteValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractByteIterator
  {
    final Iterator<Byte> field_58;
    
    public IteratorWrapper(Iterator<Byte> local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public void remove()
    {
      this.field_58.remove();
    }
    
    public byte nextByte()
    {
      return ((Byte)this.field_58.next()).byteValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractByteListIterator
  {
    private final byte[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(byte[] array, int offset, int length)
    {
      this.array = array;
      this.offset = offset;
      this.length = length;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.length;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > 0;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public byte previousByte()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + --this.curr)];
    }
    
    public int skip(int local_n)
    {
      if (local_n <= this.length - this.curr)
      {
        this.curr += local_n;
        return local_n;
      }
      local_n = this.length - this.curr;
      this.curr = this.length;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (local_n <= this.curr)
      {
        this.curr -= local_n;
        return local_n;
      }
      local_n = this.curr;
      this.curr = 0;
      return local_n;
    }
    
    public int nextIndex()
    {
      return this.curr;
    }
    
    public int previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  private static class SingletonIterator
    extends AbstractByteListIterator
  {
    private final byte element;
    private int curr;
    
    public SingletonIterator(byte element)
    {
      this.element = element;
    }
    
    public boolean hasNext()
    {
      return this.curr == 0;
    }
    
    public boolean hasPrevious()
    {
      return this.curr == 1;
    }
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public byte previousByte()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = 0;
      return this.element;
    }
    
    public int nextIndex()
    {
      return this.curr;
    }
    
    public int previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  public static class EmptyIterator
    extends AbstractByteListIterator
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean hasNext()
    {
      return false;
    }
    
    public boolean hasPrevious()
    {
      return false;
    }
    
    public byte nextByte()
    {
      throw new NoSuchElementException();
    }
    
    public byte previousByte()
    {
      throw new NoSuchElementException();
    }
    
    public int nextIndex()
    {
      return 0;
    }
    
    public int previousIndex()
    {
      return -1;
    }
    
    public int skip(int local_n)
    {
      return 0;
    }
    
    public int back(int local_n)
    {
      return 0;
    }
    
    public Object clone()
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ByteIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */