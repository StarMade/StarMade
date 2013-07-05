/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class SumFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  93 */     if (args.size() == 1)
/*     */     {
/*  95 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/*  99 */     throw new FunctionCallException("sum() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 117 */     double sum = 0.0D;
/*     */ 
/* 119 */     if ((obj instanceof List))
/*     */     {
/* 121 */       Iterator nodeIter = ((List)obj).iterator();
/* 122 */       while (nodeIter.hasNext())
/*     */       {
/* 124 */         double term = NumberFunction.evaluate(nodeIter.next(), nav).doubleValue();
/*     */ 
/* 126 */         sum += term;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 131 */       throw new FunctionCallException("The argument to the sum function must be a node-set");
/*     */     }
/*     */ 
/* 134 */     return new Double(sum);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SumFunction
 * JD-Core Version:    0.6.2
 */