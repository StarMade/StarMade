/*   1:    */package org.apache.commons.lang3.text.translate;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */
/*  36:    */public class NumericEntityEscaper
/*  37:    */  extends CodePointTranslator
/*  38:    */{
/*  39:    */  private final int below;
/*  40:    */  private final int above;
/*  41:    */  private final boolean between;
/*  42:    */  
/*  43:    */  private NumericEntityEscaper(int below, int above, boolean between)
/*  44:    */  {
/*  45: 45 */    this.below = below;
/*  46: 46 */    this.above = above;
/*  47: 47 */    this.between = between;
/*  48:    */  }
/*  49:    */  
/*  52:    */  public NumericEntityEscaper()
/*  53:    */  {
/*  54: 54 */    this(0, 2147483647, true);
/*  55:    */  }
/*  56:    */  
/*  62:    */  public static NumericEntityEscaper below(int codepoint)
/*  63:    */  {
/*  64: 64 */    return outsideOf(codepoint, 2147483647);
/*  65:    */  }
/*  66:    */  
/*  72:    */  public static NumericEntityEscaper above(int codepoint)
/*  73:    */  {
/*  74: 74 */    return outsideOf(0, codepoint);
/*  75:    */  }
/*  76:    */  
/*  83:    */  public static NumericEntityEscaper between(int codepointLow, int codepointHigh)
/*  84:    */  {
/*  85: 85 */    return new NumericEntityEscaper(codepointLow, codepointHigh, true);
/*  86:    */  }
/*  87:    */  
/*  94:    */  public static NumericEntityEscaper outsideOf(int codepointLow, int codepointHigh)
/*  95:    */  {
/*  96: 96 */    return new NumericEntityEscaper(codepointLow, codepointHigh, false);
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
/* 114:114 */    out.write("&#");
/* 115:115 */    out.write(Integer.toString(codepoint, 10));
/* 116:116 */    out.write(59);
/* 117:117 */    return true;
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.NumericEntityEscaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */