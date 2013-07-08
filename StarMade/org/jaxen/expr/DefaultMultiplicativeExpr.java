package org.jaxen.expr;

abstract class DefaultMultiplicativeExpr
  extends DefaultArithExpr
  implements MultiplicativeExpr
{
  DefaultMultiplicativeExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(DefaultMultiplicativeExpr): " + getLHS() + ", " + getRHS() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultMultiplicativeExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */