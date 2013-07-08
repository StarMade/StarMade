package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ObjectBigListIterators
{
  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
  
  public static <K> ObjectBigListIterator<K> singleton(K element)
  {
    return new SingletonBigListIterator(element);
  }
  
  public static <K> ObjectBigListIterator<K> unmodifiable(ObjectBigListIterator<K> local_i)
  {
    return new UnmodifiableBigListIterator(local_i);
  }
  
  public static <K> ObjectBigListIterator<K> asBigListIterator(ObjectListIterator<K> local_i)
  {
    return new BigListIteratorListIterator(local_i);
  }
  
  public static class BigListIteratorListIterator<K>
    extends AbstractObjectBigListIterator<K>
  {
    protected final ObjectListIterator<K> field_3;
    
    protected BigListIteratorListIterator(ObjectListIterator<K> local_i)
    {
      this.field_3 = local_i;
    }
    
    private int intDisplacement(long local_n)
    {
      if ((local_n < -2147483648L) || (local_n > 2147483647L)) {
        throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
      }
      return (int)local_n;
    }
    
    public void set(K local_ok)
    {
      this.field_3.set(local_ok);
    }
    
    public void add(K local_ok)
    {
      this.field_3.add(local_ok);
    }
    
    public int back(int local_n)
    {
      return this.field_3.back(local_n);
    }
    
    public long back(long local_n)
    {
      return this.field_3.back(intDisplacement(local_n));
    }
    
    public void remove()
    {
      this.field_3.remove();
    }
    
    public int skip(int local_n)
    {
      return this.field_3.skip(local_n);
    }
    
    public long skip(long local_n)
    {
      return this.field_3.skip(intDisplacement(local_n));
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
    
    public long nextIndex()
    {
      return this.field_3.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_3.previousIndex();
    }
  }
  
  public static class UnmodifiableBigListIterator<K>
    extends AbstractObjectBigListIterator<K>
  {
    protected final ObjectBigListIterator<K> field_3;
    
    public UnmodifiableBigListIterator(ObjectBigListIterator<K> local_i)
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
    
    public long nextIndex()
    {
      return this.field_3.nextIndex();
    }
    
    public long previousIndex()
    {
      return this.field_3.previousIndex();
    }
  }
  
  private static class SingletonBigListIterator<K>
    extends AbstractObjectBigListIterator<K>
  {
    private final K element;
    private int curr;
    
    public SingletonBigListIterator(K element)
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
    
    public long nextIndex()
    {
      return this.curr;
    }
    
    public long previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  public static class EmptyBigListIterator<K>
    extends AbstractObjectBigListIterator<K>
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
      return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
    
    private Object readResolve()
    {
      return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */