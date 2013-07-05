/*     */ package org.jaxen.function.ext;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.StringFunction;
/*     */ 
/*     */ public class LowerFunction extends LocaleFunctionSupport
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  81 */     Navigator navigator = context.getNavigator();
/*  82 */     int size = args.size();
/*  83 */     if (size > 0)
/*     */     {
/*  85 */       Object text = args.get(0);
/*  86 */       Locale locale = null;
/*  87 */       if (size > 1)
/*     */       {
/*  89 */         locale = getLocale(args.get(1), navigator);
/*     */       }
/*  91 */       return evaluate(text, locale, navigator);
/*     */     }
/*  93 */     throw new FunctionCallException("lower-case() requires at least one argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object strArg, Locale locale, Navigator nav)
/*     */   {
/* 109 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 113 */     if (locale == null) locale = Locale.ENGLISH;
/* 114 */     return str.toLowerCase(locale);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.LowerFunction
 * JD-Core Version:    0.6.2
 */