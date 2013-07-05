package org.jaxen.expr;

public abstract interface BinaryExpr extends Expr
{
  public abstract Expr getLHS();

  public abstract Expr getRHS();

  public abstract String getOperator();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.BinaryExpr
 * JD-Core Version:    0.6.2
 */