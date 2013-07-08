package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class NormalizeSpaceFunction
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
    throw new FunctionCallException("normalize-space() cannot have more than one argument");
  }
  
  public static String evaluate(Object strArg, Navigator nav)
  {
    String str = StringFunction.evaluate(strArg, nav);
    char[] buffer = str.toCharArray();
    int write = 0;
    int lastWrite = 0;
    boolean wroteOne = false;
    int read = 0;
    for (;;)
    {
      if (read >= buffer.length) {
        break label101;
      }
      if (isXMLSpace(buffer[read]))
      {
        if (wroteOne) {
          buffer[(write++)] = ' ';
        }
        read++;
        if (read >= buffer.length) {
          continue;
        }
        if (isXMLSpace(buffer[read])) {
          break;
        }
        continue;
      }
      buffer[(write++)] = buffer[(read++)];
      wroteOne = true;
      lastWrite = write;
    }
    label101:
    return new String(buffer, 0, lastWrite);
  }
  
  private static boolean isXMLSpace(char local_c)
  {
    return (local_c == ' ') || (local_c == '\n') || (local_c == '\r') || (local_c == '\t');
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.NormalizeSpaceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */