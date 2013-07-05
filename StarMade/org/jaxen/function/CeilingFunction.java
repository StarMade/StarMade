/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class CeilingFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 104 */     if (args.size() == 1)
/*     */     {
/* 106 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 110 */     throw new FunctionCallException("ceiling() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */   {
/* 126 */     Double value = NumberFunction.evaluate(obj, nav);
/*     */ 
/* 129 */     return new Double(Math.ceil(value.doubleValue()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.CeilingFunction
 * JD-Core Version:    0.6.2
 */