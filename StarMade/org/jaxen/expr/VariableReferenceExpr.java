package org.jaxen.expr;

public abstract interface VariableReferenceExpr extends Expr
{
  public abstract String getPrefix();

  public abstract String getVariableName();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.VariableReferenceExpr
 * JD-Core Version:    0.6.2
 */