/*     */ package org.apache.commons.lang3.time;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class DurationFormatUtils
/*     */ {
/*     */   public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
/* 469 */   static final Object y = "y";
/* 470 */   static final Object M = "M";
/* 471 */   static final Object d = "d";
/* 472 */   static final Object H = "H";
/* 473 */   static final Object m = "m";
/* 474 */   static final Object s = "s";
/* 475 */   static final Object S = "S";
/*     */ 
/*     */   public static String formatDurationHMS(long durationMillis)
/*     */   {
/*  76 */     return formatDuration(durationMillis, "H:mm:ss.SSS");
/*     */   }
/*     */ 
/*     */   public static String formatDurationISO(long durationMillis)
/*     */   {
/*  91 */     return formatDuration(durationMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false);
/*     */   }
/*     */ 
/*     */   public static String formatDuration(long durationMillis, String format)
/*     */   {
/* 106 */     return formatDuration(durationMillis, format, true);
/*     */   }
/*     */ 
/*     */   public static String formatDuration(long durationMillis, String format, boolean padWithZeros)
/*     */   {
/* 124 */     Token[] tokens = lexx(format);
/*     */ 
/* 126 */     int days = 0;
/* 127 */     int hours = 0;
/* 128 */     int minutes = 0;
/* 129 */     int seconds = 0;
/* 130 */     int milliseconds = 0;
/*     */ 
/* 132 */     if (Token.containsTokenWithValue(tokens, d)) {
/* 133 */       days = (int)(durationMillis / 86400000L);
/* 134 */       durationMillis -= days * 86400000L;
/*     */     }
/* 136 */     if (Token.containsTokenWithValue(tokens, H)) {
/* 137 */       hours = (int)(durationMillis / 3600000L);
/* 138 */       durationMillis -= hours * 3600000L;
/*     */     }
/* 140 */     if (Token.containsTokenWithValue(tokens, m)) {
/* 141 */       minutes = (int)(durationMillis / 60000L);
/* 142 */       durationMillis -= minutes * 60000L;
/*     */     }
/* 144 */     if (Token.containsTokenWithValue(tokens, s)) {
/* 145 */       seconds = (int)(durationMillis / 1000L);
/* 146 */       durationMillis -= seconds * 1000L;
/*     */     }
/* 148 */     if (Token.containsTokenWithValue(tokens, S)) {
/* 149 */       milliseconds = (int)durationMillis;
/*     */     }
/*     */ 
/* 152 */     return format(tokens, 0, 0, days, hours, minutes, seconds, milliseconds, padWithZeros);
/*     */   }
/*     */ 
/*     */   public static String formatDurationWords(long durationMillis, boolean suppressLeadingZeroElements, boolean suppressTrailingZeroElements)
/*     */   {
/* 174 */     String duration = formatDuration(durationMillis, "d' days 'H' hours 'm' minutes 's' seconds'");
/* 175 */     if (suppressLeadingZeroElements)
/*     */     {
/* 177 */       duration = " " + duration;
/* 178 */       String tmp = StringUtils.replaceOnce(duration, " 0 days", "");
/* 179 */       if (tmp.length() != duration.length()) {
/* 180 */         duration = tmp;
/* 181 */         tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 182 */         if (tmp.length() != duration.length()) {
/* 183 */           duration = tmp;
/* 184 */           tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 185 */           duration = tmp;
/* 186 */           if (tmp.length() != duration.length()) {
/* 187 */             duration = StringUtils.replaceOnce(tmp, " 0 seconds", "");
/*     */           }
/*     */         }
/*     */       }
/* 191 */       if (duration.length() != 0)
/*     */       {
/* 193 */         duration = duration.substring(1);
/*     */       }
/*     */     }
/* 196 */     if (suppressTrailingZeroElements) {
/* 197 */       String tmp = StringUtils.replaceOnce(duration, " 0 seconds", "");
/* 198 */       if (tmp.length() != duration.length()) {
/* 199 */         duration = tmp;
/* 200 */         tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 201 */         if (tmp.length() != duration.length()) {
/* 202 */           duration = tmp;
/* 203 */           tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 204 */           if (tmp.length() != duration.length()) {
/* 205 */             duration = StringUtils.replaceOnce(tmp, " 0 days", "");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 211 */     duration = " " + duration;
/* 212 */     duration = StringUtils.replaceOnce(duration, " 1 seconds", " 1 second");
/* 213 */     duration = StringUtils.replaceOnce(duration, " 1 minutes", " 1 minute");
/* 214 */     duration = StringUtils.replaceOnce(duration, " 1 hours", " 1 hour");
/* 215 */     duration = StringUtils.replaceOnce(duration, " 1 days", " 1 day");
/* 216 */     return duration.trim();
/*     */   }
/*     */ 
/*     */   public static String formatPeriodISO(long startMillis, long endMillis)
/*     */   {
/* 230 */     return formatPeriod(startMillis, endMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false, TimeZone.getDefault());
/*     */   }
/*     */ 
/*     */   public static String formatPeriod(long startMillis, long endMillis, String format)
/*     */   {
/* 243 */     return formatPeriod(startMillis, endMillis, format, true, TimeZone.getDefault());
/*     */   }
/*     */ 
/*     */   public static String formatPeriod(long startMillis, long endMillis, String format, boolean padWithZeros, TimeZone timezone)
/*     */   {
/* 278 */     Token[] tokens = lexx(format);
/*     */ 
/* 282 */     Calendar start = Calendar.getInstance(timezone);
/* 283 */     start.setTime(new Date(startMillis));
/* 284 */     Calendar end = Calendar.getInstance(timezone);
/* 285 */     end.setTime(new Date(endMillis));
/*     */ 
/* 288 */     int milliseconds = end.get(14) - start.get(14);
/* 289 */     int seconds = end.get(13) - start.get(13);
/* 290 */     int minutes = end.get(12) - start.get(12);
/* 291 */     int hours = end.get(11) - start.get(11);
/* 292 */     int days = end.get(5) - start.get(5);
/* 293 */     int months = end.get(2) - start.get(2);
/* 294 */     int years = end.get(1) - start.get(1);
/*     */ 
/* 297 */     while (milliseconds < 0) {
/* 298 */       milliseconds += 1000;
/* 299 */       seconds--;
/*     */     }
/* 301 */     while (seconds < 0) {
/* 302 */       seconds += 60;
/* 303 */       minutes--;
/*     */     }
/* 305 */     while (minutes < 0) {
/* 306 */       minutes += 60;
/* 307 */       hours--;
/*     */     }
/* 309 */     while (hours < 0) {
/* 310 */       hours += 24;
/* 311 */       days--;
/*     */     }
/*     */ 
/* 314 */     if (Token.containsTokenWithValue(tokens, M)) {
/* 315 */       while (days < 0) {
/* 316 */         days += start.getActualMaximum(5);
/* 317 */         months--;
/* 318 */         start.add(2, 1);
/*     */       }
/*     */ 
/* 321 */       while (months < 0) {
/* 322 */         months += 12;
/* 323 */         years--;
/*     */       }
/*     */ 
/* 326 */       if ((!Token.containsTokenWithValue(tokens, y)) && (years != 0)) {
/* 327 */         while (years != 0) {
/* 328 */           months += 12 * years;
/* 329 */           years = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 335 */       if (!Token.containsTokenWithValue(tokens, y)) {
/* 336 */         int target = end.get(1);
/* 337 */         if (months < 0)
/*     */         {
/* 339 */           target--;
/*     */         }
/*     */ 
/* 342 */         while (start.get(1) != target) {
/* 343 */           days += start.getActualMaximum(6) - start.get(6);
/*     */ 
/* 346 */           if (((start instanceof GregorianCalendar)) && (start.get(2) == 1) && (start.get(5) == 29))
/*     */           {
/* 349 */             days++;
/*     */           }
/*     */ 
/* 352 */           start.add(1, 1);
/*     */ 
/* 354 */           days += start.get(6);
/*     */         }
/*     */ 
/* 357 */         years = 0;
/*     */       }
/*     */ 
/* 360 */       while (start.get(2) != end.get(2)) {
/* 361 */         days += start.getActualMaximum(5);
/* 362 */         start.add(2, 1);
/*     */       }
/*     */ 
/* 365 */       months = 0;
/*     */ 
/* 367 */       while (days < 0) {
/* 368 */         days += start.getActualMaximum(5);
/* 369 */         months--;
/* 370 */         start.add(2, 1);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 379 */     if (!Token.containsTokenWithValue(tokens, d)) {
/* 380 */       hours += 24 * days;
/* 381 */       days = 0;
/*     */     }
/* 383 */     if (!Token.containsTokenWithValue(tokens, H)) {
/* 384 */       minutes += 60 * hours;
/* 385 */       hours = 0;
/*     */     }
/* 387 */     if (!Token.containsTokenWithValue(tokens, m)) {
/* 388 */       seconds += 60 * minutes;
/* 389 */       minutes = 0;
/*     */     }
/* 391 */     if (!Token.containsTokenWithValue(tokens, s)) {
/* 392 */       milliseconds += 1000 * seconds;
/* 393 */       seconds = 0;
/*     */     }
/*     */ 
/* 396 */     return format(tokens, years, months, days, hours, minutes, seconds, milliseconds, padWithZeros);
/*     */   }
/*     */ 
/*     */   static String format(Token[] tokens, int years, int months, int days, int hours, int minutes, int seconds, int milliseconds, boolean padWithZeros)
/*     */   {
/* 416 */     StringBuffer buffer = new StringBuffer();
/* 417 */     boolean lastOutputSeconds = false;
/* 418 */     int sz = tokens.length;
/* 419 */     for (int i = 0; i < sz; i++) {
/* 420 */       Token token = tokens[i];
/* 421 */       Object value = token.getValue();
/* 422 */       int count = token.getCount();
/* 423 */       if ((value instanceof StringBuffer)) {
/* 424 */         buffer.append(value.toString());
/*     */       }
/* 426 */       else if (value == y) {
/* 427 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(years), count, '0') : Integer.toString(years));
/*     */ 
/* 429 */         lastOutputSeconds = false;
/* 430 */       } else if (value == M) {
/* 431 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(months), count, '0') : Integer.toString(months));
/*     */ 
/* 433 */         lastOutputSeconds = false;
/* 434 */       } else if (value == d) {
/* 435 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(days), count, '0') : Integer.toString(days));
/*     */ 
/* 437 */         lastOutputSeconds = false;
/* 438 */       } else if (value == H) {
/* 439 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(hours), count, '0') : Integer.toString(hours));
/*     */ 
/* 441 */         lastOutputSeconds = false;
/* 442 */       } else if (value == m) {
/* 443 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(minutes), count, '0') : Integer.toString(minutes));
/*     */ 
/* 445 */         lastOutputSeconds = false;
/* 446 */       } else if (value == s) {
/* 447 */         buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(seconds), count, '0') : Integer.toString(seconds));
/*     */ 
/* 449 */         lastOutputSeconds = true;
/* 450 */       } else if (value == S) {
/* 451 */         if (lastOutputSeconds) {
/* 452 */           milliseconds += 1000;
/* 453 */           String str = padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds);
/*     */ 
/* 456 */           buffer.append(str.substring(1));
/*     */         } else {
/* 458 */           buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds));
/*     */         }
/*     */ 
/* 462 */         lastOutputSeconds = false;
/*     */       }
/*     */     }
/*     */ 
/* 466 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   static Token[] lexx(String format)
/*     */   {
/* 484 */     char[] array = format.toCharArray();
/* 485 */     ArrayList list = new ArrayList(array.length);
/*     */ 
/* 487 */     boolean inLiteral = false;
/* 488 */     StringBuffer buffer = null;
/* 489 */     Token previous = null;
/* 490 */     int sz = array.length;
/* 491 */     for (int i = 0; i < sz; i++) {
/* 492 */       char ch = array[i];
/* 493 */       if ((inLiteral) && (ch != '\'')) {
/* 494 */         buffer.append(ch);
/*     */       }
/*     */       else {
/* 497 */         Object value = null;
/* 498 */         switch (ch)
/*     */         {
/*     */         case '\'':
/* 501 */           if (inLiteral) {
/* 502 */             buffer = null;
/* 503 */             inLiteral = false;
/*     */           } else {
/* 505 */             buffer = new StringBuffer();
/* 506 */             list.add(new Token(buffer));
/* 507 */             inLiteral = true;
/*     */           }
/* 509 */           break;
/*     */         case 'y':
/* 510 */           value = y; break;
/*     */         case 'M':
/* 511 */           value = M; break;
/*     */         case 'd':
/* 512 */           value = d; break;
/*     */         case 'H':
/* 513 */           value = H; break;
/*     */         case 'm':
/* 514 */           value = m; break;
/*     */         case 's':
/* 515 */           value = s; break;
/*     */         case 'S':
/* 516 */           value = S; break;
/*     */         default:
/* 518 */           if (buffer == null) {
/* 519 */             buffer = new StringBuffer();
/* 520 */             list.add(new Token(buffer));
/*     */           }
/* 522 */           buffer.append(ch);
/*     */         }
/*     */ 
/* 525 */         if (value != null) {
/* 526 */           if ((previous != null) && (previous.getValue() == value)) {
/* 527 */             previous.increment();
/*     */           } else {
/* 529 */             Token token = new Token(value);
/* 530 */             list.add(token);
/* 531 */             previous = token;
/*     */           }
/* 533 */           buffer = null;
/*     */         }
/*     */       }
/*     */     }
/* 536 */     return (Token[])list.toArray(new Token[list.size()]);
/*     */   }
/*     */ 
/*     */   static class Token
/*     */   {
/*     */     private final Object value;
/*     */     private int count;
/*     */ 
/*     */     static boolean containsTokenWithValue(Token[] tokens, Object value)
/*     */     {
/* 553 */       int sz = tokens.length;
/* 554 */       for (int i = 0; i < sz; i++) {
/* 555 */         if (tokens[i].getValue() == value) {
/* 556 */           return true;
/*     */         }
/*     */       }
/* 559 */       return false;
/*     */     }
/*     */ 
/*     */     Token(Object value)
/*     */     {
/* 571 */       this.value = value;
/* 572 */       this.count = 1;
/*     */     }
/*     */ 
/*     */     Token(Object value, int count)
/*     */     {
/* 583 */       this.value = value;
/* 584 */       this.count = count;
/*     */     }
/*     */ 
/*     */     void increment()
/*     */     {
/* 591 */       this.count += 1;
/*     */     }
/*     */ 
/*     */     int getCount()
/*     */     {
/* 600 */       return this.count;
/*     */     }
/*     */ 
/*     */     Object getValue()
/*     */     {
/* 609 */       return this.value;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object obj2)
/*     */     {
/* 620 */       if ((obj2 instanceof Token)) {
/* 621 */         Token tok2 = (Token)obj2;
/* 622 */         if (this.value.getClass() != tok2.value.getClass()) {
/* 623 */           return false;
/*     */         }
/* 625 */         if (this.count != tok2.count) {
/* 626 */           return false;
/*     */         }
/* 628 */         if ((this.value instanceof StringBuffer))
/* 629 */           return this.value.toString().equals(tok2.value.toString());
/* 630 */         if ((this.value instanceof Number)) {
/* 631 */           return this.value.equals(tok2.value);
/*     */         }
/* 633 */         return this.value == tok2.value;
/*     */       }
/*     */ 
/* 636 */       return false;
/*     */     }
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 648 */       return this.value.hashCode();
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 658 */       return StringUtils.repeat(this.value.toString(), this.count);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.DurationFormatUtils
 * JD-Core Version:    0.6.2
 */