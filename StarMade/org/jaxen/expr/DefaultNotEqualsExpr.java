package org.jaxen.expr;

import org.jaxen.function.NumberFunction;

class DefaultNotEqualsExpr
  extends DefaultEqualityExpr
{
  private static final long serialVersionUID = -8001267398136979152L;
  
  DefaultNotEqualsExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "!=";
  }
  
  public String toString()
  {
    return "[(DefaultNotEqualsExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  protected boolean evaluateObjectObject(Object lhs, Object rhs)
  {
    if ((eitherIsNumber(lhs, rhs)) && ((NumberFunction.isNaN((Double)lhs)) || (NumberFunction.isNaN((Double)rhs)))) {
      return true;
    }
    return !lhs.equals(rhs);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultNotEqualsExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */