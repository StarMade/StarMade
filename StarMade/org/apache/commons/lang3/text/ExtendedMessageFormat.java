/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.text.Format;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class ExtendedMessageFormat extends MessageFormat
/*     */ {
/*     */   private static final long serialVersionUID = -2362048321261811743L;
/*     */   private static final int HASH_SEED = 31;
/*     */   private static final String DUMMY_PATTERN = "";
/*     */   private static final String ESCAPED_QUOTE = "''";
/*     */   private static final char START_FMT = ',';
/*     */   private static final char END_FE = '}';
/*     */   private static final char START_FE = '{';
/*     */   private static final char QUOTE = '\'';
/*     */   private String toPattern;
/*     */   private final Map<String, ? extends FormatFactory> registry;
/*     */ 
/*     */   public ExtendedMessageFormat(String pattern)
/*     */   {
/*  91 */     this(pattern, Locale.getDefault());
/*     */   }
/*     */ 
/*     */   public ExtendedMessageFormat(String pattern, Locale locale)
/*     */   {
/* 102 */     this(pattern, locale, null);
/*     */   }
/*     */ 
/*     */   public ExtendedMessageFormat(String pattern, Map<String, ? extends FormatFactory> registry)
/*     */   {
/* 113 */     this(pattern, Locale.getDefault(), registry);
/*     */   }
/*     */ 
/*     */   public ExtendedMessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry)
/*     */   {
/* 125 */     super("");
/* 126 */     setLocale(locale);
/* 127 */     this.registry = registry;
/* 128 */     applyPattern(pattern);
/*     */   }
/*     */ 
/*     */   public String toPattern()
/*     */   {
/* 136 */     return this.toPattern;
/*     */   }
/*     */ 
/*     */   public final void applyPattern(String pattern)
/*     */   {
/* 146 */     if (this.registry == null) {
/* 147 */       super.applyPattern(pattern);
/* 148 */       this.toPattern = super.toPattern();
/* 149 */       return;
/*     */     }
/* 151 */     ArrayList foundFormats = new ArrayList();
/* 152 */     ArrayList foundDescriptions = new ArrayList();
/* 153 */     StringBuilder stripCustom = new StringBuilder(pattern.length());
/*     */ 
/* 155 */     ParsePosition pos = new ParsePosition(0);
/* 156 */     char[] c = pattern.toCharArray();
/* 157 */     int fmtCount = 0;
/* 158 */     while (pos.getIndex() < pattern.length()) {
/* 159 */       switch (c[pos.getIndex()]) {
/*     */       case '\'':
/* 161 */         appendQuotedString(pattern, pos, stripCustom, true);
/* 162 */         break;
/*     */       case '{':
/* 164 */         fmtCount++;
/* 165 */         seekNonWs(pattern, pos);
/* 166 */         int start = pos.getIndex();
/* 167 */         int index = readArgumentIndex(pattern, next(pos));
/* 168 */         stripCustom.append('{').append(index);
/* 169 */         seekNonWs(pattern, pos);
/* 170 */         Format format = null;
/* 171 */         String formatDescription = null;
/* 172 */         if (c[pos.getIndex()] == ',') {
/* 173 */           formatDescription = parseFormatDescription(pattern, next(pos));
/*     */ 
/* 175 */           format = getFormat(formatDescription);
/* 176 */           if (format == null) {
/* 177 */             stripCustom.append(',').append(formatDescription);
/*     */           }
/*     */         }
/* 180 */         foundFormats.add(format);
/* 181 */         foundDescriptions.add(format == null ? null : formatDescription);
/* 182 */         Validate.isTrue(foundFormats.size() == fmtCount);
/* 183 */         Validate.isTrue(foundDescriptions.size() == fmtCount);
/* 184 */         if (c[pos.getIndex()] != '}') {
/* 185 */           throw new IllegalArgumentException("Unreadable format element at position " + start);
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/* 190 */       stripCustom.append(c[pos.getIndex()]);
/* 191 */       next(pos);
/*     */     }
/*     */ 
/* 194 */     super.applyPattern(stripCustom.toString());
/* 195 */     this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
/* 196 */     if (containsElements(foundFormats)) {
/* 197 */       Format[] origFormats = getFormats();
/*     */ 
/* 200 */       int i = 0;
/* 201 */       for (Iterator it = foundFormats.iterator(); it.hasNext(); i++) {
/* 202 */         Format f = (Format)it.next();
/* 203 */         if (f != null) {
/* 204 */           origFormats[i] = f;
/*     */         }
/*     */       }
/* 207 */       super.setFormats(origFormats);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setFormat(int formatElementIndex, Format newFormat)
/*     */   {
/* 220 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setFormatByArgumentIndex(int argumentIndex, Format newFormat)
/*     */   {
/* 232 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setFormats(Format[] newFormats)
/*     */   {
/* 243 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setFormatsByArgumentIndex(Format[] newFormats)
/*     */   {
/* 254 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 265 */     if (obj == this) {
/* 266 */       return true;
/*     */     }
/* 268 */     if (obj == null) {
/* 269 */       return false;
/*     */     }
/* 271 */     if (!super.equals(obj)) {
/* 272 */       return false;
/*     */     }
/* 274 */     if (ObjectUtils.notEqual(getClass(), obj.getClass())) {
/* 275 */       return false;
/*     */     }
/* 277 */     ExtendedMessageFormat rhs = (ExtendedMessageFormat)obj;
/* 278 */     if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern)) {
/* 279 */       return false;
/*     */     }
/* 281 */     if (ObjectUtils.notEqual(this.registry, rhs.registry)) {
/* 282 */       return false;
/*     */     }
/* 284 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 294 */     int result = super.hashCode();
/* 295 */     result = 31 * result + ObjectUtils.hashCode(this.registry);
/* 296 */     result = 31 * result + ObjectUtils.hashCode(this.toPattern);
/* 297 */     return result;
/*     */   }
/*     */ 
/*     */   private Format getFormat(String desc)
/*     */   {
/* 307 */     if (this.registry != null) {
/* 308 */       String name = desc;
/* 309 */       String args = null;
/* 310 */       int i = desc.indexOf(',');
/* 311 */       if (i > 0) {
/* 312 */         name = desc.substring(0, i).trim();
/* 313 */         args = desc.substring(i + 1).trim();
/*     */       }
/* 315 */       FormatFactory factory = (FormatFactory)this.registry.get(name);
/* 316 */       if (factory != null) {
/* 317 */         return factory.getFormat(name, args, getLocale());
/*     */       }
/*     */     }
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */   private int readArgumentIndex(String pattern, ParsePosition pos)
/*     */   {
/* 331 */     int start = pos.getIndex();
/* 332 */     seekNonWs(pattern, pos);
/* 333 */     StringBuffer result = new StringBuffer();
/* 334 */     boolean error = false;
/* 335 */     for (; (!error) && (pos.getIndex() < pattern.length()); next(pos)) {
/* 336 */       char c = pattern.charAt(pos.getIndex());
/* 337 */       if (Character.isWhitespace(c)) {
/* 338 */         seekNonWs(pattern, pos);
/* 339 */         c = pattern.charAt(pos.getIndex());
/* 340 */         if ((c != ',') && (c != '}')) {
/* 341 */           error = true;
/* 342 */           continue;
/*     */         }
/*     */       }
/* 345 */       if (((c == ',') || (c == '}')) && (result.length() > 0)) {
/*     */         try {
/* 347 */           return Integer.parseInt(result.toString());
/*     */         }
/*     */         catch (NumberFormatException e)
/*     */         {
/*     */         }
/*     */       }
/* 353 */       error = !Character.isDigit(c);
/* 354 */       result.append(c);
/*     */     }
/* 356 */     if (error) {
/* 357 */       throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex()));
/*     */     }
/*     */ 
/* 361 */     throw new IllegalArgumentException("Unterminated format element at position " + start);
/*     */   }
/*     */ 
/*     */   private String parseFormatDescription(String pattern, ParsePosition pos)
/*     */   {
/* 373 */     int start = pos.getIndex();
/* 374 */     seekNonWs(pattern, pos);
/* 375 */     int text = pos.getIndex();
/* 376 */     int depth = 1;
/* 377 */     for (; pos.getIndex() < pattern.length(); next(pos)) {
/* 378 */       switch (pattern.charAt(pos.getIndex())) {
/*     */       case '{':
/* 380 */         depth++;
/* 381 */         break;
/*     */       case '}':
/* 383 */         depth--;
/* 384 */         if (depth == 0) {
/* 385 */           return pattern.substring(text, pos.getIndex());
/*     */         }
/*     */         break;
/*     */       case '\'':
/* 389 */         getQuotedString(pattern, pos, false);
/*     */       }
/*     */     }
/*     */ 
/* 393 */     throw new IllegalArgumentException("Unterminated format element at position " + start);
/*     */   }
/*     */ 
/*     */   private String insertFormats(String pattern, ArrayList<String> customPatterns)
/*     */   {
/* 405 */     if (!containsElements(customPatterns)) {
/* 406 */       return pattern;
/*     */     }
/* 408 */     StringBuilder sb = new StringBuilder(pattern.length() * 2);
/* 409 */     ParsePosition pos = new ParsePosition(0);
/* 410 */     int fe = -1;
/* 411 */     int depth = 0;
/* 412 */     while (pos.getIndex() < pattern.length()) {
/* 413 */       char c = pattern.charAt(pos.getIndex());
/* 414 */       switch (c) {
/*     */       case '\'':
/* 416 */         appendQuotedString(pattern, pos, sb, false);
/* 417 */         break;
/*     */       case '{':
/* 419 */         depth++;
/* 420 */         if (depth == 1) {
/* 421 */           fe++;
/* 422 */           sb.append('{').append(readArgumentIndex(pattern, next(pos)));
/*     */ 
/* 424 */           String customPattern = (String)customPatterns.get(fe);
/* 425 */           if (customPattern != null)
/* 426 */             sb.append(',').append(customPattern);
/*     */         }
/* 428 */         break;
/*     */       case '}':
/* 431 */         depth--;
/*     */       default:
/* 434 */         sb.append(c);
/* 435 */         next(pos);
/*     */       }
/*     */     }
/* 438 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private void seekNonWs(String pattern, ParsePosition pos)
/*     */   {
/* 448 */     int len = 0;
/* 449 */     char[] buffer = pattern.toCharArray();
/*     */     do {
/* 451 */       len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
/* 452 */       pos.setIndex(pos.getIndex() + len);
/* 453 */     }while ((len > 0) && (pos.getIndex() < pattern.length()));
/*     */   }
/*     */ 
/*     */   private ParsePosition next(ParsePosition pos)
/*     */   {
/* 463 */     pos.setIndex(pos.getIndex() + 1);
/* 464 */     return pos;
/*     */   }
/*     */ 
/*     */   private StringBuilder appendQuotedString(String pattern, ParsePosition pos, StringBuilder appendTo, boolean escapingOn)
/*     */   {
/* 479 */     int start = pos.getIndex();
/* 480 */     char[] c = pattern.toCharArray();
/* 481 */     if ((escapingOn) && (c[start] == '\'')) {
/* 482 */       next(pos);
/* 483 */       return appendTo == null ? null : appendTo.append('\'');
/*     */     }
/* 485 */     int lastHold = start;
/* 486 */     for (int i = pos.getIndex(); i < pattern.length(); i++) {
/* 487 */       if ((escapingOn) && (pattern.substring(i).startsWith("''"))) {
/* 488 */         appendTo.append(c, lastHold, pos.getIndex() - lastHold).append('\'');
/*     */ 
/* 490 */         pos.setIndex(i + "''".length());
/* 491 */         lastHold = pos.getIndex();
/*     */       }
/*     */       else {
/* 494 */         switch (c[pos.getIndex()]) {
/*     */         case '\'':
/* 496 */           next(pos);
/* 497 */           return appendTo == null ? null : appendTo.append(c, lastHold, pos.getIndex() - lastHold);
/*     */         }
/*     */ 
/* 500 */         next(pos);
/*     */       }
/*     */     }
/* 503 */     throw new IllegalArgumentException("Unterminated quoted string at position " + start);
/*     */   }
/*     */ 
/*     */   private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn)
/*     */   {
/* 516 */     appendQuotedString(pattern, pos, null, escapingOn);
/*     */   }
/*     */ 
/*     */   private boolean containsElements(Collection<?> coll)
/*     */   {
/* 525 */     if ((coll == null) || (coll.isEmpty())) {
/* 526 */       return false;
/*     */     }
/* 528 */     for (Iterator i$ = coll.iterator(); i$.hasNext(); ) { Object name = i$.next();
/* 529 */       if (name != null) {
/* 530 */         return true;
/*     */       }
/*     */     }
/* 533 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.ExtendedMessageFormat
 * JD-Core Version:    0.6.2
 */