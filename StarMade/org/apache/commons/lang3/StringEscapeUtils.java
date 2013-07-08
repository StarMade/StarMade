package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
import org.apache.commons.lang3.text.translate.OctalUnescaper;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

public class StringEscapeUtils
{
  public static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(new String[][] { { "\"", "\\\"" }, { "\\", "\\\\" } }).with(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()) }).with(new CharSequenceTranslator[] { UnicodeEscaper.outsideOf(32, 127) });
  public static final CharSequenceTranslator ESCAPE_ECMASCRIPT = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(new String[][] { { "'", "\\'" }, { "\"", "\\\"" }, { "\\", "\\\\" }, { "/", "\\/" } }), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()), UnicodeEscaper.outsideOf(32, 127) });
  public static final CharSequenceTranslator ESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.APOS_ESCAPE()) });
  public static final CharSequenceTranslator ESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()) });
  public static final CharSequenceTranslator ESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE()) });
  public static final CharSequenceTranslator ESCAPE_CSV = new CsvEscaper();
  public static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new CharSequenceTranslator[] { new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()), new LookupTranslator(new String[][] { { "\\\\", "\\" }, { "\\\"", "\"" }, { "\\'", "'" }, { "\\", "" } }) });
  public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT = UNESCAPE_JAVA;
  public static final CharSequenceTranslator UNESCAPE_HTML3 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
  public static final CharSequenceTranslator UNESCAPE_HTML4 = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
  public static final CharSequenceTranslator UNESCAPE_XML = new AggregateTranslator(new CharSequenceTranslator[] { new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.APOS_UNESCAPE()), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]) });
  public static final CharSequenceTranslator UNESCAPE_CSV = new CsvUnescaper();
  
  public static final String escapeJava(String input)
  {
    return ESCAPE_JAVA.translate(input);
  }
  
  public static final String escapeEcmaScript(String input)
  {
    return ESCAPE_ECMASCRIPT.translate(input);
  }
  
  public static final String unescapeJava(String input)
  {
    return UNESCAPE_JAVA.translate(input);
  }
  
  public static final String unescapeEcmaScript(String input)
  {
    return UNESCAPE_ECMASCRIPT.translate(input);
  }
  
  public static final String escapeHtml4(String input)
  {
    return ESCAPE_HTML4.translate(input);
  }
  
  public static final String escapeHtml3(String input)
  {
    return ESCAPE_HTML3.translate(input);
  }
  
  public static final String unescapeHtml4(String input)
  {
    return UNESCAPE_HTML4.translate(input);
  }
  
  public static final String unescapeHtml3(String input)
  {
    return UNESCAPE_HTML3.translate(input);
  }
  
  public static final String escapeXml(String input)
  {
    return ESCAPE_XML.translate(input);
  }
  
  public static final String unescapeXml(String input)
  {
    return UNESCAPE_XML.translate(input);
  }
  
  public static final String escapeCsv(String input)
  {
    return ESCAPE_CSV.translate(input);
  }
  
  public static final String unescapeCsv(String input)
  {
    return UNESCAPE_CSV.translate(input);
  }
  
  static class CsvUnescaper
    extends CharSequenceTranslator
  {
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '"';
    private static final String CSV_QUOTE_STR = String.valueOf('"');
    private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
    
    public int translate(CharSequence input, int index, Writer out)
      throws IOException
    {
      if (index != 0) {
        throw new IllegalStateException("CsvUnescaper should never reach the [1] index");
      }
      if ((input.charAt(0) != '"') || (input.charAt(input.length() - 1) != '"'))
      {
        out.write(input.toString());
        return input.length();
      }
      String quoteless = input.subSequence(1, input.length() - 1).toString();
      if (StringUtils.containsAny(quoteless, CSV_SEARCH_CHARS)) {
        out.write(StringUtils.replace(quoteless, CSV_QUOTE_STR + CSV_QUOTE_STR, CSV_QUOTE_STR));
      } else {
        out.write(input.toString());
      }
      return input.length();
    }
  }
  
  static class CsvEscaper
    extends CharSequenceTranslator
  {
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '"';
    private static final String CSV_QUOTE_STR = String.valueOf('"');
    private static final char[] CSV_SEARCH_CHARS = { ',', '"', '\r', '\n' };
    
    public int translate(CharSequence input, int index, Writer out)
      throws IOException
    {
      if (index != 0) {
        throw new IllegalStateException("CsvEscaper should never reach the [1] index");
      }
      if (StringUtils.containsNone(input.toString(), CSV_SEARCH_CHARS))
      {
        out.write(input.toString());
      }
      else
      {
        out.write(34);
        out.write(StringUtils.replace(input.toString(), CSV_QUOTE_STR, CSV_QUOTE_STR + CSV_QUOTE_STR));
        out.write(34);
      }
      return input.length();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.StringEscapeUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */