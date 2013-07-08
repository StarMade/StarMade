/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */
/*  81:    */public class TrueFunction
/*  82:    */  implements Function
/*  83:    */{
/*  84:    */  public Object call(Context context, List args)
/*  85:    */    throws FunctionCallException
/*  86:    */  {
/*  87: 87 */    if (args.size() == 0)
/*  88:    */    {
/*  89: 89 */      return evaluate();
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    throw new FunctionCallException("true() requires no arguments.");
/*  93:    */  }
/*  94:    */  
/* 100:    */  public static Boolean evaluate()
/* 101:    */  {
/* 102:102 */    return Boolean.TRUE;
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.TrueFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */