/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*  52:    */public class CharSequenceUtils
/*  53:    */{
/*  54:    */  public static CharSequence subSequence(CharSequence cs, int start)
/*  55:    */  {
/*  56: 56 */    return cs == null ? null : cs.subSequence(start, cs.length());
/*  57:    */  }
/*  58:    */  
/*  68:    */  static int indexOf(CharSequence cs, int searchChar, int start)
/*  69:    */  {
/*  70: 70 */    if ((cs instanceof String)) {
/*  71: 71 */      return ((String)cs).indexOf(searchChar, start);
/*  72:    */    }
/*  73: 73 */    int sz = cs.length();
/*  74: 74 */    if (start < 0) {
/*  75: 75 */      start = 0;
/*  76:    */    }
/*  77: 77 */    for (int i = start; i < sz; i++) {
/*  78: 78 */      if (cs.charAt(i) == searchChar) {
/*  79: 79 */        return i;
/*  80:    */      }
/*  81:    */    }
/*  82: 82 */    return -1;
/*  83:    */  }
/*  84:    */  
/*  93:    */  static int indexOf(CharSequence cs, CharSequence searchChar, int start)
/*  94:    */  {
/*  95: 95 */    return cs.toString().indexOf(searchChar.toString(), start);
/*  96:    */  }
/*  97:    */  
/* 115:    */  static int lastIndexOf(CharSequence cs, int searchChar, int start)
/* 116:    */  {
/* 117:117 */    if ((cs instanceof String)) {
/* 118:118 */      return ((String)cs).lastIndexOf(searchChar, start);
/* 119:    */    }
/* 120:120 */    int sz = cs.length();
/* 121:121 */    if (start < 0) {
/* 122:122 */      return -1;
/* 123:    */    }
/* 124:124 */    if (start >= sz) {
/* 125:125 */      start = sz - 1;
/* 126:    */    }
/* 127:127 */    for (int i = start; i >= 0; i--) {
/* 128:128 */      if (cs.charAt(i) == searchChar) {
/* 129:129 */        return i;
/* 130:    */      }
/* 131:    */    }
/* 132:132 */    return -1;
/* 133:    */  }
/* 134:    */  
/* 143:    */  static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start)
/* 144:    */  {
/* 145:145 */    return cs.toString().lastIndexOf(searchChar.toString(), start);
/* 146:    */  }
/* 147:    */  
/* 162:    */  static char[] toCharArray(CharSequence cs)
/* 163:    */  {
/* 164:164 */    if ((cs instanceof String)) {
/* 165:165 */      return ((String)cs).toCharArray();
/* 166:    */    }
/* 167:167 */    int sz = cs.length();
/* 168:168 */    char[] array = new char[cs.length()];
/* 169:169 */    for (int i = 0; i < sz; i++) {
/* 170:170 */      array[i] = cs.charAt(i);
/* 171:    */    }
/* 172:172 */    return array;
/* 173:    */  }
/* 174:    */  
/* 187:    */  static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length)
/* 188:    */  {
/* 189:189 */    if (((cs instanceof String)) && ((substring instanceof String))) {
/* 190:190 */      return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
/* 191:    */    }
/* 192:    */    
/* 193:193 */    return cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);
/* 194:    */  }
/* 195:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSequenceUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */