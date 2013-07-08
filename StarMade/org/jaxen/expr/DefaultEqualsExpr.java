package org.jaxen.expr;

import org.jaxen.function.NumberFunction;

class DefaultEqualsExpr
  extends DefaultEqualityExpr
{
  private static final long serialVersionUID = -8327599812627931648L;
  
  DefaultEqualsExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "=";
  }
  
  public String toString()
  {
    return "[(DefaultEqualsExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  protected boolean evaluateObjectObject(Object lhs, Object rhs)
  {
    if ((eitherIsNumber(lhs, rhs)) && ((NumberFunction.isNaN((Double)lhs)) || (NumberFunction.isNaN((Double)rhs)))) {
      return false;
    }
    return lhs.equals(rhs);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultEqualsExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */