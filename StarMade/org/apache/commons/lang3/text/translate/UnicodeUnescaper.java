package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

public class UnicodeUnescaper
  extends CharSequenceTranslator
{
  public int translate(CharSequence input, int index, Writer out)
    throws IOException
  {
    if ((input.charAt(index) == '\\') && (index + 1 < input.length()) && (input.charAt(index + 1) == 'u'))
    {
      for (int local_i = 2; (index + local_i < input.length()) && (input.charAt(index + local_i) == 'u'); local_i++) {}
      if ((index + local_i < input.length()) && (input.charAt(index + local_i) == '+')) {
        local_i++;
      }
      if (index + local_i + 4 <= input.length())
      {
        CharSequence unicode = input.subSequence(index + local_i, index + local_i + 4);
        try
        {
          int value = Integer.parseInt(unicode.toString(), 16);
          out.write((char)value);
        }
        catch (NumberFormatException value)
        {
          throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, value);
        }
        return local_i + 4;
      }
      throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.translate.UnicodeUnescaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */