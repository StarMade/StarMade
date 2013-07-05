/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ 
/*     */ public class LocaleUtils
/*     */ {
/*  42 */   private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
/*     */ 
/*  46 */   private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();
/*     */ 
/*     */   public static Locale toLocale(String str)
/*     */   {
/*  89 */     if (str == null) {
/*  90 */       return null;
/*     */     }
/*  92 */     int len = str.length();
/*  93 */     if ((len != 2) && (len != 5) && (len < 7)) {
/*  94 */       throw new IllegalArgumentException("Invalid locale format: " + str);
/*     */     }
/*  96 */     char ch0 = str.charAt(0);
/*  97 */     char ch1 = str.charAt(1);
/*  98 */     if ((ch0 < 'a') || (ch0 > 'z') || (ch1 < 'a') || (ch1 > 'z')) {
/*  99 */       throw new IllegalArgumentException("Invalid locale format: " + str);
/*     */     }
/* 101 */     if (len == 2) {
/* 102 */       return new Locale(str, "");
/*     */     }
/* 104 */     if (str.charAt(2) != '_') {
/* 105 */       throw new IllegalArgumentException("Invalid locale format: " + str);
/*     */     }
/* 107 */     char ch3 = str.charAt(3);
/* 108 */     if (ch3 == '_') {
/* 109 */       return new Locale(str.substring(0, 2), "", str.substring(4));
/*     */     }
/* 111 */     char ch4 = str.charAt(4);
/* 112 */     if ((ch3 < 'A') || (ch3 > 'Z') || (ch4 < 'A') || (ch4 > 'Z')) {
/* 113 */       throw new IllegalArgumentException("Invalid locale format: " + str);
/*     */     }
/* 115 */     if (len == 5) {
/* 116 */       return new Locale(str.substring(0, 2), str.substring(3, 5));
/*     */     }
/* 118 */     if (str.charAt(5) != '_') {
/* 119 */       throw new IllegalArgumentException("Invalid locale format: " + str);
/*     */     }
/* 121 */     return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
/*     */   }
/*     */ 
/*     */   public static List<Locale> localeLookupList(Locale locale)
/*     */   {
/* 140 */     return localeLookupList(locale, locale);
/*     */   }
/*     */ 
/*     */   public static List<Locale> localeLookupList(Locale locale, Locale defaultLocale)
/*     */   {
/* 162 */     List list = new ArrayList(4);
/* 163 */     if (locale != null) {
/* 164 */       list.add(locale);
/* 165 */       if (locale.getVariant().length() > 0) {
/* 166 */         list.add(new Locale(locale.getLanguage(), locale.getCountry()));
/*     */       }
/* 168 */       if (locale.getCountry().length() > 0) {
/* 169 */         list.add(new Locale(locale.getLanguage(), ""));
/*     */       }
/* 171 */       if (!list.contains(defaultLocale)) {
/* 172 */         list.add(defaultLocale);
/*     */       }
/*     */     }
/* 175 */     return Collections.unmodifiableList(list);
/*     */   }
/*     */ 
/*     */   public static List<Locale> availableLocaleList()
/*     */   {
/* 189 */     return SyncAvoid.AVAILABLE_LOCALE_LIST;
/*     */   }
/*     */ 
/*     */   public static Set<Locale> availableLocaleSet()
/*     */   {
/* 203 */     return SyncAvoid.AVAILABLE_LOCALE_SET;
/*     */   }
/*     */ 
/*     */   public static boolean isAvailableLocale(Locale locale)
/*     */   {
/* 214 */     return availableLocaleList().contains(locale);
/*     */   }
/*     */ 
/*     */   public static List<Locale> languagesByCountry(String countryCode)
/*     */   {
/* 228 */     if (countryCode == null) {
/* 229 */       return Collections.emptyList();
/*     */     }
/* 231 */     List langs = (List)cLanguagesByCountry.get(countryCode);
/* 232 */     if (langs == null) {
/* 233 */       langs = new ArrayList();
/* 234 */       List locales = availableLocaleList();
/* 235 */       for (int i = 0; i < locales.size(); i++) {
/* 236 */         Locale locale = (Locale)locales.get(i);
/* 237 */         if ((countryCode.equals(locale.getCountry())) && (locale.getVariant().length() == 0))
/*     */         {
/* 239 */           langs.add(locale);
/*     */         }
/*     */       }
/* 242 */       langs = Collections.unmodifiableList(langs);
/* 243 */       cLanguagesByCountry.putIfAbsent(countryCode, langs);
/* 244 */       langs = (List)cLanguagesByCountry.get(countryCode);
/*     */     }
/* 246 */     return langs;
/*     */   }
/*     */ 
/*     */   public static List<Locale> countriesByLanguage(String languageCode)
/*     */   {
/* 260 */     if (languageCode == null) {
/* 261 */       return Collections.emptyList();
/*     */     }
/* 263 */     List countries = (List)cCountriesByLanguage.get(languageCode);
/* 264 */     if (countries == null) {
/* 265 */       countries = new ArrayList();
/* 266 */       List locales = availableLocaleList();
/* 267 */       for (int i = 0; i < locales.size(); i++) {
/* 268 */         Locale locale = (Locale)locales.get(i);
/* 269 */         if ((languageCode.equals(locale.getLanguage())) && (locale.getCountry().length() != 0) && (locale.getVariant().length() == 0))
/*     */         {
/* 272 */           countries.add(locale);
/*     */         }
/*     */       }
/* 275 */       countries = Collections.unmodifiableList(countries);
/* 276 */       cCountriesByLanguage.putIfAbsent(languageCode, countries);
/* 277 */       countries = (List)cCountriesByLanguage.get(languageCode);
/*     */     }
/* 279 */     return countries;
/*     */   }
/*     */ 
/*     */   static class SyncAvoid
/*     */   {
/* 292 */     private static List<Locale> AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(list);
/* 293 */     private static Set<Locale> AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet(LocaleUtils.availableLocaleList()));
/*     */ 
/*     */     static
/*     */     {
/* 291 */       List list = new ArrayList(Arrays.asList(Locale.getAvailableLocales()));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.LocaleUtils
 * JD-Core Version:    0.6.2
 */