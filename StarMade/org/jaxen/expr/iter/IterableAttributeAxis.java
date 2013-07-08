package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class IterableAttributeAxis
  extends IterableAxis
{
  private static final long serialVersionUID = 1L;
  
  public IterableAttributeAxis(int value)
  {
    super(value);
  }
  
  public Iterator iterator(Object contextNode, ContextSupport support)
    throws UnsupportedAxisException
  {
    return support.getNavigator().getAttributeAxisIterator(contextNode);
  }
  
  public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
    throws UnsupportedAxisException
  {
    NamedAccessNavigator nav = (NamedAccessNavigator)support.getNavigator();
    return nav.getAttributeAxisIterator(contextNode, localName, namespacePrefix, namespaceURI);
  }
  
  public boolean supportsNamedAccess(ContextSupport support)
  {
    return support.getNavigator() instanceof NamedAccessNavigator;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.iter.IterableAttributeAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */