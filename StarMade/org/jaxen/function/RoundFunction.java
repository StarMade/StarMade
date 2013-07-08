/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  94:    */public class RoundFunction
/*  95:    */  implements Function
/*  96:    */{
/*  97:    */  public Object call(Context context, List args)
/*  98:    */    throws FunctionCallException
/*  99:    */  {
/* 100:100 */    if (args.size() == 1)
/* 101:    */    {
/* 102:102 */      return evaluate(args.get(0), context.getNavigator());
/* 103:    */    }
/* 104:    */    
/* 106:106 */    throw new FunctionCallException("round() requires one argument.");
/* 107:    */  }
/* 108:    */  
/* 120:    */  public static Double evaluate(Object obj, Navigator nav)
/* 121:    */  {
/* 122:122 */    Double d = NumberFunction.evaluate(obj, nav);
/* 123:    */    
/* 125:125 */    if ((d.isNaN()) || (d.isInfinite()))
/* 126:    */    {
/* 127:127 */      return d;
/* 128:    */    }
/* 129:    */    
/* 130:130 */    double value = d.doubleValue();
/* 131:131 */    return new Double(Math.round(value));
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.RoundFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */