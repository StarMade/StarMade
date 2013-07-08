package org.jaxen;

import java.util.List;

public abstract interface Function
{
  public abstract Object call(Context paramContext, List paramList)
    throws FunctionCallException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.Function
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */