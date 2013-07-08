package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.util.SingleObjectIterator;
import org.jaxen.util.SingletonList;

/**
 * @deprecated
 */
public abstract class DefaultExpr
  implements Expr
{
  public Expr simplify()
  {
    return this;
  }
  
  public static Iterator convertToIterator(Object obj)
  {
    if ((obj instanceof Iterator)) {
      return (Iterator)obj;
    }
    if ((obj instanceof List)) {
      return ((List)obj).iterator();
    }
    return new SingleObjectIterator(obj);
  }
  
  public static List convertToList(Object obj)
  {
    if ((obj instanceof List)) {
      return (List)obj;
    }
    return new SingletonList(obj);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */