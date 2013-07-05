/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class FloorFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 101 */     if (args.size() == 1)
/*     */     {
/* 103 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 107 */     throw new FunctionCallException("floor() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */   {
/* 123 */     Double value = NumberFunction.evaluate(obj, nav);
/*     */ 
/* 126 */     return new Double(Math.floor(value.doubleValue()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.FloorFunction
 * JD-Core Version:    0.6.2
 */