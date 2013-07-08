package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static DoubleListIterator singleton(double element)
  {
    return new SingletonIterator(element);
  }
  
  public static DoubleListIterator wrap(double[] array, int offset, int length)
  {
    DoubleArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static DoubleListIterator wrap(double[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(DoubleIterator local_i, double[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextDouble();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(DoubleIterator local_i, double[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static double[] unwrap(DoubleIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    double[] array = new double[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = DoubleArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextDouble();
    }
    return DoubleArrays.trim(array, local_j);
  }
  
  public static double[] unwrap(DoubleIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(DoubleIterator local_i, DoubleCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextDouble());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(DoubleIterator local_i, DoubleCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextDouble());
    }
    return local_n;
  }
  
  public static int pour(DoubleIterator local_i, DoubleCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextDouble());
    }
    return max - local_j - 1;
  }
  
  public static int pour(DoubleIterator local_i, DoubleCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static DoubleList pour(DoubleIterator local_i, int max)
  {
    DoubleArrayList local_l = new DoubleArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static DoubleList pour(DoubleIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static DoubleIterator asDoubleIterator(Iterator local_i)
  {
    if ((local_i instanceof DoubleIterator)) {
      return (DoubleIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static DoubleListIterator asDoubleIterator(ListIterator local_i)
  {
    if ((local_i instanceof DoubleListIterator)) {
      return (DoubleListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static DoubleIterator concat(DoubleIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static DoubleIterator concat(DoubleIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static DoubleIterator unmodifiable(DoubleIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static DoubleBidirectionalIterator unmodifiable(DoubleBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static DoubleListIterator unmodifiable(DoubleListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static DoubleIterator wrap(ByteIterator iterator)
  {
    return new ByteIteratorWrapper(iterator);
  }
  
  public static DoubleIterator wrap(ShortIterator iterator)
  {
    return new ShortIteratorWrapper(iterator);
  }
  
  public static DoubleIterator wrap(IntIterator iterator)
  {
    return new IntIteratorWrapper(iterator);
  }
  
  public static DoubleIterator wrap(FloatIterator iterator)
  {
    return new FloatIteratorWrapper(iterator);
  }
  
  protected static class FloatIteratorWrapper
    implements DoubleIterator
  {
    final FloatIterator iterator;
    
    public FloatIteratorWrapper(FloatIterator iterator)
    {
      this.iterator = iterator;
    }
    
    public boolean hasNext()
    {
      return this.iterator.hasNext();
    }
    
    public Double next()
    {
      return Double.valueOf(this.iterator.nextFloat());
    }
    
    public double nextDouble()
    {
      return this.iterator.nextFloat();
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
  
  protected static class IntIteratorWrapper
    implements DoubleIterator
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
    
    public Double next()
    {
      return Double.valueOf(this.iterator.nextInt());
    }
    
    public double nextDouble()
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
    implements DoubleIterator
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
    
    public Double next()
    {
      return Double.valueOf(this.iterator.nextShort());
    }
    
    public double nextDouble()
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
    implements DoubleIterator
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
    
    public Double next()
    {
      return Double.valueOf(this.iterator.nextByte());
    }
    
    public double nextDouble()
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
    extends AbstractDoubleListIterator
  {
    protected final DoubleListIterator field_68;
    
    public UnmodifiableListIterator(DoubleListIterator local_i)
    {
      this.field_68 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_68.hasPrevious();
    }
    
    public double nextDouble()
    {
      return this.field_68.nextDouble();
    }
    
    public double previousDouble()
    {
      return this.field_68.previousDouble();
    }
    
    public int nextIndex()
    {
      return this.field_68.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_68.previousIndex();
    }
    
    public Double next()
    {
      return (Double)this.field_68.next();
    }
    
    public Double previous()
    {
      return (Double)this.field_68.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final DoubleBidirectionalIterator field_68;
    
    public UnmodifiableBidirectionalIterator(DoubleBidirectionalIterator local_i)
    {
      this.field_68 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_68.hasPrevious();
    }
    
    public double nextDouble()
    {
      return this.field_68.nextDouble();
    }
    
    public double previousDouble()
    {
      return this.field_68.previousDouble();
    }
    
    public Double next()
    {
      return (Double)this.field_68.next();
    }
    
    public Double previous()
    {
      return (Double)this.field_68.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractDoubleIterator
  {
    protected final DoubleIterator field_68;
    
    public UnmodifiableIterator(DoubleIterator local_i)
    {
      this.field_68 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public double nextDouble()
    {
      return this.field_68.nextDouble();
    }
    
    public Double next()
    {
      return (Double)this.field_68.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractDoubleIterator
  {
    final DoubleIterator[] field_418;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(DoubleIterator[] local_a, int offset, int length)
    {
      this.field_418 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_418[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      double next = this.field_418[(this.lastOffset = this.offset)].nextDouble();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_418[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_418[this.offset].skip(local_n - skipped);
        if (this.field_418[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractDoubleListIterator
  {
    final ListIterator<Double> field_68;
    
    public ListIteratorWrapper(ListIterator<Double> local_i)
    {
      this.field_68 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_68.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_68.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_68.previousIndex();
    }
    
    public void set(double local_k)
    {
      this.field_68.set(Double.valueOf(local_k));
    }
    
    public void add(double local_k)
    {
      this.field_68.add(Double.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_68.remove();
    }
    
    public double nextDouble()
    {
      return ((Double)this.field_68.next()).doubleValue();
    }
    
    public double previousDouble()
    {
      return ((Double)this.field_68.previous()).doubleValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractDoubleIterator
  {
    final Iterator<Double> field_68;
    
    public IteratorWrapper(Iterator<Double> local_i)
    {
      this.field_68 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public void remove()
    {
      this.field_68.remove();
    }
    
    public double nextDouble()
    {
      return ((Double)this.field_68.next()).doubleValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractDoubleListIterator
  {
    private final double[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(double[] array, int offset, int length)
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
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public double previousDouble()
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
    extends AbstractDoubleListIterator
  {
    private final double element;
    private int curr;
    
    public SingletonIterator(double element)
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
    
    public double nextDouble()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public double previousDouble()
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
    extends AbstractDoubleListIterator
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
    
    public double nextDouble()
    {
      throw new NoSuchElementException();
    }
    
    public double previousDouble()
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
      return DoubleIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return DoubleIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */