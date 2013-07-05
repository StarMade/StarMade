/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NotFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  94 */     if (args.size() == 1)
/*     */     {
/*  96 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/*  99 */     throw new FunctionCallException("not() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Boolean evaluate(Object obj, Navigator nav)
/*     */   {
/* 116 */     return BooleanFunction.evaluate(obj, nav).booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NotFunction
 * JD-Core Version:    0.6.2
 */