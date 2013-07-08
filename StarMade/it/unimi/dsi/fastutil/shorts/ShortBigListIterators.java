package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ShortBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static ShortBigListIterator singleton(short element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static ShortBigListIterator unmodifiable(ShortBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static ShortBigListIterator asBigListIterator(ShortListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractShortBigListIterator
  {
    protected final ShortListIterator field_53;
    
    protected BigListIteratorListIterator(ShortListIterator local_i)
    {
      this.field_53 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(short local_ok)
    {
      this.field_53.set(local_ok);
    }
    
    public void add(short local_ok)
    {
      this.field_53.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_53.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_53.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_53.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_53.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_53.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_53.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_53.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractShortBigListIterator
  {
    protected final ShortBigListIterator field_53;
    
    public UnmodifiableBigListIterator(ShortBigListIterator local_i)
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
    
    public long nextIndex()
    {
      return this.field_53.nextIndex();
    }
    
    public long previousIndex()
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
  
  private static class SingletonBigListIterator
    extends AbstractShortBigListIterator
  {
    private final short element;
    private int curr;
    
    public SingletonBigListIterator(short element)
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
    extends AbstractShortBigListIterator
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
      return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */