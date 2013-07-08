package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultMinusExpr
  extends DefaultAdditiveExpr
{
  private static final long serialVersionUID = 6468563688098527800L;
  
  DefaultMinusExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "-";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
    Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
    double result = lhsValue.doubleValue() - rhsValue.doubleValue();
    return new Double(result);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultMinusExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */