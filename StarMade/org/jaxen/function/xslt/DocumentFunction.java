/*  1:   */package org.jaxen.function.xslt;
/*  2:   */
/*  3:   */import java.util.List;
/*  4:   */import org.jaxen.Context;
/*  5:   */import org.jaxen.Function;
/*  6:   */import org.jaxen.FunctionCallException;
/*  7:   */import org.jaxen.Navigator;
/*  8:   */import org.jaxen.function.StringFunction;
/*  9:   */
/* 64:   */public class DocumentFunction
/* 65:   */  implements Function
/* 66:   */{
/* 67:   */  public Object call(Context context, List args)
/* 68:   */    throws FunctionCallException
/* 69:   */  {
/* 70:70 */    if (args.size() == 1)
/* 71:   */    {
/* 72:72 */      Navigator nav = context.getNavigator();
/* 73:   */      
/* 74:74 */      String url = StringFunction.evaluate(args.get(0), nav);
/* 75:   */      
/* 77:77 */      return evaluate(url, nav);
/* 78:   */    }
/* 79:   */    
/* 81:81 */    throw new FunctionCallException("document() requires one argument.");
/* 82:   */  }
/* 83:   */  
/* 84:   */  public static Object evaluate(String url, Navigator nav)
/* 85:   */    throws FunctionCallException
/* 86:   */  {
/* 87:87 */    return nav.getDocument(url);
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.xslt.DocumentFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */