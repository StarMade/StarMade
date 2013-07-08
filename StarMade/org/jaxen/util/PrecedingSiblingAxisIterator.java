package org.jaxen.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class PrecedingSiblingAxisIterator
  implements Iterator
{
  private Object contextNode;
  private Navigator navigator;
  private Iterator siblingIter;
  private Object nextObj;
  
  public PrecedingSiblingAxisIterator(Object contextNode, Navigator navigator)
    throws UnsupportedAxisException
  {
    this.contextNode = contextNode;
    this.navigator = navigator;
    init();
    if (this.siblingIter.hasNext()) {
      this.nextObj = this.siblingIter.next();
    }
  }
  
  private void init()
    throws UnsupportedAxisException
  {
    Object parent = this.navigator.getParentNode(this.contextNode);
    if (parent != null)
    {
      Iterator childIter = this.navigator.getChildAxisIterator(parent);
      LinkedList siblings = new LinkedList();
      while (childIter.hasNext())
      {
        Object eachChild = childIter.next();
        if (eachChild.equals(this.contextNode)) {
          break;
        }
        siblings.addFirst(eachChild);
      }
      this.siblingIter = siblings.iterator();
    }
    else
    {
      this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
    }
  }
  
  public boolean hasNext()
  {
    return this.nextObj != null;
  }
  
  public Object next()
    throws NoSuchElementException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Object obj = this.nextObj;
    if (this.siblingIter.hasNext()) {
      this.nextObj = this.siblingIter.next();
    } else {
      this.nextObj = null;
    }
    return obj;
  }
  
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.PrecedingSiblingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */