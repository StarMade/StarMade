/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  92:    */public class ContainsFunction
/*  93:    */  implements Function
/*  94:    */{
/*  95:    */  public Object call(Context context, List args)
/*  96:    */    throws FunctionCallException
/*  97:    */  {
/*  98: 98 */    if (args.size() == 2)
/*  99:    */    {
/* 100:100 */      return evaluate(args.get(0), args.get(1), context.getNavigator());
/* 101:    */    }
/* 102:    */    
/* 105:105 */    throw new FunctionCallException("contains() requires two arguments.");
/* 106:    */  }
/* 107:    */  
/* 123:    */  public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/* 124:    */  {
/* 125:125 */    String str = StringFunction.evaluate(strArg, nav);
/* 126:    */    
/* 128:128 */    String match = StringFunction.evaluate(matchArg, nav);
/* 129:    */    
/* 131:131 */    return str.indexOf(match) >= 0 ? Boolean.TRUE : Boolean.FALSE;
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ContainsFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */