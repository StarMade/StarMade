package org.jaxen.util;

import org.jaxen.Navigator;

public class AncestorAxisIterator
  extends AncestorOrSelfAxisIterator
{
  public AncestorAxisIterator(Object contextNode, Navigator navigator)
  {
    super(contextNode, navigator);
    next();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.AncestorAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */