/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ public class CharUtils
/*     */ {
/*  32 */   private static final String[] CHAR_STRING_ARRAY = new String[''];
/*     */   public static final char LF = '\n';
/*     */   public static final char CR = '\r';
/*     */ 
/*     */   @Deprecated
/*     */   public static Character toCharacterObject(char ch)
/*     */   {
/*  88 */     return Character.valueOf(ch);
/*     */   }
/*     */ 
/*     */   public static Character toCharacterObject(String str)
/*     */   {
/* 109 */     if (StringUtils.isEmpty(str)) {
/* 110 */       return null;
/*     */     }
/* 112 */     return Character.valueOf(str.charAt(0));
/*     */   }
/*     */ 
/*     */   public static char toChar(Character ch)
/*     */   {
/* 130 */     if (ch == null) {
/* 131 */       throw new IllegalArgumentException("The Character must not be null");
/*     */     }
/* 133 */     return ch.charValue();
/*     */   }
/*     */ 
/*     */   public static char toChar(Character ch, char defaultValue)
/*     */   {
/* 150 */     if (ch == null) {
/* 151 */       return defaultValue;
/*     */     }
/* 153 */     return ch.charValue();
/*     */   }
/*     */ 
/*     */   public static char toChar(String str)
/*     */   {
/* 173 */     if (StringUtils.isEmpty(str)) {
/* 174 */       throw new IllegalArgumentException("The String must not be empty");
/*     */     }
/* 176 */     return str.charAt(0);
/*     */   }
/*     */ 
/*     */   public static char toChar(String str, char defaultValue)
/*     */   {
/* 195 */     if (StringUtils.isEmpty(str)) {
/* 196 */       return defaultValue;
/*     */     }
/* 198 */     return str.charAt(0);
/*     */   }
/*     */ 
/*     */   public static int toIntValue(char ch)
/*     */   {
/* 218 */     if (!isAsciiNumeric(ch)) {
/* 219 */       throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
/*     */     }
/* 221 */     return ch - '0';
/*     */   }
/*     */ 
/*     */   public static int toIntValue(char ch, int defaultValue)
/*     */   {
/* 240 */     if (!isAsciiNumeric(ch)) {
/* 241 */       return defaultValue;
/*     */     }
/* 243 */     return ch - '0';
/*     */   }
/*     */ 
/*     */   public static int toIntValue(Character ch)
/*     */   {
/* 263 */     if (ch == null) {
/* 264 */       throw new IllegalArgumentException("The character must not be null");
/*     */     }
/* 266 */     return toIntValue(ch.charValue());
/*     */   }
/*     */ 
/*     */   public static int toIntValue(Character ch, int defaultValue)
/*     */   {
/* 286 */     if (ch == null) {
/* 287 */       return defaultValue;
/*     */     }
/* 289 */     return toIntValue(ch.charValue(), defaultValue);
/*     */   }
/*     */ 
/*     */   public static String toString(char ch)
/*     */   {
/* 308 */     if (ch < '') {
/* 309 */       return CHAR_STRING_ARRAY[ch];
/*     */     }
/* 311 */     return new String(new char[] { ch });
/*     */   }
/*     */ 
/*     */   public static String toString(Character ch)
/*     */   {
/* 332 */     if (ch == null) {
/* 333 */       return null;
/*     */     }
/* 335 */     return toString(ch.charValue());
/*     */   }
/*     */ 
/*     */   public static String unicodeEscaped(char ch)
/*     */   {
/* 353 */     if (ch < '\020')
/* 354 */       return "\\u000" + Integer.toHexString(ch);
/* 355 */     if (ch < 'Ā')
/* 356 */       return "\\u00" + Integer.toHexString(ch);
/* 357 */     if (ch < 'က') {
/* 358 */       return "\\u0" + Integer.toHexString(ch);
/*     */     }
/* 360 */     return "\\u" + Integer.toHexString(ch);
/*     */   }
/*     */ 
/*     */   public static String unicodeEscaped(Character ch)
/*     */   {
/* 380 */     if (ch == null) {
/* 381 */       return null;
/*     */     }
/* 383 */     return unicodeEscaped(ch.charValue());
/*     */   }
/*     */ 
/*     */   public static boolean isAscii(char ch)
/*     */   {
/* 403 */     return ch < '';
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiPrintable(char ch)
/*     */   {
/* 422 */     return (ch >= ' ') && (ch < '');
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiControl(char ch)
/*     */   {
/* 441 */     return (ch < ' ') || (ch == '');
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiAlpha(char ch)
/*     */   {
/* 460 */     return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'));
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiAlphaUpper(char ch)
/*     */   {
/* 479 */     return (ch >= 'A') && (ch <= 'Z');
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiAlphaLower(char ch)
/*     */   {
/* 498 */     return (ch >= 'a') && (ch <= 'z');
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiNumeric(char ch)
/*     */   {
/* 517 */     return (ch >= '0') && (ch <= '9');
/*     */   }
/*     */ 
/*     */   public static boolean isAsciiAlphanumeric(char ch)
/*     */   {
/* 536 */     return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= '0') && (ch <= '9'));
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  54 */     for (char c = '\000'; c < CHAR_STRING_ARRAY.length; c = (char)(c + '\001'))
/*  55 */       CHAR_STRING_ARRAY[c] = String.valueOf(c);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharUtils
 * JD-Core Version:    0.6.2
 */