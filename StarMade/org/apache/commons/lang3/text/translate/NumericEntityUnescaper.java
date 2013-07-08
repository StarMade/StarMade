/*   1:    */package org.apache.commons.lang3.text.translate;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import java.util.Arrays;
/*   6:    */import java.util.EnumSet;
/*   7:    */
/*  28:    */public class NumericEntityUnescaper
/*  29:    */  extends CharSequenceTranslator
/*  30:    */{
/*  31:    */  private final EnumSet<OPTION> options;
/*  32:    */  
/*  33:    */  public static enum OPTION
/*  34:    */  {
/*  35: 35 */    semiColonRequired,  semiColonOptional,  errorIfNoSemiColon;
/*  36:    */    
/*  45:    */    private OPTION() {}
/*  46:    */  }
/*  47:    */  
/*  55:    */  public NumericEntityUnescaper(OPTION... options)
/*  56:    */  {
/*  57: 57 */    if (options.length > 0) {
/*  58: 58 */      this.options = EnumSet.copyOf(Arrays.asList(options));
/*  59:    */    } else {
/*  60: 60 */      this.options = EnumSet.copyOf(Arrays.asList(new OPTION[] { OPTION.semiColonRequired }));
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  69:    */  public boolean isSet(OPTION option)
/*  70:    */  {
/*  71: 71 */    return this.options == null ? false : this.options.contains(option);
/*  72:    */  }
/*  73:    */  
/*  76:    */  public int translate(CharSequence input, int index, Writer out)
/*  77:    */    throws IOException
/*  78:    */  {
/*  79: 79 */    int seqEnd = input.length();
/*  80:    */    
/*  81: 81 */    if ((input.charAt(index) == '&') && (index < seqEnd - 2) && (input.charAt(index + 1) == '#')) {
/*  82: 82 */      int start = index + 2;
/*  83: 83 */      boolean isHex = false;
/*  84:    */      
/*  85: 85 */      char firstChar = input.charAt(start);
/*  86: 86 */      if ((firstChar == 'x') || (firstChar == 'X')) {
/*  87: 87 */        start++;
/*  88: 88 */        isHex = true;
/*  89:    */        
/*  91: 91 */        if (start == seqEnd) {
/*  92: 92 */          return 0;
/*  93:    */        }
/*  94:    */      }
/*  95:    */      
/*  96: 96 */      int end = start;
/*  97:    */      
/*  98: 98 */      while ((end < seqEnd) && (((input.charAt(end) >= '0') && (input.charAt(end) <= '9')) || ((input.charAt(end) >= 'a') && (input.charAt(end) <= 'f')) || ((input.charAt(end) >= 'A') && (input.charAt(end) <= 'F'))))
/*  99:    */      {
/* 102:102 */        end++;
/* 103:    */      }
/* 104:    */      
/* 105:105 */      boolean semiNext = (end != seqEnd) && (input.charAt(end) == ';');
/* 106:    */      
/* 107:107 */      if (!semiNext) {
/* 108:108 */        if (isSet(OPTION.semiColonRequired)) {
/* 109:109 */          return 0;
/* 110:    */        }
/* 111:111 */        if (isSet(OPTION.errorIfNoSemiColon)) {
/* 112:112 */          throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
/* 113:    */        }
/* 114:    */      }
/* 115:    */      int entityValue;
/* 116:    */      try {
/* 117:    */        int entityValue;
/* 118:118 */        if (isHex) {
/* 119:119 */          entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 16);
/* 120:    */        } else {
/* 121:121 */          entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 10);
/* 122:    */        }
/* 123:    */      } catch (NumberFormatException nfe) {
/* 124:124 */        return 0;
/* 125:    */      }
/* 126:    */      
/* 127:127 */      if (entityValue > 65535) {
/* 128:128 */        char[] chrs = Character.toChars(entityValue);
/* 129:129 */        out.write(chrs[0]);
/* 130:130 */        out.write(chrs[1]);
/* 131:    */      } else {
/* 132:132 */        out.write(entityValue);
/* 133:    */      }
/* 134:    */      
/* 135:135 */      return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
/* 136:    */    }
/* 137:137 */    return 0;
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.NumericEntityUnescaper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */