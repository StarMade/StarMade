/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class StrLookup<V>
/*     */ {
/*  48 */   private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);
/*     */ 
/*  58 */   private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = lookup;
/*     */ 
/*     */   public static StrLookup<?> noneLookup()
/*     */   {
/*  68 */     return NONE_LOOKUP;
/*     */   }
/*     */ 
/*     */   public static StrLookup<String> systemPropertiesLookup()
/*     */   {
/*  83 */     return SYSTEM_PROPERTIES_LOOKUP;
/*     */   }
/*     */ 
/*     */   public static <V> StrLookup<V> mapLookup(Map<String, V> map)
/*     */   {
/*  97 */     return new MapStrLookup(map);
/*     */   }
/*     */ 
/*     */   public abstract String lookup(String paramString);
/*     */ 
/*     */   static
/*     */   {
/*  49 */     StrLookup lookup = null;
/*     */     try {
/*  51 */       Map propMap = System.getProperties();
/*     */ 
/*  53 */       Map properties = propMap;
/*  54 */       lookup = new MapStrLookup(properties);
/*     */     } catch (SecurityException ex) {
/*  56 */       lookup = NONE_LOOKUP;
/*     */     }
/*     */   }
/*     */ 
/*     */   static class MapStrLookup<V> extends StrLookup<V>
/*     */   {
/*     */     private final Map<String, V> map;
/*     */ 
/*     */     MapStrLookup(Map<String, V> map)
/*     */     {
/* 148 */       this.map = map;
/*     */     }
/*     */ 
/*     */     public String lookup(String key)
/*     */     {
/* 162 */       if (this.map == null) {
/* 163 */         return null;
/*     */       }
/* 165 */       Object obj = this.map.get(key);
/* 166 */       if (obj == null) {
/* 167 */         return null;
/*     */       }
/* 169 */       return obj.toString();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrLookup
 * JD-Core Version:    0.6.2
 */