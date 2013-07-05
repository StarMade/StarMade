/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class ConcatFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  94 */     if (args.size() >= 2)
/*     */     {
/*  96 */       return evaluate(args, context.getNavigator());
/*     */     }
/*     */ 
/* 100 */     throw new FunctionCallException("concat() requires at least two arguments");
/*     */   }
/*     */ 
/*     */   public static String evaluate(List list, Navigator nav)
/*     */   {
/* 117 */     StringBuffer result = new StringBuffer();
/* 118 */     Iterator argIter = list.iterator();
/* 119 */     while (argIter.hasNext())
/*     */     {
/* 121 */       result.append(StringFunction.evaluate(argIter.next(), nav));
/*     */     }
/*     */ 
/* 125 */     return result.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ConcatFunction
 * JD-Core Version:    0.6.2
 */