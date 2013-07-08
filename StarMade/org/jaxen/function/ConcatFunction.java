/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.Function;
/*   7:    */import org.jaxen.FunctionCallException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */
/*  88:    */public class ConcatFunction
/*  89:    */  implements Function
/*  90:    */{
/*  91:    */  public Object call(Context context, List args)
/*  92:    */    throws FunctionCallException
/*  93:    */  {
/*  94: 94 */    if (args.size() >= 2)
/*  95:    */    {
/*  96: 96 */      return evaluate(args, context.getNavigator());
/*  97:    */    }
/*  98:    */    
/* 100:100 */    throw new FunctionCallException("concat() requires at least two arguments");
/* 101:    */  }
/* 102:    */  
/* 115:    */  public static String evaluate(List list, Navigator nav)
/* 116:    */  {
/* 117:117 */    StringBuffer result = new StringBuffer();
/* 118:118 */    Iterator argIter = list.iterator();
/* 119:119 */    while (argIter.hasNext())
/* 120:    */    {
/* 121:121 */      result.append(StringFunction.evaluate(argIter.next(), nav));
/* 122:    */    }
/* 123:    */    
/* 125:125 */    return result.toString();
/* 126:    */  }
/* 127:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ConcatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */