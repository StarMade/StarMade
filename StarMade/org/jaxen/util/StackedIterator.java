package org.jaxen.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;
import org.jaxen.Navigator;

/**
 * @deprecated
 */
public abstract class StackedIterator
  implements Iterator
{
  private LinkedList iteratorStack = new LinkedList();
  private Navigator navigator;
  private Set created = new HashSet();
  
  public StackedIterator(Object contextNode, Navigator navigator)
  {
    init(contextNode, navigator);
  }
  
  protected StackedIterator() {}
  
  protected void init(Object contextNode, Navigator navigator)
  {
    this.navigator = navigator;
  }
  
  protected Iterator internalCreateIterator(Object contextNode)
  {
    if (this.created.contains(contextNode)) {
      return null;
    }
    this.created.add(contextNode);
    return createIterator(contextNode);
  }
  
  public boolean hasNext()
  {
    Iterator curIter = currentIterator();
    if (curIter == null) {
      return false;
    }
    return curIter.hasNext();
  }
  
  public Object next()
    throws NoSuchElementException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Iterator curIter = currentIterator();
    Object object = curIter.next();
    pushIterator(internalCreateIterator(object));
    return object;
  }
  
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
  
  protected abstract Iterator createIterator(Object paramObject);
  
  protected void pushIterator(Iterator iter)
  {
    if (iter != null) {
      this.iteratorStack.addFirst(iter);
    }
  }
  
  private Iterator currentIterator()
  {
    while (this.iteratorStack.size() > 0)
    {
      Iterator curIter = (Iterator)this.iteratorStack.getFirst();
      if (curIter.hasNext()) {
        return curIter;
      }
      this.iteratorStack.removeFirst();
    }
    return null;
  }
  
  protected Navigator getNavigator()
  {
    return this.navigator;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.StackedIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */