package org.hsqldb.lib;

import java.util.NoSuchElementException;

public class WrapperIterator
  implements Iterator
{
  private static final Object[] emptyelements = new Object[0];
  private Object[] elements;
  private int i;
  private boolean chained;
  private Iterator it1;
  private Iterator it2;
  private boolean notNull;
  
  public WrapperIterator()
  {
    this.elements = emptyelements;
  }
  
  public WrapperIterator(Object[] paramArrayOfObject)
  {
    this.elements = paramArrayOfObject;
  }
  
  public WrapperIterator(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    this.elements = paramArrayOfObject;
    this.notNull = paramBoolean;
  }
  
  public WrapperIterator(Object paramObject)
  {
    this.elements = new Object[] { paramObject };
  }
  
  public WrapperIterator(Iterator paramIterator1, Iterator paramIterator2)
  {
    this.it1 = paramIterator1;
    this.it2 = paramIterator2;
    this.chained = true;
  }
  
  public boolean hasNext()
  {
    if (this.chained)
    {
      if (this.it1 == null)
      {
        if (this.it2 == null) {
          return false;
        }
        if (this.it2.hasNext()) {
          return true;
        }
        this.it2 = null;
        return false;
      }
      if (this.it1.hasNext()) {
        return true;
      }
      this.it1 = null;
      return hasNext();
    }
    if (this.elements == null) {
      return false;
    }
    while ((this.notNull) && (this.i < this.elements.length) && (this.elements[this.i] == null)) {
      this.i += 1;
    }
    if (this.i < this.elements.length) {
      return true;
    }
    this.elements = null;
    return false;
  }
  
  public Object next()
  {
    if (this.chained) {
      if (this.it1 == null)
      {
        if (this.it2 == null) {
          throw new NoSuchElementException();
        }
        if (this.it2.hasNext()) {
          return this.it2.next();
        }
        this.it2 = null;
        next();
      }
      else
      {
        if (this.it1.hasNext()) {
          return this.it1.next();
        }
        this.it1 = null;
        next();
      }
    }
    if (hasNext()) {
      return this.elements[(this.i++)];
    }
    throw new NoSuchElementException();
  }
  
  public int nextInt()
  {
    throw new NoSuchElementException();
  }
  
  public long nextLong()
  {
    throw new NoSuchElementException();
  }
  
  public void remove()
  {
    throw new NoSuchElementException();
  }
  
  public void setValue(Object paramObject)
  {
    throw new NoSuchElementException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.WrapperIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */