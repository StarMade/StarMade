/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public abstract class StrMatcher
/*     */ {
/*  38 */   private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
/*     */ 
/*  42 */   private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
/*     */ 
/*  46 */   private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
/*     */ 
/*  51 */   private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
/*     */ 
/*  55 */   private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
/*     */ 
/*  59 */   private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
/*     */ 
/*  63 */   private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('"');
/*     */ 
/*  67 */   private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
/*     */ 
/*  71 */   private static final StrMatcher NONE_MATCHER = new NoMatcher();
/*     */ 
/*     */   public static StrMatcher commaMatcher()
/*     */   {
/*  81 */     return COMMA_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher tabMatcher()
/*     */   {
/*  90 */     return TAB_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher spaceMatcher()
/*     */   {
/*  99 */     return SPACE_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher splitMatcher()
/*     */   {
/* 109 */     return SPLIT_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher trimMatcher()
/*     */   {
/* 118 */     return TRIM_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher singleQuoteMatcher()
/*     */   {
/* 127 */     return SINGLE_QUOTE_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher doubleQuoteMatcher()
/*     */   {
/* 136 */     return DOUBLE_QUOTE_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher quoteMatcher()
/*     */   {
/* 145 */     return QUOTE_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher noneMatcher()
/*     */   {
/* 154 */     return NONE_MATCHER;
/*     */   }
/*     */ 
/*     */   public static StrMatcher charMatcher(char ch)
/*     */   {
/* 164 */     return new CharMatcher(ch);
/*     */   }
/*     */ 
/*     */   public static StrMatcher charSetMatcher(char[] chars)
/*     */   {
/* 174 */     if ((chars == null) || (chars.length == 0)) {
/* 175 */       return NONE_MATCHER;
/*     */     }
/* 177 */     if (chars.length == 1) {
/* 178 */       return new CharMatcher(chars[0]);
/*     */     }
/* 180 */     return new CharSetMatcher(chars);
/*     */   }
/*     */ 
/*     */   public static StrMatcher charSetMatcher(String chars)
/*     */   {
/* 190 */     if ((chars == null) || (chars.length() == 0)) {
/* 191 */       return NONE_MATCHER;
/*     */     }
/* 193 */     if (chars.length() == 1) {
/* 194 */       return new CharMatcher(chars.charAt(0));
/*     */     }
/* 196 */     return new CharSetMatcher(chars.toCharArray());
/*     */   }
/*     */ 
/*     */   public static StrMatcher stringMatcher(String str)
/*     */   {
/* 206 */     if (StringUtils.isEmpty(str)) {
/* 207 */       return NONE_MATCHER;
/*     */     }
/* 209 */     return new StringMatcher(str);
/*     */   }
/*     */ 
/*     */   public abstract int isMatch(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/*     */   public int isMatch(char[] buffer, int pos)
/*     */   {
/* 268 */     return isMatch(buffer, pos, 0, buffer.length);
/*     */   }
/*     */ 
/*     */   static final class TrimMatcher extends StrMatcher
/*     */   {
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/*     */     {
/* 432 */       return buffer[pos] <= ' ' ? 1 : 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class NoMatcher extends StrMatcher
/*     */   {
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/*     */     {
/* 404 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class StringMatcher extends StrMatcher
/*     */   {
/*     */     private final char[] chars;
/*     */ 
/*     */     StringMatcher(String str)
/*     */     {
/* 353 */       this.chars = str.toCharArray();
/*     */     }
/*     */ 
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/*     */     {
/* 367 */       int len = this.chars.length;
/* 368 */       if (pos + len > bufferEnd) {
/* 369 */         return 0;
/*     */       }
/* 371 */       for (int i = 0; i < this.chars.length; pos++) {
/* 372 */         if (this.chars[i] != buffer[pos])
/* 373 */           return 0;
/* 371 */         i++;
/*     */       }
/*     */ 
/* 376 */       return len;
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class CharMatcher extends StrMatcher
/*     */   {
/*     */     private final char ch;
/*     */ 
/*     */     CharMatcher(char ch)
/*     */     {
/* 320 */       this.ch = ch;
/*     */     }
/*     */ 
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/*     */     {
/* 334 */       return this.ch == buffer[pos] ? 1 : 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class CharSetMatcher extends StrMatcher
/*     */   {
/*     */     private final char[] chars;
/*     */ 
/*     */     CharSetMatcher(char[] chars)
/*     */     {
/* 286 */       this.chars = ((char[])chars.clone());
/* 287 */       Arrays.sort(this.chars);
/*     */     }
/*     */ 
/*     */     public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/*     */     {
/* 301 */       return Arrays.binarySearch(this.chars, buffer[pos]) >= 0 ? 1 : 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrMatcher
 * JD-Core Version:    0.6.2
 */