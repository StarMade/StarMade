package org.jaxen.function;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class ConcatFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() >= 2) {
      return evaluate(args, context.getNavigator());
    }
    throw new FunctionCallException("concat() requires at least two arguments");
  }
  
  public static String evaluate(List list, Navigator nav)
  {
    StringBuffer result = new StringBuffer();
    Iterator argIter = list.iterator();
    while (argIter.hasNext()) {
      result.append(StringFunction.evaluate(argIter.next(), nav));
    }
    return result.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.ConcatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */