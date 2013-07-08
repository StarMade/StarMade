package org.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class FollowingAxisIterator
  implements Iterator
{
  private Object contextNode;
  private Navigator navigator;
  private Iterator siblings;
  private Iterator currentSibling;
  
  public FollowingAxisIterator(Object contextNode, Navigator navigator)
    throws UnsupportedAxisException
  {
    this.contextNode = contextNode;
    this.navigator = navigator;
    this.siblings = navigator.getFollowingSiblingAxisIterator(contextNode);
    this.currentSibling = JaxenConstants.EMPTY_ITERATOR;
  }
  
  private boolean goForward()
  {
    while (!this.siblings.hasNext()) {
      if (!goUp()) {
        return false;
      }
    }
    Object nextSibling = this.siblings.next();
    this.currentSibling = new DescendantOrSelfAxisIterator(nextSibling, this.navigator);
    return true;
  }
  
  private boolean goUp()
  {
    if ((this.contextNode == null) || (this.navigator.isDocument(this.contextNode))) {
      return false;
    }
    try
    {
      this.contextNode = this.navigator.getParentNode(this.contextNode);
      if ((this.contextNode != null) && (!this.navigator.isDocument(this.contextNode)))
      {
        this.siblings = this.navigator.getFollowingSiblingAxisIterator(this.contextNode);
        return true;
      }
      return false;
    }
    catch (UnsupportedAxisException local_e)
    {
      throw new JaxenRuntimeException(local_e);
    }
  }
  
  public boolean hasNext()
  {
    while (!this.currentSibling.hasNext()) {
      if (!goForward()) {
        return false;
      }
    }
    return true;
  }
  
  public Object next()
    throws NoSuchElementException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return this.currentSibling.next();
  }
  
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.FollowingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */