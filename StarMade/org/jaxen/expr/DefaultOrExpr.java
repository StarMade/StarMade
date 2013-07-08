package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;

class DefaultOrExpr
  extends DefaultLogicalExpr
{
  private static final long serialVersionUID = 4894552680753026730L;
  
  DefaultOrExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "or";
  }
  
  public String toString()
  {
    return "[(DefaultOrExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Navigator nav = context.getNavigator();
    Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
    if (lhsValue.booleanValue()) {
      return Boolean.TRUE;
    }
    Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
    if (rhsValue.booleanValue()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultOrExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */