package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IntIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static IntListIterator singleton(int element)
  {
    return new SingletonIterator(element);
  }
  
  public static IntListIterator wrap(int[] array, int offset, int length)
  {
    IntArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static IntListIterator wrap(int[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(IntIterator local_i, int[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextInt();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(IntIterator local_i, int[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static int[] unwrap(IntIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int[] array = new int[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = IntArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextInt();
    }
    return IntArrays.trim(array, local_j);
  }
  
  public static int[] unwrap(IntIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(IntIterator local_i, IntCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextInt());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(IntIterator local_i, IntCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextInt());
    }
    return local_n;
  }
  
  public static int pour(IntIterator local_i, IntCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextInt());
    }
    return max - local_j - 1;
  }
  
  public static int pour(IntIterator local_i, IntCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static IntList pour(IntIterator local_i, int max)
  {
    IntArrayList local_l = new IntArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static IntList pour(IntIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static IntIterator asIntIterator(Iterator local_i)
  {
    if ((local_i instanceof IntIterator)) {
      return (IntIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static IntListIterator asIntIterator(ListIterator local_i)
  {
    if ((local_i instanceof IntListIterator)) {
      return (IntListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static IntListIterator fromTo(int from, int local_to)
  {
    return new IntervalIterator(from, local_to);
  }
  
  public static IntIterator concat(IntIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static IntIterator concat(IntIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static IntIterator unmodifiable(IntIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static IntBidirectionalIterator unmodifiable(IntBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static IntListIterator unmodifiable(IntListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static IntIterator wrap(ByteIterator iterator)
  {
    return new ByteIteratorWrapper(iterator);
  }
  
  public static IntIterator wrap(ShortIterator iterator)
  {
    return new ShortIteratorWrapper(iterator);
  }
  
  protected static class ShortIteratorWrapper
    implements IntIterator
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
    
    public Integer next()
    {
      return Integer.valueOf(this.iterator.nextShort());
    }
    
    public int nextInt()
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
    implements IntIterator
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
    
    public Integer next()
    {
      return Integer.valueOf(this.iterator.nextByte());
    }
    
    public int nextInt()
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
    extends AbstractIntListIterator
  {
    protected final IntListIterator field_70;
    
    public UnmodifiableListIterator(IntListIterator local_i)
    {
      this.field_70 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_70.hasPrevious();
    }
    
    public int nextInt()
    {
      return this.field_70.nextInt();
    }
    
    public int previousInt()
    {
      return this.field_70.previousInt();
    }
    
    public int nextIndex()
    {
      return this.field_70.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_70.previousIndex();
    }
    
    public Integer next()
    {
      return (Integer)this.field_70.next();
    }
    
    public Integer previous()
    {
      return (Integer)this.field_70.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final IntBidirectionalIterator field_70;
    
    public UnmodifiableBidirectionalIterator(IntBidirectionalIterator local_i)
    {
      this.field_70 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_70.hasPrevious();
    }
    
    public int nextInt()
    {
      return this.field_70.nextInt();
    }
    
    public int previousInt()
    {
      return this.field_70.previousInt();
    }
    
    public Integer next()
    {
      return (Integer)this.field_70.next();
    }
    
    public Integer previous()
    {
      return (Integer)this.field_70.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractIntIterator
  {
    protected final IntIterator field_70;
    
    public UnmodifiableIterator(IntIterator local_i)
    {
      this.field_70 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public int nextInt()
    {
      return this.field_70.nextInt();
    }
    
    public Integer next()
    {
      return (Integer)this.field_70.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractIntIterator
  {
    final IntIterator[] field_421;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(IntIterator[] local_a, int offset, int length)
    {
      this.field_421 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_421[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      int next = this.field_421[(this.lastOffset = this.offset)].nextInt();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_421[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_421[this.offset].skip(local_n - skipped);
        if (this.field_421[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class IntervalIterator
    extends AbstractIntListIterator
  {
    private final int from;
    private final int field_383;
    int curr;
    
    public IntervalIterator(int from, int local_to)
    {
      this.from = (this.curr = from);
      this.field_383 = local_to;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.field_383;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > this.from;
    }
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.curr++;
    }
    
    public int previousInt()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return --this.curr;
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
      if (this.curr + local_n <= this.field_383)
      {
        this.curr += local_n;
        return local_n;
      }
      local_n = this.field_383 - this.curr;
      this.curr = this.field_383;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (this.curr - local_n >= this.from)
      {
        this.curr -= local_n;
        return local_n;
      }
      local_n = this.curr - this.from;
      this.curr = this.from;
      return local_n;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractIntListIterator
  {
    final ListIterator<Integer> field_70;
    
    public ListIteratorWrapper(ListIterator<Integer> local_i)
    {
      this.field_70 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_70.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_70.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_70.previousIndex();
    }
    
    public void set(int local_k)
    {
      this.field_70.set(Integer.valueOf(local_k));
    }
    
    public void add(int local_k)
    {
      this.field_70.add(Integer.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_70.remove();
    }
    
    public int nextInt()
    {
      return ((Integer)this.field_70.next()).intValue();
    }
    
    public int previousInt()
    {
      return ((Integer)this.field_70.previous()).intValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractIntIterator
  {
    final Iterator<Integer> field_70;
    
    public IteratorWrapper(Iterator<Integer> local_i)
    {
      this.field_70 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public void remove()
    {
      this.field_70.remove();
    }
    
    public int nextInt()
    {
      return ((Integer)this.field_70.next()).intValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractIntListIterator
  {
    private final int[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(int[] array, int offset, int length)
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
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public int previousInt()
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
    extends AbstractIntListIterator
  {
    private final int element;
    private int curr;
    
    public SingletonIterator(int element)
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
    
    public int nextInt()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public int previousInt()
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
    extends AbstractIntListIterator
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
    
    public int nextInt()
    {
      throw new NoSuchElementException();
    }
    
    public int previousInt()
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
      return IntIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return IntIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */