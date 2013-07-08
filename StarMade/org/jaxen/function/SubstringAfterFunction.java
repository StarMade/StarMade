/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  94:    */public class SubstringAfterFunction
/*  95:    */  implements Function
/*  96:    */{
/*  97:    */  public Object call(Context context, List args)
/*  98:    */    throws FunctionCallException
/*  99:    */  {
/* 100:100 */    if (args.size() == 2)
/* 101:    */    {
/* 102:102 */      return evaluate(args.get(0), args.get(1), context.getNavigator());
/* 103:    */    }
/* 104:    */    
/* 107:107 */    throw new FunctionCallException("substring-after() requires two arguments.");
/* 108:    */  }
/* 109:    */  
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
/* 143:143 */    return str.substring(loc + match.length());
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SubstringAfterFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */