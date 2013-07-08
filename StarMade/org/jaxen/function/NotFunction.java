/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  88:    */public class NotFunction
/*  89:    */  implements Function
/*  90:    */{
/*  91:    */  public Object call(Context context, List args)
/*  92:    */    throws FunctionCallException
/*  93:    */  {
/*  94: 94 */    if (args.size() == 1)
/*  95:    */    {
/*  96: 96 */      return evaluate(args.get(0), context.getNavigator());
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    throw new FunctionCallException("not() requires one argument.");
/* 100:    */  }
/* 101:    */  
/* 114:    */  public static Boolean evaluate(Object obj, Navigator nav)
/* 115:    */  {
/* 116:116 */    return BooleanFunction.evaluate(obj, nav).booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/* 117:    */  }
/* 118:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NotFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */