package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class IterablePrecedingAxis
  extends IterableAxis
{
  private static final long serialVersionUID = 587333938258540052L;
  
  public IterablePrecedingAxis(int value)
  {
    super(value);
  }
  
  public Iterator iterator(Object contextNode, ContextSupport support)
    throws UnsupportedAxisException
  {
    return support.getNavigator().getPrecedingAxisIterator(contextNode);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.iter.IterablePrecedingAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */