/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ 
/*     */ public class LastFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  91 */     if (args.size() == 0)
/*     */     {
/*  93 */       return evaluate(context);
/*     */     }
/*     */ 
/*  96 */     throw new FunctionCallException("last() requires no arguments.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Context context)
/*     */   {
/* 111 */     return new Double(context.getSize());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LastFunction
 * JD-Core Version:    0.6.2
 */