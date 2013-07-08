package org.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class AncestorOrSelfAxisIterator
  implements Iterator
{
  private Object contextNode;
  private Navigator navigator;
  
  public AncestorOrSelfAxisIterator(Object contextNode, Navigator navigator)
  {
    this.contextNode = contextNode;
    this.navigator = navigator;
  }
  
  public boolean hasNext()
  {
    return this.contextNode != null;
  }
  
  public Object next()
  {
    try
    {
      if (hasNext())
      {
        Object result = this.contextNode;
        this.contextNode = this.navigator.getParentNode(this.contextNode);
        return result;
      }
      throw new NoSuchElementException("Exhausted ancestor-or-self axis");
    }
    catch (UnsupportedAxisException result)
    {
      throw new JaxenRuntimeException(result);
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.AncestorOrSelfAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */