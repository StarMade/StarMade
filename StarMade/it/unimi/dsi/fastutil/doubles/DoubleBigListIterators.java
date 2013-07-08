package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class DoubleBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static DoubleBigListIterator singleton(double element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static DoubleBigListIterator unmodifiable(DoubleBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static DoubleBigListIterator asBigListIterator(DoubleListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractDoubleBigListIterator
  {
    protected final DoubleListIterator field_68;
    
    protected BigListIteratorListIterator(DoubleListIterator local_i)
    {
      this.field_68 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(double local_ok)
    {
      this.field_68.set(local_ok);
    }
    
    public void add(double local_ok)
    {
      this.field_68.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_68.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_68.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_68.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_68.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_68.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_68.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_68.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractDoubleBigListIterator
  {
    protected final DoubleBigListIterator field_68;
    
    public UnmodifiableBigListIterator(DoubleBigListIterator local_i)
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
    
    public long nextIndex()
    {
      return this.field_68.nextIndex();
    }
    
    public long previousIndex()
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
  
  private static class SingletonBigListIterator
    extends AbstractDoubleBigListIterator
  {
    private final double element;
    private int curr;
    
    public SingletonBigListIterator(double element)
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
    extends AbstractDoubleBigListIterator
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
      return DoubleBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return DoubleBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */