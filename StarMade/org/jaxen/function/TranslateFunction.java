package org.jaxen.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class TranslateFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 3) {
      return evaluate(args.get(0), args.get(1), args.get(2), context.getNavigator());
    }
    throw new FunctionCallException("translate() requires three arguments.");
  }
  
  public static String evaluate(Object strArg, Object fromArg, Object toArg, Navigator nav)
    throws FunctionCallException
  {
    String inStr = StringFunction.evaluate(strArg, nav);
    String fromStr = StringFunction.evaluate(fromArg, nav);
    String toStr = StringFunction.evaluate(toArg, nav);
    Map characterMap = new HashMap();
    String[] fromCharacters = toUnicodeCharacters(fromStr);
    String[] toCharacters = toUnicodeCharacters(toStr);
    int fromLen = fromCharacters.length;
    int toLen = toCharacters.length;
    for (int local_i = 0; local_i < fromLen; local_i++)
    {
      String cFrom = fromCharacters[local_i];
      if (!characterMap.containsKey(cFrom)) {
        if (local_i < toLen) {
          characterMap.put(cFrom, toCharacters[local_i]);
        } else {
          characterMap.put(cFrom, null);
        }
      }
    }
    StringBuffer local_i = new StringBuffer(inStr.length());
    String[] cFrom = toUnicodeCharacters(inStr);
    int inLen = cFrom.length;
    for (int local_i1 = 0; local_i1 < inLen; local_i1++)
    {
      String cIn = cFrom[local_i1];
      if (characterMap.containsKey(cIn))
      {
        String cTo = (String)characterMap.get(cIn);
        if (cTo != null) {
          local_i.append(cTo);
        }
      }
      else
      {
        local_i.append(cIn);
      }
    }
    return local_i.toString();
  }
  
  private static String[] toUnicodeCharacters(String local_s)
    throws FunctionCallException
  {
    String[] result = new String[local_s.length()];
    int stringLength = 0;
    for (int local_i = 0; local_i < local_s.length(); local_i++)
    {
      char local_c1 = local_s.charAt(local_i);
      if (isHighSurrogate(local_c1)) {
        try
        {
          char local_c2 = local_s.charAt(local_i + 1);
          if (isLowSurrogate(local_c2))
          {
            result[stringLength] = (local_c1 + "" + local_c2).intern();
            local_i++;
          }
          else
          {
            throw new FunctionCallException("Mismatched surrogate pair in translate function");
          }
        }
        catch (StringIndexOutOfBoundsException local_c2)
        {
          throw new FunctionCallException("High surrogate without low surrogate at end of string passed to translate function");
        }
      } else {
        result[stringLength] = String.valueOf(local_c1).intern();
      }
      stringLength++;
    }
    if (stringLength == result.length) {
      return result;
    }
    String[] local_i = new String[stringLength];
    System.arraycopy(result, 0, local_i, 0, stringLength);
    return local_i;
  }
  
  private static boolean isHighSurrogate(char local_c)
  {
    return (local_c >= 55296) && (local_c <= 56319);
  }
  
  private static boolean isLowSurrogate(char local_c)
  {
    return (local_c >= 56320) && (local_c <= 57343);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.TranslateFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */