/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */
/*  82:    */public class CountFunction
/*  83:    */  implements Function
/*  84:    */{
/*  85:    */  public Object call(Context context, List args)
/*  86:    */    throws FunctionCallException
/*  87:    */  {
/*  88: 88 */    if (args.size() == 1)
/*  89:    */    {
/*  90: 90 */      return evaluate(args.get(0));
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    throw new FunctionCallException("count() requires one argument.");
/*  94:    */  }
/*  95:    */  
/* 105:    */  public static Double evaluate(Object obj)
/* 106:    */    throws FunctionCallException
/* 107:    */  {
/* 108:108 */    if ((obj instanceof List))
/* 109:    */    {
/* 110:110 */      return new Double(((List)obj).size());
/* 111:    */    }
/* 112:    */    
/* 113:113 */    throw new FunctionCallException("count() function can only be used for node-sets");
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.CountFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */