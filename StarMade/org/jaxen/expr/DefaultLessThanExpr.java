package org.jaxen.expr;

class DefaultLessThanExpr
  extends DefaultRelationalExpr
{
  private static final long serialVersionUID = 8423816025305001283L;
  
  DefaultLessThanExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "<";
  }
  
  protected boolean evaluateDoubleDouble(Double lhs, Double rhs)
  {
    return lhs.compareTo(rhs) < 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultLessThanExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */