/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  98:    */public class CeilingFunction
/*  99:    */  implements Function
/* 100:    */{
/* 101:    */  public Object call(Context context, List args)
/* 102:    */    throws FunctionCallException
/* 103:    */  {
/* 104:104 */    if (args.size() == 1)
/* 105:    */    {
/* 106:106 */      return evaluate(args.get(0), context.getNavigator());
/* 107:    */    }
/* 108:    */    
/* 110:110 */    throw new FunctionCallException("ceiling() requires one argument.");
/* 111:    */  }
/* 112:    */  
/* 124:    */  public static Double evaluate(Object obj, Navigator nav)
/* 125:    */  {
/* 126:126 */    Double value = NumberFunction.evaluate(obj, nav);
/* 127:    */    
/* 129:129 */    return new Double(Math.ceil(value.doubleValue()));
/* 130:    */  }
/* 131:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.CeilingFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */