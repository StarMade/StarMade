package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class IterableSelfAxis
  extends IterableAxis
{
  private static final long serialVersionUID = 8292222516706760134L;
  
  public IterableSelfAxis(int value)
  {
    super(value);
  }
  
  public Iterator iterator(Object contextNode, ContextSupport support)
    throws UnsupportedAxisException
  {
    return support.getNavigator().getSelfAxisIterator(contextNode);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.iter.IterableSelfAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */