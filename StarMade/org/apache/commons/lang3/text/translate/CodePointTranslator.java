/*  1:   */package org.apache.commons.lang3.text.translate;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */
/* 31:   */public abstract class CodePointTranslator
/* 32:   */  extends CharSequenceTranslator
/* 33:   */{
/* 34:   */  public final int translate(CharSequence input, int index, Writer out)
/* 35:   */    throws IOException
/* 36:   */  {
/* 37:37 */    int codepoint = Character.codePointAt(input, index);
/* 38:38 */    boolean consumed = translate(codepoint, out);
/* 39:39 */    if (consumed) {
/* 40:40 */      return 1;
/* 41:   */    }
/* 42:42 */    return 0;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public abstract boolean translate(int paramInt, Writer paramWriter)
/* 46:   */    throws IOException;
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.CodePointTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */