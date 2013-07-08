/*  1:   */package org.apache.commons.lang3.text.translate;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */
/* 31:   */public class UnicodeUnescaper
/* 32:   */  extends CharSequenceTranslator
/* 33:   */{
/* 34:   */  public int translate(CharSequence input, int index, Writer out)
/* 35:   */    throws IOException
/* 36:   */  {
/* 37:37 */    if ((input.charAt(index) == '\\') && (index + 1 < input.length()) && (input.charAt(index + 1) == 'u'))
/* 38:   */    {
/* 39:39 */      int i = 2;
/* 40:40 */      while ((index + i < input.length()) && (input.charAt(index + i) == 'u')) {
/* 41:41 */        i++;
/* 42:   */      }
/* 43:   */      
/* 44:44 */      if ((index + i < input.length()) && (input.charAt(index + i) == '+')) {
/* 45:45 */        i++;
/* 46:   */      }
/* 47:   */      
/* 48:48 */      if (index + i + 4 <= input.length())
/* 49:   */      {
/* 50:50 */        CharSequence unicode = input.subSequence(index + i, index + i + 4);
/* 51:   */        try
/* 52:   */        {
/* 53:53 */          int value = Integer.parseInt(unicode.toString(), 16);
/* 54:54 */          out.write((char)value);
/* 55:   */        } catch (NumberFormatException nfe) {
/* 56:56 */          throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, nfe);
/* 57:   */        }
/* 58:58 */        return i + 4;
/* 59:   */      }
/* 60:60 */      throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
/* 61:   */    }
/* 62:   */    
/* 64:64 */    return 0;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.UnicodeUnescaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */