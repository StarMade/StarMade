package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class LongBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static LongBigListIterator singleton(long element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static LongBigListIterator unmodifiable(LongBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static LongBigListIterator asBigListIterator(LongListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractLongBigListIterator
  {
    protected final LongListIterator field_1;
    
    protected BigListIteratorListIterator(LongListIterator local_i)
    {
      this.field_1 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(long local_ok)
    {
      this.field_1.set(local_ok);
    }
    
    public void add(long local_ok)
    {
      this.field_1.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_1.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_1.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_1.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_1.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_1.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_1.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_1.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractLongBigListIterator
  {
    protected final LongBigListIterator field_1;
    
    public UnmodifiableBigListIterator(LongBigListIterator local_i)
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
    
    public long nextIndex()
    {
      return this.field_1.nextIndex();
    }
    
    public long previousIndex()
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
  
  private static class SingletonBigListIterator
    extends AbstractLongBigListIterator
  {
    private final long element;
    private int curr;
    
    public SingletonBigListIterator(long element)
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
    
    public long nextIndex()
    {
      return this.curr;
    }
    
    public long previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  public static class EmptyBigListIterator
    extends AbstractLongBigListIterator
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
    
    public long nextIndex()
    {
      return 0L;
    }
    
    public long previousIndex()
    {
      return -1L;
    }
    
    public long skip(long local_n)
    {
      return 0L;
    }
    
    public long back(long local_n)
    {
      return 0L;
    }
    
    public Object clone()
    {
      return LongBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return LongBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */