/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ 
/*     */ public class CountFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  88 */     if (args.size() == 1)
/*     */     {
/*  90 */       return evaluate(args.get(0));
/*     */     }
/*     */ 
/*  93 */     throw new FunctionCallException("count() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj)
/*     */     throws FunctionCallException
/*     */   {
/* 108 */     if ((obj instanceof List))
/*     */     {
/* 110 */       return new Double(((List)obj).size());
/*     */     }
/*     */ 
/* 113 */     throw new FunctionCallException("count() function can only be used for node-sets");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.CountFunction
 * JD-Core Version:    0.6.2
 */