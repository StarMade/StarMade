/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class RoundFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 100 */     if (args.size() == 1)
/*     */     {
/* 102 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 106 */     throw new FunctionCallException("round() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */   {
/* 122 */     Double d = NumberFunction.evaluate(obj, nav);
/*     */ 
/* 125 */     if ((d.isNaN()) || (d.isInfinite()))
/*     */     {
/* 127 */       return d;
/*     */     }
/*     */ 
/* 130 */     double value = d.doubleValue();
/* 131 */     return new Double(Math.round(value));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.RoundFunction
 * JD-Core Version:    0.6.2
 */