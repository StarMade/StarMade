package org.jaxen.util;

import org.jaxen.Navigator;

public class DescendantOrSelfAxisIterator
  extends DescendantAxisIterator
{
  public DescendantOrSelfAxisIterator(Object contextNode, Navigator navigator)
  {
    super(navigator, new SingleObjectIterator(contextNode));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.DescendantOrSelfAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */