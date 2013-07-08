package org.jaxen.expr;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;

/**
 * @deprecated
 */
public class DefaultXPathExpr
  implements XPathExpr
{
  private static final long serialVersionUID = 3007613096320896040L;
  private Expr rootExpr;
  
  public DefaultXPathExpr(Expr rootExpr)
  {
    this.rootExpr = rootExpr;
  }
  
  public Expr getRootExpr()
  {
    return this.rootExpr;
  }
  
  public void setRootExpr(Expr rootExpr)
  {
    this.rootExpr = rootExpr;
  }
  
  public String toString()
  {
    return "[(DefaultXPath): " + getRootExpr() + "]";
  }
  
  public String getText()
  {
    return getRootExpr().getText();
  }
  
  public void simplify()
  {
    setRootExpr(getRootExpr().simplify());
  }
  
  public List asList(Context context)
    throws JaxenException
  {
    Expr expr = getRootExpr();
    Object value = expr.evaluate(context);
    List result = DefaultExpr.convertToList(value);
    return result;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultXPathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */