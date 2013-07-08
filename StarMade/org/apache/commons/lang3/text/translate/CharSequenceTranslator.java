/*   1:    */package org.apache.commons.lang3.text.translate;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.StringWriter;
/*   5:    */import java.io.Writer;
/*   6:    */import java.util.Locale;
/*   7:    */
/*  47:    */public abstract class CharSequenceTranslator
/*  48:    */{
/*  49:    */  public abstract int translate(CharSequence paramCharSequence, int paramInt, Writer paramWriter)
/*  50:    */    throws IOException;
/*  51:    */  
/*  52:    */  public final String translate(CharSequence input)
/*  53:    */  {
/*  54: 54 */    if (input == null) {
/*  55: 55 */      return null;
/*  56:    */    }
/*  57:    */    try {
/*  58: 58 */      StringWriter writer = new StringWriter(input.length() * 2);
/*  59: 59 */      translate(input, writer);
/*  60: 60 */      return writer.toString();
/*  61:    */    }
/*  62:    */    catch (IOException ioe) {
/*  63: 63 */      throw new RuntimeException(ioe);
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  73:    */  public final void translate(CharSequence input, Writer out)
/*  74:    */    throws IOException
/*  75:    */  {
/*  76: 76 */    if (out == null) {
/*  77: 77 */      throw new IllegalArgumentException("The Writer must not be null");
/*  78:    */    }
/*  79: 79 */    if (input == null) {
/*  80: 80 */      return;
/*  81:    */    }
/*  82: 82 */    int pos = 0;
/*  83: 83 */    int len = input.length();
/*  84: 84 */    while (pos < len) {
/*  85: 85 */      int consumed = translate(input, pos, out);
/*  86: 86 */      if (consumed == 0) {
/*  87: 87 */        char[] c = Character.toChars(Character.codePointAt(input, pos));
/*  88: 88 */        out.write(c);
/*  89: 89 */        pos += c.length;
/*  91:    */      }
/*  92:    */      else
/*  93:    */      {
/*  94: 94 */        for (int pt = 0; pt < consumed; pt++) {
/*  95: 95 */          pos += Character.charCount(Character.codePointAt(input, pos));
/*  96:    */        }
/*  97:    */      }
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 106:    */  public final CharSequenceTranslator with(CharSequenceTranslator... translators)
/* 107:    */  {
/* 108:108 */    CharSequenceTranslator[] newArray = new CharSequenceTranslator[translators.length + 1];
/* 109:109 */    newArray[0] = this;
/* 110:110 */    System.arraycopy(translators, 0, newArray, 1, translators.length);
/* 111:111 */    return new AggregateTranslator(newArray);
/* 112:    */  }
/* 113:    */  
/* 120:    */  public static String hex(int codepoint)
/* 121:    */  {
/* 122:122 */    return Integer.toHexString(codepoint).toUpperCase(Locale.ENGLISH);
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.CharSequenceTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */