package org.jaxen.function.ext;

import java.util.List;
import java.util.Locale;
import org.jaxen.Context;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public class LowerFunction
  extends LocaleFunctionSupport
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    Navigator navigator = context.getNavigator();
    int size = args.size();
    if (size > 0)
    {
      Object text = args.get(0);
      Locale locale = null;
      if (size > 1) {
        locale = getLocale(args.get(1), navigator);
      }
      return evaluate(text, locale, navigator);
    }
    throw new FunctionCallException("lower-case() requires at least one argument.");
  }
  
  public static String evaluate(Object strArg, Locale locale, Navigator nav)
  {
    String str = StringFunction.evaluate(strArg, nav);
    if (locale == null) {
      locale = Locale.ENGLISH;
    }
    return str.toLowerCase(locale);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.ext.LowerFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */