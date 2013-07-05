/*     */ package org.apache.ws.commons.util;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Calendar;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class XsDateTimeFormat extends Format
/*     */ {
/*     */   private static final long serialVersionUID = 3258131340871479609L;
/*     */   final boolean parseDate;
/*     */   final boolean parseTime;
/*     */ 
/*     */   XsDateTimeFormat(boolean pParseDate, boolean pParseTime)
/*     */   {
/*  35 */     this.parseDate = pParseDate;
/*  36 */     this.parseTime = pParseTime;
/*     */   }
/*     */ 
/*     */   public XsDateTimeFormat()
/*     */   {
/*  42 */     this(true, true);
/*     */   }
/*     */ 
/*     */   private int parseInt(String pString, int pOffset, StringBuffer pDigits) {
/*  46 */     int length = pString.length();
/*  47 */     pDigits.setLength(0);
/*  48 */     while (pOffset < length) {
/*  49 */       char c = pString.charAt(pOffset);
/*  50 */       if (!Character.isDigit(c)) break;
/*  51 */       pDigits.append(c);
/*  52 */       pOffset++;
/*     */     }
/*     */ 
/*  57 */     return pOffset;
/*     */   }
/*     */ 
/*     */   public Object parseObject(String pString, ParsePosition pParsePosition) {
/*  61 */     if (pString == null) {
/*  62 */       throw new NullPointerException("The String argument must not be null.");
/*     */     }
/*  64 */     if (pParsePosition == null) {
/*  65 */       throw new NullPointerException("The ParsePosition argument must not be null.");
/*     */     }
/*  67 */     int offset = pParsePosition.getIndex();
/*  68 */     int length = pString.length();
/*     */ 
/*  70 */     boolean isMinus = false;
/*  71 */     StringBuffer digits = new StringBuffer();
/*     */     int mday;
/*     */     int month;
/*     */     int year;
/*  73 */     if (this.parseDate)
/*     */     {
/*  75 */       if (offset < length) {
/*  76 */         char c = pString.charAt(offset);
/*  77 */         if (c == '+') {
/*  78 */           offset++;
/*  79 */         } else if (c == '-') {
/*  80 */           offset++;
/*  81 */           isMinus = true;
/*     */         }
/*     */       }
/*     */ 
/*  85 */       offset = parseInt(pString, offset, digits);
/*  86 */       if (digits.length() < 4) {
/*  87 */         pParsePosition.setErrorIndex(offset);
/*  88 */         return null;
/*     */       }
/*  90 */       int year = Integer.parseInt(digits.toString());
/*     */ 
/*  92 */       if ((offset < length) && (pString.charAt(offset) == '-')) {
/*  93 */         offset++;
/*     */       } else {
/*  95 */         pParsePosition.setErrorIndex(offset);
/*  96 */         return null;
/*     */       }
/*     */ 
/*  99 */       offset = parseInt(pString, offset, digits);
/* 100 */       if (digits.length() != 2) {
/* 101 */         pParsePosition.setErrorIndex(offset);
/* 102 */         return null;
/*     */       }
/* 104 */       int month = Integer.parseInt(digits.toString());
/*     */ 
/* 106 */       if ((offset < length) && (pString.charAt(offset) == '-')) {
/* 107 */         offset++;
/*     */       } else {
/* 109 */         pParsePosition.setErrorIndex(offset);
/* 110 */         return null;
/*     */       }
/*     */ 
/* 113 */       offset = parseInt(pString, offset, digits);
/* 114 */       if (digits.length() != 2) {
/* 115 */         pParsePosition.setErrorIndex(offset);
/* 116 */         return null;
/*     */       }
/* 118 */       int mday = Integer.parseInt(digits.toString());
/*     */ 
/* 120 */       if (this.parseTime)
/* 121 */         if ((offset < length) && (pString.charAt(offset) == 'T')) {
/* 122 */           offset++;
/*     */         } else {
/* 124 */           pParsePosition.setErrorIndex(offset);
/* 125 */           return null;
/*     */         }
/*     */     }
/*     */     else {
/* 129 */       year = month = mday = 0;
/*     */     }
/*     */     int millis;
/*     */     int millis;
/*     */     int second;
/*     */     int minute;
/*     */     int hour;
/* 133 */     if (this.parseTime) {
/* 134 */       offset = parseInt(pString, offset, digits);
/* 135 */       if (digits.length() != 2) {
/* 136 */         pParsePosition.setErrorIndex(offset);
/* 137 */         return null;
/*     */       }
/* 139 */       int hour = Integer.parseInt(digits.toString());
/*     */ 
/* 141 */       if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 142 */         offset++;
/*     */       } else {
/* 144 */         pParsePosition.setErrorIndex(offset);
/* 145 */         return null;
/*     */       }
/*     */ 
/* 148 */       offset = parseInt(pString, offset, digits);
/* 149 */       if (digits.length() != 2) {
/* 150 */         pParsePosition.setErrorIndex(offset);
/* 151 */         return null;
/*     */       }
/* 153 */       int minute = Integer.parseInt(digits.toString());
/*     */ 
/* 155 */       if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 156 */         offset++;
/*     */       } else {
/* 158 */         pParsePosition.setErrorIndex(offset);
/* 159 */         return null;
/*     */       }
/*     */ 
/* 162 */       offset = parseInt(pString, offset, digits);
/* 163 */       if (digits.length() != 2) {
/* 164 */         pParsePosition.setErrorIndex(offset);
/* 165 */         return null;
/*     */       }
/* 167 */       int second = Integer.parseInt(digits.toString());
/*     */       int millis;
/* 169 */       if ((offset < length) && (pString.charAt(offset) == '.')) {
/* 170 */         offset++;
/* 171 */         offset = parseInt(pString, offset, digits);
/* 172 */         if (digits.length() > 0) {
/* 173 */           int millis = Integer.parseInt(digits.toString());
/* 174 */           if (millis > 999) {
/* 175 */             pParsePosition.setErrorIndex(offset);
/* 176 */             return null;
/*     */           }
/* 178 */           for (int i = digits.length(); i < 3; i++)
/* 179 */             millis *= 10;
/*     */         }
/*     */         else {
/* 182 */           millis = 0;
/*     */         }
/*     */       } else {
/* 185 */         millis = 0;
/*     */       }
/*     */     } else {
/* 188 */       hour = minute = second = millis = 0;
/*     */     }
/*     */ 
/* 191 */     digits.setLength(0);
/* 192 */     digits.append("GMT");
/* 193 */     if (offset < length) {
/* 194 */       char c = pString.charAt(offset);
/* 195 */       if (c == 'Z')
/*     */       {
/* 197 */         offset++;
/* 198 */       } else if ((c == '+') || (c == '-')) {
/* 199 */         digits.append(c);
/* 200 */         offset++;
/* 201 */         for (int i = 0; i < 5; i++) {
/* 202 */           if (offset >= length) {
/* 203 */             pParsePosition.setErrorIndex(offset);
/* 204 */             return null;
/*     */           }
/* 206 */           c = pString.charAt(offset);
/* 207 */           if (((i != 2) && (Character.isDigit(c))) || ((i == 2) && (c == ':')))
/*     */           {
/* 209 */             digits.append(c);
/*     */           } else {
/* 211 */             pParsePosition.setErrorIndex(offset);
/* 212 */             return null;
/*     */           }
/* 214 */           offset++;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 219 */     Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(digits.toString()));
/* 220 */     cal.set(isMinus ? -year : year, this.parseDate ? month - 1 : month, mday, hour, minute, second);
/* 221 */     cal.set(14, millis);
/* 222 */     pParsePosition.setIndex(offset);
/* 223 */     return cal;
/*     */   }
/*     */ 
/*     */   private void append(StringBuffer pBuffer, int pNum, int pMinLen) {
/* 227 */     String s = Integer.toString(pNum);
/* 228 */     for (int i = s.length(); i < pMinLen; i++) {
/* 229 */       pBuffer.append('0');
/*     */     }
/* 231 */     pBuffer.append(s);
/*     */   }
/*     */ 
/*     */   public StringBuffer format(Object pCalendar, StringBuffer pBuffer, FieldPosition pPos) {
/* 235 */     if (pCalendar == null) {
/* 236 */       throw new NullPointerException("The Calendar argument must not be null.");
/*     */     }
/* 238 */     if (pBuffer == null) {
/* 239 */       throw new NullPointerException("The StringBuffer argument must not be null.");
/*     */     }
/* 241 */     if (pPos == null) {
/* 242 */       throw new NullPointerException("The FieldPosition argument must not be null.");
/*     */     }
/*     */ 
/* 245 */     Calendar cal = (Calendar)pCalendar;
/* 246 */     if (this.parseDate) {
/* 247 */       int year = cal.get(1);
/* 248 */       if (year < 0) {
/* 249 */         pBuffer.append('-');
/* 250 */         year = -year;
/*     */       }
/* 252 */       append(pBuffer, year, 4);
/* 253 */       pBuffer.append('-');
/* 254 */       append(pBuffer, cal.get(2) + 1, 2);
/* 255 */       pBuffer.append('-');
/* 256 */       append(pBuffer, cal.get(5), 2);
/* 257 */       if (this.parseTime) {
/* 258 */         pBuffer.append('T');
/*     */       }
/*     */     }
/* 261 */     if (this.parseTime) {
/* 262 */       append(pBuffer, cal.get(11), 2);
/* 263 */       pBuffer.append(':');
/* 264 */       append(pBuffer, cal.get(12), 2);
/* 265 */       pBuffer.append(':');
/* 266 */       append(pBuffer, cal.get(13), 2);
/* 267 */       int millis = cal.get(14);
/* 268 */       if (millis > 0) {
/* 269 */         pBuffer.append('.');
/* 270 */         append(pBuffer, millis, 3);
/*     */       }
/*     */     }
/* 273 */     TimeZone tz = cal.getTimeZone();
/*     */ 
/* 275 */     int offset = cal.get(15);
/* 276 */     if (tz.inDaylightTime(cal.getTime())) {
/* 277 */       offset += cal.get(16);
/*     */     }
/* 279 */     if (offset == 0) {
/* 280 */       pBuffer.append('Z');
/*     */     } else {
/* 282 */       if (offset < 0) {
/* 283 */         pBuffer.append('-');
/* 284 */         offset = -offset;
/*     */       } else {
/* 286 */         pBuffer.append('+');
/*     */       }
/* 288 */       int minutes = offset / 60000;
/* 289 */       int hours = minutes / 60;
/* 290 */       minutes -= hours * 60;
/* 291 */       append(pBuffer, hours, 2);
/* 292 */       pBuffer.append(':');
/* 293 */       append(pBuffer, minutes, 2);
/*     */     }
/* 295 */     return pBuffer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.XsDateTimeFormat
 * JD-Core Version:    0.6.2
 */