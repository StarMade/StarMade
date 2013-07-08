/*  1:   */package org.jaxen.jdom;
/*  2:   */
/*  3:   */import org.jaxen.BaseXPath;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */
/* 83:   */public class JDOMXPath
/* 84:   */  extends BaseXPath
/* 85:   */{
/* 86:   */  private static final long serialVersionUID = 6426091824802286928L;
/* 87:   */  
/* 88:   */  public JDOMXPath(String xpathExpr)
/* 89:   */    throws JaxenException
/* 90:   */  {
/* 91:91 */    super(xpathExpr, DocumentNavigator.getInstance());
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.JDOMXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */