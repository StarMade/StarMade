/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class SubstringBeforeFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 102 */     if (args.size() == 2)
/*     */     {
/* 104 */       return evaluate(args.get(0), args.get(1), context.getNavigator());
/*     */     }
/*     */ 
/* 109 */     throw new FunctionCallException("substring-before() requires two arguments.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object strArg, Object matchArg, Navigator nav)
/*     */   {
/* 130 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 133 */     String match = StringFunction.evaluate(matchArg, nav);
/*     */ 
/* 136 */     int loc = str.indexOf(match);
/*     */ 
/* 138 */     if (loc < 0)
/*     */     {
/* 140 */       return "";
/*     */     }
/*     */ 
/* 143 */     return str.substring(0, loc);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SubstringBeforeFunction
 * JD-Core Version:    0.6.2
 */