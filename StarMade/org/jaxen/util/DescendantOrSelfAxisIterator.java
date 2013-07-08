/*  1:   */package org.jaxen.util;
/*  2:   */
/*  3:   */import org.jaxen.Navigator;
/*  4:   */
/* 67:   */public class DescendantOrSelfAxisIterator
/* 68:   */  extends DescendantAxisIterator
/* 69:   */{
/* 70:   */  public DescendantOrSelfAxisIterator(Object contextNode, Navigator navigator)
/* 71:   */  {
/* 72:72 */    super(navigator, new SingleObjectIterator(contextNode));
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.DescendantOrSelfAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */