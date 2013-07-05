/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class ContainsFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  98 */     if (args.size() == 2)
/*     */     {
/* 100 */       return evaluate(args.get(0), args.get(1), context.getNavigator());
/*     */     }
/*     */ 
/* 105 */     throw new FunctionCallException("contains() requires two arguments.");
/*     */   }
/*     */ 
/*     */   public static Boolean evaluate(Object strArg, Object matchArg, Navigator nav)
/*     */   {
/* 125 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 128 */     String match = StringFunction.evaluate(matchArg, nav);
/*     */ 
/* 131 */     return str.indexOf(match) >= 0 ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ContainsFunction
 * JD-Core Version:    0.6.2
 */