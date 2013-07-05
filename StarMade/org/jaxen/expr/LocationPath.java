package org.jaxen.expr;

import java.util.List;

public abstract interface LocationPath extends Expr
{
  public abstract void addStep(Step paramStep);

  public abstract List getSteps();

  public abstract boolean isAbsolute();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.LocationPath
 * JD-Core Version:    0.6.2
 */