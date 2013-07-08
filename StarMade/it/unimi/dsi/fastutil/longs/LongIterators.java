package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LongIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static LongListIterator singleton(long element)
  {
    return new SingletonIterator(element);
  }
  
  public static LongListIterator wrap(long[] array, int offset, int length)
  {
    LongArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static LongListIterator wrap(long[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(LongIterator local_i, long[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextLong();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(LongIterator local_i, long[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static long[] unwrap(LongIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    long[] array = new long[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = LongArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextLong();
    }
    return LongArrays.trim(array, local_j);
  }
  
  public static long[] unwrap(LongIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(LongIterator local_i, LongCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextLong());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(LongIterator local_i, LongCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextLong());
    }
    return local_n;
  }
  
  public static int pour(LongIterator local_i, LongCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextLong());
    }
    return max - local_j - 1;
  }
  
  public static int pour(LongIterator local_i, LongCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static LongList pour(LongIterator local_i, int max)
  {
    LongArrayList local_l = new LongArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static LongList pour(LongIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static LongIterator asLongIterator(Iterator local_i)
  {
    if ((local_i instanceof LongIterator)) {
      return (LongIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static LongListIterator asLongIterator(ListIterator local_i)
  {
    if ((local_i instanceof LongListIterator)) {
      return (LongListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static LongBidirectionalIterator fromTo(long from, long local_to)
  {
    return new IntervalIterator(from, local_to);
  }
  
  public static LongIterator concat(LongIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static LongIterator concat(LongIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static LongIterator unmodifiable(LongIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static LongBidirectionalIterator unmodifiable(LongBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static LongListIterator unmodifiable(LongListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static LongIterator wrap(ByteIterator iterator)
  {
    return new ByteIteratorWrapper(iterator);
  }
  
  public static LongIterator wrap(ShortIterator iterator)
  {
    return new ShortIteratorWrapper(iterator);
  }
  
  public static LongIterator wrap(IntIterator iterator)
  {
    return new IntIteratorWrapper(iterator);
  }
  
  protected static class IntIteratorWrapper
    implements LongIterator
  {
    final IntIterator iterator;
    
    public IntIteratorWrapper(IntIterator iterator)
    {
      this.iterator = iterator;
    }
    
    public boolean hasNext()
    {
      return this.iterator.hasNext();
    }
    
    public Long next()
    {
      return Long.valueOf(this.iterator.nextInt());
    }
    
    public long nextLong()
    {
      return this.iterator.nextInt();
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
  
  protected static class ShortIteratorWrapper
    implements LongIterator
  {
    final ShortIterator iterator;
    
    public ShortIteratorWrapper(ShortIterator iterator)
    {
      this.iterator = iterator;
    }
    
    public boolean hasNext()
    {
      return this.iterator.hasNext();
    }
    
    public Long next()
    {
      return Long.valueOf(this.iterator.nextShort());
    }
    
    public long nextLong()
    {
      return this.iterator.nextShort();
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
  
  protected static class ByteIteratorWrapper
    implements LongIterator
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
    
    public Long next()
    {
      return Long.valueOf(this.iterator.nextByte());
    }
    
    public long nextLong()
    {
      return this.iterator.nextByte();
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
    extends AbstractLongListIterator
  {
    protected final LongListIterator field_1;
    
    public UnmodifiableListIterator(LongListIterator local_i)
    {
      this.field_1 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_1.hasPrevious();
    }
    
    public long nextLong()
    {
      return this.field_1.nextLong();
    }
    
    public long previousLong()
    {
      return this.field_1.previousLong();
    }
    
    public int nextIndex()
    {
      return this.field_1.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_1.previousIndex();
    }
    
    public Long next()
    {
      return (Long)this.field_1.next();
    }
    
    public Long previous()
    {
      return (Long)this.field_1.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final LongBidirectionalIterator field_1;
    
    public UnmodifiableBidirectionalIterator(LongBidirectionalIterator local_i)
    {
      this.field_1 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_1.hasPrevious();
    }
    
    public long nextLong()
    {
      return this.field_1.nextLong();
    }
    
    public long previousLong()
    {
      return this.field_1.previousLong();
    }
    
    public Long next()
    {
      return (Long)this.field_1.next();
    }
    
    public Long previous()
    {
      return (Long)this.field_1.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractLongIterator
  {
    protected final LongIterator field_1;
    
    public UnmodifiableIterator(LongIterator local_i)
    {
      this.field_1 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public long nextLong()
    {
      return this.field_1.nextLong();
    }
    
    public Long next()
    {
      return (Long)this.field_1.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractLongIterator
  {
    final LongIterator[] field_404;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(LongIterator[] local_a, int offset, int length)
    {
      this.field_404 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_404[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public long nextLong()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      long next = this.field_404[(this.lastOffset = this.offset)].nextLong();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_404[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_404[this.offset].skip(local_n - skipped);
        if (this.field_404[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class IntervalIterator
    extends AbstractLongBidirectionalIterator
  {
    private final long from;
    private final long field_311;
    long curr;
    
    public IntervalIterator(long from, long local_to)
    {
      this.from = (this.curr = from);
      this.field_311 = local_to;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.field_311;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > this.from;
    }
    
    public long nextLong()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.curr++;
    }
    
    public long previousLong()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return --this.curr;
    }
    
    public int skip(int local_n)
    {
      if (this.curr + local_n <= this.field_311)
      {
        this.curr += local_n;
        return local_n;
      }
      local_n = (int)(this.field_311 - this.curr);
      this.curr = this.field_311;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (this.curr - local_n >= this.from)
      {
        this.curr -= local_n;
        return local_n;
      }
      local_n = (int)(this.curr - this.from);
      this.curr = this.from;
      return local_n;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractLongListIterator
  {
    final ListIterator<Long> field_1;
    
    public ListIteratorWrapper(ListIterator<Long> local_i)
    {
      this.field_1 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_1.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_1.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_1.previousIndex();
    }
    
    public void set(long local_k)
    {
      this.field_1.set(Long.valueOf(local_k));
    }
    
    public void add(long local_k)
    {
      this.field_1.add(Long.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_1.remove();
    }
    
    public long nextLong()
    {
      return ((Long)this.field_1.next()).longValue();
    }
    
    public long previousLong()
    {
      return ((Long)this.field_1.previous()).longValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractLongIterator
  {
    final Iterator<Long> field_1;
    
    public IteratorWrapper(Iterator<Long> local_i)
    {
      this.field_1 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public void remove()
    {
      this.field_1.remove();
    }
    
    public long nextLong()
    {
      return ((Long)this.field_1.next()).longValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractLongListIterator
  {
    private final long[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(long[] array, int offset, int length)
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
    
    public long nextLong()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public long previousLong()
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
    extends AbstractLongListIterator
  {
    private final long element;
    private int curr;
    
    public SingletonIterator(long element)
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
    
    public long nextLong()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public long previousLong()
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
    extends AbstractLongListIterator
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
    
    public long nextLong()
    {
      throw new NoSuchElementException();
    }
    
    public long previousLong()
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
      return LongIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return LongIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */