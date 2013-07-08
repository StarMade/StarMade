package org.jaxen.expr;

import java.io.Serializable;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public abstract interface XPathExpr
  extends Serializable
{
  public abstract Expr getRootExpr();
  
  public abstract void setRootExpr(Expr paramExpr);
  
  public abstract String getText();
  
  public abstract void simplify();
  
  public abstract List asList(Context paramContext)
    throws JaxenException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.XPathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */