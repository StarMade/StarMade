package it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BooleanIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static BooleanListIterator singleton(boolean element)
  {
    return new SingletonIterator(element);
  }
  
  public static BooleanListIterator wrap(boolean[] array, int offset, int length)
  {
    BooleanArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static BooleanListIterator wrap(boolean[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(BooleanIterator local_i, boolean[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextBoolean();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(BooleanIterator local_i, boolean[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static boolean[] unwrap(BooleanIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    boolean[] array = new boolean[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = BooleanArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextBoolean();
    }
    return BooleanArrays.trim(array, local_j);
  }
  
  public static boolean[] unwrap(BooleanIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(BooleanIterator local_i, BooleanCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextBoolean());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(BooleanIterator local_i, BooleanCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextBoolean());
    }
    return local_n;
  }
  
  public static int pour(BooleanIterator local_i, BooleanCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextBoolean());
    }
    return max - local_j - 1;
  }
  
  public static int pour(BooleanIterator local_i, BooleanCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static BooleanList pour(BooleanIterator local_i, int max)
  {
    BooleanArrayList local_l = new BooleanArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static BooleanList pour(BooleanIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static BooleanIterator asBooleanIterator(Iterator local_i)
  {
    if ((local_i instanceof BooleanIterator)) {
      return (BooleanIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static BooleanListIterator asBooleanIterator(ListIterator local_i)
  {
    if ((local_i instanceof BooleanListIterator)) {
      return (BooleanListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static BooleanIterator concat(BooleanIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static BooleanIterator concat(BooleanIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static BooleanIterator unmodifiable(BooleanIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static BooleanBidirectionalIterator unmodifiable(BooleanBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static BooleanListIterator unmodifiable(BooleanListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static class UnmodifiableListIterator
    extends AbstractBooleanListIterator
  {
    protected final BooleanListIterator field_60;
    
    public UnmodifiableListIterator(BooleanListIterator local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_60.hasPrevious();
    }
    
    public boolean nextBoolean()
    {
      return this.field_60.nextBoolean();
    }
    
    public boolean previousBoolean()
    {
      return this.field_60.previousBoolean();
    }
    
    public int nextIndex()
    {
      return this.field_60.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_60.previousIndex();
    }
    
    public Boolean next()
    {
      return (Boolean)this.field_60.next();
    }
    
    public Boolean previous()
    {
      return (Boolean)this.field_60.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractBooleanBidirectionalIterator
  {
    protected final BooleanBidirectionalIterator field_60;
    
    public UnmodifiableBidirectionalIterator(BooleanBidirectionalIterator local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_60.hasPrevious();
    }
    
    public boolean nextBoolean()
    {
      return this.field_60.nextBoolean();
    }
    
    public boolean previousBoolean()
    {
      return this.field_60.previousBoolean();
    }
    
    public Boolean next()
    {
      return (Boolean)this.field_60.next();
    }
    
    public Boolean previous()
    {
      return (Boolean)this.field_60.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractBooleanIterator
  {
    protected final BooleanIterator field_60;
    
    public UnmodifiableIterator(BooleanIterator local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
    
    public boolean nextBoolean()
    {
      return this.field_60.nextBoolean();
    }
    
    public Boolean next()
    {
      return (Boolean)this.field_60.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractBooleanIterator
  {
    final BooleanIterator[] field_373;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(BooleanIterator[] local_a, int offset, int length)
    {
      this.field_373 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_373[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public boolean nextBoolean()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      boolean next = this.field_373[(this.lastOffset = this.offset)].nextBoolean();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_373[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_373[this.offset].skip(local_n - skipped);
        if (this.field_373[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractBooleanListIterator
  {
    final ListIterator<Boolean> field_60;
    
    public ListIteratorWrapper(ListIterator<Boolean> local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_60.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_60.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_60.previousIndex();
    }
    
    public void set(boolean local_k)
    {
      this.field_60.set(Boolean.valueOf(local_k));
    }
    
    public void add(boolean local_k)
    {
      this.field_60.add(Boolean.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_60.remove();
    }
    
    public boolean nextBoolean()
    {
      return ((Boolean)this.field_60.next()).booleanValue();
    }
    
    public boolean previousBoolean()
    {
      return ((Boolean)this.field_60.previous()).booleanValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractBooleanIterator
  {
    final Iterator<Boolean> field_60;
    
    public IteratorWrapper(Iterator<Boolean> local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
    
    public void remove()
    {
      this.field_60.remove();
    }
    
    public boolean nextBoolean()
    {
      return ((Boolean)this.field_60.next()).booleanValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractBooleanListIterator
  {
    private final boolean[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(boolean[] array, int offset, int length)
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
    
    public boolean nextBoolean()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public boolean previousBoolean()
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
    extends AbstractBooleanListIterator
  {
    private final boolean element;
    private int curr;
    
    public SingletonIterator(boolean element)
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
    
    public boolean nextBoolean()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public boolean previousBoolean()
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
    extends AbstractBooleanListIterator
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
    
    public boolean nextBoolean()
    {
      throw new NoSuchElementException();
    }
    
    public boolean previousBoolean()
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
      return BooleanIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return BooleanIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */