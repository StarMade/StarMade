/*     */ package org.jaxen.function.ext;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.StringFunction;
/*     */ 
/*     */ public class UpperFunction extends LocaleFunctionSupport
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  80 */     Navigator navigator = context.getNavigator();
/*  81 */     int size = args.size();
/*  82 */     if (size > 0)
/*     */     {
/*  84 */       Object text = args.get(0);
/*  85 */       Locale locale = null;
/*  86 */       if (size > 1)
/*     */       {
/*  88 */         locale = getLocale(args.get(1), navigator);
/*     */       }
/*  90 */       return evaluate(text, locale, navigator);
/*     */     }
/*  92 */     throw new FunctionCallException("upper-case() requires at least one argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object strArg, Locale locale, Navigator nav)
/*     */   {
/* 108 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 112 */     if (locale == null) locale = Locale.ENGLISH;
/* 113 */     return str.toUpperCase(locale);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.UpperFunction
 * JD-Core Version:    0.6.2
 */