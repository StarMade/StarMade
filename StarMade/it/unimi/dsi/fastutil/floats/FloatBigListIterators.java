package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class FloatBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static FloatBigListIterator singleton(float element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static FloatBigListIterator unmodifiable(FloatBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static FloatBigListIterator asBigListIterator(FloatListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractFloatBigListIterator
  {
    protected final FloatListIterator field_52;
    
    protected BigListIteratorListIterator(FloatListIterator local_i)
    {
      this.field_52 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(float local_ok)
    {
      this.field_52.set(local_ok);
    }
    
    public void add(float local_ok)
    {
      this.field_52.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_52.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_52.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_52.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_52.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_52.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_52.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_52.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractFloatBigListIterator
  {
    protected final FloatBigListIterator field_52;
    
    public UnmodifiableBigListIterator(FloatBigListIterator local_i)
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
    
    public long nextIndex()
    {
      return this.field_52.nextIndex();
    }
    
    public long previousIndex()
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
  
  private static class SingletonBigListIterator
    extends AbstractFloatBigListIterator
  {
    private final float element;
    private int curr;
    
    public SingletonBigListIterator(float element)
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
    extends AbstractFloatBigListIterator
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
      return FloatBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return FloatBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */