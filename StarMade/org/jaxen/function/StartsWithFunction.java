/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  90:    */public class StartsWithFunction
/*  91:    */  implements Function
/*  92:    */{
/*  93:    */  public Object call(Context context, List args)
/*  94:    */    throws FunctionCallException
/*  95:    */  {
/*  96: 96 */    if (args.size() == 2)
/*  97:    */    {
/*  98: 98 */      return evaluate(args.get(0), args.get(1), context.getNavigator());
/*  99:    */    }
/* 100:    */    
/* 103:103 */    throw new FunctionCallException("starts-with() requires two arguments.");
/* 104:    */  }
/* 105:    */  
/* 122:    */  public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/* 123:    */  {
/* 124:124 */    String str = StringFunction.evaluate(strArg, nav);
/* 125:    */    
/* 127:127 */    String match = StringFunction.evaluate(matchArg, nav);
/* 128:    */    
/* 130:130 */    return str.startsWith(match) ? Boolean.TRUE : Boolean.FALSE;
/* 131:    */  }
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StartsWithFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */