package org.jaxen.expr;

abstract class DefaultAdditiveExpr
  extends DefaultArithExpr
  implements AdditiveExpr
{
  DefaultAdditiveExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(" + getClass().getName() + "): " + getLHS() + ", " + getRHS() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultAdditiveExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */