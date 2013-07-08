package org.jaxen.expr;

class DefaultGreaterThanEqualExpr
  extends DefaultRelationalExpr
{
  private static final long serialVersionUID = -7848747981787197470L;
  
  DefaultGreaterThanEqualExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return ">=";
  }
  
  protected boolean evaluateDoubleDouble(Double lhs, Double rhs)
  {
    return lhs.compareTo(rhs) >= 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultGreaterThanEqualExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */