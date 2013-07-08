package org.jaxen.expr;

abstract class DefaultArithExpr
  extends DefaultBinaryExpr
{
  DefaultArithExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(DefaultArithExpr): " + getLHS() + ", " + getRHS() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultArithExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */