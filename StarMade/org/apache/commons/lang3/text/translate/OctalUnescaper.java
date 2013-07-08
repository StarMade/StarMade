/*  1:   */package org.apache.commons.lang3.text.translate;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */
/* 33:   */public class OctalUnescaper
/* 34:   */  extends CharSequenceTranslator
/* 35:   */{
/* 36:36 */  private static int OCTAL_MAX = 377;
/* 37:   */  
/* 40:   */  public int translate(CharSequence input, int index, Writer out)
/* 41:   */    throws IOException
/* 42:   */  {
/* 43:43 */    if ((input.charAt(index) == '\\') && (index < input.length() - 1) && (Character.isDigit(input.charAt(index + 1)))) {
/* 44:44 */      int start = index + 1;
/* 45:   */      
/* 46:46 */      int end = index + 2;
/* 47:47 */      while ((end < input.length()) && (Character.isDigit(input.charAt(end)))) {
/* 48:48 */        end++;
/* 49:49 */        if (Integer.parseInt(input.subSequence(start, end).toString(), 10) > OCTAL_MAX) {
/* 50:50 */          end--;
/* 51:   */        }
/* 52:   */      }
/* 53:   */      
/* 55:55 */      out.write(Integer.parseInt(input.subSequence(start, end).toString(), 8));
/* 56:56 */      return 1 + end - start;
/* 57:   */    }
/* 58:58 */    return 0;
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.OctalUnescaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */