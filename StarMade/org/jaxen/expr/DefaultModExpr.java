package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.function.NumberFunction;

class DefaultModExpr
  extends DefaultMultiplicativeExpr
{
  private static final long serialVersionUID = -5554964716492040687L;
  
  DefaultModExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "mod";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
    Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
    double result = lhsValue.doubleValue() % rhsValue.doubleValue();
    return new Double(result);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultModExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */