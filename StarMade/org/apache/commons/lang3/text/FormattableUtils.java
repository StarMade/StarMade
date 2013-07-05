/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.util.Formattable;
/*     */ import java.util.Formatter;
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class FormattableUtils
/*     */ {
/*     */   private static final String SIMPLEST_FORMAT = "%s";
/*     */ 
/*     */   public static String toString(Formattable formattable)
/*     */   {
/*  66 */     return String.format("%s", new Object[] { formattable });
/*     */   }
/*     */ 
/*     */   public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision)
/*     */   {
/*  83 */     return append(seq, formatter, flags, width, precision, ' ', null);
/*     */   }
/*     */ 
/*     */   public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar)
/*     */   {
/* 100 */     return append(seq, formatter, flags, width, precision, padChar, null);
/*     */   }
/*     */ 
/*     */   public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, CharSequence ellipsis)
/*     */   {
/* 118 */     return append(seq, formatter, flags, width, precision, ' ', ellipsis);
/*     */   }
/*     */ 
/*     */   public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar, CharSequence ellipsis)
/*     */   {
/* 136 */     Validate.isTrue((ellipsis == null) || (precision < 0) || (ellipsis.length() <= precision), "Specified ellipsis '%1$s' exceeds precision of %2$s", new Object[] { ellipsis, Integer.valueOf(precision) });
/*     */ 
/* 138 */     StringBuilder buf = new StringBuilder(seq);
/* 139 */     if ((precision >= 0) && (precision < seq.length())) {
/* 140 */       CharSequence _ellipsis = (CharSequence)ObjectUtils.defaultIfNull(ellipsis, "");
/* 141 */       buf.replace(precision - _ellipsis.length(), seq.length(), _ellipsis.toString());
/*     */     }
/* 143 */     boolean leftJustify = (flags & 0x1) == 1;
/* 144 */     for (int i = buf.length(); i < width; i++) {
/* 145 */       buf.insert(leftJustify ? i : 0, padChar);
/*     */     }
/* 147 */     formatter.format(buf.toString(), new Object[0]);
/* 148 */     return formatter;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.FormattableUtils
 * JD-Core Version:    0.6.2
 */