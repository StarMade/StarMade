package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class CharBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static CharBigListIterator singleton(char element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static CharBigListIterator unmodifiable(CharBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static CharBigListIterator asBigListIterator(CharListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractCharBigListIterator
  {
    protected final CharListIterator field_67;
    
    protected BigListIteratorListIterator(CharListIterator local_i)
    {
      this.field_67 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(char local_ok)
    {
      this.field_67.set(local_ok);
    }
    
    public void add(char local_ok)
    {
      this.field_67.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_67.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_67.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_67.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_67.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_67.skip(intDisplacement(local_n));
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
    
    public char nextChar()
    {
      return this.field_67.nextChar();
    }
    
    public char previousChar()
    {
      return this.field_67.previousChar();
    }
    
    public long nextIndex()
    {
      return this.field_67.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_67.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractCharBigListIterator
  {
    protected final CharBigListIterator field_67;
    
    public UnmodifiableBigListIterator(CharBigListIterator local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
    
    public char nextChar()
    {
      return this.field_67.nextChar();
    }
    
    public char previousChar()
    {
      return this.field_67.previousChar();
    }
    
    public long nextIndex()
    {
      return this.field_67.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_67.previousIndex();
    }
    
    public Character next()
    {
      return (Character)this.field_67.next();
    }
    
    public Character previous()
    {
      return (Character)this.field_67.previous();
    }
  }
  
  private static class SingletonBigListIterator
    extends AbstractCharBigListIterator
  {
    private final char element;
    private int curr;
    
    public SingletonBigListIterator(char element)
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
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public char previousChar()
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
    extends AbstractCharBigListIterator
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
    
    public char nextChar()
    {
      throw new NoSuchElementException();
    }
    
    public char previousChar()
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
      return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */