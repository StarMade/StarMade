/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class StartsWithFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  96 */     if (args.size() == 2)
/*     */     {
/*  98 */       return evaluate(args.get(0), args.get(1), context.getNavigator());
/*     */     }
/*     */ 
/* 103 */     throw new FunctionCallException("starts-with() requires two arguments.");
/*     */   }
/*     */ 
/*     */   public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/*     */   {
/* 124 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 127 */     String match = StringFunction.evaluate(matchArg, nav);
/*     */ 
/* 130 */     return str.startsWith(match) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StartsWithFunction
 * JD-Core Version:    0.6.2
 */