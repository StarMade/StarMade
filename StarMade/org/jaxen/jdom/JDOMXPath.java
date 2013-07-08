package org.jaxen.jdom;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class JDOMXPath
  extends BaseXPath
{
  private static final long serialVersionUID = 6426091824802286928L;
  
  public JDOMXPath(String xpathExpr)
    throws JaxenException
  {
    super(xpathExpr, DocumentNavigator.getInstance());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.jdom.JDOMXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */