/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  95:    */public class FloorFunction
/*  96:    */  implements Function
/*  97:    */{
/*  98:    */  public Object call(Context context, List args)
/*  99:    */    throws FunctionCallException
/* 100:    */  {
/* 101:101 */    if (args.size() == 1)
/* 102:    */    {
/* 103:103 */      return evaluate(args.get(0), context.getNavigator());
/* 104:    */    }
/* 105:    */    
/* 107:107 */    throw new FunctionCallException("floor() requires one argument.");
/* 108:    */  }
/* 109:    */  
/* 121:    */  public static Double evaluate(Object obj, Navigator nav)
/* 122:    */  {
/* 123:123 */    Double value = NumberFunction.evaluate(obj, nav);
/* 124:    */    
/* 126:126 */    return new Double(Math.floor(value.doubleValue()));
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.FloorFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */