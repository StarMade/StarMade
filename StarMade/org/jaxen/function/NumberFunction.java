package org.jaxen.function;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class NumberFunction
  implements Function
{
  private static final Double NaN = new Double((0.0D / 0.0D));
  
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(args.get(0), context.getNavigator());
    }
    if (args.size() == 0) {
      return evaluate(context.getNodeSet(), context.getNavigator());
    }
    throw new FunctionCallException("number() takes at most one argument.");
  }
  
  public static Double evaluate(Object obj, Navigator nav)
  {
    if ((obj instanceof Double)) {
      return (Double)obj;
    }
    if ((obj instanceof String))
    {
      String str = (String)obj;
      try
      {
        Double doubleValue = new Double(str);
        return doubleValue;
      }
      catch (NumberFormatException doubleValue)
      {
        return NaN;
      }
    }
    if (((obj instanceof List)) || ((obj instanceof Iterator))) {
      return evaluate(StringFunction.evaluate(obj, nav), nav);
    }
    if ((nav.isElement(obj)) || (nav.isAttribute(obj)) || (nav.isText(obj)) || (nav.isComment(obj)) || (nav.isProcessingInstruction(obj)) || (nav.isDocument(obj)) || (nav.isNamespace(obj))) {
      return evaluate(StringFunction.evaluate(obj, nav), nav);
    }
    if ((obj instanceof Boolean))
    {
      if (obj == Boolean.TRUE) {
        return new Double(1.0D);
      }
      return new Double(0.0D);
    }
    return NaN;
  }
  
  public static boolean isNaN(double val)
  {
    return Double.isNaN(val);
  }
  
  public static boolean isNaN(Double val)
  {
    return val.equals(NaN);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.NumberFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */