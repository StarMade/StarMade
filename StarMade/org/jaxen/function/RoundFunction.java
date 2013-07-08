package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class RoundFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(args.get(0), context.getNavigator());
    }
    throw new FunctionCallException("round() requires one argument.");
  }
  
  public static Double evaluate(Object obj, Navigator nav)
  {
    Double local_d = NumberFunction.evaluate(obj, nav);
    if ((local_d.isNaN()) || (local_d.isInfinite())) {
      return local_d;
    }
    double value = local_d.doubleValue();
    return new Double(Math.round(value));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.RoundFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */