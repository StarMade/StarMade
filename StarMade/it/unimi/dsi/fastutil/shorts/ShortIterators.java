package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.bytes.ByteIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static ShortListIterator singleton(short element)
  {
    return new SingletonIterator(element);
  }
  
  public static ShortListIterator wrap(short[] array, int offset, int length)
  {
    ShortArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static ShortListIterator wrap(short[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(ShortIterator local_i, short[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextShort();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(ShortIterator local_i, short[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static short[] unwrap(ShortIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    short[] array = new short[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = ShortArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextShort();
    }
    return ShortArrays.trim(array, local_j);
  }
  
  public static short[] unwrap(ShortIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(ShortIterator local_i, ShortCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextShort());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(ShortIterator local_i, ShortCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextShort());
    }
    return local_n;
  }
  
  public static int pour(ShortIterator local_i, ShortCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextShort());
    }
    return max - local_j - 1;
  }
  
  public static int pour(ShortIterator local_i, ShortCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static ShortList pour(ShortIterator local_i, int max)
  {
    ShortArrayList local_l = new ShortArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static ShortList pour(ShortIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static ShortIterator asShortIterator(Iterator local_i)
  {
    if ((local_i instanceof ShortIterator)) {
      return (ShortIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static ShortListIterator asShortIterator(ListIterator local_i)
  {
    if ((local_i instanceof ShortListIterator)) {
      return (ShortListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static ShortListIterator fromTo(short from, short local_to)
  {
    return new IntervalIterator(from, local_to);
  }
  
  public static ShortIterator concat(ShortIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static ShortIterator concat(ShortIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static ShortIterator unmodifiable(ShortIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static ShortBidirectionalIterator unmodifiable(ShortBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static ShortListIterator unmodifiable(ShortListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static ShortIterator wrap(ByteIterator iterator)
  {
    return new ByteIteratorWrapper(iterator);
  }
  
  protected static class ByteIteratorWrapper
    implements ShortIterator
  {
    final ByteIterator iterator;
    
    public ByteIteratorWrapper(ByteIterator iterator)
    {
      this.iterator = iterator;
    }
    
    public boolean hasNext()
    {
      return this.iterator.hasNext();
    }
    
    public Short next()
    {
      return Short.valueOf((short)this.iterator.nextByte());
    }
    
    public short nextShort()
    {
      return (short)this.iterator.nextByte();
    }
    
    public void remove()
    {
      this.iterator.remove();
    }
    
    public int skip(int local_n)
    {
      return this.iterator.skip(local_n);
    }
  }
  
  public static class UnmodifiableListIterator
    extends AbstractShortListIterator
  {
    protected final ShortListIterator field_53;
    
    public UnmodifiableListIterator(ShortListIterator local_i)
    {
      this.field_53 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
    
    public short nextShort()
    {
      return this.field_53.nextShort();
    }
    
    public short previousShort()
    {
      return this.field_53.previousShort();
    }
    
    public int nextIndex()
    {
      return this.field_53.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_53.previousIndex();
    }
    
    public Short next()
    {
      return (Short)this.field_53.next();
    }
    
    public Short previous()
    {
      return (Short)this.field_53.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ShortBidirectionalIterator field_53;
    
    public UnmodifiableBidirectionalIterator(ShortBidirectionalIterator local_i)
    {
      this.field_53 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
    
    public short nextShort()
    {
      return this.field_53.nextShort();
    }
    
    public short previousShort()
    {
      return this.field_53.previousShort();
    }
    
    public Short next()
    {
      return (Short)this.field_53.next();
    }
    
    public Short previous()
    {
      return (Short)this.field_53.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractShortIterator
  {
    protected final ShortIterator field_53;
    
    public UnmodifiableIterator(ShortIterator local_i)
    {
      this.field_53 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public short nextShort()
    {
      return this.field_53.nextShort();
    }
    
    public Short next()
    {
      return (Short)this.field_53.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractShortIterator
  {
    final ShortIterator[] field_390;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(ShortIterator[] local_a, int offset, int length)
    {
      this.field_390 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_390[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      short next = this.field_390[(this.lastOffset = this.offset)].nextShort();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_390[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_390[this.offset].skip(local_n - skipped);
        if (this.field_390[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class IntervalIterator
    extends AbstractShortListIterator
  {
    private final short from;
    private final short field_400;
    short curr;
    
    public IntervalIterator(short from, short local_to)
    {
      this.from = (this.curr = from);
      this.field_400 = local_to;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.field_400;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > this.from;
    }
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.curr++;
    }
    
    public short previousShort()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return this.curr = (short)(this.curr - 1);
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
      if (this.curr + local_n <= this.field_400)
      {
        this.curr = ((short)(this.curr + local_n));
        return local_n;
      }
      local_n = this.field_400 - this.curr;
      this.curr = this.field_400;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (this.curr - local_n >= this.from)
      {
        this.curr = ((short)(this.curr - local_n));
        return local_n;
      }
      local_n = this.curr - this.from;
      this.curr = this.from;
      return local_n;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractShortListIterator
  {
    final ListIterator<Short> field_53;
    
    public ListIteratorWrapper(ListIterator<Short> local_i)
    {
      this.field_53 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_53.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_53.previousIndex();
    }
    
    public void set(short local_k)
    {
      this.field_53.set(Short.valueOf(local_k));
    }
    
    public void add(short local_k)
    {
      this.field_53.add(Short.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_53.remove();
    }
    
    public short nextShort()
    {
      return ((Short)this.field_53.next()).shortValue();
    }
    
    public short previousShort()
    {
      return ((Short)this.field_53.previous()).shortValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractShortIterator
  {
    final Iterator<Short> field_53;
    
    public IteratorWrapper(Iterator<Short> local_i)
    {
      this.field_53 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public void remove()
    {
      this.field_53.remove();
    }
    
    public short nextShort()
    {
      return ((Short)this.field_53.next()).shortValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractShortListIterator
  {
    private final short[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(short[] array, int offset, int length)
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
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public short previousShort()
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
    extends AbstractShortListIterator
  {
    private final short element;
    private int curr;
    
    public SingletonIterator(short element)
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
    
    public short nextShort()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public short previousShort()
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
    extends AbstractShortListIterator
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
    
    public short nextShort()
    {
      throw new NoSuchElementException();
    }
    
    public short previousShort()
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
      return ShortIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ShortIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */