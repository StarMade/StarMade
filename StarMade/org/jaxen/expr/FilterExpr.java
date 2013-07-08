package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;

public abstract interface FilterExpr
  extends Expr, Predicated
{
  public abstract boolean asBoolean(Context paramContext)
    throws JaxenException;
  
  public abstract Expr getExpr();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.FilterExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */