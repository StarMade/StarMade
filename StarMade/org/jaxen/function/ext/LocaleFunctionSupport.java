/*     */ package org.jaxen.function.ext;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.StringFunction;
/*     */ 
/*     */ public abstract class LocaleFunctionSupport
/*     */   implements Function
/*     */ {
/*     */   protected Locale getLocale(Object value, Navigator navigator)
/*     */   {
/*  84 */     if ((value instanceof Locale))
/*     */     {
/*  86 */       return (Locale)value;
/*     */     }
/*  88 */     if ((value instanceof List))
/*     */     {
/*  90 */       List list = (List)value;
/*  91 */       if (!list.isEmpty())
/*     */       {
/*  93 */         return getLocale(list.get(0), navigator);
/*     */       }
/*     */     }
/*     */     else {
/*  97 */       String text = StringFunction.evaluate(value, navigator);
/*  98 */       if ((text != null) && (text.length() > 0))
/*     */       {
/* 100 */         return findLocale(text);
/*     */       }
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   protected Locale findLocale(String localeText)
/*     */   {
/* 116 */     StringTokenizer tokens = new StringTokenizer(localeText, "-");
/* 117 */     if (tokens.hasMoreTokens())
/*     */     {
/* 119 */       String language = tokens.nextToken();
/* 120 */       if (!tokens.hasMoreTokens())
/*     */       {
/* 122 */         return findLocaleForLanguage(language);
/*     */       }
/*     */ 
/* 126 */       String country = tokens.nextToken();
/* 127 */       if (!tokens.hasMoreTokens())
/*     */       {
/* 129 */         return new Locale(language, country);
/*     */       }
/*     */ 
/* 133 */       String variant = tokens.nextToken();
/* 134 */       return new Locale(language, country, variant);
/*     */     }
/*     */ 
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   protected Locale findLocaleForLanguage(String language)
/*     */   {
/* 150 */     Locale[] locales = Locale.getAvailableLocales();
/* 151 */     int i = 0; for (int size = locales.length; i < size; i++)
/*     */     {
/* 153 */       Locale locale = locales[i];
/* 154 */       if (language.equals(locale.getLanguage()))
/*     */       {
/* 156 */         String country = locale.getCountry();
/* 157 */         if ((country == null) || (country.length() == 0))
/*     */         {
/* 159 */           String variant = locale.getVariant();
/* 160 */           if ((variant == null) || (variant.length() == 0))
/*     */           {
/* 162 */             return locale;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 167 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.LocaleFunctionSupport
 * JD-Core Version:    0.6.2
 */