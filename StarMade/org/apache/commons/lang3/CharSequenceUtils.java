/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ public class CharSequenceUtils
/*     */ {
/*     */   public static CharSequence subSequence(CharSequence cs, int start)
/*     */   {
/*  56 */     return cs == null ? null : cs.subSequence(start, cs.length());
/*     */   }
/*     */ 
/*     */   static int indexOf(CharSequence cs, int searchChar, int start)
/*     */   {
/*  70 */     if ((cs instanceof String)) {
/*  71 */       return ((String)cs).indexOf(searchChar, start);
/*     */     }
/*  73 */     int sz = cs.length();
/*  74 */     if (start < 0) {
/*  75 */       start = 0;
/*     */     }
/*  77 */     for (int i = start; i < sz; i++) {
/*  78 */       if (cs.charAt(i) == searchChar) {
/*  79 */         return i;
/*     */       }
/*     */     }
/*  82 */     return -1;
/*     */   }
/*     */ 
/*     */   static int indexOf(CharSequence cs, CharSequence searchChar, int start)
/*     */   {
/*  95 */     return cs.toString().indexOf(searchChar.toString(), start);
/*     */   }
/*     */ 
/*     */   static int lastIndexOf(CharSequence cs, int searchChar, int start)
/*     */   {
/* 117 */     if ((cs instanceof String)) {
/* 118 */       return ((String)cs).lastIndexOf(searchChar, start);
/*     */     }
/* 120 */     int sz = cs.length();
/* 121 */     if (start < 0) {
/* 122 */       return -1;
/*     */     }
/* 124 */     if (start >= sz) {
/* 125 */       start = sz - 1;
/*     */     }
/* 127 */     for (int i = start; i >= 0; i--) {
/* 128 */       if (cs.charAt(i) == searchChar) {
/* 129 */         return i;
/*     */       }
/*     */     }
/* 132 */     return -1;
/*     */   }
/*     */ 
/*     */   static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start)
/*     */   {
/* 145 */     return cs.toString().lastIndexOf(searchChar.toString(), start);
/*     */   }
/*     */ 
/*     */   static char[] toCharArray(CharSequence cs)
/*     */   {
/* 164 */     if ((cs instanceof String)) {
/* 165 */       return ((String)cs).toCharArray();
/*     */     }
/* 167 */     int sz = cs.length();
/* 168 */     char[] array = new char[cs.length()];
/* 169 */     for (int i = 0; i < sz; i++) {
/* 170 */       array[i] = cs.charAt(i);
/*     */     }
/* 172 */     return array;
/*     */   }
/*     */ 
/*     */   static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length)
/*     */   {
/* 189 */     if (((cs instanceof String)) && ((substring instanceof String))) {
/* 190 */       return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
/*     */     }
/*     */ 
/* 193 */     return cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSequenceUtils
 * JD-Core Version:    0.6.2
 */