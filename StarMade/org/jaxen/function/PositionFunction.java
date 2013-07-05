/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ 
/*     */ public class PositionFunction
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
/*  96 */     throw new FunctionCallException("position() does not take any arguments.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Context context)
/*     */   {
/* 112 */     return new Double(context.getPosition());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.PositionFunction
 * JD-Core Version:    0.6.2
 */