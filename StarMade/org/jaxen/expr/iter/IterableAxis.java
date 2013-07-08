/*   1:    */package org.jaxen.expr.iter;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import org.jaxen.ContextSupport;
/*   6:    */import org.jaxen.UnsupportedAxisException;
/*   7:    */
/*  53:    */public abstract class IterableAxis
/*  54:    */  implements Serializable
/*  55:    */{
/*  56:    */  private int value;
/*  57:    */  
/*  58:    */  public IterableAxis(int axisValue)
/*  59:    */  {
/*  60: 60 */    this.value = axisValue;
/*  61:    */  }
/*  62:    */  
/*  67:    */  public int value()
/*  68:    */  {
/*  69: 69 */    return this.value;
/*  70:    */  }
/*  71:    */  
/*  83:    */  public abstract Iterator iterator(Object paramObject, ContextSupport paramContextSupport)
/*  84:    */    throws UnsupportedAxisException;
/*  85:    */  
/*  96:    */  public Iterator namedAccessIterator(Object contextNode, ContextSupport support, String localName, String namespacePrefix, String namespaceURI)
/*  97:    */    throws UnsupportedAxisException
/*  98:    */  {
/*  99: 99 */    throw new UnsupportedOperationException("Named access unsupported");
/* 100:    */  }
/* 101:    */  
/* 107:    */  public boolean supportsNamedAccess(ContextSupport support)
/* 108:    */  {
/* 109:109 */    return false;
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAxis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */