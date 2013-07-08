package org.hsqldb.lib;

import java.util.NoSuchElementException;

public abstract interface Iterator
{
  public abstract boolean hasNext();
  
  public abstract Object next()
    throws NoSuchElementException;
  
  public abstract int nextInt()
    throws NoSuchElementException;
  
  public abstract long nextLong()
    throws NoSuchElementException;
  
  public abstract void remove()
    throws NoSuchElementException;
  
  public abstract void setValue(Object paramObject);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.Iterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */