/*     */ package org.apache.commons.lang3.time;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.Format;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ 
/*     */ abstract class FormatCache<F extends Format>
/*     */ {
/*     */   static final int NONE = -1;
/*     */   private final ConcurrentMap<MultipartKey, F> cInstanceCache;
/*     */   private final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache;
/*     */ 
/*     */   FormatCache()
/*     */   {
/*  41 */     this.cInstanceCache = new ConcurrentHashMap(7);
/*     */ 
/*  44 */     this.cDateTimeInstanceCache = new ConcurrentHashMap(7);
/*     */   }
/*     */ 
/*     */   public F getInstance()
/*     */   {
/*  54 */     return getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), TimeZone.getDefault(), Locale.getDefault());
/*     */   }
/*     */ 
/*     */   public F getInstance(String pattern, TimeZone timeZone, Locale locale)
/*     */   {
/*  70 */     if (pattern == null) {
/*  71 */       throw new NullPointerException("pattern must not be null");
/*     */     }
/*  73 */     if (timeZone == null) {
/*  74 */       timeZone = TimeZone.getDefault();
/*     */     }
/*  76 */     if (locale == null) {
/*  77 */       locale = Locale.getDefault();
/*     */     }
/*  79 */     MultipartKey key = new MultipartKey(new Object[] { pattern, timeZone, locale });
/*  80 */     Format format = (Format)this.cInstanceCache.get(key);
/*  81 */     if (format == null) {
/*  82 */       format = createInstance(pattern, timeZone, locale);
/*  83 */       Format previousValue = (Format)this.cInstanceCache.putIfAbsent(key, format);
/*  84 */       if (previousValue != null)
/*     */       {
/*  87 */         format = previousValue;
/*     */       }
/*     */     }
/*  90 */     return format;
/*     */   }
/*     */ 
/*     */   protected abstract F createInstance(String paramString, TimeZone paramTimeZone, Locale paramLocale);
/*     */ 
/*     */   public F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale)
/*     */   {
/* 120 */     if (locale == null) {
/* 121 */       locale = Locale.getDefault();
/*     */     }
/* 123 */     MultipartKey key = new MultipartKey(new Object[] { dateStyle, timeStyle, locale });
/*     */ 
/* 125 */     String pattern = (String)this.cDateTimeInstanceCache.get(key);
/* 126 */     if (pattern == null) {
/*     */       try
/*     */       {
/*     */         DateFormat formatter;
/*     */         DateFormat formatter;
/* 129 */         if (dateStyle == null) {
/* 130 */           formatter = DateFormat.getTimeInstance(timeStyle.intValue(), locale);
/*     */         }
/*     */         else
/*     */         {
/*     */           DateFormat formatter;
/* 132 */           if (timeStyle == null) {
/* 133 */             formatter = DateFormat.getDateInstance(dateStyle.intValue(), locale);
/*     */           }
/*     */           else
/* 136 */             formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), locale);
/*     */         }
/* 138 */         pattern = ((SimpleDateFormat)formatter).toPattern();
/* 139 */         String previous = (String)this.cDateTimeInstanceCache.putIfAbsent(key, pattern);
/* 140 */         if (previous != null)
/*     */         {
/* 144 */           pattern = previous;
/*     */         }
/*     */       } catch (ClassCastException ex) {
/* 147 */         throw new IllegalArgumentException("No date time pattern for locale: " + locale);
/*     */       }
/*     */     }
/*     */ 
/* 151 */     return getInstance(pattern, timeZone, locale);
/*     */   }
/*     */ 
/*     */   private static class MultipartKey
/*     */   {
/*     */     private final Object[] keys;
/*     */     private int hashCode;
/*     */ 
/*     */     public MultipartKey(Object[] keys)
/*     */     {
/* 167 */       this.keys = keys;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 175 */       if (this == obj) {
/* 176 */         return true;
/*     */       }
/* 178 */       if (!(obj instanceof MultipartKey)) {
/* 179 */         return false;
/*     */       }
/* 181 */       return Arrays.equals(this.keys, ((MultipartKey)obj).keys);
/*     */     }
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 189 */       if (this.hashCode == 0) {
/* 190 */         int rc = 0;
/* 191 */         for (Object key : this.keys) {
/* 192 */           if (key != null) {
/* 193 */             rc = rc * 7 + key.hashCode();
/*     */           }
/*     */         }
/* 196 */         this.hashCode = rc;
/*     */       }
/* 198 */       return this.hashCode;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.FormatCache
 * JD-Core Version:    0.6.2
 */