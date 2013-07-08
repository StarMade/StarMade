package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class IterablePrecedingSiblingAxis
  extends IterableAxis
{
  private static final long serialVersionUID = -3140080721715120745L;
  
  public IterablePrecedingSiblingAxis(int value)
  {
    super(value);
  }
  
  public Iterator iterator(Object contextNode, ContextSupport support)
    throws UnsupportedAxisException
  {
    return support.getNavigator().getPrecedingSiblingAxisIterator(contextNode);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.iter.IterablePrecedingSiblingAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */