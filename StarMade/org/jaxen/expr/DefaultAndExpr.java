package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;

class DefaultAndExpr
  extends DefaultLogicalExpr
{
  private static final long serialVersionUID = -5237984010263103742L;
  
  DefaultAndExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "and";
  }
  
  public String toString()
  {
    return "[(DefaultAndExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Navigator nav = context.getNavigator();
    Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
    if (!lhsValue.booleanValue()) {
      return Boolean.FALSE;
    }
    Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
    if (!rhsValue.booleanValue()) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultAndExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */