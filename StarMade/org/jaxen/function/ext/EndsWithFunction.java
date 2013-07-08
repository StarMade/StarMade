package org.jaxen.function.ext;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public class EndsWithFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 2) {
      return evaluate(args.get(0), args.get(1), context.getNavigator());
    }
    throw new FunctionCallException("ends-with() requires two arguments.");
  }
  
  public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
  {
    String str = StringFunction.evaluate(strArg, nav);
    String match = StringFunction.evaluate(matchArg, nav);
    return str.endsWith(match) ? Boolean.TRUE : Boolean.FALSE;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.ext.EndsWithFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */