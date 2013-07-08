package org.jaxen.function.xslt;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public class DocumentFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1)
    {
      Navigator nav = context.getNavigator();
      String url = StringFunction.evaluate(args.get(0), nav);
      return evaluate(url, nav);
    }
    throw new FunctionCallException("document() requires one argument.");
  }
  
  public static Object evaluate(String url, Navigator nav)
    throws FunctionCallException
  {
    return nav.getDocument(url);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.xslt.DocumentFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */