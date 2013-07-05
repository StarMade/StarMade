/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NumberFunction
/*     */   implements Function
/*     */ {
/* 130 */   private static final Double NaN = new Double((0.0D / 0.0D));
/*     */ 
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 154 */     if (args.size() == 1)
/*     */     {
/* 156 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/* 158 */     if (args.size() == 0)
/*     */     {
/* 160 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 163 */     throw new FunctionCallException("number() takes at most one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */   {
/* 177 */     if ((obj instanceof Double))
/*     */     {
/* 179 */       return (Double)obj;
/*     */     }
/* 181 */     if ((obj instanceof String))
/*     */     {
/* 183 */       String str = (String)obj;
/*     */       try
/*     */       {
/* 186 */         return new Double(str);
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/* 191 */         return NaN;
/*     */       }
/*     */     }
/* 194 */     if (((obj instanceof List)) || ((obj instanceof Iterator)))
/*     */     {
/* 196 */       return evaluate(StringFunction.evaluate(obj, nav), nav);
/*     */     }
/* 198 */     if ((nav.isElement(obj)) || (nav.isAttribute(obj)) || (nav.isText(obj)) || (nav.isComment(obj)) || (nav.isProcessingInstruction(obj)) || (nav.isDocument(obj)) || (nav.isNamespace(obj)))
/*     */     {
/* 202 */       return evaluate(StringFunction.evaluate(obj, nav), nav);
/*     */     }
/* 204 */     if ((obj instanceof Boolean))
/*     */     {
/* 206 */       if (obj == Boolean.TRUE)
/*     */       {
/* 208 */         return new Double(1.0D);
/*     */       }
/*     */ 
/* 212 */       return new Double(0.0D);
/*     */     }
/*     */ 
/* 215 */     return NaN;
/*     */   }
/*     */ 
/*     */   public static boolean isNaN(double val)
/*     */   {
/* 227 */     return Double.isNaN(val);
/*     */   }
/*     */ 
/*     */   public static boolean isNaN(Double val)
/*     */   {
/* 239 */     return val.equals(NaN);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NumberFunction
 * JD-Core Version:    0.6.2
 */