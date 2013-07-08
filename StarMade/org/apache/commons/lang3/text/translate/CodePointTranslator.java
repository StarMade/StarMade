package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

public abstract class CodePointTranslator
  extends CharSequenceTranslator
{
  public final int translate(CharSequence input, int index, Writer out)
    throws IOException
  {
    int codepoint = Character.codePointAt(input, index);
    boolean consumed = translate(codepoint, out);
    if (consumed) {
      return 1;
    }
    return 0;
  }
  
  public abstract boolean translate(int paramInt, Writer paramWriter)
    throws IOException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.translate.CodePointTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */