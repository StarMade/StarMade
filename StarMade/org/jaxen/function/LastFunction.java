/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */
/*  85:    */public class LastFunction
/*  86:    */  implements Function
/*  87:    */{
/*  88:    */  public Object call(Context context, List args)
/*  89:    */    throws FunctionCallException
/*  90:    */  {
/*  91: 91 */    if (args.size() == 0)
/*  92:    */    {
/*  93: 93 */      return evaluate(context);
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    throw new FunctionCallException("last() requires no arguments.");
/*  97:    */  }
/*  98:    */  
/* 109:    */  public static Double evaluate(Context context)
/* 110:    */  {
/* 111:111 */    return new Double(context.getSize());
/* 112:    */  }
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LastFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */