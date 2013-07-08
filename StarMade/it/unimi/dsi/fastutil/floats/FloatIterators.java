package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class FloatIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static FloatListIterator singleton(float element)
  {
    return new SingletonIterator(element);
  }
  
  public static FloatListIterator wrap(float[] array, int offset, int length)
  {
    FloatArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static FloatListIterator wrap(float[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(FloatIterator local_i, float[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextFloat();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(FloatIterator local_i, float[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static float[] unwrap(FloatIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    float[] array = new float[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = FloatArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextFloat();
    }
    return FloatArrays.trim(array, local_j);
  }
  
  public static float[] unwrap(FloatIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(FloatIterator local_i, FloatCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextFloat());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(FloatIterator local_i, FloatCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextFloat());
    }
    return local_n;
  }
  
  public static int pour(FloatIterator local_i, FloatCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextFloat());
    }
    return max - local_j - 1;
  }
  
  public static int pour(FloatIterator local_i, FloatCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static FloatList pour(FloatIterator local_i, int max)
  {
    FloatArrayList local_l = new FloatArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static FloatList pour(FloatIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static FloatIterator asFloatIterator(Iterator local_i)
  {
    if ((local_i instanceof FloatIterator)) {
      return (FloatIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static FloatListIterator asFloatIterator(ListIterator local_i)
  {
    if ((local_i instanceof FloatListIterator)) {
      return (FloatListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static FloatIterator concat(FloatIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static FloatIterator concat(FloatIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static FloatIterator unmodifiable(FloatIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static FloatBidirectionalIterator unmodifiable(FloatBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static FloatListIterator unmodifiable(FloatListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static FloatIterator wrap(ByteIterator iterator)
  {
    return new ByteIteratorWrapper(iterator);
  }
  
  public static FloatIterator wrap(ShortIterator iterator)
  {
    return new ShortIteratorWrapper(iterator);
  }
  
  protected static class ShortIteratorWrapper
    implements FloatIterator
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
    
    public Float next()
    {
      return Float.valueOf(this.iterator.nextShort());
    }
    
    public float nextFloat()
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
    implements FloatIterator
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
    
    public Float next()
    {
      return Float.valueOf(this.iterator.nextByte());
    }
    
    public float nextFloat()
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
    extends AbstractFloatListIterator
  {
    protected final FloatListIterator field_52;
    
    public UnmodifiableListIterator(FloatListIterator local_i)
    {
      this.field_52 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
    
    public float nextFloat()
    {
      return this.field_52.nextFloat();
    }
    
    public float previousFloat()
    {
      return this.field_52.previousFloat();
    }
    
    public int nextIndex()
    {
      return this.field_52.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_52.previousIndex();
    }
    
    public Float next()
    {
      return (Float)this.field_52.next();
    }
    
    public Float previous()
    {
      return (Float)this.field_52.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final FloatBidirectionalIterator field_52;
    
    public UnmodifiableBidirectionalIterator(FloatBidirectionalIterator local_i)
    {
      this.field_52 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
    
    public float nextFloat()
    {
      return this.field_52.nextFloat();
    }
    
    public float previousFloat()
    {
      return this.field_52.previousFloat();
    }
    
    public Float next()
    {
      return (Float)this.field_52.next();
    }
    
    public Float previous()
    {
      return (Float)this.field_52.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractFloatIterator
  {
    protected final FloatIterator field_52;
    
    public UnmodifiableIterator(FloatIterator local_i)
    {
      this.field_52 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public float nextFloat()
    {
      return this.field_52.nextFloat();
    }
    
    public Float next()
    {
      return (Float)this.field_52.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractFloatIterator
  {
    final FloatIterator[] field_359;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(FloatIterator[] local_a, int offset, int length)
    {
      this.field_359 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_359[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      float next = this.field_359[(this.lastOffset = this.offset)].nextFloat();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_359[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_359[this.offset].skip(local_n - skipped);
        if (this.field_359[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractFloatListIterator
  {
    final ListIterator<Float> field_52;
    
    public ListIteratorWrapper(ListIterator<Float> local_i)
    {
      this.field_52 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_52.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_52.previousIndex();
    }
    
    public void set(float local_k)
    {
      this.field_52.set(Float.valueOf(local_k));
    }
    
    public void add(float local_k)
    {
      this.field_52.add(Float.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_52.remove();
    }
    
    public float nextFloat()
    {
      return ((Float)this.field_52.next()).floatValue();
    }
    
    public float previousFloat()
    {
      return ((Float)this.field_52.previous()).floatValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractFloatIterator
  {
    final Iterator<Float> field_52;
    
    public IteratorWrapper(Iterator<Float> local_i)
    {
      this.field_52 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public void remove()
    {
      this.field_52.remove();
    }
    
    public float nextFloat()
    {
      return ((Float)this.field_52.next()).floatValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractFloatListIterator
  {
    private final float[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(float[] array, int offset, int length)
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
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public float previousFloat()
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
    extends AbstractFloatListIterator
  {
    private final float element;
    private int curr;
    
    public SingletonIterator(float element)
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
    
    public float nextFloat()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public float previousFloat()
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
    extends AbstractFloatListIterator
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
    
    public float nextFloat()
    {
      throw new NoSuchElementException();
    }
    
    public float previousFloat()
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
      return FloatIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return FloatIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */