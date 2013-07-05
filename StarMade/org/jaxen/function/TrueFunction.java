/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ 
/*     */ public class TrueFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  87 */     if (args.size() == 0)
/*     */     {
/*  89 */       return evaluate();
/*     */     }
/*     */ 
/*  92 */     throw new FunctionCallException("true() requires no arguments.");
/*     */   }
/*     */ 
/*     */   public static Boolean evaluate()
/*     */   {
/* 102 */     return Boolean.TRUE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.TrueFunction
 * JD-Core Version:    0.6.2
 */