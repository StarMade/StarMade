/*    */ package org.jaxen.function.xslt;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.Function;
/*    */ import org.jaxen.FunctionCallException;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.function.StringFunction;
/*    */ 
/*    */ public class DocumentFunction
/*    */   implements Function
/*    */ {
/*    */   public Object call(Context context, List args)
/*    */     throws FunctionCallException
/*    */   {
/* 70 */     if (args.size() == 1)
/*    */     {
/* 72 */       Navigator nav = context.getNavigator();
/*    */ 
/* 74 */       String url = StringFunction.evaluate(args.get(0), nav);
/*    */ 
/* 77 */       return evaluate(url, nav);
/*    */     }
/*    */ 
/* 81 */     throw new FunctionCallException("document() requires one argument.");
/*    */   }
/*    */ 
/*    */   public static Object evaluate(String url, Navigator nav)
/*    */     throws FunctionCallException
/*    */   {
/* 87 */     return nav.getDocument(url);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.xslt.DocumentFunction
 * JD-Core Version:    0.6.2
 */