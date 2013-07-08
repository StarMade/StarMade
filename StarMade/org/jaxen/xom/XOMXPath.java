/*  1:   */package org.jaxen.xom;
/*  2:   */
/*  3:   */import org.jaxen.BaseXPath;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */
/* 80:   */public class XOMXPath
/* 81:   */  extends BaseXPath
/* 82:   */{
/* 83:   */  private static final long serialVersionUID = -5332108546921857671L;
/* 84:   */  
/* 85:   */  public XOMXPath(String xpathExpr)
/* 86:   */    throws JaxenException
/* 87:   */  {
/* 88:88 */    super(xpathExpr, new DocumentNavigator());
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.xom.XOMXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */