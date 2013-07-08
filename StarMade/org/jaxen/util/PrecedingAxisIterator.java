package org.jaxen.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class PrecedingAxisIterator
  implements Iterator
{
  private Iterator ancestorOrSelf;
  private Iterator precedingSibling;
  private ListIterator childrenOrSelf;
  private ArrayList stack;
  private Navigator navigator;
  
  public PrecedingAxisIterator(Object contextNode, Navigator navigator)
    throws UnsupportedAxisException
  {
    this.navigator = navigator;
    this.ancestorOrSelf = navigator.getAncestorOrSelfAxisIterator(contextNode);
    this.precedingSibling = JaxenConstants.EMPTY_ITERATOR;
    this.childrenOrSelf = JaxenConstants.EMPTY_LIST_ITERATOR;
    this.stack = new ArrayList();
  }
  
  public boolean hasNext()
  {
    try
    {
      while (!this.childrenOrSelf.hasPrevious()) {
        if (this.stack.isEmpty())
        {
          while (!this.precedingSibling.hasNext())
          {
            if (!this.ancestorOrSelf.hasNext()) {
              return false;
            }
            Object contextNode = this.ancestorOrSelf.next();
            this.precedingSibling = new PrecedingSiblingAxisIterator(contextNode, this.navigator);
          }
          Object contextNode = this.precedingSibling.next();
          this.childrenOrSelf = childrenOrSelf(contextNode);
        }
        else
        {
          this.childrenOrSelf = ((ListIterator)this.stack.remove(this.stack.size() - 1));
        }
      }
      return true;
    }
    catch (UnsupportedAxisException contextNode)
    {
      throw new JaxenRuntimeException(contextNode);
    }
  }
  
  private ListIterator childrenOrSelf(Object node)
  {
    try
    {
      ArrayList reversed = new ArrayList();
      reversed.add(node);
      Iterator childAxisIterator = this.navigator.getChildAxisIterator(node);
      if (childAxisIterator != null) {
        while (childAxisIterator.hasNext()) {
          reversed.add(childAxisIterator.next());
        }
      }
      return reversed.listIterator(reversed.size());
    }
    catch (UnsupportedAxisException reversed)
    {
      throw new JaxenRuntimeException(reversed);
    }
  }
  
  public Object next()
    throws NoSuchElementException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Object result;
    for (;;)
    {
      result = this.childrenOrSelf.previous();
      if (!this.childrenOrSelf.hasPrevious()) {
        break;
      }
      this.stack.add(this.childrenOrSelf);
      this.childrenOrSelf = childrenOrSelf(result);
    }
    return result;
  }
  
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.PrecedingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */