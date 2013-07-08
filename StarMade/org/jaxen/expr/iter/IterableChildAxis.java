/*   1:    */package org.jaxen.expr.iter;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import org.jaxen.ContextSupport;
/*   5:    */import org.jaxen.NamedAccessNavigator;
/*   6:    */import org.jaxen.Navigator;
/*   7:    */import org.jaxen.UnsupportedAxisException;
/*   8:    */
/*  55:    */public class IterableChildAxis
/*  56:    */  extends IterableAxis
/*  57:    */{
/*  58:    */  private static final long serialVersionUID = 1L;
/*  59:    */  
/*  60:    */  public IterableChildAxis(int value)
/*  61:    */  {
/*  62: 62 */    super(value);
/*  63:    */  }
/*  64:    */  
/*  72:    */  public Iterator iterator(Object contextNode, ContextSupport support)
/*  73:    */    throws UnsupportedAxisException
/*  74:    */  {
/*  75: 75 */    return support.getNavigator().getChildAxisIterator(contextNode);
/*  76:    */  }
/*  77:    */  
/*  94:    */  public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
/*  95:    */    throws UnsupportedAxisException
/*  96:    */  {
/*  97: 97 */    NamedAccessNavigator nav = (NamedAccessNavigator)support.getNavigator();
/*  98: 98 */    return nav.getChildAxisIterator(contextNode, localName, namespacePrefix, namespaceURI);
/*  99:    */  }
/* 100:    */  
/* 106:    */  public boolean supportsNamedAccess(ContextSupport support)
/* 107:    */  {
/* 108:108 */    return support.getNavigator() instanceof NamedAccessNavigator;
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableChildAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */