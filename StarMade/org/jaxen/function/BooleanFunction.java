/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class BooleanFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 126 */     if (args.size() == 1)
/*     */     {
/* 128 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 131 */     throw new FunctionCallException("boolean() requires one argument");
/*     */   }
/*     */ 
/*     */   public static Boolean evaluate(Object obj, Navigator nav)
/*     */   {
/* 153 */     if ((obj instanceof List))
/*     */     {
/* 155 */       List list = (List)obj;
/*     */ 
/* 158 */       if (list.size() == 0)
/*     */       {
/* 160 */         return Boolean.FALSE;
/*     */       }
/*     */ 
/* 164 */       obj = list.get(0);
/*     */     }
/*     */ 
/* 171 */     if ((obj instanceof Boolean))
/*     */     {
/* 173 */       return (Boolean)obj;
/*     */     }
/*     */ 
/* 176 */     if ((obj instanceof Number))
/*     */     {
/* 178 */       double d = ((Number)obj).doubleValue();
/* 179 */       if ((d == 0.0D) || (Double.isNaN(d)))
/*     */       {
/* 181 */         return Boolean.FALSE;
/*     */       }
/* 183 */       return Boolean.TRUE;
/*     */     }
/*     */ 
/* 186 */     if ((obj instanceof String))
/*     */     {
/* 188 */       return ((String)obj).length() > 0 ? Boolean.TRUE : Boolean.FALSE;
/*     */     }
/*     */ 
/* 196 */     return obj != null ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.BooleanFunction
 * JD-Core Version:    0.6.2
 */