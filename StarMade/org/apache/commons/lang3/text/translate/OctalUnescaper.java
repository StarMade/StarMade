package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

public class OctalUnescaper
  extends CharSequenceTranslator
{
  private static int OCTAL_MAX = 377;
  
  public int translate(CharSequence input, int index, Writer out)
    throws IOException
  {
    if ((input.charAt(index) == '\\') && (index < input.length() - 1) && (Character.isDigit(input.charAt(index + 1))))
    {
      int start = index + 1;
      int end = index + 2;
      while ((end < input.length()) && (Character.isDigit(input.charAt(end))))
      {
        end++;
        if (Integer.parseInt(input.subSequence(start, end).toString(), 10) > OCTAL_MAX) {
          end--;
        }
      }
      out.write(Integer.parseInt(input.subSequence(start, end).toString(), 8));
      return 1 + end - start;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.translate.OctalUnescaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */