/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import org.apache.commons.lang3.text.translate.AggregateTranslator;
/*   6:    */import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
/*   7:    */import org.apache.commons.lang3.text.translate.EntityArrays;
/*   8:    */import org.apache.commons.lang3.text.translate.LookupTranslator;
/*   9:    */import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
/*  10:    */import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
/*  11:    */import org.apache.commons.lang3.text.translate.OctalUnescaper;
/*  12:    */import org.apache.commons.lang3.text.translate.UnicodeEscaper;
/*  13:    */import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
/*  14:    */
/*  50:    */public class StringEscapeUtils
/*  51:    */{
/*  52: 52 */  public static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(new String[][] { { "\"", "\\\"" }, { "\\", "\\\\" } }).with(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()) }).with(new CharSequenceTranslator[] { UnicodeEscaper.outsideOf(32, 127) });
/*  53:    */  
/*  72: 72 */  public static final CharSequenceTranslator ESCAPE_ECMASCRIPT = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(new String[][] { { "'", "\\'" }, { "\"", "\\\"" }, { "\\", "\\\\" }, { "/", "\\/" } }), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()), UnicodeEscaper.outsideOf(32, 127) });
/*  73:    */  
/*  94: 94 */  public static final CharSequenceTranslator ESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.APOS_ESCAPE()) });
/*  95:    */  
/* 109:109 */  public static final CharSequenceTranslator ESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()) });
/* 110:    */  
/* 124:124 */  public static final CharSequenceTranslator ESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE()) });
/* 125:    */  
/* 140:140 */  public static final CharSequenceTranslator ESCAPE_CSV = new CsvEscaper();
/* 141:    */  
/* 143:    */  static class CsvEscaper
/* 144:    */    extends CharSequenceTranslator
/* 145:    */  {
/* 146:    */    private static final char CSV_DELIMITER = ',';
/* 147:    */    
/* 148:    */    private static final char CSV_QUOTE = '"';
/* 149:149 */    private static final String CSV_QUOTE_STR = String.valueOf('"');
/* 150:150 */    private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
/* 151:    */    
/* 153:    */    public int translate(CharSequence input, int index, Writer out)
/* 154:    */      throws IOException
/* 155:    */    {
/* 156:156 */      if (index != 0) {
/* 157:157 */        throw new IllegalStateException("CsvEscaper should never reach the [1] index");
/* 158:    */      }
/* 159:    */      
/* 160:160 */      if (StringUtils.containsNone(input.toString(), CSV_SEARCH_CHARS)) {
/* 161:161 */        out.write(input.toString());
/* 162:    */      } else {
/* 163:163 */        out.write(34);
/* 164:164 */        out.write(StringUtils.replace(input.toString(), CSV_QUOTE_STR, CSV_QUOTE_STR + CSV_QUOTE_STR));
/* 165:165 */        out.write(34);
/* 166:    */      }
/* 167:167 */      return input.length();
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 183:183 */  public static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new CharSequenceTranslator[] { new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()), new LookupTranslator(new String[][] { { "\\\\", "\\" }, { "\\\"", "\"" }, { "\\'", "'" }, { "\\", "" } }) });
/* 184:    */  
/* 206:206 */  public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT = UNESCAPE_JAVA;
/* 207:    */  
/* 217:217 */  public static final CharSequenceTranslator UNESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/* 218:    */  
/* 233:233 */  public static final CharSequenceTranslator UNESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/* 234:    */  
/* 250:250 */  public static final CharSequenceTranslator UNESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.APOS_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
/* 251:    */  
/* 266:266 */  public static final CharSequenceTranslator UNESCAPE_CSV = new CsvUnescaper();
/* 267:    */  
/* 268:    */  static class CsvUnescaper extends CharSequenceTranslator
/* 269:    */  {
/* 270:    */    private static final char CSV_DELIMITER = ',';
/* 271:    */    private static final char CSV_QUOTE = '"';
/* 272:272 */    private static final String CSV_QUOTE_STR = String.valueOf('"');
/* 273:273 */    private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
/* 274:    */    
/* 276:    */    public int translate(CharSequence input, int index, Writer out)
/* 277:    */      throws IOException
/* 278:    */    {
/* 279:279 */      if (index != 0) {
/* 280:280 */        throw new IllegalStateException("CsvUnescaper should never reach the [1] index");
/* 281:    */      }
/* 282:    */      
/* 283:283 */      if ((input.charAt(0) != '"') || (input.charAt(input.length() - 1) != '"')) {
/* 284:284 */        out.write(input.toString());
/* 285:285 */        return input.length();
/* 286:    */      }
/* 287:    */      
/* 289:289 */      String quoteless = input.subSequence(1, input.length() - 1).toString();
/* 290:    */      
/* 291:291 */      if (StringUtils.containsAny(quoteless, CSV_SEARCH_CHARS))
/* 292:    */      {
/* 293:293 */        out.write(StringUtils.replace(quoteless, CSV_QUOTE_STR + CSV_QUOTE_STR, CSV_QUOTE_STR));
/* 294:    */      } else {
/* 295:295 */        out.write(input.toString());
/* 296:    */      }
/* 297:297 */      return input.length();
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 339:    */  public static final String escapeJava(String input)
/* 340:    */  {
/* 341:341 */    return ESCAPE_JAVA.translate(input);
/* 342:    */  }
/* 343:    */  
/* 368:    */  public static final String escapeEcmaScript(String input)
/* 369:    */  {
/* 370:370 */    return ESCAPE_ECMASCRIPT.translate(input);
/* 371:    */  }
/* 372:    */  
/* 381:    */  public static final String unescapeJava(String input)
/* 382:    */  {
/* 383:383 */    return UNESCAPE_JAVA.translate(input);
/* 384:    */  }
/* 385:    */  
/* 398:    */  public static final String unescapeEcmaScript(String input)
/* 399:    */  {
/* 400:400 */    return UNESCAPE_ECMASCRIPT.translate(input);
/* 401:    */  }
/* 402:    */  
/* 431:    */  public static final String escapeHtml4(String input)
/* 432:    */  {
/* 433:433 */    return ESCAPE_HTML4.translate(input);
/* 434:    */  }
/* 435:    */  
/* 444:    */  public static final String escapeHtml3(String input)
/* 445:    */  {
/* 446:446 */    return ESCAPE_HTML3.translate(input);
/* 447:    */  }
/* 448:    */  
/* 466:    */  public static final String unescapeHtml4(String input)
/* 467:    */  {
/* 468:468 */    return UNESCAPE_HTML4.translate(input);
/* 469:    */  }
/* 470:    */  
/* 480:    */  public static final String unescapeHtml3(String input)
/* 481:    */  {
/* 482:482 */    return UNESCAPE_HTML3.translate(input);
/* 483:    */  }
/* 484:    */  
/* 504:    */  public static final String escapeXml(String input)
/* 505:    */  {
/* 506:506 */    return ESCAPE_XML.translate(input);
/* 507:    */  }
/* 508:    */  
/* 525:    */  public static final String unescapeXml(String input)
/* 526:    */  {
/* 527:527 */    return UNESCAPE_XML.translate(input);
/* 528:    */  }
/* 529:    */  
/* 554:    */  public static final String escapeCsv(String input)
/* 555:    */  {
/* 556:556 */    return ESCAPE_CSV.translate(input);
/* 557:    */  }
/* 558:    */  
/* 580:    */  public static final String unescapeCsv(String input)
/* 581:    */  {
/* 582:582 */    return UNESCAPE_CSV.translate(input);
/* 583:    */  }
/* 584:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.StringEscapeUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */