/*  1:   */package org.jaxen.expr.iter;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import org.jaxen.ContextSupport;
/*  5:   */import org.jaxen.Navigator;
/*  6:   */import org.jaxen.UnsupportedAxisException;
/*  7:   */
/* 59:   */public class IterableAncestorAxis
/* 60:   */  extends IterableAxis
/* 61:   */{
/* 62:   */  private static final long serialVersionUID = 1L;
/* 63:   */  
/* 64:   */  public IterableAncestorAxis(int value)
/* 65:   */  {
/* 66:66 */    super(value);
/* 67:   */  }
/* 68:   */  
/* 69:   */  public Iterator iterator(Object contextNode, ContextSupport support)
/* 70:   */    throws UnsupportedAxisException
/* 71:   */  {
/* 72:72 */    return support.getNavigator().getAncestorAxisIterator(contextNode);
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAncestorAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */