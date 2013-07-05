package org.jaxen.expr;

import java.io.Serializable;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public abstract interface Predicate extends Serializable
{
  public abstract Expr getExpr();

  public abstract void setExpr(Expr paramExpr);

  public abstract void simplify();

  public abstract String getText();

  public abstract Object evaluate(Context paramContext)
    throws JaxenException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.Predicate
 * JD-Core Version:    0.6.2
 */