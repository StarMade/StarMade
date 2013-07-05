package org.jaxen.expr;

import java.util.List;

public abstract interface FunctionCallExpr extends Expr
{
  public abstract String getPrefix();

  public abstract String getFunctionName();

  public abstract void addParameter(Expr paramExpr);

  public abstract List getParameters();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.FunctionCallExpr
 * JD-Core Version:    0.6.2
 */