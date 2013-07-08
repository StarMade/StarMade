package org.jaxen.expr;

abstract class DefaultLogicalExpr
  extends DefaultTruthExpr
  implements LogicalExpr
{
  DefaultLogicalExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultLogicalExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */