/*  1:   */package org.jaxen.function.ext;
/*  2:   */
/*  3:   */import java.util.List;
/*  4:   */import org.jaxen.Context;
/*  5:   */import org.jaxen.Function;
/*  6:   */import org.jaxen.FunctionCallException;
/*  7:   */import org.jaxen.Navigator;
/*  8:   */import org.jaxen.function.StringFunction;
/*  9:   */
/* 63:   */public class EndsWithFunction
/* 64:   */  implements Function
/* 65:   */{
/* 66:   */  public Object call(Context context, List args)
/* 67:   */    throws FunctionCallException
/* 68:   */  {
/* 69:69 */    if (args.size() == 2)
/* 70:   */    {
/* 71:71 */      return evaluate(args.get(0), args.get(1), context.getNavigator());
/* 72:   */    }
/* 73:   */    
/* 76:76 */    throw new FunctionCallException("ends-with() requires two arguments.");
/* 77:   */  }
/* 78:   */  
/* 81:   */  public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/* 82:   */  {
/* 83:83 */    String str = StringFunction.evaluate(strArg, nav);
/* 84:   */    
/* 86:86 */    String match = StringFunction.evaluate(matchArg, nav);
/* 87:   */    
/* 89:89 */    return str.endsWith(match) ? Boolean.TRUE : Boolean.FALSE;
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.EndsWithFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */