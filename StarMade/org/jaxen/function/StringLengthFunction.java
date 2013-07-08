package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class StringLengthFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 0) {
      return evaluate(context.getNodeSet(), context.getNavigator());
    }
    if (args.size() == 1) {
      return evaluate(args.get(0), context.getNavigator());
    }
    throw new FunctionCallException("string-length() requires one argument.");
  }
  
  public static Double evaluate(Object obj, Navigator nav)
    throws FunctionCallException
  {
    String str = StringFunction.evaluate(obj, nav);
    char[] data = str.toCharArray();
    int length = 0;
    for (int local_i = 0; local_i < data.length; local_i++)
    {
      char local_c = data[local_i];
      length++;
      if ((local_c >= 55296) && (local_c <= 57343)) {
        try
        {
          char low = data[(local_i + 1)];
          if ((low < 56320) || (low > 57343)) {
            throw new FunctionCallException("Bad surrogate pair in string " + str);
          }
          local_i++;
        }
        catch (ArrayIndexOutOfBoundsException low)
        {
          throw new FunctionCallException("Bad surrogate pair in string " + str);
        }
      }
    }
    return new Double(length);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.StringLengthFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */