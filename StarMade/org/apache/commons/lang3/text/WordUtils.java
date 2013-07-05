/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.SystemUtils;
/*     */ 
/*     */ public class WordUtils
/*     */ {
/*     */   public static String wrap(String str, int wrapLength)
/*     */   {
/*  67 */     return wrap(str, wrapLength, null, false);
/*     */   }
/*     */ 
/*     */   public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords)
/*     */   {
/*  89 */     if (str == null) {
/*  90 */       return null;
/*     */     }
/*  92 */     if (newLineStr == null) {
/*  93 */       newLineStr = SystemUtils.LINE_SEPARATOR;
/*     */     }
/*  95 */     if (wrapLength < 1) {
/*  96 */       wrapLength = 1;
/*     */     }
/*  98 */     int inputLineLength = str.length();
/*  99 */     int offset = 0;
/* 100 */     StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
/*     */ 
/* 102 */     while (inputLineLength - offset > wrapLength) {
/* 103 */       if (str.charAt(offset) == ' ') {
/* 104 */         offset++;
/*     */       }
/*     */       else {
/* 107 */         int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);
/*     */ 
/* 109 */         if (spaceToWrapAt >= offset)
/*     */         {
/* 111 */           wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 112 */           wrappedLine.append(newLineStr);
/* 113 */           offset = spaceToWrapAt + 1;
/*     */         }
/* 117 */         else if (wrapLongWords)
/*     */         {
/* 119 */           wrappedLine.append(str.substring(offset, wrapLength + offset));
/* 120 */           wrappedLine.append(newLineStr);
/* 121 */           offset += wrapLength;
/*     */         }
/*     */         else {
/* 124 */           spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
/* 125 */           if (spaceToWrapAt >= 0) {
/* 126 */             wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 127 */             wrappedLine.append(newLineStr);
/* 128 */             offset = spaceToWrapAt + 1;
/*     */           } else {
/* 130 */             wrappedLine.append(str.substring(offset));
/* 131 */             offset = inputLineLength;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 138 */     wrappedLine.append(str.substring(offset));
/*     */ 
/* 140 */     return wrappedLine.toString();
/*     */   }
/*     */ 
/*     */   public static String capitalize(String str)
/*     */   {
/* 168 */     return capitalize(str, null);
/*     */   }
/*     */ 
/*     */   public static String capitalize(String str, char[] delimiters)
/*     */   {
/* 201 */     int delimLen = delimiters == null ? -1 : delimiters.length;
/* 202 */     if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 203 */       return str;
/*     */     }
/* 205 */     char[] buffer = str.toCharArray();
/* 206 */     boolean capitalizeNext = true;
/* 207 */     for (int i = 0; i < buffer.length; i++) {
/* 208 */       char ch = buffer[i];
/* 209 */       if (isDelimiter(ch, delimiters)) {
/* 210 */         capitalizeNext = true;
/* 211 */       } else if (capitalizeNext) {
/* 212 */         buffer[i] = Character.toTitleCase(ch);
/* 213 */         capitalizeNext = false;
/*     */       }
/*     */     }
/* 216 */     return new String(buffer);
/*     */   }
/*     */ 
/*     */   public static String capitalizeFully(String str)
/*     */   {
/* 240 */     return capitalizeFully(str, null);
/*     */   }
/*     */ 
/*     */   public static String capitalizeFully(String str, char[] delimiters)
/*     */   {
/* 270 */     int delimLen = delimiters == null ? -1 : delimiters.length;
/* 271 */     if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 272 */       return str;
/*     */     }
/* 274 */     str = str.toLowerCase();
/* 275 */     return capitalize(str, delimiters);
/*     */   }
/*     */ 
/*     */   public static String uncapitalize(String str)
/*     */   {
/* 297 */     return uncapitalize(str, null);
/*     */   }
/*     */ 
/*     */   public static String uncapitalize(String str, char[] delimiters)
/*     */   {
/* 326 */     int delimLen = delimiters == null ? -1 : delimiters.length;
/* 327 */     if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 328 */       return str;
/*     */     }
/* 330 */     char[] buffer = str.toCharArray();
/* 331 */     boolean uncapitalizeNext = true;
/* 332 */     for (int i = 0; i < buffer.length; i++) {
/* 333 */       char ch = buffer[i];
/* 334 */       if (isDelimiter(ch, delimiters)) {
/* 335 */         uncapitalizeNext = true;
/* 336 */       } else if (uncapitalizeNext) {
/* 337 */         buffer[i] = Character.toLowerCase(ch);
/* 338 */         uncapitalizeNext = false;
/*     */       }
/*     */     }
/* 341 */     return new String(buffer);
/*     */   }
/*     */ 
/*     */   public static String swapCase(String str)
/*     */   {
/* 368 */     if (StringUtils.isEmpty(str)) {
/* 369 */       return str;
/*     */     }
/* 371 */     char[] buffer = str.toCharArray();
/*     */ 
/* 373 */     boolean whitespace = true;
/*     */ 
/* 375 */     for (int i = 0; i < buffer.length; i++) {
/* 376 */       char ch = buffer[i];
/* 377 */       if (Character.isUpperCase(ch)) {
/* 378 */         buffer[i] = Character.toLowerCase(ch);
/* 379 */         whitespace = false;
/* 380 */       } else if (Character.isTitleCase(ch)) {
/* 381 */         buffer[i] = Character.toLowerCase(ch);
/* 382 */         whitespace = false;
/* 383 */       } else if (Character.isLowerCase(ch)) {
/* 384 */         if (whitespace) {
/* 385 */           buffer[i] = Character.toTitleCase(ch);
/* 386 */           whitespace = false;
/*     */         } else {
/* 388 */           buffer[i] = Character.toUpperCase(ch);
/*     */         }
/*     */       } else {
/* 391 */         whitespace = Character.isWhitespace(ch);
/*     */       }
/*     */     }
/* 394 */     return new String(buffer);
/*     */   }
/*     */ 
/*     */   public static String initials(String str)
/*     */   {
/* 421 */     return initials(str, null);
/*     */   }
/*     */ 
/*     */   public static String initials(String str, char[] delimiters)
/*     */   {
/* 452 */     if (StringUtils.isEmpty(str)) {
/* 453 */       return str;
/*     */     }
/* 455 */     if ((delimiters != null) && (delimiters.length == 0)) {
/* 456 */       return "";
/*     */     }
/* 458 */     int strLen = str.length();
/* 459 */     char[] buf = new char[strLen / 2 + 1];
/* 460 */     int count = 0;
/* 461 */     boolean lastWasGap = true;
/* 462 */     for (int i = 0; i < strLen; i++) {
/* 463 */       char ch = str.charAt(i);
/*     */ 
/* 465 */       if (isDelimiter(ch, delimiters)) {
/* 466 */         lastWasGap = true;
/* 467 */       } else if (lastWasGap) {
/* 468 */         buf[(count++)] = ch;
/* 469 */         lastWasGap = false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 474 */     return new String(buf, 0, count);
/*     */   }
/*     */ 
/*     */   private static boolean isDelimiter(char ch, char[] delimiters)
/*     */   {
/* 486 */     if (delimiters == null) {
/* 487 */       return Character.isWhitespace(ch);
/*     */     }
/* 489 */     for (char delimiter : delimiters) {
/* 490 */       if (ch == delimiter) {
/* 491 */         return true;
/*     */       }
/*     */     }
/* 494 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.WordUtils
 * JD-Core Version:    0.6.2
 */