package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;

public class CountFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(args.get(0));
    }
    throw new FunctionCallException("count() requires one argument.");
  }
  
  public static Double evaluate(Object obj)
    throws FunctionCallException
  {
    if ((obj instanceof List)) {
      return new Double(((List)obj).size());
    }
    throw new FunctionCallException("count() function can only be used for node-sets");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.CountFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */