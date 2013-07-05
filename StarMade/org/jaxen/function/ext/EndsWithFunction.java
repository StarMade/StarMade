/*    */ package org.jaxen.function.ext;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.Function;
/*    */ import org.jaxen.FunctionCallException;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.function.StringFunction;
/*    */ 
/*    */ public class EndsWithFunction
/*    */   implements Function
/*    */ {
/*    */   public Object call(Context context, List args)
/*    */     throws FunctionCallException
/*    */   {
/* 69 */     if (args.size() == 2)
/*    */     {
/* 71 */       return evaluate(args.get(0), args.get(1), context.getNavigator());
/*    */     }
/*    */ 
/* 76 */     throw new FunctionCallException("ends-with() requires two arguments.");
/*    */   }
/*    */ 
/*    */   public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/*    */   {
/* 83 */     String str = StringFunction.evaluate(strArg, nav);
/*    */ 
/* 86 */     String match = StringFunction.evaluate(matchArg, nav);
/*    */ 
/* 89 */     return str.endsWith(match) ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.EndsWithFunction
 * JD-Core Version:    0.6.2
 */