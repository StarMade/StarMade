package org.jaxen;

import java.util.List;

public abstract interface Function
{
  public abstract Object call(Context paramContext, List paramList)
    throws FunctionCallException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.Function
 * JD-Core Version:    0.6.2
 */