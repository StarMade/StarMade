package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.UnsupportedAxisException;

public abstract interface Step extends Predicated
{
  public abstract boolean matches(Object paramObject, ContextSupport paramContextSupport)
    throws JaxenException;

  public abstract String getText();

  public abstract void simplify();

  public abstract int getAxis();

  public abstract Iterator axisIterator(Object paramObject, ContextSupport paramContextSupport)
    throws UnsupportedAxisException;

  public abstract List evaluate(Context paramContext)
    throws JaxenException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.Step
 * JD-Core Version:    0.6.2
 */