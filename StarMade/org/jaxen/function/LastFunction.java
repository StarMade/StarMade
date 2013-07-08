package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;

public class LastFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 0) {
      return evaluate(context);
    }
    throw new FunctionCallException("last() requires no arguments.");
  }
  
  public static Double evaluate(Context context)
  {
    return new Double(context.getSize());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.LastFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */