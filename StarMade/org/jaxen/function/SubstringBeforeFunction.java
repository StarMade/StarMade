/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  96:    */public class SubstringBeforeFunction
/*  97:    */  implements Function
/*  98:    */{
/*  99:    */  public Object call(Context context, List args)
/* 100:    */    throws FunctionCallException
/* 101:    */  {
/* 102:102 */    if (args.size() == 2)
/* 103:    */    {
/* 104:104 */      return evaluate(args.get(0), args.get(1), context.getNavigator());
/* 105:    */    }
/* 106:    */    
/* 109:109 */    throw new FunctionCallException("substring-before() requires two arguments.");
/* 110:    */  }
/* 111:    */  
/* 128:    */  public static String evaluate(Object strArg, Object matchArg, Navigator nav)
/* 129:    */  {
/* 130:130 */    String str = StringFunction.evaluate(strArg, nav);
/* 131:    */    
/* 133:133 */    String match = StringFunction.evaluate(matchArg, nav);
/* 134:    */    
/* 136:136 */    int loc = str.indexOf(match);
/* 137:    */    
/* 138:138 */    if (loc < 0)
/* 139:    */    {
/* 140:140 */      return "";
/* 141:    */    }
/* 142:    */    
/* 143:143 */    return str.substring(0, loc);
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SubstringBeforeFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */