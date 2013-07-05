package org.jaxen.expr;

import java.io.Serializable;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public abstract interface Expr extends Serializable
{
  public abstract String getText();

  public abstract Expr simplify();

  public abstract Object evaluate(Context paramContext)
    throws JaxenException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.Expr
 * JD-Core Version:    0.6.2
 */