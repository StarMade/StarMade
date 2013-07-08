package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ByteBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static ByteBigListIterator singleton(byte element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static ByteBigListIterator unmodifiable(ByteBigListIterator local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static ByteBigListIterator asBigListIterator(ByteListIterator local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator
    extends AbstractByteBigListIterator
  {
    protected final ByteListIterator field_58;
    
    protected BigListIteratorListIterator(ByteListIterator local_i)
    {
      this.field_58 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(byte local_ok)
    {
      this.field_58.set(local_ok);
    }
    
    public void add(byte local_ok)
    {
      this.field_58.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_58.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_58.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_58.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_58.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_58.skip(intDisplacement(local_n));
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
    
    public byte nextByte()
    {
      return this.field_58.nextByte();
    }
    
    public byte previousByte()
    {
      return this.field_58.previousByte();
    }
    
    public long nextIndex()
    {
      return this.field_58.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_58.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator
    extends AbstractByteBigListIterator
  {
    protected final ByteBigListIterator field_58;
    
    public UnmodifiableBigListIterator(ByteBigListIterator local_i)
    {
      this.field_58 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
    
    public byte nextByte()
    {
      return this.field_58.nextByte();
    }
    
    public byte previousByte()
    {
      return this.field_58.previousByte();
    }
    
    public long nextIndex()
    {
      return this.field_58.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_58.previousIndex();
    }
    
    public Byte next()
    {
      return (Byte)this.field_58.next();
    }
    
    public Byte previous()
    {
      return (Byte)this.field_58.previous();
    }
  }
  
  private static class SingletonBigListIterator
    extends AbstractByteBigListIterator
  {
    private final byte element;
    private int curr;
    
    public SingletonBigListIterator(byte element)
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
    
    public byte nextByte()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public byte previousByte()
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
    extends AbstractByteBigListIterator
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
    
    public byte nextByte()
    {
      throw new NoSuchElementException();
    }
    
    public byte previousByte()
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
      return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */