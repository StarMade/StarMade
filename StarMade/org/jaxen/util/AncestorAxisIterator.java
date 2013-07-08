/*  1:   */package org.jaxen.util;
/*  2:   */
/*  3:   */import org.jaxen.Navigator;
/*  4:   */
/* 71:   */public class AncestorAxisIterator
/* 72:   */  extends AncestorOrSelfAxisIterator
/* 73:   */{
/* 74:   */  public AncestorAxisIterator(Object contextNode, Navigator navigator)
/* 75:   */  {
/* 76:76 */    super(contextNode, navigator);
/* 77:   */    
/* 78:78 */    next();
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.AncestorAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */