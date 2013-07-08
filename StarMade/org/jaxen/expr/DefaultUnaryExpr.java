package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultUnaryExpr
  extends DefaultExpr
  implements UnaryExpr
{
  private static final long serialVersionUID = 2303714238683092334L;
  private Expr expr;
  
  DefaultUnaryExpr(Expr expr)
  {
    this.expr = expr;
  }
  
  public Expr getExpr()
  {
    return this.expr;
  }
  
  public String toString()
  {
    return "[(DefaultUnaryExpr): " + getExpr() + "]";
  }
  
  public String getText()
  {
    return "-(" + getExpr().getText() + ")";
  }
  
  public Expr simplify()
  {
    this.expr = this.expr.simplify();
    return this;
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Number number = NumberFunction.evaluate(getExpr().evaluate(context), context.getNavigator());
    return new Double(number.doubleValue() * -1.0D);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultUnaryExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */