/*   1:    */package org.jaxen.expr.iter;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import org.jaxen.ContextSupport;
/*   5:    */import org.jaxen.NamedAccessNavigator;
/*   6:    */import org.jaxen.Navigator;
/*   7:    */import org.jaxen.UnsupportedAxisException;
/*   8:    */
/*  58:    */public class IterableAttributeAxis
/*  59:    */  extends IterableAxis
/*  60:    */{
/*  61:    */  private static final long serialVersionUID = 1L;
/*  62:    */  
/*  63:    */  public IterableAttributeAxis(int value)
/*  64:    */  {
/*  65: 65 */    super(value);
/*  66:    */  }
/*  67:    */  
/*  72:    */  public Iterator iterator(Object contextNode, ContextSupport support)
/*  73:    */    throws UnsupportedAxisException
/*  74:    */  {
/*  75: 75 */    return support.getNavigator().getAttributeAxisIterator(contextNode);
/*  76:    */  }
/*  77:    */  
/*  92:    */  public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
/*  93:    */    throws UnsupportedAxisException
/*  94:    */  {
/*  95: 95 */    NamedAccessNavigator nav = (NamedAccessNavigator)support.getNavigator();
/*  96: 96 */    return nav.getAttributeAxisIterator(contextNode, localName, namespacePrefix, namespaceURI);
/*  97:    */  }
/*  98:    */  
/* 104:    */  public boolean supportsNamedAccess(ContextSupport support)
/* 105:    */  {
/* 106:106 */    return support.getNavigator() instanceof NamedAccessNavigator;
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAttributeAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */