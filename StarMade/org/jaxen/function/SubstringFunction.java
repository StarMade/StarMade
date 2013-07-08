package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class SubstringFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    int argc = args.size();
    if ((argc < 2) || (argc > 3)) {
      throw new FunctionCallException("substring() requires two or three arguments.");
    }
    Navigator nav = context.getNavigator();
    String str = StringFunction.evaluate(args.get(0), nav);
    if (str == null) {
      return "";
    }
    int stringLength = StringLengthFunction.evaluate(args.get(0), nav).intValue();
    if (stringLength == 0) {
      return "";
    }
    Double local_d1 = NumberFunction.evaluate(args.get(1), nav);
    if (local_d1.isNaN()) {
      return "";
    }
    int start = RoundFunction.evaluate(local_d1, nav).intValue() - 1;
    int substringLength = stringLength;
    if (argc == 3)
    {
      Double local_d2 = NumberFunction.evaluate(args.get(2), nav);
      if (!local_d2.isNaN()) {
        substringLength = RoundFunction.evaluate(local_d2, nav).intValue();
      } else {
        substringLength = 0;
      }
    }
    if (substringLength < 0) {
      return "";
    }
    int local_d2 = start + substringLength;
    if (argc == 2) {
      local_d2 = stringLength;
    }
    if (start < 0) {
      start = 0;
    } else if (start > stringLength) {
      return "";
    }
    if (local_d2 > stringLength) {
      local_d2 = stringLength;
    } else if (local_d2 < start) {
      return "";
    }
    if (stringLength == str.length()) {
      return str.substring(start, local_d2);
    }
    return unicodeSubstring(str, start, local_d2);
  }
  
  private static String unicodeSubstring(String local_s, int start, int end)
  {
    StringBuffer result = new StringBuffer(local_s.length());
    int jChar = 0;
    for (int uChar = 0; uChar < end; uChar++)
    {
      char local_c = local_s.charAt(jChar);
      if (uChar >= start) {
        result.append(local_c);
      }
      if (local_c >= 55296)
      {
        jChar++;
        if (uChar >= start) {
          result.append(local_s.charAt(jChar));
        }
      }
      jChar++;
    }
    return result.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.SubstringFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */