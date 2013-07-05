/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.apache.commons.lang3.text.translate.AggregateTranslator;
/*     */ import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
/*     */ import org.apache.commons.lang3.text.translate.EntityArrays;
/*     */ import org.apache.commons.lang3.text.translate.LookupTranslator;
/*     */ import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
/*     */ import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
/*     */ import org.apache.commons.lang3.text.translate.OctalUnescaper;
/*     */ import org.apache.commons.lang3.text.translate.UnicodeEscaper;
/*     */ import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
/*     */ 
/*     */ public class StringEscapeUtils
/*     */ {
/*  52 */   public static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(new String[][] { { "\"", "\\\"" }, { "\\", "\\\\" } }).with(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()) }).with(new CharSequenceTranslator[] { UnicodeEscaper.outsideOf(32, 127) });
/*     */ 
/*  72 */   public static final CharSequenceTranslator ESCAPE_ECMASCRIPT = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(new String[][] { { "'", "\\'" }, { "\"", "\\\"" }, { "\\", "\\\\" }, { "/", "\\/" } }), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()), UnicodeEscaper.outsideOf(32, 127) });
/*     */ 
/*  94 */   public static final CharSequenceTranslator ESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.APOS_ESCAPE()) });
/*     */ 
/* 109 */   public static final CharSequenceTranslator ESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()) });
/*     */ 
/* 124 */   public static final CharSequenceTranslator ESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE()) });
/*     */ 
/* 140 */   public static final CharSequenceTranslator ESCAPE_CSV = new CsvEscaper();
/*     */ 
/* 183 */   public static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new CharSequenceTranslator[] { new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()), new LookupTranslator(new String[][] { { "\\\\", "\\" }, { "\\\"", "\"" }, { "\\'", "'" }, { "\\", "" } }) });
/*     */ 
/* 206 */   public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT = UNESCAPE_JAVA;
/*     */ 
/* 217 */   public static final CharSequenceTranslator UNESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/*     */ 
/* 233 */   public static final CharSequenceTranslator UNESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/*     */ 
/* 250 */   public static final CharSequenceTranslator UNESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.APOS_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/*     */ 
/* 266 */   public static final CharSequenceTranslator UNESCAPE_CSV = new CsvUnescaper();
/*     */ 
/*     */   public static final String escapeJava(String input)
/*     */   {
/* 341 */     return ESCAPE_JAVA.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String escapeEcmaScript(String input)
/*     */   {
/* 370 */     return ESCAPE_ECMASCRIPT.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeJava(String input)
/*     */   {
/* 383 */     return UNESCAPE_JAVA.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeEcmaScript(String input)
/*     */   {
/* 400 */     return UNESCAPE_ECMASCRIPT.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String escapeHtml4(String input)
/*     */   {
/* 433 */     return ESCAPE_HTML4.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String escapeHtml3(String input)
/*     */   {
/* 446 */     return ESCAPE_HTML3.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeHtml4(String input)
/*     */   {
/* 468 */     return UNESCAPE_HTML4.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeHtml3(String input)
/*     */   {
/* 482 */     return UNESCAPE_HTML3.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String escapeXml(String input)
/*     */   {
/* 506 */     return ESCAPE_XML.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeXml(String input)
/*     */   {
/* 527 */     return UNESCAPE_XML.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String escapeCsv(String input)
/*     */   {
/* 556 */     return ESCAPE_CSV.translate(input);
/*     */   }
/*     */ 
/*     */   public static final String unescapeCsv(String input)
/*     */   {
/* 582 */     return UNESCAPE_CSV.translate(input);
/*     */   }
/*     */ 
/*     */   static class CsvUnescaper extends CharSequenceTranslator
/*     */   {
/*     */     private static final char CSV_DELIMITER = ',';
/*     */     private static final char CSV_QUOTE = '"';
/* 272 */     private static final String CSV_QUOTE_STR = String.valueOf('"');
/* 273 */     private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
/*     */ 
/*     */     public int translate(CharSequence input, int index, Writer out)
/*     */       throws IOException
/*     */     {
/* 279 */       if (index != 0) {
/* 280 */         throw new IllegalStateException("CsvUnescaper should never reach the [1] index");
/*     */       }
/*     */ 
/* 283 */       if ((input.charAt(0) != '"') || (input.charAt(input.length() - 1) != '"')) {
/* 284 */         out.write(input.toString());
/* 285 */         return input.length();
/*     */       }
/*     */ 
/* 289 */       String quoteless = input.subSequence(1, input.length() - 1).toString();
/*     */ 
/* 291 */       if (StringUtils.containsAny(quoteless, CSV_SEARCH_CHARS))
/*     */       {
/* 293 */         out.write(StringUtils.replace(quoteless, CSV_QUOTE_STR + CSV_QUOTE_STR, CSV_QUOTE_STR));
/*     */       }
/* 295 */       else out.write(input.toString());
/*     */ 
/* 297 */       return input.length();
/*     */     }
/*     */   }
/*     */ 
/*     */   static class CsvEscaper extends CharSequenceTranslator
/*     */   {
/*     */     private static final char CSV_DELIMITER = ',';
/*     */     private static final char CSV_QUOTE = '"';
/* 149 */     private static final String CSV_QUOTE_STR = String.valueOf('"');
/* 150 */     private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
/*     */ 
/*     */     public int translate(CharSequence input, int index, Writer out)
/*     */       throws IOException
/*     */     {
/* 156 */       if (index != 0) {
/* 157 */         throw new IllegalStateException("CsvEscaper should never reach the [1] index");
/*     */       }
/*     */ 
/* 160 */       if (StringUtils.containsNone(input.toString(), CSV_SEARCH_CHARS)) {
/* 161 */         out.write(input.toString());
/*     */       } else {
/* 163 */         out.write(34);
/* 164 */         out.write(StringUtils.replace(input.toString(), CSV_QUOTE_STR, CSV_QUOTE_STR + CSV_QUOTE_STR));
/* 165 */         out.write(34);
/*     */       }
/* 167 */       return input.length();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.StringEscapeUtils
 * JD-Core Version:    0.6.2
 */