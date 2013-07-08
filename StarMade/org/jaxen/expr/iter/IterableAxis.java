package org.jaxen.expr.iter;

import java.io.Serializable;
import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public abstract class IterableAxis
  implements Serializable
{
  private int value;
  
  public IterableAxis(int axisValue)
  {
    this.value = axisValue;
  }
  
  public int value()
  {
    return this.value;
  }
  
  public abstract Iterator iterator(Object paramObject, ContextSupport paramContextSupport)
    throws UnsupportedAxisException;
  
  public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
    throws UnsupportedAxisException
  {
    throw new UnsupportedOperationException("Named access unsupported");
  }
  
  public boolean supportsNamedAccess(ContextSupport support)
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.iter.IterableAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */