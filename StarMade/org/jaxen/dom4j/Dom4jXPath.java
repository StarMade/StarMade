/*  1:   */package org.jaxen.dom4j;
/*  2:   */
/*  3:   */import org.jaxen.BaseXPath;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */
/* 84:   */public class Dom4jXPath
/* 85:   */  extends BaseXPath
/* 86:   */{
/* 87:   */  private static final long serialVersionUID = -75510941087659775L;
/* 88:   */  
/* 89:   */  public Dom4jXPath(String xpathExpr)
/* 90:   */    throws JaxenException
/* 91:   */  {
/* 92:92 */    super(xpathExpr, DocumentNavigator.getInstance());
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom4j.Dom4jXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */