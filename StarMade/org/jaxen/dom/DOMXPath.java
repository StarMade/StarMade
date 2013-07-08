package org.jaxen.dom;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class DOMXPath
  extends BaseXPath
{
  private static final long serialVersionUID = 5551221776001439091L;
  
  public DOMXPath(String xpathExpr)
    throws JaxenException
  {
    super(xpathExpr, DocumentNavigator.getInstance());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.dom.DOMXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */