/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import java.util.List;
/*  5:   */import org.jaxen.util.SingleObjectIterator;
/*  6:   */import org.jaxen.util.SingletonList;
/*  7:   */
/* 58:   *//**
/* 59:   */ * @deprecated
/* 60:   */ */
/* 61:   */public abstract class DefaultExpr
/* 62:   */  implements Expr
/* 63:   */{
/* 64:   */  public Expr simplify()
/* 65:   */  {
/* 66:66 */    return this;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public static Iterator convertToIterator(Object obj)
/* 70:   */  {
/* 71:71 */    if ((obj instanceof Iterator))
/* 72:   */    {
/* 73:73 */      return (Iterator)obj;
/* 74:   */    }
/* 75:   */    
/* 76:76 */    if ((obj instanceof List))
/* 77:   */    {
/* 78:78 */      return ((List)obj).iterator();
/* 79:   */    }
/* 80:   */    
/* 81:81 */    return new SingleObjectIterator(obj);
/* 82:   */  }
/* 83:   */  
/* 84:   */  public static List convertToList(Object obj)
/* 85:   */  {
/* 86:86 */    if ((obj instanceof List))
/* 87:   */    {
/* 88:88 */      return (List)obj;
/* 89:   */    }
/* 90:   */    
/* 91:91 */    return new SingletonList(obj);
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */