package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class IntBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static IntBigListIterator singleton(int element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static IntBigListIterator unmodifiable(IntBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static IntBigListIterator asBigListIterator(IntListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractIntBigListIterator
  {
    protected final IntListIterator field_70;
    
    protected BigListIteratorListIterator(IntListIterator local_i)
    {
      this.field_70 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(int local_ok)
    {
      this.field_70.set(local_ok);
    }
    
    public void add(int local_ok)
    {
      this.field_70.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_70.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_70.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_70.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_70.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_70.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_70.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_70.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractIntBigListIterator
  {
    protected final IntBigListIterator field_70;
    
    public UnmodifiableBigListIterator(IntBigListIterator local_i)
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
    
    public long nextIndex()
    {
      return this.field_70.nextIndex();
    }
    
    public long previousIndex()
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
  
  private static class SingletonBigListIterator
    extends AbstractIntBigListIterator
  {
    private final int element;
    private int curr;
    
    public SingletonBigListIterator(int element)
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
    extends AbstractIntBigListIterator
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
      return IntBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return IntBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */