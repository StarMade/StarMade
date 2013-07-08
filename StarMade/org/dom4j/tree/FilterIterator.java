package org.dom4j.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @deprecated
 */
public abstract class FilterIterator
  implements Iterator
{
  protected Iterator proxy;
  private Object next;
  private boolean first = true;
  
  public FilterIterator(Iterator proxy)
  {
    this.proxy = proxy;
  }
  
  public boolean hasNext()
  {
    if (this.first)
    {
      this.next = findNext();
      this.first = false;
    }
    return this.next != null;
  }
  
  public Object next()
    throws NoSuchElementException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Object answer = this.next;
    this.next = findNext();
    return answer;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  protected abstract boolean matches(Object paramObject);
  
  protected Object findNext()
  {
    if (this.proxy != null)
    {
      while (this.proxy.hasNext())
      {
        Object nextObject = this.proxy.next();
        if ((nextObject != null) && (matches(nextObject))) {
          return nextObject;
        }
      }
      this.proxy = null;
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.FilterIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */