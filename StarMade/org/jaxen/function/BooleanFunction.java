package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class BooleanFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(args.get(0), context.getNavigator());
    }
    throw new FunctionCallException("boolean() requires one argument");
  }
  
  public static Boolean evaluate(Object obj, Navigator nav)
  {
    if ((obj instanceof List))
    {
      List list = (List)obj;
      if (list.size() == 0) {
        return Boolean.FALSE;
      }
      obj = list.get(0);
    }
    if ((obj instanceof Boolean)) {
      return (Boolean)obj;
    }
    if ((obj instanceof Number))
    {
      double list = ((Number)obj).doubleValue();
      if ((list == 0.0D) || (Double.isNaN(list))) {
        return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }
    if ((obj instanceof String)) {
      return ((String)obj).length() > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
    return obj != null ? Boolean.TRUE : Boolean.FALSE;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */