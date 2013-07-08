package org.jaxen.expr;

public abstract interface PathExpr
  extends Expr
{
  public abstract Expr getFilterExpr();
  
  public abstract void setFilterExpr(Expr paramExpr);
  
  public abstract LocationPath getLocationPath();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.PathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */