/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ public class CharSetUtils
/*     */ {
/*     */   public static String squeeze(String str, String[] set)
/*     */   {
/*  65 */     if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/*  66 */       return str;
/*     */     }
/*  68 */     CharSet chars = CharSet.getInstance(set);
/*  69 */     StringBuilder buffer = new StringBuilder(str.length());
/*  70 */     char[] chrs = str.toCharArray();
/*  71 */     int sz = chrs.length;
/*  72 */     char lastChar = ' ';
/*  73 */     char ch = ' ';
/*  74 */     for (int i = 0; i < sz; i++) {
/*  75 */       ch = chrs[i];
/*     */ 
/*  77 */       if ((ch != lastChar) || (i == 0) || (!chars.contains(ch)))
/*     */       {
/*  80 */         buffer.append(ch);
/*  81 */         lastChar = ch;
/*     */       }
/*     */     }
/*  83 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public static int count(String str, String[] set)
/*     */   {
/* 107 */     if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/* 108 */       return 0;
/*     */     }
/* 110 */     CharSet chars = CharSet.getInstance(set);
/* 111 */     int count = 0;
/* 112 */     for (char c : str.toCharArray()) {
/* 113 */       if (chars.contains(c)) {
/* 114 */         count++;
/*     */       }
/*     */     }
/* 117 */     return count;
/*     */   }
/*     */ 
/*     */   public static String keep(String str, String[] set)
/*     */   {
/* 142 */     if (str == null) {
/* 143 */       return null;
/*     */     }
/* 145 */     if ((str.length() == 0) || (deepEmpty(set))) {
/* 146 */       return "";
/*     */     }
/* 148 */     return modify(str, set, true);
/*     */   }
/*     */ 
/*     */   public static String delete(String str, String[] set)
/*     */   {
/* 172 */     if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/* 173 */       return str;
/*     */     }
/* 175 */     return modify(str, set, false);
/*     */   }
/*     */ 
/*     */   private static String modify(String str, String[] set, boolean expect)
/*     */   {
/* 188 */     CharSet chars = CharSet.getInstance(set);
/* 189 */     StringBuilder buffer = new StringBuilder(str.length());
/* 190 */     char[] chrs = str.toCharArray();
/* 191 */     int sz = chrs.length;
/* 192 */     for (int i = 0; i < sz; i++) {
/* 193 */       if (chars.contains(chrs[i]) == expect) {
/* 194 */         buffer.append(chrs[i]);
/*     */       }
/*     */     }
/* 197 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   private static boolean deepEmpty(String[] strings)
/*     */   {
/* 208 */     if (strings != null) {
/* 209 */       for (String s : strings) {
/* 210 */         if (StringUtils.isNotEmpty(s)) {
/* 211 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 215 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSetUtils
 * JD-Core Version:    0.6.2
 */