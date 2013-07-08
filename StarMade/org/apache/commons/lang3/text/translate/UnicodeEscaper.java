/*   1:    */package org.apache.commons.lang3.text.translate;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */
/*  29:    */public class UnicodeEscaper
/*  30:    */  extends CodePointTranslator
/*  31:    */{
/*  32:    */  private final int below;
/*  33:    */  private final int above;
/*  34:    */  private final boolean between;
/*  35:    */  
/*  36:    */  public UnicodeEscaper()
/*  37:    */  {
/*  38: 38 */    this(0, 2147483647, true);
/*  39:    */  }
/*  40:    */  
/*  50:    */  private UnicodeEscaper(int below, int above, boolean between)
/*  51:    */  {
/*  52: 52 */    this.below = below;
/*  53: 53 */    this.above = above;
/*  54: 54 */    this.between = between;
/*  55:    */  }
/*  56:    */  
/*  62:    */  public static UnicodeEscaper below(int codepoint)
/*  63:    */  {
/*  64: 64 */    return outsideOf(codepoint, 2147483647);
/*  65:    */  }
/*  66:    */  
/*  72:    */  public static UnicodeEscaper above(int codepoint)
/*  73:    */  {
/*  74: 74 */    return outsideOf(0, codepoint);
/*  75:    */  }
/*  76:    */  
/*  83:    */  public static UnicodeEscaper outsideOf(int codepointLow, int codepointHigh)
/*  84:    */  {
/*  85: 85 */    return new UnicodeEscaper(codepointLow, codepointHigh, false);
/*  86:    */  }
/*  87:    */  
/*  94:    */  public static UnicodeEscaper between(int codepointLow, int codepointHigh)
/*  95:    */  {
/*  96: 96 */    return new UnicodeEscaper(codepointLow, codepointHigh, true);
/*  97:    */  }
/*  98:    */  
/* 101:    */  public boolean translate(int codepoint, Writer out)
/* 102:    */    throws IOException
/* 103:    */  {
/* 104:104 */    if (this.between) {
/* 105:105 */      if ((codepoint < this.below) || (codepoint > this.above)) {
/* 106:106 */        return false;
/* 107:    */      }
/* 108:    */    }
/* 109:109 */    else if ((codepoint >= this.below) && (codepoint <= this.above)) {
/* 110:110 */      return false;
/* 111:    */    }
/* 112:    */    
/* 115:115 */    if (codepoint > 65535)
/* 116:    */    {
/* 118:118 */      out.write("\\u" + hex(codepoint));
/* 119:119 */    } else if (codepoint > 4095) {
/* 120:120 */      out.write("\\u" + hex(codepoint));
/* 121:121 */    } else if (codepoint > 255) {
/* 122:122 */      out.write("\\u0" + hex(codepoint));
/* 123:123 */    } else if (codepoint > 15) {
/* 124:124 */      out.write("\\u00" + hex(codepoint));
/* 125:    */    } else {
/* 126:126 */      out.write("\\u000" + hex(codepoint));
/* 127:    */    }
/* 128:128 */    return true;
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.UnicodeEscaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */