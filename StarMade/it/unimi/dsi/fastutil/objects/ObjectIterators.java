package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ObjectIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static <K> ObjectListIterator<K> singleton(K element)
  {
    return new SingletonIterator(element);
  }
  
  public static <K> ObjectListIterator<K> wrap(K[] array, int offset, int length)
  {
    ObjectArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static <K> ObjectListIterator<K> wrap(K[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static <K> int unwrap(Iterator<? extends K> local_i, K[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.next();
    }
    return max - local_j - 1;
  }
  
  public static <K> int unwrap(Iterator<? extends K> local_i, K[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static <K> K[] unwrap(Iterator<? extends K> local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    K[] array = (Object[])new Object[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = ObjectArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.next();
    }
    return ObjectArrays.trim(array, local_j);
  }
  
  public static <K> K[] unwrap(Iterator<? extends K> local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static <K> int unwrap(Iterator<K> local_i, ObjectCollection<? super K> local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.next());
    }
    return max - local_j - 1;
  }
  
  public static <K> long unwrap(Iterator<K> local_i, ObjectCollection<? super K> local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.next());
    }
    return local_n;
  }
  
  public static <K> int pour(Iterator<K> local_i, ObjectCollection<? super K> local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.next());
    }
    return max - local_j - 1;
  }
  
  public static <K> int pour(Iterator<K> local_i, ObjectCollection<? super K> local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static <K> ObjectList<K> pour(Iterator<K> local_i, int max)
  {
    ObjectArrayList<K> local_l = new ObjectArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static <K> ObjectList<K> pour(Iterator<K> local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static <K> ObjectIterator<K> asObjectIterator(Iterator<K> local_i)
  {
    if ((local_i instanceof ObjectIterator)) {
      return (ObjectIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static <K> ObjectListIterator<K> asObjectIterator(ListIterator<K> local_i)
  {
    if ((local_i instanceof ObjectListIterator)) {
      return (ObjectListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static <K> ObjectIterator<K> unmodifiable(ObjectIterator<K> local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static <K> ObjectBidirectionalIterator<K> unmodifiable(ObjectBidirectionalIterator<K> local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static <K> ObjectListIterator<K> unmodifiable(ObjectListIterator<K> local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static class UnmodifiableListIterator<K>
    extends AbstractObjectListIterator<K>
  {
    protected final ObjectListIterator<K> field_3;
    
    public UnmodifiableListIterator(ObjectListIterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
    
    public K next()
    {
      return this.field_3.next();
    }
    
    public K previous()
    {
      return this.field_3.previous();
    }
    
    public int nextIndex()
    {
      return this.field_3.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_3.previousIndex();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<K> field_3;
    
    public UnmodifiableBidirectionalIterator(ObjectBidirectionalIterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
    
    public K next()
    {
      return this.field_3.next();
    }
    
    public K previous()
    {
      return this.field_3.previous();
    }
  }
  
  public static class UnmodifiableIterator<K>
    extends AbstractObjectIterator<K>
  {
    protected final ObjectIterator<K> field_3;
    
    public UnmodifiableIterator(ObjectIterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public K next()
    {
      return this.field_3.next();
    }
  }
  
  private static class IteratorConcatenator<K>
    extends AbstractObjectIterator<K>
  {
    final ObjectIterator<? extends K>[] field_295;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(ObjectIterator<? extends K>[] local_a, int offset, int length)
    {
      this.field_295 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_295[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      K next = this.field_295[(this.lastOffset = this.offset)].next();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_295[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_295[this.offset].skip(local_n - skipped);
        if (this.field_295[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class ListIteratorWrapper<K>
    extends AbstractObjectListIterator<K>
  {
    final ListIterator<K> field_3;
    
    public ListIteratorWrapper(ListIterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_3.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_3.previousIndex();
    }
    
    public void set(K local_k)
    {
      this.field_3.set(local_k);
    }
    
    public void add(K local_k)
    {
      this.field_3.add(local_k);
    }
    
    public void remove()
    {
      this.field_3.remove();
    }
    
    public K next()
    {
      return this.field_3.next();
    }
    
    public K previous()
    {
      return this.field_3.previous();
    }
  }
  
  private static class IteratorWrapper<K>
    extends AbstractObjectIterator<K>
  {
    final Iterator<K> field_3;
    
    public IteratorWrapper(Iterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public void remove()
    {
      this.field_3.remove();
    }
    
    public K next()
    {
      return this.field_3.next();
    }
  }
  
  private static class ArrayIterator<K>
    extends AbstractObjectListIterator<K>
  {
    private final K[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(K[] array, int offset, int length)
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
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public K previous()
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
  
  private static class SingletonIterator<K>
    extends AbstractObjectListIterator<K>
  {
    private final K element;
    private int curr;
    
    public SingletonIterator(K element)
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
    
    public K next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public K previous()
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
  
  public static class EmptyIterator<K>
    extends AbstractObjectListIterator<K>
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
    
    public K next()
    {
      throw new NoSuchElementException();
    }
    
    public K previous()
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
      return ObjectIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ObjectIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */