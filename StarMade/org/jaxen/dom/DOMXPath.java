/*  1:   */package org.jaxen.dom;
/*  2:   */
/*  3:   */import org.jaxen.BaseXPath;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */
/* 79:   */public class DOMXPath
/* 80:   */  extends BaseXPath
/* 81:   */{
/* 82:   */  private static final long serialVersionUID = 5551221776001439091L;
/* 83:   */  
/* 84:   */  public DOMXPath(String xpathExpr)
/* 85:   */    throws JaxenException
/* 86:   */  {
/* 87:87 */    super(xpathExpr, DocumentNavigator.getInstance());
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.DOMXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */